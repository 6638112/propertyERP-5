package com.cnfantasia.server.ms.revenue.entity;

import java.util.Date;

import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;

public class CarDeduRevenueSignalAmount extends RevenueSignalAmount {
	
	private static final long serialVersionUID = 8286419699457467399L;
	
	private String carNum; //车牌

	private String gbName; //小区
	
//	private Date month; //账单月份，即抵扣月份
	
	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

//	public Date getMonth() {
//		return month;
//	}
//
//	public void setMonth(Date month) {
//		this.month = month;
//	}

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

}
