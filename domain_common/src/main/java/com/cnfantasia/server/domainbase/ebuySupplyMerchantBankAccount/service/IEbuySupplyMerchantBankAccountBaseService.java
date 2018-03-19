package com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.entity.EbuySupplyMerchantBankAccount;

/**
 * 描述:(楼下店等电商供应商银行卡信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantBankAccountBaseService {
	
	/**
	 * 根据条件查询(楼下店等电商供应商银行卡信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchantBankAccount> getEbuySupplyMerchantBankAccountByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(楼下店等电商供应商银行卡信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchantBankAccount> getEbuySupplyMerchantBankAccountByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(楼下店等电商供应商银行卡信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchantBankAccount> getEbuySupplyMerchantBankAccountByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(楼下店等电商供应商银行卡信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchantBankAccount> getEbuySupplyMerchantBankAccountByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(楼下店等电商供应商银行卡信息)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchantBankAccount getEbuySupplyMerchantBankAccountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(楼下店等电商供应商银行卡信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantBankAccountCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(楼下店等电商供应商银行卡信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantBankAccountCountDim(Map<String, Object> paramMap);
	/**
	 * 往(楼下店等电商供应商银行卡信息)新增一条记录
	 * @param ebuySupplyMerchantBankAccount
	 * @return
	 */
	public int insertEbuySupplyMerchantBankAccount(EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount);
	/**
	 * 批量新增(楼下店等电商供应商银行卡信息)
	 * @param ebuySupplyMerchantBankAccountList
	 * @return
	 */
	public int insertEbuySupplyMerchantBankAccountBatch(List<EbuySupplyMerchantBankAccount> ebuySupplyMerchantBankAccountList);
	/**
	 * 更新(楼下店等电商供应商银行卡信息)信息
	 * @param ebuySupplyMerchantBankAccount
	 * @return
	 */
	public int updateEbuySupplyMerchantBankAccount(EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount);
	/**
	 * 批量更新(楼下店等电商供应商银行卡信息)信息
	 * @param ebuySupplyMerchantBankAccountList
	 * @return
	 */
	public int updateEbuySupplyMerchantBankAccountBatch(List<EbuySupplyMerchantBankAccount> ebuySupplyMerchantBankAccountList);
	/**
	 * 根据序列号删除(楼下店等电商供应商银行卡信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantBankAccountLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(楼下店等电商供应商银行卡信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantBankAccountLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(楼下店等电商供应商银行卡信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantBankAccount(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(楼下店等电商供应商银行卡信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantBankAccountBatch(List<java.math.BigInteger> idList);
	
}
