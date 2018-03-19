package com.cnfantasia.server.ms.revenue.entity;

/**
 * 结算中心搜索参数
 * 
 * @author liyulin
 * @version 1.0 2016年7月27日 下午3:45:29
 */
public class RevenueSearchParam {

	/** 发起开始时间 */
	private String date01;
	/** 发起结束时间 */
	private String date02;
	/** 提款单号 */
	private String tkNo;
	/** 结算状态 */
	private String tkStatus;
	/** 申请对象名称 */
	private String roleName;
	/** 申请对象类型 */
	private String goalType;
	/** 结算项目 */
	private String roleType;

	public RevenueSearchParam() {
		super();
	}

	public RevenueSearchParam(String date01, String date02, String tkNo, String tkStatus, String roleName, String goalType, String roleType) {
		super();
		this.date01 = date01;
		this.date02 = date02;
		this.tkNo = tkNo;
		this.tkStatus = tkStatus;
		this.roleName = roleName;
		this.goalType = goalType;
		this.roleType = roleType;
	}

	public final String getDate01() {
		return date01;
	}

	public final void setDate01(String date01) {
		this.date01 = date01;
	}

	public final String getDate02() {
		return date02;
	}

	public final void setDate02(String date02) {
		this.date02 = date02;
	}

	public final String getTkNo() {
		return tkNo;
	}

	public final void setTkNo(String tkNo) {
		this.tkNo = tkNo;
	}

	public final String getTkStatus() {
		return tkStatus;
	}

	public final void setTkStatus(String tkStatus) {
		this.tkStatus = tkStatus;
	}

	public final String getRoleName() {
		return roleName;
	}

	public final void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public final String getGoalType() {
		return goalType;
	}

	public final void setGoalType(String goalType) {
		this.goalType = goalType;
	}

	public final String getRoleType() {
		return roleType;
	}

	public final void setRoleType(String roleType) {
		this.roleType = roleType;
	}

}
