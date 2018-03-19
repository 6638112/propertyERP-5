package com.cnfantasia.server.domainbase.propertyManagement.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;

/**
 * 描述:(物业管理) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyManagementBaseService {
	
	/**
	 * 根据条件查询(物业管理)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyManagement> getPropertyManagementByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业管理)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyManagement> getPropertyManagementByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业管理)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyManagement> getPropertyManagementByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业管理)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyManagement> getPropertyManagementByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业管理)信息
	 * @param id
	 * @return
	 */
	public PropertyManagement getPropertyManagementBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业管理)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyManagementCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业管理)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyManagementCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业管理)新增一条记录
	 * @param propertyManagement
	 * @return
	 */
	public int insertPropertyManagement(PropertyManagement propertyManagement);
	/**
	 * 批量新增(物业管理)
	 * @param propertyManagementList
	 * @return
	 */
	public int insertPropertyManagementBatch(List<PropertyManagement> propertyManagementList);
	/**
	 * 更新(物业管理)信息
	 * @param propertyManagement
	 * @return
	 */
	public int updatePropertyManagement(PropertyManagement propertyManagement);
	/**
	 * 批量更新(物业管理)信息
	 * @param propertyManagementList
	 * @return
	 */
	public int updatePropertyManagementBatch(List<PropertyManagement> propertyManagementList);
	/**
	 * 根据序列号删除(物业管理)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyManagementLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业管理)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyManagementLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业管理)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyManagement(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业管理)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyManagementBatch(List<java.math.BigInteger> idList);
	
}
