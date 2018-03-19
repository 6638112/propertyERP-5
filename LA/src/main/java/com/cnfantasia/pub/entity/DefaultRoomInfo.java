package com.cnfantasia.pub.entity;

public class DefaultRoomInfo {
	String id;
	String roomNum;
	String building;
	String groupBuilding;
	String block;
	String city;
	String province;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getGroupBuilding() {
		return groupBuilding;
	}

	public void setGroupBuilding(String groupBuilding) {
		this.groupBuilding = groupBuilding;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getTotalAddress() {
		return totalAddress;
	}

	public void setTotalAddress(String totalAddress) {
		this.totalAddress = totalAddress;
	}
	String totalAddress;
}
