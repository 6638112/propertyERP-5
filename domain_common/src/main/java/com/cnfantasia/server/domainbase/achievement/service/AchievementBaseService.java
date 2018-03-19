package com.cnfantasia.server.domainbase.achievement.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.achievement.dao.IAchievementBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.achievement.entity.Achievement;

/**
 * 描述:(成就表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AchievementBaseService implements IAchievementBaseService{
	
	private IAchievementBaseDao achievementBaseDao;
	public void setAchievementBaseDao(IAchievementBaseDao achievementBaseDao) {
		this.achievementBaseDao = achievementBaseDao;
	}
	/**
	 * 根据条件查询(成就表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Achievement> getAchievementByCondition(Map<String,Object> paramMap){
		return achievementBaseDao.selectAchievementByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(成就表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Achievement> getAchievementByConditionDim(Map<String,Object> paramMap){
		return achievementBaseDao.selectAchievementByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(成就表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Achievement> getAchievementByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return achievementBaseDao.selectAchievementByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(成就表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Achievement> getAchievementByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return achievementBaseDao.selectAchievementByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(成就表)信息
	 * @param id
	 * @return
	 */
	@Override
	public Achievement getAchievementBySeqId(java.math.BigInteger id){
		return achievementBaseDao.selectAchievementBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(成就表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAchievementCount(Map<String,Object> paramMap){
		return achievementBaseDao.selectAchievementCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(成就表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAchievementCountDim(Map<String,Object> paramMap){
		return achievementBaseDao.selectAchievementCount(paramMap,true);
	}
	/**
	 * 往(成就表)新增一条记录
	 * @param achievement
	 * @return
	 */
	@Override
	public int insertAchievement(Achievement achievement){
		return achievementBaseDao.insertAchievement(achievement);
	}
	/**
	 * 批量新增(成就表)
	 * @param achievementList
	 * @return
	 */
	@Override
	public int insertAchievementBatch(List<Achievement> achievementList){
		return achievementBaseDao.insertAchievementBatch(achievementList);
	}
	/**
	 * 更新(成就表)信息
	 * @param achievement
	 * @return
	 */
	@Override
	public int updateAchievement(Achievement achievement){
		return achievementBaseDao.updateAchievement(achievement);
	}
	/**
	 * 批量更新(成就表)信息
	 * @param achievementList
	 * @return
	 */
	@Override
	public int updateAchievementBatch(List<Achievement> achievementList){
		return achievementBaseDao.updateAchievementBatch(achievementList);
	}
	/**
	 * 根据序列号删除(成就表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAchievementLogic(java.math.BigInteger id){
		return achievementBaseDao.deleteAchievementLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(成就表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAchievementLogicBatch(List<java.math.BigInteger> idList){
		return achievementBaseDao.deleteAchievementLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(成就表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAchievement(java.math.BigInteger id){
//		return achievementBaseDao.deleteAchievement(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(成就表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAchievementBatch(List<java.math.BigInteger> idList){
//		return achievementBaseDao.deleteAchievementBatch(idList);
//	}
	
}
