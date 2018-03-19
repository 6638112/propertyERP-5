package com.cnfantasia.server.domainbase.userHasHomeMessage.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userHasHomeMessage.dao.IUserHasHomeMessageBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;

/**
 * 描述:(用户首页消息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserHasHomeMessageBaseService implements IUserHasHomeMessageBaseService{
	
	private IUserHasHomeMessageBaseDao userHasHomeMessageBaseDao;
	public void setUserHasHomeMessageBaseDao(IUserHasHomeMessageBaseDao userHasHomeMessageBaseDao) {
		this.userHasHomeMessageBaseDao = userHasHomeMessageBaseDao;
	}
	/**
	 * 根据条件查询(用户首页消息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasHomeMessage> getUserHasHomeMessageByCondition(Map<String,Object> paramMap){
		return userHasHomeMessageBaseDao.selectUserHasHomeMessageByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户首页消息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasHomeMessage> getUserHasHomeMessageByConditionDim(Map<String,Object> paramMap){
		return userHasHomeMessageBaseDao.selectUserHasHomeMessageByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户首页消息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasHomeMessage> getUserHasHomeMessageByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasHomeMessageBaseDao.selectUserHasHomeMessageByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户首页消息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasHomeMessage> getUserHasHomeMessageByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasHomeMessageBaseDao.selectUserHasHomeMessageByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户首页消息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasHomeMessage getUserHasHomeMessageBySeqId(java.math.BigInteger id){
		return userHasHomeMessageBaseDao.selectUserHasHomeMessageBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户首页消息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasHomeMessageCount(Map<String,Object> paramMap){
		return userHasHomeMessageBaseDao.selectUserHasHomeMessageCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户首页消息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasHomeMessageCountDim(Map<String,Object> paramMap){
		return userHasHomeMessageBaseDao.selectUserHasHomeMessageCount(paramMap,true);
	}
	/**
	 * 往(用户首页消息表)新增一条记录
	 * @param userHasHomeMessage
	 * @return
	 */
	@Override
	public int insertUserHasHomeMessage(UserHasHomeMessage userHasHomeMessage){
		return userHasHomeMessageBaseDao.insertUserHasHomeMessage(userHasHomeMessage);
	}
	/**
	 * 批量新增(用户首页消息表)
	 * @param userHasHomeMessageList
	 * @return
	 */
	@Override
	public int insertUserHasHomeMessageBatch(List<UserHasHomeMessage> userHasHomeMessageList){
		return userHasHomeMessageBaseDao.insertUserHasHomeMessageBatch(userHasHomeMessageList);
	}
	/**
	 * 更新(用户首页消息表)信息
	 * @param userHasHomeMessage
	 * @return
	 */
	@Override
	public int updateUserHasHomeMessage(UserHasHomeMessage userHasHomeMessage){
		return userHasHomeMessageBaseDao.updateUserHasHomeMessage(userHasHomeMessage);
	}
	/**
	 * 批量更新(用户首页消息表)信息
	 * @param userHasHomeMessageList
	 * @return
	 */
	@Override
	public int updateUserHasHomeMessageBatch(List<UserHasHomeMessage> userHasHomeMessageList){
		return userHasHomeMessageBaseDao.updateUserHasHomeMessageBatch(userHasHomeMessageList);
	}
	/**
	 * 根据序列号删除(用户首页消息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasHomeMessageLogic(java.math.BigInteger id){
		return userHasHomeMessageBaseDao.deleteUserHasHomeMessageLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户首页消息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasHomeMessageLogicBatch(List<java.math.BigInteger> idList){
		return userHasHomeMessageBaseDao.deleteUserHasHomeMessageLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户首页消息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasHomeMessage(java.math.BigInteger id){
//		return userHasHomeMessageBaseDao.deleteUserHasHomeMessage(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户首页消息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasHomeMessageBatch(List<java.math.BigInteger> idList){
//		return userHasHomeMessageBaseDao.deleteUserHasHomeMessageBatch(idList);
//	}
	
}
