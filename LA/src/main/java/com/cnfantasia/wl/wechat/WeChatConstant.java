package com.cnfantasia.wl.wechat;

import com.cnfantasia.pub.util.WeChatConfig;

/**
 * 微信相关的常量
 * 
 * @author wenfq
 * 
 */
public class WeChatConstant {


	public static String MSG_FromUserName = "FromUserName";

	public static String MSG_ToUserName = "ToUserName";

	public static String MSG_Content = "Content";

	//public static String APPID = "wx766bad973e68e42e"; //解放区Prod
	//public static String APPSECRET = "3381d213f6fbf25fe067d686a82b2bed";//解放区Prod

	/*public static String APPID = "wx07b4a96c9765b5f5"; //解放区Test
	public static String APPSECRET = "368ff94a3cdf80d011b683e4a32d5f7f";//解放区Test
*/
	public static final String APP_KEY = "WXzf201505081758Wxzf201505081758"; //解放区商户支付密钥


	/**
	 * AccessToken的请求URL
	 */
	public static String AccessTokenGetterURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WeChatConfig.APPID + "&secret=" + WeChatConfig.APPSECRET;
	/**
	 * JSAPITicket的请求URL
	 */
	public static String JSAPITicketGetterURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

	/**
	 * 获取用户信息
	 */
	public static String UserInfoGetterURL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

	/**
	 * OAuth2授权
	 */
	public static String OAuth2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	/**
	 * 发送模板消息
	 */
	public static String TEMPLATE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	/**
	 * 模板消息体，使用时请替换其中元素
	 */
	public static String TEMPLATE_MESSAGE_BODY = "{\"touser\":\"OPENID\",\"template_id\":\"TEMPLATE_ID\",\"url\":\"URL\",\"topcolor\":\"#FF0000\",\"data\":DATA}";

	/**
	 * AccessToken和JSAPITicket的有效期(毫秒)，2小时
	 */
	public static long ExpireTime = 2 * 60 * 60 * 1000 - 30*1000;//过期前30s去取，免得AccessToken是无效值

	public static String SessionKey = "sessionKey";

	public static String OPENID = "openid";
}
