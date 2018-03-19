package com.cnfantasia.server.ms.communitySupply.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupply.dao.CommunitySupplyBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyBelongEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyBelongViewEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyDetailEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpViewEntity;
import com.cnfantasia.server.ms.communitySupply.entity.PropertySuggestCommsupplyEntity;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyCompanyEntity;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;

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

	@Override
	public List<CommunitySupplyTmpEntity> getCommunitySupplyTmpList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("communitySupply.select_csTmpList_forAdmin", paramMap);
	}

	@Override
	public int getCommunitySupplyTmpList_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "communitySupply.select_csTmpList_forAdmin", paramMap);
	}

	@Override
	public CommunitySupplyTmpViewEntity getTmpDetailById(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return sqlSession.selectOne("communitySupply.select_csTmp_byId", id);
	}

	@Override
	public int getCommunitySupplyBelongList_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "communitySupply.select_csBelongList_forAdmin", paramMap);
	}

	@Override
	public List<CommunitySupplyBelongEntity> getCommunitySupplyBelongList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("communitySupply.select_csBelongList_forAdmin", paramMap);
	}

	@Override
	public CommunitySupplyBelongViewEntity getBelongDetailById(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return sqlSession.selectOne("communitySupply.select_csBelong_byId", id);
	}

	@Override
	public int getPropertySuggestCommsupply_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "communitySupply.select_pscList", paramMap);
	}

	@Override
	public List<PropertySuggestCommsupplyEntity> getPropertySuggestCommsupply_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("communitySupply.select_pscList", paramMap);
	}

	@Override
	public CommunitySupplyDetailEntity getCommunitySupplyDetailById(String id) {
		return sqlSession.selectOne("communitySupply.select_communitySupply_detail", id);
	}

	@Override
	public PropertySuggestCommsupplyEntity getPropertySuggestCommsupplyDetail(String id) {
		return sqlSession.selectOne("communitySupply.select_PSC_detail", id);
	}

	@Override
	public CommunitySupplyCompanyEntity getCommunitySupplyCompanyDetail(Map<String, Object> paramMap) {
		return sqlSession.selectOne("communitySupplyCompany.select_communitySupply_withCompany", paramMap);
	}

	@Override
	public int validateCSBelong_forCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("communitySupply.validateCSBelong_forCount", paramMap);
	}
}
