package com.cnfantasia.server.domainbase.userSwitchGb.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userSwitchGb.entity.UserSwitchGb;

/**
 * 描述:(用户切换小区表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserSwitchGbBaseService {
	
	/**
	 * 根据条件查询(用户切换小区表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserSwitchGb> getUserSwitchGbByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(用户切换小区表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserSwitchGb> getUserSwitchGbByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(用户切换小区表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserSwitchGb> getUserSwitchGbByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(用户切换小区表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserSwitchGb> getUserSwitchGbByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(用户切换小区表)信息
	 * @param id
	 * @return
	 */
	public UserSwitchGb getUserSwitchGbBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户切换小区表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserSwitchGbCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户切换小区表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserSwitchGbCountDim(Map<String, Object> paramMap);
	/**
	 * 往(用户切换小区表)新增一条记录
	 * @param userSwitchGb
	 * @return
	 */
	public int insertUserSwitchGb(UserSwitchGb userSwitchGb);
	/**
	 * 批量新增(用户切换小区表)
	 * @param userSwitchGbList
	 * @return
	 */
	public int insertUserSwitchGbBatch(List<UserSwitchGb> userSwitchGbList);
	/**
	 * 更新(用户切换小区表)信息
	 * @param userSwitchGb
	 * @return
	 */
	public int updateUserSwitchGb(UserSwitchGb userSwitchGb);
	/**
	 * 批量更新(用户切换小区表)信息
	 * @param userSwitchGbList
	 * @return
	 */
	public int updateUserSwitchGbBatch(List<UserSwitchGb> userSwitchGbList);
	/**
	 * 根据序列号删除(用户切换小区表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserSwitchGbLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户切换小区表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserSwitchGbLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户切换小区表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserSwitchGb(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户切换小区表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserSwitchGbBatch(List<java.math.BigInteger> idList);
	
}
