package com.cnfantasia.server.api.ebuyorder.entity;

public class GroupBuilding {
	long gbId;
	
	String gbName;
	
	/**
	 * 小区所在省市区
	 */
	String gbArea;
	
	String gbAddress;

	public long getGbId() {
		return gbId;
	}

	public void setGbId(long gbId) {
		this.gbId = gbId;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getGbArea() {
		return gbArea;
	}

	public void setGbArea(String gbArea) {
		this.gbArea = gbArea;
	}

	public String getGbAddress() {
		return gbAddress;
	}

	public void setGbAddress(String gbAddress) {
		this.gbAddress = gbAddress;
	}
}
