package com.cnfantasia.server.ms.property.importer.entity;

import java.math.BigInteger;

public class BuildingRoomProprietor {
	BigInteger buildingId;
	String buildingName;
	BigInteger roomId;
	String roomUnit; //单元号
	String roomNumber; //房间号

	Double roomSize = Double.valueOf(0); //房屋面积
	Double roomManagerPrice = Double.valueOf(0); //管理费单价
	Double manangeFee;//物业费

	/** 出售情况 */
	private Integer saleStatus;
	/** 居住情况 */
	private Integer livingStatus;
	/** 出租情况 */
	private Integer leaseStatus;

	String proprietorName1;
	String proprietorPhone1;
	String proprietorIdNumber1; //业主身份证号
	String proprietorName2;
	String proprietorPhone2;
	String proprietorIdNumber2; //业主身份证号

	public BigInteger getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(BigInteger buildingId) {
		this.buildingId = buildingId;
	}

	public BigInteger getRoomId() {
		return roomId;
	}

	public void setRoomId(BigInteger roomId) {
		this.roomId = roomId;
	}

	public Double getManangeFee() {
		return manangeFee;
	}

	public void setManangeFee(double manangeFee) {
		this.manangeFee = manangeFee;
	}

	public void setRoomSize(Double roomSize) {
		this.roomSize = roomSize;
	}

	public void setRoomManagerPrice(Double roomManagerPrice) {
		this.roomManagerPrice = roomManagerPrice;
	}

	public void setManangeFee(Double manangeFee) {
		this.manangeFee = manangeFee;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoomUnit() {
		return roomUnit;
	}

	public void setRoomUnit(String roomUnit) {
		this.roomUnit = roomUnit;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}


	public Double getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(double roomSize) {
		this.roomSize = roomSize;
	}

	public Double getRoomManagerPrice() {
		return roomManagerPrice;
	}

	public void setRoomManagerPrice(double roomManagerPrice) {
		this.roomManagerPrice = roomManagerPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildingName == null) ? 0 : buildingName.hashCode());
		result = prime * result + ((roomNumber == null) ? 0 : roomNumber.hashCode());
		result = prime * result + ((roomUnit == null) ? 0 : roomUnit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuildingRoomProprietor other = (BuildingRoomProprietor) obj;
		if (buildingName == null) {
			if (other.buildingName != null)
				return false;
		} else if (!buildingName.equals(other.buildingName))
			return false;

		if (roomUnit == null) {
			if (other.roomUnit != null)
				return false;
		} else if (!roomUnit.equals(other.roomUnit))
			return false;
		
		if (roomNumber == null) {
			if (other.roomNumber != null)
				return false;
		} else if (!roomNumber.equals(other.roomNumber))
			return false;

		
		return true;
	}

	public String getProprietorName1() {
		return proprietorName1;
	}

	public void setProprietorName1(String proprietorName1) {
		this.proprietorName1 = proprietorName1;
	}

	public String getProprietorPhone1() {
		return proprietorPhone1;
	}

	public void setProprietorPhone1(String proprietorPhone1) {
		this.proprietorPhone1 = proprietorPhone1;
	}

	public String getProprietorIdNumber1() {
		return proprietorIdNumber1;
	}

	public void setProprietorIdNumber1(String proprietorIdNumber1) {
		this.proprietorIdNumber1 = proprietorIdNumber1;
	}

	public String getProprietorName2() {
		return proprietorName2;
	}

	public void setProprietorName2(String proprietorName2) {
		this.proprietorName2 = proprietorName2;
	}

	public String getProprietorPhone2() {
		return proprietorPhone2;
	}

	public void setProprietorPhone2(String proprietorPhone2) {
		this.proprietorPhone2 = proprietorPhone2;
	}

	public String getProprietorIdNumber2() {
		return proprietorIdNumber2;
	}

	public void setProprietorIdNumber2(String proprietorIdNumber2) {
		this.proprietorIdNumber2 = proprietorIdNumber2;
	}

	@Override
	public String toString() {
		return "BuildingRoomProprietor{" +
				"buildingId=" + buildingId +
				", buildingName='" + buildingName + '\'' +
				", roomId=" + roomId +
				", roomUnit='" + roomUnit + '\'' +
				", roomNumber='" + roomNumber + '\'' +
				", roomSize=" + roomSize +
				", roomManagerPrice=" + roomManagerPrice +
				", manangeFee=" + manangeFee +
				", proprietorName1='" + proprietorName1 + '\'' +
				", proprietorPhone1='" + proprietorPhone1 + '\'' +
				", proprietorIdNumber1='" + proprietorIdNumber1 + '\'' +
				", proprietorName2='" + proprietorName2 + '\'' +
				", proprietorPhone2='" + proprietorPhone2 + '\'' +
				", proprietorIdNumber2='" + proprietorIdNumber2 + '\'' +
				'}';
	}

	public Integer getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(Integer saleStatus) {
		this.saleStatus = saleStatus;
	}

	public Integer getLivingStatus() {
		return livingStatus;
	}

	public void setLivingStatus(Integer livingStatus) {
		this.livingStatus = livingStatus;
	}

	public Integer getLeaseStatus() {
		return leaseStatus;
	}

	public void setLeaseStatus(Integer leaseStatus) {
		this.leaseStatus = leaseStatus;
	}
}
