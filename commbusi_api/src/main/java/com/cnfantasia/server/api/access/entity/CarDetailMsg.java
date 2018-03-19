package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

public class CarDetailMsg {
	private BigInteger carNumId;//车牌表id
	private String carNum;//车牌号 
	private int status;//是否月卡车
	private BigInteger remainingDays;//剩余天数
	private double surplusMoney;//余额
	public BigInteger getCarNumId() {
		return carNumId;
	}
	public void setCarNumId(BigInteger carNumId) {
		this.carNumId = carNumId;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public BigInteger getRemainingDays() {
		return remainingDays;
	}
	public void setRemainingDays(BigInteger remainingDays) {
		this.remainingDays = remainingDays;
	}
	public double getSurplusMoney() {
		return surplusMoney;
	}
	public void setSurplusMoney(double surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

}
