package com.cnfantasia.server.domainbase.message.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.message.dao.IMessageBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.message.entity.Message;

/**
 * 描述:(消息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MessageBaseService implements IMessageBaseService{
	
	private IMessageBaseDao messageBaseDao;
	public void setMessageBaseDao(IMessageBaseDao messageBaseDao) {
		this.messageBaseDao = messageBaseDao;
	}
	/**
	 * 根据条件查询(消息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Message> getMessageByCondition(Map<String,Object> paramMap){
		return messageBaseDao.selectMessageByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(消息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Message> getMessageByConditionDim(Map<String,Object> paramMap){
		return messageBaseDao.selectMessageByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(消息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Message> getMessageByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return messageBaseDao.selectMessageByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(消息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Message> getMessageByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return messageBaseDao.selectMessageByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(消息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public Message getMessageBySeqId(java.math.BigInteger id){
		return messageBaseDao.selectMessageBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(消息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessageCount(Map<String,Object> paramMap){
		return messageBaseDao.selectMessageCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(消息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessageCountDim(Map<String,Object> paramMap){
		return messageBaseDao.selectMessageCount(paramMap,true);
	}
	/**
	 * 往(消息表)新增一条记录
	 * @param message
	 * @return
	 */
	@Override
	public int insertMessage(Message message){
		return messageBaseDao.insertMessage(message);
	}
	/**
	 * 批量新增(消息表)
	 * @param messageList
	 * @return
	 */
	@Override
	public int insertMessageBatch(List<Message> messageList){
		return messageBaseDao.insertMessageBatch(messageList);
	}
	/**
	 * 更新(消息表)信息
	 * @param message
	 * @return
	 */
	@Override
	public int updateMessage(Message message){
		return messageBaseDao.updateMessage(message);
	}
	/**
	 * 批量更新(消息表)信息
	 * @param messageList
	 * @return
	 */
	@Override
	public int updateMessageBatch(List<Message> messageList){
		return messageBaseDao.updateMessageBatch(messageList);
	}
	/**
	 * 根据序列号删除(消息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessageLogic(java.math.BigInteger id){
		return messageBaseDao.deleteMessageLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(消息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessageLogicBatch(List<java.math.BigInteger> idList){
		return messageBaseDao.deleteMessageLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(消息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessage(java.math.BigInteger id){
//		return messageBaseDao.deleteMessage(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessageBatch(List<java.math.BigInteger> idList){
//		return messageBaseDao.deleteMessageBatch(idList);
//	}
	
}
