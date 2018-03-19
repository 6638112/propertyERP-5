package com.cnfantasia.server.api.access.constant;

/**
 * 华庭车禁常量
 * 
 * @author liyulin
 * @version 1.0 2017年6月15日 下午7:29:58
 */
public final class HpfCarDict {
	/** while循环休眠间隔时间（单位：毫秒） */
	public static final int WHILE_WAITING_TIME = 5;
	
	/**
	 * 远程命令
	 */
	public static final class Command {
		/** 1-主动上传进出记录 */
		public static final String UP_IN_OUT = "inout";
		/** 2-主动上传收费记录 */
		//public static final String UP_CHARGE_LOG = "";
		/** 3-主动上传充值记录 */
		//public static final String UP_RECHARGE_LOG = "";
		/** 4-远程服务器发起临时车停车计费请求 */
		public static final String REMOTE_RESPONSE_TEMP_CAR_PAY = "upmoney";
		/** 5-远程服务器发起月租车充值计费请求 */
		public static final String REMOTE_RESPONSE_MONTH_CAR_PAY = "uprecharge";
		/** 6-远程服务器发起获取车辆信息请求 */
		public static final String REMOTE_REQUEST_CAR_INFO = "upinfo";
		/** 7-远程服务器发回临时车停车支付结果 */
		//public static final String REMOTE_RESPONSE_TEMP_CAR_PAY_RESULT = "";
		/** 8-远程服务器发回月租车充值支付结果 */
		//public static final String REMOTE_RESPONSE_MONTH_CAR_PAY_RESULT = "";
		/** 9-远程服务器发起获取剩余车位请求 */
		//public static final String REMOTE_REQUEST_REMIAN_PARKING = "";
		/** 10-远程服务器发起锁车请求 */
		//public static final String REMOTE_REQUEST_LOCK_CAR = "";
		/** 11-远程服务器发起注册车辆信息请求 */
		//public static final String REMOTE_REQUEST_REGISTER_CAR = "";
		/** 12-远程服务器发起车辆进场请求 */
		//public static final String REMOTE_REQUEST_CAR_IN = "";
		/** 13-主动上传车辆信息 */
		//public static final String UP_CAR_INFO = "";
	}

	/**
	 * 远程命令
	 */
	public static final class CommandNo {
		/** 1-主动上传进出记录 */
		public static final int UP_IN_OUT = 1;
		/** 2-主动上传收费记录 */
		//public static final int UP_CHARGE_LOG = 2;
		/** 3-主动上传充值记录 */
		public static final int UP_RECHARGE_LOG = 3;
		/** 4-远程服务器发起临时车停车计费请求 */
		public static final int REMOTE_RESPONSE_TEMP_CAR_PAY = 4;
		/** 5-远程服务器发起月租车充值计费请求 */
		public static final int REMOTE_RESPONSE_MONTH_CAR_PAY = 5;
		/** 6-远程服务器发起获取车辆信息请求 */
		public static final int REMOTE_REQUEST_CAR_INFO = 6;
		/** 7-远程服务器发回临时车停车支付结果 */
		public static final int REMOTE_RESPONSE_TEMP_CAR_PAY_RESULT = 7;
		/** 8-远程服务器发回月租车充值支付结果 */
		public static final int REMOTE_RESPONSE_MONTH_CAR_PAY_RESULT = 8;
		/** 9-远程服务器发起获取剩余车位请求 */
		//public static final int REMOTE_REQUEST_REMIAN_PARKING = 9;
		/** 10-远程服务器发起锁车请求 */
		//public static final int REMOTE_REQUEST_LOCK_CAR = 10;
		/** 11-远程服务器发起注册车辆信息请求 */
		//public static final int REMOTE_REQUEST_REGISTER_CAR = 11;
		/** 12-远程服务器发起车辆进场请求 */
		//public static final int REMOTE_REQUEST_CAR_IN = 12;
		/** 13-主动上传车辆信息 */
		//public static final int UP_CAR_INFO = 13;
	}

