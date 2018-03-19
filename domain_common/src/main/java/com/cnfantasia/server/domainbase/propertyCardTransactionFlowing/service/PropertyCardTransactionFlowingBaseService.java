package com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.dao.IPropertyCardTransactionFlowingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.entity.PropertyCardTransactionFlowing;

/**
 * 描述:(物业代扣卡交易流水) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyCardTransactionFlowingBaseService implements IPropertyCardTransactionFlowingBaseService{
	
	private IPropertyCardTransactionFlowingBaseDao propertyCardTransactionFlowingBaseDao;
	public void setPropertyCardTransactionFlowingBaseDao(IPropertyCardTransactionFlowingBaseDao propertyCardTransactionFlowingBaseDao) {
		this.propertyCardTransactionFlowingBaseDao = propertyCardTransactionFlowingBaseDao;
	}
	/**
	 * 根据条件查询(物业代扣卡交易流水)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCardTransactionFlowing> getPropertyCardTransactionFlowingByCondition(Map<String,Object> paramMap){
		return propertyCardTransactionFlowingBaseDao.selectPropertyCardTransactionFlowingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业代扣卡交易流水)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCardTransactionFlowing> getPropertyCardTransactionFlowingByConditionDim(Map<String,Object> paramMap){
		return propertyCardTransactionFlowingBaseDao.selectPropertyCardTransactionFlowingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业代扣卡交易流水)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCardTransactionFlowing> getPropertyCardTransactionFlowingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCardTransactionFlowingBaseDao.selectPropertyCardTransactionFlowingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业代扣卡交易流水)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCardTransactionFlowing> getPropertyCardTransactionFlowingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCardTransactionFlowingBaseDao.selectPropertyCardTransactionFlowingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业代扣卡交易流水)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCardTransactionFlowing getPropertyCardTransactionFlowingBySeqId(java.math.BigInteger id){
		return propertyCardTransactionFlowingBaseDao.selectPropertyCardTransactionFlowingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡交易流水)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCardTransactionFlowingCount(Map<String,Object> paramMap){
		return propertyCardTransactionFlowingBaseDao.selectPropertyCardTransactionFlowingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡交易流水)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCardTransactionFlowingCountDim(Map<String,Object> paramMap){
		return propertyCardTransactionFlowingBaseDao.selectPropertyCardTransactionFlowingCount(paramMap,true);
	}
	/**
	 * 往(物业代扣卡交易流水)新增一条记录
	 * @param propertyCardTransactionFlowing
	 * @return
	 */
	@Override
	public int insertPropertyCardTransactionFlowing(PropertyCardTransactionFlowing propertyCardTransactionFlowing){
		return propertyCardTransactionFlowingBaseDao.insertPropertyCardTransactionFlowing(propertyCardTransactionFlowing);
	}
	/**
	 * 批量新增(物业代扣卡交易流水)
	 * @param propertyCardTransactionFlowingList
	 * @return
	 */
	@Override
	public int insertPropertyCardTransactionFlowingBatch(List<PropertyCardTransactionFlowing> propertyCardTransactionFlowingList){
		return propertyCardTransactionFlowingBaseDao.insertPropertyCardTransactionFlowingBatch(propertyCardTransactionFlowingList);
	}
	/**
	 * 更新(物业代扣卡交易流水)信息
	 * @param propertyCardTransactionFlowing
	 * @return
	 */
	@Override
	public int updatePropertyCardTransactionFlowing(PropertyCardTransactionFlowing propertyCardTransactionFlowing){
		return propertyCardTransactionFlowingBaseDao.updatePropertyCardTransactionFlowing(propertyCardTransactionFlowing);
	}
	/**
	 * 批量更新(物业代扣卡交易流水)信息
	 * @param propertyCardTransactionFlowingList
	 * @return
	 */
	@Override
	public int updatePropertyCardTransactionFlowingBatch(List<PropertyCardTransactionFlowing> propertyCardTransactionFlowingList){
		return propertyCardTransactionFlowingBaseDao.updatePropertyCardTransactionFlowingBatch(propertyCardTransactionFlowingList);
	}
	/**
	 * 根据序列号删除(物业代扣卡交易流水)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCardTransactionFlowingLogic(java.math.BigInteger id){
		return propertyCardTransactionFlowingBaseDao.deletePropertyCardTransactionFlowingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业代扣卡交易流水)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCardTransactionFlowingLogicBatch(List<java.math.BigInteger> idList){
		return propertyCardTransactionFlowingBaseDao.deletePropertyCardTransactionFlowingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业代扣卡交易流水)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardTransactionFlowing(java.math.BigInteger id){
//		return propertyCardTransactionFlowingBaseDao.deletePropertyCardTransactionFlowing(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡交易流水)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardTransactionFlowingBatch(List<java.math.BigInteger> idList){
//		return propertyCardTransactionFlowingBaseDao.deletePropertyCardTransactionFlowingBatch(idList);
//	}
	
}
