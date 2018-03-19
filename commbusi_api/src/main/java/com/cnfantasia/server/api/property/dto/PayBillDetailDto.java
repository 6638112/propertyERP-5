package com.cnfantasia.server.api.property.dto;

import java.math.BigDecimal;

/**
 * 支付详情账单明细
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午10:49:24
 */
public class PayBillDetailDto {

	/** 账单名称 */
	private String billName;
	private String mrName;
	/** 账单金额（单位：元） */
	private String billAmount;
	/** 账单金额（单位：元） */
	private BigDecimal amountBigDecimal;
	/** 抄水表开始值 */
	private String startValue;
	/** 抄水表结束值 */
	private String endValue;
	/** 收费模式类型 */
	private Integer feeType;
	/** 账单开始月份 */
	private String billMonthStart;
	/** 账单截止月份 */
	private String billMonthEnd;
	/** 缴费月数 */
	private Integer billMonthSize;

	public String getStartValue() {
		return startValue;
	}

	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}

	public String getEndValue() {
		return endValue;
	}

	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}

	public String getBillMonthStart() {
		return billMonthStart;
	}

	public void setBillMonthStart(String billMonthStart) {
		this.billMonthStart = billMonthStart;
	}

	public String getBillMonthEnd() {
		return billMonthEnd;
	}

	public void setBillMonthEnd(String billMonthEnd) {
		this.billMonthEnd = billMonthEnd;
	}

	public Integer getBillMonthSize() {
		return billMonthSize;
	}

	public void setBillMonthSize(Integer billMonthSize) {
		this.billMonthSize = billMonthSize;
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	
	public String getMrName() {
		return mrName;
	}

	public void setMrName(String mrName) {
		this.mrName = mrName;
	}

	public BigDecimal getAmountBigDecimal() {
		return amountBigDecimal;
	}

	public void setAmountBigDecimal(BigDecimal amountBigDecimal) {
		this.amountBigDecimal = amountBigDecimal;
	}
	
}
