package com.cnfantasia.server.ms.revenue.entity;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;

/**
 * 包含实际起止时间的配置规则实体类
* Filename:    RevenueConfigByTime.java
* @version:    1.0.0
* Create at:   2015年11月16日 下午4:40:13
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月16日       shiyl             1.0             1.0 Version
 */
public class RevenueConfigByTime {
	private String startTime;
	private String endTime;//截止日期+1天假,取值范围[startTime,endTime)
	private RevenueConfig revenueConfig;
	public RevenueConfig getRevenueConfig() {
		return revenueConfig;
	}
	public void setRevenueConfig(RevenueConfig revenueConfig) {
		this.revenueConfig = revenueConfig;
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
		result = prime * result + ((revenueConfig == null) ? 0 : revenueConfig.hashCode());
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
		RevenueConfigByTime other = (RevenueConfigByTime) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (revenueConfig == null) {
			if (other.revenueConfig != null)
				return false;
		} else if (!revenueConfig.equals(other.revenueConfig))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}
	
}
