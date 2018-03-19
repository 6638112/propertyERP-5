package com.cnfantasia.server.ms.revenue.entity;

import java.util.Date;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.PayUtils;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;

public class CarRevenueSignalAmount extends RevenueSignalAmount {
	
	private static final long serialVersionUID = 8286419699457467399L;

	private String gbName;
	
	private String building;
	
	private String unitName;
	
	private String room;
	
	private String carNum;
	
	private Date payTime;
	
	private Integer isnotStatus; //车辆类型 
	
	private Integer payNum; //缴费时长
	
	private String payStartDate;//月卡车缴费开始有效期
	
	private String payEndDate;//月卡车缴费截至有效期
	/**是否需要发票=={"0":"不需要","1";"需要"}*/
	private Integer needBill; 
	
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

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getIsnotStatus() {
		return isnotStatus;
	}

	public void setIsnotStatus(Integer isnotStatus) {
		this.isnotStatus = isnotStatus;
	}

	public Integer getPayNum() {
		return payNum;
	}

	public void setPayNum(Integer payNum) {
		this.payNum = payNum;
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
	
	public String getPayMethodStr() {
		return PayUtils.getPayMethodStr(getPayMethod());
	}

	public String getPayStartDate() {
		return payStartDate;
	}

	public void setPayStartDate(String payStartDate) {
		this.payStartDate = payStartDate;
	}

	public String getPayEndDate() {
		return payEndDate;
	}

	public void setPayEndDate(String payEndDate) {
		this.payEndDate = payEndDate;
	}

	public Integer getNeedBill() {
		return needBill;
	}

	public void setNeedBill(Integer needBill) {
		this.needBill = needBill;
	}
	
}
