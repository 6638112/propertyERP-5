package com.cnfantasia.server.ms.payBill.entity;

import java.math.BigDecimal;

/**
 * 费用详情
 * @author wenfq 2016-01-18
 *
 */
public class FeeDetail {
	String name;
	BigDecimal amount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
