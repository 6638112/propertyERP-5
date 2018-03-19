package com.cnfantasia.server.api.common.constant;

public class Lock {
	
	public static final String EBUY_ORDER_FILE_TICKET = "t_ebuy_order_film_ticket";
	
	public static final String EBUY_ORDER_FLOW_RECHARGE = "t_ebuy_order_flow_recharge";
	
	public static final String PRIZE_RECORD_TMP_DATA = "t_prize_record_tmp_data";
	
	//生成物业宝抵扣账单时，避免多线程等问题导致错误数据
	public static final String FINANCE_DEDUCTION = "finance_decuction";
	
	//楼下店收益明细Job生成数据时，避免多线程或多结点导致的并发问题
	public static final String EBUY_SYN_TASK_SUPPLY_EXEC_TASK = "EbuySynTaskSupply-execTask";
	
	public static final String DREDGE_SYN_TASK_EXEC_TASK = "DredgeBillSynTask-execTask";
	
	public static final String FINANCE_SYN_TASK_EXEC_TASK = "FinanceSynTask-execTask";
	
	public static final String CAR_SYN_TASK_EXEC_TASK = "CarSynTask-execTask";
	
	public static final String FINACE_DEDU_SYN_TASK_EXEC_TASK = "FinanceDeduSynTask-execTask";
	
	public static final String CAR_DEDU_SYN_TASK_EXEC_TASK = "CarDeduSynTask-execTask";
	
	
//	public static final String RevenueSynTask = "RevenueSynTask";
	public static final String RevenueAutoApplyCompanyTask = "RevenueAutoApplyCompanyTask";

	public static final String PropertyOtherFeeSynTask = "PropertyOtherFeeSynTask";
	public static final String PropertyRealPayAmoutSynTask = "PropertyRealPayAmoutSynTask";
	public static final String PropertySubsidyAmoutSynTask = "PropertySubsidyAmoutSynTask";
	public static final String RoomValidateSynTaskAgent = "RoomValidateSynTaskAgent";
	public static final String RoomValidateSynTaskCompany = "RoomValidateSynTaskCompany";

	public static final String DredgeBillSynTask = "DredgeBillSynTask";
	
	public static final String CAR_BILLPUSH = "Car-billPush";
	
	public static final String Eguorder_billPush = "Eguorder-billPush";
	
	public static final class CashLockTable{
		/** 物业费 */
		public static final String PAY_BILL_WY = "pay_bill_wy";
		/** 其他代收费用 */
		public static final String PAY_BILL_OTHER = "pay_bill_other";
		/** 停车宝 */
		public static final String PARKING_B = "parking_b";
		/** 物业宝 */
		public static final String WYB = "wy_B";
		/** 上门维修 */
		public static final String DREDGE_BILL_REPAIR = "dredge_bill_repair";
		/** 楼下店 */
		public static final String EBUY_ORDER_DOWN = "ebuy_order_down";
		/** 自营超市 */
		public static final String EBUY_ORDER_SELF_RUN = "ebuy_order_self_run";
		/** 停车费 */
		public static final String CAR_PAY_LOG = "car_pay_log";
	}
}
