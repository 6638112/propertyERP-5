package com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.entity.PropertyCompanyBankCollectionInfo;

/**
 * 描述:(物业公司托收银行信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyCompanyBankCollectionInfoBaseService {
	
	/**
	 * 根据条件查询(物业公司托收银行信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCompanyBankCollectionInfo> getPropertyCompanyBankCollectionInfoByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业公司托收银行信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyCompanyBankCollectionInfo> getPropertyCompanyBankCollectionInfoByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业公司托收银行信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCompanyBankCollectionInfo> getPropertyCompanyBankCollectionInfoByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业公司托收银行信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyCompanyBankCollectionInfo> getPropertyCompanyBankCollectionInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业公司托收银行信息)信息
	 * @param id
	 * @return
	 */
	public PropertyCompanyBankCollectionInfo getPropertyCompanyBankCollectionInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCompanyBankCollectionInfoCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyCompanyBankCollectionInfoCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业公司托收银行信息)新增一条记录
	 * @param propertyCompanyBankCollectionInfo
	 * @return
	 */
	public int insertPropertyCompanyBankCollectionInfo(PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo);
	/**
	 * 批量新增(物业公司托收银行信息)
	 * @param propertyCompanyBankCollectionInfoList
	 * @return
	 */
	public int insertPropertyCompanyBankCollectionInfoBatch(List<PropertyCompanyBankCollectionInfo> propertyCompanyBankCollectionInfoList);
	/**
	 * 更新(物业公司托收银行信息)信息
	 * @param propertyCompanyBankCollectionInfo
	 * @return
	 */
	public int updatePropertyCompanyBankCollectionInfo(PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo);
	/**
	 * 批量更新(物业公司托收银行信息)信息
	 * @param propertyCompanyBankCollectionInfoList
	 * @return
	 */
	public int updatePropertyCompanyBankCollectionInfoBatch(List<PropertyCompanyBankCollectionInfo> propertyCompanyBankCollectionInfoList);
	/**
	 * 根据序列号删除(物业公司托收银行信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyCompanyBankCollectionInfoLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业公司托收银行信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyCompanyBankCollectionInfoLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业公司托收银行信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyCompanyBankCollectionInfo(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业公司托收银行信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyCompanyBankCollectionInfoBatch(List<java.math.BigInteger> idList);
	
}
