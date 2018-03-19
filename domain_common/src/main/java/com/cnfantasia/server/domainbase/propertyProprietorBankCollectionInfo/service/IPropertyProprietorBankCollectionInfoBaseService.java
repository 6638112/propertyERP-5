package com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.entity.PropertyProprietorBankCollectionInfo;

/**
 * 描述:(业主银行托收信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyProprietorBankCollectionInfoBaseService {
	
	/**
	 * 根据条件查询(业主银行托收信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyProprietorBankCollectionInfo> getPropertyProprietorBankCollectionInfoByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(业主银行托收信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyProprietorBankCollectionInfo> getPropertyProprietorBankCollectionInfoByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(业主银行托收信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyProprietorBankCollectionInfo> getPropertyProprietorBankCollectionInfoByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(业主银行托收信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyProprietorBankCollectionInfo> getPropertyProprietorBankCollectionInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(业主银行托收信息)信息
	 * @param id
	 * @return
	 */
	public PropertyProprietorBankCollectionInfo getPropertyProprietorBankCollectionInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(业主银行托收信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyProprietorBankCollectionInfoCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(业主银行托收信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyProprietorBankCollectionInfoCountDim(Map<String,Object> paramMap);
	/**
	 * 往(业主银行托收信息)新增一条记录
	 * @param propertyProprietorBankCollectionInfo
	 * @return
	 */
	public int insertPropertyProprietorBankCollectionInfo(PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo);
	/**
	 * 批量新增(业主银行托收信息)
	 * @param propertyProprietorBankCollectionInfoList
	 * @return
	 */
	public int insertPropertyProprietorBankCollectionInfoBatch(List<PropertyProprietorBankCollectionInfo> propertyProprietorBankCollectionInfoList);
	/**
	 * 更新(业主银行托收信息)信息
	 * @param propertyProprietorBankCollectionInfo
	 * @return
	 */
	public int updatePropertyProprietorBankCollectionInfo(PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo);
	/**
	 * 批量更新(业主银行托收信息)信息
	 * @param propertyProprietorBankCollectionInfoList
	 * @return
	 */
	public int updatePropertyProprietorBankCollectionInfoBatch(List<PropertyProprietorBankCollectionInfo> propertyProprietorBankCollectionInfoList);
	/**
	 * 根据序列号删除(业主银行托收信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyProprietorBankCollectionInfoLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(业主银行托收信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyProprietorBankCollectionInfoLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(业主银行托收信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyProprietorBankCollectionInfo(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(业主银行托收信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyProprietorBankCollectionInfoBatch(List<java.math.BigInteger> idList);
	
}
