package com.cnfantasia.server.api.ebuy.domain;

import java.io.Serializable;
import java.math.BigInteger;

public class BuyCar implements Serializable {
	
	private static final long serialVersionUID = 6267146090207648808L;

	private BigInteger productId;
	
	private BigInteger productSpecId;
	
	private Long num;

	public BigInteger getProductId() {
		return productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public BigInteger getProductSpecId() {
		return productSpecId;
	}

	public void setProductSpecId(BigInteger productSpecId) {
		this.productSpecId = productSpecId;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

}
