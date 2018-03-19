package com.tenpay.util;

import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil;
import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil.WeiXinPay_GoalAccType;

public class ConstantUtil {
	/**
	 * 商家可以考虑读取配置文件
	 */
	
//	//初始化
//	public static String APP_ID = "wxef9afd077ebdbdca";//微信开发平台应用id
//	public static String APP_SECRET = "b3a84081381a6f3c8ca91df28c6081c7";//应用对应的凭证
//	//应用对应的密钥
//	public static String APP_KEY = "FWYuA61dSqgxi4jZ5myUu1waw2AN9B1GLbrvShSwU2JouTJQfA6ImVKn8K2ek4pUK2G1fSEzoHdoMcIq5FoSr72RcOlMyOj1i3gPyALF5dZYBFeKUpLVQU4gKMuwuksN";
//	public static String PARTNER = "1219483001";//财付通商户号
//	public static String PARTNER_KEY = "95dc7a47b2173d457155502ace256df2";//商户号对应的密钥
	
	//初始化
	public static String getAPP_ID(WeiXinPay_GoalAccType goalAccType){//微信开发平台应用id
		return WeiXinPayConstantUtil.getAPP_ID(goalAccType);
	}
	
	public static String getAPP_SECRET(WeiXinPay_GoalAccType goalAccType){//应用对应的凭证
		return WeiXinPayConstantUtil.getAPP_SECRET(goalAccType);
	}
	
	public static String getAPP_KEY(WeiXinPay_GoalAccType goalAccType){//应用对应的密钥
		return WeiXinPayConstantUtil.getAPP_KEY(goalAccType);
	}
	
	public static String getPARTNER(WeiXinPay_GoalAccType goalAccType){//财付通商户号
		return WeiXinPayConstantUtil.getPARTNER(goalAccType);
	}
	
	public static String getPARTNER_KEY(WeiXinPay_GoalAccType goalAccType){//商户号对应的密钥
		return WeiXinPayConstantUtil.getPARTNER_KEY(goalAccType);
	}
	
	public static final String TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";//获取access_token对应的url
	public static final String GRANT_TYPE = "client_credential";//常量固定值
	public static final String EXPIRE_ERRCODE = "42001";//access_token失效后请求返回的errcode
	public static final String FAIL_ERRCODE = "40001";//重复获取导致上一次获取的access_token失效,返回错误码
	public static final String GATEURL = "https://api.weixin.qq.com/pay/genprepay?access_token=";//获取预支付id的接口url
	public static final String ACCESS_TOKEN = "access_token";//access_token常量值
	public static final String ERRORCODE = "errcode";//用来判断access_token是否失效的值
	public static final String SIGN_METHOD = "sha1";//签名算法常量值
	
	//package常量值
//	public static String packageValue = "bank_type=WX&body=%B2%E2%CA%D4&fee_type=1&input_charset=GBK&notify_url=http%3A%2F%2F127.0.0.1%3A8180%2Ftenpay_api_b2c%2FpayNotifyUrl.jsp&out_trade_no=2051571832&partner=1900000109&sign=10DA99BCB3F63EF23E4981B331B0A3EF&spbill_create_ip=127.0.0.1&time_expire=20131222091010&total_fee=1";
//	public static String traceid = "testtraceid001";//测试用户id
	
}
