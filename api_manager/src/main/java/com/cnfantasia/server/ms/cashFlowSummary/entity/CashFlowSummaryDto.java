package com.cnfantasia.server.ms.cashFlowSummary.entity;

import com.cnfantasia.server.domainbase.cashFlowSummary.entity.CashFlowSummary;

/**
 * 现金流量汇总表查询Dto
 * 
 * @author liyulin
 * @version 1.0 2016年7月14日 上午11:05:57
 */
public class CashFlowSummaryDto extends CashFlowSummary {

	/** 认证状态 */
	private Integer verifyState;
	/** 订单数量 */
	private Integer orderNum;
	/** 物业公司 */
	private String pcName;
	/** 代理商 */
	private String cpCompanyName;

	public CashFlowSummaryDto() {
		super();
	}

	public CashFlowSummaryDto(Integer verifyState, Integer orderNum, String pcName, String cpCompanyName) {
		super();
		this.verifyState = verifyState;
		this.orderNum = orderNum;
		this.pcName = pcName;
		this.cpCompanyName = cpCompanyName;
	}

	public Integer getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(Integer verifyState) {
		this.verifyState = verifyState;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getCpCompanyName() {
		return cpCompanyName;
	}

	public void setCpCompanyName(String cpCompanyName) {
		this.cpCompanyName = cpCompanyName;
	}

}
