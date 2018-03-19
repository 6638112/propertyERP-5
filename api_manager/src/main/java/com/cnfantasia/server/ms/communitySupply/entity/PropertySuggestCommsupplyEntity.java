package com.cnfantasia.server.ms.communitySupply.entity;

import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertySuggestCommsupply.entity.PropertySuggestCommsupply;

public class PropertySuggestCommsupplyEntity extends PropertySuggestCommsupply {
	private CommunitySupply communitySupply;
	private PropertyCompany propertyCompany;
	private CommunitySupplyType communitySupplyType;

	public CommunitySupply getCommunitySupply() {
		return communitySupply;
	}

	public CommunitySupplyType getCommunitySupplyType() {
		return communitySupplyType;
	}

	public void setCommunitySupplyType(CommunitySupplyType communitySupplyType) {
		this.communitySupplyType = communitySupplyType;
	}

	public void setCommunitySupply(CommunitySupply communitySupply) {
		this.communitySupply = communitySupply;
	}

	public PropertyCompany getPropertyCompany() {
		return propertyCompany;
	}

	public void setPropertyCompany(PropertyCompany propertyCompany) {
		this.propertyCompany = propertyCompany;
	}

}
