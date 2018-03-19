package com.cnfantasia.server.domainbase.message.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.message.entity.Message;

/**
 * 描述:(消息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMessageBaseService {
	
	/**
	 * 根据条件查询(消息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<Message> getMessageByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(消息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<Message> getMessageByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(消息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Message> getMessageByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(消息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Message> getMessageByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(消息表)信息
	 * @param id
	 * @return
	 */
	public Message getMessageBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMessageCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(消息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMessageCountDim(Map<String,Object> paramMap);
	/**
	 * 往(消息表)新增一条记录
	 * @param message
	 * @return
	 */
	public int insertMessage(Message message);
	/**
	 * 批量新增(消息表)
	 * @param messageList
	 * @return
	 */
	public int insertMessageBatch(List<Message> messageList);
	/**
	 * 更新(消息表)信息
	 * @param message
	 * @return
	 */
	public int updateMessage(Message message);
	/**
	 * 批量更新(消息表)信息
	 * @param messageList
	 * @return
	 */
	public int updateMessageBatch(List<Message> messageList);
	/**
	 * 根据序列号删除(消息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMessageLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(消息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMessageLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(消息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMessage(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(消息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMessageBatch(List<java.math.BigInteger> idList);
	
}
