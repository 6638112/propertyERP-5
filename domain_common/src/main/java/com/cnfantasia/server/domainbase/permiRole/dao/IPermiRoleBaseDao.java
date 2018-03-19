package com.cnfantasia.server.domainbase.permiRole.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiRole.entity.PermiRole;

/**
 * 描述:(角色表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPermiRoleBaseDao {
	/**
	 * 根据条件查询(角色表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PermiRole> selectPermiRoleByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(角色表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PermiRole> selectPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(角色表)信息
	 * @param id
	 * @return
	 */
	public PermiRole selectPermiRoleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(角色表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPermiRoleCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(角色表)新增一条记录
	 * @param permiRole
	 * @return
	 */
	public int insertPermiRole(PermiRole permiRole);
	
	/**
	 * 批量新增(角色表)信息
	 * @param permiRoleList
	 * @return
	 */
	public int insertPermiRoleBatch(List<PermiRole> permiRoleList);
	
	/**
	 * 更新(角色表)信息
	 * @param permiRole
	 * @return
	 */
	public int updatePermiRole(PermiRole permiRole);
	
	/**
	 * 批量更新(角色表)信息
	 * @param permiRoleList
	 * @return
	 */
	public int updatePermiRoleBatch(List<PermiRole> permiRoleList);
	
	/**
	 * 根据序列号删除(角色表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePermiRoleLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(角色表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePermiRoleLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(角色表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePermiRole(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(角色表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePermiRoleBatch(List<java.math.BigInteger> idList);
	
	
}
