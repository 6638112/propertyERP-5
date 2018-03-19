package com.cnfantasia.server.ms.propertyRepair.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;

/**
 * 报修类型
 * 
 * @author wenfq 2015-04-06
 * 
 */
public class PropertyRepairTypeEntity extends PropertyRepairType {
	String pmName;//管理处
	BigInteger pmId;//管理处理ID
	
	private BigInteger baseTypeId;//关联解放区预设维修类型id
	
	private String baseTypeName; // 关联解放区预设维修类型name

	private String desc;// 价格描述

	private String priceDesc;//价格说明  参考价格

	private BigInteger priceCfgId;//维修价格配置id

	public BigInteger getBaseTypeId() {
		return baseTypeId;
	}

	public void setBaseTypeId(BigInteger baseTypeId) {
		this.baseTypeId = baseTypeId;
	}

	public String getBaseTypeName() {
		return baseTypeName;
	}

	public void setBaseTypeName(String baseTypeName) {
		this.baseTypeName = baseTypeName;
	}

	public BigInteger getPmId() {
		return pmId;
	}

	public void setPmId(BigInteger pmId) {
		this.pmId = pmId;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPriceDesc() {
		return priceDesc;
	}

	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}

	public BigInteger getPriceCfgId() {
		return priceCfgId;
	}

	public void setPriceCfgId(BigInteger priceCfgId) {
		this.priceCfgId = priceCfgId;
	}
}
