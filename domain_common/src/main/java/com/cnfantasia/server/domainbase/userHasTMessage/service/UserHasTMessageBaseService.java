package com.cnfantasia.server.domainbase.userHasTMessage.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;

/**
 * 描述:(用户消息关系) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserHasTMessageBaseService implements IUserHasTMessageBaseService{
	
	private IUserHasTMessageBaseDao userHasTMessageBaseDao;
	public void setUserHasTMessageBaseDao(IUserHasTMessageBaseDao userHasTMessageBaseDao) {
		this.userHasTMessageBaseDao = userHasTMessageBaseDao;
	}
	/**
	 * 根据条件查询(用户消息关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasTMessage> getUserHasTMessageByCondition(Map<String,Object> paramMap){
		return userHasTMessageBaseDao.selectUserHasTMessageByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户消息关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasTMessage> getUserHasTMessageByConditionDim(Map<String,Object> paramMap){
		return userHasTMessageBaseDao.selectUserHasTMessageByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户消息关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasTMessage> getUserHasTMessageByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasTMessageBaseDao.selectUserHasTMessageByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户消息关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasTMessage> getUserHasTMessageByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasTMessageBaseDao.selectUserHasTMessageByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户消息关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasTMessage getUserHasTMessageBySeqId(java.math.BigInteger id){
		return userHasTMessageBaseDao.selectUserHasTMessageBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户消息关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasTMessageCount(Map<String,Object> paramMap){
		return userHasTMessageBaseDao.selectUserHasTMessageCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户消息关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasTMessageCountDim(Map<String,Object> paramMap){
		return userHasTMessageBaseDao.selectUserHasTMessageCount(paramMap,true);
	}
	/**
	 * 往(用户消息关系)新增一条记录
	 * @param userHasTMessage
	 * @return
	 */
	@Override
	public int insertUserHasTMessage(UserHasTMessage userHasTMessage){
		return userHasTMessageBaseDao.insertUserHasTMessage(userHasTMessage);
	}
	/**
	 * 批量新增(用户消息关系)
	 * @param userHasTMessageList
	 * @return
	 */
	@Override
	public int insertUserHasTMessageBatch(List<UserHasTMessage> userHasTMessageList){
		return userHasTMessageBaseDao.insertUserHasTMessageBatch(userHasTMessageList);
	}
	/**
	 * 更新(用户消息关系)信息
	 * @param userHasTMessage
	 * @return
	 */
	@Override
	public int updateUserHasTMessage(UserHasTMessage userHasTMessage){
		return userHasTMessageBaseDao.updateUserHasTMessage(userHasTMessage);
	}
	/**
	 * 批量更新(用户消息关系)信息
	 * @param userHasTMessageList
	 * @return
	 */
	@Override
	public int updateUserHasTMessageBatch(List<UserHasTMessage> userHasTMessageList){
		return userHasTMessageBaseDao.updateUserHasTMessageBatch(userHasTMessageList);
	}
	/**
	 * 根据序列号删除(用户消息关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasTMessageLogic(java.math.BigInteger id){
		return userHasTMessageBaseDao.deleteUserHasTMessageLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户消息关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasTMessageLogicBatch(List<java.math.BigInteger> idList){
		return userHasTMessageBaseDao.deleteUserHasTMessageLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户消息关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTMessage(java.math.BigInteger id){
//		return userHasTMessageBaseDao.deleteUserHasTMessage(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户消息关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTMessageBatch(List<java.math.BigInteger> idList){
//		return userHasTMessageBaseDao.deleteUserHasTMessageBatch(idList);
//	}
	
}
