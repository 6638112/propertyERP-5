package com.cnfantasia.server.domainbase.userPushInfo.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

/**
 * 描述:(用户推送配置消息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserPushInfoBaseService {
	
	/**
	 * 根据条件查询(用户推送配置消息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserPushInfo> getUserPushInfoByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户推送配置消息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserPushInfo> getUserPushInfoByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户推送配置消息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserPushInfo> getUserPushInfoByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户推送配置消息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserPushInfo> getUserPushInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户推送配置消息)信息
	 * @param id
	 * @return
	 */
	public UserPushInfo getUserPushInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户推送配置消息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserPushInfoCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户推送配置消息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserPushInfoCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户推送配置消息)新增一条记录
	 * @param userPushInfo
	 * @return
	 */
	public int insertUserPushInfo(UserPushInfo userPushInfo);
	/**
	 * 批量新增(用户推送配置消息)
	 * @param userPushInfoList
	 * @return
	 */
	public int insertUserPushInfoBatch(List<UserPushInfo> userPushInfoList);
	/**
	 * 更新(用户推送配置消息)信息
	 * @param userPushInfo
	 * @return
	 */
	public int updateUserPushInfo(UserPushInfo userPushInfo);
	/**
	 * 批量更新(用户推送配置消息)信息
	 * @param userPushInfoList
	 * @return
	 */
	public int updateUserPushInfoBatch(List<UserPushInfo> userPushInfoList);
	/**
	 * 根据序列号删除(用户推送配置消息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserPushInfoLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户推送配置消息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserPushInfoLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户推送配置消息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserPushInfo(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户推送配置消息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserPushInfoBatch(List<java.math.BigInteger> idList);
	
}
