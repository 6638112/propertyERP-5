package com.cnfantasia.server.api.revenueApplyPush.bean;

/**
 * EAS 报销单需要的动态参数配置 
 * @author wenfq 2016-07-01
 *
 */
public class EASBizAccountParam {

	String applierCompany = "HYN_LLL"; // 报销人公司
	String applierDept = "HYN_LLL_01_01"; // 报销人部门
	String applier = "HYNZGJT0290"; // 报销人
	String position = "HYN_LLL_01.01.002"; // 职位
	String payCompany = "HYN_LLL"; // 费用支付公司
	String prior = "LOW"; //优先级
	
	public String getApplierCompany() {
		return applierCompany;
	}
	public void setApplierCompany(String applierCompany) {
		this.applierCompany = applierCompany;
	}
	public String getApplierDept() {
		return applierDept;
	}
	public void setApplierDept(String applierDept) {
		this.applierDept = applierDept;
	}
	public String getApplier() {
		return applier;
	}
	public void setApplier(String applier) {
		this.applier = applier;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPayCompany() {
		return payCompany;
	}
	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}
	public String getPrior() {
		return prior;
	}
	public void setPrior(String prior) {
		this.prior = prior;
	}
	
	public EASBizAccountParam(String applierCompany, String applierDept, String applier, String position, String payCompany, String prior) {
		super();
		this.applierCompany = applierCompany;
		this.applierDept = applierDept;
		this.applier = applier;
		this.position = position;
		this.payCompany = payCompany;
		this.prior = prior;
	}
	
	@Override
	public String toString() {
		return "EASBizAccountParam [applierCompany=" + applierCompany + ", applierDept=" + applierDept + ", applier=" + applier + ", position=" + position + ", payCompany="
				+ payCompany + ", prior=" + prior + "]";
	}
}
