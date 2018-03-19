package com.cnfantasia.server.api.cashSqpayBt.constants;

/**
 * 双乾支付优惠补贴明细表常量
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 上午11:42:14
 */
public final class CashSqpayBtConstants {

	/**
	 * 订单类型=={"1":"电商商品","2":物业费","3":"维修","4":"停车费","5":"其他代收费","6":"物业代扣卡"}
	 * 
	 * @author liyulin
	 * @version 1.0 2016年9月9日 上午11:42:47
	 */
	public final static class OrderType {
		/** 电商商品 */
		public final static Integer EBUY_FEE = 1;
		/** 物业费 */
		public final static Integer WY_FEE = 2;
		/** 维修 */
		public final static Integer REPAIR_FEE = 3;
		/** 停车费 */
		public final static Integer PARKING_FEE = 4;
		/** 其他代收费 */
		public final static Integer OTHER_FEE = 5;
		/** 物业代扣卡 */
		public final static Integer WY_CARD_FEE = 6;
	}
}
