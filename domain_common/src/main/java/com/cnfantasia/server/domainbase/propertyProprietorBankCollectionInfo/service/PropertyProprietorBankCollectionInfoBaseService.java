package com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.dao.IPropertyProprietorBankCollectionInfoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.entity.PropertyProprietorBankCollectionInfo;

/**
 * 描述:(业主银行托收信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyProprietorBankCollectionInfoBaseService implements IPropertyProprietorBankCollectionInfoBaseService{
	
	private IPropertyProprietorBankCollectionInfoBaseDao propertyProprietorBankCollectionInfoBaseDao;
	public void setPropertyProprietorBankCollectionInfoBaseDao(IPropertyProprietorBankCollectionInfoBaseDao propertyProprietorBankCollectionInfoBaseDao) {
		this.propertyProprietorBankCollectionInfoBaseDao = propertyProprietorBankCollectionInfoBaseDao;
	}
	/**
	 * 根据条件查询(业主银行托收信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyProprietorBankCollectionInfo> getPropertyProprietorBankCollectionInfoByCondition(Map<String,Object> paramMap){
		return propertyProprietorBankCollectionInfoBaseDao.selectPropertyProprietorBankCollectionInfoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(业主银行托收信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyProprietorBankCollectionInfo> getPropertyProprietorBankCollectionInfoByConditionDim(Map<String,Object> paramMap){
		return propertyProprietorBankCollectionInfoBaseDao.selectPropertyProprietorBankCollectionInfoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(业主银行托收信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyProprietorBankCollectionInfo> getPropertyProprietorBankCollectionInfoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyProprietorBankCollectionInfoBaseDao.selectPropertyProprietorBankCollectionInfoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(业主银行托收信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyProprietorBankCollectionInfo> getPropertyProprietorBankCollectionInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyProprietorBankCollectionInfoBaseDao.selectPropertyProprietorBankCollectionInfoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(业主银行托收信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyProprietorBankCollectionInfo getPropertyProprietorBankCollectionInfoBySeqId(java.math.BigInteger id){
		return propertyProprietorBankCollectionInfoBaseDao.selectPropertyProprietorBankCollectionInfoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(业主银行托收信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyProprietorBankCollectionInfoCount(Map<String,Object> paramMap){
		return propertyProprietorBankCollectionInfoBaseDao.selectPropertyProprietorBankCollectionInfoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(业主银行托收信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyProprietorBankCollectionInfoCountDim(Map<String,Object> paramMap){
		return propertyProprietorBankCollectionInfoBaseDao.selectPropertyProprietorBankCollectionInfoCount(paramMap,true);
	}
	/**
	 * 往(业主银行托收信息)新增一条记录
	 * @param propertyProprietorBankCollectionInfo
	 * @return
	 */
	@Override
	public int insertPropertyProprietorBankCollectionInfo(PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo){
		return propertyProprietorBankCollectionInfoBaseDao.insertPropertyProprietorBankCollectionInfo(propertyProprietorBankCollectionInfo);
	}
	/**
	 * 批量新增(业主银行托收信息)
	 * @param propertyProprietorBankCollectionInfoList
	 * @return
	 */
	@Override
	public int insertPropertyProprietorBankCollectionInfoBatch(List<PropertyProprietorBankCollectionInfo> propertyProprietorBankCollectionInfoList){
		return propertyProprietorBankCollectionInfoBaseDao.insertPropertyProprietorBankCollectionInfoBatch(propertyProprietorBankCollectionInfoList);
	}
	/**
	 * 更新(业主银行托收信息)信息
	 * @param propertyProprietorBankCollectionInfo
	 * @return
	 */
	@Override
	public int updatePropertyProprietorBankCollectionInfo(PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo){
		return propertyProprietorBankCollectionInfoBaseDao.updatePropertyProprietorBankCollectionInfo(propertyProprietorBankCollectionInfo);
	}
	/**
	 * 批量更新(业主银行托收信息)信息
	 * @param propertyProprietorBankCollectionInfoList
	 * @return
	 */
	@Override
	public int updatePropertyProprietorBankCollectionInfoBatch(List<PropertyProprietorBankCollectionInfo> propertyProprietorBankCollectionInfoList){
		return propertyProprietorBankCollectionInfoBaseDao.updatePropertyProprietorBankCollectionInfoBatch(propertyProprietorBankCollectionInfoList);
	}
	/**
	 * 根据序列号删除(业主银行托收信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyProprietorBankCollectionInfoLogic(java.math.BigInteger id){
		return propertyProprietorBankCollectionInfoBaseDao.deletePropertyProprietorBankCollectionInfoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(业主银行托收信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyProprietorBankCollectionInfoLogicBatch(List<java.math.BigInteger> idList){
		return propertyProprietorBankCollectionInfoBaseDao.deletePropertyProprietorBankCollectionInfoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(业主银行托收信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyProprietorBankCollectionInfo(java.math.BigInteger id){
//		return propertyProprietorBankCollectionInfoBaseDao.deletePropertyProprietorBankCollectionInfo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(业主银行托收信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyProprietorBankCollectionInfoBatch(List<java.math.BigInteger> idList){
//		return propertyProprietorBankCollectionInfoBaseDao.deletePropertyProprietorBankCollectionInfoBatch(idList);
//	}
	
}
