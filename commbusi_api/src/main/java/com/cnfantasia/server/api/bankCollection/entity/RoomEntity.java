package com.cnfantasia.server.api.bankCollection.entity;

import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;

public class RoomEntity extends RealRoom{
	private static final long serialVersionUID = 1L;
	
	private String groupbuildingName;//小区
	private String buildingName;//楼栋
	public String getGroupbuildingName() {
		return groupbuildingName;
	}

	public void setGroupbuildingName(String groupbuildingName) {
		this.groupbuildingName = groupbuildingName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	private PropertyProprietor proprietor;

	public PropertyProprietor getProprietor() {
		return proprietor;
	}

	public void setProprietor(PropertyProprietor proprietor) {
		this.proprietor = proprietor;
	}
	
}
