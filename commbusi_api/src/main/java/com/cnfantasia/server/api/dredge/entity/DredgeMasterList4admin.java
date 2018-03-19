package com.cnfantasia.server.api.dredge.entity;

/**
 * 维修师傅信息，供管理员审核使用
 * @author wenfq 2015-12-2
 *
 */
public class DredgeMasterList4admin {
	long dwId;
	long huaId;
	String realName;
	String mobile;
	String idNumber;//身份证号
	String registTime;//注册时间
	int certificateStatus;//实名验证状态
	String auditDesc;//验证结果描述

	public long getDwId() {
		return dwId;
	}

	public void setDwId(long dwId) {
		this.dwId = dwId;
	}

	public long getHuaId() {
		return huaId;
	}

	public void setHuaId(long huaId) {
		this.huaId = huaId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public int getCertificateStatus() {
		return certificateStatus;
	}

	public void setCertificateStatus(int certificateStatus) {
		this.certificateStatus = certificateStatus;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}
}
