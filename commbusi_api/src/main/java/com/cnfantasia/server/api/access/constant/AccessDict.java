package com.cnfantasia.server.api.access.constant;

/**
 * @author wangzhe
 * @date 2015年12月10日
 * @description
 */
public final class AccessDict {
	/** 默认的车牌前缀 */
	public static final String DEFAULT_CAR_NUM_PREFIX = "粤B";
	/** while循环休眠间隔时间（单位：毫秒） */
	public static final int WHILE_WAITING_TIME = 5;

	public static final class CachePrefix {
		public static final String CARNUM_KEY_PREFIX = "car_";
		public static final String TMP_CAR_PLOT_PREFIX = "plotId_";
	}

	/** 缓存有效期 */
	public static final class CacheExpire {
		/** redis保存有效期：120秒 */
		public static final int EXPIRE_120s = 2 * 60;
		/** redis保存有效期：600秒 */
		public static final int EXPIRE_600s = 10 * 60;
		/** redis保存有效期：1小时 */
		public static final int FINANCE_CAR_EXPIRE_3600s = 60 * 60;
	}

	/**
	 * <p>
	 * 万人差消息类型
	 * </p>
	 * 1：“查询费用”<br>
	 * 2：“发送缴费成功”<br>
	 * 3：“查询所有连接”<br>
	 * 4：“断开连接”<br>
	 * 5：“加入黑名单”<br>
	 * 6：“移除黑名单”
	 */
	public static final class MsgType {
		/*====================================万人插=====================================*/
		/** 查询费用 */
		public static final String W_QUERY_FEE = "W1";
		/** 发送缴费成功 */
		public static final String W_SEND_PAY_SUCCESS = "W2";
		/** 查询所有连接 */
		public static final String W_QUERY_ALL_LINKS = "W3";
		/** 断开连接 */
		public static final String W_BREAK_LINK = "W4";
		/** 加入黑名单 */
		public static final String W_ADD_BLACK_LIST = "W5";
		/** 移除黑名单 */
		public static final String W_REMOVE_BLACK_LIST = "W6";


		/*====================================华庭车禁websocket============================*/
		/** 根据小区id向特定的连接发送数据 */
		public static final String WS_SEND_WEBSOCKET_MSG = "WS1";
		/** 查询所有websocket连接 */
		public static final String WS_QUERY_ALL_LINKS = "WS2";
	}

	/* 解放区车辆类别（"0":"非月卡","1":"月卡"） */
	public static final class JFQCarType {
		/** 0-非月卡 */
		public static final Integer UN_MONTH_CAR = 0;
		/** 1-月卡 */
		public static final Integer MONTH_CAR = 1;
	}
	
	/**
	 * 锁的key
	 */
	public static final class LockKey {
		/** 临停车缴费推送 */
		public static final String TMP_CAR_PAY_NOTIFY = "tmp_car_pay_notify";
		/** 月卡缴费推送 */
		public static final String MONTH_CAR_PAY_NOTIFY = "month_car_pay_notify";
		/** 月卡信息同步 */
		public static final String SYNC_MONTH_CAR_INFO = "sync_month_car_info";
		/**月卡缴费记录同步 */
		public static final String SYNC_MONTH_CAR_CASH_PAYLOG = "sync_month_car_cash_paylog";
		/**停车记录同步*/
		public static final String SYNC_PARKING_RECORD = "sync_parking_record";
	}
	
	/**
	 * 锁的有效期
	 */
	public static final class LockExpire {
		/** 临停车缴费推送 锁有效期（秒） */
		public static final int TMP_CAR_PAY_NOTIFY = 60;
		/** 月卡缴费推送 锁有效期（秒） */
		public static final int MONTH_CAR_PAY_NOTIFY = 600;
		/** 月卡信息同步 锁有效期（秒） */
		public static final int SYNC_MONTH_CAR_INFO = 60*60*24;
		/**月卡缴费记录同步 锁有效期（秒）*/
		public static final int SYNC_MONTH_CAR_CASH_PAYLOG = 1800;
		/**停车记录同步 锁有效期（秒）*/
		public static final int SYNC_PARKING_RECORD = 600;
	}
	
	/** 第三方唯一编码 */
	public enum Code {
		yihaosheng, huapengfei, xiaomifeng, huaan, yidaotong;
	}
	
	/** 临停车缴费优惠开关 */
	public static final class TmpCarOpenStatus {
		/** 未开启 */
		public static final int CLOSE = 0;
		/** 平台优惠开启" */
		public static final int JFQ_OPEN = 1;
		/** 物业优惠开启 */
		public static final int WY_OPEN = 2;
	}

	public static final class PushState {
		/** 未开启 */
		public static final int FAIL = 0;
		/** 平台优惠开启" */
		public static final int SUCCESS = 1;
		/** 物业优惠开启 */
		public static final int WY_OPEN = 2;
	}

	public static final class DateFormat {
		public static final String yyyy_MM_dd = "yyyy-MM-dd";
		public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	}

}
