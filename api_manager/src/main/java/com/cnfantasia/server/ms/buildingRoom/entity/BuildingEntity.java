package com.cnfantasia.server.ms.buildingRoom.entity;

import com.cnfantasia.server.domainbase.building.entity.Building;

public class BuildingEntity extends Building{
	private static final long serialVersionUID = 1L;
	
	private String managementName;//管理处
	private String groupbuildingName;//小区

	public String getManagementName() {
		return managementName;
	}

	public void setManagementName(String managementName) {
		this.managementName = managementName;
	}

	public String getGroupbuildingName() {
		return groupbuildingName;
	}

	public void setGroupbuildingName(String groupbuildingName) {
		this.groupbuildingName = groupbuildingName;
	}
}
