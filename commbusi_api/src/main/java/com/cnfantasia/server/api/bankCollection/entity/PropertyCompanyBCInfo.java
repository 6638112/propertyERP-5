package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.entity.PropertyCompanyBankCollectionInfo;

public class PropertyCompanyBCInfo extends PropertyCompanyBankCollectionInfo{
	List<BigInteger> gbIdList;//托收所包含的小区ID列表
	
	public List<BigInteger> getGbIdList() {
		return gbIdList;
	}
	public void setGbIdList(List<BigInteger> gbIdList) {
		this.gbIdList = gbIdList;
	}
 	
}
