/**   
* Filename:    WeiXinPayConstantUtil.java   
* @version:    1.0  
* Create at:   2014年12月16日 上午6:31:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.constant;

/**
 * Filename:    WeiXinPayConstantUtil.java
 * @version:    1.0.0
 * Create at:   2014年12月16日 上午6:31:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月16日       shiyl             1.0             1.0 Version
 */
public class WeiXinPayConstantUtil {
	/**
	 * 微信支付目标账户
	 */
	public static enum WeiXinPay_GoalAccType{
		/**彩之云*/
		CaiZhiYun
		/**邻里乐*/
		, LinLiLe_i
		/** 文旅 */
		,WenLv_i
		/**解放区 */
		,JieFangQu
		/**解放区轻应用 */
		,JieFangQu_LightApp
	}
	public static class CaiZhiYun{
		// 初始化
		public static String APP_ID = "wxef9afd077ebdbdca";// 微信开发平台应用id
		public static String APP_SECRET = "b3a84081381a6f3c8ca91df28c6081c7";// 应用对应的凭证
		// 应用对应的密钥
		public static String APP_KEY = "FWYuA61dSqgxi4jZ5myUu1waw2AN9B1GLbrvShSwU2JouTJQfA6ImVKn8K2ek4pUK2G1fSEzoHdoMcIq5FoSr72RcOlMyOj1i3gPyALF5dZYBFeKUpLVQU4gKMuwuksN";
		public static String PARTNER = "1219483001";// 财付通商户号
		public static String PARTNER_KEY = "95dc7a47b2173d457155502ace256df2";// 商户号对应的密钥
	}
	
	public static class LinLiLe_i{
		// 初始化
		public static String APP_ID = "wxf85b8444f7a2dc5f";// 微信开发平台应用id
		public static String APP_SECRET = "a06087fdbd7cde94a6879dafb36672a1";// 应用对应的凭证
		// 应用对应的密钥
		public static String APP_KEY = "zM3p9eDrm4UKLKU81z0iKEwSapklHe3M825jpJpl1LL9UMlOjTnrsNJIZSY052DK61tGLOhDnHRNJgcTmsYnRVAOoPRJHsndCkPJBlufRtVlA9npu2Kq1BvoyDZzPdfz";
		public static String PARTNER = "1225481801";// 财付通商户号
		public static String PARTNER_KEY = "c6d4c8396d319fc21d96be0a5e0ddbfe";// 商户号对应的密钥
	}
	
	public static class WenLv_i {
		// 初始化
		public static String APP_ID = "wx681fb1b637c7c74f";// 微信开发平台应用id
		public static String APP_SECRET = "65e7369cba6306abc6d6b50d08aa90f2";// 应用对应的凭证
		// 应用对应的密钥
		public static String APP_KEY = "";
		public static String PARTNER = "1226657802";// 财付通商户号
		public static String PARTNER_KEY = "5myUu1waw2AN9B1GLbrvShSwU2JouTJQ";// 商户号对应的密钥
	}
	
	public static class JieFangQu{
		// 初始化
		public static String APP_ID = "wx2207655615a998fe";// 微信开发平台应用id
		public static String APP_SECRET = "875d61805cf90991d0623fef67cb7586";// 应用对应的凭证
		// 应用对应的密钥
		public static String APP_KEY = "pyzqY1V5ZU0a1dzhvlZ926rtBRNbIj5n0kHBD4Vt8KOJ4SxQc5YHtWPBW8Pt10B82WfeL9Rsn3FmqS4FtSfoD1zhnk2SldAnt4zoUedrRqMogKDY4cZajZn8LYG2LRWA";
		public static String PARTNER = "1238366201";// 财付通商户号
		public static String PARTNER_KEY = "99f3584071471b7d8472e169da426bcf";// 商户号对应的密钥
	}
	
