package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigDecimal;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;

public class RevenueAmountNoTime {

	protected RevenueRole revenueRole;
	protected RevenueConfig revenueConfig;
	protected Integer projectType;
	protected BigDecimal money;
	public RevenueAmountNoTime(){}
	public RevenueAmountNoTime(RevenueRole revenueRole, RevenueConfig revenueConfig, Integer projectType, BigDecimal money) {
		super();
		this.revenueRole = revenueRole;
		this.revenueConfig = revenueConfig;
		this.projectType = projectType;
		this.money = money;
	}
	
	public RevenueRole getRevenueRole() {
		return revenueRole;
	}
	public void setRevenueRole(RevenueRole revenueRole) {
		this.revenueRole = revenueRole;
	}
	
	public RevenueConfig getRevenueConfig() {
		return revenueConfig;
	}
	public void setRevenueConfig(RevenueConfig revenueConfig) {
		this.revenueConfig = revenueConfig;
	}
	public Integer getProjectType() {
		return projectType;
	}
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
}
