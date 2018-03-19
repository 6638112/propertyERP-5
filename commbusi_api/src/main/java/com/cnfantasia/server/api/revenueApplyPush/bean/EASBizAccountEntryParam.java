package com.cnfantasia.server.api.revenueApplyPush.bean;

/**
 * EAS 报销单分录 需要的动态参数配置 
 * @author wenfq 2016-07-01
 *
 */
public class EASBizAccountEntryParam {
	String budgetNumber = "LLL.02"; // 预算编号
	String operationType = "AL.01"; // 业务类别
	String expenseType = "AI.01.01"; // 费用类型
	String purpose = "";

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
}
