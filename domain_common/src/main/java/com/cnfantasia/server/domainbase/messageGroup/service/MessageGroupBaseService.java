package com.cnfantasia.server.domainbase.messageGroup.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.messageGroup.dao.IMessageGroupBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;

/**
 * 描述:(消息小区关联表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MessageGroupBaseService implements IMessageGroupBaseService{
	
	private IMessageGroupBaseDao messageGroupBaseDao;
	public void setMessageGroupBaseDao(IMessageGroupBaseDao messageGroupBaseDao) {
		this.messageGroupBaseDao = messageGroupBaseDao;
	}
	/**
	 * 根据条件查询(消息小区关联表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MessageGroup> getMessageGroupByCondition(Map<String,Object> paramMap){
		return messageGroupBaseDao.selectMessageGroupByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(消息小区关联表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MessageGroup> getMessageGroupByConditionDim(Map<String,Object> paramMap){
		return messageGroupBaseDao.selectMessageGroupByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(消息小区关联表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MessageGroup> getMessageGroupByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return messageGroupBaseDao.selectMessageGroupByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(消息小区关联表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MessageGroup> getMessageGroupByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return messageGroupBaseDao.selectMessageGroupByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(消息小区关联表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MessageGroup getMessageGroupBySeqId(java.math.BigInteger id){
		return messageGroupBaseDao.selectMessageGroupBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(消息小区关联表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessageGroupCount(Map<String,Object> paramMap){
		return messageGroupBaseDao.selectMessageGroupCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(消息小区关联表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessageGroupCountDim(Map<String,Object> paramMap){
		return messageGroupBaseDao.selectMessageGroupCount(paramMap,true);
	}
	/**
	 * 往(消息小区关联表)新增一条记录
	 * @param messageGroup
	 * @return
	 */
	@Override
	public int insertMessageGroup(MessageGroup messageGroup){
		return messageGroupBaseDao.insertMessageGroup(messageGroup);
	}
	/**
	 * 批量新增(消息小区关联表)
	 * @param messageGroupList
	 * @return
	 */
	@Override
	public int insertMessageGroupBatch(List<MessageGroup> messageGroupList){
		return messageGroupBaseDao.insertMessageGroupBatch(messageGroupList);
	}
	/**
	 * 更新(消息小区关联表)信息
	 * @param messageGroup
	 * @return
	 */
	@Override
	public int updateMessageGroup(MessageGroup messageGroup){
		return messageGroupBaseDao.updateMessageGroup(messageGroup);
	}
	/**
	 * 批量更新(消息小区关联表)信息
	 * @param messageGroupList
	 * @return
	 */
	@Override
	public int updateMessageGroupBatch(List<MessageGroup> messageGroupList){
		return messageGroupBaseDao.updateMessageGroupBatch(messageGroupList);
	}
	/**
	 * 根据序列号删除(消息小区关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessageGroupLogic(java.math.BigInteger id){
		return messageGroupBaseDao.deleteMessageGroupLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(消息小区关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessageGroupLogicBatch(List<java.math.BigInteger> idList){
		return messageGroupBaseDao.deleteMessageGroupLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(消息小区关联表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessageGroup(java.math.BigInteger id){
//		return messageGroupBaseDao.deleteMessageGroup(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消息小区关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessageGroupBatch(List<java.math.BigInteger> idList){
//		return messageGroupBaseDao.deleteMessageGroupBatch(idList);
//	}
	
}
