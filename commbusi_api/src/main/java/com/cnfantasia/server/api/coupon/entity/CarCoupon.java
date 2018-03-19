package com.cnfantasia.server.api.coupon.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.coupon.entity.Coupon;

/**
 * 停车券
 * 
 * @author liyulin
 * @version 1.0 2017年7月28日 下午4:12:52
 */
public class CarCoupon extends Coupon {

	private BigInteger ucId;

	public BigInteger getUcId() {
		return ucId;
	}

	public void setUcId(BigInteger ucId) {
		this.ucId = ucId;
	}

}
