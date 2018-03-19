package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigInteger;

public class BcInfoByPPDto {

	private BigInteger ppbciId;
	/** 管理处 */
	private String pmName;
	/** 小区id */
	private BigInteger gbId;
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
	/** 业主托收银行卡号 */
	private String bankAcount;
	/** 业主开卡银行 */
	private String bankName;
	/** 托管状态（1：未选；2：已选） */
	private String bcStatus;

	public BigInteger getPpbciId() {
		return ppbciId;
	}

	public void setPpbciId(BigInteger ppbciId) {
		this.ppbciId = ppbciId;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

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

	public String getBankAcount() {
		return bankAcount;
	}

	public void setBankAcount(String bankAcount) {
		this.bankAcount = bankAcount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBcStatus() {
		return bcStatus;
	}

	public void setBcStatus(String bcStatus) {
		this.bcStatus = bcStatus;
	}

}
