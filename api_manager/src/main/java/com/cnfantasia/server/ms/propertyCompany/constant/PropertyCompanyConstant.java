/**   
* Filename:    LoginConstant.java   
* @version:    1.0  
* Create at:   2014年8月8日 上午7:14:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.propertyCompany.constant;


/**
 * 邀请奖励记录常量
 */
public class PropertyCompanyConstant {
	/**
	 * 是否高级合作=={"0":"未合作","1":"已合作","2":"申请中"}
	 * */
	public static class HC_Status {
		/** 未合作 */
		public static final int No = 0;
		/** 已合作 */
		public static final int Yes = 1;
		/** 申请中-待审核 */
		public static final int Apply = 2;
		/** 审核失败 */
		public static final int Fail = 3;
	}

	/**
	 * 单据审核状态=={"0":"未审核","1":"已审核","2":"审核失败"}
	 */
	public static class Audit_Status {
		/** 未审核 */
		public static final Integer Init = 0;
		/** 已审核 */
		public static final Integer Pass = 1;
		/** 审核失败 */
		public static final Integer Fail = 2;
	}
}
