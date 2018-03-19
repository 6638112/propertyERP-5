package com.cnfantasia.server.api.revenueApplyPush.bean;

import java.math.BigDecimal;

/**
 * 付款单分录
 * */
public class PaymentEntryEntity {
	private BigDecimal actPayAmt;
	private BigDecimal actPayLocAmt;
	private String entryCostCenter;
	private String expenseType;
	private String outBgItem;
	private String remark;
	public BigDecimal getActPayAmt() {
		return actPayAmt;
	}
	public void setActPayAmt(BigDecimal actPayAmt) {
		this.actPayAmt = actPayAmt;
	}
	public BigDecimal getActPayLocAmt() {
		return actPayLocAmt;
	}
	public void setActPayLocAmt(BigDecimal actPayLocAmt) {
		this.actPayLocAmt = actPayLocAmt;
	}
	public String getEntryCostCenter() {
		return entryCostCenter;
	}
	public void setEntryCostCenter(String entryCostCenter) {
		this.entryCostCenter = entryCostCenter;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public String getOutBgItem() {
		return outBgItem;
	}
	public void setOutBgItem(String outBgItem) {
		this.outBgItem = outBgItem;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
