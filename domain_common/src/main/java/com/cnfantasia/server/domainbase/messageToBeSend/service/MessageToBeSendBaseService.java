package com.cnfantasia.server.domainbase.messageToBeSend.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.messageToBeSend.dao.IMessageToBeSendBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;

/**
 * 描述:(待发送短信或待推送消息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MessageToBeSendBaseService implements IMessageToBeSendBaseService{
	
	private IMessageToBeSendBaseDao messageToBeSendBaseDao;
	public void setMessageToBeSendBaseDao(IMessageToBeSendBaseDao messageToBeSendBaseDao) {
		this.messageToBeSendBaseDao = messageToBeSendBaseDao;
	}
	/**
	 * 根据条件查询(待发送短信或待推送消息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MessageToBeSend> getMessageToBeSendByCondition(Map<String,Object> paramMap){
		return messageToBeSendBaseDao.selectMessageToBeSendByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(待发送短信或待推送消息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MessageToBeSend> getMessageToBeSendByConditionDim(Map<String,Object> paramMap){
		return messageToBeSendBaseDao.selectMessageToBeSendByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(待发送短信或待推送消息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MessageToBeSend> getMessageToBeSendByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return messageToBeSendBaseDao.selectMessageToBeSendByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(待发送短信或待推送消息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MessageToBeSend> getMessageToBeSendByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return messageToBeSendBaseDao.selectMessageToBeSendByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(待发送短信或待推送消息)信息
	 * @param id
	 * @return
	 */
	@Override
	public MessageToBeSend getMessageToBeSendBySeqId(java.math.BigInteger id){
		return messageToBeSendBaseDao.selectMessageToBeSendBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(待发送短信或待推送消息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessageToBeSendCount(Map<String,Object> paramMap){
		return messageToBeSendBaseDao.selectMessageToBeSendCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(待发送短信或待推送消息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessageToBeSendCountDim(Map<String,Object> paramMap){
		return messageToBeSendBaseDao.selectMessageToBeSendCount(paramMap,true);
	}
	/**
	 * 往(待发送短信或待推送消息)新增一条记录
	 * @param messageToBeSend
	 * @return
	 */
	@Override
	public int insertMessageToBeSend(MessageToBeSend messageToBeSend){
		return messageToBeSendBaseDao.insertMessageToBeSend(messageToBeSend);
	}
	/**
	 * 批量新增(待发送短信或待推送消息)
	 * @param messageToBeSendList
	 * @return
	 */
	@Override
	public int insertMessageToBeSendBatch(List<MessageToBeSend> messageToBeSendList){
		return messageToBeSendBaseDao.insertMessageToBeSendBatch(messageToBeSendList);
	}
	/**
	 * 更新(待发送短信或待推送消息)信息
	 * @param messageToBeSend
	 * @return
	 */
	@Override
	public int updateMessageToBeSend(MessageToBeSend messageToBeSend){
		return messageToBeSendBaseDao.updateMessageToBeSend(messageToBeSend);
	}
	/**
	 * 批量更新(待发送短信或待推送消息)信息
	 * @param messageToBeSendList
	 * @return
	 */
	@Override
	public int updateMessageToBeSendBatch(List<MessageToBeSend> messageToBeSendList){
		return messageToBeSendBaseDao.updateMessageToBeSendBatch(messageToBeSendList);
	}
	/**
	 * 根据序列号删除(待发送短信或待推送消息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessageToBeSendLogic(java.math.BigInteger id){
		return messageToBeSendBaseDao.deleteMessageToBeSendLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(待发送短信或待推送消息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessageToBeSendLogicBatch(List<java.math.BigInteger> idList){
		return messageToBeSendBaseDao.deleteMessageToBeSendLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(待发送短信或待推送消息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessageToBeSend(java.math.BigInteger id){
//		return messageToBeSendBaseDao.deleteMessageToBeSend(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(待发送短信或待推送消息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessageToBeSendBatch(List<java.math.BigInteger> idList){
//		return messageToBeSendBaseDao.deleteMessageToBeSendBatch(idList);
//	}
	
}
