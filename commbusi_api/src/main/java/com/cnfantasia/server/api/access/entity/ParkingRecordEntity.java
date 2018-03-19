package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;
import java.util.Date;

public class ParkingRecordEntity {
	
	private BigInteger parkingId;
	private Date parkingTime;
	private BigInteger gbId;
	private String gbName;
	private String type;

	public ParkingRecordEntity() {
		super();
	}

	public ParkingRecordEntity(BigInteger parkingId, Date parkingTime,
			BigInteger gbId, String gbName, String type) {
		super();
		this.parkingId = parkingId;
		this.parkingTime = parkingTime;
		this.gbId = gbId;
		this.gbName = gbName;
		this.type = type;
	}

	public BigInteger getParkingId() {
		return parkingId;
	}

	public void setParkingId(BigInteger parkingId) {
		this.parkingId = parkingId;
	}

	public Date getParkingTime() {
		return parkingTime;
	}

	public void setParkingTime(Date parkingTime) {
		this.parkingTime = parkingTime;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
