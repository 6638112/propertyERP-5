package com.cnfantasia.server.api.room.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.building.entity.Building;
/**
 * 描述:(建筑单元) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class BuildingEntity extends Building{
	private static final long serialVersionUID = 1L;
	/**所属小区*/
	private GroupBuildingEntity groupBuildingEntity;
	public GroupBuildingEntity getGroupBuildingEntity() {
		return groupBuildingEntity;
	}
	public void setGroupBuildingEntity(GroupBuildingEntity groupBuildingEntity) {
		this.groupBuildingEntity = groupBuildingEntity;
	}
	@Override
	public BigInteger gettGroupBuildingFId() {
		if(groupBuildingEntity==null){return null;}
		return groupBuildingEntity.getId();
	}
	@Override
	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {
		if(groupBuildingEntity==null){groupBuildingEntity = new GroupBuildingEntity();}
		groupBuildingEntity.setId(tGroupBuildingFId);
	}
	
}
