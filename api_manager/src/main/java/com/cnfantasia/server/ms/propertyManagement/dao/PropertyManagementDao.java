package com.cnfantasia.server.ms.propertyManagement.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.propertyManagement.dao.PropertyManagementBaseDao;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.ms.propertyManagement.entity.PropertyManagementEntity;

public class PropertyManagementDao extends PropertyManagementBaseDao implements IPropertyManagementDao {

	@Override
	public PropertyManagementEntity selectPropertyManagementById(BigInteger id) {
		return sqlSession.selectOne("propertyManagement.select_propertyManagement_byId", id);
	}

	@Override
	public int deletePropertyManagementHasOmsUserByMgtId(Map<String, Object> paramMap) {
		return sqlSession.update("propertyManagement.del_PM_Has_Ou_byMgtId", paramMap);
	}

	@Override
	public List<PropertyManagementEntity> selectPropertyManagementForList(Map<String, Object> paramMap) {
		return sqlSession.selectList("propertyManagement.select_propertyManagement_forList", paramMap);
	}

	@Override
	public int updPropertyManagement(PropertyManagement pm) {
		return sqlSession.update("propertyManagement.update_propertyManagement", pm);
	}
	
}
