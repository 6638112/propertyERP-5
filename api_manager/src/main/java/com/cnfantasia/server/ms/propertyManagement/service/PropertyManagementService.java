package com.cnfantasia.server.ms.propertyManagement.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.omsPermiRole.constant.OmsPermiRoleConstant;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.omsUser.dao.IOmsUserBaseDao;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.dao.IOmsUserHasTOmsPermiRoleBaseDao;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyManagement.service.PropertyManagementBaseService;
import com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.dao.IPropertyManagementHasOmsUserBaseDao;
import com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.entity.PropertyManagementHasOmsUser;
import com.cnfantasia.server.ms.propertyManagement.dao.IPropertyManagementDao;
import com.cnfantasia.server.ms.propertyManagement.entity.PropertyManagementEntity;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;

public class PropertyManagementService extends PropertyManagementBaseService implements IPropertyManagementService {
	
	private Log logger = LogFactory.getLog(getClass());
	
	private IPropertyManagementDao propertyManagementDao;
	
	private IPropertyManagementHasOmsUserBaseDao propertyManagementHasOmsUserBaseDao;
	
	private IOmsUserBaseDao omsUserBaseDao;
	
	private IOmsUserHasTOmsPermiRoleBaseDao omsUserHasTOmsPermiRoleBaseDao;
	
	public IPropertyManagementDao getPropertyManagementDao() {
		return propertyManagementDao;
	}

	public void setPropertyManagementDao(IPropertyManagementDao propertyManagementDao) {
		this.propertyManagementDao = propertyManagementDao;
	}

	public IPropertyManagementHasOmsUserBaseDao getPropertyManagementHasOmsUserBaseDao() {
		return propertyManagementHasOmsUserBaseDao;
	}

	public void setPropertyManagementHasOmsUserBaseDao(IPropertyManagementHasOmsUserBaseDao propertyManagementHasOmsUserBaseDao) {
		this.propertyManagementHasOmsUserBaseDao = propertyManagementHasOmsUserBaseDao;
	}

	public IOmsUserBaseDao getOmsUserBaseDao() {
		return omsUserBaseDao;
	}

	public void setOmsUserBaseDao(IOmsUserBaseDao omsUserBaseDao) {
		this.omsUserBaseDao = omsUserBaseDao;
	}

	public IOmsUserHasTOmsPermiRoleBaseDao getOmsUserHasTOmsPermiRoleBaseDao() {
		return omsUserHasTOmsPermiRoleBaseDao;
	}

	public void setOmsUserHasTOmsPermiRoleBaseDao(IOmsUserHasTOmsPermiRoleBaseDao omsUserHasTOmsPermiRoleBaseDao) {
		this.omsUserHasTOmsPermiRoleBaseDao = omsUserHasTOmsPermiRoleBaseDao;
	}

	@Override
	public int saveManagement(PropertyManagement management, String omsUserId, String userAccount, String password){
		int i = 0;
		try{
			//1.保存管理处信息
			management.setIsEdit(0);//无论是新增还是修改都需要进行审核
			if(!StringUtils.isEmpty(management.getId())){
				CnfantasiaCommUtil.updateStandard(management);
				i = propertyManagementDao.updPropertyManagement(management);
			}else{
				CnfantasiaCommUtil.newStandard(management,SEQConstants.t_property_management);
				i = insertPropertyManagement(management);
			}
			//2.保存OMS用户信息
			OmsUser omsUser = new OmsUser();
			if(!StringUtils.isEmpty(omsUserId)){
				omsUser = this.omsUserBaseDao.selectOmsUserBySeqId(CnfantasiaCommUtil.convert2BigInteger(omsUserId));
			}
			omsUser.setUserAccount(userAccount);
			if(!StringUtils.isEmpty(password)){
				omsUser.setPassword(Md5Util.MD5Twice(password));
			}
			omsUser.setIsadmin(0);
			omsUser.setIsSubUser(1);
			omsUser.setIsPmUser(1);//标识是物业管理处公司
			omsUser.setUserState(0L);
			if(!StringUtils.isEmpty(omsUserId)){
				CnfantasiaCommUtil.updateStandard(omsUser);
				i = omsUserBaseDao.updateOmsUser(omsUser);
			}else{
				// omsUser.setParentUserId(UserContext.getCurrUser().getId());//父账号是物业公司的用户ID
				CnfantasiaCommUtil.newStandard(omsUser, SEQConstants.t_oms_user);
				i = omsUserBaseDao.insertOmsUser(omsUser);
				//3.建立管理处和OMS账号的关联关系
				PropertyManagementHasOmsUser hasOmsUser = new PropertyManagementHasOmsUser();
				hasOmsUser.setName(management.getName());
				hasOmsUser.setTel(management.getTel());
				hasOmsUser.settOmsUserFId(omsUser.getId());
				hasOmsUser.settPropertyManagementFId(management.getId());
				CnfantasiaCommUtil.newStandard(hasOmsUser, SEQConstants.t_property_management_has_oms_user);
				i = this.propertyManagementHasOmsUserBaseDao.insertPropertyManagementHasOmsUser(hasOmsUser);
				//4.给该用户添加管理处角色
				OmsUserHasTOmsPermiRole userHasRole = new OmsUserHasTOmsPermiRole();
				userHasRole.settOmsUserFId(omsUser.getId());
				userHasRole.settOmsPermiRoleFId(CnfantasiaCommUtil.getRoleIdByRoleCode(OmsPermiRoleConstant.Sys_Role_PC_Management));
				CnfantasiaCommUtil.newStandard(userHasRole, SEQConstants.t_oms_user_has_t_oms_permi_role);
				omsUserHasTOmsPermiRoleBaseDao.insertOmsUserHasTOmsPermiRole(userHasRole);
			}
			
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int deleteManagement(BigInteger id) {
		int i = 0;
		try{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("managementId", id);
			paramMap.put("delUser", CnfantasiaCommUtil.getCurrentUserId());
			this.propertyManagementDao.deletePropertyManagementHasOmsUserByMgtId(paramMap);
			//1.删除管理处
			
			//2.删除用户
			
			//3.删除关联关系
			
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public PropertyManagementEntity selectPropertyManagementById(BigInteger id) {
		return propertyManagementDao.selectPropertyManagementById(id);
	}

	@Override
	public List<PropertyManagementEntity> selectPropertyManagementForList(Map<String, Object> paramMap) {
		return propertyManagementDao.selectPropertyManagementForList(paramMap);
	}



}
