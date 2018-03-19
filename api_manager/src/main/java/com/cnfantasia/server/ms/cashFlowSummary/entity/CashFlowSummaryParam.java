package com.cnfantasia.server.ms.cashFlowSummary.entity;

/**
 * 现金流量汇总表查询参数
 * 
 * @author liyulin
 * @version 1.0 2016年7月14日 上午11:12:57
 */
public class CashFlowSummaryParam extends CashFlowSummaryDto {

	/** 业务开始时间 */
	private String billStartTime;
	/** 业务结束时间 */
	private String billEndTime;
	/** 汇总方式：1、按小区；2、按门牌 */
	private String summaryType;

	public CashFlowSummaryParam() {
		super();
	}

	public CashFlowSummaryParam(String billStartTime, String billEndTime, String summaryType) {
		super();
		this.billStartTime = billStartTime;
		this.billEndTime = billEndTime;
		this.summaryType = summaryType;
	}

	public String getBillStartTime() {
		return billStartTime;
	}

	public void setBillStartTime(String billStartTime) {
		this.billStartTime = billStartTime;
	}

	public String getBillEndTime() {
		return billEndTime;
	}

	public void setBillEndTime(String billEndTime) {
		this.billEndTime = billEndTime;
	}

	public String getSummaryType() {
		return summaryType;
	}

	public void setSummaryType(String summaryType) {
		this.summaryType = summaryType;
	}

}
