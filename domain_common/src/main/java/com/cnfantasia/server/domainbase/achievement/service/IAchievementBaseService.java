package com.cnfantasia.server.domainbase.achievement.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.achievement.entity.Achievement;

/**
 * 描述:(成就表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAchievementBaseService {
	
	/**
	 * 根据条件查询(成就表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<Achievement> getAchievementByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(成就表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<Achievement> getAchievementByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(成就表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Achievement> getAchievementByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(成就表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Achievement> getAchievementByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(成就表)信息
	 * @param id
	 * @return
	 */
	public Achievement getAchievementBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(成就表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAchievementCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(成就表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAchievementCountDim(Map<String,Object> paramMap);
	/**
	 * 往(成就表)新增一条记录
	 * @param achievement
	 * @return
	 */
	public int insertAchievement(Achievement achievement);
	/**
	 * 批量新增(成就表)
	 * @param achievementList
	 * @return
	 */
	public int insertAchievementBatch(List<Achievement> achievementList);
	/**
	 * 更新(成就表)信息
	 * @param achievement
	 * @return
	 */
	public int updateAchievement(Achievement achievement);
	/**
	 * 批量更新(成就表)信息
	 * @param achievementList
	 * @return
	 */
	public int updateAchievementBatch(List<Achievement> achievementList);
	/**
	 * 根据序列号删除(成就表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAchievementLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(成就表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAchievementLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(成就表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAchievement(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(成就表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAchievementBatch(List<java.math.BigInteger> idList);
	
}
