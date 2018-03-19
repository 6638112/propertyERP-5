package com.cnfantasia.server.domainbase.bankCollectionDate.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.bankCollectionDate.dao.IBankCollectionDateBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bankCollectionDate.entity.BankCollectionDate;

/**
 * 描述:(物业公司银行托收日期配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BankCollectionDateBaseService implements IBankCollectionDateBaseService{
	
	private IBankCollectionDateBaseDao bankCollectionDateBaseDao;
	public void setBankCollectionDateBaseDao(IBankCollectionDateBaseDao bankCollectionDateBaseDao) {
		this.bankCollectionDateBaseDao = bankCollectionDateBaseDao;
	}
	/**
	 * 根据条件查询(物业公司银行托收日期配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BankCollectionDate> getBankCollectionDateByCondition(Map<String,Object> paramMap){
		return bankCollectionDateBaseDao.selectBankCollectionDateByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业公司银行托收日期配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BankCollectionDate> getBankCollectionDateByConditionDim(Map<String,Object> paramMap){
		return bankCollectionDateBaseDao.selectBankCollectionDateByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业公司银行托收日期配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BankCollectionDate> getBankCollectionDateByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return bankCollectionDateBaseDao.selectBankCollectionDateByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业公司银行托收日期配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BankCollectionDate> getBankCollectionDateByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return bankCollectionDateBaseDao.selectBankCollectionDateByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业公司银行托收日期配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public BankCollectionDate getBankCollectionDateBySeqId(java.math.BigInteger id){
		return bankCollectionDateBaseDao.selectBankCollectionDateBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司银行托收日期配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBankCollectionDateCount(Map<String,Object> paramMap){
		return bankCollectionDateBaseDao.selectBankCollectionDateCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业公司银行托收日期配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBankCollectionDateCountDim(Map<String,Object> paramMap){
		return bankCollectionDateBaseDao.selectBankCollectionDateCount(paramMap,true);
	}
	/**
	 * 往(物业公司银行托收日期配置)新增一条记录
	 * @param bankCollectionDate
	 * @return
	 */
	@Override
	public int insertBankCollectionDate(BankCollectionDate bankCollectionDate){
		return bankCollectionDateBaseDao.insertBankCollectionDate(bankCollectionDate);
	}
	/**
	 * 批量新增(物业公司银行托收日期配置)
	 * @param bankCollectionDateList
	 * @return
	 */
	@Override
	public int insertBankCollectionDateBatch(List<BankCollectionDate> bankCollectionDateList){
		return bankCollectionDateBaseDao.insertBankCollectionDateBatch(bankCollectionDateList);
	}
	/**
	 * 更新(物业公司银行托收日期配置)信息
	 * @param bankCollectionDate
	 * @return
	 */
	@Override
	public int updateBankCollectionDate(BankCollectionDate bankCollectionDate){
		return bankCollectionDateBaseDao.updateBankCollectionDate(bankCollectionDate);
	}
	/**
	 * 批量更新(物业公司银行托收日期配置)信息
	 * @param bankCollectionDateList
	 * @return
	 */
	@Override
	public int updateBankCollectionDateBatch(List<BankCollectionDate> bankCollectionDateList){
		return bankCollectionDateBaseDao.updateBankCollectionDateBatch(bankCollectionDateList);
	}
	/**
	 * 根据序列号删除(物业公司银行托收日期配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBankCollectionDateLogic(java.math.BigInteger id){
		return bankCollectionDateBaseDao.deleteBankCollectionDateLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业公司银行托收日期配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBankCollectionDateLogicBatch(List<java.math.BigInteger> idList){
		return bankCollectionDateBaseDao.deleteBankCollectionDateLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司银行托收日期配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBankCollectionDate(java.math.BigInteger id){
//		return bankCollectionDateBaseDao.deleteBankCollectionDate(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司银行托收日期配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBankCollectionDateBatch(List<java.math.BigInteger> idList){
//		return bankCollectionDateBaseDao.deleteBankCollectionDateBatch(idList);
//	}
	
}
