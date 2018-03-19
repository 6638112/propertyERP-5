package com.cnfantasia.server.api.loan.entity;

import java.math.BigDecimal;

/**
 * 用电缴费金额及缴费时间
 * 
 * @author liyulin
 * @version 1.0 2017年7月4日 下午5:10:10
 */
public class ChargeInfoEntity {
	/** 缴费次数 */
	private Integer chargeCount;
	/** 缴费总金额（单位：元） */
	private BigDecimal totalAmount;
	/** 实缴总金额（单位：元） */
	private BigDecimal realAmount;
	/** 最近一次缴费时间 */
	private String lastPayTime;

	public Integer getChargeCount() {
		return chargeCount;
	}

	public void setChargeCount(Integer chargeCount) {
		this.chargeCount = chargeCount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public String getLastPayTime() {
		return lastPayTime;
	}

	public void setLastPayTime(String lastPayTime) {
		this.lastPayTime = lastPayTime;
	}

}