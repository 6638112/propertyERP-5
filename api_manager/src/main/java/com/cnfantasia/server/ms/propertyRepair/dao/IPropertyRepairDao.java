package com.cnfantasia.server.ms.propertyRepair.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyRepair.dao.IPropertyRepairBaseDao;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;
import com.cnfantasia.server.ms.propertyRepair.entity.*;

public interface IPropertyRepairDao extends IPropertyRepairBaseDao {

	List<PropertyRepairConfig> listRepairConfig(Map<String, Object> paramMap);

	List<PropertyRepairType> getPropertyRepairTypeListByGbId(String gbId);

	List<PropertyRepairerEntity> listRepairer(Map<String, Object> paramMap);

	List<PropertyRepairTypeEntity> listRepairerTypeByOmsUser(Map<String, Object> paramMap);

	List<PropertyManagement> listPropertyManagementByOmsUser(OmsUser currUser);

	int listRepairerTypeByOmsUser_forCount(Map<String, Object> paramMap);

	int listRepairer_forCount(Map<String, Object> paramMap);

	int listRepair_forCount(Map<String, Object> paramMap);

	PropertyRepairEntity select_PropertyRepair_byId(String id);

	List<PropertyRepairEntity> listRepair(Map<String, Object> paramMap);

	double getStarLevelForRepairer(BigInteger id);

	int listRepairConfig_forCount(Map<String, Object> paramMap);

	List<PropertyRepairTypeEntity> getPropertyRepairTypeEntityListByPMId(BigInteger pmId);

	int qryIsOpenRepaireServiceByRoomId(BigInteger roomId);

	List<PropertyRepairTypeEntity> select_prTypeList_by_roomId(BigInteger roomId);

	List<DredgeTypeEntity> getDredgeTypeByCondition(Map<String, Object> map);
	
}
