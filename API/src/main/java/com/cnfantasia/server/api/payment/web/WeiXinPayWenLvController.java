/**   
* Filename:    WeiXinPayWenLvController.java   
* @version:    1.0  
* Create at:   2014年12月16日 上午7:06:24   
* Description:  
*   
* Modification History:   文旅微信支付
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年03月10日    wenfq      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil;
import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil.WeiXinPay_GoalAccType;
import com.cnfantasia.server.api.payment.entity.OrderPayInfo;
import com.cnfantasia.server.api.payment.entity.WeiXinLightAppNotifyEntity;
import com.cnfantasia.server.api.payment.serviceUntran.ICommPayService;
import com.cnfantasia.server.api.payment.serviceUntran.IWeiXinLightAppPayService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.tenpay.util.MD5Util;
import com.tenpay.util.WXUtil;
import com.tenpay.util.XMLUtil;

/**
 * Filename:    WeiXinPayWenLvController.java
 * @version:    1.0.0
 * Create at:   2015年03月10日 上午7:06:24
 * Description:
 *
 * Modification History: 文旅微信支付
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年03月10日       wenfq             1.0             1.0 Version
 */
@Controller
@RequestMapping("/weixinPayWenLv")
public class WeiXinPayWenLvController extends BaseController /*AbstractWeiXinPaymentController*/{
	private Log logger = LogFactory.getLog(getClass());

	private static final String payNotifyUrl = "weixinPayWenLv/payNotify.json?";

	private IWeiXinLightAppPayService weiXinLightAppPayService;

	public void setWeiXinLightAppPayService(IWeiXinLightAppPayService weiXinLightAppPayService) {
		this.weiXinLightAppPayService = weiXinLightAppPayService;
	}

	private ISysParamManager sysParamManager;

	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}

	private ICommPayService commPayService;

	public void setCommPayService(ICommPayService commPayService) {
		this.commPayService = commPayService;
	}

	/**
	 * 支付请求
	 * 
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 * @throws JDOMException
	 */
	@RequestMapping("/prePayRequest.json")
	@ResponseBody
	public JsonResponse prePayRequest(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException, JDOMException {
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		OrderPayInfo orderPayInfo = commPayService.getOrderPayInfoById(userId, orderId);

		String postData = "https://api.mch.weixin.qq.com/pay/unifiedorder";//微信统一下单地址
		HttpPost httpPost = new HttpPost(postData);
		
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WeiXinPayConstantUtil.getAPP_ID(WeiXinPay_GoalAccType.WenLv_i));
		parameters.put("mch_id", WeiXinPayConstantUtil.getPARTNER(WeiXinPay_GoalAccType.WenLv_i));
		parameters.put("nonce_str", WXUtil.getNonceStr());
		parameters.put("body", orderPayInfo.getProductDetail());
		parameters.put("out_trade_no", orderPayInfo.getOutTradeNo());
		parameters.put("total_fee", orderPayInfo.getTotalAmount() + "");
		parameters.put("spbill_create_ip", request.getRemoteAddr());
		parameters.put("notify_url", this.getNotifyUrl());
		parameters.put("trade_type", "JSAPI");
		parameters.put("openid", request.getParameter("openid"));
		parameters.put("sign", createMD5Sign("UTF-8", parameters));
		String reqXml = getRequestXml(parameters);
		logger.info("reqXml: " + reqXml);
		String packageData = new String(reqXml.getBytes(), "ISO8859-1");

		{//post param
			HttpEntity entity = new StringEntity(packageData);
			httpPost.setEntity(entity);
		}

		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse chResponse = client.execute(httpPost);

		HttpEntity entity = chResponse.getEntity();
		String res = "";
		if (entity != null) {
			if (chResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				res = EntityUtils.toString(entity, "UTF-8");
				request.setAttribute("prepayResponse", XMLUtil.doXMLParse(res));
				logger.info("prepay response's content: " + res);
			}
		}

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(XMLUtil.doXMLParse(res));
		return jsonResponse;
	}

	@RequestMapping("/payNotify.json")
	@ResponseBody
	public JsonResponse payNotify(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String s = null;
		while ((s = bf.readLine()) != null) {
			sb.append(s);
		}
		Map requestMap = XMLUtil.doXMLParse(sb.toString());
		/* 支付通知
		 <xml>
		<appid><![CDATA[wx8888888888888888]]></appid>  
		<bank_type><![CDATA[CFT]]></bank_type>  
		<fee_type><![CDATA[CNY]]></fee_type>  
		<is_subscribe><![CDATA[Y]]></is_subscribe>  
		<mch_id><![CDATA[10012345]]></mch_id>  
		<nonce_str><![CDATA[60uf9sh6nmppr9azveb2bn7arhy79izk]]></nonce_str>  
		<openid><![CDATA[ou9dHt0L8qFLI1foP-kj5x1mDWsM]]></openid>  
		<out_trade_no><![CDATA[wx88888888888888881414411779]]></out_trade_no>  
		<result_code><![CDATA[SUCCESS]]></result_code>  
		<return_code><![CDATA[SUCCESS]]></return_code>  
		<sign><![CDATA[0C1D7F2534F1473247550A5A138F0CEB]]></sign>  
		<sub_mch_id><![CDATA[10012345]]></sub_mch_id>  
		<time_end><![CDATA[20141027200958]]></time_end>  
		<total_fee>1</total_fee>  
		<trade_type><![CDATA[JSAPI]]></trade_type>  
		<transaction_id><![CDATA[1002750185201410270005514026]]></transaction_id> 
		</xml>
		 */
		logger.info("pay notify result is: " + sb.toString());
		//{is_subscribe=Y, appid=wx681fb1b637c7c74f, fee_type=CNY, nonce_str=d5756748da7d4fc61bb0b1bcba6e6d4d, out_trade_no=20150311125950152, transaction_id=1010200652201503110030223565, trade_type=JSAPI, result_code=SUCCESS, sign=870A4ACDE06E824FC054A235605C6429, mch_id=1226657802, total_fee=2, time_end=20150311130109, openid=o33fQtzx0kf6e3FNvFJXhAF_rcdY, bank_type=PAB_CREDIT, return_code=SUCCESS, cash_fee=2}

		WeiXinLightAppNotifyEntity currWeiXinNotifyEntity = JSON.parseObject(JSON.toJSONString(requestMap), WeiXinLightAppNotifyEntity.class);
		logger.info("the order's number is: " + currWeiXinNotifyEntity.getOut_trade_no());
		weiXinLightAppPayService.payNotify(currWeiXinNotifyEntity);
		return null;
	}

	public String getNotifyUrl() {
		String baseUrl = sysParamManager.getSysParaValue(SysParamKey.PAYNOTIFY_BASEURL);
		baseUrl = baseUrl.replace("API510", "API");
		return baseUrl + payNotifyUrl;
	}

	public void payNotify(WeiXinLightAppNotifyEntity resultEntity) {
		weiXinLightAppPayService.payNotify(resultEntity);
	}

//	public void addNotifyRecord(WeiXinLightAppNotifyEntity currWeiXinNotifyEntity, String payErrInfo, BigInteger orderId) {
//		weiXinLightAppPayService.addNotifyRecord(currWeiXinNotifyEntity, payErrInfo, orderId);
//	}
	
	/**
	 * @Description：sign签名
	 * @param characterEncoding
	 *            编码格式
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	private String createMD5Sign(String characterEncoding, SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + WeiXinPayConstantUtil.getPARTNER_KEY(WeiXinPay_GoalAccType.WenLv_i));
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}

	/**
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	private String getRequestXml(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
}