	public static class JieFangQu_LightApp{
		// 初始化
		public static String APP_ID = "wx766bad973e68e42e";// 微信开发平台应用id
		public static String APP_SECRET = "3381d213f6fbf25fe067d686a82b2bed";// 应用对应的凭证
		// 应用对应的密钥
		public static String APP_KEY = "";
		public static String PARTNER = "1238656902";// 财付通商户号
		public static String PARTNER_KEY = "WXzf201505081758Wxzf201505081758";// 商户号对应的密钥
	}
	
	
	//初始化
	public static String getAPP_ID(WeiXinPay_GoalAccType goalAccType) {//微信开发平台应用id
		switch (goalAccType) {
		case CaiZhiYun:
			return WeiXinPayConstantUtil.CaiZhiYun.APP_ID;
		case LinLiLe_i:
			return WeiXinPayConstantUtil.LinLiLe_i.APP_ID;
		case WenLv_i:
			return WeiXinPayConstantUtil.WenLv_i.APP_ID;
		case JieFangQu:
			return WeiXinPayConstantUtil.JieFangQu.APP_ID;
		case JieFangQu_LightApp:
			return WeiXinPayConstantUtil.JieFangQu_LightApp.APP_ID;
		}
		return null;
	}

	public static String getAPP_SECRET(WeiXinPay_GoalAccType goalAccType) {//应用对应的凭证
		switch (goalAccType) {
		case CaiZhiYun:
			return WeiXinPayConstantUtil.CaiZhiYun.APP_SECRET;
		case LinLiLe_i:
			return WeiXinPayConstantUtil.LinLiLe_i.APP_SECRET;
		case WenLv_i:
			return WeiXinPayConstantUtil.WenLv_i.APP_SECRET;
		case JieFangQu:
			return WeiXinPayConstantUtil.JieFangQu.APP_SECRET;
		case JieFangQu_LightApp:
			return WeiXinPayConstantUtil.JieFangQu_LightApp.APP_SECRET;
		}
		return null;
	}

	public static String getAPP_KEY(WeiXinPay_GoalAccType goalAccType) {//应用对应的密钥
		switch (goalAccType) {
		case CaiZhiYun:
			return WeiXinPayConstantUtil.CaiZhiYun.APP_KEY;
		case LinLiLe_i:
			return WeiXinPayConstantUtil.LinLiLe_i.APP_KEY;
		case WenLv_i:
			return WeiXinPayConstantUtil.WenLv_i.APP_KEY;
		case JieFangQu:
			return WeiXinPayConstantUtil.JieFangQu.APP_KEY;
		case JieFangQu_LightApp:
			return WeiXinPayConstantUtil.JieFangQu_LightApp.APP_KEY;
		}
		return null;
	}

	public static String getPARTNER(WeiXinPay_GoalAccType goalAccType) {//财付通商户号
		switch (goalAccType) {
		case CaiZhiYun:
			return WeiXinPayConstantUtil.CaiZhiYun.PARTNER;
		case LinLiLe_i:
			return WeiXinPayConstantUtil.LinLiLe_i.PARTNER;
		case WenLv_i:
			return WeiXinPayConstantUtil.WenLv_i.PARTNER;
		case JieFangQu:
			return WeiXinPayConstantUtil.JieFangQu.PARTNER;
		case JieFangQu_LightApp:
			return WeiXinPayConstantUtil.JieFangQu_LightApp.PARTNER;
		}
		return null;
	}

	public static String getPARTNER_KEY(WeiXinPay_GoalAccType goalAccType) {//商户号对应的密钥
		switch (goalAccType) {
		case CaiZhiYun:
			return WeiXinPayConstantUtil.CaiZhiYun.PARTNER_KEY;
		case LinLiLe_i:
			return WeiXinPayConstantUtil.LinLiLe_i.PARTNER_KEY;
		case WenLv_i:
			return WeiXinPayConstantUtil.WenLv_i.PARTNER_KEY;
		case JieFangQu:
			return WeiXinPayConstantUtil.JieFangQu.PARTNER_KEY;
		case JieFangQu_LightApp:
			return WeiXinPayConstantUtil.JieFangQu_LightApp.PARTNER_KEY;
		}
		return null;
	}
	
}
