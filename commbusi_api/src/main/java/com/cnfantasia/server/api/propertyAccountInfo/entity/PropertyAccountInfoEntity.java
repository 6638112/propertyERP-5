package com.cnfantasia.server.api.propertyAccountInfo.entity;

import com.cnfantasia.server.domainbase.propertyAccountInfo.entity.PropertyAccountInfo;

public class PropertyAccountInfoEntity extends PropertyAccountInfo {

	private String oldUpdateTime;

	public PropertyAccountInfoEntity() {
		super();
	}

	public PropertyAccountInfoEntity(PropertyAccountInfo arg) {
		super(arg);
	}

	public String getOldUpdateTime() {
		return oldUpdateTime;
	}

	public void setOldUpdateTime(String oldUpdateTime) {
		this.oldUpdateTime = oldUpdateTime;
	}

}
