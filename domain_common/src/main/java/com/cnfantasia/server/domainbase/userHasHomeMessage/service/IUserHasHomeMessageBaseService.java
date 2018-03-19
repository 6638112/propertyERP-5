package com.cnfantasia.server.domainbase.userHasHomeMessage.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;

/**
 * 描述:(用户首页消息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserHasHomeMessageBaseService {
	
	/**
	 * 根据条件查询(用户首页消息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserHasHomeMessage> getUserHasHomeMessageByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(用户首页消息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserHasHomeMessage> getUserHasHomeMessageByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(用户首页消息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserHasHomeMessage> getUserHasHomeMessageByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(用户首页消息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserHasHomeMessage> getUserHasHomeMessageByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(用户首页消息表)信息
	 * @param id
	 * @return
	 */
	public UserHasHomeMessage getUserHasHomeMessageBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户首页消息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserHasHomeMessageCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户首页消息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserHasHomeMessageCountDim(Map<String, Object> paramMap);
	/**
	 * 往(用户首页消息表)新增一条记录
	 * @param userHasHomeMessage
	 * @return
	 */
	public int insertUserHasHomeMessage(UserHasHomeMessage userHasHomeMessage);
	/**
	 * 批量新增(用户首页消息表)
	 * @param userHasHomeMessageList
	 * @return
	 */
	public int insertUserHasHomeMessageBatch(List<UserHasHomeMessage> userHasHomeMessageList);
	/**
	 * 更新(用户首页消息表)信息
	 * @param userHasHomeMessage
	 * @return
	 */
	public int updateUserHasHomeMessage(UserHasHomeMessage userHasHomeMessage);
	/**
	 * 批量更新(用户首页消息表)信息
	 * @param userHasHomeMessageList
	 * @return
	 */
	public int updateUserHasHomeMessageBatch(List<UserHasHomeMessage> userHasHomeMessageList);
	/**
	 * 根据序列号删除(用户首页消息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserHasHomeMessageLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户首页消息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserHasHomeMessageLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户首页消息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserHasHomeMessage(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户首页消息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserHasHomeMessageBatch(List<java.math.BigInteger> idList);
	
}
