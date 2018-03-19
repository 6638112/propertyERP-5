/**   
* Filename:    WeiXinPayJieFangQuController.java   
* @version:    1.0  
* Create at:   2014年12月16日 上午7:06:24   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil;
import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil.WeiXinPay_GoalAccType;
import com.cnfantasia.server.api.payment.entity.OrderPayInfo;
import com.cnfantasia.server.api.payment.entity.WeiXinNotifyEntity;
import com.cnfantasia.server.api.payment.serviceUntran.ICommPayService;
import com.cnfantasia.server.api.payment.serviceUntran.IWeiXinPayService;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.wx.pay.WxAppCallBackVo;
import com.cnfantasia.server.business.wx.pay.WxAppPay;
import com.cnfantasia.server.business.wx.pay.WxAppPayDTO;
import com.cnfantasia.server.business.wx.pay.WxAppPayVO;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.entity.EbuyPrepayWeixinLog;
import com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.service.IEbuyPrepayWeixinLogBaseService;
import com.tenpay.ClientRequestHandler;
import com.tenpay.PackageRequestHandler;
import com.tenpay.PrepayIdRequestHandler;

/**
 * Filename:    WeiXinPayJieFangQuController.java
 * @version:    1.0.0
 * Create at:   2014年12月16日 上午7:06:24
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月16日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/weixinPayJieFangQu")
public class WeiXinPayJieFangQuController extends AbstractWeiXinPaymentController{

	private Log logger = LogFactory.getLog(getClass());

	private static final String payNotifyUrl = "weixinPayJieFangQu/payNotify.json?";

	private IWeiXinPayService weiXinPayService;
	public void setWeiXinPayService(IWeiXinPayService weiXinPayService) {
		this.weiXinPayService = weiXinPayService;
	}

	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}

	@Resource
	private ICommPayService commPayService;

	@Resource
	private IEbuyPrepayWeixinLogBaseService ebuyPrepayWeixinLogBaseService;

	@Resource
	private IUuidManager uuidManager;

	/**
	 * 支付请求
	 * 
	 * @param request
	 */
	@RequestMapping("/prePayRequest.json")
	@ResponseBody
//	@Override
	public JsonResponse prePayRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("WeiXinPayController prePayRequest start..");
		if(StringUtils.isEmpty(request.getParameter("orderId"))){
			throw new BusinessRuntimeException("Payment.orderId.isEmpty");
		}
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		String notify_url =  getNotifyUrl();
		String remoteAddr = request.getRemoteAddr();
		OrderPayInfo orderPayInfo = commPayService.getOrderPayInfoById(userId, orderId);
		String productInfo = orderPayInfo.getProductInfo();
		Long totalAmount = orderPayInfo.getTotalAmount();
		String out_trade_no = orderPayInfo.getOutTradeNo();
		WxAppPayDTO dto = new WxAppPayDTO();
		dto.setAppId(WeiXinPayConstantUtil.JieFangQu.APP_ID);
		dto.setMchId(WeiXinPayConstantUtil.JieFangQu.PARTNER);
		dto.setNotifyUrl(notify_url);
		dto.setOutTradeNo(out_trade_no);
		dto.setTotalFee(String.valueOf(totalAmount));
		dto.setSpBillCreateIp(remoteAddr);
		dto.setBody(productInfo);
		dto.setApiKey(WeiXinPayConstantUtil.JieFangQu.PARTNER_KEY);
		WxAppPayVO wxAppPayVO = WxAppPay.buildPayInfo(dto);
		recordPrepayLog(orderId, notify_url, dto, wxAppPayVO.getResXml(), wxAppPayVO.getPrepayId());
		if (StringUtils.isEmpty(wxAppPayVO.getPrepayId())) {
			throw new BusinessRuntimeException("EbuyPayService.prePayRequest.getPrepayId.error");
		}
		JsonResponse json = new JsonResponse();
		json.putData("partnerid", WeiXinPayConstantUtil.JieFangQu.PARTNER);
		json.putData("prepayid", wxAppPayVO.getPrepayId());
		json.putData("packageValue", "Sign=WXPay");
		json.putData("noncestr", wxAppPayVO.getNonceStr());
		json.putData("timestamp", wxAppPayVO.getTimestamp());
		json.putData("sign", wxAppPayVO.getSign());
		json.putData("appid", WeiXinPayConstantUtil.JieFangQu.APP_ID);
		json.putData("appkey", WeiXinPayConstantUtil.JieFangQu.APP_KEY);
		logger.info("reqXml : " + wxAppPayVO.getReqXml() + "  ------ resXml : " + wxAppPayVO.getResXml());
		logger.info("WeiXinPayController prePayRequest end..");
		return json;
	}

	@RequestMapping("/payNotify.json")
	@ResponseBody
