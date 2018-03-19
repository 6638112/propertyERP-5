package com.cnfantasia.server.api.property.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 预支付order信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月15日 上午9:30:12
 */
public class PreOrderDto {

	/** 总金额（单位：分） */
	private BigDecimal totalAmt;
	/** 实付金额 （单位：分） */
	private BigDecimal realAmt;
	/** 补贴金额（单位：分） */
	private BigDecimal amtBt;
	/** 订单id */
	private BigInteger orderId;
	/** 是否免单 */
	private boolean isFree;

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getRealAmt() {
		if(realAmt == null) {
			realAmt = BigDecimal.ZERO;
		}
		return realAmt;
	}

	public void setRealAmt(BigDecimal realAmt) {
		this.realAmt = realAmt;
	}

	public BigDecimal getAmtBt() {
		return amtBt;
	}

	public void setAmtBt(BigDecimal amtBt) {
		this.amtBt = amtBt;
	}

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

}
