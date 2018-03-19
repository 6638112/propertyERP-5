package com.cnfantasia.server.ms.groupBuilding.entity;

import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

import java.math.BigInteger;

public class GroupBuildingSimpleEntity extends GroupBuilding {
	private static final long serialVersionUID = -690922444566861546L;
	/**
	 * 所在区
	 */
	private String addressBlockName;
	
	/**
	 * 所在区id
	 */
	private BigInteger blockId;

	/**
	 * 所在市
	 */
	private String addressCityName;

	/**
	 * 所在省
	 */
	private String addressProvinceName;

	/**
	 * 所属物业公司
	 * */
	private String propertyCompanyName;

	/**
	 * 所属管理处
	 * */
	private String propertyManagementName;

	//是否物业对接了
	private Boolean hasSoftwareDock = false;

	/** 是否开启定期收费配置={"0" */
	private Integer fixedFeeStatus;
	/** 是否开启抄表收费配置={"0" */
	private Integer meterFeeStatus;
	/** 是否开启临时收费配置={"0" */
	private Integer tempFeeStatus;
	private Integer openBankcollection;

	private int isPayToPc;//是否直接付款到到物业：0不是，1是

	/**
	 * 消息是否已推送
	 */
	private String isPushed = "no";
	private BigInteger cityId;

	public int getIsPayToPc() {
		return isPayToPc;
	}

	public void setIsPayToPc(int isPayToPc) {
		this.isPayToPc = isPayToPc;
	}

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

	public String getPropertyCompanyName() {
		return propertyCompanyName;
	}

	public void setPropertyCompanyName(String propertyCompanyName) {
		this.propertyCompanyName = propertyCompanyName;
	}

	public String getPropertyManagementName() {
		return propertyManagementName;
	}

	public void setPropertyManagementName(String propertyManagementName) {
		this.propertyManagementName = propertyManagementName;
	}

	public Boolean getHasSoftwareDock() {
		return hasSoftwareDock;
	}

	public void setHasSoftwareDock(Boolean hasSoftwareDock) {
		this.hasSoftwareDock = hasSoftwareDock;
	}

	public Integer getFixedFeeStatus() {
		return fixedFeeStatus;
	}

	public void setFixedFeeStatus(Integer fixedFeeStatus) {
		this.fixedFeeStatus = fixedFeeStatus;
	}

	public Integer getMeterFeeStatus() {
		return meterFeeStatus;
	}

	public void setMeterFeeStatus(Integer meterFeeStatus) {
		this.meterFeeStatus = meterFeeStatus;
	}

	public Integer getTempFeeStatus() {
		return tempFeeStatus;
	}

	public void setTempFeeStatus(Integer tempFeeStatus) {
		this.tempFeeStatus = tempFeeStatus;
	}

	public BigInteger getCityId() {
		return cityId;
	}

	public void setCityId(BigInteger cityId) {
		this.cityId = cityId;
	}

	public Integer getOpenBankcollection() {
		return openBankcollection;
	}

	public void setOpenBankcollection(Integer openBankcollection) {
		this.openBankcollection = openBankcollection;
	}

	public BigInteger getBlockId() {
		return blockId;
	}

	public void setBlockId(BigInteger blockId) {
		this.blockId = blockId;
	}
	
}
