package com.cnfantasia.server.api.payment.entity;

import java.math.BigInteger;

/**
 * 双乾补贴写入信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月20日 下午4:27:54
 */
public class SqPayBtInfoDto {
	private BigInteger orderId;
	private Integer orderType;
	private Double amountBt;

	public BigInteger getOrderId() {
		return orderId;
	}

	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Double getAmountBt() {
		return amountBt;
	}

	public void setAmountBt(Double amountBt) {
		this.amountBt = amountBt;
	}

}
