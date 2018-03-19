package com.cnfantasia.server.ms.omsPermiRole.entity;

import java.io.Serializable;
import java.util.List;

import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.propertyDistrict.entity.PropertyDistrict;

public class DistrictManager extends PropertyDistrict implements Serializable {

	private static final long serialVersionUID = 6340652737853765132L;

	private List<OmsUser> omsUserList;
	
	private List<GroupBuilding> groupBuildingList;

	public List<OmsUser> getOmsUserList() {
		return omsUserList;
	}

	public void setOmsUserList(List<OmsUser> omsUserList) {
		this.omsUserList = omsUserList;
	}

	public List<GroupBuilding> getGroupBuildingList() {
		return groupBuildingList;
	}

	public void setGroupBuildingList(List<GroupBuilding> groupBuildingList) {
		this.groupBuildingList = groupBuildingList;
	}
	
	
}
