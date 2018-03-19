package com.cnfantasia.server.ms.payBill.constant;

public class PayBillDict {
	/**
	 * 是否已缴费：未缴
	 */
	public static final int isPayed_no = 2;

	/**
	 * 是否已缴费：已缴
	 */
	public static final int isPayed_yes = 1;

	/**
	 * 费用详情明细
	 * 
	 * @author wenfq
	 * 
	 */
	public static class PropertyFeeDetailDict {
		/**
		 * 物业费
		 */
		public static final int FeeType_Property = 1;

		/**
		 * 主体费
		 */
		public static final int FeeType_Main = 2;
		/**
		 * 垃圾处理费
		 */
		public static final int FeeType_Garbage = 3;
		/**
		 * 水费
		 */
		public static final int FeeType_Water = 4;
		/**
		 * 污水处理费
		 */
		public static final int FeeType_Sewage = 5;
		/**
		 * 其它费用
		 */
		public static final int FeeType_Other = 9;
	}
}
