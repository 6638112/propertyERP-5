package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

/**
 * 线下缴费记录同步参数
 * 
 * @author liyulin
 * @version 1.0 2017年8月21日 上午11:38:26
 */
public class SynCarPayLogParam {

	/** 车牌 */
	private String carNum;
	/** 小区id */
	private BigInteger gbId;
	/** 车辆类型 */
	private Integer jfqCarType;
	/** 缴费月数 */
	private long payNum;
	/** 月卡每月费用（单位：分） */
	private long perMonthFee;
	/** 缴费费用（单位：分） */
	private long fee;
	/** 应收费用（单位：分） */
	private long receivableFee;
	/** 有效开始时间 */
	private String startTime;
	/** 有效截止时间 */
	private String endTime;
	/** 支付时间 */
	private String payTime;
	/** 是否为月卡缴费 */
	private boolean isMonthCar;

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public Integer getJfqCarType() {
		return jfqCarType;
	}

	public void setJfqCarType(Integer jfqCarType) {
		this.jfqCarType = jfqCarType;
	}

	public long getPayNum() {
		return payNum;
	}

	public void setPayNum(long payNum) {
		this.payNum = payNum;
	}

	public long getPerMonthFee() {
		return perMonthFee;
	}

	public void setPerMonthFee(long perMonthFee) {
		this.perMonthFee = perMonthFee;
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	public long getReceivableFee() {
		return receivableFee;
	}

	public void setReceivableFee(long receivableFee) {
		this.receivableFee = receivableFee;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public boolean isMonthCar() {
		return isMonthCar;
	}

	public void setMonthCar(boolean isMonthCar) {
		this.isMonthCar = isMonthCar;
	}

}
