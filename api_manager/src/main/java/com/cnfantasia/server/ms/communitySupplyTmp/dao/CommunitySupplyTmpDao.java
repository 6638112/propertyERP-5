package com.cnfantasia.server.ms.communitySupplyTmp.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;
import com.cnfantasia.server.domainbase.communitySupplyTmp.dao.CommunitySupplyTmpBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;
import com.cnfantasia.server.ms.communitySupplyTmp.entity.CommunitySupplyTmpEntity;

public class CommunitySupplyTmpDao extends CommunitySupplyTmpBaseDao implements ICommunitySupplyTmpDao {

	@Override
	public List<CommunitySupplyTmpEntity> getCommunitySupplyTmpList_byUserId_forPage(int curPageIndex,
			int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("communitySupplyTmp.select_csList_byOmsUser", paramMap);
	}

	@Override
	public int getCommunitySupplyTmp_byUserId_forCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("communitySupplyTmp.select_csCount_byOmsUser", paramMap);
	}

	@Override
	public CommunitySupplyTmpEntity getCommunitySupplyTmp_byId(BigInteger id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return sqlSession.selectOne("communitySupplyTmp.select_csTmp_byId", id);
	}

	@Override
	public int delGroupBuildingHasTCommunitySupplyTmp_byId(BigInteger supplyTmpId, BigInteger sysDelUser) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyTmpId", supplyTmpId);
		paramMap.put("sysDelUser", sysDelUser);
		return sqlSession.update("communitySupplyTmp.del_GroupBuildingHasTCommunitySupplyTmp_bySupplyTmpId", paramMap);
	}

	@Override
	public List<String> getGBHasCSIsExists(Map<String, Object> paramMap, boolean isTmp) {
		if(isTmp){
			return sqlSession.selectList("communitySupplyTmp.select_GBHasTCSTMP_bySupplyTmpName", paramMap);
		}
		return sqlSession.selectList("communitySupplyTmp.select_GBHasTCS_bySupplyName", paramMap);
	}

	@Override
	public int delCommunitySupplyTmpContect_byId(BigInteger supplyTmpId, BigInteger sysDelUser) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyTmpId", supplyTmpId);
		paramMap.put("sysDelUser", sysDelUser);
		return sqlSession.update("communitySupplyTmp.del_CommunitySupplyContect_bySupplyTmpId", paramMap);
	}

	@Override
	public int updatePicBatchForCSEdit(List<CommunitySupplyPic> picList) {
		return sqlSession.update("communitySupplyTmp.update_communitySupplyPic_Batch_forCSEdit", picList);
	}

	@Override
	public int updateCSTmpForEdit(CommunitySupplyTmp tmp) {
		return sqlSession.update("communitySupplyTmp.update_communitySupplyTmp_forEdit", tmp);
	}

	
}
