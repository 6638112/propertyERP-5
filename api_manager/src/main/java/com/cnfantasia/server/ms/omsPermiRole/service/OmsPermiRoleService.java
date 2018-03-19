package com.cnfantasia.server.ms.omsPermiRole.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.omsPermiRole.dao.OmsPermiRoleBaseDao;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.domainbase.omsPermiRoleHasTOmsPermiFunction.dao.OmsPermiRoleHasTOmsPermiFunctionBaseDao;
import com.cnfantasia.server.domainbase.omsPermiRoleHasTOmsPermiFunction.entity.OmsPermiRoleHasTOmsPermiFunction;
import com.cnfantasia.server.domainbase.omsUserHasPropertyDistrict.entity.OmsUserHasPropertyDistrict;
import com.cnfantasia.server.domainbase.omsUserHasPropertyDistrict.service.IOmsUserHasPropertyDistrictBaseService;
import com.cnfantasia.server.domainbase.propertyDistrictHasGroupBuilding.entity.PropertyDistrictHasGroupBuilding;
import com.cnfantasia.server.domainbase.propertyDistrictHasGroupBuilding.service.IPropertyDistrictHasGroupBuildingBaseService;
import com.cnfantasia.server.ms.omsPermiRole.dao.IOmsPermiRoleDao;
import com.cnfantasia.server.ms.omsPermiRole.entity.DistrictManager;
import com.cnfantasia.server.ms.omsPermiRole.entity.OmsPermiFunctionEntity;
import com.cnfantasia.server.ms.pub.session.UserContext;

