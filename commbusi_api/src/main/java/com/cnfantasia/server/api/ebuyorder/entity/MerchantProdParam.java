package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;

/**
 * 类说明：
 * 
 * @author yewj
 */
public class MerchantProdParam implements Serializable {
	
	private static final long serialVersionUID = 5634700060277735266L;

	private Long id;
	
	private String propName;
	
	private String propValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}
	
}
