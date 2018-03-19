package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * 物业公司信息，包含收益配置请求
 */
public class PropCompanyWithRevenue {
	/**物业公司Id*/
	private BigInteger companyId;
	/**物业公司名称*/
	private String companyName;
	/**代理名称*/
	private String agentName;
	/**收益配置预览信息*/
	private List<RevenueProjectInfo> projectInfoList;
	
//	public int getProjectSize(){
//		return projectInfoList==null?0:projectInfoList.size();
//	}
	
	public BigInteger getCompanyId() {
		return companyId;
	}
	public void setCompanyId(BigInteger companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public List<RevenueProjectInfo> getProjectInfoList() {
		return projectInfoList;
	}
	public void setProjectInfoList(List<RevenueProjectInfo> projectInfoList) {
		this.projectInfoList = projectInfoList;
	}
	
}
