package com.cnfantasia.server.ms.car.entity;

import java.math.BigInteger;

/**
 * 自动搜索Entity
 * 
 * @author liyulin
 * @version 1.0 2016年11月24日 上午11:15:35
 */
public class AutoCompleteEntity {
	private BigInteger id;
	private String name;
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
