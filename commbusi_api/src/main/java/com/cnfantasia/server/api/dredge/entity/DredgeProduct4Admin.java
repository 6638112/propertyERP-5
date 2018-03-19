package com.cnfantasia.server.api.dredge.entity;

import java.math.BigInteger;

public class DredgeProduct4Admin {
	BigInteger dpId;//服务商品id
	String prdtName;//服务商品名称
	String updTime;
	String fullTypeName;//类型全称,包含三级分类名称
	String ssName;//服务商名称
	String payTypeName;//付款方式："1":"服务前付款","2":"服务后付款"
	int dpStatus;//上架状态，1上架，2下架
	public int getDpStatus() {
		return dpStatus;
	}
	public void setDpStatus(int dpStatus) {
		this.dpStatus = dpStatus;
	}
	public BigInteger getDpId() {
		return dpId;
	}
	public void setDpId(BigInteger dpId) {
		this.dpId = dpId;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	public String getUpdTime() {
		return updTime;
	}
	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}
	public String getFullTypeName() {
		return fullTypeName;
	}
	public void setFullTypeName(String fullTypeName) {
		this.fullTypeName = fullTypeName;
	}
	public String getSsName() {
		return ssName;
	}
	public void setSsName(String ssName) {
		this.ssName = ssName;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	
	
}