//	@Override
	public String payNotify(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		WxAppCallBackVo callBackVo = WxAppPay.checkCallBack(request, WeiXinPayConstantUtil.JieFangQu.APP_ID,
				WeiXinPayConstantUtil.JieFangQu.PARTNER, WeiXinPayConstantUtil.JieFangQu.PARTNER_KEY);
		String result = null;
		if (callBackVo.isValid()) {
			WeiXinNotifyEntity entity = new WeiXinNotifyEntity();
			entity.setRetcode(callBackVo.getResultCode());
			entity.setTotal_fee(Long.valueOf(callBackVo.getTotalFee()));
			entity.setTransaction_id(callBackVo.getTransId());
			entity.setOut_trade_no(callBackVo.getOutTradeNo());
			payNotify(entity);
			result = setXML(callBackVo.getTradeStatus(), "OK");
		} else {
			WeiXinNotifyEntity entity = new WeiXinNotifyEntity();
			entity.setTotal_fee(Long.valueOf(callBackVo.getTotalFee()));
			entity.setTransaction_id(callBackVo.getTransId());
			entity.setOut_trade_no(callBackVo.getOutTradeNo());
			addNotifyRecord(entity, callBackVo.getCallBackStr(), null);
			result = setXML("FAIL", "校验不通过");
		}
//		return super.payNotify(WeiXinPay_GoalAccType.JieFangQu,request, response);
		return result;
	}

	@Override
	public Map<String, Object> doWeixinPrePay(WeiXinPay_GoalAccType goalAccType,BigInteger userId, BigInteger orderId, String notify_url,
			String remoteAddr, PackageRequestHandler packageReqHandler, PrepayIdRequestHandler prepayReqHandler,
			ClientRequestHandler clientHandler) {
		return weiXinPayService.weixinPrePay(goalAccType,userId, orderId, notify_url, remoteAddr, packageReqHandler, prepayReqHandler,
				clientHandler);
	}

	@Override
	public String getNotifyUrl() {
		String baseUrl = sysParamManager.getSysParaValue(SysParamKey.PAYNOTIFY_BASEURL);
		baseUrl = baseUrl.replace("API510", "API");
		return baseUrl + payNotifyUrl;
	}

	@Override
	public void payNotify(WeiXinNotifyEntity resultEntity) {
		weiXinPayService.payNotify(resultEntity);
	}

	@Override
	public void addNotifyRecord(WeiXinNotifyEntity currWeiXinNotifyEntity, String payErrInfo, BigInteger orderId) {
		weiXinPayService.addNotifyRecord(currWeiXinNotifyEntity, payErrInfo, orderId);
	}

	private void recordPrepayLog(BigInteger orderId, String payNotifyUrl, WxAppPayDTO dto, String debugInfo, String prepayId) {

		EbuyPrepayWeixinLog log = new EbuyPrepayWeixinLog();
		BigInteger id = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_prepay_weixin_log);
		log.setId(id);
		log.setOrderId(orderId);
		log.setUserId(UserContext.getOperIdBigInteger());
		log.setNotifyUrl(payNotifyUrl);
		log.setPrepayIdParams(JSON.toJSONString(dto));
		log.setDebugInfo(debugInfo);
		log.setPrepayId(prepayId);
		ebuyPrepayWeixinLogBaseService.insertEbuyPrepayWeixinLog(log);
	}

	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code
				+ "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}
}
