package com.cnfantasia.server.api.bankCollection.entity;

public class RebackRecordDto {
	/** 小区 */
	private String gbName;
	/** 楼栋 */
	private String buildingName;
	/** 单元 */
	private String unitName;
	/** 房号 */
	private String roomName;
	/** 业主姓名 */
	private String ppName;
	/** 回盘信息 */
	private String rebackContent;
	/** 回盘时间 */
	private String rebackTime;
	/** 结果 */
	private Integer rtStatus;

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getPpName() {
		return ppName;
	}

	public void setPpName(String ppName) {
		this.ppName = ppName;
	}

	public String getRebackContent() {
		return rebackContent;
	}

	public void setRebackContent(String rebackContent) {
		this.rebackContent = rebackContent;
	}

	public String getRebackTime() {
		return rebackTime;
	}

	public void setRebackTime(String rebackTime) {
		this.rebackTime = rebackTime;
	}

	public Integer getRtStatus() {
		return rtStatus;
	}

	public void setRtStatus(Integer rtStatus) {
		this.rtStatus = rtStatus;
	}

}
