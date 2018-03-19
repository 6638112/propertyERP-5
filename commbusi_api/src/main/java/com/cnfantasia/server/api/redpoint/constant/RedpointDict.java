/**   
* Filename:    RedpointDict.java   
* @version:    1.0  
* Create at:   2015年3月26日 上午7:42:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.constant;

/**
 * Filename:    RedpointDict.java
 * @version:    1.0.0
 * Create at:   2015年3月26日 上午7:42:59
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月26日       shiyl             1.0             1.0 Version
 */
public class RedpointDict {
	
	/**
	 * 红点 点击状态=={"1":"已点","2":"未点"}
	 */
	public static class RedpointContent_clickStatus{
		public static final Integer NOT_CLICK = 2;
		public static final Integer HAVE_CLICK = 1;
	}
	
	/**
	 * 来源类型=={"1":"家里留言","2":"系统消息"}
	 */
	public static class Redpoint_Content_SourceType{
		/**"1":"家里留言"*/
		public static final Integer FamilyMessage = 1;
		/**"2":"系统消息"*/
		public static final Integer SystemMessage = 2;
		/**"3":"家庭菜谱收藏"*/
		public static final Integer FamilyCookBook_Collect = 3;
		
		/**"4":"上门维修"*/
		public static final Integer Dredage = 4;
		
		/**"5":"账单"*/
		public static final Integer PayBill = 5;

		/** 消费券*/
		public static final Integer Coupon = 6;
	}
	
}
