package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigDecimal;

public class DredgeRevenue {
	private BigDecimal amountOrderReal;
	private BigDecimal srcMoney;
	private BigDecimal amountRepair;
	
	public BigDecimal getAmountOrderReal() {
		return amountOrderReal;
	}

	public void setAmountOrderReal(BigDecimal amountOrderReal) {
		this.amountOrderReal = amountOrderReal;
	}

	public BigDecimal getSrcMoney() {
		return srcMoney;
	}

	public void setSrcMoney(BigDecimal srcMoney) {
		this.srcMoney = srcMoney;
	}

	public BigDecimal getAmountRepair() {
		return amountRepair;
	}

	public void setAmountRepair(BigDecimal amountRepair) {
		this.amountRepair = amountRepair;
	}

}
