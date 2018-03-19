package com.cnfantasia.server.ms.channelPartner.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.channelPartner.dao.ChannelPartnerBaseDao;
import com.cnfantasia.server.ms.channelPartner.entity.ChannelPartnerDetailEntity;
import com.cnfantasia.server.ms.channelPartner.entity.ChannelPartnerEntity;
import com.cnfantasia.server.ms.channelPartner.entity.GroupBuildingRegisterEntiy;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanySimple;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;

public class ChannelPartnerDao extends ChannelPartnerBaseDao implements IChannelPartnerDao {


	@Override
	public int selectChannelPartnerCount(Map paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "channelPartner.select_cp_list", paramMap);
	}

	@Override
	public List<ChannelPartnerEntity> getChannelPartnerList_forPage(Map paramMap) {
		return sqlSession.selectList("channelPartner.select_cp_list", paramMap);
	}

	@Override
	public ChannelPartnerDetailEntity select_cp_detail(Map<String, Object> paramMap) {
		return sqlSession.selectOne("channelPartner.select_cp_detail", paramMap);
	}

	@Override
	public List<GroupBuildingRegisterEntiy> select_gbr_by_cprId(String cprId) {
		return sqlSession.selectList("channelPartner.select_gbr_by_cprId", cprId);
	}

	@Override
	public int getGroupBuildingCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("channelPartner.select_groupbuilding_with_gbname_and_blockname", paramMap);
	}

	@Override
	public List<Map<Long, String>> getChannelPartnerList_ByCpnameAndType(String cpName, String channelType) {
		Map<String, String> parmpMap = new HashMap<String, String>();
		parmpMap.put("cpName", cpName);
		parmpMap.put("channelType", channelType);
		return sqlSession.selectList("channelPartner.select_channel_partner_list_ByCpnameAndType", parmpMap);
	}

	@Override
	public List<PropertyCompanySimple> selectRelevanceCompanyList(Map<String, Object> paramMap) {
		return sqlSession.selectList("channelPartner.select_cp_detail_check", paramMap);
	}
}
