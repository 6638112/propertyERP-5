package com.cnfantasia.server.api.access.constant;

/**
 * 小蜜蜂车禁常量
 * 
 * @author liyulin
 * @version 1.0 2017年6月28日 下午4:18:50
 */
public final class XMFCarDict {
	
	/**每次查询的最大数据条数*/
	public static final int MAX_PAGE_SIZE = 50;

	/**
	 * <p>
	 * 操作执行结果状态
	 * </p>
	 * 0-异常<br>
	 * 1-成功<br>
	 * 2-失败
	 */
	public static final class State {
		/** 0-异常 */
		public static final String EXCEPTION = "0";
		/** 1-成功 */
		public static final String SUCCESS = "1";
		/** 2-失败 */
		public static final String FAIL = "2";
	}

	/**
	 * 蜜蜂车禁方法
	 */
	public static final class Method {
		/** 获取车辆的停车信息 */
		public static final String GET_ORDER = "getOrder";
		/** 第三方临停车缴费成功后，将车辆的缴费信息通知蜜蜂停车 */
		public static final String PAY_ORDER = "payOrder";
		/** 获取月卡信息 */
		public static final String GET_CARDLIST_BY_CARNUM = "getCardListByCarnumber";
		/** 获取月卡类型列表 */
		public static final String GET_CARDTYPE_LIST = "getCardTypeList";
		/** 第三方月卡缴费成功后，将车辆的缴费信息通知蜜蜂停车 */
		public static final String ADD_CARD_ORDER_LIST = "addCardOrderList";
		/** 获取月卡的续费信息列表 */
		public static final String GET_CARD_ORDER_LIST = "getCardOrderList";
		/** 获取车场列表 */
		public static final String GET_PLOLIST = "getPloList";
		/** 获取停车记录 */
		public static final String GET_ORDER_LIST = "getOrderList";
	}
	
	/**
	 * <p>是否有停车记录</p>
	 * 1-有<br>
	 * 0-没有
	 */
	public static final class IsParking{
		/**0-没有*/
		public static final String NO = "0";
		/**1-有*/
		public static final String YES = "1";
	}
	
	/**
	 * <p>车辆类型</p>
     * 1-临停车<br>
     * 2-月卡
	 */
	public static final class CarType{
		/**1-临停车*/
		public static final int TMP_CAR = 1;
		/**2-月卡*/
		public static final int MONTH_CAR = 2;
	}
	
	/**
	 * <p>付费方式</p>
	 *  1-现金<br>
	 *  2-刷卡<br>
	 *  3-转账
	 */
	public static final class PayMode{
		/**1-现金*/
		public static final String XIAN_JIN = "1";
		/**2-刷卡*/
		public static final String SHUA_KA = "2";
		/**3-转账*/
		public static final String ZHUAN_ZHANG = "3";
	}
	
	/**
	 * <p>续费类型</p>
	 * 1-充值续费<br>
	 * 2-封存延期
	 */
	public static final class RechargeType{
		/**1-充值续费*/
		public static final String XU_FEI = "1";
		/**2-封存延期*/
		public static final String YAN_QI = "2";
	}
	
	/**
	 * <p>task同步间隔时间</p>
	 */
	public static final class SynTaskIntervalTime{
		/**同步缴费记录（30分钟）*/
		public static final long PAY_LOG = (30+1)*60*1000;
		/**同步出入场记录（10分钟）*/
		public static final long PARKING_RECORD = (10+1)*60*1000;
	}
}
