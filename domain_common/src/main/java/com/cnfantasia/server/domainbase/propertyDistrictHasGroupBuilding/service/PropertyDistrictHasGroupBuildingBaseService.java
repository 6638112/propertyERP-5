package com.cnfantasia.server.domainbase.propertyDistrictHasGroupBuilding.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyDistrictHasGroupBuilding.dao.IPropertyDistrictHasGroupBuildingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyDistrictHasGroupBuilding.entity.PropertyDistrictHasGroupBuilding;

/**
 * 描述:(物业片区与小区关联) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyDistrictHasGroupBuildingBaseService implements IPropertyDistrictHasGroupBuildingBaseService{
	
	private IPropertyDistrictHasGroupBuildingBaseDao propertyDistrictHasGroupBuildingBaseDao;
	public void setPropertyDistrictHasGroupBuildingBaseDao(IPropertyDistrictHasGroupBuildingBaseDao propertyDistrictHasGroupBuildingBaseDao) {
		this.propertyDistrictHasGroupBuildingBaseDao = propertyDistrictHasGroupBuildingBaseDao;
	}
	/**
	 * 根据条件查询(物业片区与小区关联)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyDistrictHasGroupBuilding> getPropertyDistrictHasGroupBuildingByCondition(Map<String,Object> paramMap){
		return propertyDistrictHasGroupBuildingBaseDao.selectPropertyDistrictHasGroupBuildingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业片区与小区关联)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyDistrictHasGroupBuilding> getPropertyDistrictHasGroupBuildingByConditionDim(Map<String,Object> paramMap){
		return propertyDistrictHasGroupBuildingBaseDao.selectPropertyDistrictHasGroupBuildingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业片区与小区关联)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyDistrictHasGroupBuilding> getPropertyDistrictHasGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyDistrictHasGroupBuildingBaseDao.selectPropertyDistrictHasGroupBuildingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业片区与小区关联)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyDistrictHasGroupBuilding> getPropertyDistrictHasGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyDistrictHasGroupBuildingBaseDao.selectPropertyDistrictHasGroupBuildingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业片区与小区关联)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyDistrictHasGroupBuilding getPropertyDistrictHasGroupBuildingBySeqId(java.math.BigInteger id){
		return propertyDistrictHasGroupBuildingBaseDao.selectPropertyDistrictHasGroupBuildingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业片区与小区关联)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyDistrictHasGroupBuildingCount(Map<String,Object> paramMap){
		return propertyDistrictHasGroupBuildingBaseDao.selectPropertyDistrictHasGroupBuildingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业片区与小区关联)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyDistrictHasGroupBuildingCountDim(Map<String,Object> paramMap){
		return propertyDistrictHasGroupBuildingBaseDao.selectPropertyDistrictHasGroupBuildingCount(paramMap,true);
	}
	/**
	 * 往(物业片区与小区关联)新增一条记录
	 * @param propertyDistrictHasGroupBuilding
	 * @return
	 */
	@Override
	public int insertPropertyDistrictHasGroupBuilding(PropertyDistrictHasGroupBuilding propertyDistrictHasGroupBuilding){
		return propertyDistrictHasGroupBuildingBaseDao.insertPropertyDistrictHasGroupBuilding(propertyDistrictHasGroupBuilding);
	}
	/**
	 * 批量新增(物业片区与小区关联)
	 * @param propertyDistrictHasGroupBuildingList
	 * @return
	 */
	@Override
	public int insertPropertyDistrictHasGroupBuildingBatch(List<PropertyDistrictHasGroupBuilding> propertyDistrictHasGroupBuildingList){
		return propertyDistrictHasGroupBuildingBaseDao.insertPropertyDistrictHasGroupBuildingBatch(propertyDistrictHasGroupBuildingList);
	}
	/**
	 * 更新(物业片区与小区关联)信息
	 * @param propertyDistrictHasGroupBuilding
	 * @return
	 */
	@Override
	public int updatePropertyDistrictHasGroupBuilding(PropertyDistrictHasGroupBuilding propertyDistrictHasGroupBuilding){
		return propertyDistrictHasGroupBuildingBaseDao.updatePropertyDistrictHasGroupBuilding(propertyDistrictHasGroupBuilding);
	}
	/**
	 * 批量更新(物业片区与小区关联)信息
	 * @param propertyDistrictHasGroupBuildingList
	 * @return
	 */
	@Override
	public int updatePropertyDistrictHasGroupBuildingBatch(List<PropertyDistrictHasGroupBuilding> propertyDistrictHasGroupBuildingList){
		return propertyDistrictHasGroupBuildingBaseDao.updatePropertyDistrictHasGroupBuildingBatch(propertyDistrictHasGroupBuildingList);
	}
	/**
	 * 根据序列号删除(物业片区与小区关联)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyDistrictHasGroupBuildingLogic(java.math.BigInteger id){
		return propertyDistrictHasGroupBuildingBaseDao.deletePropertyDistrictHasGroupBuildingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业片区与小区关联)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyDistrictHasGroupBuildingLogicBatch(List<java.math.BigInteger> idList){
		return propertyDistrictHasGroupBuildingBaseDao.deletePropertyDistrictHasGroupBuildingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业片区与小区关联)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyDistrictHasGroupBuilding(java.math.BigInteger id){
//		return propertyDistrictHasGroupBuildingBaseDao.deletePropertyDistrictHasGroupBuilding(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业片区与小区关联)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyDistrictHasGroupBuildingBatch(List<java.math.BigInteger> idList){
//		return propertyDistrictHasGroupBuildingBaseDao.deletePropertyDistrictHasGroupBuildingBatch(idList);
//	}
	
}
