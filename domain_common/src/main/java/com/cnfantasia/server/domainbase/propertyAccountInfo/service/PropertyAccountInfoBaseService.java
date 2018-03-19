package com.cnfantasia.server.domainbase.propertyAccountInfo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyAccountInfo.dao.IPropertyAccountInfoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyAccountInfo.entity.PropertyAccountInfo;

/**
 * 描述:(用户物业账户信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyAccountInfoBaseService implements IPropertyAccountInfoBaseService{
	
	private IPropertyAccountInfoBaseDao propertyAccountInfoBaseDao;
	public void setPropertyAccountInfoBaseDao(IPropertyAccountInfoBaseDao propertyAccountInfoBaseDao) {
		this.propertyAccountInfoBaseDao = propertyAccountInfoBaseDao;
	}
	/**
	 * 根据条件查询(用户物业账户信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyAccountInfo> getPropertyAccountInfoByCondition(Map<String,Object> paramMap){
		return propertyAccountInfoBaseDao.selectPropertyAccountInfoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户物业账户信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyAccountInfo> getPropertyAccountInfoByConditionDim(Map<String,Object> paramMap){
		return propertyAccountInfoBaseDao.selectPropertyAccountInfoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户物业账户信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyAccountInfo> getPropertyAccountInfoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyAccountInfoBaseDao.selectPropertyAccountInfoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户物业账户信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyAccountInfo> getPropertyAccountInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyAccountInfoBaseDao.selectPropertyAccountInfoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户物业账户信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyAccountInfo getPropertyAccountInfoBySeqId(java.math.BigInteger id){
		return propertyAccountInfoBaseDao.selectPropertyAccountInfoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户物业账户信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyAccountInfoCount(Map<String,Object> paramMap){
		return propertyAccountInfoBaseDao.selectPropertyAccountInfoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户物业账户信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyAccountInfoCountDim(Map<String,Object> paramMap){
		return propertyAccountInfoBaseDao.selectPropertyAccountInfoCount(paramMap,true);
	}
	/**
	 * 往(用户物业账户信息)新增一条记录
	 * @param propertyAccountInfo
	 * @return
	 */
	@Override
	public int insertPropertyAccountInfo(PropertyAccountInfo propertyAccountInfo){
		return propertyAccountInfoBaseDao.insertPropertyAccountInfo(propertyAccountInfo);
	}
	/**
	 * 批量新增(用户物业账户信息)
	 * @param propertyAccountInfoList
	 * @return
	 */
	@Override
	public int insertPropertyAccountInfoBatch(List<PropertyAccountInfo> propertyAccountInfoList){
		return propertyAccountInfoBaseDao.insertPropertyAccountInfoBatch(propertyAccountInfoList);
	}
	/**
	 * 更新(用户物业账户信息)信息
	 * @param propertyAccountInfo
	 * @return
	 */
	@Override
	public int updatePropertyAccountInfo(PropertyAccountInfo propertyAccountInfo){
		return propertyAccountInfoBaseDao.updatePropertyAccountInfo(propertyAccountInfo);
	}
	/**
	 * 批量更新(用户物业账户信息)信息
	 * @param propertyAccountInfoList
	 * @return
	 */
	@Override
	public int updatePropertyAccountInfoBatch(List<PropertyAccountInfo> propertyAccountInfoList){
		return propertyAccountInfoBaseDao.updatePropertyAccountInfoBatch(propertyAccountInfoList);
	}
	/**
	 * 根据序列号删除(用户物业账户信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyAccountInfoLogic(java.math.BigInteger id){
		return propertyAccountInfoBaseDao.deletePropertyAccountInfoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户物业账户信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyAccountInfoLogicBatch(List<java.math.BigInteger> idList){
		return propertyAccountInfoBaseDao.deletePropertyAccountInfoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户物业账户信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyAccountInfo(java.math.BigInteger id){
//		return propertyAccountInfoBaseDao.deletePropertyAccountInfo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户物业账户信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyAccountInfoBatch(List<java.math.BigInteger> idList){
//		return propertyAccountInfoBaseDao.deletePropertyAccountInfoBatch(idList);
//	}
	
}
