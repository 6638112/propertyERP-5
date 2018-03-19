package com.cnfantasia.server.ms.communitySupplyCompany.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domainbase.communitySupplyCompany.dao.CommunitySupplyCompanyBaseDao;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyCompanyEntity;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyEditEntity;

public class CommunitySupplyCompanyDao extends CommunitySupplyCompanyBaseDao implements ICommunitySupplyCompanyDao {

	@Override
	public List<CommunitySupplyCompanyEntity> getCommunitySupplyCompanyList_byUserId_forPage(int curPageIndex,
			int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("communitySupplyCompany.select_communitySupply_withCompany", paramMap);
	}

	@Override
	public int getCommunitySupplyCompany_byUserId_forCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("communitySupplyCompany.select_count_withCompany", paramMap);
	}

	@Override
	public List<CommunitySupplyEditEntity> getCommunitySupplyEditEntityList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("communitySupplyCompany.select_csEditList_forOmsUser", paramMap);
	}

	@Override
	public int getCommunitySupplyEditEntityList_byUserId_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "communitySupplyCompany.select_csEditList_forOmsUser", paramMap);
	}

	@Override
	public CommunitySupplyEditEntity getCommunitySupplyEditEntity_byId(BigInteger id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return sqlSession.selectOne("communitySupplyCompany.select_csEdit_byId", id);
	}


	
}
