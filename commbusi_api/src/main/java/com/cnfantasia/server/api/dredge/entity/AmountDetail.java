package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;

public class AmountDetail {
	private long id;
	private int feeType;//费用类型
	String feeName;//费用名称
	BigDecimal feeAmount;//费用金额
	private int isIncludePlatformFee;//是否包含平台费
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getFeeType() {
		return feeType;
	}
	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public BigDecimal getFeeAmount() {
		return feeAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}
	public int getIsIncludePlatformFee() {
		return isIncludePlatformFee;
	}
	public void setIsIncludePlatformFee(int isIncludePlatformFee) {
		this.isIncludePlatformFee = isIncludePlatformFee;
	}
}
