package com.cnfantasia.server.domainbase.permiRoleHasTPermiFunction.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiRoleHasTPermiFunction.entity.PermiRoleHasTPermiFunction;

/**
 * 描述:(角色功能关系) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPermiRoleHasTPermiFunctionBaseDao {
	/**
	 * 根据条件查询(角色功能关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PermiRoleHasTPermiFunction> selectPermiRoleHasTPermiFunctionByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(角色功能关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PermiRoleHasTPermiFunction> selectPermiRoleHasTPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(角色功能关系)信息
	 * @param id
	 * @return
	 */
	public PermiRoleHasTPermiFunction selectPermiRoleHasTPermiFunctionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(角色功能关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPermiRoleHasTPermiFunctionCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(角色功能关系)新增一条记录
	 * @param permiRoleHasTPermiFunction
	 * @return
	 */
	public int insertPermiRoleHasTPermiFunction(PermiRoleHasTPermiFunction permiRoleHasTPermiFunction);
	
	/**
	 * 批量新增(角色功能关系)信息
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
	 * 根据Id 批量删除(角色功能关系)信息_逻辑删除
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
