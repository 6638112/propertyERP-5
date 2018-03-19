package com.cnfantasia.server.ms.propertyRepair.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.propertyRepair.entity.*;
import org.springframework.web.multipart.MultipartFile;

import com.cnfantasia.server.api.dredge.entity.ProcessRecord;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity.DredgeBillHasProcessRecording;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepair.service.IPropertyRepairBaseService;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;

public interface IPropertyRepairService extends IPropertyRepairBaseService {

	int openRepairService(String gbId, String serviceTel);

	List<PropertyRepairType> getPropertyRepairTypeListByGbId(String gbId);

	void saveRepairer(String gbId, String propertyManagementFId, BigInteger repairTypeId, String repairerId, String repairerName, String repairerTel,
			MultipartFile headimgurl, String idNumber, MultipartFile idimgurl1, MultipartFile idimgurl2);

	List<PropertyRepairerEntity> listRepairer(Map<String, Object> paramMap);

	List<PropertyRepairTypeEntity> listRepairerTypeByOmsUser(Map<String, Object> paramMap);

	List<PropertyManagement> listPropertyManagementByOmsUser(OmsUser currUser);

	int listRepairerTypeByOmsUser_forCount(Map<String, Object> paramMap);

	int listRepairer_forCount(Map<String, Object> paramMap);

	int listRepair_forCount(Map<String, Object> paramMap);

	List<PropertyRepairEntity> listRepair(Map<String, Object> paramMap);

	PropertyRepairEntity select_PropertyRepair_byId(String id);

	void assignPropertyRepair(PropertyRepair propertyRepair);

	void closePropertyRepair(PropertyRepair propertyRepair);

	void addPushMessage(String title, String content, String pushId, BigInteger desUserId, Long msgType);

	double getStarLevelForRepairer(BigInteger id);

	List<PropertyRepairConfig> listRepairConfig(Map<String, Object> paramMap);

	int listRepairConfig_forCount(Map<String, Object> paramMap);

	List<PropertyRepairTypeEntity> getPropertyRepairTypeEntityListByPMId(BigInteger pmId);

	int qryIsOpenRepaireServiceByRoomId(BigInteger roomId);

	List<PropertyRepairTypeEntity> select_prTypeList_by_roomId(BigInteger roomId);

	List<ProcessRecord> getProcessRecordList(List<DredgeBillHasProcessRecording> prList);

	List<DredgeTypeEntity> getDredgeTypeByCondition(Map<String, Object> map);
	
}
