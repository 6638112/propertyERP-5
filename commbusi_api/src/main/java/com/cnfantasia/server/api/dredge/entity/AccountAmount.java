package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;

/**
 * 账户金额
 * @author wenfq 2015-08-17
 *
 */
public class AccountAmount {
	/**
	 * 账户余额
	 */
	BigDecimal balance;

	/**
	 * 总收入
	 */
	BigDecimal allIncome;

	/**
	 * 累计提现
	 */
	BigDecimal allWithdraw;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getAllIncome() {
		return allIncome;
	}

	public void setAllIncome(BigDecimal allIncome) {
		this.allIncome = allIncome;
	}

	public BigDecimal getAllWithdraw() {
		return allWithdraw;
	}

	public void setAllWithdraw(BigDecimal allWithdraw) {
		this.allWithdraw = allWithdraw;
	}
}
