package com.cnfantasia.server.ms.roomValidate.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.roomValidate.service.RoomValidateBaseService;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;
import com.cnfantasia.server.ms.roomValidate.dao.IRoomValidateDao;

public class RoomValidateService extends RoomValidateBaseService implements IRoomValidateService {
	IRoomValidateDao roomValidateDao;

	public void setRoomValidateDao(IRoomValidateDao roomValidateDao) {
		this.roomValidateDao = roomValidateDao;
	}

	@Override
	public int getRVList_byUserId_forCount(Map<String, Object> paramMap) {
		return roomValidateDao.getRVList_byUserId_forCount(paramMap);
	}

	@Override
	public List<PropertyProprietorEntity> getRVList_byUserId_forPage(Map<String, Object> paramMap) {
		return roomValidateDao.getRVList_byUserId_forPage(paramMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map select_rm_forAudit(String rvId) {
		Map resMap =  roomValidateDao.select_rm_forAudit(rvId);
		if(resMap.get("planPropertyAmount")!=null){
			resMap.put("planPropertyAmount", PriceUtil.div100(resMap.get("planPropertyAmount").toString()));
		}
		return resMap;
	}

	@Override
	public int auditPassUpdate(Map<String, Object> paramMap) {
		return roomValidateDao.auditPassUpdate(paramMap);
	}

	@Override
	public int auditNotPassUpdate(Map<String, Object> paramMap) {
		return roomValidateDao.auditNotPassUpdate(paramMap);
	}

	@Override
	public int changeUserHasTRoomWhenAudit(Map<String, Object> paramMap) {
		return roomValidateDao.updateUserHasTRoomWhenAudit(paramMap);
	}

	@Override
	public int updateGroupBuildingName(Map<String, Object> paramMap) {
		return roomValidateDao.updateGroupBuildingName(paramMap);
	}

	@Override
	public boolean qryGbSignStatus(BigInteger gbId) {
		return roomValidateDao.selectGbSignStatus(gbId);
	}


}
