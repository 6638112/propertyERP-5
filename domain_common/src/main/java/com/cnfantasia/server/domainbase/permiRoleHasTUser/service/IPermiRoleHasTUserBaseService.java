package com.cnfantasia.server.domainbase.permiRoleHasTUser.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.permiRoleHasTUser.entity.PermiRoleHasTUser;

/**
 * 描述:(用户角色关系) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPermiRoleHasTUserBaseService {
	
	/**
	 * 根据条件查询(用户角色关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PermiRoleHasTUser> getPermiRoleHasTUserByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户角色关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PermiRoleHasTUser> getPermiRoleHasTUserByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户角色关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PermiRoleHasTUser> getPermiRoleHasTUserByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户角色关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PermiRoleHasTUser> getPermiRoleHasTUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户角色关系)信息
	 * @param id
	 * @return
	 */
	public PermiRoleHasTUser getPermiRoleHasTUserBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户角色关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPermiRoleHasTUserCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户角色关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPermiRoleHasTUserCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户角色关系)新增一条记录
	 * @param permiRoleHasTUser
	 * @return
	 */
	public int insertPermiRoleHasTUser(PermiRoleHasTUser permiRoleHasTUser);
	/**
	 * 批量新增(用户角色关系)
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
	 * 根据序列号批量删除(用户角色关系)信息_逻辑删除
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