	public static final class CommandType {
		public static final String INFO = "info";
		public static final String MONEY = "money";
	}

	/**
	 * <p>华鹏飞车禁客户端返回结果</p>
	 * 0-成功<br>
	 * 1-参数错误<br>
	 * 2-没有查询到数据<br>
	 * 3-保存数据错误<br>
	 * 4-非临时车<br>
	 * 5-非月租车<br>
	 * 6-已支付未超时<br>
	 * 7-其他错误
	 * 
	 * @author liyulin
	 * @version 1.0 2017年6月18日 下午10:29:49
	 */
	public static final class LocalResult {
		/** 0-成功 */
		public static final String SUCCESS = "0";
		/** 1-参数错误 */
		public static final String PARAM_ERROR = "1";
		/** 2-没有查询到数据 */
		public static final String NO_RESULT = "2";
		/** 3-保存数据错误 */
		public static final String SAVE_ERROR = "3";
		/** 4-非临时车 */
		public static final String UN_TEMP_CAR = "4";
		/** 5-非月租车 */
		public static final String UN_MONTH_CAR = "5";
		/** 6-已支付未超时 */
		public static final String PAID_NONE_OUT_TIME = "6";
		/** 7-其他错误 */
		public static final String OTHER_ERROR = "7";
	}
	
	/**
	 * <p>进出方向</p>
	 * 0-进场<br>
	 * 1-出场<br>
	 * 
	 * @author liyulin
	 * @version 1.0 2017年6月19日 上午10:58:59
	 */
	public static final class Direction {
		/**0-进场*/
		public static final String IN = "0";
		/**1-出场*/
		public static final String OUT = "1";
	}
	
	/*车辆类别（2-月租车，3-临时车，4-储值车，5-贵宾车）*/
	public static final class CarType {
		/**2-月租车*/
		public static final String MONTH_CAR = "2";
		/**3-临时车*/
		public static final String TEMP_CAR = "3";
		/**4-储值车*/
		public static final String STORED_CAR = "4";
		/**5-贵宾车*/
		public static final String VIP_CAR = "5";
	}

	/**
	 * <p>解放区返回给华鹏飞车禁的结果</p>
	 * -2-交易拒绝<br>
	 * -1-通信失败<br>
	 *  0-参数错误<br>
	 *  1-入口开闸<br>
	 *  2-出口开闸<br>
	 *  3-收费放行<br>
	 *  4-已进场<br>
	 *  5-已出场<br>
	 *  6-已挂失<br>
	 *  7-已注销<br>
	 *  8-已过期<br>
	 *  9-余额不足<br>
	 *  10-其他错误<br>
	 * 
	 * @author liyulin
	 * @version 1.0 2017年6月25日 下午12:01:22
	 */
	public static final class RemoteResult{
		/** -2-交易拒绝*/
		public static final String REFUSE = "-2";
		/** -1-通信失败*/
		public static final String CONNECTION_FAIL = "-1";
		/** 0-参数错误*/
		public static final String PARAM_ERROR = "0";
		/** 1-入口开闸*/
		public static final String OPEN_IN_DOOR = "1";
		/** 2-出口开闸*/
		public static final String OPEN_OUT_DOOR = "2";
		/** 3-收费放行*/
		public static final String TOLL_RELEASE = "3";
		/** 4-已进场*/
		public static final String GONE_IN = "4";
		/** 5-已出场*/
		public static final String GONE_OUT = "5";
		/** 6-已挂失*/
		public static final String MISSING = "6";
		/** 7-已注销*/
		public static final String CANCEL_ACCOUNT  = "7";
		/** 8-已过期*/
		public static final String OVERDUE = "8";
		/** 9-余额不足*/
		public static final String BALANCE_NOT_ENOUGH = "9";
		/** 10-其他错误*/
		public static final String OTHER_ERROR = "10";
	}
}
