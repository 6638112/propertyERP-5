package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;

/**
 * 可提现维修单
 * 
 * @author wenfq
 *
 */
public class CanWithdrawBill {
	// canWithdrawInfo.put("dredgeType", "下水道疏通");
	// canWithdrawInfo.put("totalAmt", 65); 
	// canWithdrawInfo.put("billAmt", 60); 
	// canWithdrawInfo.put("billAddress", "青青世界A-103室"); 
	// canWithdrawInfo.put("submitDate", "2015-11-20"); 
	// canWithdrawInfo.put("subsidyAmt", 5); 
	// canWithdrawInfo.put("costAmt", 0); 
	
	long dredgeBillId; //维修单id
	String dredgeType;
	BigDecimal totalAmt;//总金额
	BigDecimal billAmt;//订单金额
	BigDecimal subsidyAmt;//补贴金额
	BigDecimal costAmt;//平台费用
	String billAddress;//订单地址
	
	String payTime;//交易时间
	
	String submitDate;//预约时间
	String userMobile;//预约手机号
	
	String referrerMobile;//推荐人手机号

	public String getReferrerMobile() {
		return referrerMobile;
	}

	public void setReferrerMobile(String referrerMobile) {
		this.referrerMobile = referrerMobile;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public long getDredgeBillId() {
		return dredgeBillId;
	}

	public void setDredgeBillId(long dredgeBillId) {
		this.dredgeBillId = dredgeBillId;
	}

	public String getDredgeType() {
		return dredgeType;
	}

	public void setDredgeType(String dredgeType) {
		this.dredgeType = dredgeType;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getBillAmt() {
		return billAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setBillAmt(BigDecimal billAmt) {
		this.billAmt = billAmt;
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

	public String getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

}
