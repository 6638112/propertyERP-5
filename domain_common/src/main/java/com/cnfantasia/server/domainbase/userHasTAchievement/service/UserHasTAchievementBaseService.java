package com.cnfantasia.server.domainbase.userHasTAchievement.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userHasTAchievement.dao.IUserHasTAchievementBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTAchievement.entity.UserHasTAchievement;

/**
 * 描述:(用户成就关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserHasTAchievementBaseService implements IUserHasTAchievementBaseService{
	
	private IUserHasTAchievementBaseDao userHasTAchievementBaseDao;
	public void setUserHasTAchievementBaseDao(IUserHasTAchievementBaseDao userHasTAchievementBaseDao) {
		this.userHasTAchievementBaseDao = userHasTAchievementBaseDao;
	}
	/**
	 * 根据条件查询(用户成就关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasTAchievement> getUserHasTAchievementByCondition(Map<String,Object> paramMap){
		return userHasTAchievementBaseDao.selectUserHasTAchievementByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户成就关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasTAchievement> getUserHasTAchievementByConditionDim(Map<String,Object> paramMap){
		return userHasTAchievementBaseDao.selectUserHasTAchievementByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户成就关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasTAchievement> getUserHasTAchievementByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasTAchievementBaseDao.selectUserHasTAchievementByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户成就关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasTAchievement> getUserHasTAchievementByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasTAchievementBaseDao.selectUserHasTAchievementByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户成就关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasTAchievement getUserHasTAchievementBySeqId(java.math.BigInteger id){
		return userHasTAchievementBaseDao.selectUserHasTAchievementBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户成就关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasTAchievementCount(Map<String,Object> paramMap){
		return userHasTAchievementBaseDao.selectUserHasTAchievementCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户成就关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasTAchievementCountDim(Map<String,Object> paramMap){
		return userHasTAchievementBaseDao.selectUserHasTAchievementCount(paramMap,true);
	}
	/**
	 * 往(用户成就关系表)新增一条记录
	 * @param userHasTAchievement
	 * @return
	 */
	@Override
	public int insertUserHasTAchievement(UserHasTAchievement userHasTAchievement){
		return userHasTAchievementBaseDao.insertUserHasTAchievement(userHasTAchievement);
	}
	/**
	 * 批量新增(用户成就关系表)
	 * @param userHasTAchievementList
	 * @return
	 */
	@Override
	public int insertUserHasTAchievementBatch(List<UserHasTAchievement> userHasTAchievementList){
		return userHasTAchievementBaseDao.insertUserHasTAchievementBatch(userHasTAchievementList);
	}
	/**
	 * 更新(用户成就关系表)信息
	 * @param userHasTAchievement
	 * @return
	 */
	@Override
	public int updateUserHasTAchievement(UserHasTAchievement userHasTAchievement){
		return userHasTAchievementBaseDao.updateUserHasTAchievement(userHasTAchievement);
	}
	/**
	 * 批量更新(用户成就关系表)信息
	 * @param userHasTAchievementList
	 * @return
	 */
	@Override
	public int updateUserHasTAchievementBatch(List<UserHasTAchievement> userHasTAchievementList){
		return userHasTAchievementBaseDao.updateUserHasTAchievementBatch(userHasTAchievementList);
	}
	/**
	 * 根据序列号删除(用户成就关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasTAchievementLogic(java.math.BigInteger id){
		return userHasTAchievementBaseDao.deleteUserHasTAchievementLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户成就关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasTAchievementLogicBatch(List<java.math.BigInteger> idList){
		return userHasTAchievementBaseDao.deleteUserHasTAchievementLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户成就关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTAchievement(java.math.BigInteger id){
//		return userHasTAchievementBaseDao.deleteUserHasTAchievement(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户成就关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTAchievementBatch(List<java.math.BigInteger> idList){
//		return userHasTAchievementBaseDao.deleteUserHasTAchievementBatch(idList);
//	}
	
}
