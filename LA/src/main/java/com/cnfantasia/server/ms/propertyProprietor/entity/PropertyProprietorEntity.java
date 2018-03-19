package com.cnfantasia.server.ms.propertyProprietor.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;

public class PropertyProprietorEntity extends PropertyProprietor {
	/**
	 * 城市
	 */
	private String cityName;

	/**
	 * 小区名称
	 */
	private String groupBuildingName;

	/**
	 * 小区ID
	 */
	private BigInteger groupbuildingId;

	/**
	 * 合同号
	 */
	private String contactNum;

	/**
	 * 楼栋号
	 */
	private String buildingName;

	/**
	 * 楼栋Id
	 */
	private BigInteger buidingId;

	/**
	 * 房间-单元号
	 */
	private String realRoomUnitName;

	/**
	 * 房间-房间号
	 */
	private String realRoomNum;

	private BigInteger realRoomId;

	public BigInteger getGroupbuildingId() {
		return groupbuildingId;
	}

	public void setGroupbuildingId(BigInteger groupbuildingId) {
		this.groupbuildingId = groupbuildingId;
	}

	public BigInteger getBuidingId() {
		return buidingId;
	}

	public void setBuidingId(BigInteger buidingId) {
		this.buidingId = buidingId;
	}

	public BigInteger getRealRoomId() {
		return realRoomId;
	}

	public void setRealRoomId(BigInteger realRoomId) {
		this.realRoomId = realRoomId;
	}

	public String getGroupBuildingName() {
		return groupBuildingName;
	}

	public void setGroupBuildingName(String groupBuildingName) {
		this.groupBuildingName = groupBuildingName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRealRoomUnitName() {
		return realRoomUnitName;
	}

	public void setRealRoomUnitName(String realRoomUnitName) {
		this.realRoomUnitName = realRoomUnitName;
	}

	public String getRealRoomNum() {
		return realRoomNum;
	}

	public void setRealRoomNum(String realRoomNum) {
		this.realRoomNum = realRoomNum;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
}
