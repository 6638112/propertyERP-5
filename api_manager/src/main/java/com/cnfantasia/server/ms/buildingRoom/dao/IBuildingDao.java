package com.cnfantasia.server.ms.buildingRoom.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.building.dao.IBuildingBaseDao;
import com.cnfantasia.server.ms.buildingRoom.entity.BuildingEntity;

public interface IBuildingDao extends IBuildingBaseDao {
	public int queryBuildingForCount(Map<String,Object> paramMap);
	
	public List<BuildingEntity> queryBuildingForList(Map<String, Object> paramMap);
	
	public BuildingEntity queryBuildingById(String id);
}
