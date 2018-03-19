/**   
* Filename:    PinyipinDict.java   
* @version:    1.0  
* Create at:   2014年10月16日 上午3:52:12   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pinyipin.constant;

/**
 * Filename:    PinyipinDict.java
 * @version:    1.0.0
 * Create at:   2014年10月16日 上午3:52:12
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月16日       shiyl             1.0             1.0 Version
 */
public class PinyipinDict {
	
	/**
	 * 拼单状态 {"1":"预定中","2":"预定结束"}
	 */
	public static class PinyipinContent_Ext_Status{
		public static final Integer Start = 1;
		public static final Integer Finished = 2;
	}
	
	/**
	 * 发货清单状态{"0":"预定中","1":"生成清单成功","2":"已过时"}
	 */
	public static class PinyipinContent_DeliveryStatus{
		public static final Integer Start = 0;
		public static final Integer Success = 1;
		public static final Integer TimeOut = 2;
	}
	
	/**
	 * 领取状态=={"1":"未领取","2":"已领取"}
	 */
	public static class PinyipinReserve_ClaimStatus{
		public static final Integer UnClaim = 1;
		public static final Integer HasClaimed = 2;
	}
	
	/**
	 * 取消状态=={"1":"未取消","2":"已取消"}
	 */
	public static class PinyipinReserve_CancleStatus{
		public static final Integer NotCancel = 1;
		public static final Integer HasCancel = 2;
	}
	
	/**
	 * 拼单所属用户=={"1":"发起者","2":"参与者"},0都不是
	 */
	public static class PinyipinContent_UserBelong{
		public static final Integer None = 0;//0都不是
		public static final Integer Launcher = 1;
		public static final Integer Joiner = 2;
	}
	
	/**
	 * 拼单配送方式=={"1":"自提","2":"送货上门"}
	 */
	public static class PinyipinContent_SendType{
		public static final Integer SelfAddress = 1;
		public static final Integer HomeDelivery = 2;
	}
	
}
