package com.cnfantasia.server.ms.propertyCompany.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.propertyCompany.dao.PropertyCompanyBaseDao;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyEntity;
import com.cnfantasia.server.ms.pub.pagination.TotalCountGetter;

public class PropertyCompanyDao extends PropertyCompanyBaseDao implements IPropertyCompanyDao {

	/**
	 * 根据登录用户查询(物业公司)信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCompanyEntity selectPropertyCompanyByOmsUserId(java.math.BigInteger id) {
		return sqlSession.selectOne("propertyCompany.select_pcInfo_ByOmsUserId", id);
	}

	@Override
	public List<GroupBuildingSimpleEntity> select_gbList_ByOmsUserId(BigInteger id) {
		return sqlSession.selectList("propertyCompany.select_gbList_ByOmsUserId", id);
	}

	@Override
	public int getPCList4admin_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "propertyCompany.select_pcList_forAdmin", paramMap);
	}

	@Override
	public List<PropertyCompanyEntity> getPCList4admin_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("propertyCompany.select_pcList_forAdmin", paramMap);
	}

	@Override
	public List<Map<String, Object>> select_gbrList_ByPCId(BigInteger pcId) {
		return sqlSession.selectList("propertyCompany.select_gbrList_ByPCId", pcId);
	}
}
