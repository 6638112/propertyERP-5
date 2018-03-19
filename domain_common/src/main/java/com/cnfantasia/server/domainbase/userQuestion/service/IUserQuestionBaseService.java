package com.cnfantasia.server.domainbase.userQuestion.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userQuestion.entity.UserQuestion;

/**
 * 描述:(用户提问信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserQuestionBaseService {
	
	/**
	 * 根据条件查询(用户提问信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserQuestion> getUserQuestionByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(用户提问信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserQuestion> getUserQuestionByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(用户提问信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserQuestion> getUserQuestionByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(用户提问信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserQuestion> getUserQuestionByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(用户提问信息表)信息
	 * @param id
	 * @return
	 */
	public UserQuestion getUserQuestionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户提问信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserQuestionCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户提问信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserQuestionCountDim(Map<String, Object> paramMap);
	/**
	 * 往(用户提问信息表)新增一条记录
	 * @param userQuestion
	 * @return
	 */
	public int insertUserQuestion(UserQuestion userQuestion);
	/**
	 * 批量新增(用户提问信息表)
	 * @param userQuestionList
	 * @return
	 */
	public int insertUserQuestionBatch(List<UserQuestion> userQuestionList);
	/**
	 * 更新(用户提问信息表)信息
	 * @param userQuestion
	 * @return
	 */
	public int updateUserQuestion(UserQuestion userQuestion);
	/**
	 * 批量更新(用户提问信息表)信息
	 * @param userQuestionList
	 * @return
	 */
	public int updateUserQuestionBatch(List<UserQuestion> userQuestionList);
	/**
	 * 根据序列号删除(用户提问信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserQuestionLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户提问信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserQuestionLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户提问信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserQuestion(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户提问信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserQuestionBatch(List<java.math.BigInteger> idList);
	
}
