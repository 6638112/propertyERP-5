package com.cnfantasia.server.api.cashFlowSummary.entity;

import com.cnfantasia.server.api.cashFlowSummary.service.ICashFlowSummaryService;

/**
 * 现金流数据生成线程类构造参数
 * 
 * @author liyulin
 * @version 1.0 2016年8月24日 上午9:27:18
 */
public class CashFlowSummaryRunnableParam {

	private ICashFlowSummaryService cashFlowSummaryService;
	/** 加锁的表名称 */
	private String lockTable;
	/** 现金流类型 */
	private int billType;
	/** while循环，每次分页查询的条数 */
	private int queryLimitCount;

	public CashFlowSummaryRunnableParam() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param cashFlowSummaryService
	 * @param lockTable 加锁的表名称
	 * @param billType 现金流类型
	 * @param queryLimitCount while循环，每次分页查询的条数
	 */
	public CashFlowSummaryRunnableParam(
			ICashFlowSummaryService cashFlowSummaryService, String lockTable,
			int billType, int queryLimitCount) {
		super();
		this.cashFlowSummaryService = cashFlowSummaryService;
		this.lockTable = lockTable;
		this.billType = billType;
		this.queryLimitCount = queryLimitCount;
	}

	public ICashFlowSummaryService getCashFlowSummaryService() {
		return cashFlowSummaryService;
	}

	public void setCashFlowSummaryService(
			ICashFlowSummaryService cashFlowSummaryService) {
		this.cashFlowSummaryService = cashFlowSummaryService;
	}

	public String getLockTable() {
		return lockTable;
	}

	/** 设置“加锁的表名称” */
	public void setLockTable(String lockTable) {
		this.lockTable = lockTable;
	}

	public int getBillType() {
		return billType;
	}

	/** 设置“现金流类型” */
	public void setBillType(int billType) {
		this.billType = billType;
	}

	public int getQueryLimitCount() {
		return queryLimitCount;
	}

	/** 设置“while循环，每次分页查询的条数” */
	public void setQueryLimitCount(int queryLimitCount) {
		this.queryLimitCount = queryLimitCount;
	}

}
