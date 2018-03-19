package com.cnfantasia.server.ms.communitySupply.entity;


/**
 * 店铺认领 查看实体
 * 
 * @author wenfq 2014-02-11
 * 
 */
public class CommunitySupplyBelongViewEntity extends CommunitySupplyBelongEntity {
	String abName;//区
	String acName;//市
	String apName;//省

	public String getAbName() {
		return abName;
	}

	public void setAbName(String abName) {
		this.abName = abName;
	}

	public String getAcName() {
		return acName;
	}

	public void setAcName(String acName) {
		this.acName = acName;
	}

	public String getApName() {
		return apName;
	}

	public void setApName(String apName) {
		this.apName = apName;
	}
}
