package com.cnfantasia.server.domainbase.userSetRealroomDeductionConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userSetRealroomDeductionConfig.entity.UserSetRealroomDeductionConfig;

/**
 * 描述:(用户房间划扣配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserSetRealroomDeductionConfigBaseService {
	
	/**
	 * 根据条件查询(用户房间划扣配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserSetRealroomDeductionConfig> getUserSetRealroomDeductionConfigByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户房间划扣配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserSetRealroomDeductionConfig> getUserSetRealroomDeductionConfigByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户房间划扣配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserSetRealroomDeductionConfig> getUserSetRealroomDeductionConfigByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户房间划扣配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserSetRealroomDeductionConfig> getUserSetRealroomDeductionConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户房间划扣配置)信息
	 * @param id
	 * @return
	 */
	public UserSetRealroomDeductionConfig getUserSetRealroomDeductionConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户房间划扣配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserSetRealroomDeductionConfigCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户房间划扣配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserSetRealroomDeductionConfigCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户房间划扣配置)新增一条记录
	 * @param userSetRealroomDeductionConfig
	 * @return
	 */
	public int insertUserSetRealroomDeductionConfig(UserSetRealroomDeductionConfig userSetRealroomDeductionConfig);
	/**
	 * 批量新增(用户房间划扣配置)
	 * @param userSetRealroomDeductionConfigList
	 * @return
	 */
	public int insertUserSetRealroomDeductionConfigBatch(List<UserSetRealroomDeductionConfig> userSetRealroomDeductionConfigList);
	/**
	 * 更新(用户房间划扣配置)信息
	 * @param userSetRealroomDeductionConfig
	 * @return
	 */
	public int updateUserSetRealroomDeductionConfig(UserSetRealroomDeductionConfig userSetRealroomDeductionConfig);
	/**
	 * 批量更新(用户房间划扣配置)信息
	 * @param userSetRealroomDeductionConfigList
	 * @return
	 */
	public int updateUserSetRealroomDeductionConfigBatch(List<UserSetRealroomDeductionConfig> userSetRealroomDeductionConfigList);
	/**
	 * 根据序列号删除(用户房间划扣配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserSetRealroomDeductionConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户房间划扣配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserSetRealroomDeductionConfigLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户房间划扣配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserSetRealroomDeductionConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户房间划扣配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserSetRealroomDeductionConfigBatch(List<java.math.BigInteger> idList);
	
}
