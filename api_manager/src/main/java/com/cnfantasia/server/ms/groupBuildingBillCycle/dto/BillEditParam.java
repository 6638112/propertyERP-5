package com.cnfantasia.server.ms.groupBuildingBillCycle.dto;

import java.math.BigInteger;

/**
 * 小区缴费管理-收费账单配置-缴费周期：新增账单/修改账单
 * 
 * @author liyulin
 * @version 1.0 2017年2月8日 上午10:01:16
 */
public class BillEditParam {

	/** t_group_building_bill_cycle表f_id */
	private BigInteger id;
	private BigInteger gbId;
	/** 账单名称 */
	private String billName;
	/** 包含费用项 */
	private String[] feeType;
	private String billMonth;
	private String billMonthStartEdit;
	private String billMonthEndEdit;
	private String billMonthStart;
	private String billMonthEnd;
	/** 账单缴费开始时间 */
	private String billPayStart;
	/** 账单缴费结束时间 */
	private String billPayEnd;
	private BigInteger payBillTypeId;
	private BigInteger payBillTimeCfgId;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String[] getFeeType() {
		return feeType;
	}

	public void setFeeType(String[] feeType) {
		this.feeType = feeType;
	}

	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public String getBillMonthStartEdit() {
		return billMonthStartEdit;
	}

	public void setBillMonthStartEdit(String billMonthStartEdit) {
		this.billMonthStartEdit = billMonthStartEdit;
	}

	public String getBillMonthEndEdit() {
		return billMonthEndEdit;
	}

	public void setBillMonthEndEdit(String billMonthEndEdit) {
		this.billMonthEndEdit = billMonthEndEdit;
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

	public String getBillPayStart() {
		return billPayStart;
	}

	public void setBillPayStart(String billPayStart) {
		this.billPayStart = billPayStart;
	}

	public String getBillPayEnd() {
		return billPayEnd;
	}

	public void setBillPayEnd(String billPayEnd) {
		this.billPayEnd = billPayEnd;
	}

	public BigInteger getPayBillTypeId() {
		return payBillTypeId;
	}

	public void setPayBillTypeId(BigInteger payBillTypeId) {
		this.payBillTypeId = payBillTypeId;
	}

	public BigInteger getPayBillTimeCfgId() {
		return payBillTimeCfgId;
	}

	public void setPayBillTimeCfgId(BigInteger payBillTimeCfgId) {
		this.payBillTimeCfgId = payBillTimeCfgId;
	}
}
