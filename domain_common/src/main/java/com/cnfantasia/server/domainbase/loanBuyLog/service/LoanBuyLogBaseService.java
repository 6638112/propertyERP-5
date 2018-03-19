package com.cnfantasia.server.domainbase.loanBuyLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.loanBuyLog.dao.ILoanBuyLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loanBuyLog.entity.LoanBuyLog;

/**
 * 描述:(借贷购买记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LoanBuyLogBaseService implements ILoanBuyLogBaseService{
	
	private ILoanBuyLogBaseDao loanBuyLogBaseDao;
	public void setLoanBuyLogBaseDao(ILoanBuyLogBaseDao loanBuyLogBaseDao) {
		this.loanBuyLogBaseDao = loanBuyLogBaseDao;
	}
	/**
	 * 根据条件查询(借贷购买记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoanBuyLog> getLoanBuyLogByCondition(Map<String,Object> paramMap){
		return loanBuyLogBaseDao.selectLoanBuyLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(借贷购买记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoanBuyLog> getLoanBuyLogByConditionDim(Map<String,Object> paramMap){
		return loanBuyLogBaseDao.selectLoanBuyLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(借贷购买记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoanBuyLog> getLoanBuyLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return loanBuyLogBaseDao.selectLoanBuyLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(借贷购买记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoanBuyLog> getLoanBuyLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return loanBuyLogBaseDao.selectLoanBuyLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(借贷购买记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoanBuyLog getLoanBuyLogBySeqId(java.math.BigInteger id){
		return loanBuyLogBaseDao.selectLoanBuyLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(借贷购买记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoanBuyLogCount(Map<String,Object> paramMap){
		return loanBuyLogBaseDao.selectLoanBuyLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(借贷购买记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoanBuyLogCountDim(Map<String,Object> paramMap){
		return loanBuyLogBaseDao.selectLoanBuyLogCount(paramMap,true);
	}
	/**
	 * 往(借贷购买记录表)新增一条记录
	 * @param loanBuyLog
	 * @return
	 */
	@Override
	public int insertLoanBuyLog(LoanBuyLog loanBuyLog){
		return loanBuyLogBaseDao.insertLoanBuyLog(loanBuyLog);
	}
	/**
	 * 批量新增(借贷购买记录表)
	 * @param loanBuyLogList
	 * @return
	 */
	@Override
	public int insertLoanBuyLogBatch(List<LoanBuyLog> loanBuyLogList){
		return loanBuyLogBaseDao.insertLoanBuyLogBatch(loanBuyLogList);
	}
	/**
	 * 更新(借贷购买记录表)信息
	 * @param loanBuyLog
	 * @return
	 */
	@Override
	public int updateLoanBuyLog(LoanBuyLog loanBuyLog){
		return loanBuyLogBaseDao.updateLoanBuyLog(loanBuyLog);
	}
	/**
	 * 批量更新(借贷购买记录表)信息
	 * @param loanBuyLogList
	 * @return
	 */
	@Override
	public int updateLoanBuyLogBatch(List<LoanBuyLog> loanBuyLogList){
		return loanBuyLogBaseDao.updateLoanBuyLogBatch(loanBuyLogList);
	}
	/**
	 * 根据序列号删除(借贷购买记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoanBuyLogLogic(java.math.BigInteger id){
		return loanBuyLogBaseDao.deleteLoanBuyLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(借贷购买记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoanBuyLogLogicBatch(List<java.math.BigInteger> idList){
		return loanBuyLogBaseDao.deleteLoanBuyLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(借贷购买记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoanBuyLog(java.math.BigInteger id){
//		return loanBuyLogBaseDao.deleteLoanBuyLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(借贷购买记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoanBuyLogBatch(List<java.math.BigInteger> idList){
//		return loanBuyLogBaseDao.deleteLoanBuyLogBatch(idList);
//	}
	
}
