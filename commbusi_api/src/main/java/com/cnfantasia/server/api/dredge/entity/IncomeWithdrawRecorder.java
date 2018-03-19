package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 收入与提现记录
 * 
 * @author wenfq 2015-08-17
 *
 */
public class IncomeWithdrawRecorder {
	/**
	 * 疏通单id
	 */
	BigInteger dredgeBillId;

	/**
	 * 疏通单描述
	 */
	String billDesc;
	/**
	 * 提交日期
	 */
	String submitDate;
	/**
	 * 收入（提现）金额
	 */
	BigDecimal amount;

	public BigInteger getDredgeBillId() {
		return dredgeBillId;
	}

	public void setDredgeBillId(BigInteger dredgeBillId) {
		this.dredgeBillId = dredgeBillId;
	}

	public String getBillDesc() {
		return billDesc;
	}

	public void setBillDesc(String billDesc) {
		this.billDesc = billDesc;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
