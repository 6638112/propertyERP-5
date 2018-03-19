package com.cnfantasia.server.ms.roomValidate.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.roomValidate.dao.IRoomValidateBaseDao;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;

public interface IRoomValidateDao extends IRoomValidateBaseDao {

	int getRVList_byUserId_forCount(Map<String, Object> paramMap);

	List<PropertyProprietorEntity> getRVList_byUserId_forPage(Map<String, Object> paramMap);

	Map select_rm_forAudit(String rvId);

	int auditPassUpdate(Map<String, Object> paramMap);

	int auditNotPassUpdate(Map<String, Object> paramMap);

}
