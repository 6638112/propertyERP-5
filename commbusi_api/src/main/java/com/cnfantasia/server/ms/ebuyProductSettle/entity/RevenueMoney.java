package com.cnfantasia.server.ms.ebuyProductSettle.entity;

import java.math.BigDecimal;

/**
 * 结算金额
 * 
 * @author liyulin
 * @version 1.0 2016年9月19日 下午2:27:19
 */
public final class RevenueMoney {

	/** 结算金额 */
	private BigDecimal totalAmount;
	/** 平台补贴额 */
	private BigDecimal amountPtbt;
	/** 用户实缴 */
	private BigDecimal amountUsrReal;

	/** 结算金额 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/** 结算金额 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/** 平台补贴额 */
	public BigDecimal getAmountPtbt() {
		return amountPtbt;
	}

	/** 平台补贴额 */
	public void setAmountPtbt(BigDecimal amountPtbt) {
		this.amountPtbt = amountPtbt;
	}

	/** 用户实缴 */
	public BigDecimal getAmountUsrReal() {
		return amountUsrReal;
	}

	/** 用户实缴 */
	public void setAmountUsrReal(BigDecimal amountUsrReal) {
		this.amountUsrReal = amountUsrReal;
	}

	@Override
	public String toString() {
		return "RevenueMoney [totalAmount=" + totalAmount + ", amountPtbt="
				+ amountPtbt + ", amountUsrReal=" + amountUsrReal + "]";
	}

}
