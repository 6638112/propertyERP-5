package com.cnfantasia.server.ms.payBill.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.cnfantasia.server.common.utils.PriceUtil;

public class MsgForPaybill {

	private String gbName;
	private String mobile;
	/** 账单月份 */
	private Date billMothDate;
	/** 账单截止日 */
	private Date billEndDate;
	/** 房间号 */
	private String roomNum;
	/** 账单名称 */
	private String billName;
	/** 账单金额 */
	private Long amount;
	/** 账单开始时间（周期） */
	private String billMonthStart;
	/** 账单结束时间（周期） */
	private String billMonthEnd;

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getBillMothDate() {
		return billMothDate;
	}

	public void setBillMothDate(Date billMothDate) {
		this.billMothDate = billMothDate;
	}

	public Date getBillEndDate() {
		return billEndDate;
	}

	public void setBillEndDate(Date billEndDate) {
		this.billEndDate = billEndDate;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public BigDecimal getAmount() {
		return PriceUtil.div100(amount);
	}

	public void setAmount(Long amount) {
		this.amount = amount;
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

}
