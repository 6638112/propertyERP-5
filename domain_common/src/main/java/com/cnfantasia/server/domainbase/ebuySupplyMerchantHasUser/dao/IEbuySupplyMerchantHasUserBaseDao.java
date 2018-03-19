package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.entity.EbuySupplyMerchantHasUser;

/**
 * 描述:(用户供应商关系表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantHasUserBaseDao {
	/**
	 * 根据条件查询(用户供应商关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchantHasUser> selectEbuySupplyMerchantHasUserByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户供应商关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchantHasUser> selectEbuySupplyMerchantHasUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户供应商关系表)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchantHasUser selectEbuySupplyMerchantHasUserBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户供应商关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuySupplyMerchantHasUserCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户供应商关系表)新增一条记录
	 * @param ebuySupplyMerchantHasUser
	 * @return
	 */
	public int insertEbuySupplyMerchantHasUser(EbuySupplyMerchantHasUser ebuySupplyMerchantHasUser);
	
	/**
	 * 批量新增(用户供应商关系表)信息
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
	 * 根据Id 批量删除(用户供应商关系表)信息_逻辑删除
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
