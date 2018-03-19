/**   
* Filename:    CommunitySupplyTmpDict.java   
* @version:    1.0  
* Create at:   2015年2月9日 上午10:21:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.constant;

/**
 * Filename:    CommunitySupplyTmpDict.java
 * @version:    1.0.0
 * Create at:   2015年2月9日 上午10:21:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月9日       shiyl             1.0             1.0 Version
 */
public class CommunitySupplyTmpDict {
	
	/**
	 * 审核类型=={"0":"新增审核","1":"编辑审核"}
	 */
	public static class CommunitySupply_AuditType {
		/** 新增审核*/
		public static final Integer ADD = 0;
		/** 编辑审核 */
		public static final Integer UPD = 1;
	}
	
	/**
	 * 审核状态=={"1":"待审批","2":"审核通过","3":"审核不通过"}
	 */
	public static class CommunitySupply_AuditStatus {
		/** 待审批*/
		public static final Integer TODO = 1;
		/** 审核通过 */
		public static final Integer PASS = 2;
		/** 审核不通过 */
		public static final Integer REJECT = 3;
	}
	
}
