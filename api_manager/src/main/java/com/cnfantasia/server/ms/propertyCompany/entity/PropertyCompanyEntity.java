package com.cnfantasia.server.ms.propertyCompany.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;

public class PropertyCompanyEntity extends PropertyCompany {
	
	/**最后登录时间*/
	private String lastLoginTime;
	
	/**是否被代理人锁定*/
	private int isLocked;
	
	/**管理处id*/
	private BigInteger propertyManagementId;
	/**管理处信息更新时间*/
	private String pmSys0UpdTime;
	/**管理处审核状态*/
	private int pmIsAudited;
	
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public int getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public BigInteger getPropertyManagementId() {
		return propertyManagementId;
	}

	public void setPropertyManagementId(BigInteger propertyManagementId) {
		this.propertyManagementId = propertyManagementId;
	}

	public String getPmSys0UpdTime() {
		return pmSys0UpdTime;
	}

	public void setPmSys0UpdTime(String pmSys0UpdTime) {
		this.pmSys0UpdTime = pmSys0UpdTime;
	}

	public int getPmIsAudited() {
		return pmIsAudited;
	}

	public void setPmIsAudited(int pmIsAudited) {
		this.pmIsAudited = pmIsAudited;
	}
}
