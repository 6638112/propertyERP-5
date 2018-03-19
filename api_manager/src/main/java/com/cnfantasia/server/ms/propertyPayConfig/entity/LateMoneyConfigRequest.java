package com.cnfantasia.server.ms.propertyPayConfig.entity;

import java.math.BigInteger;

/**
 * 保存滞纳金配置请求参数
 * 
 * @author liyulin
 * @version 1.0 2016年10月24日 下午4:59:14
 */
public class LateMoneyConfigRequest {
	private BigInteger gbId;
	private String alterPeriodFeeItems;
	private Integer latefeeStatus;
	private double latefeeRate;
	private String calExp;
	private String billName;
	private String periodMonths;

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public String getAlterPeriodFeeItems() {
		return alterPeriodFeeItems;
	}

	public void setAlterPeriodFeeItems(String alterPeriodFeeItems) {
		this.alterPeriodFeeItems = alterPeriodFeeItems;
	}

	public Integer getLatefeeStatus() {
		return latefeeStatus;
	}

	public void setLatefeeStatus(Integer latefeeStatus) {
		this.latefeeStatus = latefeeStatus;
	}

	public double getLatefeeRate() {
		return latefeeRate;
	}

	public void setLatefeeRate(double latefeeRate) {
		this.latefeeRate = latefeeRate;
	}

	public String getCalExp() {
		return calExp;
	}

	public void setCalExp(String calExp) {
		this.calExp = calExp;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getPeriodMonths() {
		return periodMonths;
	}

	public void setPeriodMonths(String periodMonths) {
		this.periodMonths = periodMonths;
	}
}
