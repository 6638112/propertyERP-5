package com.cnfantasia.server.ms.revenue.dao;


public class EachRoleCount {
	private Integer adminCount;
	private Integer companyCount;
	private Integer agentCount;
	private Integer financeCount;
	private Integer downStarCount;
	private Integer masterCount;
	private Integer pcManagermentCount;//物业管理处
	private Integer serviceCount;//客服
	private Integer districtCount;//客服
	
	public Integer getAdminCount() {
		return adminCount;
	}
	public Integer getPcManagermentCount() {
		return pcManagermentCount;
	}
	public void setPcManagermentCount(Integer pcManagermentCount) {
		this.pcManagermentCount = pcManagermentCount;
	}
	public void setAdminCount(Integer adminCount) {
		this.adminCount = adminCount;
	}
	public Integer getCompanyCount() {
		return companyCount;
	}
	public void setCompanyCount(Integer companyCount) {
		this.companyCount = companyCount;
	}
	public Integer getAgentCount() {
		return agentCount;
	}
	public void setAgentCount(Integer agentCount) {
		this.agentCount = agentCount;
	}
	public Integer getFinanceCount() {
		return financeCount;
	}
	public void setFinanceCount(Integer financeCount) {
		this.financeCount = financeCount;
	}
	public Integer getDownStarCount() {
		return downStarCount;
	}
	public void setDownStarCount(Integer downStarCount) {
		this.downStarCount = downStarCount;
	}
	public Integer getMasterCount() {
		return masterCount;
	}
	public void setMasterCount(Integer masterCount) {
		this.masterCount = masterCount;
	}
	public Integer getServiceCount() {
		return serviceCount;
	}
	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}
	public Integer getDistrictCount() {
		return districtCount;
	}
	public void setDistrictCount(Integer districtCount) {
		this.districtCount = districtCount;
	}
	
}
