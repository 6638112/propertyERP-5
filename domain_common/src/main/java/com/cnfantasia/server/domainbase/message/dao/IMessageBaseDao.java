package com.cnfantasia.server.domainbase.message.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.message.entity.Message;

/**
 * 描述:(消息表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMessageBaseDao {
	/**
	 * 根据条件查询(消息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Message> selectMessageByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(消息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Message> selectMessageByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(消息表)信息
	 * @param id
	 * @return
	 */
	public Message selectMessageBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMessageCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(消息表)新增一条记录
	 * @param message
	 * @return
	 */
	public int insertMessage(Message message);
	
	/**
	 * 批量新增(消息表)信息
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
	 * 根据Id 批量删除(消息表)信息_逻辑删除
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
