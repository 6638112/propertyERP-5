package com.cnfantasia.server.domainbase.propertyCardDeductionDetail.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyCardDeductionDetail.dao.IPropertyCardDeductionDetailBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCardDeductionDetail.entity.PropertyCardDeductionDetail;

/**
 * 描述:(物业代扣卡划扣详情) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyCardDeductionDetailBaseService implements IPropertyCardDeductionDetailBaseService{
	
	private IPropertyCardDeductionDetailBaseDao propertyCardDeductionDetailBaseDao;
	public void setPropertyCardDeductionDetailBaseDao(IPropertyCardDeductionDetailBaseDao propertyCardDeductionDetailBaseDao) {
		this.propertyCardDeductionDetailBaseDao = propertyCardDeductionDetailBaseDao;
	}
	/**
	 * 根据条件查询(物业代扣卡划扣详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCardDeductionDetail> getPropertyCardDeductionDetailByCondition(Map<String,Object> paramMap){
		return propertyCardDeductionDetailBaseDao.selectPropertyCardDeductionDetailByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业代扣卡划扣详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCardDeductionDetail> getPropertyCardDeductionDetailByConditionDim(Map<String,Object> paramMap){
		return propertyCardDeductionDetailBaseDao.selectPropertyCardDeductionDetailByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业代扣卡划扣详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCardDeductionDetail> getPropertyCardDeductionDetailByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCardDeductionDetailBaseDao.selectPropertyCardDeductionDetailByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业代扣卡划扣详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCardDeductionDetail> getPropertyCardDeductionDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCardDeductionDetailBaseDao.selectPropertyCardDeductionDetailByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业代扣卡划扣详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCardDeductionDetail getPropertyCardDeductionDetailBySeqId(java.math.BigInteger id){
		return propertyCardDeductionDetailBaseDao.selectPropertyCardDeductionDetailBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡划扣详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCardDeductionDetailCount(Map<String,Object> paramMap){
		return propertyCardDeductionDetailBaseDao.selectPropertyCardDeductionDetailCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡划扣详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCardDeductionDetailCountDim(Map<String,Object> paramMap){
		return propertyCardDeductionDetailBaseDao.selectPropertyCardDeductionDetailCount(paramMap,true);
	}
	/**
	 * 往(物业代扣卡划扣详情)新增一条记录
	 * @param propertyCardDeductionDetail
	 * @return
	 */
	@Override
	public int insertPropertyCardDeductionDetail(PropertyCardDeductionDetail propertyCardDeductionDetail){
		return propertyCardDeductionDetailBaseDao.insertPropertyCardDeductionDetail(propertyCardDeductionDetail);
	}
	/**
	 * 批量新增(物业代扣卡划扣详情)
	 * @param propertyCardDeductionDetailList
	 * @return
	 */
	@Override
	public int insertPropertyCardDeductionDetailBatch(List<PropertyCardDeductionDetail> propertyCardDeductionDetailList){
		return propertyCardDeductionDetailBaseDao.insertPropertyCardDeductionDetailBatch(propertyCardDeductionDetailList);
	}
	/**
	 * 更新(物业代扣卡划扣详情)信息
	 * @param propertyCardDeductionDetail
	 * @return
	 */
	@Override
	public int updatePropertyCardDeductionDetail(PropertyCardDeductionDetail propertyCardDeductionDetail){
		return propertyCardDeductionDetailBaseDao.updatePropertyCardDeductionDetail(propertyCardDeductionDetail);
	}
	/**
	 * 批量更新(物业代扣卡划扣详情)信息
	 * @param propertyCardDeductionDetailList
	 * @return
	 */
	@Override
	public int updatePropertyCardDeductionDetailBatch(List<PropertyCardDeductionDetail> propertyCardDeductionDetailList){
		return propertyCardDeductionDetailBaseDao.updatePropertyCardDeductionDetailBatch(propertyCardDeductionDetailList);
	}
	/**
	 * 根据序列号删除(物业代扣卡划扣详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCardDeductionDetailLogic(java.math.BigInteger id){
		return propertyCardDeductionDetailBaseDao.deletePropertyCardDeductionDetailLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业代扣卡划扣详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCardDeductionDetailLogicBatch(List<java.math.BigInteger> idList){
		return propertyCardDeductionDetailBaseDao.deletePropertyCardDeductionDetailLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业代扣卡划扣详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardDeductionDetail(java.math.BigInteger id){
//		return propertyCardDeductionDetailBaseDao.deletePropertyCardDeductionDetail(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡划扣详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardDeductionDetailBatch(List<java.math.BigInteger> idList){
//		return propertyCardDeductionDetailBaseDao.deletePropertyCardDeductionDetailBatch(idList);
//	}
	
}
