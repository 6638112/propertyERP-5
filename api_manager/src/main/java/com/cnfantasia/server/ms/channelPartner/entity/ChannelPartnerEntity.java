package com.cnfantasia.server.ms.channelPartner.entity;

import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;

public class ChannelPartnerEntity extends ChannelPartner {
	String omsUserName;
	
	String inviteName;//邀请人名称，如胡文敏

	public String getOmsUserName() {
		return omsUserName;
	}

	public String getInviteName() {
		return inviteName;
	}

	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
	}

	public void setOmsUserName(String omsUserName) {
		this.omsUserName = omsUserName;
	}

}
