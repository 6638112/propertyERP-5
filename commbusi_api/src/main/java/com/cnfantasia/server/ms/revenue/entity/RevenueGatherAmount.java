package com.cnfantasia.server.ms.revenue.entity;

import java.io.Serializable;

/**
 * 各角色收益金额
 * @author shiyl
 *
 */
public class RevenueGatherAmount implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 收益规则计算总值=物业公司+代理+平台 */
	private Long totalAmount;
	/** 物业收益 */
	private Long companyAmount;
	/** 代理收益 */
	private Long agentAmount;
	/** 平台收益 */
	private Long platformAmount;
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getCompanyAmount() {
		return companyAmount;
	}
	public void setCompanyAmount(Long companyAmount) {
		this.companyAmount = companyAmount;
	}
	public Long getAgentAmount() {
		return agentAmount;
	}
	public void setAgentAmount(Long agentAmount) {
		this.agentAmount = agentAmount;
	}
	public Long getPlatformAmount() {
		return platformAmount;
	}
	public void setPlatformAmount(Long platformAmount) {
		this.platformAmount = platformAmount;
	}
	
	
}
