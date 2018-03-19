package com.cnfantasia.server.api.cashFlowSummary.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cnfantasia.server.api.cashFlowSummary.constant.CashFlowSummaryDict;
import com.cnfantasia.server.api.cashFlowSummary.entity.CashFlowSummaryRunnableParam;
import com.cnfantasia.server.api.cashFlowSummary.service.ICashFlowSummaryService;
import com.cnfantasia.server.api.cashFlowSummary.thread.CashFlowSummaryRunnable;

/**
 * 现金流数据Job生成
 * 
 * @author liyulin
 * @version 1.0 2016年7月18日 上午10:41:00
 */
public class CashFlowSummaryTask implements ICashFlowSummaryTask {
	/** newFixedThreadPool默认大小设置为现金流类型大小 */
	private final static ExecutorService cashFlowSummaryFixedThreadPool = Executors.newFixedThreadPool(CashFlowSummaryDict.BILL_TYPES.length);
	
	private ICashFlowSummaryService cashFlowSummaryService;

	public void setCashFlowSummaryService(
			ICashFlowSummaryService cashFlowSummaryService) {
		this.cashFlowSummaryService = cashFlowSummaryService;
	}

	@Override
	public void execTask() {
		int[] billTypes = CashFlowSummaryDict.BILL_TYPES;
		String[] lockTables = CashFlowSummaryDict.LOCK_TABLES;
		for (int i = 0; i < billTypes.length; i++) {
			CashFlowSummaryRunnableParam cashFlowSummaryRunnableParam = new CashFlowSummaryRunnableParam();
			{
				cashFlowSummaryRunnableParam.setCashFlowSummaryService(cashFlowSummaryService);
				cashFlowSummaryRunnableParam.setLockTable(lockTables[i]);
				cashFlowSummaryRunnableParam.setBillType(billTypes[i]);
				cashFlowSummaryRunnableParam.setQueryLimitCount(CashFlowSummaryDict.QUERY_LIMIT_COUNT);
			}
			
			CashFlowSummaryRunnable cashFlowSummaryRunnable = new CashFlowSummaryRunnable(cashFlowSummaryRunnableParam);
			cashFlowSummaryFixedThreadPool.execute(cashFlowSummaryRunnable);
		}
	}

}
