package com.cnfantasia.server.domainbase.userPushInfo.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

/**
 * 描述:(用户推送配置消息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserPushInfoBaseDao {
	/**
	 * 根据条件查询(用户推送配置消息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserPushInfo> selectUserPushInfoByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户推送配置消息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserPushInfo> selectUserPushInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户推送配置消息)信息
	 * @param id
	 * @return
	 */
	public UserPushInfo selectUserPushInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户推送配置消息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserPushInfoCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户推送配置消息)新增一条记录
	 * @param userPushInfo
	 * @return
	 */
	public int insertUserPushInfo(UserPushInfo userPushInfo);
	
	/**
	 * 批量新增(用户推送配置消息)信息
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
	 * 根据Id 批量删除(用户推送配置消息)信息_逻辑删除
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
