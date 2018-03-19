package com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.entity.PropertyProprietorBankCollectionInfo;

/**
 * 描述:(业主银行托收信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyProprietorBankCollectionInfoBaseDao {
	/**
	 * 根据条件查询(业主银行托收信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyProprietorBankCollectionInfo> selectPropertyProprietorBankCollectionInfoByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(业主银行托收信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyProprietorBankCollectionInfo> selectPropertyProprietorBankCollectionInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(业主银行托收信息)信息
	 * @param id
	 * @return
	 */
	public PropertyProprietorBankCollectionInfo selectPropertyProprietorBankCollectionInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(业主银行托收信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyProprietorBankCollectionInfoCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(业主银行托收信息)新增一条记录
	 * @param propertyProprietorBankCollectionInfo
	 * @return
	 */
	public int insertPropertyProprietorBankCollectionInfo(PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo);
	
	/**
	 * 批量新增(业主银行托收信息)信息
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
	 * 根据Id 批量删除(业主银行托收信息)信息_逻辑删除
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
