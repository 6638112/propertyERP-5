package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;

public class CarRevenue extends RevenueSignalAmount {
	
	private static final long serialVersionUID = 2784696341551582441L;
	
	private Integer revenueStatus;
	
	private BigInteger gbId;

	public Integer getRevenueStatus() {
		return revenueStatus;
	}

	public void setRevenueStatus(Integer revenueStatus) {
		this.revenueStatus = revenueStatus;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	
}
