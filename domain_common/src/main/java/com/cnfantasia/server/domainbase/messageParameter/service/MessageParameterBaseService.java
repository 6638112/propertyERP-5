package com.cnfantasia.server.domainbase.messageParameter.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.messageParameter.dao.IMessageParameterBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;

/**
 * 描述:(消息参数表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MessageParameterBaseService implements IMessageParameterBaseService{
	
	private IMessageParameterBaseDao messageParameterBaseDao;
	public void setMessageParameterBaseDao(IMessageParameterBaseDao messageParameterBaseDao) {
		this.messageParameterBaseDao = messageParameterBaseDao;
	}
	/**
	 * 根据条件查询(消息参数表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MessageParameter> getMessageParameterByCondition(Map<String,Object> paramMap){
		return messageParameterBaseDao.selectMessageParameterByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(消息参数表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MessageParameter> getMessageParameterByConditionDim(Map<String,Object> paramMap){
		return messageParameterBaseDao.selectMessageParameterByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(消息参数表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MessageParameter> getMessageParameterByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return messageParameterBaseDao.selectMessageParameterByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(消息参数表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MessageParameter> getMessageParameterByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return messageParameterBaseDao.selectMessageParameterByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(消息参数表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MessageParameter getMessageParameterBySeqId(java.math.BigInteger id){
		return messageParameterBaseDao.selectMessageParameterBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(消息参数表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessageParameterCount(Map<String,Object> paramMap){
		return messageParameterBaseDao.selectMessageParameterCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(消息参数表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessageParameterCountDim(Map<String,Object> paramMap){
		return messageParameterBaseDao.selectMessageParameterCount(paramMap,true);
	}
	/**
	 * 往(消息参数表)新增一条记录
	 * @param messageParameter
	 * @return
	 */
	@Override
	public int insertMessageParameter(MessageParameter messageParameter){
		return messageParameterBaseDao.insertMessageParameter(messageParameter);
	}
	/**
	 * 批量新增(消息参数表)
	 * @param messageParameterList
	 * @return
	 */
	@Override
	public int insertMessageParameterBatch(List<MessageParameter> messageParameterList){
		return messageParameterBaseDao.insertMessageParameterBatch(messageParameterList);
	}
	/**
	 * 更新(消息参数表)信息
	 * @param messageParameter
	 * @return
	 */
	@Override
	public int updateMessageParameter(MessageParameter messageParameter){
		return messageParameterBaseDao.updateMessageParameter(messageParameter);
	}
	/**
	 * 批量更新(消息参数表)信息
	 * @param messageParameterList
	 * @return
	 */
	@Override
	public int updateMessageParameterBatch(List<MessageParameter> messageParameterList){
		return messageParameterBaseDao.updateMessageParameterBatch(messageParameterList);
	}
	/**
	 * 根据序列号删除(消息参数表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessageParameterLogic(java.math.BigInteger id){
		return messageParameterBaseDao.deleteMessageParameterLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(消息参数表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessageParameterLogicBatch(List<java.math.BigInteger> idList){
		return messageParameterBaseDao.deleteMessageParameterLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(消息参数表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessageParameter(java.math.BigInteger id){
//		return messageParameterBaseDao.deleteMessageParameter(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消息参数表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessageParameterBatch(List<java.math.BigInteger> idList){
//		return messageParameterBaseDao.deleteMessageParameterBatch(idList);
//	}
	
}
