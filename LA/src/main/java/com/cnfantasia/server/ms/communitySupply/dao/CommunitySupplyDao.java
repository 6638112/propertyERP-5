package com.cnfantasia.server.ms.communitySupply.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupply.dao.CommunitySupplyBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyEntity;
import com.cnfantasia.server.ms.pub.pagination.TotalCountGetter;

public class CommunitySupplyDao extends CommunitySupplyBaseDao implements ICommunitySupplyDao {

	@Override
	public List<CommunitySupplyEntity> select_csList_byOmsUser(Map<String, Object> paramMap) {
		return sqlSession.selectList("communitySupply.select_csList_byOmsUser", paramMap);
	}

	@Override
	public List<CommunitySupplyType> getCommunitySupplyTypeByCondition(Map<String, Object> paramMap) {
		return sqlSession.selectList("communitySupply.select_communitySupplyType", paramMap);
	}

	@Override
	public int deletePropertySuggestCommsupply_byCSId(String csId, String pcId, String gbId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("csId", csId);
		paramMap.put("pcId", pcId);
		paramMap.put("gbId", gbId);
		return sqlSession.update("communitySupply.deletePropertySuggestCommsupply_byCSId", paramMap);
	}

	@Override
	public int getCommunitySupply_byUserId_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "communitySupply.select_csList_byOmsUser", paramMap);
	}

	@Override
	public List<CommunitySupplyEntity> getCommunitySupplyList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("communitySupply.select_csList_byOmsUser", paramMap);
	}
}
