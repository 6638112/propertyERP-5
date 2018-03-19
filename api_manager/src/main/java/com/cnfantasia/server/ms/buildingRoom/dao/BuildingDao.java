package com.cnfantasia.server.ms.buildingRoom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domainbase.building.dao.BuildingBaseDao;
import com.cnfantasia.server.ms.buildingRoom.entity.BuildingEntity;

public class BuildingDao extends BuildingBaseDao implements IBuildingDao {
	@Override
	public int queryBuildingForCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "building.select_building_forList", paramMap);
	}

	@Override
	public List<BuildingEntity> queryBuildingForList(Map<String, Object> paramMap) {
		return sqlSession.selectList("building.select_building_forList", paramMap);
	}

	@Override
	public BuildingEntity queryBuildingById(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return sqlSession.selectOne("building.select_building_byId", id);
	}

}
