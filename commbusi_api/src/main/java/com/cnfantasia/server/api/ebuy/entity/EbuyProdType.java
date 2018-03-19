package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;

public class EbuyProdType implements Serializable {

	private static final long serialVersionUID = -2425258607288158588L;
	
	private Long id;
	
	private String typeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
