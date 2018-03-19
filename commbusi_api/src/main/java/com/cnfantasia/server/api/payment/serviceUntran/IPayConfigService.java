package com.cnfantasia.server.api.payment.serviceUntran;

/**
 * 支付配置服务类
 * @author shiyl
 *
 */
public interface IPayConfigService {
	/**
	 * 获取粮票支付配置百分比
	 * @param orderType 订单类型
	 * @return
	 */
	Double getPayConfigHbPercent(Integer orderType);
}
