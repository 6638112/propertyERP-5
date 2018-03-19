package com.cnfantasia.server.domainbase.userHasTMessage.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;

/**
 * 描述:(用户消息关系) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserHasTMessageBaseService {
	
	/**
	 * 根据条件查询(用户消息关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserHasTMessage> getUserHasTMessageByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户消息关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserHasTMessage> getUserHasTMessageByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户消息关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserHasTMessage> getUserHasTMessageByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户消息关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserHasTMessage> getUserHasTMessageByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户消息关系)信息
	 * @param id
	 * @return
	 */
	public UserHasTMessage getUserHasTMessageBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户消息关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserHasTMessageCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户消息关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserHasTMessageCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户消息关系)新增一条记录
	 * @param userHasTMessage
	 * @return
	 */
	public int insertUserHasTMessage(UserHasTMessage userHasTMessage);
	/**
	 * 批量新增(用户消息关系)
	 * @param userHasTMessageList
	 * @return
	 */
	public int insertUserHasTMessageBatch(List<UserHasTMessage> userHasTMessageList);
	/**
	 * 更新(用户消息关系)信息
	 * @param userHasTMessage
	 * @return
	 */
	public int updateUserHasTMessage(UserHasTMessage userHasTMessage);
	/**
	 * 批量更新(用户消息关系)信息
	 * @param userHasTMessageList
	 * @return
	 */
	public int updateUserHasTMessageBatch(List<UserHasTMessage> userHasTMessageList);
	/**
	 * 根据序列号删除(用户消息关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserHasTMessageLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户消息关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserHasTMessageLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户消息关系)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserHasTMessage(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户消息关系)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserHasTMessageBatch(List<java.math.BigInteger> idList);
	
}
