package com.cnfantasia.server.domainbase.cashFlowSummary.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.cashFlowSummary.entity.CashFlowSummary;

/**
 * 描述:(现金流量汇总表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICashFlowSummaryBaseService {
	
	/**
	 * 根据条件查询(现金流量汇总表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CashFlowSummary> getCashFlowSummaryByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(现金流量汇总表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CashFlowSummary> getCashFlowSummaryByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(现金流量汇总表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CashFlowSummary> getCashFlowSummaryByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(现金流量汇总表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CashFlowSummary> getCashFlowSummaryByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(现金流量汇总表)信息
	 * @param id
	 * @return
	 */
	public CashFlowSummary getCashFlowSummaryBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(现金流量汇总表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCashFlowSummaryCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(现金流量汇总表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCashFlowSummaryCountDim(Map<String,Object> paramMap);
	/**
	 * 往(现金流量汇总表)新增一条记录
	 * @param cashFlowSummary
	 * @return
	 */
	public int insertCashFlowSummary(CashFlowSummary cashFlowSummary);
	/**
	 * 批量新增(现金流量汇总表)
	 * @param cashFlowSummaryList
	 * @return
	 */
	public int insertCashFlowSummaryBatch(List<CashFlowSummary> cashFlowSummaryList);
	/**
	 * 更新(现金流量汇总表)信息
	 * @param cashFlowSummary
	 * @return
	 */
	public int updateCashFlowSummary(CashFlowSummary cashFlowSummary);
	/**
	 * 批量更新(现金流量汇总表)信息
	 * @param cashFlowSummaryList
	 * @return
	 */
	public int updateCashFlowSummaryBatch(List<CashFlowSummary> cashFlowSummaryList);
	/**
	 * 根据序列号删除(现金流量汇总表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCashFlowSummaryLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(现金流量汇总表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCashFlowSummaryLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(现金流量汇总表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCashFlowSummary(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(现金流量汇总表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCashFlowSummaryBatch(List<java.math.BigInteger> idList);
	
}
