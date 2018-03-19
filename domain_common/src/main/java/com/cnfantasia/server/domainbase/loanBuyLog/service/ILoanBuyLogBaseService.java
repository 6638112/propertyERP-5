package com.cnfantasia.server.domainbase.loanBuyLog.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.loanBuyLog.entity.LoanBuyLog;

/**
 * 描述:(借贷购买记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoanBuyLogBaseService {
	
	/**
	 * 根据条件查询(借贷购买记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoanBuyLog> getLoanBuyLogByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(借贷购买记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoanBuyLog> getLoanBuyLogByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(借贷购买记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoanBuyLog> getLoanBuyLogByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(借贷购买记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoanBuyLog> getLoanBuyLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(借贷购买记录表)信息
	 * @param id
	 * @return
	 */
	public LoanBuyLog getLoanBuyLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(借贷购买记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLoanBuyLogCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(借贷购买记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLoanBuyLogCountDim(Map<String,Object> paramMap);
	/**
	 * 往(借贷购买记录表)新增一条记录
	 * @param loanBuyLog
	 * @return
	 */
	public int insertLoanBuyLog(LoanBuyLog loanBuyLog);
	/**
	 * 批量新增(借贷购买记录表)
	 * @param loanBuyLogList
	 * @return
	 */
	public int insertLoanBuyLogBatch(List<LoanBuyLog> loanBuyLogList);
	/**
	 * 更新(借贷购买记录表)信息
	 * @param loanBuyLog
	 * @return
	 */
	public int updateLoanBuyLog(LoanBuyLog loanBuyLog);
	/**
	 * 批量更新(借贷购买记录表)信息
	 * @param loanBuyLogList
	 * @return
	 */
	public int updateLoanBuyLogBatch(List<LoanBuyLog> loanBuyLogList);
	/**
	 * 根据序列号删除(借贷购买记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLoanBuyLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(借贷购买记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLoanBuyLogLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(借贷购买记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLoanBuyLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(借贷购买记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLoanBuyLogBatch(List<java.math.BigInteger> idList);
	
}
