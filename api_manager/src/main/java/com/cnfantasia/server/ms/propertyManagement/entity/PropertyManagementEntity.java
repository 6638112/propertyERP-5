package com.cnfantasia.server.ms.propertyManagement.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;

public class PropertyManagementEntity extends PropertyManagement {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 管理处联系人信息
	 * */
	private BigInteger omsUserId;//用户ID
	private String userAccount;//用户账号
	private String password;//用户密码
	public BigInteger getOmsUserId() {
		return omsUserId;
	}
	public void setOmsUserId(BigInteger omsUserId) {
		this.omsUserId = omsUserId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 物业公司信息
	 * */
	private String companyName;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
