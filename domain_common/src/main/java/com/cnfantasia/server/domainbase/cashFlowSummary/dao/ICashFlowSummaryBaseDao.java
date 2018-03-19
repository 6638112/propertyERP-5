package com.cnfantasia.server.domainbase.cashFlowSummary.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cashFlowSummary.entity.CashFlowSummary;

/**
 * 描述:(现金流量汇总表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICashFlowSummaryBaseDao {
	/**
	 * 根据条件查询(现金流量汇总表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CashFlowSummary> selectCashFlowSummaryByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(现金流量汇总表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CashFlowSummary> selectCashFlowSummaryByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(现金流量汇总表)信息
	 * @param id
	 * @return
	 */
	public CashFlowSummary selectCashFlowSummaryBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(现金流量汇总表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCashFlowSummaryCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(现金流量汇总表)新增一条记录
	 * @param cashFlowSummary
	 * @return
	 */
	public int insertCashFlowSummary(CashFlowSummary cashFlowSummary);
	
	/**
	 * 批量新增(现金流量汇总表)信息
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
	 * 根据Id 批量删除(现金流量汇总表)信息_逻辑删除
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
