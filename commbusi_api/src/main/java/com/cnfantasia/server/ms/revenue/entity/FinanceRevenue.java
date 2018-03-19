package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.plotproperty.entity.FinanceBuyEntity;

public class FinanceRevenue extends FinanceBuyEntity {
	
	private static final long serialVersionUID = -6617323433142322754L;

	private String propertyCompanyName;
	
	private String channelPartnerName;
	
	private String carNum;
	
	private BigInteger gbId;
	
	private BigInteger propManagementId; //管理处ID

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getPropertyCompanyName() {
		return propertyCompanyName;
	}

	public void setPropertyCompanyName(String propertyCompanyName) {
		this.propertyCompanyName = propertyCompanyName;
	}

	public String getChannelPartnerName() {
		return channelPartnerName;
	}

	public void setChannelPartnerName(String channelPartnerName) {
		this.channelPartnerName = channelPartnerName;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public BigInteger getPropManagementId() {
		return propManagementId;
	}

	public void setPropManagementId(BigInteger propManagementId) {
		this.propManagementId = propManagementId;
	}
	
}
