package com.cnfantasia.server.api.cashFlowSummary.thread;

import com.cnfantasia.server.api.cashFlowSummary.entity.CashFlowSummaryRunnableParam;
import com.cnfantasia.server.api.cashFlowSummary.service.ICashFlowSummaryService;

/**
 * 现金流数据生成线程类
 * 
 * @author liyulin
 * @version 1.0 2016年7月21日 上午11:28:30
 */
public class CashFlowSummaryRunnable implements Runnable {
	private ICashFlowSummaryService cashFlowSummaryService;
	/** 加锁的表名称 */
	private String lockTable;
	/** 现金流类型 */
	private int billType;
	/** while循环，每次分页查询的条数 */
	private int queryLimitCount;

	public CashFlowSummaryRunnable(CashFlowSummaryRunnableParam param) {
		this.lockTable = param.getLockTable();
		this.billType = param.getBillType();
		this.queryLimitCount = param.getQueryLimitCount();
		this.cashFlowSummaryService = param.getCashFlowSummaryService();
	}

	@Override
	public void run() {
		cashFlowSummaryService.autoInsertCashFlowSummaryBatch(lockTable, billType, queryLimitCount);
	}

}
