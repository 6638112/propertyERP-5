package com.cnfantasia.server.domainbase.userHasTAchievement.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userHasTAchievement.entity.UserHasTAchievement;

/**
 * 描述:(用户成就关系表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserHasTAchievementBaseService {
	
	/**
	 * 根据条件查询(用户成就关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserHasTAchievement> getUserHasTAchievementByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户成就关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserHasTAchievement> getUserHasTAchievementByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户成就关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserHasTAchievement> getUserHasTAchievementByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户成就关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserHasTAchievement> getUserHasTAchievementByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户成就关系表)信息
	 * @param id
	 * @return
	 */
	public UserHasTAchievement getUserHasTAchievementBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户成就关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserHasTAchievementCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户成就关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserHasTAchievementCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户成就关系表)新增一条记录
	 * @param userHasTAchievement
	 * @return
	 */
	public int insertUserHasTAchievement(UserHasTAchievement userHasTAchievement);
	/**
	 * 批量新增(用户成就关系表)
	 * @param userHasTAchievementList
	 * @return
	 */
	public int insertUserHasTAchievementBatch(List<UserHasTAchievement> userHasTAchievementList);
	/**
	 * 更新(用户成就关系表)信息
	 * @param userHasTAchievement
	 * @return
	 */
	public int updateUserHasTAchievement(UserHasTAchievement userHasTAchievement);
	/**
	 * 批量更新(用户成就关系表)信息
	 * @param userHasTAchievementList
	 * @return
	 */
	public int updateUserHasTAchievementBatch(List<UserHasTAchievement> userHasTAchievementList);
	/**
	 * 根据序列号删除(用户成就关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserHasTAchievementLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户成就关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserHasTAchievementLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户成就关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserHasTAchievement(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户成就关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserHasTAchievementBatch(List<java.math.BigInteger> idList);
	
}
