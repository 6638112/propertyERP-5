package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigInteger;

public class BillCycleAndTypeName {
	private BigInteger gbbcId; //账期ID
	private String billMonthStart; //账期的开始日期
	private String billMonthEnd;//账期的结束日期
	private String pbtName;//账期类型名称
	private String gbName;//小区名称 
	
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public BigInteger getGbbcId() {
		return gbbcId;
	}
	public void setGbbcId(BigInteger gbbcId) {
		this.gbbcId = gbbcId;
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
	public String getPbtName() {
		return pbtName;
	}
	public void setPbtName(String pbtName) {
		this.pbtName = pbtName;
	}

	@Override
	public String toString() {
		return "BillCycleAndTypeName [gbbcId=" + gbbcId + ", billMonthStart=" + billMonthStart + ", billMonthEnd="
				+ billMonthEnd + ", pbtName=" + pbtName + ", gbName=" + gbName + "]";
	}
}
