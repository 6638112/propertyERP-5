package com.cnfantasia.server.api.propertyRepair.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.propertyRepair.entity.PropertyRepair4List;
import com.cnfantasia.server.api.propertyRepair.entity.PropertyRepairEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.propertyRepair.dao.PropertyRepairBaseDao;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;

public class PropertyRepairDao extends PropertyRepairBaseDao implements IPropertyRepairDao {

	@Override
	public List<PropertyRepairType> getPropertyRepairTypeList_ByGbId(Map<String, Object> paramMap) {
		return sqlSession.selectList("propertyRepair.select_PropertyRepairType_List_ByGbId", paramMap);
	}

	@Override
	public Map<String, Object> qryIsRepairEnable(Map<String, Object> paramMap) {
		return sqlSession.selectOne("propertyRepair.select_Is_Repair_Enable", paramMap);
	}

	@Override
	public List<PropertyRepair4List> getRepairList(BigInteger userId, BigInteger gbId, int type, PageModel pageModel) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("stateType", type);
		paramMap.put("gbId", gbId);

		String pageSqlKey = "propertyRepair.select_my_repair_list_withPage";
		String countSqlKey = "propertyRepair.select_my_repair_list_count";
		return PageQueryUtil.selectPageList(sqlSession, paramMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public PropertyRepairEntity getRepairDetail(BigInteger prId) {
		return sqlSession.selectOne("propertyRepair.select_my_repair_detail", prId);
	}

}
