package com.cnfantasia.server.domainbase.propertyRepair.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyRepair.dao.IPropertyRepairBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;

/**
 * 描述:(物业报修单) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyRepairBaseService implements IPropertyRepairBaseService{
	
	private IPropertyRepairBaseDao propertyRepairBaseDao;
	public void setPropertyRepairBaseDao(IPropertyRepairBaseDao propertyRepairBaseDao) {
		this.propertyRepairBaseDao = propertyRepairBaseDao;
	}
	/**
	 * 根据条件查询(物业报修单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRepair> getPropertyRepairByCondition(Map<String,Object> paramMap){
		return propertyRepairBaseDao.selectPropertyRepairByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业报修单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRepair> getPropertyRepairByConditionDim(Map<String,Object> paramMap){
		return propertyRepairBaseDao.selectPropertyRepairByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业报修单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRepair> getPropertyRepairByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRepairBaseDao.selectPropertyRepairByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业报修单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRepair> getPropertyRepairByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRepairBaseDao.selectPropertyRepairByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业报修单)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRepair getPropertyRepairBySeqId(java.math.BigInteger id){
		return propertyRepairBaseDao.selectPropertyRepairBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业报修单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRepairCount(Map<String,Object> paramMap){
		return propertyRepairBaseDao.selectPropertyRepairCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业报修单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRepairCountDim(Map<String,Object> paramMap){
		return propertyRepairBaseDao.selectPropertyRepairCount(paramMap,true);
	}
	/**
	 * 往(物业报修单)新增一条记录
	 * @param propertyRepair
	 * @return
	 */
	@Override
	public int insertPropertyRepair(PropertyRepair propertyRepair){
		return propertyRepairBaseDao.insertPropertyRepair(propertyRepair);
	}
	/**
	 * 批量新增(物业报修单)
	 * @param propertyRepairList
	 * @return
	 */
	@Override
	public int insertPropertyRepairBatch(List<PropertyRepair> propertyRepairList){
		return propertyRepairBaseDao.insertPropertyRepairBatch(propertyRepairList);
	}
	/**
	 * 更新(物业报修单)信息
	 * @param propertyRepair
	 * @return
	 */
	@Override
	public int updatePropertyRepair(PropertyRepair propertyRepair){
		return propertyRepairBaseDao.updatePropertyRepair(propertyRepair);
	}
	/**
	 * 批量更新(物业报修单)信息
	 * @param propertyRepairList
	 * @return
	 */
	@Override
	public int updatePropertyRepairBatch(List<PropertyRepair> propertyRepairList){
		return propertyRepairBaseDao.updatePropertyRepairBatch(propertyRepairList);
	}
	/**
	 * 根据序列号删除(物业报修单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairLogic(java.math.BigInteger id){
		return propertyRepairBaseDao.deletePropertyRepairLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业报修单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairLogicBatch(List<java.math.BigInteger> idList){
		return propertyRepairBaseDao.deletePropertyRepairLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业报修单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepair(java.math.BigInteger id){
//		return propertyRepairBaseDao.deletePropertyRepair(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业报修单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairBatch(List<java.math.BigInteger> idList){
//		return propertyRepairBaseDao.deletePropertyRepairBatch(idList);
//	}
	
}
