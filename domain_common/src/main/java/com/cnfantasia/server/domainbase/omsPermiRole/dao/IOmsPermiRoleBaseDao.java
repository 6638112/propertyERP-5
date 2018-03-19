package com.cnfantasia.server.domainbase.omsPermiRole.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;

/**
 * 描述:(OMS角色表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsPermiRoleBaseDao {
	/**
	 * 根据条件查询(OMS角色表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsPermiRole> selectOmsPermiRoleByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(OMS角色表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsPermiRole> selectOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(OMS角色表)信息
	 * @param id
	 * @return
	 */
	public OmsPermiRole selectOmsPermiRoleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsPermiRoleCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(OMS角色表)新增一条记录
	 * @param omsPermiRole
	 * @return
	 */
	public int insertOmsPermiRole(OmsPermiRole omsPermiRole);
	
	/**
	 * 批量新增(OMS角色表)信息
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
	 * 根据Id 批量删除(OMS角色表)信息_逻辑删除
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
