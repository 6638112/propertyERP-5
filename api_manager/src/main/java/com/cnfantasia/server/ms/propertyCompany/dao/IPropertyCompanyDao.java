package com.cnfantasia.server.ms.propertyCompany.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;
import com.cnfantasia.server.domainbase.propertyCompany.dao.IPropertyCompanyBaseDao;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyEntity;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyWorkbenchEntity;

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
	
	/**
	 * 物业公司登录展示工作台信息
	 * */
	public PropertyCompanyWorkbenchEntity getPropertyCompanyWorkbench(BigInteger userId);
	
	/**
	 * 查询合作申请Count
	 * */
	public int queryPropertyCompanyForApplyCount(Map<String, Object> paramMap);
	/**
	 * 查询合作申请List
	 * */
	public List<PropertyCompanyEntity> queryPropertyCompanyForApplyList(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	public int selectValidPropertyCompanyByPcName(String pcName);
	/**
	 * 物业公司信息审核列表
	 */
	public List<PropertyCompanyEntity> selectpcMsg(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	/**
	 * 查询修改物业公司条数
	 */
	public int selectEditCount(Map<String, Object> paramMap);

	/**
	 * 根据物业公司名称查询合作代理信息
	 * @param name
	 * @return
	 */
	public List<ChannelPartner> selectChannelPartnerByPcName(String pcName);

	/**
	 * 根据物业公司id  查询小区信息
	 * @param pcId
	 * @return
	 */
	public List<Map<String, Object>> select_gbList_ByPCId(BigInteger pcId);
}
