package com.cnfantasia.server.domainbase.userHasTAchievement.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTAchievement.entity.UserHasTAchievement;

/**
 * 描述:(用户成就关系表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserHasTAchievementBaseDao {
	/**
	 * 根据条件查询(用户成就关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserHasTAchievement> selectUserHasTAchievementByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户成就关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserHasTAchievement> selectUserHasTAchievementByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户成就关系表)信息
	 * @param id
	 * @return
	 */
	public UserHasTAchievement selectUserHasTAchievementBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户成就关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserHasTAchievementCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户成就关系表)新增一条记录
	 * @param userHasTAchievement
	 * @return
	 */
	public int insertUserHasTAchievement(UserHasTAchievement userHasTAchievement);
	
	/**
	 * 批量新增(用户成就关系表)信息
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
	 * 根据Id 批量删除(用户成就关系表)信息_逻辑删除
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
