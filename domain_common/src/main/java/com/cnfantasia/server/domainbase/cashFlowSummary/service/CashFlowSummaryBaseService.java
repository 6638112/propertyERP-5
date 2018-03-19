package com.cnfantasia.server.domainbase.cashFlowSummary.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.cashFlowSummary.dao.ICashFlowSummaryBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cashFlowSummary.entity.CashFlowSummary;

/**
 * 描述:(现金流量汇总表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CashFlowSummaryBaseService implements ICashFlowSummaryBaseService{
	
	private ICashFlowSummaryBaseDao cashFlowSummaryBaseDao;
	public void setCashFlowSummaryBaseDao(ICashFlowSummaryBaseDao cashFlowSummaryBaseDao) {
		this.cashFlowSummaryBaseDao = cashFlowSummaryBaseDao;
	}
	/**
	 * 根据条件查询(现金流量汇总表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CashFlowSummary> getCashFlowSummaryByCondition(Map<String,Object> paramMap){
		return cashFlowSummaryBaseDao.selectCashFlowSummaryByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(现金流量汇总表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CashFlowSummary> getCashFlowSummaryByConditionDim(Map<String,Object> paramMap){
		return cashFlowSummaryBaseDao.selectCashFlowSummaryByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(现金流量汇总表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CashFlowSummary> getCashFlowSummaryByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return cashFlowSummaryBaseDao.selectCashFlowSummaryByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(现金流量汇总表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CashFlowSummary> getCashFlowSummaryByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return cashFlowSummaryBaseDao.selectCashFlowSummaryByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(现金流量汇总表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CashFlowSummary getCashFlowSummaryBySeqId(java.math.BigInteger id){
		return cashFlowSummaryBaseDao.selectCashFlowSummaryBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(现金流量汇总表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCashFlowSummaryCount(Map<String,Object> paramMap){
		return cashFlowSummaryBaseDao.selectCashFlowSummaryCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(现金流量汇总表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCashFlowSummaryCountDim(Map<String,Object> paramMap){
		return cashFlowSummaryBaseDao.selectCashFlowSummaryCount(paramMap,true);
	}
	/**
	 * 往(现金流量汇总表)新增一条记录
	 * @param cashFlowSummary
	 * @return
	 */
	@Override
	public int insertCashFlowSummary(CashFlowSummary cashFlowSummary){
		return cashFlowSummaryBaseDao.insertCashFlowSummary(cashFlowSummary);
	}
	/**
	 * 批量新增(现金流量汇总表)
	 * @param cashFlowSummaryList
	 * @return
	 */
	@Override
	public int insertCashFlowSummaryBatch(List<CashFlowSummary> cashFlowSummaryList){
		return cashFlowSummaryBaseDao.insertCashFlowSummaryBatch(cashFlowSummaryList);
	}
	/**
	 * 更新(现金流量汇总表)信息
	 * @param cashFlowSummary
	 * @return
	 */
	@Override
	public int updateCashFlowSummary(CashFlowSummary cashFlowSummary){
		return cashFlowSummaryBaseDao.updateCashFlowSummary(cashFlowSummary);
	}
	/**
	 * 批量更新(现金流量汇总表)信息
	 * @param cashFlowSummaryList
	 * @return
	 */
	@Override
	public int updateCashFlowSummaryBatch(List<CashFlowSummary> cashFlowSummaryList){
		return cashFlowSummaryBaseDao.updateCashFlowSummaryBatch(cashFlowSummaryList);
	}
	/**
	 * 根据序列号删除(现金流量汇总表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCashFlowSummaryLogic(java.math.BigInteger id){
		return cashFlowSummaryBaseDao.deleteCashFlowSummaryLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(现金流量汇总表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCashFlowSummaryLogicBatch(List<java.math.BigInteger> idList){
		return cashFlowSummaryBaseDao.deleteCashFlowSummaryLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(现金流量汇总表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCashFlowSummary(java.math.BigInteger id){
//		return cashFlowSummaryBaseDao.deleteCashFlowSummary(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(现金流量汇总表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCashFlowSummaryBatch(List<java.math.BigInteger> idList){
//		return cashFlowSummaryBaseDao.deleteCashFlowSummaryBatch(idList);
//	}
	
}
