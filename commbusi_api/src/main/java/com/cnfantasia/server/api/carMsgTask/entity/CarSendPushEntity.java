package com.cnfantasia.server.api.carMsgTask.entity;

import java.math.BigInteger;

public class CarSendPushEntity implements Comparable{
	private BigInteger defaultRoomId;
	private BigInteger userId;
	private BigInteger carId;
	/** 车牌 */
	private String carNum;
	/** 剩余天数 */
	private String expireDate;
	private String fee;
	private String status;
	/** 停车场 */
	private String park;

	public BigInteger getDefaultRoomId() {
		return defaultRoomId;
	}

	public void setDefaultRoomId(BigInteger defaultRoomId) {
		this.defaultRoomId = defaultRoomId;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getCarId() {
		return carId;
	}

	public void setCarId(BigInteger carId) {
		this.carId = carId;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	@Override
	public int compareTo(Object o) {
		CarSendPushEntity entity = (CarSendPushEntity) o;
		return this.userId.compareTo(entity.getUserId());
	}
}
