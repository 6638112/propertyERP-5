package com.cnfantasia.server.ms.communitySupply.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;

public class CommunitySupplyEntity extends CommunitySupply {
	/**
	 * 小区名称
	 */
	String gbName;

	/**
	 * 小区Id
	 */
	BigInteger gbId;

	/**
	 * 物业公司Id
	 */
	BigInteger pcId;
	/**
	 * 商家类别
	 */
	String supplyType;

	/**
	 * 推荐时间
	 */
	String suggestTime;

	/**
	 * 物业推荐表中存储的小区Id
	 */
	BigInteger pscGbId;

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public BigInteger getPcId() {
		return pcId;
	}

	public void setPcId(BigInteger pcId) {
		this.pcId = pcId;
	}

	public BigInteger getPscGbId() {
		return pscGbId;
	}

	public void setPscGbId(BigInteger pscGbId) {
		this.pscGbId = pscGbId;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getSuggestTime() {
		return suggestTime;
	}

	public void setSuggestTime(String suggestTime) {
		this.suggestTime = suggestTime;
	}

}
