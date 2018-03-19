package com.cnfantasia.server.domainbase.permiRoleHasTUser.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiRoleHasTUser.entity.PermiRoleHasTUser;

/**
 * 描述:(用户角色关系) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPermiRoleHasTUserBaseDao {
	/**
	 * 根据条件查询(用户角色关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PermiRoleHasTUser> selectPermiRoleHasTUserByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户角色关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PermiRoleHasTUser> selectPermiRoleHasTUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户角色关系)信息
	 * @param id
	 * @return
	 */
	public PermiRoleHasTUser selectPermiRoleHasTUserBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户角色关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPermiRoleHasTUserCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户角色关系)新增一条记录
	 * @param permiRoleHasTUser
	 * @return
	 */
	public int insertPermiRoleHasTUser(PermiRoleHasTUser permiRoleHasTUser);
	
	/**
	 * 批量新增(用户角色关系)信息
	 * @param permiRoleHasTUserList
	 * @return
	 */
	public int insertPermiRoleHasTUserBatch(List<PermiRoleHasTUser> permiRoleHasTUserList);
	
	/**
	 * 更新(用户角色关系)信息
	 * @param permiRoleHasTUser
	 * @return
	 */
	public int updatePermiRoleHasTUser(PermiRoleHasTUser permiRoleHasTUser);
	
	/**
	 * 批量更新(用户角色关系)信息
	 * @param permiRoleHasTUserList
	 * @return
	 */
	public int updatePermiRoleHasTUserBatch(List<PermiRoleHasTUser> permiRoleHasTUserList);
	
	/**
	 * 根据序列号删除(用户角色关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePermiRoleHasTUserLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(用户角色关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePermiRoleHasTUserLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(用户角色关系)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePermiRoleHasTUser(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户角色关系)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePermiRoleHasTUserBatch(List<java.math.BigInteger> idList);
	
	
}
