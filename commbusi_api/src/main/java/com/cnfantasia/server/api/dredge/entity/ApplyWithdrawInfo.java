package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;

/**
 * 申请提现，返回提现结果Entity
 * @author wenfq
 *
 */
public class ApplyWithdrawInfo {
//	jsonResponse.putData("billAmt", 120.00);
//	jsonResponse.putData("billCount", "3笔");
//	jsonResponse.putData("submitTime", "2015-11-19 18:20");
//	jsonResponse.putData("bankName", "提款银行");
//	jsonResponse.putData("bankCardNumber", "6225********4546");
//	jsonResponse.putData("tkStatus", "提款中");
	
	String billCount;
	BigDecimal totalAmt;//合计金额
	BigDecimal billAmt;//工单金额
	BigDecimal subsidyAmt = BigDecimal.ZERO;//补贴金额
	BigDecimal costAmt = BigDecimal.ZERO;//平台费用
	String submitTime;
	String bankName;
	String bankCardNumber;
	String tkStatus;
	public BigDecimal getTotalAmt() {
		return billAmt.add(subsidyAmt).subtract(costAmt).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public BigDecimal getSubsidyAmt() {
		return subsidyAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setSubsidyAmt(BigDecimal subsidyAmt) {
		this.subsidyAmt = subsidyAmt;
	}
	public BigDecimal getCostAmt() {
		return costAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setCostAmt(BigDecimal costAmt) {
		this.costAmt = costAmt;
	}
	public BigDecimal getBillAmt() {
		return billAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setBillAmt(BigDecimal billAmt) {
		this.billAmt = billAmt;
	}
	public String getBillCount() {
		return billCount;
	}
	public void setBillCount(String billCount) {
		this.billCount = billCount;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	public String getTkStatus() {
		return tkStatus;
	}
	public void setTkStatus(String tkStatus) {
		this.tkStatus = tkStatus;
	}
	
}
