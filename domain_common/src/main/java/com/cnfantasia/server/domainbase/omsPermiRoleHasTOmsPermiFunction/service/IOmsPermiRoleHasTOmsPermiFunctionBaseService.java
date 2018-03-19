package com.cnfantasia.server.domainbase.omsPermiRoleHasTOmsPermiFunction.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.omsPermiRoleHasTOmsPermiFunction.entity.OmsPermiRoleHasTOmsPermiFunction;

/**
 * 描述:(OMS角色功能关系) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsPermiRoleHasTOmsPermiFunctionBaseService {
	
	/**
	 * 根据条件查询(OMS角色功能关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsPermiRoleHasTOmsPermiFunction> getOmsPermiRoleHasTOmsPermiFunctionByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(OMS角色功能关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsPermiRoleHasTOmsPermiFunction> getOmsPermiRoleHasTOmsPermiFunctionByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(OMS角色功能关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsPermiRoleHasTOmsPermiFunction> getOmsPermiRoleHasTOmsPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(OMS角色功能关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsPermiRoleHasTOmsPermiFunction> getOmsPermiRoleHasTOmsPermiFunctionByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(OMS角色功能关系)信息
	 * @param id
	 * @return
	 */
	public OmsPermiRoleHasTOmsPermiFunction getOmsPermiRoleHasTOmsPermiFunctionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS角色功能关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsPermiRoleHasTOmsPermiFunctionCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(OMS角色功能关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsPermiRoleHasTOmsPermiFunctionCountDim(Map<String,Object> paramMap);
	/**
	 * 往(OMS角色功能关系)新增一条记录
	 * @param omsPermiRoleHasTOmsPermiFunction
	 * @return
	 */
	public int insertOmsPermiRoleHasTOmsPermiFunction(OmsPermiRoleHasTOmsPermiFunction omsPermiRoleHasTOmsPermiFunction);
	/**
	 * 批量新增(OMS角色功能关系)
	 * @param omsPermiRoleHasTOmsPermiFunctionList
	 * @return
	 */
	public int insertOmsPermiRoleHasTOmsPermiFunctionBatch(List<OmsPermiRoleHasTOmsPermiFunction> omsPermiRoleHasTOmsPermiFunctionList);
	/**
	 * 更新(OMS角色功能关系)信息
	 * @param omsPermiRoleHasTOmsPermiFunction
	 * @return
	 */
	public int updateOmsPermiRoleHasTOmsPermiFunction(OmsPermiRoleHasTOmsPermiFunction omsPermiRoleHasTOmsPermiFunction);
	/**
	 * 批量更新(OMS角色功能关系)信息
	 * @param omsPermiRoleHasTOmsPermiFunctionList
	 * @return
	 */
	public int updateOmsPermiRoleHasTOmsPermiFunctionBatch(List<OmsPermiRoleHasTOmsPermiFunction> omsPermiRoleHasTOmsPermiFunctionList);
	/**
	 * 根据序列号删除(OMS角色功能关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOmsPermiRoleHasTOmsPermiFunctionLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(OMS角色功能关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOmsPermiRoleHasTOmsPermiFunctionLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(OMS角色功能关系)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOmsPermiRoleHasTOmsPermiFunction(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(OMS角色功能关系)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOmsPermiRoleHasTOmsPermiFunctionBatch(List<java.math.BigInteger> idList);
	
}
