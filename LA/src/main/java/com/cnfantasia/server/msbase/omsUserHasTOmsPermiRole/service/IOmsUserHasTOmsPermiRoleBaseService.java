package com.cnfantasia.server.msbase.omsUserHasTOmsPermiRole.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;

/**
 * 描述:(OMS用户角色关系) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserHasTOmsPermiRoleBaseService {
	
	/**
	 * 根据条件查询(OMS用户角色关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsUserHasTOmsPermiRole> getOmsUserHasTOmsPermiRoleByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(OMS用户角色关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsUserHasTOmsPermiRole> getOmsUserHasTOmsPermiRoleByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(OMS用户角色关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsUserHasTOmsPermiRole> getOmsUserHasTOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(OMS用户角色关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsUserHasTOmsPermiRole> getOmsUserHasTOmsPermiRoleByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(OMS用户角色关系)信息
	 * @param id
	 * @return
	 */
	public OmsUserHasTOmsPermiRole getOmsUserHasTOmsPermiRoleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS用户角色关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsUserHasTOmsPermiRoleCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(OMS用户角色关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsUserHasTOmsPermiRoleCountDim(Map<String,Object> paramMap);
	/**
	 * 往(OMS用户角色关系)新增一条记录
	 * @param omsUserHasTOmsPermiRole
	 * @return
	 */
	public int insertOmsUserHasTOmsPermiRole(OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole);
	/**
	 * 批量新增(OMS用户角色关系)
	 * @param omsUserHasTOmsPermiRoleList
	 * @return
	 */
	public int insertOmsUserHasTOmsPermiRoleBatch(List<OmsUserHasTOmsPermiRole> omsUserHasTOmsPermiRoleList);
	/**
	 * 更新(OMS用户角色关系)信息
	 * @param omsUserHasTOmsPermiRole
	 * @return
	 */
	public int updateOmsUserHasTOmsPermiRole(OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole);
	/**
	 * 批量更新(OMS用户角色关系)信息
	 * @param omsUserHasTOmsPermiRoleList
	 * @return
	 */
	public int updateOmsUserHasTOmsPermiRoleBatch(List<OmsUserHasTOmsPermiRole> omsUserHasTOmsPermiRoleList);
	/**
	 * 根据序列号删除(OMS用户角色关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOmsUserHasTOmsPermiRoleLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(OMS用户角色关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOmsUserHasTOmsPermiRoleLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(OMS用户角色关系)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOmsUserHasTOmsPermiRole(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(OMS用户角色关系)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOmsUserHasTOmsPermiRoleBatch(List<java.math.BigInteger> idList);
	
}
