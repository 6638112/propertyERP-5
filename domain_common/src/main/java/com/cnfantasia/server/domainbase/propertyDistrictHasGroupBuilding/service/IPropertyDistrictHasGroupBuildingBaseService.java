package com.cnfantasia.server.domainbase.propertyDistrictHasGroupBuilding.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyDistrictHasGroupBuilding.entity.PropertyDistrictHasGroupBuilding;

/**
 * 描述:(物业片区与小区关联) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyDistrictHasGroupBuildingBaseService {
	
	/**
	 * 根据条件查询(物业片区与小区关联)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyDistrictHasGroupBuilding> getPropertyDistrictHasGroupBuildingByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业片区与小区关联)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyDistrictHasGroupBuilding> getPropertyDistrictHasGroupBuildingByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业片区与小区关联)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyDistrictHasGroupBuilding> getPropertyDistrictHasGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业片区与小区关联)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyDistrictHasGroupBuilding> getPropertyDistrictHasGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业片区与小区关联)信息
	 * @param id
	 * @return
	 */
	public PropertyDistrictHasGroupBuilding getPropertyDistrictHasGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业片区与小区关联)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyDistrictHasGroupBuildingCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业片区与小区关联)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyDistrictHasGroupBuildingCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业片区与小区关联)新增一条记录
	 * @param propertyDistrictHasGroupBuilding
	 * @return
	 */
	public int insertPropertyDistrictHasGroupBuilding(PropertyDistrictHasGroupBuilding propertyDistrictHasGroupBuilding);
	/**
	 * 批量新增(物业片区与小区关联)
	 * @param propertyDistrictHasGroupBuildingList
	 * @return
	 */
	public int insertPropertyDistrictHasGroupBuildingBatch(List<PropertyDistrictHasGroupBuilding> propertyDistrictHasGroupBuildingList);
	/**
	 * 更新(物业片区与小区关联)信息
	 * @param propertyDistrictHasGroupBuilding
	 * @return
	 */
	public int updatePropertyDistrictHasGroupBuilding(PropertyDistrictHasGroupBuilding propertyDistrictHasGroupBuilding);
	/**
	 * 批量更新(物业片区与小区关联)信息
	 * @param propertyDistrictHasGroupBuildingList
	 * @return
	 */
	public int updatePropertyDistrictHasGroupBuildingBatch(List<PropertyDistrictHasGroupBuilding> propertyDistrictHasGroupBuildingList);
	/**
	 * 根据序列号删除(物业片区与小区关联)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyDistrictHasGroupBuildingLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业片区与小区关联)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyDistrictHasGroupBuildingLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业片区与小区关联)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyDistrictHasGroupBuilding(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业片区与小区关联)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyDistrictHasGroupBuildingBatch(List<java.math.BigInteger> idList);
	
}
