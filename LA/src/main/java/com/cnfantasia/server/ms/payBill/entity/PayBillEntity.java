package com.cnfantasia.server.ms.payBill.entity;

import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

public class PayBillEntity extends PayBill {

	private static final long serialVersionUID = -4324406873627559241L;

	/**
	 * 小区名称
	 */
	private String groupBuildingName;

	/** 缴费周期开始时间 */
	private Integer payPeriodStart;

	/** 缴费周期结束时间 */
	private Integer payPeriodEnd;

	/**
	 * 楼栋号
	 */
	private String buildingName;

	/**
	 * 房间-单元号
	 */
	private String realRoomUnitName;

	/**
	 * 房间-房间号
	 */
	private String realRoomNum;

	/**
	 * 合同号
	 */
	private String contractNum;

	/**
	 * 业主姓名
	 */
	private String propertyProprietorName;

	/**
	 * 业主身份证号
	 */
	private String identifyNo;

	/**
	 * 状态更新人
	 */
	private String updateUserName;

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getGroupBuildingName() {
		return groupBuildingName;
	}

	public void setGroupBuildingName(String groupBuildingName) {
		this.groupBuildingName = groupBuildingName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRealRoomUnitName() {
		return realRoomUnitName;
	}

	public void setRealRoomUnitName(String realRoomUnitName) {
		this.realRoomUnitName = realRoomUnitName;
	}

	public String getRealRoomNum() {
		return realRoomNum;
	}

	public void setRealRoomNum(String realRoomNum) {
		this.realRoomNum = realRoomNum;
	}

	public String getPropertyProprietorName() {
		return propertyProprietorName;
	}

	public void setPropertyProprietorName(String propertyProprietorName) {
		this.propertyProprietorName = propertyProprietorName;
	}

	public String getIdentifyNo() {
		return identifyNo;
	}

	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
	}

	public Integer getPayPeriodStart() {
		return payPeriodStart;
	}

	public void setPayPeriodStart(Integer payPeriodStart) {
		this.payPeriodStart = payPeriodStart;
	}

	public Integer getPayPeriodEnd() {
		return payPeriodEnd;
	}

	public void setPayPeriodEnd(Integer payPeriodEnd) {
		this.payPeriodEnd = payPeriodEnd;
	}

	/**
	 * 从f_month中获取年信息
	 * 
	 * getMonth()获得的日期格式是：yyyy年MM月
	 * 
	 * @return
	 */
	public String getYearFromMonthfiled() {
		return getMonth().substring(0, 4);
	}

	/**
	 * 从f_month中获取月信息
	 * <p>
	 * getMonth()获得的日期格式是：yyyy年MM月
	 * 
	 * 注意：如果是单月的话，还不能带08这样，必须转成8，因为mysql的month()函数返回的是整型
	 * 
	 * @return
	 */
	public String getMonthFromMonthfiled() {
		if (getMonth().substring(5, 7).startsWith("0")) {
			return getMonth().substring(6, 7);//小于10月的，要去掉0
		} else {
			return getMonth().substring(5, 7);//大于等于10月的
		}
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append(this.getGroupBuildingName());
		sbf.append(this.getBuildingName());
		sbf.append(this.getRealRoomUnitName());
		sbf.append(this.getRealRoomNum());
		sbf.append(this.getPropertyProprietorName());
		return sbf.toString();
	}
}
