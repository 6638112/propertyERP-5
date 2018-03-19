package com.cnfantasia.server.api.paybill.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class PayBillCycle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigInteger billTypeId;
	private BigInteger billTimeCfgId;
	private String billMonth;
	private String billMonthStart;
	private String billMonthEnd;
	private Integer paytimeType;
	private BigInteger gbId;
	private String payStart;
	private String payEnd;
	
	public BigInteger getBillTypeId() {
		return billTypeId;
	}
	public void setBillTypeId(BigInteger billTypeId) {
		this.billTypeId = billTypeId;
	}
	public BigInteger getBillTimeCfgId() {
		return billTimeCfgId;
	}
	public void setBillTimeCfgId(BigInteger billTimeCfgId) {
		this.billTimeCfgId = billTimeCfgId;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
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
	public Integer getPaytimeType() {
		return paytimeType;
	}
	public void setPaytimeType(Integer paytimeType) {
		this.paytimeType = paytimeType;
	}
	public BigInteger getGbId() {
		return gbId;
	}
	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPayStart() {
		return payStart;
	}
	public void setPayStart(String payStart) {
		this.payStart = payStart;
	}
	public String getPayEnd() {
		return payEnd;
	}
	public void setPayEnd(String payEnd) {
		this.payEnd = payEnd;
	}
	
}
