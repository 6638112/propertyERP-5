package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

public class UserDoorKeyList {
	/** 楼栋id */
	private BigInteger buildingId;
	/** 门禁密钥 */
	private String doorKey;
	/** 供应商类型=={"1":"立方","2";"芝麻开门"} */
	private Integer type;
	/** 门禁位置描述 */
	private String doorName;

	public BigInteger getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(BigInteger buildingId) {
		this.buildingId = buildingId;
	}

	public String getDoorKey() {
		return doorKey;
	}

	public void setDoorKey(String doorKey) {
		this.doorKey = doorKey;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDoorName() {
		return doorName;
	}

	public void setDoorName(String doorName) {
		this.doorName = doorName;
	}

}
