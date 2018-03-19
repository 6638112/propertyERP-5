package com.cnfantasia.server.ms.communitySupply.entity;

import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

public class CommunitySupplyDetailEntity extends CommunitySupply {
	private CommunitySupplyType communitySupplyType;

	public CommunitySupplyType getCommunitySupplyType() {
		return communitySupplyType;
	}

	public void setCommunitySupplyType(CommunitySupplyType communitySupplyType) {
		this.communitySupplyType = communitySupplyType;
	}
}
