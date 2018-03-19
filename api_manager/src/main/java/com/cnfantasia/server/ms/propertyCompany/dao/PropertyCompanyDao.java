package com.cnfantasia.server.ms.propertyCompany.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;
import com.cnfantasia.server.domainbase.propertyCompany.dao.PropertyCompanyBaseDao;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyEntity;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyWorkbenchEntity;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;

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

	@Override
	public PropertyCompanyWorkbenchEntity getPropertyCompanyWorkbench(BigInteger userId) {
		return sqlSession.selectOne("propertyCompany.select_Workbench_forPC", userId);
	}

	@Override
	public int queryPropertyCompanyForApplyCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "propertyCompany.select_propertyCompany_forApply", paramMap);
	}

	@Override
	public List<PropertyCompanyEntity> queryPropertyCompanyForApplyList(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("propertyCompany.select_propertyCompany_forApply", paramMap);
	}

	@Override
	public int selectValidPropertyCompanyByPcName(String pcName) {
		return sqlSession.selectOne("propertyCompany.selectValidPropertyCompanyByPcName", pcName);
	}

	@Override
	public List<PropertyCompanyEntity> selectpcMsg(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		if(paramMap.get("editType").equals("1")) {//物业公司
			return sqlSession.selectList("propertyCompany.selectpropertycompanyedit_company", paramMap);
		} else if (paramMap.get("editType").equals("2")) {//管理处
			return sqlSession.selectList("propertyCompany.selectpropertycompanyedit_management", paramMap);
		} else {
			return sqlSession.selectList("propertyCompany.selectpropertycompanyedit", paramMap);
		}
		
	}

	@Override
	public int selectEditCount(Map<String, Object> paramMap) {
		if(paramMap.get("editType").equals("1")) {//物业公司
			return sqlSession.selectOne("propertyCompany.selectpropertycompanyedit_companycount", paramMap);
		} else if (paramMap.get("editType").equals("2")) {//管理处
			return sqlSession.selectOne("propertyCompany.selectpropertycompanyedit_managementcount", paramMap);
		} else {
			return sqlSession.selectOne("propertyCompany.selectpropertycompanyeditcount", paramMap);
		}
	}

	@Override
	public List<ChannelPartner> selectChannelPartnerByPcName(String pcName) {
		return sqlSession.selectList("propertyCompany.select_channel_partner_ByPcName", pcName);
	}

	@Override
	public List<Map<String, Object>> select_gbList_ByPCId(BigInteger pcId) {
		return sqlSession.selectList("propertyCompany.select_gbList_ByPCId", pcId);
	}
}
