package com.cnfantasia.server.api.propertyRepair.service;

import com.cnfantasia.server.api.propertyRepair.entity.PropertyRepair4List;
import com.cnfantasia.server.api.propertyRepair.entity.PropertyRepairEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepair.service.IPropertyRepairBaseService;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;
import com.propertySoftwareDock.base.entity.RepairPushEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface IPropertyRepairService extends IPropertyRepairBaseService {

	public void addRepair(PropertyRepair propertyRepair, List<RequestFileEntity> picList);

	List<PropertyRepairType> getPropertyRepairTypeList_ByGbId(Map<String, Object> paramMap);

	Map<String, Object> qryIsRepairEnable(Map<String, Object> paramMap);

	List<PropertyRepair4List> getRepairList(BigInteger userId, int type, PageModel pageModel);

	List<Map<String, Object>> propertyRepair2MapList(BigInteger userId, List<PropertyRepair4List> propertyRepairList);

	PropertyRepairEntity qryRepairDetail(String repairId);

	Object propertyRepair2Map(PropertyRepairEntity propertyRepairEntity);

	public void pushRepairToSoftware(RepairPushEntity entity);

	public int getRepairDetailExpireSecond(RepairPushEntity entity);

}
