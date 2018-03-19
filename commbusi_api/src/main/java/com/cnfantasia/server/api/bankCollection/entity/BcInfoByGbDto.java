package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigInteger;

public class BcInfoByGbDto {

	private BigInteger gbId;
	/** 小区名称 */
	private String gbName;
	/** 管理处名称 */
	private String pmName;
	/** 托管状态（1：未选；2：已选） */
	private String bcStatus;

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

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getBcStatus() {
		return bcStatus;
	}

	public void setBcStatus(String bcStatus) {
		this.bcStatus = bcStatus;
	}

}
