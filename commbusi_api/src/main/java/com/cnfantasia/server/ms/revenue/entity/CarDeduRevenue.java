package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;
import java.util.Date;

import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;

public class CarDeduRevenue extends RevenueSignalAmount {
	
	private static final long serialVersionUID = 2784696341551582441L;
	
	private Integer revenueStatus;
	
	private Date deduBeginDate;
	
	private Date deduEndDate;
	
	private BigInteger gbId;

	public Integer getRevenueStatus() {
		return revenueStatus;
	}

	public void setRevenueStatus(Integer revenueStatus) {
		this.revenueStatus = revenueStatus;
	}

	public Date getDeduBeginDate() {
		return deduBeginDate;
	}

	public void setDeduBeginDate(Date deduBeginDate) {
		this.deduBeginDate = deduBeginDate;
	}

	public Date getDeduEndDate() {
		return deduEndDate;
	}

	public void setDeduEndDate(Date deduEndDate) {
		this.deduEndDate = deduEndDate;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	
}
