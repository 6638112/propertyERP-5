package com.cnfantasia.server.ms.communitySupply.entity;

import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;

public class CommunitySupplyTmpEntity extends CommunitySupplyTmp {

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
