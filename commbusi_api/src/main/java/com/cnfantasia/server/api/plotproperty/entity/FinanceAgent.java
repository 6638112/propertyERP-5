package com.cnfantasia.server.api.plotproperty.entity;

import java.io.Serializable;

public class FinanceAgent implements Serializable {
	
	private static final long serialVersionUID = 4138183298723821624L;

	private Long propertyCompanyId;
	
	private Long channelPartnerId;

	public Long getPropertyCompanyId() {
		return propertyCompanyId;
	}

	public void setPropertyCompanyId(Long propertyCompanyId) {
		this.propertyCompanyId = propertyCompanyId;
	}

	public Long getChannelPartnerId() {
		return channelPartnerId;
	}

	public void setChannelPartnerId(Long channelPartnerId) {
		this.channelPartnerId = channelPartnerId;
	}

}
