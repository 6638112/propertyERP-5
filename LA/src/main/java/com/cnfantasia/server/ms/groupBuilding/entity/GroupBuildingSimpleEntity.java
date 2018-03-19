package com.cnfantasia.server.ms.groupBuilding.entity;

import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

public class GroupBuildingSimpleEntity extends GroupBuilding{
	/**
	 * 所在区
	 */
	private String addressBlockName;

	/**
	 * 所在市
	 */
	private String addressCityName;

	/**
	 * 所在省
	 */
	private String addressProvinceName;

	/**
	 * 消息是否已推送
	 */
	private String isPushed = "no";

	public String getIsPushed() {
		return isPushed;
	}

	public void setIsPushed(String isPushed) {
		this.isPushed = isPushed;
	}

	public String getAddressCityName() {
		return addressCityName;
	}

	public void setAddressCityName(String addressCityName) {
		this.addressCityName = addressCityName;
	}

	public String getAddressProvinceName() {
		return addressProvinceName;
	}

	public void setAddressProvinceName(String addressProvince) {
		this.addressProvinceName = addressProvince;
	}

	public String getAddressBlockName() {
		return addressBlockName;
	}

	public void setAddressBlockName(String adressBlockName) {
		this.addressBlockName = adressBlockName;
	}
}
