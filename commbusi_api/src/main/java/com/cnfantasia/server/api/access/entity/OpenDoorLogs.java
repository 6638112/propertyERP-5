package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

public class OpenDoorLogs {
	private Integer openStatus;
	private String failReason;
	private String openTime;
	private BigInteger buildingId;
	public Integer getOpenStatus() {
		return openStatus;
	}
	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public BigInteger getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(BigInteger buildingId) {
		this.buildingId = buildingId;
	}
	
}
