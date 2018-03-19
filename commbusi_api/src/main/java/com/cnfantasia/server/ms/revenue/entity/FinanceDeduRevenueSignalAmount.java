package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;
import java.util.Date;

import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;

public class FinanceDeduRevenueSignalAmount extends RevenueSignalAmount {
	
	private static final long serialVersionUID = 8286419699457467399L;

	private String gbName; //小区
	
	private String building; //楼栋
	
	private String unitName; //单元
	
	private String room; //房间号
	
	private Date month; //账单月份，即抵扣月份
	
	private BigInteger channelPartner;//代理商ID
	
	private String financeType;//交易类别/渠道
	
	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public String getTkStatusStr() {
		if(RevenueDict.TkStatus.Doing.equals(getTkStatus())) {
			return "申请中";
		} else if(RevenueDict.TkStatus.Finished.equals(getTkStatus())) {
			return "已结算";
		} else {
			return "未结算";
		} 
	}
	
	public Date getGoalRevTimeDate() {
		String goalRevTime = super.getGoalRevTime();
		return DateUtils.strToDateTime(goalRevTime);
	}

	public BigInteger getChannelPartner() {
		return channelPartner;
	}

	public void setChannelPartner(BigInteger channelPartner) {
		this.channelPartner = channelPartner;
	}

	public String getFinanceType() {
		return financeType;
	}

	public void setFinanceType(String financeType) {
		this.financeType = financeType;
	}
	
}
