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
	 * 缴费方式
	 * @author wenfq
	 *
	 */
	public static class PaymentWay {
		/**
		 * 在线支付
		 */
		public static final int Pay_Online = 1;
		/**
		 * 手工标记
		 */
		public static final int Marked_Manual = 2;
		
		/**
		 * 物业代扣卡划扣
		 */
		public static final int Property_Card_Deduction  = 3;
		
		/**
		 * 物业宝抵扣
		 */
		public static final int FINANCE_DEDUCTION  = 4;
		
		/**
		 * 银行托收
		 */
		public static final int BANK_COLLECTION  = 5;
	}

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

	public static class DataFromType {
		public static final int fromSystem = 0;
		public static final int fromSoftware = 1;
	}
//	/**
//	 * 物业账单提前月份数=={"0":"本月","1":"当月录下个月账单"}
//	 */
//	public static class GroupBuilding_PropertyMonthChange{
//		/**"1":"当月录下个月账单"*/
//		public static final Integer NextMonth = 1;
//	}
}
