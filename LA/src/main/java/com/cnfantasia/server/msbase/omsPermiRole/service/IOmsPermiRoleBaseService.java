package com.cnfantasia.server.msbase.omsPermiRole.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.msbase.omsPermiRole.entity.OmsPermiRole;

/**
 * 描述:(OMS角色表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsPermiRoleBaseService {
	
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
	/**
	 * 根据序列号查询(OMS角色表)信息
	 * @param id
	 * @return
	 */
	public OmsPermiRole getOmsPermiRoleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsPermiRoleCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsPermiRoleCountDim(Map<String,Object> paramMap);
	/**
	 * 往(OMS角色表)新增一条记录
	 * @param omsPermiRole
	 * @return
	 */
	public int insertOmsPermiRole(OmsPermiRole omsPermiRole);
	/**
	 * 批量新增(OMS角色表)
	 * @param omsPermiRoleList
	 * @return
	 */
	public int insertOmsPermiRoleBatch(List<OmsPermiRole> omsPermiRoleList);
	/**
	 * 更新(OMS角色表)信息
	 * @param omsPermiRole
	 * @return
	 */
	public int updateOmsPermiRole(OmsPermiRole omsPermiRole);
	/**
	 * 批量更新(OMS角色表)信息
	 * @param omsPermiRoleList
	 * @return
	 */
	public int updateOmsPermiRoleBatch(List<OmsPermiRole> omsPermiRoleList);
	/**
	 * 根据序列号删除(OMS角色表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOmsPermiRoleLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(OMS角色表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOmsPermiRoleLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(OMS角色表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOmsPermiRole(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(OMS角色表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOmsPermiRoleBatch(List<java.math.BigInteger> idList);
	
}
