package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 银行出盘明细打印（按房间编号）
 * 
 * @author liyulin
 * @version 1.0 2017年5月22日 上午11:39:06
 */
public class BcPrintDetail {
	/** 小区id */
	private BigInteger gbId;
	/**
	 * 小区名称
	 */
	private String gbName;
	/** 楼栋id */
	private BigInteger bId;
	/**
	 * 楼栋名称
	 */
	private String bName;
	/** 房屋编号 */
	private String roomNo;
	/** 客户姓名 */
	private String ppName;
	/** 银行账号 */
	private String bankAccount;
	/** 出盘金额（单位：元） */
	private BigDecimal outAmount;
	/** 划款金额（单位：元） */
	private BigDecimal dealAmount;
	/** 划款滞纳金（单位：元） */
	private BigDecimal dealLateAmount;

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public BigInteger getbId() {
		return bId;
	}

	public void setbId(BigInteger bId) {
		this.bId = bId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getPpName() {
		return ppName;
	}

	public void setPpName(String ppName) {
		this.ppName = ppName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public BigDecimal getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(BigDecimal outAmount) {
		this.outAmount = outAmount;
	}

	public BigDecimal getDealAmount() {
		//return dealAmount;
		return this.outAmount; //目前 dealAmount 与  outAmount一样
	}

	public void setDealAmount(BigDecimal dealAmount) {
		this.dealAmount = dealAmount;
	}

	public BigDecimal getDealLateAmount() {
		return dealLateAmount;
	}

	public void setDealLateAmount(BigDecimal dealLateAmount) {
		this.dealLateAmount = dealLateAmount;
	}

}
