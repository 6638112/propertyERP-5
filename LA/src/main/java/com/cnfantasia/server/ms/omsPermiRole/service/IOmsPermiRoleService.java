package com.cnfantasia.server.ms.omsPermiRole.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.ms.omsPermiRole.entity.OmsPermiFunctionEntity;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;

/**
 * 描述:(OMS角色表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsPermiRoleService {
	
	/**
	 * 根据条件查询(OMS角色表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsPermiRole> getOmsPermiRoleByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(OMS角色表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsPermiRole> getOmsPermiRoleByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(OMS角色表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsPermiRole> getOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(OMS角色表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsPermiRole> getOmsPermiRoleByConditionDim(Map<String,Object> paramMap,PageModel pageModel);

	public int getOmsPermiRoleCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsPermiRoleCountDim(Map<String,Object> paramMap);
	
	/**
	 * 保存角色，且维护好 角色-权限 中间表
	 * 
	 * @param role
	 * @param opfIds
	 */
	public void saveRole(OmsPermiRole role, String[] opfIds);

	/**
	 * 找出所有可以分配给角色的权限功能表
	 * 
	 * @author wenfq
	 * @return 权限列表
	 */
	public List<OmsPermiFunctionEntity> selectAllOmsPermiFunction();

	/**
	 * 根据角色ID找到所有已分配的权限项ID列表
	 * 
	 * @author wenfq
	 * @return 权限项ID列表
	 */
	List<BigInteger> selectOmsPermiFunctionByRoleId(BigInteger roleId);
}
