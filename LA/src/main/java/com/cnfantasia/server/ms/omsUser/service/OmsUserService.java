package com.cnfantasia.server.ms.omsUser.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.ms.omsUser.dao.IOmsUserDao;
import com.cnfantasia.server.ms.pub.constant.SEQConstants;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.user.entity.UserEntity;
import com.cnfantasia.server.msbase.omsPermiFunction.entity.OmsPermiFunction;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUser.service.OmsUserBaseService;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.dao.OmsUserHasTOmsPermiRoleBaseDao;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
/**
 * 描述:() 具体业务Service层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsUserService extends OmsUserBaseService implements IOmsUserService{
	private IOmsUserDao omsUserDao;

	public void setOmsUserDao(IOmsUserDao omsUserDao) {
		this.omsUserDao = omsUserDao;
	}

	private IUuidManager uuidManager;

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	private IDualDao dualDao;

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	private OmsUserHasTOmsPermiRoleBaseDao omsUserHasTOmsPermiRoleBaseDao;

	public void setOmsUserHasTOmsPermiRoleBaseDao(OmsUserHasTOmsPermiRoleBaseDao omsUserHasTOmsPermiRoleBaseDao) {
		this.omsUserHasTOmsPermiRoleBaseDao = omsUserHasTOmsPermiRoleBaseDao;
	}

	@Override
	public UserEntity updPersonInfo(OmsUser omsUser) {
		int updRes = omsUserDao.updateOmsUser(omsUser);
		if(updRes<=0){
			throw new BusinessRuntimeException("UserService.updPersonInfo.failed");
		}
		return omsUserDao.selectUserById(omsUser.getId());
	}

	@Override
	public UserEntity getUserById(BigInteger userId) {
		return null;
	}

	@Override
	public void saveOmsUser(OmsUser omsUser, String[] roleIds) {
		if (omsUser.getId() == null) {//新增
			omsUser.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user));
			omsUser.setSys0AddUser(UserContext.getCurrUser().getId());
			omsUser.setUserState(1L);
			omsUserDao.insertOmsUser(omsUser);
		} else {//更新
			omsUser.setSys0UpdTime(dualDao.getNowTime());
			omsUser.setSys0UpdUser(UserContext.getCurrUser().getId());
			omsUserDao.updateOmsUser(omsUser);

			//删除之前 用户-角色 中间表中的数据

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("omsUserId", omsUser.getId());
			paramMap.put("sys0DelUser", UserContext.getCurrUser().getId());
			omsUserDao.delete_userRole_byUserId(paramMap);
		}

		// 处理 用户-角色 中间表

		List<OmsUserHasTOmsPermiRole> omsUserHasTOmsPermiRoleList = new ArrayList<OmsUserHasTOmsPermiRole>(roleIds.length);
		List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user_has_t_oms_permi_role, roleIds.length);
		for (int i = 0; i < roleIds.length; i++) {
			OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole = new OmsUserHasTOmsPermiRole();
			omsUserHasTOmsPermiRole.setId(ids.get(i));
			omsUserHasTOmsPermiRole.settOmsUserFId(omsUser.getId());
			omsUserHasTOmsPermiRole.settOmsPermiRoleFId(new BigInteger(roleIds[i]));
			omsUserHasTOmsPermiRole.setSys0AddUser(UserContext.getCurrUser().getId());
			omsUserHasTOmsPermiRoleList.add(omsUserHasTOmsPermiRole);
		}

		omsUserHasTOmsPermiRoleBaseDao.insertOmsUserHasTOmsPermiRoleBatch(omsUserHasTOmsPermiRoleList);
	}

	@Override
	public List<OmsPermiFunction> select_OmsPermiFunction_byOmsUserId(BigInteger omsUserId) {
		return omsUserDao.select_OmsPermiFunction_byOmsUserId(omsUserId);
	}

	@Override
	public String getWelcomMsgInfo_byOmsuserId(BigInteger omsUserId) {
		return omsUserDao.getWelcomMsgInfo_byOmsuserId(omsUserId);
	}

}
