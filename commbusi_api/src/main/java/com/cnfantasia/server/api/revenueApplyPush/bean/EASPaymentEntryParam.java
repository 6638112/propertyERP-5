package com.cnfantasia.server.api.revenueApplyPush.bean;

public class EASPaymentEntryParam {
	// 角色 业务类型 费用类型 流出预算项目
	String roleName;
	String expenseType; // 费用类型
	String budgetNumber; // 预算编号
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public String getBudgetNumber() {
		return budgetNumber;
	}
	public void setBudgetNumber(String budgetNumber) {
		this.budgetNumber = budgetNumber;
	}
}
