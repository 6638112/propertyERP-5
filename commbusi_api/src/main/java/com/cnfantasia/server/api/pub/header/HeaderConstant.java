/**   
* Filename:    HeaderConstant.java   
* @version:    1.0  
* Create at:   2014年8月8日 上午7:18:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.header;

/**
 * Filename:    HeaderConstant.java
 * @version:    1.0.0
 * Create at:   2014年8月8日 上午7:18:00
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月8日       shiyl             1.0             1.0 Version
 */
public class HeaderConstant {
	/**
	 * 请求头包含的参数信息
	 */
	public class Header_Param {
		// public static final String HEADER_PARAM_NAME_CHANNEL = "channelId";
		public static final String HEADER_PARAM_NAME_SUB_CHANNEL = "subChannelId";
		public static final String HEADER_PARAM_NAME_DEVICE_ID = "deviceId";
		public static final String HEADER_PARAM_NAME_SESSIONO_KEY = "sessionKey";
		public static final String HEADER_PARAM_NAME_VERSION = "version";
		public static final String HEADER_PARAM_NAME_GBID = "gbId";
		public static final String HEADER_PARAM_NAME_SIGN_AND_FOUND = "signAndFound";
	}
	
	public static class SubChannelId{
		public static final Long Android_Phone = Long.valueOf(1); //Android手机
		public static final Long ISO_Phone = Long.valueOf(2); //ISO手机
		public static final Long PC_Browser = Long.valueOf(3); //PC浏览器
		public static final Long Test_account = Long.valueOf(4); //测试账号
		public static final Long Wl_Light_App = Long.valueOf("5");//文旅微信轻应用
		public static final Long Client_Inner_Web = Long.valueOf("6");//APP内置Web控件
		public static final Long Jfq_Light_App = Long.valueOf("7");//解放区微信轻应用
		
		public static final Long EBUY_MERCHANT_APP = Long.valueOf("20");//电商商户应用

		public static final Long Jfq_Master_App = Long.valueOf("9");//解放区-师傅端(安桌或ios未知)
		
		public static final Long Jfq_Master_App_Android = Long.valueOf("10");//解放区-师傅端Android手机
		public static final Long Jfq_Master_App_ISO = Long.valueOf("11");//解放区-师傅端ISO手机
	}
	
}
