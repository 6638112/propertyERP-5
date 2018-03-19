package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;

/**
 * 维修收益明细
 * @author wenfq 2016-05-26
 *
 */
public class RSADetail {
	//BigDecimal totalAmt;//总的可提金额
	BigDecimal subsidyAmt;//补贴金额
	BigDecimal costAmt;//平台费用
	
	long id;
	int feeType;
	String feeName;
	BigDecimal feeAmount;//总的可提金额
	int isWithdrawed;
	int canWithdrawDays;
	
	public BigDecimal getSubsidyAmt() {
		return subsidyAmt;
	}
	public void setSubsidyAmt(BigDecimal subsidyAmt) {
		this.subsidyAmt = subsidyAmt;
	}
	public BigDecimal getCostAmt() {
		return costAmt;
	}
	public void setCostAmt(BigDecimal costAmt) {
		this.costAmt = costAmt;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getFeeType() {
		return feeType;
	}
	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public BigDecimal getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
	public int getIsWithdrawed() {
		return isWithdrawed;
	}
	public void setIsWithdrawed(int isWithdrawed) {
		this.isWithdrawed = isWithdrawed;
	}
	public int getCanWithdrawDays() {
		return canWithdrawDays;
	}
	public void setCanWithdrawDays(int canWithdrawDays) {
		this.canWithdrawDays = canWithdrawDays;
	}
	
	
}
