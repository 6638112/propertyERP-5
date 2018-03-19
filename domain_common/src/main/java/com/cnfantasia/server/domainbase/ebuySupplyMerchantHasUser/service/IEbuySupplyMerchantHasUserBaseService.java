package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.entity.EbuySupplyMerchantHasUser;

/**
 * 描述:(用户供应商关系表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantHasUserBaseService {
	
	/**
	 * 根据条件查询(用户供应商关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchantHasUser> getEbuySupplyMerchantHasUserByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户供应商关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchantHasUser> getEbuySupplyMerchantHasUserByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户供应商关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchantHasUser> getEbuySupplyMerchantHasUserByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户供应商关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchantHasUser> getEbuySupplyMerchantHasUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户供应商关系表)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchantHasUser getEbuySupplyMerchantHasUserBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户供应商关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantHasUserCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户供应商关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantHasUserCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户供应商关系表)新增一条记录
	 * @param ebuySupplyMerchantHasUser
	 * @return
	 */
	public int insertEbuySupplyMerchantHasUser(EbuySupplyMerchantHasUser ebuySupplyMerchantHasUser);
	/**
	 * 批量新增(用户供应商关系表)
	 * @param ebuySupplyMerchantHasUserList
	 * @return
	 */
	public int insertEbuySupplyMerchantHasUserBatch(List<EbuySupplyMerchantHasUser> ebuySupplyMerchantHasUserList);
	/**
	 * 更新(用户供应商关系表)信息
	 * @param ebuySupplyMerchantHasUser
	 * @return
	 */
	public int updateEbuySupplyMerchantHasUser(EbuySupplyMerchantHasUser ebuySupplyMerchantHasUser);
	/**
	 * 批量更新(用户供应商关系表)信息
	 * @param ebuySupplyMerchantHasUserList
	 * @return
	 */
	public int updateEbuySupplyMerchantHasUserBatch(List<EbuySupplyMerchantHasUser> ebuySupplyMerchantHasUserList);
	/**
	 * 根据序列号删除(用户供应商关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantHasUserLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户供应商关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantHasUserLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户供应商关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantHasUser(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户供应商关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantHasUserBatch(List<java.math.BigInteger> idList);
	
}
