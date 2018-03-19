package com.cnfantasia.server.domainbase.userHasTMessage.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;

/**
 * 描述:(用户消息关系) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserHasTMessageBaseDao {
	/**
	 * 根据条件查询(用户消息关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserHasTMessage> selectUserHasTMessageByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户消息关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserHasTMessage> selectUserHasTMessageByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户消息关系)信息
	 * @param id
	 * @return
	 */
	public UserHasTMessage selectUserHasTMessageBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户消息关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserHasTMessageCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户消息关系)新增一条记录
	 * @param userHasTMessage
	 * @return
	 */
	public int insertUserHasTMessage(UserHasTMessage userHasTMessage);
	
	/**
	 * 批量新增(用户消息关系)信息
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
	 * 根据Id 批量删除(用户消息关系)信息_逻辑删除
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
