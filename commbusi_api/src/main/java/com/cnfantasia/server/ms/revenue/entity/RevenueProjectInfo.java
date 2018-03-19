package com.cnfantasia.server.ms.revenue.entity;

/**
 * 收益配置预览信息
 */
public class RevenueProjectInfo {
	/**项目类别*/
	private Integer projectType;
	/**已配置数量*/
	private Integer configCount;
	/**最早配置开始时间*/
	private String startTime;
	
	public Integer getProjectType() {
		return projectType;
	}
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
	public Integer getConfigCount() {
		return configCount;
	}
	public void setConfigCount(Integer configCount) {
		this.configCount = configCount;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
}
