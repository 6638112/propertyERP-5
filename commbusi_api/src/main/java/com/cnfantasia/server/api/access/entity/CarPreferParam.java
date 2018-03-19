package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

/**
 * “随机立减”参数
 * 
 * @author liyulin
 * @version 1.0 2016年8月24日 上午9:54:10
 */
public class CarPreferParam {

	/** 车牌id */
	private BigInteger carId;
	/** 用户id */
	private BigInteger userId;
	/** 小区id */
	private BigInteger gbId;
	/** 缴费类型： */
	private Integer payType;
	/** 缴费月数 */
	private int payMonth;
	/** 缴费金额（单位：分） */
	private BigInteger amount;
	/** 临停车缴费优惠开关=={"0":"未开启","1":"平台优惠开启","2":"物业优惠开启"} */
	private Integer carTmpOpenStatus;

	public CarPreferParam() {
		super();
	}

	public CarPreferParam(BigInteger carId, BigInteger userId, BigInteger gbId,
			Integer payType, int payMonth, BigInteger amount,
			Integer carTmpOpenStatus) {
		super();
		this.carId = carId;
		this.userId = userId;
		this.gbId = gbId;
		this.payType = payType;
		this.payMonth = payMonth;
		this.amount = amount;
		this.carTmpOpenStatus = carTmpOpenStatus;
	}

	public Integer getCarTmpOpenStatus() {
		return carTmpOpenStatus;
	}

	public void setCarTmpOpenStatus(Integer carTmpOpenStatus) {
		this.carTmpOpenStatus = carTmpOpenStatus;
	}

	public BigInteger getCarId() {
		return carId;
	}

	public void setCarId(BigInteger carId) {
		this.carId = carId;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public int getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(int payMonth) {
		this.payMonth = payMonth;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

}
