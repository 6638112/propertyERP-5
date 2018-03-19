package com.cnfantasia.server.api.bankCollection.constant;

/**
 * 回盘常量
 * 
 * @author liyulin
 * @version 1.0 2017年4月13日 下午3:21:13
 */
public final class BankCollectionConstants {

	/**托收范围={1:按小区; 2按业主}*/
	public static final class CollectionRange{
		/**按小区*/
		public static final Integer By_GB = 1;
		/**按业主*/
		public static final Integer By_PP = 2;
	}

	/**账单支付状态*/
	public static final class PayBillIsPay {
		/** 已缴 */
		public static final int PAID_IN = 1;
		/** 未缴 */
		public static final int UNPAID  = 2;
	}

	/**银行回盘状态(解放区oos平台定义)
	 * 1成功; 2未找到账单;3余额不足;4金额不匹配;5重复回盘
	 * */
	public static final class BankHuiPanStatus {
		public static final int SUCCESSED = 1;
		public static final int NOT_FIND_PAYBILL = 2;
		public static final int NOT_SUFFICIENT_FUNDS = 3;
		public static final int AMOUNT_MISMATCHING = 4;
		public static final int REPEAT = 5;
	}

	/**
	 * 银行回盘状态（银行自定义状态）
	 */
	public static final class  BankHuiPanFileStatus {
		public static final String Y = "处理成功";
		public static final String E = "可用余额不足";
		public static final String O = "帐户状态不正常";
	}
	public static String bankHuiPanFileStatus(String s) {
		if(s.equals("Y")) {
			return BankHuiPanFileStatus.Y;
		}
		if(s.equals("E")) {
			return BankHuiPanFileStatus.E;
		}
		if(s.equals("O")) {
			return BankHuiPanFileStatus.O;
		}
		if(s.equals("R")) {
			return "重复回盘";
		}
		return "获取结果失败";
	}

	/**
	 * 缴费方式=={"1":"用户在线支付","2":"物业公司手工标记","3":"物业代扣卡划扣","4":"物业宝抵扣","5":"银行托收"}
	 */
	public static final class BillPayMethod{
		/**用户在线支付*/
		public static final int Zai_Xian = 1;
		/**物业公司手工标记*/
		public static final int Shou_Gong = 2;
		/**物业代扣卡划扣*/
		public static final int Hua_Kou = 3;
		/**物业宝抵扣*/
		public static final int Di_Kou = 4;
		/**银行托收*/
		public static final int BANK_COLLECTION = 5;
	}

}
