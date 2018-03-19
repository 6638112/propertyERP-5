package com.cnfantasia.server.domainbase.supportBank.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.supportBank.dao.ISupportBankBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.supportBank.entity.SupportBank;

/**
 * 描述:(支持的转账银行) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class SupportBankBaseService implements ISupportBankBaseService{
	
	private ISupportBankBaseDao supportBankBaseDao;
	public void setSupportBankBaseDao(ISupportBankBaseDao supportBankBaseDao) {
		this.supportBankBaseDao = supportBankBaseDao;
	}
	/**
	 * 根据条件查询(支持的转账银行)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SupportBank> getSupportBankByCondition(Map<String,Object> paramMap){
		return supportBankBaseDao.selectSupportBankByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(支持的转账银行)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SupportBank> getSupportBankByConditionDim(Map<String,Object> paramMap){
		return supportBankBaseDao.selectSupportBankByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(支持的转账银行)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SupportBank> getSupportBankByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return supportBankBaseDao.selectSupportBankByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(支持的转账银行)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SupportBank> getSupportBankByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return supportBankBaseDao.selectSupportBankByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(支持的转账银行)信息
	 * @param id
	 * @return
	 */
	@Override
	public SupportBank getSupportBankBySeqId(java.math.BigInteger id){
		return supportBankBaseDao.selectSupportBankBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(支持的转账银行)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSupportBankCount(Map<String,Object> paramMap){
		return supportBankBaseDao.selectSupportBankCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(支持的转账银行)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSupportBankCountDim(Map<String,Object> paramMap){
		return supportBankBaseDao.selectSupportBankCount(paramMap,true);
	}
	/**
	 * 往(支持的转账银行)新增一条记录
	 * @param supportBank
	 * @return
	 */
	@Override
	public int insertSupportBank(SupportBank supportBank){
		return supportBankBaseDao.insertSupportBank(supportBank);
	}
	/**
	 * 批量新增(支持的转账银行)
	 * @param supportBankList
	 * @return
	 */
	@Override
	public int insertSupportBankBatch(List<SupportBank> supportBankList){
		return supportBankBaseDao.insertSupportBankBatch(supportBankList);
	}
	/**
	 * 更新(支持的转账银行)信息
	 * @param supportBank
	 * @return
	 */
	@Override
	public int updateSupportBank(SupportBank supportBank){
		return supportBankBaseDao.updateSupportBank(supportBank);
	}
	/**
	 * 批量更新(支持的转账银行)信息
	 * @param supportBankList
	 * @return
	 */
	@Override
	public int updateSupportBankBatch(List<SupportBank> supportBankList){
		return supportBankBaseDao.updateSupportBankBatch(supportBankList);
	}
	/**
	 * 根据序列号删除(支持的转账银行)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteSupportBankLogic(java.math.BigInteger id){
		return supportBankBaseDao.deleteSupportBankLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(支持的转账银行)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteSupportBankLogicBatch(List<java.math.BigInteger> idList){
		return supportBankBaseDao.deleteSupportBankLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(支持的转账银行)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSupportBank(java.math.BigInteger id){
//		return supportBankBaseDao.deleteSupportBank(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(支持的转账银行)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSupportBankBatch(List<java.math.BigInteger> idList){
//		return supportBankBaseDao.deleteSupportBankBatch(idList);
//	}
	
}
