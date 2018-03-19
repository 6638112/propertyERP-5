package com.cnfantasia.server.domainbase.userPushInfo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userPushInfo.dao.IUserPushInfoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

/**
 * 描述:(用户推送配置消息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserPushInfoBaseService implements IUserPushInfoBaseService{
	
	private IUserPushInfoBaseDao userPushInfoBaseDao;
	public void setUserPushInfoBaseDao(IUserPushInfoBaseDao userPushInfoBaseDao) {
		this.userPushInfoBaseDao = userPushInfoBaseDao;
	}
	/**
	 * 根据条件查询(用户推送配置消息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserPushInfo> getUserPushInfoByCondition(Map<String,Object> paramMap){
		return userPushInfoBaseDao.selectUserPushInfoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户推送配置消息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserPushInfo> getUserPushInfoByConditionDim(Map<String,Object> paramMap){
		return userPushInfoBaseDao.selectUserPushInfoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户推送配置消息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserPushInfo> getUserPushInfoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userPushInfoBaseDao.selectUserPushInfoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户推送配置消息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserPushInfo> getUserPushInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userPushInfoBaseDao.selectUserPushInfoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户推送配置消息)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserPushInfo getUserPushInfoBySeqId(java.math.BigInteger id){
		return userPushInfoBaseDao.selectUserPushInfoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户推送配置消息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserPushInfoCount(Map<String,Object> paramMap){
		return userPushInfoBaseDao.selectUserPushInfoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户推送配置消息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserPushInfoCountDim(Map<String,Object> paramMap){
		return userPushInfoBaseDao.selectUserPushInfoCount(paramMap,true);
	}
	/**
	 * 往(用户推送配置消息)新增一条记录
	 * @param userPushInfo
	 * @return
	 */
	@Override
	public int insertUserPushInfo(UserPushInfo userPushInfo){
		return userPushInfoBaseDao.insertUserPushInfo(userPushInfo);
	}
	/**
	 * 批量新增(用户推送配置消息)
	 * @param userPushInfoList
	 * @return
	 */
	@Override
	public int insertUserPushInfoBatch(List<UserPushInfo> userPushInfoList){
		return userPushInfoBaseDao.insertUserPushInfoBatch(userPushInfoList);
	}
	/**
	 * 更新(用户推送配置消息)信息
	 * @param userPushInfo
	 * @return
	 */
	@Override
	public int updateUserPushInfo(UserPushInfo userPushInfo){
		return userPushInfoBaseDao.updateUserPushInfo(userPushInfo);
	}
	/**
	 * 批量更新(用户推送配置消息)信息
	 * @param userPushInfoList
	 * @return
	 */
	@Override
	public int updateUserPushInfoBatch(List<UserPushInfo> userPushInfoList){
		return userPushInfoBaseDao.updateUserPushInfoBatch(userPushInfoList);
	}
	/**
	 * 根据序列号删除(用户推送配置消息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserPushInfoLogic(java.math.BigInteger id){
		return userPushInfoBaseDao.deleteUserPushInfoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户推送配置消息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserPushInfoLogicBatch(List<java.math.BigInteger> idList){
		return userPushInfoBaseDao.deleteUserPushInfoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户推送配置消息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserPushInfo(java.math.BigInteger id){
//		return userPushInfoBaseDao.deleteUserPushInfo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户推送配置消息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserPushInfoBatch(List<java.math.BigInteger> idList){
//		return userPushInfoBaseDao.deleteUserPushInfoBatch(idList);
//	}
	
}
