package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigDecimal;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;

/**
 * 收益结果金额
* Filename:    RevenueAmount.java
* @version:    1.0.0
* Create at:   2015年11月16日 下午5:51:15
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月16日       shiyl             1.0             1.0 Version
 */
public class RevenueAmount extends RevenueAmountNoTime{
	private String startTime;
	private String endTime;
	public RevenueAmount(){}
	
	public RevenueAmount(RevenueAmountNoTime noTime,String startTime,String endTime){
		this.revenueRole = noTime.getRevenueRole();
		this.startTime = startTime;
		this.endTime = endTime;
		this.revenueConfig = noTime.getRevenueConfig();
		this.projectType = noTime.getProjectType();
		this.money = noTime.getMoney();
	}
	public RevenueAmount(RevenueRole revenueRole,String startTime,String endTime,RevenueConfig revenueConfig,Integer projectType,BigDecimal money){
		this.revenueRole = revenueRole;
		this.startTime = startTime;
		this.endTime = endTime;
		this.revenueConfig = revenueConfig;
		this.projectType = projectType;
		this.money = money;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((projectType == null) ? 0 : projectType.hashCode());
		result = prime * result + ((revenueConfig == null) ? 0 : revenueConfig.hashCode());
		result = prime * result + ((revenueRole == null) ? 0 : revenueRole.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RevenueAmount other = (RevenueAmount) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (projectType == null) {
			if (other.projectType != null)
				return false;
		} else if (!projectType.equals(other.projectType))
			return false;
		if (revenueConfig == null) {
			if (other.revenueConfig != null)
				return false;
		} else if (!revenueConfig.equals(other.revenueConfig))
			return false;
		if (revenueRole == null) {
			if (other.revenueRole != null)
				return false;
		} else if (!revenueRole.equals(other.revenueRole))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}
}
