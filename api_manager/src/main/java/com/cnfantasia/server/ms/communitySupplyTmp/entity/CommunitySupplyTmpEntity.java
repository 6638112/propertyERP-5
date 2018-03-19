package com.cnfantasia.server.ms.communitySupplyTmp.entity;

import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;

public class CommunitySupplyTmpEntity extends CommunitySupplyTmp {
	
	private static final long serialVersionUID = 1L;
	
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

	/**
	 * 商家类别
	 */
	String supplyType;

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}
}
