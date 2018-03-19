package com.cnfantasia.server.ms.groupBuilding.constant;

import java.math.BigInteger;


/**
 * 小区管理常量
 */
public class GroupBuildingConstant {
	// 合作类型=={1:基本合作,2:高级合作,3:全面合作}
	public static class Cooperation_Type {
		/** 基本合作 */
		public static final int BASE = 1;
		/** 高级合作 */
		public static final int SUPPER = 2;
		/** 全面合作 */
		public static final int ALL = 3;
	}
	//审核状态=={"0":"待审核","1":"审核通过","2":"审核不通过","9":"无需审核"}
	public static class Check_Status {
		/** 待审核 */
		public static final int CS0 = 0;
		/** 审核通过 */
		public static final int CS1 = 1;
		/** 审核不通过 */
		public static final int CS2 = 2;
		/** 无需审核 */
		public static final int CS9 = 9;
	}
	
	/**物业公司的id*/
	public static final BigInteger PC_ID = BigInteger.valueOf(-1L);
}
