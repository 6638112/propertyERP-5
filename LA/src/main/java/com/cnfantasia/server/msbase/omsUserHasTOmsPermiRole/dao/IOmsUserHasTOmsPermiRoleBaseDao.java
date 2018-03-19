package com.cnfantasia.server.msbase.omsUserHasTOmsPermiRole.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;

/**
 * 描述:(OMS用户角色关系) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserHasTOmsPermiRoleBaseDao {
	/**
	 * 根据条件查询(OMS用户角色关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsUserHasTOmsPermiRole> selectOmsUserHasTOmsPermiRoleByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(OMS用户角色关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsUserHasTOmsPermiRole> selectOmsUserHasTOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(OMS用户角色关系)信息
	 * @param id
	 * @return
	 */
	public OmsUserHasTOmsPermiRole selectOmsUserHasTOmsPermiRoleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS用户角色关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsUserHasTOmsPermiRoleCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(OMS用户角色关系)新增一条记录
	 * @param omsUserHasTOmsPermiRole
	 * @return
	 */
	public int insertOmsUserHasTOmsPermiRole(OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole);
	
	/**
	 * 批量新增(OMS用户角色关系)信息
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
	 * 根据Id 批量删除(OMS用户角色关系)信息_逻辑删除
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
