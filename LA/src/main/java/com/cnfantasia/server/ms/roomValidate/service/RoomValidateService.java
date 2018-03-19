package com.cnfantasia.server.ms.roomValidate.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domainbase.roomValidate.service.RoomValidateBaseService;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;
import com.cnfantasia.server.ms.roomValidate.dao.IRoomValidateDao;

public class RoomValidateService extends RoomValidateBaseService implements IRoomValidateService {
	IRoomValidateDao roomValidateDao;

	private IUuidManager uuidManager;
	private IDualDao dualDao;

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	public void setRoomValidateDao(IRoomValidateDao roomValidateDao) {
		this.roomValidateDao = roomValidateDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	@Override
	public int getRVList_byUserId_forCount(Map<String, Object> paramMap) {
		return roomValidateDao.getRVList_byUserId_forCount(paramMap);
	}

	@Override
	public List<PropertyProprietorEntity> getRVList_byUserId_forPage(Map<String, Object> paramMap) {
		return roomValidateDao.getRVList_byUserId_forPage(paramMap);
	}

	@Override
	public Map select_rm_forAudit(String rvId) {
		return roomValidateDao.select_rm_forAudit(rvId);
	}

	@Override
	public int auditPassUpdate(Map<String, Object> paramMap) {
		return roomValidateDao.auditPassUpdate(paramMap);
	}

	@Override
	public int auditNotPassUpdate(Map<String, Object> paramMap) {
		return roomValidateDao.auditNotPassUpdate(paramMap);
	}

}
