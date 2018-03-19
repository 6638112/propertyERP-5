package com.cnfantasia.server.api.access.entity;

/**
 * 蜜蜂停车记录
 * 
 * @author liyulin
 * @version 1.0 2017年8月16日 上午11:27:44
 */
public class MFParkingRecord {
	private String carNum;
	private String inDate;
	private String outDate;
	private Integer carType;
	private long fee;

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

}
