package com.cnfantasia.server.ms.groupBuilding.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.GroupBuildingBaseService;

public class GroupBuildingService extends GroupBuildingBaseService implements IGroupBuildingService {
	private IUuidManager uuidManager;

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	@Override
	public int insertGroupBuildingBatch(List<GroupBuilding> groupBuildingList) {
		List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building, groupBuildingList.size());
		for (int i = 0; i < idList.size(); i++) {
			groupBuildingList.get(i).setId(idList.get(i));
		}
		return super.insertGroupBuildingBatch(groupBuildingList);
	}
}
