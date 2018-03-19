package com.cnfantasia.server.ms.groupBuildingRegister.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;
import com.cnfantasia.server.domainbase.groupBuildingRegister.service.GroupBuildingRegisterBaseService;

public class GroupBuildingRegisterService extends GroupBuildingRegisterBaseService implements IGroupBuildingRegisterService {
	private IUuidManager uuidManager;

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	@Override
	public int insertGroupBuildingRegisterBatch(List<GroupBuildingRegister> groupBuildingRegisterList) {
		List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building_register, groupBuildingRegisterList.size());
		for (int i = 0; i < groupBuildingRegisterList.size(); i++) {
			groupBuildingRegisterList.get(i).setId(idList.get(i));
		}
		return super.insertGroupBuildingRegisterBatch(groupBuildingRegisterList);
	}
}
