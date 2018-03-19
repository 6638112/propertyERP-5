package com.cnfantasia.server.ms.communitySupply.constant;

public class CommunitySupplyConstant {
	
	// 审核状态=={"1":"待审批","2":"审核通过","3":"审核不通过"}
	public static class PropertySuggestCommsupply_Audit_Status {
		/** 待审核 */
		public static final int WAIT_AUDIT = 1;
		/** 审核通过 */
		public static final int AUDIT_PASS = 2;
		/**
		 * 审核不通过
		 */
		public static final int AUDIT_NOT_PASS = 3;
	}

	// 审核状态=={"1":"待审批","2":"审核通过","3":"审核不通过"}
	public static class CommunitySupplyTmp_Audit_Status {
		/** 待审核 */
		public static final int WAIT_AUDIT = 1;
		/** 审核通过 */
		public static final int AUDIT_PASS = 2;
		/**
		 * 审核不通过
		 */
		public static final int AUDIT_NOT_PASS = 3;
	}
	
	// 审核状态=={"1":"待审批","2":"审核通过","3":"审核不通过"}
	public static class CommunitySupplyBelong_Audit_Status {
		/** 待审核 */
		public static final int WAIT_AUDIT = 1;
		/** 审核通过 */
		public static final int AUDIT_PASS = 2;
		/**
		 * 审核不通过
		 */
		public static final int AUDIT_NOT_PASS = 3;
	}

	// 审核类型=={"0":"新增审核","1":"编辑审核"}
	public static class CommunitySupplyTmp_Audit_Type {
		/** 新增审核 */
		public static final int NEW_AUDIT = 0;
		/** 编辑审核 */
		public static final int EDIT_AUDIT = 1;
	}

//	/**
//	 * 新增审核通过短信内容（首次）
//	 */
//	public static final String AddNew_AuditPassSMSInfo_First = "解放区已为您分配维护店铺信息账号，账号和初始密码：{0}，登录地址：http://oos.jiefangqu.com【解放区】";
//
//	/**
//	 * 新增审核通过短信内容（非首次）
//	 */
//	public static final String AddNew_AuditPassSMSInfo_NotFirst = "您新增的店铺已通过解放区审核，请用账号：{0}登录http://oos.jiefangqu.com维护您的店铺信息【解放区】";
//
//	/**
//	 * 新增审核不通过短信内容
//	 */
//	public static final String AddNew_AuditNotPassSMSInfo = "您新增的店铺资料未通过审核，原因：{0}，请重新录入，感谢您对解放区的支持【解放区】";
//
//	/**
//	 * 认领审核通过短信内容（首次）
//	 */
//	public static final String Belong_AuditPassSMSInfo_First = "解放区已为您分配维护店铺信息账号，账号和初始密码：{0}，登录地址：http://oos.jiefangqu.com【解放区】";
//
//	/**
//	 * 认领审核通过短信内容（非首次）
//	 */
//	public static final String Belong_AuditPassSMSInfo_NotFirst = "您认领的店铺已通过解放区审核，请用账号：{0}登录 http://oos.jiefangqu.com 维护您的店铺信息【解放区】";
//
//	/**
//	 * 认领审核不通过短信内容
//	 */
//	public static final String Belong_AuditNotPassSMSInfo = "您认领的店铺资料未通过审核，原因：{0}，请重新录入，感谢您对解放区的支持【解放区】";
	
	//数据来源类型=={"1":"运维平台录入","2":"手工搜集","11":"百度地图API","12":"深圳国土局一手房","13":"深圳国土局二手房","21":"美丽加"}
	public static class CommunitySupply_source_Type {
		/** 运维平台录入 */
		public static final int ST_JFQ = 1;
		/** 手工搜集(商家APP新增) */
		public static final int ST_APP = 2;
		/** 百度地图API */
		public static final int ST_Baidu_API = 11;
		/** 深圳国土局一手房 */
		public static final int ST_SZ_LB1 = 12;
		/** 深圳国土局二手房 */
		public static final int ST_SZ_LB2 = 13;
		/** 美丽加 */
		public static final int ST_MLJ = 21;
	}
}
