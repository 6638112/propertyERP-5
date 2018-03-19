package com.cnfantasia.server.domainbase.propertyCardDiscountTerm.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyCardDiscountTerm.dao.IPropertyCardDiscountTermBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCardDiscountTerm.entity.PropertyCardDiscountTerm;

/**
 * 描述:(物业代扣卡优惠方案) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyCardDiscountTermBaseService implements IPropertyCardDiscountTermBaseService{
	
	private IPropertyCardDiscountTermBaseDao propertyCardDiscountTermBaseDao;
	public void setPropertyCardDiscountTermBaseDao(IPropertyCardDiscountTermBaseDao propertyCardDiscountTermBaseDao) {
		this.propertyCardDiscountTermBaseDao = propertyCardDiscountTermBaseDao;
	}
	/**
	 * 根据条件查询(物业代扣卡优惠方案)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCardDiscountTerm> getPropertyCardDiscountTermByCondition(Map<String,Object> paramMap){
		return propertyCardDiscountTermBaseDao.selectPropertyCardDiscountTermByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业代扣卡优惠方案)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCardDiscountTerm> getPropertyCardDiscountTermByConditionDim(Map<String,Object> paramMap){
		return propertyCardDiscountTermBaseDao.selectPropertyCardDiscountTermByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业代扣卡优惠方案)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCardDiscountTerm> getPropertyCardDiscountTermByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCardDiscountTermBaseDao.selectPropertyCardDiscountTermByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业代扣卡优惠方案)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCardDiscountTerm> getPropertyCardDiscountTermByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCardDiscountTermBaseDao.selectPropertyCardDiscountTermByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业代扣卡优惠方案)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCardDiscountTerm getPropertyCardDiscountTermBySeqId(java.math.BigInteger id){
		return propertyCardDiscountTermBaseDao.selectPropertyCardDiscountTermBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡优惠方案)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCardDiscountTermCount(Map<String,Object> paramMap){
		return propertyCardDiscountTermBaseDao.selectPropertyCardDiscountTermCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡优惠方案)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCardDiscountTermCountDim(Map<String,Object> paramMap){
		return propertyCardDiscountTermBaseDao.selectPropertyCardDiscountTermCount(paramMap,true);
	}
	/**
	 * 往(物业代扣卡优惠方案)新增一条记录
	 * @param propertyCardDiscountTerm
	 * @return
	 */
	@Override
	public int insertPropertyCardDiscountTerm(PropertyCardDiscountTerm propertyCardDiscountTerm){
		return propertyCardDiscountTermBaseDao.insertPropertyCardDiscountTerm(propertyCardDiscountTerm);
	}
	/**
	 * 批量新增(物业代扣卡优惠方案)
	 * @param propertyCardDiscountTermList
	 * @return
	 */
	@Override
	public int insertPropertyCardDiscountTermBatch(List<PropertyCardDiscountTerm> propertyCardDiscountTermList){
		return propertyCardDiscountTermBaseDao.insertPropertyCardDiscountTermBatch(propertyCardDiscountTermList);
	}
	/**
	 * 更新(物业代扣卡优惠方案)信息
	 * @param propertyCardDiscountTerm
	 * @return
	 */
	@Override
	public int updatePropertyCardDiscountTerm(PropertyCardDiscountTerm propertyCardDiscountTerm){
		return propertyCardDiscountTermBaseDao.updatePropertyCardDiscountTerm(propertyCardDiscountTerm);
	}
	/**
	 * 批量更新(物业代扣卡优惠方案)信息
	 * @param propertyCardDiscountTermList
	 * @return
	 */
	@Override
	public int updatePropertyCardDiscountTermBatch(List<PropertyCardDiscountTerm> propertyCardDiscountTermList){
		return propertyCardDiscountTermBaseDao.updatePropertyCardDiscountTermBatch(propertyCardDiscountTermList);
	}
	/**
	 * 根据序列号删除(物业代扣卡优惠方案)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCardDiscountTermLogic(java.math.BigInteger id){
		return propertyCardDiscountTermBaseDao.deletePropertyCardDiscountTermLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业代扣卡优惠方案)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCardDiscountTermLogicBatch(List<java.math.BigInteger> idList){
		return propertyCardDiscountTermBaseDao.deletePropertyCardDiscountTermLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业代扣卡优惠方案)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardDiscountTerm(java.math.BigInteger id){
//		return propertyCardDiscountTermBaseDao.deletePropertyCardDiscountTerm(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡优惠方案)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardDiscountTermBatch(List<java.math.BigInteger> idList){
//		return propertyCardDiscountTermBaseDao.deletePropertyCardDiscountTermBatch(idList);
//	}
	
}
