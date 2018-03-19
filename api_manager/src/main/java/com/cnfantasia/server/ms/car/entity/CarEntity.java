package com.cnfantasia.server.ms.car.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 车辆管理信息展示实体bean
 * 
 * @author liyulin
 * @version 1.0 2016年11月23日 下午5:22:11
 */
public class CarEntity {

	private BigInteger carId;
	/** 停车场 */
	private String park;
	/** 小区 */
	private String xqName;
	/** 楼栋 */
	private String buildingName;
	/** 房间号 */
	private String roomName;
	/** 车牌 */
	private String carNum;
	/** 每月费用 */
	private BigDecimal fee;
	/** 有效期 */
	private String expireDate;
	private String from;

	public BigInteger getCarId() {
		return carId;
	}

	public void setCarId(BigInteger carId) {
		this.carId = carId;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getXqName() {
		return xqName;
	}

	public void setXqName(String xqName) {
		this.xqName = xqName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
