package com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.dao.IPropertyCompanyBankCollectionInfoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.entity.PropertyCompanyBankCollectionInfo;

/**
 * 描述:(物业公司托收银行信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyCompanyBankCollectionInfoBaseService implements IPropertyCompanyBankCollectionInfoBaseService{
	
	private IPropertyCompanyBankCollectionInfoBaseDao propertyCompanyBankCollectionInfoBaseDao;
	public void setPropertyCompanyBankCollectionInfoBaseDao(IPropertyCompanyBankCollectionInfoBaseDao propertyCompanyBankCollectionInfoBaseDao) {
		this.propertyCompanyBankCollectionInfoBaseDao = propertyCompanyBankCollectionInfoBaseDao;
	}
	/**
	 * 根据条件查询(物业公司托收银行信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCompanyBankCollectionInfo> getPropertyCompanyBankCollectionInfoByCondition(Map<String,Object> paramMap){
		return propertyCompanyBankCollectionInfoBaseDao.selectPropertyCompanyBankCollectionInfoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业公司托收银行信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCompanyBankCollectionInfo> getPropertyCompanyBankCollectionInfoByConditionDim(Map<String,Object> paramMap){
		return propertyCompanyBankCollectionInfoBaseDao.selectPropertyCompanyBankCollectionInfoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业公司托收银行信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCompanyBankCollectionInfo> getPropertyCompanyBankCollectionInfoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCompanyBankCollectionInfoBaseDao.selectPropertyCompanyBankCollectionInfoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业公司托收银行信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCompanyBankCollectionInfo> getPropertyCompanyBankCollectionInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCompanyBankCollectionInfoBaseDao.selectPropertyCompanyBankCollectionInfoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业公司托收银行信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCompanyBankCollectionInfo getPropertyCompanyBankCollectionInfoBySeqId(java.math.BigInteger id){
		return propertyCompanyBankCollectionInfoBaseDao.selectPropertyCompanyBankCollectionInfoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCompanyBankCollectionInfoCount(Map<String,Object> paramMap){
		return propertyCompanyBankCollectionInfoBaseDao.selectPropertyCompanyBankCollectionInfoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCompanyBankCollectionInfoCountDim(Map<String,Object> paramMap){
		return propertyCompanyBankCollectionInfoBaseDao.selectPropertyCompanyBankCollectionInfoCount(paramMap,true);
	}
	/**
	 * 往(物业公司托收银行信息)新增一条记录
	 * @param propertyCompanyBankCollectionInfo
	 * @return
	 */
	@Override
	public int insertPropertyCompanyBankCollectionInfo(PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo){
		return propertyCompanyBankCollectionInfoBaseDao.insertPropertyCompanyBankCollectionInfo(propertyCompanyBankCollectionInfo);
	}
	/**
	 * 批量新增(物业公司托收银行信息)
	 * @param propertyCompanyBankCollectionInfoList
	 * @return
	 */
	@Override
	public int insertPropertyCompanyBankCollectionInfoBatch(List<PropertyCompanyBankCollectionInfo> propertyCompanyBankCollectionInfoList){
		return propertyCompanyBankCollectionInfoBaseDao.insertPropertyCompanyBankCollectionInfoBatch(propertyCompanyBankCollectionInfoList);
	}
	/**
	 * 更新(物业公司托收银行信息)信息
	 * @param propertyCompanyBankCollectionInfo
	 * @return
	 */
	@Override
	public int updatePropertyCompanyBankCollectionInfo(PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo){
		return propertyCompanyBankCollectionInfoBaseDao.updatePropertyCompanyBankCollectionInfo(propertyCompanyBankCollectionInfo);
	}
	/**
	 * 批量更新(物业公司托收银行信息)信息
	 * @param propertyCompanyBankCollectionInfoList
	 * @return
	 */
	@Override
	public int updatePropertyCompanyBankCollectionInfoBatch(List<PropertyCompanyBankCollectionInfo> propertyCompanyBankCollectionInfoList){
		return propertyCompanyBankCollectionInfoBaseDao.updatePropertyCompanyBankCollectionInfoBatch(propertyCompanyBankCollectionInfoList);
	}
	/**
	 * 根据序列号删除(物业公司托收银行信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyBankCollectionInfoLogic(java.math.BigInteger id){
		return propertyCompanyBankCollectionInfoBaseDao.deletePropertyCompanyBankCollectionInfoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业公司托收银行信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyBankCollectionInfoLogicBatch(List<java.math.BigInteger> idList){
		return propertyCompanyBankCollectionInfoBaseDao.deletePropertyCompanyBankCollectionInfoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司托收银行信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompanyBankCollectionInfo(java.math.BigInteger id){
//		return propertyCompanyBankCollectionInfoBaseDao.deletePropertyCompanyBankCollectionInfo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司托收银行信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompanyBankCollectionInfoBatch(List<java.math.BigInteger> idList){
//		return propertyCompanyBankCollectionInfoBaseDao.deletePropertyCompanyBankCollectionInfoBatch(idList);
//	}
	
}
