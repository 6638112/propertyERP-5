package com.cnfantasia.server.ms.propertyManagement.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.propertyManagement.dao.IPropertyManagementBaseDao;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.ms.propertyManagement.entity.PropertyManagementEntity;

public interface IPropertyManagementDao extends IPropertyManagementBaseDao {
	/**
	 * 根据id 查询(物业管理处)信息
	 * @param id
	 * @return PropertyManagementEntity
	 */
	public PropertyManagementEntity selectPropertyManagementById(BigInteger id);
	/**
	 * 根据id 查询(物业管理处)信息
	 * @param id
	 * @return PropertyManagementEntity
	 */
	public int deletePropertyManagementHasOmsUserByMgtId(Map<String, Object> paramMap);
	
	/**
	 * 根据条件查询(物业管理处)信息
	 * @param id
	 * @return PropertyManagementEntity
	 */
	public List<PropertyManagementEntity> selectPropertyManagementForList(Map<String, Object> paramMap);
	
	public int updPropertyManagement(PropertyManagement pm);
}
