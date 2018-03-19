package com.cnfantasia.server.domainbase.userHasTRoom.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userHasTRoom.dao.IUserHasTRoomBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * 描述:(用户门牌关系) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserHasTRoomBaseService implements IUserHasTRoomBaseService{
	
	private IUserHasTRoomBaseDao userHasTRoomBaseDao;
	public void setUserHasTRoomBaseDao(IUserHasTRoomBaseDao userHasTRoomBaseDao) {
		this.userHasTRoomBaseDao = userHasTRoomBaseDao;
	}
	/**
	 * 根据条件查询(用户门牌关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasTRoom> getUserHasTRoomByCondition(Map<String,Object> paramMap){
		return userHasTRoomBaseDao.selectUserHasTRoomByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户门牌关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasTRoom> getUserHasTRoomByConditionDim(Map<String,Object> paramMap){
		return userHasTRoomBaseDao.selectUserHasTRoomByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户门牌关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasTRoom> getUserHasTRoomByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasTRoomBaseDao.selectUserHasTRoomByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户门牌关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasTRoom> getUserHasTRoomByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasTRoomBaseDao.selectUserHasTRoomByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户门牌关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasTRoom getUserHasTRoomBySeqId(java.math.BigInteger id){
		return userHasTRoomBaseDao.selectUserHasTRoomBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户门牌关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasTRoomCount(Map<String,Object> paramMap){
		return userHasTRoomBaseDao.selectUserHasTRoomCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户门牌关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasTRoomCountDim(Map<String,Object> paramMap){
		return userHasTRoomBaseDao.selectUserHasTRoomCount(paramMap,true);
	}
	/**
	 * 往(用户门牌关系)新增一条记录
	 * @param userHasTRoom
	 * @return
	 */
	@Override
	public int insertUserHasTRoom(UserHasTRoom userHasTRoom){
		return userHasTRoomBaseDao.insertUserHasTRoom(userHasTRoom);
	}
	/**
	 * 批量新增(用户门牌关系)
	 * @param userHasTRoomList
	 * @return
	 */
	@Override
	public int insertUserHasTRoomBatch(List<UserHasTRoom> userHasTRoomList){
		return userHasTRoomBaseDao.insertUserHasTRoomBatch(userHasTRoomList);
	}
	/**
	 * 更新(用户门牌关系)信息
	 * @param userHasTRoom
	 * @return
	 */
	@Override
	public int updateUserHasTRoom(UserHasTRoom userHasTRoom){
		return userHasTRoomBaseDao.updateUserHasTRoom(userHasTRoom);
	}
	/**
	 * 批量更新(用户门牌关系)信息
	 * @param userHasTRoomList
	 * @return
	 */
	@Override
	public int updateUserHasTRoomBatch(List<UserHasTRoom> userHasTRoomList){
		return userHasTRoomBaseDao.updateUserHasTRoomBatch(userHasTRoomList);
	}
	/**
	 * 根据序列号删除(用户门牌关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasTRoomLogic(java.math.BigInteger id){
		return userHasTRoomBaseDao.deleteUserHasTRoomLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户门牌关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasTRoomLogicBatch(List<java.math.BigInteger> idList){
		return userHasTRoomBaseDao.deleteUserHasTRoomLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户门牌关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTRoom(java.math.BigInteger id){
//		return userHasTRoomBaseDao.deleteUserHasTRoom(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户门牌关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTRoomBatch(List<java.math.BigInteger> idList){
//		return userHasTRoomBaseDao.deleteUserHasTRoomBatch(idList);
//	}
	
}
