package com.cnfantasia.server.api.property.dto;

import java.math.BigInteger;

/**
 * 确认支付结果
 * 
 * @author liyulin
 * @version 1.0 2017年3月15日 上午10:21:04
 */
public class ConfirmPayResultDto {

	/** 订单id */
	private BigInteger orderId;
	/** 是否免单 */
	private boolean isFree;
	/** 是否用了红包 */
	private boolean isUseHb;

	public BigInteger getOrderId() {
		return orderId;
	}

	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public boolean isUseHb() {
		return isUseHb;
	}

	public void setUseHb(boolean isUseHb) {
		this.isUseHb = isUseHb;
	}

}
