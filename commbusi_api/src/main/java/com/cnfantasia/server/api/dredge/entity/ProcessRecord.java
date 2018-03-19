package com.cnfantasia.server.api.dredge.entity;

import java.math.BigInteger;
import java.util.List;

public class ProcessRecord {
	private BigInteger id;
	private long dredgeBillId;//维修单ID
	private String prDesc;//记录描述
	private long prAddTime;//记录的时间
	private String prAddTimeStr;//记录的时间String
	private List<String> picList;//记录的图片集

	public String getPrAddTimeStr() {
		return prAddTimeStr;
	}
	public void setPrAddTimeStr(String prAddTimeStr) {
		this.prAddTimeStr = prAddTimeStr;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public long getDredgeBillId() {
		return dredgeBillId;
	}
	public void setDredgeBillId(long dredgeBillId) {
		this.dredgeBillId = dredgeBillId;
	}
	public String getPrDesc() {
		return prDesc;
	}
	public void setPrDesc(String prDesc) {
		this.prDesc = prDesc;
	}
	public long getPrAddTime() {
		return prAddTime;
	}
	public void setPrAddTime(long prAddTime) {
		this.prAddTime = prAddTime;
	}
	public List<String> getPicList() {
		return picList;
	}
	public void setPicList(List<String> picList) {
		this.picList = picList;
	}
}
