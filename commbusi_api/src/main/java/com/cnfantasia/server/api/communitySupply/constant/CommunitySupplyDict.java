/**   
* Filename:    CommunitySupplyDict.java   
* @version:    1.0  
* Create at:   2014年8月26日 上午3:45:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.constant;

/**
 * Filename:    CommunitySupplyDict.java
 * @version:    1.0.0
 * Create at:   2014年8月26日 上午3:45:28
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月26日       shiyl             1.0             1.0 Version
 */
public class CommunitySupplyDict {
	/**
	 * 社区商户类别的主次级别
	 */
	public static class CommunitySupply_Type_Importance_Level{
		/**"1":"一级类别"*/
		public static final Integer level01 = 1;
		/**"2":"二级类别"*/
		public static final Integer level02 = 2;
	}
	
	/**
	 * 是否已收藏 0未收藏，1已收藏
	 */
	public static class CommunitySupply_IsCollect {
		/** 未收藏 */
		public static final Integer NO = 0;
		/** 已收藏 */
		public static final Integer YES = 1;
	}
	
	/**
	 * 联系方式类型=={"1":"电话","2":"邮箱"}
	 */
	public static class CommunitySupplyContect_Type {
		/** "1":"电话" */
		public static final Integer Phone = 1;
		/** "2":"邮箱"*/
		public static final Integer Mail = 2;
	}
	
	/**
	 * 认领状态=={"1":"待审批","1":"审核通过","2":"审核不通过"}
	 */
	public static class CommunitySupply_Belong {
		/** 待审批 */
		public static final Integer UNDO = 1;
		/** 审核通过 */
		public static final Integer SUCCESS = 2;
		/** 审核不通过 */
		public static final Integer FAILED = 3;
	}
	
}
