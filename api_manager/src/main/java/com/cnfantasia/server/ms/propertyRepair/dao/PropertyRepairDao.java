package com.cnfantasia.server.ms.propertyRepair.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyRepair.dao.PropertyRepairBaseDao;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;
import com.cnfantasia.server.ms.propertyRepair.entity.*;

public class PropertyRepairDao extends PropertyRepairBaseDao implements IPropertyRepairDao {

	@Override
	public int listRepairConfig_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "propertyRepair.select_PropertyRepairConfig_List_byOmsUser", paramMap);
	}
	
	@Override
	public List<PropertyRepairConfig> listRepairConfig(Map<String, Object> paramMap) {
		return sqlSession.selectList("propertyRepair.select_PropertyRepairConfig_List_byOmsUser", paramMap);
	}

	@Override
	public List<PropertyRepairType> getPropertyRepairTypeListByGbId(String gbId) {
		return sqlSession.selectList("propertyRepair.select_PropertyRepairType_List_ByGbId", gbId);
	}

	@Override
	public List<PropertyRepairerEntity> listRepairer(Map<String, Object> paramMap) {
		return sqlSession.selectList("propertyRepair.select_PropertyRepairer_List_byOmsUser", paramMap);
	}

	@Override
	public List<PropertyRepairTypeEntity> listRepairerTypeByOmsUser(Map<String, Object> paramMap) {
		return sqlSession.selectList("propertyRepair.select_RepairerType_List_byOmsUser", paramMap);
	}

	@Override
	public List<PropertyManagement> listPropertyManagementByOmsUser(OmsUser currUser) {
		return sqlSession.selectList("propertyRepair.select_PropertyManagement_List_byOmsUser", currUser);
	}

	@Override
	public int listRepairerTypeByOmsUser_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "propertyRepair.select_RepairerType_List_byOmsUser", paramMap);
	}

	@Override
	public int listRepairer_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "propertyRepair.select_PropertyRepairer_List_byOmsUser", paramMap);
	}

	@Override
	public int listRepair_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "propertyRepair.select_PropertyRepair_List_byOmsUser", paramMap);
	}

	@Override
	public List<PropertyRepairEntity> listRepair(Map<String, Object> paramMap) {
		return sqlSession.selectList("propertyRepair.select_PropertyRepair_List_byOmsUser", paramMap);
	}

	@Override
	public PropertyRepairEntity select_PropertyRepair_byId(String id) {
		return sqlSession.selectOne("propertyRepair.select_PropertyRepair_byId", id);
	}

	@Override
	public double getStarLevelForRepairer(BigInteger id) {
		return sqlSession.selectOne("propertyRepair.select_avg_level_for_property_repairer", id);
	}

	@Override
	public List<PropertyRepairTypeEntity> getPropertyRepairTypeEntityListByPMId(BigInteger pmId) {
		return sqlSession.selectList("propertyRepair.select_prTypeList_by_pmId", pmId);
	}

	@Override
	public int qryIsOpenRepaireServiceByRoomId(BigInteger roomId) {
		return sqlSession.selectOne("propertyRepair.qry_isOpenRepaireService_by_roomId", roomId);
	}

	@Override
	public List<PropertyRepairTypeEntity> select_prTypeList_by_roomId(BigInteger roomId) {
		return sqlSession.selectList("propertyRepair.select_prTypeList_by_roomId", roomId);
	}

	@Override
	public List<DredgeTypeEntity> getDredgeTypeByCondition(Map<String, Object> paramMap) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, false);}
		return sqlSession.selectList("propertyRepair.select_dredgeType",paramMap);
	}
	
}
