package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;

public class RevenueConfigSimple {
	/**数据库收益记录配置的Id  */
	private BigInteger id;
	/** 规则类型 */
	private Integer ruleType;
	/** 收益配置取值 */
	private Double calcValue;
	/**配置原始数据*/
	private RevenueConfig revenueConfig;
	
	public RevenueConfigSimple(BigInteger id,Integer ruleType,Double calcValue,RevenueConfig revenueConfig){
		this.id = id;
		this.ruleType = ruleType;
		this.calcValue = calcValue;
		this.revenueConfig = revenueConfig;
	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Integer getRuleType() {
		return ruleType;
	}
	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}
	public Double getCalcValue() {
		return calcValue;
	}
	public void setCalcValue(Double calcValue) {
		this.calcValue = calcValue;
	}
	public RevenueConfig getRevenueConfig() {
		return revenueConfig;
	}
	public void setRevenueConfig(RevenueConfig revenueConfig) {
		this.revenueConfig = revenueConfig;
	}
	
}
