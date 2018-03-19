package com.cnfantasia.server.ms.roomValidate.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.roomValidate.dao.RoomValidateBaseDao;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;

public class RoomValidateDao extends RoomValidateBaseDao implements IRoomValidateDao {

	@Override
	public int getRVList_byUserId_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "roomValidate.select_rvList_ByOmsUserId", paramMap);
	}

	@Override
	public List<PropertyProprietorEntity> getRVList_byUserId_forPage(Map<String, Object> paramMap) {
		return sqlSession.selectList("roomValidate.select_rvList_ByOmsUserId", paramMap);
	}

	@Override
	public Map select_rm_forAudit(String rvId) {
		return (Map) sqlSession.selectList("roomValidate.select_rm_forAudit", rvId).get(0);
	}

	@Override
	public int auditPassUpdate(Map<String, Object> paramMap) {
		int r1 = sqlSession.update("roomValidate.update_roomValidate_forAuditPass", paramMap);
		int r2 = sqlSession.update("roomValidate.update_realRoom_forAudit", paramMap);
		int r3 = sqlSession.update("roomValidate.update_roomValidate_checkStatus", paramMap);
		return r1 + r2 + r3;
	}


	@Override
	public int auditNotPassUpdate(Map<String, Object> paramMap) {
		return sqlSession.update("roomValidate.update_roomValidate_forAuditNotPass", paramMap);
	}

	@Override
	public int updateUserHasTRoomWhenAudit(Map<String, Object> paramMap) {
		return sqlSession.update("roomValidate.update_userHasTRoom_whenAudit", paramMap);
	}

	@Override
	public int updateGroupBuildingName(Map<String, Object> paramMap) {
		return sqlSession.update("roomValidate.update_roomValidate_name", paramMap);
	}

	@Override
	public boolean selectGbSignStatus(BigInteger gbId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbId", gbId);
		Boolean res= sqlSession.selectOne("roomValidate.select_GbSignStatus",paramMap);
		return res==null?false:res;
	}
}
