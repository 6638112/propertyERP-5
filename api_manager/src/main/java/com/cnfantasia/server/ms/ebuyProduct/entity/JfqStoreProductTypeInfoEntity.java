package com.cnfantasia.server.ms.ebuyProduct.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JfqStoreProductTypeInfoEntity {
	private BigInteger id;
	private String name;
	private BigInteger typeId;
	private BigDecimal sellPriceRate;

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

	public BigInteger getTypeId() {
		return typeId;
	}

	public void setTypeId(BigInteger typeId) {
		this.typeId = typeId;
	}

	public BigDecimal getSellPriceRate() {
		return sellPriceRate;
	}

	public void setSellPriceRate(BigDecimal sellPriceRate) {
		this.sellPriceRate = sellPriceRate;
	}

}
