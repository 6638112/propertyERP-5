package com.cnfantasia.server.ms.channelPartner.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.channelPartner.service.IChannelPartnerBaseService;
import com.cnfantasia.server.ms.channelPartner.entity.ChannelPartnerDetailEntity;
import com.cnfantasia.server.ms.channelPartner.entity.ChannelPartnerEntity;
import com.cnfantasia.server.ms.channelPartner.entity.GroupBuildingRegisterEntiy;

public interface IChannelPartnerService extends IChannelPartnerBaseService {

	public int getChannelPartnerCount(Map paramMap);

	List<ChannelPartnerEntity> getChannelPartnerList_forPage(Map paramMap);

	ChannelPartnerDetailEntity select_cp_detail(Map<String, Object> paramMap);

	List<GroupBuildingRegisterEntiy> select_gbr_by_cprId(String cprId);
}
