package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;

/**
 * 申请提款的参数信息
* Filename:    RevenueApplyParam.java
* @version:    1.0.0
* Create at:   2015年11月20日 下午3:11:44
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月20日       shiyl             1.0             1.0 Version
 */
public class RevenueApplyParam {
	private BigInteger miniRoleId;
	private Integer miniRoleType;
	private Integer goalType;
	private String goalStartTime;
	private String goalEndTime;
	private Double totalAmount;
	
	public RevenueApplyParam(BigInteger miniRoleId, Integer miniRoleType, Integer goalType, String goalStartTime, String goalEndTime, Double totalAmount) {
		super();
		this.miniRoleId = miniRoleId;
		this.miniRoleType = miniRoleType;
		this.goalType = goalType;
		this.goalStartTime = goalStartTime;
		this.goalEndTime = goalEndTime;
		this.totalAmount = totalAmount;
	}
	public RevenueApplyParam(BigInteger miniRoleId, Integer miniRoleType, Integer goalType, String goalStartTime, String goalEndTime) {
		super();
		this.miniRoleId = miniRoleId;
		this.miniRoleType = miniRoleType;
		this.goalType = goalType;
		this.goalStartTime = goalStartTime;
		this.goalEndTime = goalEndTime;
	}
	
	public String getGoalStartTime() {
		return goalStartTime;
	}
	public void setGoalStartTime(String goalStartTime) {
		this.goalStartTime = goalStartTime;
	}
	public String getGoalEndTime() {
		return goalEndTime;
	}
	public void setGoalEndTime(String goalEndTime) {
		this.goalEndTime = goalEndTime;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigInteger getMiniRoleId() {
		return miniRoleId;
	}
	public void setMiniRoleId(BigInteger miniRoleId) {
		this.miniRoleId = miniRoleId;
	}
	public Integer getMiniRoleType() {
		return miniRoleType;
	}
	public void setMiniRoleType(Integer miniRoleType) {
		this.miniRoleType = miniRoleType;
	}
	public Integer getGoalType() {
		return goalType;
	}
	public void setGoalType(Integer goalType) {
		this.goalType = goalType;
	}
	
}
