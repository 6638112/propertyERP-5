package com.cnfantasia.server.ms.roomValidate.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.roomValidate.service.IRoomValidateBaseService;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;

public interface IRoomValidateService extends IRoomValidateBaseService {

	int getRVList_byUserId_forCount(Map<String, Object> paramMap);

	List<PropertyProprietorEntity> getRVList_byUserId_forPage(Map<String, Object> paramMap);

	Map select_rm_forAudit(String rvId);

	int auditPassUpdate(Map<String, Object> paramMap);

	int auditNotPassUpdate(Map<String, Object> paramMap);
	
	int changeUserHasTRoomWhenAudit(Map<String, Object> paramMap);

	int updateGroupBuildingName(Map<String, Object> paramMap);
	
	/**
	 * 查询小区的签约状态
	 * @param gbId
	 * @return
	 */
	public boolean qryGbSignStatus(BigInteger gbId);
}
