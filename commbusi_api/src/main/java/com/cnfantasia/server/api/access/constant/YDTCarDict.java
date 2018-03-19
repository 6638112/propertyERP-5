package com.cnfantasia.server.api.access.constant;

/**
 * 一道通车禁常量
 * 
 * @author liyulin
 * @version 1.0 2017年12月7日 上午11:19:04
 */
public final class YDTCarDict {
	/**
	 * <p>
	 * 操作执行结果状态
	 * </p>
	 * 0-成功<br>
	 */
	public static final class ResponseState {
		/** 0-成功 */
		public static final String SUCCESS = "0";
	}

	/**
	 * 临停车状态
	 */
	public static final class TempState {
		/** 场内欠费 */
		public static final String IN = "1";
		/** 当前可以离场 */
		public static final String CAN_LEAVE = "2";
		/** 不在场内 */
		public static final String OUT = "3";
	}

	/**
	 * 访问接口
	 */
	public static final class Method {
		/** 月卡缴费接口 */
		public static final String MONTHLY_CARD_PAYMENT = "monthlyCardPayment.htm";
		/** 获取车辆信息 */
		public static final String GET_CAR_INFO = "getCarInfo.htm";
		/** 临时卡信息查询 */
		public static final String GET_TEMPCARD_INFO = "gettempCardInfo.htm";
		/** 临时卡代缴 */
		public static final String TEMPCARD_PAYMENT = "tempCardPayment.htm";
		/** 获取进场数据 */
		public static final String GET_IN_INFO = "getInInfo.htm";
		/** 获取出场数据 */
		public static final String GET_OUT_INFO = "getOutInfo.htm";
		/** 获取月租车充值记录 */
		public static final String GET_MONTHCARD_RECORD = "getMonthCarRecord.htm";
	}
	
}
