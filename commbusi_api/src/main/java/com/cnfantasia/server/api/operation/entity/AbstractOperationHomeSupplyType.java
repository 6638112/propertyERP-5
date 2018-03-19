package com.cnfantasia.server.api.operation.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;


public abstract class AbstractOperationHomeSupplyType extends OperationHomeSupplyType{
	private static final long serialVersionUID = 1L;
	
	public AbstractOperationHomeSupplyType() {
		super();
	}

	public AbstractOperationHomeSupplyType(OperationHomeSupplyType arg) {
		super(arg);
	}

	@Override
	public BigInteger getId() {
		return super.getId();
	}
	
	@Override
	public Integer getDataType() {
		return super.getDataType();
	}
	
	@Override
	public Integer getDataLevel() {
		return super.getDataLevel();
	}
	
	@Override
	public BigInteger getParentId() {
		return super.getParentId();
	}
	
	@Override
	public BigInteger getSupplyTypeId() {
		return super.getSupplyTypeId();
	}
	@Override
	public String getLinkUrl() {
		return super.getLinkUrl();
	}
	@Override
	public String getCode() {
		return super.getCode();
	}
	@Override
	public String getDesc() {
		return super.getDesc();
	}
	@Override
	public Integer getIsHot() {
		return super.getIsHot();
	}
	@Override
	public Long getOrder() {
		return super.getOrder();
	}
	@Override
	public String getLastUpdTime() {
		return super.getLastUpdTime();
	}
	@Override
	public Integer getHomePlace() {
		return super.getHomePlace();
	}
}
