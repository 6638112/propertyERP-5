package com.cnfantasia.server.api.commonBusiness.entity;

import java.math.BigInteger;

/**
 * 运营配置范围
 * @author wenfq 2017-06-08
 *
 */
public class OperateConfigRange {
	BigInteger acId;
	String acName;//城市、	全国借用这个字段，同时abName和gbName为空
	BigInteger abId;
	String abName;//行政区
	BigInteger gbId;
	String gbName;//小区
	
	public BigInteger getAcId() {
		return acId;
	}
	public void setAcId(BigInteger acId) {
		this.acId = acId;
	}
	public BigInteger getAbId() {
		return abId;
	}
	public void setAbId(BigInteger abId) {
		this.abId = abId;
	}
	public BigInteger getGbId() {
		return gbId;
	}
	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	
	public String getAcName() {
		return acName;
	}
	public void setAcName(String acName) {
		this.acName = acName;
	}
	public String getAbName() {
		return abName;
	}
	public void setAbName(String abName) {
		this.abName = abName;
	}
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}	
}
