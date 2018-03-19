package com.cnfantasia.server.domainbase.remoteUser.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.remoteUser.entity.RemoteUser;

/**
 * 描述:(远程用户信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRemoteUserBaseService {
	
	/**
	 * 根据条件查询(远程用户信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RemoteUser> getRemoteUserByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(远程用户信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RemoteUser> getRemoteUserByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(远程用户信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RemoteUser> getRemoteUserByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(远程用户信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RemoteUser> getRemoteUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(远程用户信息)信息
	 * @param uid
	 * @return
	 */
	public RemoteUser getRemoteUserBySeqId(java.lang.Long uid);
	/**
	 * 根据条件查询满足条件的(远程用户信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRemoteUserCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(远程用户信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRemoteUserCountDim(Map<String,Object> paramMap);
	/**
	 * 往(远程用户信息)新增一条记录
	 * @param remoteUser
	 * @return
	 */
	public int insertRemoteUser(RemoteUser remoteUser);
	/**
	 * 批量新增(远程用户信息)
	 * @param remoteUserList
	 * @return
	 */
	public int insertRemoteUserBatch(List<RemoteUser> remoteUserList);
	/**
	 * 更新(远程用户信息)信息
	 * @param remoteUser
	 * @return
	 */
	public int updateRemoteUser(RemoteUser remoteUser);
	/**
	 * 批量更新(远程用户信息)信息
	 * @param remoteUserList
	 * @return
	 */
	public int updateRemoteUserBatch(List<RemoteUser> remoteUserList);
	/**
	 * 根据序列号删除(远程用户信息)信息_逻辑删除
	 * @param uid
	 * @return
	 */
	/* 
	public int deleteRemoteUserLogic(java.lang.Long uid);
	 */
	/**
	 * 根据序列号批量删除(远程用户信息)信息_逻辑删除
	 * @param uidList
	 * @return
	 */
	/* 
	public int deleteRemoteUserLogicBatch(List<java.lang.Long> uidList);
	 */
//	/**
//	 * 根据序列号删除(远程用户信息)信息
//	 * @param uid
//	 * @return
//	 */
//	public int deleteRemoteUser(java.lang.Long uid);
//	
//	/**
//	 * 根据序列号批量删除(远程用户信息)信息
//	 * @param uidList
//	 * @return
//	 */
//	public int deleteRemoteUserBatch(List<java.lang.Long> uidList);
	
}
