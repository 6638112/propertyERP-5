package com.cnfantasia.server.api.revenueApplyPush.bean;

import java.math.BigDecimal;

public class BizAccountEntryEntity {
	private String budgetNumber;			// 预算编号
	private String operationType;				// 业务类别
	private String expenseType;					// 费用类型
	private String purpose;						// 费用说明
	private String happenTime;					// 发生时间
	private BigDecimal originalAmount;			// 原币金额
	private BigDecimal amount;					// 金额(人民币)
	private BigDecimal approvedAmount;			// 核定金额
	private String comment;						// 备注
	private String leaseItem;//项目
	
	public String getLeaseItem() {
		return leaseItem;
	}
	public void setLeaseItem(String leaseItem) {
		this.leaseItem = leaseItem;
	}
	public String getBudgetNumber() {
		return budgetNumber;
	}
	public void setBudgetNumber(String budgetNumber) {
		this.budgetNumber = budgetNumber;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getHappenTime() {
		return happenTime;
	}
	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
	}
	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}
	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(BigDecimal approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
