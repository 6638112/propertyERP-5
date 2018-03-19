package com.cnfantasia.server.domainbase.remoteUser.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.remoteUser.dao.IRemoteUserBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.remoteUser.entity.RemoteUser;

/**
 * 描述:(远程用户信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RemoteUserBaseService implements IRemoteUserBaseService{
	
	private IRemoteUserBaseDao remoteUserBaseDao;
	public void setRemoteUserBaseDao(IRemoteUserBaseDao remoteUserBaseDao) {
		this.remoteUserBaseDao = remoteUserBaseDao;
	}
	/**
	 * 根据条件查询(远程用户信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RemoteUser> getRemoteUserByCondition(Map<String,Object> paramMap){
		return remoteUserBaseDao.selectRemoteUserByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(远程用户信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RemoteUser> getRemoteUserByConditionDim(Map<String,Object> paramMap){
		return remoteUserBaseDao.selectRemoteUserByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(远程用户信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RemoteUser> getRemoteUserByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return remoteUserBaseDao.selectRemoteUserByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(远程用户信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RemoteUser> getRemoteUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return remoteUserBaseDao.selectRemoteUserByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(远程用户信息)信息
	 * @param uid
	 * @return
	 */
	@Override
	public RemoteUser getRemoteUserBySeqId(java.lang.Long uid){
		return remoteUserBaseDao.selectRemoteUserBySeqId(uid);
	}
	/**
	 * 根据条件查询满足条件的(远程用户信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRemoteUserCount(Map<String,Object> paramMap){
		return remoteUserBaseDao.selectRemoteUserCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(远程用户信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRemoteUserCountDim(Map<String,Object> paramMap){
		return remoteUserBaseDao.selectRemoteUserCount(paramMap,true);
	}
	/**
	 * 往(远程用户信息)新增一条记录
	 * @param remoteUser
	 * @return
	 */
	@Override
	public int insertRemoteUser(RemoteUser remoteUser){
		return remoteUserBaseDao.insertRemoteUser(remoteUser);
	}
	/**
	 * 批量新增(远程用户信息)
	 * @param remoteUserList
	 * @return
	 */
	@Override
	public int insertRemoteUserBatch(List<RemoteUser> remoteUserList){
		return remoteUserBaseDao.insertRemoteUserBatch(remoteUserList);
	}
	/**
	 * 更新(远程用户信息)信息
	 * @param remoteUser
	 * @return
	 */
	@Override
	public int updateRemoteUser(RemoteUser remoteUser){
		return remoteUserBaseDao.updateRemoteUser(remoteUser);
	}
	/**
	 * 批量更新(远程用户信息)信息
	 * @param remoteUserList
	 * @return
	 */
	@Override
	public int updateRemoteUserBatch(List<RemoteUser> remoteUserList){
		return remoteUserBaseDao.updateRemoteUserBatch(remoteUserList);
	}
	/**
	 * 根据序列号删除(远程用户信息)信息_逻辑删除
	 * @param uid
	 * @return
	 */
	/* 
	@Override
	public int deleteRemoteUserLogic(java.lang.Long uid){
		return remoteUserBaseDao.deleteRemoteUserLogic(uid);
	}
	 */
	/**
	 * 根据序列号批量删除(远程用户信息)信息_逻辑删除
	 * @param uidList
	 * @return
	 */
	/* 
	@Override
	public int deleteRemoteUserLogicBatch(List<java.lang.Long> uidList){
		return remoteUserBaseDao.deleteRemoteUserLogicBatch(uidList);
	}
	 */
//	/**
//	 * 根据序列号删除(远程用户信息)信息
//	 * @param uid
//	 * @return
//	 */
//	@Override
//	public int deleteRemoteUser(java.lang.Long uid){
//		return remoteUserBaseDao.deleteRemoteUser(uid);
//	}
//	
//	/**
//	 * 根据序列号批量删除(远程用户信息)信息
//	 * @param uidList
//	 * @return
//	 */
//	@Override
//	public int deleteRemoteUserBatch(List<java.lang.Long> uidList){
//		return remoteUserBaseDao.deleteRemoteUserBatch(uidList);
//	}
	
}
