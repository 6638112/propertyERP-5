package com.cnfantasia.server.ms.propertyCompany.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;
import com.cnfantasia.server.domainbase.channelPartnerRecommend.entity.ChannelPartnerRecommend;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertyCompany.service.IPropertyCompanyBaseService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyEntity;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyWorkbenchEntity;

public interface IPropertyCompanyService extends IPropertyCompanyBaseService {

	/**
	 * 根据序列号查询(物业公司 )信息
	 * 
	 * @param id
	 * @return
	 */
	public PropertyCompanyEntity selectPropertyCompanyByOmsUserId(java.math.BigInteger id);

	List<GroupBuildingSimpleEntity> select_gbList_ByOmsUserId(BigInteger id);

	void savePropertyCompany(PropertyCompany pcEntity);

	public int getPCList4admin_forCount(Map<String, Object> paramMap);
	
	public int getEditPCList4admin_forCount(Map<String, Object> paramMap);

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
	 * 物业公司审核
	 * */
	public int auditPropertyCompanyApply(String id, String adminId, String auditResult, String desc);
	
	/**
	 * 查询合作申请Count
	 * */
	public int queryPropertyCompanyForApplyCount(Map<String, Object> paramMap);
	/**
	 * 查询合作申请List
	 * */
	public List<PropertyCompanyEntity> queryPropertyCompanyForApplyList(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	/**
	 * 根据pcName查询有效的物业公司数量
	 */
	public int selectValidPropertyCompanyByPcName(String pcName);
	/**
	 * 修改物业公司信息列表
	 */
	public List<PropertyCompanyEntity> queryPcMsgList(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	/**
	 * 查询物业公司的合作代理信息
	 * @param pc
	 * @return 
	 */
	public List<ChannelPartner> selectChannelPartnerByPc(PropertyCompany pc);

	/**
	 * 保存渠道合伙人的推荐 信息
	 * @param channelPartnerRecommend
	 */
	public void saveChannelPartnerRecommend(ChannelPartnerRecommend channelPartnerRecommend);

	/**
	 * 根据物业公司id  查询小区信息
	 * @param valueOf
	 * @return
	 */
	public List<Map<String, Object>> select_gbList_ByPCId(BigInteger valueOf);
	
}
