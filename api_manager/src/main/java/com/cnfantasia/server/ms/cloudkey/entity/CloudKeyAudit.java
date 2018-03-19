package com.cnfantasia.server.ms.cloudkey.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;

public class CloudKeyAudit extends CloudKeyUserAudit{
	private String gbName;
	private String buildingname;
	private String unitName;
	private String roomNum;
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public String getBuildingname() {
		return buildingname;
	}
	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
}
