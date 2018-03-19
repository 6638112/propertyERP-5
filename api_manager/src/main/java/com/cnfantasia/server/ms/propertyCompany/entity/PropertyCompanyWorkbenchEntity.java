package com.cnfantasia.server.ms.propertyCompany.entity;

import java.util.List;

import com.cnfantasia.server.ms.propertyManagement.entity.PropertyManagementEntity;


public class PropertyCompanyWorkbenchEntity extends PropertyCompanyEntity {
	
	private static final long serialVersionUID = 1L;
	
	private List<PropertyManagementEntity> managementList;

	public List<PropertyManagementEntity> getManagementList() {
		return managementList;
	}

	public void setManagementList(List<PropertyManagementEntity> managementList) {
		this.managementList = managementList;
	}
	
}
