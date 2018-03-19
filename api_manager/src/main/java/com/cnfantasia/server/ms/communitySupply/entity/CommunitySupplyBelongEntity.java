package com.cnfantasia.server.ms.communitySupply.entity;

import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

public class CommunitySupplyBelongEntity extends CommunitySupplyBelong {

	private CommunitySupplyType communitySupplyType;

	private CommunitySupply communitySupply;

	public CommunitySupplyType getCommunitySupplyType() {
		return communitySupplyType;
	}

	public void setCommunitySupplyType(CommunitySupplyType communitySupplyType) {
		this.communitySupplyType = communitySupplyType;
	}

	public CommunitySupply getCommunitySupply() {
		return communitySupply;
	}

	public void setCommunitySupply(CommunitySupply communitySupply) {
		this.communitySupply = communitySupply;
	}

}
