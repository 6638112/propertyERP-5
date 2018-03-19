package com.cnfantasia.server.ms.groupBuilding.entity;

import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;

public class GroupBuildingSimpleViewEntity extends GroupBuildingSimpleEntity{
	private static final long serialVersionUID = 1L;
	//所属物业公司
	private PropertyCompany propertyCompany;

	public PropertyCompany getPropertyCompany() {
		return propertyCompany;
	}

	public void setPropertyCompany(PropertyCompany propertyCompany) {
		this.propertyCompany = propertyCompany;
	}
	
	private String isService = "0";

	public String getIsService() {
		return isService;
	}

	public void setIsService(String isService) {
		this.isService = isService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
