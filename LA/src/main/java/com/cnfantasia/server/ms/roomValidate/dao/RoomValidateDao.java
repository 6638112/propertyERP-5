package com.cnfantasia.server.ms.roomValidate.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.roomValidate.dao.RoomValidateBaseDao;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;
import com.cnfantasia.server.ms.pub.pagination.TotalCountGetter;

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
		return r1 + r2;
	}

	@Override
	public int auditNotPassUpdate(Map<String, Object> paramMap) {
		return sqlSession.update("roomValidate.update_roomValidate_forAuditNotPass", paramMap);
	}
}
