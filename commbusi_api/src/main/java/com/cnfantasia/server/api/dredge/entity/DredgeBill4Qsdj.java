package com.cnfantasia.server.api.dredge.entity;

import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;

public class DredgeBill4Qsdj extends DredgeBill {
	
	String cityName;
	String fullAddress;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
}
