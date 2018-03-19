package com.cnfantasia.server.ms.communitySupplyCompany.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyCompany.entity.CommunitySupplyCompany;

/**
 * 商户（有店铺属性）实体
 * 
 * @author wenfq
 * 
 */
public class CommunitySupplyCompanyEntity extends CommunitySupplyCompany {
	List<CommunitySupply> communitySupplyList;

	public List<CommunitySupply> getCommunitySupplyList() {
		return communitySupplyList;
	}

	public void setCommunitySupplyList(List<CommunitySupply> communitySupplyList) {
		this.communitySupplyList = communitySupplyList;
	}

}
