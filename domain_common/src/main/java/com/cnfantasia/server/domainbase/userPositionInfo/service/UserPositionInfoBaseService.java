package com.cnfantasia.server.domainbase.userPositionInfo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userPositionInfo.dao.IUserPositionInfoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userPositionInfo.entity.UserPositionInfo;

/**
 * 描述:(用户定位信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserPositionInfoBaseService implements IUserPositionInfoBaseService{
	
	private IUserPositionInfoBaseDao userPositionInfoBaseDao;
	public void setUserPositionInfoBaseDao(IUserPositionInfoBaseDao userPositionInfoBaseDao) {
		this.userPositionInfoBaseDao = userPositionInfoBaseDao;
	}
	/**
	 * 根据条件查询(用户定位信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserPositionInfo> getUserPositionInfoByCondition(Map<String,Object> paramMap){
		return userPositionInfoBaseDao.selectUserPositionInfoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户定位信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserPositionInfo> getUserPositionInfoByConditionDim(Map<String,Object> paramMap){
		return userPositionInfoBaseDao.selectUserPositionInfoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户定位信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserPositionInfo> getUserPositionInfoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userPositionInfoBaseDao.selectUserPositionInfoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户定位信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserPositionInfo> getUserPositionInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userPositionInfoBaseDao.selectUserPositionInfoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户定位信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserPositionInfo getUserPositionInfoBySeqId(java.math.BigInteger id){
		return userPositionInfoBaseDao.selectUserPositionInfoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户定位信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserPositionInfoCount(Map<String,Object> paramMap){
		return userPositionInfoBaseDao.selectUserPositionInfoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户定位信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserPositionInfoCountDim(Map<String,Object> paramMap){
		return userPositionInfoBaseDao.selectUserPositionInfoCount(paramMap,true);
	}
	/**
	 * 往(用户定位信息)新增一条记录
	 * @param userPositionInfo
	 * @return
	 */
	@Override
	public int insertUserPositionInfo(UserPositionInfo userPositionInfo){
		return userPositionInfoBaseDao.insertUserPositionInfo(userPositionInfo);
	}
	/**
	 * 批量新增(用户定位信息)
	 * @param userPositionInfoList
	 * @return
	 */
	@Override
	public int insertUserPositionInfoBatch(List<UserPositionInfo> userPositionInfoList){
		return userPositionInfoBaseDao.insertUserPositionInfoBatch(userPositionInfoList);
	}
	/**
	 * 更新(用户定位信息)信息
	 * @param userPositionInfo
	 * @return
	 */
	@Override
	public int updateUserPositionInfo(UserPositionInfo userPositionInfo){
		return userPositionInfoBaseDao.updateUserPositionInfo(userPositionInfo);
	}
	/**
	 * 批量更新(用户定位信息)信息
	 * @param userPositionInfoList
	 * @return
	 */
	@Override
	public int updateUserPositionInfoBatch(List<UserPositionInfo> userPositionInfoList){
		return userPositionInfoBaseDao.updateUserPositionInfoBatch(userPositionInfoList);
	}
	/**
	 * 根据序列号删除(用户定位信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserPositionInfoLogic(java.math.BigInteger id){
		return userPositionInfoBaseDao.deleteUserPositionInfoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户定位信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserPositionInfoLogicBatch(List<java.math.BigInteger> idList){
		return userPositionInfoBaseDao.deleteUserPositionInfoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户定位信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserPositionInfo(java.math.BigInteger id){
//		return userPositionInfoBaseDao.deleteUserPositionInfo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户定位信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserPositionInfoBatch(List<java.math.BigInteger> idList){
//		return userPositionInfoBaseDao.deleteUserPositionInfoBatch(idList);
//	}
	
}
