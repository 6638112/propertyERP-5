package com.cnfantasia.server.api.propertyRepair.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.propertyRepair.entity.PropertyRepair4List;
import com.cnfantasia.server.api.propertyRepair.entity.PropertyRepairEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyRepair.dao.IPropertyRepairBaseDao;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;

public interface IPropertyRepairDao extends IPropertyRepairBaseDao {

	List<PropertyRepairType> getPropertyRepairTypeList_ByGbId(Map<String, Object> paramMap);

	Map<String, Object> qryIsRepairEnable(Map<String, Object> paramMap);

	List<PropertyRepair4List> getRepairList(BigInteger userId, BigInteger gbId, int type, PageModel pageModel);

	PropertyRepairEntity getRepairDetail(BigInteger prId);

}
