package com.cnfantasia.server.domainbase.userHasHomeMessage.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;

/**
 * 描述:(用户首页消息表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserHasHomeMessageBaseDao {
	/**
	 * 根据条件查询(用户首页消息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserHasHomeMessage> selectUserHasHomeMessageByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(用户首页消息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserHasHomeMessage> selectUserHasHomeMessageByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(用户首页消息表)信息
	 * @param id
	 * @return
	 */
	public UserHasHomeMessage selectUserHasHomeMessageBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户首页消息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserHasHomeMessageCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(用户首页消息表)新增一条记录
	 * @param userHasHomeMessage
	 * @return
	 */
	public int insertUserHasHomeMessage(UserHasHomeMessage userHasHomeMessage);
	
	/**
	 * 批量新增(用户首页消息表)信息
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
	 * 根据Id 批量删除(用户首页消息表)信息_逻辑删除
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
