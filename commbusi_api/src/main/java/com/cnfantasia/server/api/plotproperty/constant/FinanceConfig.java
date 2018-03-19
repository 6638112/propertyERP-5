package com.cnfantasia.server.api.plotproperty.constant;

/**
 * Finance常量配置
 *
 * @author Liyl
 * @version 1.0 2016年3月29日 下午1:12:16
 */
public final class FinanceConfig {
	/**
	 * Finance类型，对应表t_finance_buy字段f_finance_type
	 *
	 * @author Liyl
	 * @version 1.0 2016年3月29日 下午1:10:15
	 */
	public interface FinanceType{
		public final static String FINANCE_PLOTPROPERTY = "1";// 物业宝
		public final static String FINANCE_CAR          = "2";// 停车宝
	}
}
