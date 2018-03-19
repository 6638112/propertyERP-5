package com.cnfantasia.server.domainbase.propertyManagement.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyManagement.dao.IPropertyManagementBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;

/**
 * 描述:(物业管理) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyManagementBaseService implements IPropertyManagementBaseService{
	
	private IPropertyManagementBaseDao propertyManagementBaseDao;
	public void setPropertyManagementBaseDao(IPropertyManagementBaseDao propertyManagementBaseDao) {
		this.propertyManagementBaseDao = propertyManagementBaseDao;
	}
	/**
	 * 根据条件查询(物业管理)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyManagement> getPropertyManagementByCondition(Map<String,Object> paramMap){
		return propertyManagementBaseDao.selectPropertyManagementByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业管理)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyManagement> getPropertyManagementByConditionDim(Map<String,Object> paramMap){
		return propertyManagementBaseDao.selectPropertyManagementByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业管理)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyManagement> getPropertyManagementByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyManagementBaseDao.selectPropertyManagementByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业管理)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyManagement> getPropertyManagementByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyManagementBaseDao.selectPropertyManagementByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业管理)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyManagement getPropertyManagementBySeqId(java.math.BigInteger id){
		return propertyManagementBaseDao.selectPropertyManagementBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业管理)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyManagementCount(Map<String,Object> paramMap){
		return propertyManagementBaseDao.selectPropertyManagementCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业管理)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyManagementCountDim(Map<String,Object> paramMap){
		return propertyManagementBaseDao.selectPropertyManagementCount(paramMap,true);
	}
	/**
	 * 往(物业管理)新增一条记录
	 * @param propertyManagement
	 * @return
	 */
	@Override
	public int insertPropertyManagement(PropertyManagement propertyManagement){
		return propertyManagementBaseDao.insertPropertyManagement(propertyManagement);
	}
	/**
	 * 批量新增(物业管理)
	 * @param propertyManagementList
	 * @return
	 */
	@Override
	public int insertPropertyManagementBatch(List<PropertyManagement> propertyManagementList){
		return propertyManagementBaseDao.insertPropertyManagementBatch(propertyManagementList);
	}
	/**
	 * 更新(物业管理)信息
	 * @param propertyManagement
	 * @return
	 */
	@Override
	public int updatePropertyManagement(PropertyManagement propertyManagement){
		return propertyManagementBaseDao.updatePropertyManagement(propertyManagement);
	}
	/**
	 * 批量更新(物业管理)信息
	 * @param propertyManagementList
	 * @return
	 */
	@Override
	public int updatePropertyManagementBatch(List<PropertyManagement> propertyManagementList){
		return propertyManagementBaseDao.updatePropertyManagementBatch(propertyManagementList);
	}
	/**
	 * 根据序列号删除(物业管理)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyManagementLogic(java.math.BigInteger id){
		return propertyManagementBaseDao.deletePropertyManagementLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业管理)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyManagementLogicBatch(List<java.math.BigInteger> idList){
		return propertyManagementBaseDao.deletePropertyManagementLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业管理)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyManagement(java.math.BigInteger id){
//		return propertyManagementBaseDao.deletePropertyManagement(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业管理)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyManagementBatch(List<java.math.BigInteger> idList){
//		return propertyManagementBaseDao.deletePropertyManagementBatch(idList);
//	}
	
}
