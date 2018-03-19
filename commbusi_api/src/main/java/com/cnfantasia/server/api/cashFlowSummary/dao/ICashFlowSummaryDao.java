package com.cnfantasia.server.api.cashFlowSummary.dao;

import java.util.List;

import com.cnfantasia.server.domainbase.cashFlowSummary.dao.ICashFlowSummaryBaseDao;
import com.cnfantasia.server.domainbase.cashFlowSummary.entity.CashFlowSummary;

/**
 * 现金流量汇总表（Job）
 * 
 * @author liyulin
 * @version 1.0 2016年7月18日 上午11:27:33
 */
public interface ICashFlowSummaryDao extends ICashFlowSummaryBaseDao{
	
	/**
	 * 查询“物业费”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	public List<CashFlowSummary> selectForWY(int queryLimitCount);
	
	/**
	 * 查询“停车费”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	public List<CashFlowSummary> selectForParking(int queryLimitCount);
	
	/**
	 * 查询“其他代收费用”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	public List<CashFlowSummary> selectForOther(int queryLimitCount);

	/**
	 * 查询“楼下店”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	public List<CashFlowSummary> selectForDown(int queryLimitCount);
	
	/**
	 * 查询“自营超市”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	public List<CashFlowSummary> selectForSelfRun(int queryLimitCount);
	
	/**
	 * 查询“上门维修”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	public List<CashFlowSummary> selectForRepair(int queryLimitCount);
	
	/**
	 * 查询“物业宝”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	public List<CashFlowSummary> selectForWYB(int queryLimitCount);
	
	/**
	 * 查询“停车宝”数据
	 * 
	 * @param queryLimitCount 查询的数据条数
	 * @return
	 */
	public List<CashFlowSummary> selectForParkingB(int queryLimitCount);
	
	/**
	 * 批量修改表t_finance_buy字段f_cash_status=2
	 * 
	 * @param srcIds
	 * @return
	 */
	public int updateFinanceBuyCashStatusBatch(List<String> srcIds);
	
	/**
	 * 批量修改表t_dredge_bill字段f_cash_status=2
	 * 
	 * @param srcIds
	 * @return
	 */
	public int updateDredgeBillCashStatusBatch(List<String> srcIds);
	
	/**
	 * 批量修改表t_ebuy_order字段f_cash_status=2
	 * 
	 * @param srcIds
	 * @return
	 */
	public int updateEbuyOrderCashStatusBatch(List<String> srcIds);
	
	/**
	 * 批量修改表t_car_num_pay_log字段f_cash_status=2
	 * 
	 * @param srcIds
	 * @return
	 */
	public int updateCarNumPayLogCashStatusBatch(List<String> srcIds);
	
	/**
	 * 批量修改表t_pay_bill字段f_cash_status=2
	 * 
	 * @param srcIds
	 * @return
	 */
	public int updatePayBillCashStatusBatch(List<String> srcIds);
}
