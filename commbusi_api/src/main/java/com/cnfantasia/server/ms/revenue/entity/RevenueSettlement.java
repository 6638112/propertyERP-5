package com.cnfantasia.server.ms.revenue.entity;


/**
 * 收益结算 
 * @author wenfq
 *
 */
public class RevenueSettlement {
	String tkNo;
	String applyTime;
	long applyId;
	double totalAmount;
	int tkStatus;
	private Integer goalType;
	public String getTkNo() {
		return tkNo;
	}
	public void setTkNo(String tkNo) {
		this.tkNo = tkNo;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public long getApplyId() {
		return applyId;
	}
	public void setApplyId(long applyId) {
		this.applyId = applyId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getTkStatus() {
		return tkStatus;
	}
	public void setTkStatus(int tkStatus) {
		this.tkStatus = tkStatus;
	}
	public Integer getGoalType() {
		return goalType;
	}
	public void setGoalType(Integer goalType) {
		this.goalType = goalType;
	}
	
}
