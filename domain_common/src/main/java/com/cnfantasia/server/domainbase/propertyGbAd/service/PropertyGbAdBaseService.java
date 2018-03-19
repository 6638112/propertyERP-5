package com.cnfantasia.server.domainbase.propertyGbAd.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyGbAd.dao.IPropertyGbAdBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyGbAd.entity.PropertyGbAd;

/**
 * 描述:(小区物业打印广告配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyGbAdBaseService implements IPropertyGbAdBaseService{
	
	private IPropertyGbAdBaseDao propertyGbAdBaseDao;
	public void setPropertyGbAdBaseDao(IPropertyGbAdBaseDao propertyGbAdBaseDao) {
		this.propertyGbAdBaseDao = propertyGbAdBaseDao;
	}
	/**
	 * 根据条件查询(小区物业打印广告配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyGbAd> getPropertyGbAdByCondition(Map<String,Object> paramMap){
		return propertyGbAdBaseDao.selectPropertyGbAdByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区物业打印广告配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyGbAd> getPropertyGbAdByConditionDim(Map<String,Object> paramMap){
		return propertyGbAdBaseDao.selectPropertyGbAdByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区物业打印广告配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyGbAd> getPropertyGbAdByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyGbAdBaseDao.selectPropertyGbAdByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区物业打印广告配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyGbAd> getPropertyGbAdByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyGbAdBaseDao.selectPropertyGbAdByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区物业打印广告配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyGbAd getPropertyGbAdBySeqId(java.math.BigInteger id){
		return propertyGbAdBaseDao.selectPropertyGbAdBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区物业打印广告配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyGbAdCount(Map<String,Object> paramMap){
		return propertyGbAdBaseDao.selectPropertyGbAdCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区物业打印广告配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyGbAdCountDim(Map<String,Object> paramMap){
		return propertyGbAdBaseDao.selectPropertyGbAdCount(paramMap,true);
	}
	/**
	 * 往(小区物业打印广告配置表)新增一条记录
	 * @param propertyGbAd
	 * @return
	 */
	@Override
	public int insertPropertyGbAd(PropertyGbAd propertyGbAd){
		return propertyGbAdBaseDao.insertPropertyGbAd(propertyGbAd);
	}
	/**
	 * 批量新增(小区物业打印广告配置表)
	 * @param propertyGbAdList
	 * @return
	 */
	@Override
	public int insertPropertyGbAdBatch(List<PropertyGbAd> propertyGbAdList){
		return propertyGbAdBaseDao.insertPropertyGbAdBatch(propertyGbAdList);
	}
	/**
	 * 更新(小区物业打印广告配置表)信息
	 * @param propertyGbAd
	 * @return
	 */
	@Override
	public int updatePropertyGbAd(PropertyGbAd propertyGbAd){
		return propertyGbAdBaseDao.updatePropertyGbAd(propertyGbAd);
	}
	/**
	 * 批量更新(小区物业打印广告配置表)信息
	 * @param propertyGbAdList
	 * @return
	 */
	@Override
	public int updatePropertyGbAdBatch(List<PropertyGbAd> propertyGbAdList){
		return propertyGbAdBaseDao.updatePropertyGbAdBatch(propertyGbAdList);
	}
	/**
	 * 根据序列号删除(小区物业打印广告配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyGbAdLogic(java.math.BigInteger id){
		return propertyGbAdBaseDao.deletePropertyGbAdLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小区物业打印广告配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyGbAdLogicBatch(List<java.math.BigInteger> idList){
		return propertyGbAdBaseDao.deletePropertyGbAdLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小区物业打印广告配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyGbAd(java.math.BigInteger id){
//		return propertyGbAdBaseDao.deletePropertyGbAd(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区物业打印广告配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyGbAdBatch(List<java.math.BigInteger> idList){
//		return propertyGbAdBaseDao.deletePropertyGbAdBatch(idList);
//	}
	
}
