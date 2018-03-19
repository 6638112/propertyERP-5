package com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.dao.IEbuySupplyMerchantBankAccountBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.entity.EbuySupplyMerchantBankAccount;

/**
 * 描述:(楼下店等电商供应商银行卡信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuySupplyMerchantBankAccountBaseService implements IEbuySupplyMerchantBankAccountBaseService{
	
	private IEbuySupplyMerchantBankAccountBaseDao ebuySupplyMerchantBankAccountBaseDao;
	public void setEbuySupplyMerchantBankAccountBaseDao(IEbuySupplyMerchantBankAccountBaseDao ebuySupplyMerchantBankAccountBaseDao) {
		this.ebuySupplyMerchantBankAccountBaseDao = ebuySupplyMerchantBankAccountBaseDao;
	}
	/**
	 * 根据条件查询(楼下店等电商供应商银行卡信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantBankAccount> getEbuySupplyMerchantBankAccountByCondition(Map<String,Object> paramMap){
		return ebuySupplyMerchantBankAccountBaseDao.selectEbuySupplyMerchantBankAccountByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(楼下店等电商供应商银行卡信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantBankAccount> getEbuySupplyMerchantBankAccountByConditionDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantBankAccountBaseDao.selectEbuySupplyMerchantBankAccountByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(楼下店等电商供应商银行卡信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantBankAccount> getEbuySupplyMerchantBankAccountByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantBankAccountBaseDao.selectEbuySupplyMerchantBankAccountByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(楼下店等电商供应商银行卡信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantBankAccount> getEbuySupplyMerchantBankAccountByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantBankAccountBaseDao.selectEbuySupplyMerchantBankAccountByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(楼下店等电商供应商银行卡信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchantBankAccount getEbuySupplyMerchantBankAccountBySeqId(java.math.BigInteger id){
		return ebuySupplyMerchantBankAccountBaseDao.selectEbuySupplyMerchantBankAccountBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(楼下店等电商供应商银行卡信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantBankAccountCount(Map<String,Object> paramMap){
		return ebuySupplyMerchantBankAccountBaseDao.selectEbuySupplyMerchantBankAccountCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(楼下店等电商供应商银行卡信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantBankAccountCountDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantBankAccountBaseDao.selectEbuySupplyMerchantBankAccountCount(paramMap,true);
	}
	/**
	 * 往(楼下店等电商供应商银行卡信息)新增一条记录
	 * @param ebuySupplyMerchantBankAccount
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantBankAccount(EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount){
		return ebuySupplyMerchantBankAccountBaseDao.insertEbuySupplyMerchantBankAccount(ebuySupplyMerchantBankAccount);
	}
	/**
	 * 批量新增(楼下店等电商供应商银行卡信息)
	 * @param ebuySupplyMerchantBankAccountList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantBankAccountBatch(List<EbuySupplyMerchantBankAccount> ebuySupplyMerchantBankAccountList){
		return ebuySupplyMerchantBankAccountBaseDao.insertEbuySupplyMerchantBankAccountBatch(ebuySupplyMerchantBankAccountList);
	}
	/**
	 * 更新(楼下店等电商供应商银行卡信息)信息
	 * @param ebuySupplyMerchantBankAccount
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantBankAccount(EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount){
		return ebuySupplyMerchantBankAccountBaseDao.updateEbuySupplyMerchantBankAccount(ebuySupplyMerchantBankAccount);
	}
	/**
	 * 批量更新(楼下店等电商供应商银行卡信息)信息
	 * @param ebuySupplyMerchantBankAccountList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantBankAccountBatch(List<EbuySupplyMerchantBankAccount> ebuySupplyMerchantBankAccountList){
		return ebuySupplyMerchantBankAccountBaseDao.updateEbuySupplyMerchantBankAccountBatch(ebuySupplyMerchantBankAccountList);
	}
	/**
	 * 根据序列号删除(楼下店等电商供应商银行卡信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantBankAccountLogic(java.math.BigInteger id){
		return ebuySupplyMerchantBankAccountBaseDao.deleteEbuySupplyMerchantBankAccountLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(楼下店等电商供应商银行卡信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantBankAccountLogicBatch(List<java.math.BigInteger> idList){
		return ebuySupplyMerchantBankAccountBaseDao.deleteEbuySupplyMerchantBankAccountLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(楼下店等电商供应商银行卡信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantBankAccount(java.math.BigInteger id){
//		return ebuySupplyMerchantBankAccountBaseDao.deleteEbuySupplyMerchantBankAccount(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(楼下店等电商供应商银行卡信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantBankAccountBatch(List<java.math.BigInteger> idList){
//		return ebuySupplyMerchantBankAccountBaseDao.deleteEbuySupplyMerchantBankAccountBatch(idList);
//	}
	
}
