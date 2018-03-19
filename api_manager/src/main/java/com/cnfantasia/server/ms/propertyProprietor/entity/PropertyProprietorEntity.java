package com.cnfantasia.server.ms.propertyProprietor.entity;

import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;

import java.math.BigInteger;

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
	private BigInteger buildingId;

	/**
	 * 房间-单元号
	 */
	private String realRoomUnitName;

	/**
	 * 房间-房间号
	 */
	private String realRoomNum;

	Double roomSize = Double.valueOf(0); //房屋面积
	Double roomManagerPrice = Double.valueOf(0); //管理费单价
	Double manangeFee;//物业费
	
	// 用户提交的记录数
	private int recordNum;

	private BigInteger realRoomId;

	private int saleStatus;//出售情况
	private int livingStatus;//居住情况
	private int leaseStatus;//出租情况

	public int getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(int saleStatus) {
		this.saleStatus = saleStatus;
	}

	public int getLivingStatus() {
		return livingStatus;
	}

	public void setLivingStatus(int livingStatus) {
		this.livingStatus = livingStatus;
	}

	public int getLeaseStatus() {
		return leaseStatus;
	}

	public void setLeaseStatus(int leaseStatus) {
		this.leaseStatus = leaseStatus;
	}

	public Double getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(Double roomSize) {
		this.roomSize = roomSize;
	}

	public Double getRoomManagerPrice() {
		return roomManagerPrice;
	}

	public void setRoomManagerPrice(Double roomManagerPrice) {
		this.roomManagerPrice = roomManagerPrice;
	}

	public Double getManangeFee() {
		return manangeFee;
	}

	public void setManangeFee(Double manangeFee) {
		this.manangeFee = manangeFee;
	}

	public BigInteger getGroupbuildingId() {
		return groupbuildingId;
	}

	public void setGroupbuildingId(BigInteger groupbuildingId) {
		this.groupbuildingId = groupbuildingId;
	}

	public BigInteger getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(BigInteger buildingId) {
		this.buildingId = buildingId;
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

	public int getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}
}
