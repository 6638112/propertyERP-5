package com.cnfantasia.server.api.cashFlowSummary.service;

import com.cnfantasia.server.domainbase.cashFlowSummary.service.ICashFlowSummaryBaseService;

/**
 * 现金流量汇总表
 * 
 * @author liyulin
 * @version 1.0 2016年7月18日 下午5:39:58
 */
public interface ICashFlowSummaryService extends ICashFlowSummaryBaseService{
	
	/**
	 * 表t_cash_flow_summary数据自动生成
	 * 
	 * @param lockTable 锁定的表
	 * @param billType 现金流类型
	 * @param limitCount while循环每次查询的数据条数
	 */
	public void autoInsertCashFlowSummaryBatch(String lockTable, int billType, int limitCount);
}
