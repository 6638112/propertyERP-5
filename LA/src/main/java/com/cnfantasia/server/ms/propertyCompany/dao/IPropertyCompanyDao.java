package com.cnfantasia.server.ms.propertyCompany.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.propertyCompany.dao.IPropertyCompanyBaseDao;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyEntity;

public interface IPropertyCompanyDao extends IPropertyCompanyBaseDao {
	/**
	 * 根据omsUser.id 查询(物业公司表)信息
	 * 
	 * @param id
	 * @return
	 */
	public PropertyCompanyEntity selectPropertyCompanyByOmsUserId(java.math.BigInteger id);

	List<GroupBuildingSimpleEntity> select_gbList_ByOmsUserId(BigInteger id);

	public int getPCList4admin_forCount(Map<String, Object> paramMap);

	public List<PropertyCompanyEntity> getPCList4admin_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	/**
	 * 根据 物业公司Id，找到注册时录入小区 列表
	 * 
	 * @param pcId
	 * @return
	 */
	List<Map<String, Object>> select_gbrList_ByPCId(BigInteger pcId);
}
