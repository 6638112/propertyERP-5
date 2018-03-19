package com.cnfantasia.server.api.payment.dao;

/**
 * 支付配置
 * @author shiyl
 *
 */
public interface IPayConfigDao {
	
	/**
	 * 获取粮票支付配置百分比
	 * @param orderType 订单类型
	 * @return
	 */
	Double selectPayConfigHbPercent(Integer orderType);
}
