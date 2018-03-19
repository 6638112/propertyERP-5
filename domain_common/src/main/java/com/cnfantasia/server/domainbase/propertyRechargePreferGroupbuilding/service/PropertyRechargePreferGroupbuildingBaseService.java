package com.cnfantasia.server.domainbase.propertyRechargePreferGroupbuilding.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyRechargePreferGroupbuilding.dao.IPropertyRechargePreferGroupbuildingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRechargePreferGroupbuilding.entity.PropertyRechargePreferGroupbuilding;

/**
 * 描述:(物业预充值随机立减小区规则关联表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyRechargePreferGroupbuildingBaseService implements IPropertyRechargePreferGroupbuildingBaseService{
	
	private IPropertyRechargePreferGroupbuildingBaseDao propertyRechargePreferGroupbuildingBaseDao;
	public void setPropertyRechargePreferGroupbuildingBaseDao(IPropertyRechargePreferGroupbuildingBaseDao propertyRechargePreferGroupbuildingBaseDao) {
		this.propertyRechargePreferGroupbuildingBaseDao = propertyRechargePreferGroupbuildingBaseDao;
	}
	/**
	 * 根据条件查询(物业预充值随机立减小区规则关联表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferGroupbuilding> getPropertyRechargePreferGroupbuildingByCondition(Map<String,Object> paramMap){
		return propertyRechargePreferGroupbuildingBaseDao.selectPropertyRechargePreferGroupbuildingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业预充值随机立减小区规则关联表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferGroupbuilding> getPropertyRechargePreferGroupbuildingByConditionDim(Map<String,Object> paramMap){
		return propertyRechargePreferGroupbuildingBaseDao.selectPropertyRechargePreferGroupbuildingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业预充值随机立减小区规则关联表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferGroupbuilding> getPropertyRechargePreferGroupbuildingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRechargePreferGroupbuildingBaseDao.selectPropertyRechargePreferGroupbuildingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业预充值随机立减小区规则关联表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferGroupbuilding> getPropertyRechargePreferGroupbuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRechargePreferGroupbuildingBaseDao.selectPropertyRechargePreferGroupbuildingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业预充值随机立减小区规则关联表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRechargePreferGroupbuilding getPropertyRechargePreferGroupbuildingBySeqId(java.math.BigInteger id){
		return propertyRechargePreferGroupbuildingBaseDao.selectPropertyRechargePreferGroupbuildingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业预充值随机立减小区规则关联表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRechargePreferGroupbuildingCount(Map<String,Object> paramMap){
		return propertyRechargePreferGroupbuildingBaseDao.selectPropertyRechargePreferGroupbuildingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业预充值随机立减小区规则关联表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRechargePreferGroupbuildingCountDim(Map<String,Object> paramMap){
		return propertyRechargePreferGroupbuildingBaseDao.selectPropertyRechargePreferGroupbuildingCount(paramMap,true);
	}
	/**
	 * 往(物业预充值随机立减小区规则关联表)新增一条记录
	 * @param propertyRechargePreferGroupbuilding
	 * @return
	 */
	@Override
	public int insertPropertyRechargePreferGroupbuilding(PropertyRechargePreferGroupbuilding propertyRechargePreferGroupbuilding){
		return propertyRechargePreferGroupbuildingBaseDao.insertPropertyRechargePreferGroupbuilding(propertyRechargePreferGroupbuilding);
	}
	/**
	 * 批量新增(物业预充值随机立减小区规则关联表)
	 * @param propertyRechargePreferGroupbuildingList
	 * @return
	 */
	@Override
	public int insertPropertyRechargePreferGroupbuildingBatch(List<PropertyRechargePreferGroupbuilding> propertyRechargePreferGroupbuildingList){
		return propertyRechargePreferGroupbuildingBaseDao.insertPropertyRechargePreferGroupbuildingBatch(propertyRechargePreferGroupbuildingList);
	}
	/**
	 * 更新(物业预充值随机立减小区规则关联表)信息
	 * @param propertyRechargePreferGroupbuilding
	 * @return
	 */
	@Override
	public int updatePropertyRechargePreferGroupbuilding(PropertyRechargePreferGroupbuilding propertyRechargePreferGroupbuilding){
		return propertyRechargePreferGroupbuildingBaseDao.updatePropertyRechargePreferGroupbuilding(propertyRechargePreferGroupbuilding);
	}
	/**
	 * 批量更新(物业预充值随机立减小区规则关联表)信息
	 * @param propertyRechargePreferGroupbuildingList
	 * @return
	 */
	@Override
	public int updatePropertyRechargePreferGroupbuildingBatch(List<PropertyRechargePreferGroupbuilding> propertyRechargePreferGroupbuildingList){
		return propertyRechargePreferGroupbuildingBaseDao.updatePropertyRechargePreferGroupbuildingBatch(propertyRechargePreferGroupbuildingList);
	}
	/**
	 * 根据序列号删除(物业预充值随机立减小区规则关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deletePropertyRechargePreferGroupbuildingLogic(java.math.BigInteger id){
		return propertyRechargePreferGroupbuildingBaseDao.deletePropertyRechargePreferGroupbuildingLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(物业预充值随机立减小区规则关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deletePropertyRechargePreferGroupbuildingLogicBatch(List<java.math.BigInteger> idList){
		return propertyRechargePreferGroupbuildingBaseDao.deletePropertyRechargePreferGroupbuildingLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(物业预充值随机立减小区规则关联表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePreferGroupbuilding(java.math.BigInteger id){
//		return propertyRechargePreferGroupbuildingBaseDao.deletePropertyRechargePreferGroupbuilding(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业预充值随机立减小区规则关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePreferGroupbuildingBatch(List<java.math.BigInteger> idList){
//		return propertyRechargePreferGroupbuildingBaseDao.deletePropertyRechargePreferGroupbuildingBatch(idList);
//	}
	
}
