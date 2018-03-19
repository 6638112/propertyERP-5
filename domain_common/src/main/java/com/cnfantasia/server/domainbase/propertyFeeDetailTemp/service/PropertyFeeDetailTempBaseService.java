package com.cnfantasia.server.domainbase.propertyFeeDetailTemp.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyFeeDetailTemp.dao.IPropertyFeeDetailTempBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyFeeDetailTemp.entity.PropertyFeeDetailTemp;

/**
 * 描述:(物业收费项费用明细临时表（生成账单使用）) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyFeeDetailTempBaseService implements IPropertyFeeDetailTempBaseService{
	
	private IPropertyFeeDetailTempBaseDao propertyFeeDetailTempBaseDao;
	public void setPropertyFeeDetailTempBaseDao(IPropertyFeeDetailTempBaseDao propertyFeeDetailTempBaseDao) {
		this.propertyFeeDetailTempBaseDao = propertyFeeDetailTempBaseDao;
	}
	/**
	 * 根据条件查询(物业收费项费用明细临时表（生成账单使用）)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyFeeDetailTemp> getPropertyFeeDetailTempByCondition(Map<String,Object> paramMap){
		return propertyFeeDetailTempBaseDao.selectPropertyFeeDetailTempByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业收费项费用明细临时表（生成账单使用）)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyFeeDetailTemp> getPropertyFeeDetailTempByConditionDim(Map<String,Object> paramMap){
		return propertyFeeDetailTempBaseDao.selectPropertyFeeDetailTempByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业收费项费用明细临时表（生成账单使用）)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyFeeDetailTemp> getPropertyFeeDetailTempByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyFeeDetailTempBaseDao.selectPropertyFeeDetailTempByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业收费项费用明细临时表（生成账单使用）)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyFeeDetailTemp> getPropertyFeeDetailTempByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyFeeDetailTempBaseDao.selectPropertyFeeDetailTempByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyFeeDetailTemp getPropertyFeeDetailTempBySeqId(java.math.BigInteger id){
		return propertyFeeDetailTempBaseDao.selectPropertyFeeDetailTempBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业收费项费用明细临时表（生成账单使用）)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyFeeDetailTempCount(Map<String,Object> paramMap){
		return propertyFeeDetailTempBaseDao.selectPropertyFeeDetailTempCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业收费项费用明细临时表（生成账单使用）)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyFeeDetailTempCountDim(Map<String,Object> paramMap){
		return propertyFeeDetailTempBaseDao.selectPropertyFeeDetailTempCount(paramMap,true);
	}
	/**
	 * 往(物业收费项费用明细临时表（生成账单使用）)新增一条记录
	 * @param propertyFeeDetailTemp
	 * @return
	 */
	@Override
	public int insertPropertyFeeDetailTemp(PropertyFeeDetailTemp propertyFeeDetailTemp){
		return propertyFeeDetailTempBaseDao.insertPropertyFeeDetailTemp(propertyFeeDetailTemp);
	}
	/**
	 * 批量新增(物业收费项费用明细临时表（生成账单使用）)
	 * @param propertyFeeDetailTempList
	 * @return
	 */
	@Override
	public int insertPropertyFeeDetailTempBatch(List<PropertyFeeDetailTemp> propertyFeeDetailTempList){
		return propertyFeeDetailTempBaseDao.insertPropertyFeeDetailTempBatch(propertyFeeDetailTempList);
	}
	/**
	 * 更新(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param propertyFeeDetailTemp
	 * @return
	 */
	@Override
	public int updatePropertyFeeDetailTemp(PropertyFeeDetailTemp propertyFeeDetailTemp){
		return propertyFeeDetailTempBaseDao.updatePropertyFeeDetailTemp(propertyFeeDetailTemp);
	}
	/**
	 * 批量更新(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param propertyFeeDetailTempList
	 * @return
	 */
	@Override
	public int updatePropertyFeeDetailTempBatch(List<PropertyFeeDetailTemp> propertyFeeDetailTempList){
		return propertyFeeDetailTempBaseDao.updatePropertyFeeDetailTempBatch(propertyFeeDetailTempList);
	}
	/**
	 * 根据序列号删除(物业收费项费用明细临时表（生成账单使用）)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyFeeDetailTempLogic(java.math.BigInteger id){
		return propertyFeeDetailTempBaseDao.deletePropertyFeeDetailTempLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业收费项费用明细临时表（生成账单使用）)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyFeeDetailTempLogicBatch(List<java.math.BigInteger> idList){
		return propertyFeeDetailTempBaseDao.deletePropertyFeeDetailTempLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业收费项费用明细临时表（生成账单使用）)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyFeeDetailTemp(java.math.BigInteger id){
//		return propertyFeeDetailTempBaseDao.deletePropertyFeeDetailTemp(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业收费项费用明细临时表（生成账单使用）)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyFeeDetailTempBatch(List<java.math.BigInteger> idList){
//		return propertyFeeDetailTempBaseDao.deletePropertyFeeDetailTempBatch(idList);
//	}
	
}
