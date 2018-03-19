package com.cnfantasia.server.api.access.constant;

/**
 * @ClassName: HACarDict
 * @Date: 2017-08-15 17:37
 * @Auther: yanghua
 * @Description:(华安车禁常量)
 */
public final class HACarDict {

	/**
	 * <p>
	 * 操作执行结果状态
	 * </p>
	 * -1-失败<br>
	 * 1-成功
	 */
	public static final class State {
		/** 请求失败 */
		public static final String FAIL = "0";
		/** 请求成功 */
		public static final String SUCCESS = "1";
	}

	/**
	 * <p>
	 * 月卡车可缴费状态
	 * </p>
	 * A：允许继续缴费<br>
	 * I：不允许继续缴费
	 */
	public static final class MonthCarPayStatus {
		/** 允许继续缴费 */
		public static final String YES = "A";
		/** 不允许继续缴费 */
		public static final String NO = "I";
	}

	/**
	 * 访问接口
	 */
	public static final class Method {
		/** 获取临时车当前费用 */
		public static final String GET_TEMP_CAR_FEE = "restMobileService/GetCarMoney";
		/** 通知岗亭车禁缴费成功 */
		public static final String NOTIFY_TEMP_CAR_FEE_PAY = "restMobileService/PayCarMoney";
		/** 获取月卡车信息 */
		public static final String GET_MONTH_CAR_FEE = "restMobileService/GetMonthlyCarRateInfo";
		/** 通知月卡车缴费成功 */
		public static final String NOTIFY_MONTH_CAR_FEE_PAY = "restMobileService/SetEndDate";
	}

	/**
	 * <p>
	 * 车辆类型
	 * </p>
	 * 1-临停车<br>
	 * 2-月卡
	 */
	public static final class CarType {
		/** 1-临停车 */
		public static final int TMP_CAR = 1;
		/** 2-月卡 */
		public static final int MONTH_CAR = 2;
	}

	/**
	 * 进出场类型
	 */
	public static final class EnterExitType {
		/** 入场 */
		public static final int IN = 3;
		/** 出场 */
		public static final int OUT = 4;
	}

	/**
	 * 车位授权类型定义
	 */
	public static final class ParkType {
		/** 普通临时卡 */
		public static final int PARK_CUSTTYPE_TEMP = 1;
		/** 年卡 */
		public static final int PARK_CUSTTYPE_YEAR = 2;
		/** 月卡 */
		public static final int PARK_CUSTTYPE_MONTH = 3;
		/** 日卡 */
		public static final int PARK_CUSTTYPE_DAY = 4;
		/** 私家车位卡 */
		public static final int PARK_CUSTTYPE_OWNER = 5;
		/** 免费卡 */
		public static final int PARK_CUSTTYPE_FREE = 6;
		/** 充值卡 */
		public static final int PARK_CUSTTYPE_PREPAY = 7;
		/** 错峰月卡 */
		public static final int PARK_CUSTTYPE_SEGMONTH = 8;
		/** 季卡 */
		public static final int PARK_CUSTTYPE_QUARTER = 9;
		/** 半年卡 */
		public static final int PARK_CUSTTYPE_HALFYEAR = 10;
		/** 预约临时卡 */
		public static final int PARK_CUSTTYPE_BOOKINGTEMP = 11;
		/** 出租车位卡 */
		public static final int PARK_CUSTTYPE_RENT = 12;
		/** 临租车位卡 */
		public static final int PARK_CUSTTYPE_RENTED = 13;
	}
}
