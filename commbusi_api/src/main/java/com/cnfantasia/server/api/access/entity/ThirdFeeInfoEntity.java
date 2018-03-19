package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

public class ThirdFeeInfoEntity {
	/** 停车场对应的小区id */
	private BigInteger gbId;
	/** 进场时间 */
	private long inDate;

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public long getInDate() {
		return inDate;
	}

	public void setInDate(long inDate) {
		this.inDate = inDate;
	}

	@Override
	public String toString() {
		return "ThirdFeeInfoEntity [gbId=" + gbId + ", inDate=" + inDate + "]";
	}

}
