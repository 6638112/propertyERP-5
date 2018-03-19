package com.cnfantasia.server.ms.propertyManagement.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyManagement.service.IPropertyManagementBaseService;
import com.cnfantasia.server.ms.propertyManagement.entity.PropertyManagementEntity;

public interface IPropertyManagementService extends IPropertyManagementBaseService {

	/**
	 * 删除方法
	 * @param id
	 * */
	public int deleteManagement(BigInteger id);
	
	/**
	 * 根据id 查询(物业管理处)信息
	 * @param id
	 */
	public PropertyManagementEntity selectPropertyManagementById(BigInteger id);
	
	/**
	 * 根据条件查询(物业管理处)信息
	 * @param id
	 * @return PropertyManagementEntity
	 */
	public List<PropertyManagementEntity> selectPropertyManagementForList(Map<String, Object> paramMap);

	public int saveManagement(PropertyManagement management, String omsUserId, String userAccount, String password);
	
}
