package com.cnfantasia.server.ms.bankCollection.entity;

public class BCHistoryReq {

	private String no;
	private String gbName;
	private String bankOrg;
	private String billMonthStart;
	private String billMonthEnd;
	private String billName;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getBankOrg() {
		return bankOrg;
	}

	public void setBankOrg(String bankOrg) {
		this.bankOrg = bankOrg;
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

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

}
