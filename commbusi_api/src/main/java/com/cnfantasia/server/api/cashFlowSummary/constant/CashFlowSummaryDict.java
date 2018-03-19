package com.cnfantasia.server.api.cashFlowSummary.constant;

import com.cnfantasia.server.api.common.constant.Lock;

/**
 * 现金流常量
 * 
 * @author liyulin
 * @version 1.0 2016年7月18日 上午10:52:43
 */
public final class CashFlowSummaryDict {
	/**
	 * while循环，每次分页查询的条数
	 */
	public final static int QUERY_LIMIT_COUNT = 100;

	/**
	 * 业务类型=={"1":"物业费", "2":"停车费", "3":"其他代收费用", "4":"楼下店","5":"自营超市",
	 * "6":"上门维修","7":"物业宝","8":"停车宝", "9":"月利宝","10":"日利宝","11":"爱定宝",
	 * "12":"养老理财"}'
	 * 
	 * @author liyulin
	 * @version 1.0 2016年7月18日 上午10:48:03
	 */
	public final static class BillType {
		/** 物业费 */
		public static final int WY_FEE = 1;
		/** 停车费 */
		public static final int PARKING_FEE = 2;
		/** 其他代收费用 */
		public static final int OTHER_FEE = 3;
		/** 楼下店 */
		public static final int DOWN_FEE = 4;
		/** 自营超市 */
		public static final int SELF_RUN_FEE = 5;
		/** 上门维修 */
		public static final int REPAIR_FEE = 6;
		/** 物业宝 */
		public static final int WYB_FEE = 7;
		/** 停车宝 */
		public static final int PARKINGB_FEE = 8;
	}
	
	/** 所有现金流类型：BILL_TYPES与LOCK_TABLES互相一一对应 */
	public final static int[] BILL_TYPES = {CashFlowSummaryDict.BillType.WY_FEE,
										    CashFlowSummaryDict.BillType.OTHER_FEE,
										    CashFlowSummaryDict.BillType.PARKINGB_FEE,
										    CashFlowSummaryDict.BillType.WYB_FEE,
										    CashFlowSummaryDict.BillType.REPAIR_FEE,
										    CashFlowSummaryDict.BillType.DOWN_FEE,
										    CashFlowSummaryDict.BillType.SELF_RUN_FEE,
										    CashFlowSummaryDict.BillType.PARKING_FEE};

	/** 锁定的表:BILL_TYPES与LOCK_TABLES互相一一对应 */
	public final static String[] LOCK_TABLES = {Lock.CashLockTable.PAY_BILL_WY, 
						   					    Lock.CashLockTable.PAY_BILL_OTHER, 
						   					    Lock.CashLockTable.PARKING_B, 
											    Lock.CashLockTable.WYB, 
											    Lock.CashLockTable.DREDGE_BILL_REPAIR, 
											    Lock.CashLockTable.EBUY_ORDER_DOWN, 
											    Lock.CashLockTable.EBUY_ORDER_SELF_RUN, 
											    Lock.CashLockTable.CAR_PAY_LOG};
}
