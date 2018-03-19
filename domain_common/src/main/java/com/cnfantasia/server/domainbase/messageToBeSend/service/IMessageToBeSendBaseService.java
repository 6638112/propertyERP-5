package com.cnfantasia.server.domainbase.messageToBeSend.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;

/**
 * 描述:(待发送短信或待推送消息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMessageToBeSendBaseService {
	
	/**
	 * 根据条件查询(待发送短信或待推送消息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MessageToBeSend> getMessageToBeSendByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(待发送短信或待推送消息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MessageToBeSend> getMessageToBeSendByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(待发送短信或待推送消息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MessageToBeSend> getMessageToBeSendByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(待发送短信或待推送消息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MessageToBeSend> getMessageToBeSendByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(待发送短信或待推送消息)信息
	 * @param id
	 * @return
	 */
	public MessageToBeSend getMessageToBeSendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(待发送短信或待推送消息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMessageToBeSendCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(待发送短信或待推送消息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMessageToBeSendCountDim(Map<String,Object> paramMap);
	/**
	 * 往(待发送短信或待推送消息)新增一条记录
	 * @param messageToBeSend
	 * @return
	 */
	public int insertMessageToBeSend(MessageToBeSend messageToBeSend);
	/**
	 * 批量新增(待发送短信或待推送消息)
	 * @param messageToBeSendList
	 * @return
	 */
	public int insertMessageToBeSendBatch(List<MessageToBeSend> messageToBeSendList);
	/**
	 * 更新(待发送短信或待推送消息)信息
	 * @param messageToBeSend
	 * @return
	 */
	public int updateMessageToBeSend(MessageToBeSend messageToBeSend);
	/**
	 * 批量更新(待发送短信或待推送消息)信息
	 * @param messageToBeSendList
	 * @return
	 */
	public int updateMessageToBeSendBatch(List<MessageToBeSend> messageToBeSendList);
	/**
	 * 根据序列号删除(待发送短信或待推送消息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMessageToBeSendLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(待发送短信或待推送消息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMessageToBeSendLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(待发送短信或待推送消息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMessageToBeSend(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(待发送短信或待推送消息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMessageToBeSendBatch(List<java.math.BigInteger> idList);
	
}
