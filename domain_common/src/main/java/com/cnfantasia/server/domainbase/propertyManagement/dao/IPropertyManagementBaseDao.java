package com.cnfantasia.server.domainbase.propertyManagement.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;

/**
 * 描述:(物业管理) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyManagementBaseDao {
	/**
	 * 根据条件查询(物业管理)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyManagement> selectPropertyManagementByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业管理)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyManagement> selectPropertyManagementByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业管理)信息
	 * @param id
	 * @return
	 */
	public PropertyManagement selectPropertyManagementBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业管理)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyManagementCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业管理)新增一条记录
	 * @param propertyManagement
	 * @return
	 */
	public int insertPropertyManagement(PropertyManagement propertyManagement);
	
	/**
	 * 批量新增(物业管理)信息
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
	 * 根据Id 批量删除(物业管理)信息_逻辑删除
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
