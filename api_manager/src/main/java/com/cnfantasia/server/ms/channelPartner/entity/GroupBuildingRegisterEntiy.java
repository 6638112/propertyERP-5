package com.cnfantasia.server.ms.channelPartner.entity;

import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;

public class GroupBuildingRegisterEntiy extends GroupBuildingRegister {
	String apName;//省
	String acName;//市
	String abName;//区
	int signStatus;//签约状态

	public String getApName() {
		return apName;
	}

	public void setApName(String apName) {
		this.apName = apName;
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

	public int getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(int signStatus) {
		this.signStatus = signStatus;
	}

}
