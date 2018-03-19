package com.cnfantasia.server.domainbase.remoteUser.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.remoteUser.entity.RemoteUser;

/**
 * 描述:(远程用户信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRemoteUserBaseDao {
	/**
	 * 根据条件查询(远程用户信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RemoteUser> selectRemoteUserByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(远程用户信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RemoteUser> selectRemoteUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(远程用户信息)信息
	 * @param uid
	 * @return
	 */
	public RemoteUser selectRemoteUserBySeqId(java.lang.Long uid);
	/**
	 * 根据条件查询满足条件的(远程用户信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRemoteUserCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(远程用户信息)新增一条记录
	 * @param remoteUser
	 * @return
	 */
	public int insertRemoteUser(RemoteUser remoteUser);
	
	/**
	 * 批量新增(远程用户信息)信息
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
	 * 根据Id 批量删除(远程用户信息)信息_逻辑删除
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
