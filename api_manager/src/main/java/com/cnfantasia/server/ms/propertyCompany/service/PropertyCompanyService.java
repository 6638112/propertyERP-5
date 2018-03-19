package com.cnfantasia.server.ms.propertyCompany.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.omsPermiRole.constant.OmsPermiRoleConstant;
import com.cnfantasia.server.api.propertyCompany.constant.PropertyCompanyDict;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;
import com.cnfantasia.server.domainbase.channelPartnerRecommend.dao.IChannelPartnerRecommendBaseDao;
import com.cnfantasia.server.domainbase.channelPartnerRecommend.entity.ChannelPartnerRecommend;
import com.cnfantasia.server.domainbase.channelPartnerRecommend.service.IChannelPartnerRecommendBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.dao.IOmsUserHasTOmsPermiRoleBaseDao;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertyCompany.service.PropertyCompanyBaseService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.propertyCompany.constant.PropertyCompanyConstant;
import com.cnfantasia.server.ms.propertyCompany.dao.IPropertyCompanyDao;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyEntity;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyWorkbenchEntity;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class PropertyCompanyService extends PropertyCompanyBaseService implements IPropertyCompanyService {
	IPropertyCompanyDao propertyCompanyDao;

	private IUuidManager uuidManager;
	private IDualDao dualDao;
	
	@Resource
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	
	@Resource
	private IChannelPartnerRecommendBaseDao channelPartnerRecommendBaseDao;

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	public void setPropertyCompanyDao(IPropertyCompanyDao propertyProprietorDao) {
		this.propertyCompanyDao = propertyProprietorDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IOmsUserHasTOmsPermiRoleBaseDao omsUserHasTOmsPermiRoleBaseDao;
	
	public IOmsUserHasTOmsPermiRoleBaseDao getOmsUserHasTOmsPermiRoleBaseDao() {
		return omsUserHasTOmsPermiRoleBaseDao;
	}

	public void setOmsUserHasTOmsPermiRoleBaseDao(IOmsUserHasTOmsPermiRoleBaseDao omsUserHasTOmsPermiRoleBaseDao) {
		this.omsUserHasTOmsPermiRoleBaseDao = omsUserHasTOmsPermiRoleBaseDao;
	}

	/**
	 * 根据序列号查询(物业公司 )信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCompanyEntity selectPropertyCompanyByOmsUserId(BigInteger id) {
		return propertyCompanyDao.selectPropertyCompanyByOmsUserId(id);
	}

	@Override
	public List<GroupBuildingSimpleEntity> select_gbList_ByOmsUserId(BigInteger id) {
		return propertyCompanyDao.select_gbList_ByOmsUserId(id);
	}

	@Override
	public void savePropertyCompany(PropertyCompany pcEntity) {
		if (pcEntity.getId() == null) {//新增
			pcEntity.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_company));
			pcEntity.setAdminId(UserContext.getCurrUser() != null ? UserContext.getCurrUser().getId() : null);
			propertyCompanyDao.insertPropertyCompany(pcEntity);
		} else {//更新
			propertyCompanyDao.updatePropertyCompany(pcEntity);
		}
	}

	@Override
	public int getPCList4admin_forCount(Map<String, Object> paramMap) {
		return propertyCompanyDao.getPCList4admin_forCount(paramMap);
	}

	@Override
	public List<PropertyCompanyEntity> getPCList4admin_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return propertyCompanyDao.getPCList4admin_forPage(curPageIndex, pageSize, paramMap);
	}

	@Override
	public List<Map<String, Object>> select_gbrList_ByPCId(BigInteger pcId) {
		return propertyCompanyDao.select_gbrList_ByPCId(pcId);
	}

	@Override
	public PropertyCompanyWorkbenchEntity getPropertyCompanyWorkbench(BigInteger userId) {
		return propertyCompanyDao.getPropertyCompanyWorkbench(userId);
	}

	@Override
	@Transactional
	public int auditPropertyCompanyApply(String id, String adminId, String auditResult, String desc) {
		int i = 0;
		try{
			PropertyCompany pcEntity = new PropertyCompany();
			pcEntity.setId(CnfantasiaCommUtil.convert2BigInteger(id));
			if("pass".equals(auditResult)){//审核通过
				pcEntity.setIsHighCooperation(PropertyCompanyConstant.HC_Status.Yes);
				pcEntity.setCooperationType(PropertyCompanyDict.CooperationType.supperCooperation);
			}else{//审核不通过
				pcEntity.setIsHighCooperation(PropertyCompanyConstant.HC_Status.Fail);
				pcEntity.setAuditResult(desc);
			}
			pcEntity.setSys0UpdUser(CnfantasiaCommUtil.getCurrentUserId());
			if(propertyCompanyDao.updatePropertyCompany(pcEntity)>0){
				if("pass".equals(auditResult)){
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("tOmsUserFId", adminId);
					paramMap.put("tOmsPermiRoleFId", CnfantasiaCommUtil.getRoleIdByRoleCode(OmsPermiRoleConstant.Sys_Role_PC_Base));//物业基础合作角色ID
					//给予物业公司高级合作角色
					List<OmsUserHasTOmsPermiRole> roles = omsUserHasTOmsPermiRoleBaseDao.selectOmsUserHasTOmsPermiRoleByCondition(paramMap, true);
					if(null!=roles && roles.size()>0){//修改基础合作角色改成高级合作角色
						OmsUserHasTOmsPermiRole role = roles.get(0);
						role.settOmsPermiRoleFId(CnfantasiaCommUtil.getRoleIdByRoleCode(OmsPermiRoleConstant.Sys_Role_PC_Supper));
						CnfantasiaCommUtil.updateStandard(role);
						i = omsUserHasTOmsPermiRoleBaseDao.updateOmsUserHasTOmsPermiRole(role);
					}else{//创建高级合作角色关联关系
						OmsUserHasTOmsPermiRole role = new OmsUserHasTOmsPermiRole();
						role.settOmsPermiRoleFId(CnfantasiaCommUtil.getRoleIdByRoleCode(OmsPermiRoleConstant.Sys_Role_PC_Supper));
						role.settOmsUserFId(new BigInteger(adminId));
						CnfantasiaCommUtil.newStandard(role,SEQConstants.t_oms_user_has_t_oms_permi_role);
						i = omsUserHasTOmsPermiRoleBaseDao.insertOmsUserHasTOmsPermiRole(role);
					}

					//更新其管辖下所有小区合作模式为高级合作模式
					paramMap.clear();
					paramMap.put("tPropertyCompanyFId", pcEntity.getId());
					paramMap.put("sys0DelState", 0);
					List<GroupBuilding> gbList = groupBuildingBaseDao.selectGroupBuildingByCondition(paramMap, false);
					List<GroupBuilding> gbListWillUpdate = new ArrayList<GroupBuilding>();
					for (int j = 0; j< gbList.size();j++) {
						GroupBuilding gb = new GroupBuilding();
						gb.setId(gbList.get(j).getId());
						gb.setCooperationType(PropertyCompanyDict.CooperationType.supperCooperation);
						gbListWillUpdate.add(gb);
					}
					if (!gbListWillUpdate.isEmpty()) {
						groupBuildingBaseDao.updateGroupBuildingBatch(gbListWillUpdate);
					}

				}else{
					i = 1;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int queryPropertyCompanyForApplyCount(Map<String, Object> paramMap) {
		return propertyCompanyDao.queryPropertyCompanyForApplyCount(paramMap);
	}

	@Override
	public List<PropertyCompanyEntity> queryPropertyCompanyForApplyList(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return propertyCompanyDao.queryPropertyCompanyForApplyList(curPageIndex, pageSize, paramMap);
	}

	@Override
	public int selectValidPropertyCompanyByPcName(String pcName) {
		return propertyCompanyDao.selectValidPropertyCompanyByPcName(pcName);
	}

	@Override
	public List<PropertyCompanyEntity> queryPcMsgList(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		 return propertyCompanyDao.selectpcMsg(curPageIndex, pageSize, paramMap);
	}

	@Override
	public int getEditPCList4admin_forCount(Map<String, Object> paramMap) {
		
		return propertyCompanyDao.selectEditCount(paramMap);
	}

	@Override
	public List<ChannelPartner> selectChannelPartnerByPc(PropertyCompany pc) {
		return propertyCompanyDao.selectChannelPartnerByPcName(pc.getName());
	}

	@Override
	public void saveChannelPartnerRecommend(ChannelPartnerRecommend channelPartnerRecommend) {
		channelPartnerRecommend.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_channel_partner_recommend));
		channelPartnerRecommendBaseDao.insertChannelPartnerRecommend(channelPartnerRecommend);
	}

	public IChannelPartnerRecommendBaseDao getChannelPartnerRecommendBaseDao() {
		return channelPartnerRecommendBaseDao;
	}

	public void setChannelPartnerRecommendBaseDao(IChannelPartnerRecommendBaseDao channelPartnerRecommendBaseDao) {
		this.channelPartnerRecommendBaseDao = channelPartnerRecommendBaseDao;
	}

	@Override
	public List<Map<String, Object>> select_gbList_ByPCId(BigInteger pcId) {
		return propertyCompanyDao.select_gbList_ByPCId(pcId);
	}
	
	

}
