package com.cnfantasia.server.domainbase.propertyCard.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyCard.dao.IPropertyCardBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCard.entity.PropertyCard;

/**
 * 描述:(物业代扣卡) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyCardBaseService implements IPropertyCardBaseService{
	
	private IPropertyCardBaseDao propertyCardBaseDao;
	public void setPropertyCardBaseDao(IPropertyCardBaseDao propertyCardBaseDao) {
		this.propertyCardBaseDao = propertyCardBaseDao;
	}
	/**
	 * 根据条件查询(物业代扣卡)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCard> getPropertyCardByCondition(Map<String,Object> paramMap){
		return propertyCardBaseDao.selectPropertyCardByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业代扣卡)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCard> getPropertyCardByConditionDim(Map<String,Object> paramMap){
		return propertyCardBaseDao.selectPropertyCardByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业代扣卡)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCard> getPropertyCardByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCardBaseDao.selectPropertyCardByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业代扣卡)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCard> getPropertyCardByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCardBaseDao.selectPropertyCardByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业代扣卡)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCard getPropertyCardBySeqId(java.math.BigInteger id){
		return propertyCardBaseDao.selectPropertyCardBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCardCount(Map<String,Object> paramMap){
		return propertyCardBaseDao.selectPropertyCardCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCardCountDim(Map<String,Object> paramMap){
		return propertyCardBaseDao.selectPropertyCardCount(paramMap,true);
	}
	/**
	 * 往(物业代扣卡)新增一条记录
	 * @param propertyCard
	 * @return
	 */
	@Override
	public int insertPropertyCard(PropertyCard propertyCard){
		return propertyCardBaseDao.insertPropertyCard(propertyCard);
	}
	/**
	 * 批量新增(物业代扣卡)
	 * @param propertyCardList
	 * @return
	 */
	@Override
	public int insertPropertyCardBatch(List<PropertyCard> propertyCardList){
		return propertyCardBaseDao.insertPropertyCardBatch(propertyCardList);
	}
	/**
	 * 更新(物业代扣卡)信息
	 * @param propertyCard
	 * @return
	 */
	@Override
	public int updatePropertyCard(PropertyCard propertyCard){
		return propertyCardBaseDao.updatePropertyCard(propertyCard);
	}
	/**
	 * 批量更新(物业代扣卡)信息
	 * @param propertyCardList
	 * @return
	 */
	@Override
	public int updatePropertyCardBatch(List<PropertyCard> propertyCardList){
		return propertyCardBaseDao.updatePropertyCardBatch(propertyCardList);
	}
	/**
	 * 根据序列号删除(物业代扣卡)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCardLogic(java.math.BigInteger id){
		return propertyCardBaseDao.deletePropertyCardLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业代扣卡)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCardLogicBatch(List<java.math.BigInteger> idList){
		return propertyCardBaseDao.deletePropertyCardLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业代扣卡)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCard(java.math.BigInteger id){
//		return propertyCardBaseDao.deletePropertyCard(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardBatch(List<java.math.BigInteger> idList){
//		return propertyCardBaseDao.deletePropertyCardBatch(idList);
//	}
	
}