/**
 * 描述:(OMS角色表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsPermiRoleService implements IOmsPermiRoleService {
	
	@Resource
	private IOmsUserHasPropertyDistrictBaseService omsUserHasPropertyDistrictBaseService;
	
	@Resource
	private IPropertyDistrictHasGroupBuildingBaseService propertyDistrictHasGroupBuildingBaseService;
	
	private IOmsPermiRoleDao omsPermiRoleDao;

	public IOmsPermiRoleDao getOmsPermiRoleDao() {
		return omsPermiRoleDao;
	}

	public void setOmsPermiRoleDao(IOmsPermiRoleDao omsPermiRoleDao) {
		this.omsPermiRoleDao = omsPermiRoleDao;
	}
	public OmsPermiRoleBaseDao getOmsPermiRoleBaseDao() {
		return omsPermiRoleBaseDao;
	}

	private OmsPermiRoleBaseDao omsPermiRoleBaseDao;
	public void setOmsPermiRoleBaseDao(OmsPermiRoleBaseDao omsPermiRoleBaseDao) {
		this.omsPermiRoleBaseDao = omsPermiRoleBaseDao;
	}

	private OmsPermiRoleHasTOmsPermiFunctionBaseDao omsPermiRoleHasTOmsPermiFunctionBaseDao;

	public void setOmsPermiRoleHasTOmsPermiFunctionBaseDao(OmsPermiRoleHasTOmsPermiFunctionBaseDao omsPermiRoleHasTOmsPermiFunctionBaseDao) {
		this.omsPermiRoleHasTOmsPermiFunctionBaseDao = omsPermiRoleHasTOmsPermiFunctionBaseDao;
	}

	/**
	 * 根据条件查询(OMS角色表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsPermiRole> getOmsPermiRoleByCondition(Map<String,Object> paramMap){
		return omsPermiRoleBaseDao.selectOmsPermiRoleByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(OMS角色表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsPermiRole> getOmsPermiRoleByConditionDim(Map<String,Object> paramMap){
		return omsPermiRoleBaseDao.selectOmsPermiRoleByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(OMS角色表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsPermiRole> getOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsPermiRoleBaseDao.selectOmsPermiRoleByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(OMS角色表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsPermiRole> getOmsPermiRoleByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsPermiRoleBaseDao.selectOmsPermiRoleByCondition(paramMap, pageModel,true);
	}

	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsPermiRoleCount(Map<String,Object> paramMap){
		return omsPermiRoleBaseDao.selectOmsPermiRoleCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsPermiRoleCountDim(Map<String,Object> paramMap){
		return omsPermiRoleBaseDao.selectOmsPermiRoleCount(paramMap,true);
	}

	/**
	 * 找出所有可以分配给角色的权限功能表
	 * 
	 * @author wenfq
	 * @return 权限列表
	 */
	@Override
	public List<OmsPermiFunctionEntity> selectAllOmsPermiFunction() {
		return omsPermiRoleDao.selectOmsPermiFunctionByCondition(new HashMap<String, Object>(), false);
	}

	@Override
	public List<BigInteger> selectOmsPermiFunctionByRoleId(BigInteger roleId) {
		return omsPermiRoleDao.selectOmsPermiFunctionByRoleId(roleId);
	}

	//TODO ____续写...OmsPermiRoleService

	private IUuidManager uuidManager;

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	private IDualDao dualDao;

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	@Override
	public void saveRole(OmsPermiRole role, String[] opfIds) {
		if (role.getId() == null) {//新增
			BigInteger id = uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_permi_role);
			role.setId(id);
			role.setStatus(1);
			role.setSys0AddTime(dualDao.getNowTime());
			role.setSys0AddUser(UserContext.getCurrUser().getId());
			omsPermiRoleBaseDao.insertOmsPermiRole(role);
		} else {//修改
			role.setSys0UpdTime(dualDao.getNowTime());
			role.setSys0UpdUser(UserContext.getCurrUser().getId());
			omsPermiRoleBaseDao.updateOmsPermiRole(role);
			//删除之前角色权限中间表中的数据

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("roleId", role.getId());
			paramMap.put("sys0DelUser", UserContext.getCurrUser().getId());
			omsPermiRoleDao.delete_roleFunction_byRoleId(paramMap);
		}

		// 处理 角色-权限 中间表
		List<OmsPermiRoleHasTOmsPermiFunction> omsPermiRoleHasTOmsPermiFunctionList = new ArrayList<OmsPermiRoleHasTOmsPermiFunction>(opfIds.length);
		List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_permi_role_has_t_oms_permi_function, opfIds.length);
		for (int i = 0; i < opfIds.length; i++) {
			OmsPermiRoleHasTOmsPermiFunction omsPermiRoleHasTOmsPermiFunction = new OmsPermiRoleHasTOmsPermiFunction();
			omsPermiRoleHasTOmsPermiFunction.setId(ids.get(i));
			omsPermiRoleHasTOmsPermiFunction.settOmsPermiFunctionFId(new BigInteger(opfIds[i]));
			omsPermiRoleHasTOmsPermiFunction.settOmsPermiRoleFId(role.getId());
			omsPermiRoleHasTOmsPermiFunction.setSys0AddUser(UserContext.getCurrUser().getId());
			omsPermiRoleHasTOmsPermiFunctionList.add(omsPermiRoleHasTOmsPermiFunction);
		}
		omsPermiRoleHasTOmsPermiFunctionBaseDao.insertOmsPermiRoleHasTOmsPermiFunctionBatch(omsPermiRoleHasTOmsPermiFunctionList);
	}
	
	public List<DistrictManager> getDistrictManagerList(Map<String, Object> paramMap) {
		return omsPermiRoleDao.getDistrictManagerList(paramMap);
	}
	
	public List<Map<String, Object>> getOmsUserList(String qryStr, PageModel pageModel) {
		return omsPermiRoleDao.getOmsUserList(qryStr, pageModel);
	}
	
	public void saveDistrictManager(List<OmsUserHasPropertyDistrict> omsUserHasPropertyDistrictList, List<PropertyDistrictHasGroupBuilding> propertyDistrictHasGroupBuildingList) {
		if(omsUserHasPropertyDistrictList != null && omsUserHasPropertyDistrictList.size() > 0) {
			omsPermiRoleDao.deleteByDistrictId(omsUserHasPropertyDistrictList.get(0).gettPropertyDistrictFId());
			omsUserHasPropertyDistrictBaseService.insertOmsUserHasPropertyDistrictBatch(omsUserHasPropertyDistrictList);
		}
		
		if(propertyDistrictHasGroupBuildingList != null && propertyDistrictHasGroupBuildingList.size() > 0) {
			omsPermiRoleDao.deleteGbByDistrictId(propertyDistrictHasGroupBuildingList.get(0).gettPropertyDistrictFId());
			propertyDistrictHasGroupBuildingBaseService.insertPropertyDistrictHasGroupBuildingBatch(propertyDistrictHasGroupBuildingList);
		}
	}
}
