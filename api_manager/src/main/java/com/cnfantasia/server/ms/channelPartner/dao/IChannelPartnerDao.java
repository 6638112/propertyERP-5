package com.cnfantasia.server.ms.channelPartner.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.channelPartner.dao.IChannelPartnerBaseDao;
import com.cnfantasia.server.ms.channelPartner.entity.ChannelPartnerDetailEntity;
import com.cnfantasia.server.ms.channelPartner.entity.ChannelPartnerEntity;
import com.cnfantasia.server.ms.channelPartner.entity.GroupBuildingRegisterEntiy;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanySimple;

public interface IChannelPartnerDao extends IChannelPartnerBaseDao {

	int selectChannelPartnerCount(Map paramMap);

	List<ChannelPartnerEntity> getChannelPartnerList_forPage(Map paramMap);

	ChannelPartnerDetailEntity select_cp_detail(Map<String, Object> paramMap);

	List<GroupBuildingRegisterEntiy> select_gbr_by_cprId(String cprId);

	int getGroupBuildingCount(Map<String, Object> paramMap);

	List<Map<Long, String>> getChannelPartnerList_ByCpnameAndType(String cpName, String channelType);

	/**
	 * 查询 代理合作的物业公司信息
	 * @param paramMap
	 * @return
	 */
	List<PropertyCompanySimple> selectRelevanceCompanyList(Map<String, Object> paramMap);
}
