package com.cnfantasia.server.domainbase.permiRoleHasTPermiFunction.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.permiRoleHasTPermiFunction.entity.PermiRoleHasTPermiFunction;

/**
 * 描述:(角色功能关系) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPermiRoleHasTPermiFunctionBaseService {
	
	/**
	 * 根据条件查询(角色功能关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PermiRoleHasTPermiFunction> getPermiRoleHasTPermiFunctionByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(角色功能关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PermiRoleHasTPermiFunction> getPermiRoleHasTPermiFunctionByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(角色功能关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PermiRoleHasTPermiFunction> getPermiRoleHasTPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(角色功能关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PermiRoleHasTPermiFunction> getPermiRoleHasTPermiFunctionByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(角色功能关系)信息
	 * @param id
	 * @return
	 */
	public PermiRoleHasTPermiFunction getPermiRoleHasTPermiFunctionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(角色功能关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPermiRoleHasTPermiFunctionCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(角色功能关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPermiRoleHasTPermiFunctionCountDim(Map<String,Object> paramMap);
	/**
	 * 往(角色功能关系)新增一条记录
	 * @param permiRoleHasTPermiFunction
	 * @return
	 */
	public int insertPermiRoleHasTPermiFunction(PermiRoleHasTPermiFunction permiRoleHasTPermiFunction);
	/**
	 * 批量新增(角色功能关系)
	 * @param permiRoleHasTPermiFunctionList
	 * @return
	 */
	public int insertPermiRoleHasTPermiFunctionBatch(List<PermiRoleHasTPermiFunction> permiRoleHasTPermiFunctionList);
	/**
	 * 更新(角色功能关系)信息
	 * @param permiRoleHasTPermiFunction
	 * @return
	 */
	public int updatePermiRoleHasTPermiFunction(PermiRoleHasTPermiFunction permiRoleHasTPermiFunction);
	/**
	 * 批量更新(角色功能关系)信息
	 * @param permiRoleHasTPermiFunctionList
	 * @return
	 */
	public int updatePermiRoleHasTPermiFunctionBatch(List<PermiRoleHasTPermiFunction> permiRoleHasTPermiFunctionList);
	/**
	 * 根据序列号删除(角色功能关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePermiRoleHasTPermiFunctionLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(角色功能关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePermiRoleHasTPermiFunctionLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(角色功能关系)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePermiRoleHasTPermiFunction(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(角色功能关系)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePermiRoleHasTPermiFunctionBatch(List<java.math.BigInteger> idList);
	
}
