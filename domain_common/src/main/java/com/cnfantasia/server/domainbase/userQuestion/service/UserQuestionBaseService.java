package com.cnfantasia.server.domainbase.userQuestion.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userQuestion.dao.IUserQuestionBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userQuestion.entity.UserQuestion;

/**
 * 描述:(用户提问信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserQuestionBaseService implements IUserQuestionBaseService{
	
	private IUserQuestionBaseDao userQuestionBaseDao;
	public void setUserQuestionBaseDao(IUserQuestionBaseDao userQuestionBaseDao) {
		this.userQuestionBaseDao = userQuestionBaseDao;
	}
	/**
	 * 根据条件查询(用户提问信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserQuestion> getUserQuestionByCondition(Map<String,Object> paramMap){
		return userQuestionBaseDao.selectUserQuestionByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户提问信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserQuestion> getUserQuestionByConditionDim(Map<String,Object> paramMap){
		return userQuestionBaseDao.selectUserQuestionByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户提问信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserQuestion> getUserQuestionByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userQuestionBaseDao.selectUserQuestionByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户提问信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserQuestion> getUserQuestionByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userQuestionBaseDao.selectUserQuestionByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户提问信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserQuestion getUserQuestionBySeqId(java.math.BigInteger id){
		return userQuestionBaseDao.selectUserQuestionBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户提问信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserQuestionCount(Map<String,Object> paramMap){
		return userQuestionBaseDao.selectUserQuestionCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户提问信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserQuestionCountDim(Map<String,Object> paramMap){
		return userQuestionBaseDao.selectUserQuestionCount(paramMap,true);
	}
	/**
	 * 往(用户提问信息表)新增一条记录
	 * @param userQuestion
	 * @return
	 */
	@Override
	public int insertUserQuestion(UserQuestion userQuestion){
		return userQuestionBaseDao.insertUserQuestion(userQuestion);
	}
	/**
	 * 批量新增(用户提问信息表)
	 * @param userQuestionList
	 * @return
	 */
	@Override
	public int insertUserQuestionBatch(List<UserQuestion> userQuestionList){
		return userQuestionBaseDao.insertUserQuestionBatch(userQuestionList);
	}
	/**
	 * 更新(用户提问信息表)信息
	 * @param userQuestion
	 * @return
	 */
	@Override
	public int updateUserQuestion(UserQuestion userQuestion){
		return userQuestionBaseDao.updateUserQuestion(userQuestion);
	}
	/**
	 * 批量更新(用户提问信息表)信息
	 * @param userQuestionList
	 * @return
	 */
	@Override
	public int updateUserQuestionBatch(List<UserQuestion> userQuestionList){
		return userQuestionBaseDao.updateUserQuestionBatch(userQuestionList);
	}
	/**
	 * 根据序列号删除(用户提问信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserQuestionLogic(java.math.BigInteger id){
		return userQuestionBaseDao.deleteUserQuestionLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户提问信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserQuestionLogicBatch(List<java.math.BigInteger> idList){
		return userQuestionBaseDao.deleteUserQuestionLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户提问信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserQuestion(java.math.BigInteger id){
//		return userQuestionBaseDao.deleteUserQuestion(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户提问信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserQuestionBatch(List<java.math.BigInteger> idList){
//		return userQuestionBaseDao.deleteUserQuestionBatch(idList);
//	}
	
}
