package com.cnfantasia.server.domainbase.userQuestion.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userQuestion.entity.UserQuestion;

/**
 * 描述:(用户提问信息表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserQuestionBaseDao {
	/**
	 * 根据条件查询(用户提问信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserQuestion> selectUserQuestionByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(用户提问信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserQuestion> selectUserQuestionByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(用户提问信息表)信息
	 * @param id
	 * @return
	 */
	public UserQuestion selectUserQuestionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户提问信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserQuestionCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(用户提问信息表)新增一条记录
	 * @param userQuestion
	 * @return
	 */
	public int insertUserQuestion(UserQuestion userQuestion);
	
	/**
	 * 批量新增(用户提问信息表)信息
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
	 * 根据Id 批量删除(用户提问信息表)信息_逻辑删除
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
