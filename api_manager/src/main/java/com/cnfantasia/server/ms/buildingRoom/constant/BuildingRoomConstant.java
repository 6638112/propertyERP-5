package com.cnfantasia.server.ms.buildingRoom.constant;


/**
 * 楼栋房号常量
 */
public class BuildingRoomConstant {
	// 数据来源类型=={"1":"运维平台录入","2":"手工搜集","11":"百度地图API"}
	public static class Source_Type {
		/** 运维平台录入 */
		public static final int ST1 = 1;
		/** 手工搜集 */
		public static final int ST2 = 2;
		/** 百度地图API */
		public static final int ST3 = 3;
	}
	
	//审核状态=={"0":"待审核","1":"审核通过","2":"审核不通过","9":"无需审核"}
	public static class Audit_Type {
		/** 待审核 */
		public static final int AT1 = 0;
		/** 审核通过 */
		public static final int AT2 = 1;
		/** 审核不通过 */
		public static final int AT3 = 2;
		/** 无需审核 */
		public static final int AT9 = 9;
	}
}
