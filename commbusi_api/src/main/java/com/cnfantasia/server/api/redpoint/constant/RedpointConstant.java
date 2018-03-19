/**   
* Filename:    RedpointConstant.java   
* @version:    1.0  
* Create at:   2015年3月27日 上午9:44:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.constant;

/**
 * Filename:    RedpointConstant.java
 * @version:    1.0.0
 * Create at:   2015年3月27日 上午9:44:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月27日       shiyl             1.0             1.0 Version
 */
public class RedpointConstant {
	
	public class RedpointContent_ModelCode{
		/**家庭留言*/
		public static final String FML_MSG = "FML_MSG";
		/**家庭菜单*/
		public static final String FML_COOKBOOK = "FML_COOKBOOK";
		/**家庭购物*/
		public static final String FML_SHOPPING = "FML_SHOPPING";
		/**系统公告(有未读物业公告)*/
		public static final String SYS_NOTICE = "SYS_NOTICE";
		/**额外惊喜*/
		public static final String PRIZE_GIFT = "PRIZE_GIFT";
		
		/**师傅审核通过*/
		public static final String MASTER_AUDITTED = "MASTER_AUDITTED";
		/**维修订单被师傅接单*/
		public static final String DREDGEBILL_CONFIRMED = "DREDGEBILL_CONFIRMED";
		/**维修订单被师傅设置金额*/
		public static final String DREDGEBILL_SETPAYAMOUNT = "DREDGEBILL_SETPAYAMOUNT";
		/**维修订单用户已支付*/
		public static final String DREDGEBILL_HASPAYED = "DREDGEBILL_HASPAYED";
		
		/**有未缴物业账单*/
		public static final String IS_HAS_PROPERTY_BILL = "IS_HAS_PROPERTY_BILL";

		/** 物业订单被物业关闭 */
		public static final String PROPERTYREPAIRBILL_HASCLOSEED = "PROPERTYREPAIRBILL_HASCLOSEED";
		
		/** 维修单已完成: 用户支付成功，师傅关闭物业订单，用户取消 */
		public static final String DREDGEBILL_HASFINISHED = "DREDGEBILL_HASFINISHED";

		/** 有新消费券*/
		public static final String USER_HAS_NEW_COUPON = "USER_HAS_NEW_COUPON";

		/** 商户端提款申请状态有变化*/
		public static final String EBUY_MERCHANT_SETTLE_STAUTS_CHANGE = "EBUY_MERCHANT_SETTLE_STAUTS_CHANGE";
	}
	
}
