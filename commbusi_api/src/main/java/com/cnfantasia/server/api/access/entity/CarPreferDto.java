package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

/**
 * 随机立减存储过程返回的值
 * 
 * @author liyulin
 * @version 1.0 2016年8月24日 下午1:38:46
 */
public class CarPreferDto {
	
	/** 随机立减表f_id */
	private BigInteger id;
	/** 随机立减值（单位：分） */
	private BigInteger amount;

	public CarPreferDto() {
		super();
	}

	public CarPreferDto(BigInteger id, BigInteger amount) {
		super();
		this.id = id;
		this.amount = amount;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

}
