package com.cnfantasia.server.api.property.constant;

public final class PropertyConstant {

	/** 数据来源 */
	public static final class DataFromType {
		/** 解放区 */
		public static final String Jfq = "0";
		/** 第三方，如极致 */
		public static final String Third = "1";
	}

	/** 支付状态 */
	public static final class PayStatus {
		/** "1":"支付完成" */
		public static final Integer Pay_Success = 1;
		/** "2":"未支付" */
		public static final Integer Not_Pay = 2;
		/** "3":"支付确认中" */
		public static final Integer Pay_Confirmed = 3;
	}

	/** 账单详情类型 */
	public static final class BillDetailType {
		/** 物业缴费 */
		public static final String Property = "1";
		/** 停车缴费 */
		public static final String Car = "2";
	}

	/**
	 * 缴费方式=={"1":"用户在线支付","2":"物业公司手工标记","3":"物业代扣卡划扣","4":"物业宝抵扣"}
	 */
	public static final class BillPayMethod {
		/** 用户在线支付 */
		public static final String Zai_Xian = "1";
		/** 物业公司手工标记 */
		public static final String Shou_Gong = "2";
		/** 物业代扣卡划扣 */
		public static final String Hua_Kou = "3";
		/** 物业宝抵扣 */
		public static final String Di_Kou = "4";

		/** 银行卡托收 */
		public static final String Bank = "5";
	}

	/** 周期物业费类型 */
	public static final class PropertyPeriodType {
		/** 固定周期 */
		public static final int Gu_Ding = 1;
		/** 选择周期 */
		public static final int Xuan_Ze = 2;
	}

	/** 周期物业费类型 */
	public static final class BillType {
		/** 物业费 */
		public static final String PROPERTY_FEE = "1";
		/** 非物业费 */
		public static final String NOT_PROPERTY_FEE = "2";
		/** 月卡车账单 */
		public static final String CAR = "3";
		/** 物业费充值 */
		public static final String PROPERTY_RECHARGE = "4";
	}
}
