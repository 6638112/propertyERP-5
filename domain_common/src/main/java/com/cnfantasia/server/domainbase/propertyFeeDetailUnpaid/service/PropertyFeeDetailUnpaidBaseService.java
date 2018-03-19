package com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.dao.IPropertyFeeDetailUnpaidBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.entity.PropertyFeeDetailUnpaid;

/**
 * 描述:(账单欠费信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyFeeDetailUnpaidBaseService implements IPropertyFeeDetailUnpaidBaseService{
	
	private IPropertyFeeDetailUnpaidBaseDao propertyFeeDetailUnpaidBaseDao;
	public void setPropertyFeeDetailUnpaidBaseDao(IPropertyFeeDetailUnpaidBaseDao propertyFeeDetailUnpaidBaseDao) {
		this.propertyFeeDetailUnpaidBaseDao = propertyFeeDetailUnpaidBaseDao;
	}
	/**
	 * 根据条件查询(账单欠费信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyFeeDetailUnpaid> getPropertyFeeDetailUnpaidByCondition(Map<String,Object> paramMap){
		return propertyFeeDetailUnpaidBaseDao.selectPropertyFeeDetailUnpaidByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(账单欠费信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyFeeDetailUnpaid> getPropertyFeeDetailUnpaidByConditionDim(Map<String,Object> paramMap){
		return propertyFeeDetailUnpaidBaseDao.selectPropertyFeeDetailUnpaidByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(账单欠费信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyFeeDetailUnpaid> getPropertyFeeDetailUnpaidByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyFeeDetailUnpaidBaseDao.selectPropertyFeeDetailUnpaidByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(账单欠费信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyFeeDetailUnpaid> getPropertyFeeDetailUnpaidByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyFeeDetailUnpaidBaseDao.selectPropertyFeeDetailUnpaidByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(账单欠费信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyFeeDetailUnpaid getPropertyFeeDetailUnpaidBySeqId(java.math.BigInteger id){
		return propertyFeeDetailUnpaidBaseDao.selectPropertyFeeDetailUnpaidBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(账单欠费信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyFeeDetailUnpaidCount(Map<String,Object> paramMap){
		return propertyFeeDetailUnpaidBaseDao.selectPropertyFeeDetailUnpaidCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(账单欠费信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyFeeDetailUnpaidCountDim(Map<String,Object> paramMap){
		return propertyFeeDetailUnpaidBaseDao.selectPropertyFeeDetailUnpaidCount(paramMap,true);
	}
	/**
	 * 往(账单欠费信息表)新增一条记录
	 * @param propertyFeeDetailUnpaid
	 * @return
	 */
	@Override
	public int insertPropertyFeeDetailUnpaid(PropertyFeeDetailUnpaid propertyFeeDetailUnpaid){
		return propertyFeeDetailUnpaidBaseDao.insertPropertyFeeDetailUnpaid(propertyFeeDetailUnpaid);
	}
	/**
	 * 批量新增(账单欠费信息表)
	 * @param propertyFeeDetailUnpaidList
	 * @return
	 */
	@Override
	public int insertPropertyFeeDetailUnpaidBatch(List<PropertyFeeDetailUnpaid> propertyFeeDetailUnpaidList){
		return propertyFeeDetailUnpaidBaseDao.insertPropertyFeeDetailUnpaidBatch(propertyFeeDetailUnpaidList);
	}
	/**
	 * 更新(账单欠费信息表)信息
	 * @param propertyFeeDetailUnpaid
	 * @return
	 */
	@Override
	public int updatePropertyFeeDetailUnpaid(PropertyFeeDetailUnpaid propertyFeeDetailUnpaid){
		return propertyFeeDetailUnpaidBaseDao.updatePropertyFeeDetailUnpaid(propertyFeeDetailUnpaid);
	}
	/**
	 * 批量更新(账单欠费信息表)信息
	 * @param propertyFeeDetailUnpaidList
	 * @return
	 */
	@Override
	public int updatePropertyFeeDetailUnpaidBatch(List<PropertyFeeDetailUnpaid> propertyFeeDetailUnpaidList){
		return propertyFeeDetailUnpaidBaseDao.updatePropertyFeeDetailUnpaidBatch(propertyFeeDetailUnpaidList);
	}
	/**
	 * 根据序列号删除(账单欠费信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deletePropertyFeeDetailUnpaidLogic(java.math.BigInteger id){
		return propertyFeeDetailUnpaidBaseDao.deletePropertyFeeDetailUnpaidLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(账单欠费信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deletePropertyFeeDetailUnpaidLogicBatch(List<java.math.BigInteger> idList){
		return propertyFeeDetailUnpaidBaseDao.deletePropertyFeeDetailUnpaidLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(账单欠费信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyFeeDetailUnpaid(java.math.BigInteger id){
//		return propertyFeeDetailUnpaidBaseDao.deletePropertyFeeDetailUnpaid(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账单欠费信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyFeeDetailUnpaidBatch(List<java.math.BigInteger> idList){
//		return propertyFeeDetailUnpaidBaseDao.deletePropertyFeeDetailUnpaidBatch(idList);
//	}
	
}
