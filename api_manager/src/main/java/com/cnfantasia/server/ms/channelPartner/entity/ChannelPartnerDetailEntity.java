package com.cnfantasia.server.ms.channelPartner.entity;

import java.util.List;

public class ChannelPartnerDetailEntity extends ChannelPartnerEntity {

	List<ChannelPartnerRecommendEntity> channelPartnerRecommendList;

	public List<ChannelPartnerRecommendEntity> getChannelPartnerRecommendList() {
		return channelPartnerRecommendList;
	}

	public void setChannelPartnerRecommendList(List<ChannelPartnerRecommendEntity> channelPartnerRecommendList) {
		this.channelPartnerRecommendList = channelPartnerRecommendList;
	}

}
