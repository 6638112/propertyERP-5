package com.cnfantasia.server.api.dredge.entity;

/**
 * 抢单成功后的短信info
 * 
 * @author wenfq 2015-08-24
 * 
 */
public class SMSInfo {
	/**
	 * 预约类型
	 */
	String dredgeTypeName;
	/**
	 * 师傅名称
	 */
	String masterName;
	/**
	 * 师傅电话
	 */
	String masterTel;
	/**
	 * 预约者的手机号
	 */
	String dredgeTel;

	public String getDredgeTypeName() {
		return dredgeTypeName;
	}

	public void setDredgeTypeName(String dredgeTypeName) {
		this.dredgeTypeName = dredgeTypeName;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getMasterTel() {
		return masterTel;
	}

	public void setMasterTel(String masterTel) {
		this.masterTel = masterTel;
	}

	public String getDredgeTel() {
		return dredgeTel;
	}

	public void setDredgeTel(String dredgeTel) {
		this.dredgeTel = dredgeTel;
	}

}
