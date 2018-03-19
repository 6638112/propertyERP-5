package com.cnfantasia.server.ms.revenue.entity;


/**
 * 结算中心批量导出参数
 * 
 * @author liyulin
 * @version 1.0 2016年7月27日 下午2:09:13
 */
public class RevenueBatchParam {

	private String goalType;
	private String applyId;
	private String miniRoleId;
	private String miniRoleType;
	private String applyNo;
	private String easBillNumbers;

	public RevenueBatchParam() {
		super();
	}

	public RevenueBatchParam(String goalType, String applyId, String miniRoleId, String miniRoleType, String applyNo, String easBillNumbers) {
		super();
		this.goalType = goalType;
		this.applyId = applyId;
		this.miniRoleId = miniRoleId;
		this.miniRoleType = miniRoleType;
		this.applyNo = applyNo;
		this.easBillNumbers = easBillNumbers;
	}

	public String getGoalType() {
		return goalType;
	}

	public void setGoalType(String goalType) {
		this.goalType = goalType;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getMiniRoleId() {
		return miniRoleId;
	}

	public void setMiniRoleId(String miniRoleId) {
		this.miniRoleId = miniRoleId;
	}

	public String getMiniRoleType() {
		return miniRoleType;
	}

	public void setMiniRoleType(String miniRoleType) {
		this.miniRoleType = miniRoleType;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getEasBillNumbers() {
		return easBillNumbers;
	}

	public void setEasBillNumbers(String easBillNumbers) {
		this.easBillNumbers = easBillNumbers;
	}

}
