package com.cnfantasia.server.carYhsMsg.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 临停车缴费消息发送参数
 * 
 * @author liyulin
 * @version 1.0 2016年8月16日 下午3:32:30
 */
public class CarYhsMsgSendParam {

	/** 停车小区 */
	private BigInteger gbId;
	/** 车牌 */
	private String carNum;
	/** 停车费（单位元） */
	private BigDecimal fee;
	/** 支付时间 */
	private String payTime;

	public CarYhsMsgSendParam() {
		super();
	}

	public CarYhsMsgSendParam(BigInteger gbId, String carNum, BigDecimal fee,
			String payTime) {
		super();
		this.gbId = gbId;
		this.carNum = carNum;
		this.fee = fee;
		this.payTime = payTime;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	/**
	 * 单位：元
	 * @return
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * 单位：元
	 * @param fee
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	@Override
	public String toString() {
		return "CarYhsMsgSendParam [gbId=" + gbId + ", carNum=" + carNum
				+ ", fee=" + fee + ", payTime=" + payTime + "]";
	}

}
