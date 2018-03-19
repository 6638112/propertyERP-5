package com.cnfantasia.server.domainbase.messageView.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.messageView.dao.IMessageViewBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageView.entity.MessageView;

/**
 * 描述:(消息推送的视图配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MessageViewBaseService implements IMessageViewBaseService{
	
	private IMessageViewBaseDao messageViewBaseDao;
	public void setMessageViewBaseDao(IMessageViewBaseDao messageViewBaseDao) {
		this.messageViewBaseDao = messageViewBaseDao;
	}
	/**
	 * 根据条件查询(消息推送的视图配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MessageView> getMessageViewByCondition(Map<String,Object> paramMap){
		return messageViewBaseDao.selectMessageViewByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(消息推送的视图配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MessageView> getMessageViewByConditionDim(Map<String,Object> paramMap){
		return messageViewBaseDao.selectMessageViewByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(消息推送的视图配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MessageView> getMessageViewByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return messageViewBaseDao.selectMessageViewByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(消息推送的视图配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MessageView> getMessageViewByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return messageViewBaseDao.selectMessageViewByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(消息推送的视图配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public MessageView getMessageViewBySeqId(java.math.BigInteger id){
		return messageViewBaseDao.selectMessageViewBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(消息推送的视图配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessageViewCount(Map<String,Object> paramMap){
		return messageViewBaseDao.selectMessageViewCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(消息推送的视图配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessageViewCountDim(Map<String,Object> paramMap){
		return messageViewBaseDao.selectMessageViewCount(paramMap,true);
	}
	/**
	 * 往(消息推送的视图配置)新增一条记录
	 * @param messageView
	 * @return
	 */
	@Override
	public int insertMessageView(MessageView messageView){
		return messageViewBaseDao.insertMessageView(messageView);
	}
	/**
	 * 批量新增(消息推送的视图配置)
	 * @param messageViewList
	 * @return
	 */
	@Override
	public int insertMessageViewBatch(List<MessageView> messageViewList){
		return messageViewBaseDao.insertMessageViewBatch(messageViewList);
	}
	/**
	 * 更新(消息推送的视图配置)信息
	 * @param messageView
	 * @return
	 */
	@Override
	public int updateMessageView(MessageView messageView){
		return messageViewBaseDao.updateMessageView(messageView);
	}
	/**
	 * 批量更新(消息推送的视图配置)信息
	 * @param messageViewList
	 * @return
	 */
	@Override
	public int updateMessageViewBatch(List<MessageView> messageViewList){
		return messageViewBaseDao.updateMessageViewBatch(messageViewList);
	}
	/**
	 * 根据序列号删除(消息推送的视图配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessageViewLogic(java.math.BigInteger id){
		return messageViewBaseDao.deleteMessageViewLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(消息推送的视图配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessageViewLogicBatch(List<java.math.BigInteger> idList){
		return messageViewBaseDao.deleteMessageViewLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(消息推送的视图配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessageView(java.math.BigInteger id){
//		return messageViewBaseDao.deleteMessageView(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消息推送的视图配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessageViewBatch(List<java.math.BigInteger> idList){
//		return messageViewBaseDao.deleteMessageViewBatch(idList);
//	}
	
}
