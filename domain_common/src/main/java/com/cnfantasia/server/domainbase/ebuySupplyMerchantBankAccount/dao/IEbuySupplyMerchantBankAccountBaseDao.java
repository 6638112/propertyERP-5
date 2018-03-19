package com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.entity.EbuySupplyMerchantBankAccount;

/**
 * 描述:(楼下店等电商供应商银行卡信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantBankAccountBaseDao {
	/**
	 * 根据条件查询(楼下店等电商供应商银行卡信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchantBankAccount> selectEbuySupplyMerchantBankAccountByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(楼下店等电商供应商银行卡信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchantBankAccount> selectEbuySupplyMerchantBankAccountByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(楼下店等电商供应商银行卡信息)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchantBankAccount selectEbuySupplyMerchantBankAccountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(楼下店等电商供应商银行卡信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuySupplyMerchantBankAccountCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(楼下店等电商供应商银行卡信息)新增一条记录
	 * @param ebuySupplyMerchantBankAccount
	 * @return
	 */
	public int insertEbuySupplyMerchantBankAccount(EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount);
	
	/**
	 * 批量新增(楼下店等电商供应商银行卡信息)信息
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
	 * 根据Id 批量删除(楼下店等电商供应商银行卡信息)信息_逻辑删除
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
