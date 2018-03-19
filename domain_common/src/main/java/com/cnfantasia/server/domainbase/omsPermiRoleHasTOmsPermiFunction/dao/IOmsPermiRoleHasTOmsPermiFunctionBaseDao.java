package com.cnfantasia.server.domainbase.omsPermiRoleHasTOmsPermiFunction.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsPermiRoleHasTOmsPermiFunction.entity.OmsPermiRoleHasTOmsPermiFunction;

/**
 * 描述:(OMS角色功能关系) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsPermiRoleHasTOmsPermiFunctionBaseDao {
	/**
	 * 根据条件查询(OMS角色功能关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsPermiRoleHasTOmsPermiFunction> selectOmsPermiRoleHasTOmsPermiFunctionByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(OMS角色功能关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsPermiRoleHasTOmsPermiFunction> selectOmsPermiRoleHasTOmsPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(OMS角色功能关系)信息
	 * @param id
	 * @return
	 */
	public OmsPermiRoleHasTOmsPermiFunction selectOmsPermiRoleHasTOmsPermiFunctionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS角色功能关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsPermiRoleHasTOmsPermiFunctionCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(OMS角色功能关系)新增一条记录
	 * @param omsPermiRoleHasTOmsPermiFunction
	 * @return
	 */
	public int insertOmsPermiRoleHasTOmsPermiFunction(OmsPermiRoleHasTOmsPermiFunction omsPermiRoleHasTOmsPermiFunction);
	
	/**
	 * 批量新增(OMS角色功能关系)信息
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
	 * 根据Id 批量删除(OMS角色功能关系)信息_逻辑删除
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
