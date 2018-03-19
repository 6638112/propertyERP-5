package com.cnfantasia.server.domainbase.messageToBeSend.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;

/**
 * 描述:(待发送短信或待推送消息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMessageToBeSendBaseDao {
	/**
	 * 根据条件查询(待发送短信或待推送消息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MessageToBeSend> selectMessageToBeSendByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(待发送短信或待推送消息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MessageToBeSend> selectMessageToBeSendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(待发送短信或待推送消息)信息
	 * @param id
	 * @return
	 */
	public MessageToBeSend selectMessageToBeSendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(待发送短信或待推送消息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMessageToBeSendCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(待发送短信或待推送消息)新增一条记录
	 * @param messageToBeSend
	 * @return
	 */
	public int insertMessageToBeSend(MessageToBeSend messageToBeSend);
	
	/**
	 * 批量新增(待发送短信或待推送消息)信息
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
	 * 根据Id 批量删除(待发送短信或待推送消息)信息_逻辑删除
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
