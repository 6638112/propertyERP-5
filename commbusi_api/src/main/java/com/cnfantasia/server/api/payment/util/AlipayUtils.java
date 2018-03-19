/**   
 * Filename:    AlipayUtils.java   
 * @version:    1.0  
 * Create at:   2014年12月8日 上午9:18:20   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年12月8日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.payment.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alipay.config.AlipayConfig;
import com.alipay.sign.RSA;
import com.alipay.util.AlipayCore;

/**
 * Filename: AlipayUtils.java
 * 
 * @version: 1.0.0 Create at: 2014年12月8日 上午9:18:20 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年12月8日 shiyl 1.0 1.0 Version
 */
public class AlipayUtils {
	private static Log logger = LogFactory.getLog(AlipayUtils.class);
	public static final String PARTNER = AlipayConfig.partner;//合作者身份id
	public static final String SELLER = AlipayConfig.WIDseller_email;
	public static final String RSA_PRIVATE = AlipayConfig.private_key;
	
	/**
	 * 
	 * @param isExpressGateway 是否为调用银行卡支付
	 * @param subject 商品名称
	 * @param body 商品详情
	 * @param price 价格
	 * @param notifyUrl 通知url
	 * @param outTradeNo 商户对外唯一订单号
	 * @return
	 */
	public static Map<String, String> getOrderInfoMap(boolean isExpressGateway,String subject, String body, String price,String notifyUrl,String outTradeNo) {
		Map<String, String> resMap = new HashMap<String, String>();
		resMap.put("partner", PARTNER);
		resMap.put("seller_id", SELLER);
		resMap.put("out_trade_no", outTradeNo);
		resMap.put("subject", subject);
		resMap.put("body", body);
		resMap.put("total_fee", price);
		resMap.put("notify_url", notifyUrl);
		resMap.put("service", "mobile.securitypay.pay");
		resMap.put("payment_type", "1");
		resMap.put("_input_charset", AlipayConfig.input_charset);
		resMap.put("it_b_pay", AlipayConfig.it_b_pay);
//		resMap.put("return_url", "");
		if(isExpressGateway){
			resMap.put("paymethod", "expressGateway");
		}
		return resMap;
	}
	
	public static Map<String, String> getSign(Map<String, String> sParaTemp,boolean withMark){
		//除去数组中的空值和签名参数
    Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
    //生成签名结果
    String prestr = createLinkString(sPara,withMark); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
  	String mysign = "";
  	{
      if(AlipayConfig.sign_type.equals("RSA") ){
      	mysign = RSA.sign(prestr, AlipayConfig.private_key, AlipayConfig.input_charset);
      }
  	}
    //签名结果与签名方式加入请求提交参数组中
  	try {
			mysign = URLEncoder.encode(mysign, AlipayConfig.input_charset);
		} catch (UnsupportedEncodingException e) {
			logger.debug("AlipayUtils.getOrderInfoAndSignMap.URLEncoder.encode "+mysign+" cause exception",e);
		}
    sPara.put("sign", mysign);
    if(AlipayConfig.sign_type.equals("RSA")){
    	sPara.put("sign_type", "RSA");
    }
  	return sPara;
	}
	
	public static Map<String, String> getOrderInfoAndSignMap(boolean isExpressGateway,String subject, String body, String price,String notifyUrl,String outTradeNo) {
		Map<String, String> sParaTemp = getOrderInfoMap(isExpressGateway, subject, body, price, notifyUrl, outTradeNo);
		Map<String, String> sPara = getSign(sParaTemp,true);
    return sPara;
	}
	
//	public static String getOrderInfoAndSignString(boolean isExpressGateway,String subject, String body, String price,String notifyUrl,String outTradeNo) {
//		Map<String, String> sPara = getOrderInfoAndSignMap(isExpressGateway, subject, body, price, notifyUrl, outTradeNo);
//		String resStr = createLinkStringWithMark(sPara); 
//		return resStr;
//	}
	
//	/**
//	 * create the order info. 创建订单信息
//	 * @param subject 商品名称
//	 * @param body 商品详情
//	 * @param price 商品金额
//	 * @param notifyUrl 服务器异步通知页面路径
//	 * @param outTradeNo 商户网站唯一订单号
//	 * @return
//	 */
//	public String getOrderInfo(String subject, String body, String price,String notifyUrl,String outTradeNo) {
//		// 合作者身份ID(不可空)
//		String orderInfo = "partner=" + "\"" + PARTNER + "\"";
//		// 卖家支付宝账号
//		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
//		// 商户网站唯一订单号
//		orderInfo += "&out_trade_no=" + "\"" + outTradeNo + "\"";
//		// 商品名称
//		orderInfo += "&subject=" + "\"" + subject + "\"";
//		// 商品详情
//		orderInfo += "&body=" + "\"" + body + "\"";
//		// 商品金额
//		orderInfo += "&total_fee=" + "\"" + price + "\"";
//		// 服务器异步通知页面路径
//		orderInfo += "&notify_url=" + "\"" + notifyUrl + "\"";
//		// 接口名称， 固定值(不可空)
//		orderInfo += "&service=\"mobile.securitypay.pay\"";
//		// 支付类型， 固定值
//		orderInfo += "&payment_type=\"1\"";
//		// 参数编码， 固定值
//		orderInfo += "&_input_charset=\"utf-8\"";
//
//		// 设置未付款交易的超时时间
//		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
//		// 取值范围：1m～15d。
//		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
//		// 该参数数值不接受小数点，如1.5h，可转换为90m。
//		orderInfo += "&it_b_pay=\"30m\"";
//
//		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
//		orderInfo += "&return_url=\"\"";
//
//		// 调用银行卡支付，需配置此参数，参与签名， 固定值
//		// orderInfo += "&paymethod=\"expressGateway\"";
//
//		return orderInfo;
//	}

//	/**
//	 * get the out_trade_no for an order. 获取外部订单号
//	 * 
//	 */
//	public String getOutTradeNo() {
//		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
//		Date date = new Date();
//		String key = format.format(date);
//
//		Random r = new Random();
//		key = key + r.nextInt();
//		key = key.substring(0, 15);
//		return key;
//	}
//
//	/**
//	 * sign the order info. 对订单信息进行签名
//	 * 
//	 * @param content
//	 *          待签名订单信息
//	 */
//	public String sign(String content) {
//		return SignUtils.sign(content, RSA_PRIVATE);
//	}

//	/**
//	 * get the sign type we use. 获取签名方式
//	 * 
//	 */
//	public String getSignType() {
//		return "sign_type=\"RSA\"";
//	}
	
	/** 
   * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
   * @param params 需要排序并参与字符拼接的参数组
   * @return 拼接后字符串
   */
	public static String createLinkString(Map<String, String> params, boolean withMark) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				if (withMark) {
					prestr = prestr + key + "=" + "\"" + value + "\"";
				} else {
					prestr = prestr + key + "=" + value;
				}
			} else {
				if (withMark) {
					prestr = prestr + key + "=" + "\"" + value + "\"" + "&";
				} else {
					prestr = prestr + key + "=" + value + "&";
				}
			}
		}

		return prestr;
	}
}
