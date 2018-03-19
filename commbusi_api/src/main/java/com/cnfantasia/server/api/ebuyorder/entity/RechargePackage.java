package com.cnfantasia.server.api.ebuyorder.entity;

import java.io.Serializable;

/**
 * 流量包套餐实体：
 * 
 * @author yewj
 */
public class RechargePackage implements Serializable {
	
	private static final long serialVersionUID = 1959045811826015327L;

	private String packageId;
	
	private Double salePrice;
	
	private Double price;
	
	private String packageName;
	
	private String operatorCode;
	
	private String activePeriod;
	
	private Integer isCombo;

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getActivePeriod() {
		return activePeriod;
	}

	public void setActivePeriod(String activePeriod) {
		this.activePeriod = activePeriod;
	}

	public Integer getIsCombo() {
		return isCombo;
	}

	public void setIsCombo(Integer isCombo) {
		this.isCombo = isCombo;
	}
	
}
