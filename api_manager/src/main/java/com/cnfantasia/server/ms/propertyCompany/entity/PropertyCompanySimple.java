package com.cnfantasia.server.ms.propertyCompany.entity;

import java.math.BigInteger;

public class PropertyCompanySimple {
	/**
	 * 物业公司名称
	 */
	String pcName;
	/**
	 * 物业公司的合作状态：1初级，2高级，3全面
	 */
	int cooperationType = 0;
	/**物业公司id*/
	private BigInteger pcId;
	/**管辖小区数量*/
	private int groupBuildingCount;
	/**合作时间*/
	private String cooperationTime;
	
	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public int getCooperationType() {
		return cooperationType;
	}

	public void setCooperationType(int cooperationType) {
		this.cooperationType = cooperationType;
	}

	public BigInteger getPcId() {
		return pcId;
	}

	public void setPcId(BigInteger pcId) {
		this.pcId = pcId;
	}

	public int getGroupBuildingCount() {
		return groupBuildingCount;
	}

	public void setGroupBuildingCount(int groupBuildingCount) {
		this.groupBuildingCount = groupBuildingCount;
	}

	public String getCooperationTime() {
		return cooperationTime;
	}

	public void setCooperationTime(String cooperationTime) {
		this.cooperationTime = cooperationTime;
	}
}
