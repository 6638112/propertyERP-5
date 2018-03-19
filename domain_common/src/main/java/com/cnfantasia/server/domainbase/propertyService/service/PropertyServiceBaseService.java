package com.cnfantasia.server.domainbase.propertyService.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyService.dao.IPropertyServiceBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyService.entity.PropertyService;

/**
 * 描述:(物业服务信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyServiceBaseService implements IPropertyServiceBaseService{
	
	private IPropertyServiceBaseDao propertyServiceBaseDao;
	public void setPropertyServiceBaseDao(IPropertyServiceBaseDao propertyServiceBaseDao) {
		this.propertyServiceBaseDao = propertyServiceBaseDao;
	}
	/**
	 * 根据条件查询(物业服务信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyService> getPropertyServiceByCondition(Map<String,Object> paramMap){
		return propertyServiceBaseDao.selectPropertyServiceByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业服务信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyService> getPropertyServiceByConditionDim(Map<String,Object> paramMap){
		return propertyServiceBaseDao.selectPropertyServiceByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业服务信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyService> getPropertyServiceByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyServiceBaseDao.selectPropertyServiceByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业服务信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyService> getPropertyServiceByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyServiceBaseDao.selectPropertyServiceByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业服务信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyService getPropertyServiceBySeqId(java.math.BigInteger id){
		return propertyServiceBaseDao.selectPropertyServiceBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业服务信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyServiceCount(Map<String,Object> paramMap){
		return propertyServiceBaseDao.selectPropertyServiceCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业服务信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyServiceCountDim(Map<String,Object> paramMap){
		return propertyServiceBaseDao.selectPropertyServiceCount(paramMap,true);
	}
	/**
	 * 往(物业服务信息)新增一条记录
	 * @param propertyService
	 * @return
	 */
	@Override
	public int insertPropertyService(PropertyService propertyService){
		return propertyServiceBaseDao.insertPropertyService(propertyService);
	}
	/**
	 * 批量新增(物业服务信息)
	 * @param propertyServiceList
	 * @return
	 */
	@Override
	public int insertPropertyServiceBatch(List<PropertyService> propertyServiceList){
		return propertyServiceBaseDao.insertPropertyServiceBatch(propertyServiceList);
	}
	/**
	 * 更新(物业服务信息)信息
	 * @param propertyService
	 * @return
	 */
	@Override
	public int updatePropertyService(PropertyService propertyService){
		return propertyServiceBaseDao.updatePropertyService(propertyService);
	}
	/**
	 * 批量更新(物业服务信息)信息
	 * @param propertyServiceList
	 * @return
	 */
	@Override
	public int updatePropertyServiceBatch(List<PropertyService> propertyServiceList){
		return propertyServiceBaseDao.updatePropertyServiceBatch(propertyServiceList);
	}
	/**
	 * 根据序列号删除(物业服务信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyServiceLogic(java.math.BigInteger id){
		return propertyServiceBaseDao.deletePropertyServiceLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业服务信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyServiceLogicBatch(List<java.math.BigInteger> idList){
		return propertyServiceBaseDao.deletePropertyServiceLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业服务信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyService(java.math.BigInteger id){
//		return propertyServiceBaseDao.deletePropertyService(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业服务信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyServiceBatch(List<java.math.BigInteger> idList){
//		return propertyServiceBaseDao.deletePropertyServiceBatch(idList);
//	}
	
}
