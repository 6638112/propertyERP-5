package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

/**
 * 车牌前缀相关信息
 * 
 * @author liyulin
 * @version 1.0 2017年7月24日 下午4:00:36
 */
public class CarNumPrefixEntity {

	private BigInteger gbId;
	private String carNum;

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

}
