package com.cnfantasia.server.ms.payBill.entity;

/**
 * 为导入账单准备的一些关联信息
 * 
 * @author wenfq 2014-11-21
 * 
 */
public class OtherInfo4ImportPayBill {

	/**
	 * 账单月份
	 */
	private String payBillMonth;

	/**
	 * 小区名称
	 */
	private String groupBuildingName;

	/**
	 * 楼栋名称
	 */
	private String buildingName;

	/**
	 * 单元号（realRoom）
	 */
	private String rrUnitName;

	/**
	 * 房间号
	 */
	private String rrName;


	/**
	 * realRoom.id
	 */
	private long rrId;

	/**
	 * 业主姓名
	 */
	private String ppName;

	/**
	 * 业主ID
	 */
	private long ppId;

	public String getPayBillMonth() {
		return payBillMonth;
	}

	public void setPayBillMonth(String payBillMonth) {
		this.payBillMonth = payBillMonth;
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

	public String getRrUnitName() {
		return rrUnitName;
	}

	public void setRrUnitName(String rrUnitName) {
		this.rrUnitName = rrUnitName;
	}

	public long getRrId() {
		return rrId;
	}

	public void setRrId(long rrId) {
		this.rrId = rrId;
	}

	public String getPpName() {
		return ppName;
	}

	public void setPpName(String ppName) {
		this.ppName = ppName;
	}

	public long getPpId() {
		return ppId;
	}

	public void setPpId(long ppId) {
		this.ppId = ppId;
	}

	public String getRrName() {
		return rrName;
	}

	public void setRrName(String rrName) {
		this.rrName = rrName;
	}
}
