package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 我的账户 （师傅）
 * @author wenfq 2015-11-25
 *
 */
public class MyAccount {
	/**
	 * 总的可提现金额
	 */
	BigDecimal canWithdrawAmt; 

	/**
	 * 可提现维修单列表
	 */
	List<CanWithdrawBill> canWithdrawBillList;

	public BigDecimal getCanWithdrawAmt() {
		return canWithdrawAmt;
	}

	public void setCanWithdrawAmt(BigDecimal canWithdrawAmt) {
		this.canWithdrawAmt = canWithdrawAmt;
	}

	public List<CanWithdrawBill> getCanWithdrawBillList() {
		return canWithdrawBillList;
	}

	public void setCanWithdrawBillList(List<CanWithdrawBill> canWithdrawBillList) {
		this.canWithdrawBillList = canWithdrawBillList;
	}
}
