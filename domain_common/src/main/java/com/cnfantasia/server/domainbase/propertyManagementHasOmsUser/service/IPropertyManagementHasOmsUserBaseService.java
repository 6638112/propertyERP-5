package com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.entity.PropertyManagementHasOmsUser;

/**
 * 描述:(物业管理与后账号关系) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyManagementHasOmsUserBaseService {
	
	/**
	 * 根据条件查询(物业管理与后账号关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyManagementHasOmsUser> getPropertyManagementHasOmsUserByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业管理与后账号关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyManagementHasOmsUser> getPropertyManagementHasOmsUserByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业管理与后账号关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyManagementHasOmsUser> getPropertyManagementHasOmsUserByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业管理与后账号关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyManagementHasOmsUser> getPropertyManagementHasOmsUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业管理与后账号关系)信息
	 * @param id
	 * @return
	 */
	public PropertyManagementHasOmsUser getPropertyManagementHasOmsUserBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业管理与后账号关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyManagementHasOmsUserCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业管理与后账号关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyManagementHasOmsUserCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业管理与后账号关系)新增一条记录
	 * @param propertyManagementHasOmsUser
	 * @return
	 */
	public int insertPropertyManagementHasOmsUser(PropertyManagementHasOmsUser propertyManagementHasOmsUser);
	/**
	 * 批量新增(物业管理与后账号关系)
	 * @param propertyManagementHasOmsUserList
	 * @return
	 */
	public int insertPropertyManagementHasOmsUserBatch(List<PropertyManagementHasOmsUser> propertyManagementHasOmsUserList);
	/**
	 * 更新(物业管理与后账号关系)信息
	 * @param propertyManagementHasOmsUser
	 * @return
	 */
	public int updatePropertyManagementHasOmsUser(PropertyManagementHasOmsUser propertyManagementHasOmsUser);
	/**
	 * 批量更新(物业管理与后账号关系)信息
	 * @param propertyManagementHasOmsUserList
	 * @return
	 */
	public int updatePropertyManagementHasOmsUserBatch(List<PropertyManagementHasOmsUser> propertyManagementHasOmsUserList);
	/**
	 * 根据序列号删除(物业管理与后账号关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyManagementHasOmsUserLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业管理与后账号关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyManagementHasOmsUserLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业管理与后账号关系)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyManagementHasOmsUser(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业管理与后账号关系)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyManagementHasOmsUserBatch(List<java.math.BigInteger> idList);
	
}
