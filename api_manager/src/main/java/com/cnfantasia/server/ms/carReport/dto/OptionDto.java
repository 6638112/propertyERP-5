package com.cnfantasia.server.ms.carReport.dto;

import java.math.BigInteger;

public class OptionDto {

	private BigInteger id;
	private String name;

	public OptionDto() {
		super();
	}

	public OptionDto(BigInteger id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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
