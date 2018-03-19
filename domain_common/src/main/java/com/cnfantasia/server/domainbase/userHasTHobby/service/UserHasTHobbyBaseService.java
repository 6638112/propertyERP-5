package com.cnfantasia.server.domainbase.userHasTHobby.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userHasTHobby.dao.IUserHasTHobbyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTHobby.entity.UserHasTHobby;

/**
 * 描述:(用户爱好关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserHasTHobbyBaseService implements IUserHasTHobbyBaseService{
	
	private IUserHasTHobbyBaseDao userHasTHobbyBaseDao;
	public void setUserHasTHobbyBaseDao(IUserHasTHobbyBaseDao userHasTHobbyBaseDao) {
		this.userHasTHobbyBaseDao = userHasTHobbyBaseDao;
	}
	/**
	 * 根据条件查询(用户爱好关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasTHobby> getUserHasTHobbyByCondition(Map<String,Object> paramMap){
		return userHasTHobbyBaseDao.selectUserHasTHobbyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户爱好关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasTHobby> getUserHasTHobbyByConditionDim(Map<String,Object> paramMap){
		return userHasTHobbyBaseDao.selectUserHasTHobbyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户爱好关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasTHobby> getUserHasTHobbyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasTHobbyBaseDao.selectUserHasTHobbyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户爱好关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasTHobby> getUserHasTHobbyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasTHobbyBaseDao.selectUserHasTHobbyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户爱好关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasTHobby getUserHasTHobbyBySeqId(java.math.BigInteger id){
		return userHasTHobbyBaseDao.selectUserHasTHobbyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户爱好关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasTHobbyCount(Map<String,Object> paramMap){
		return userHasTHobbyBaseDao.selectUserHasTHobbyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户爱好关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasTHobbyCountDim(Map<String,Object> paramMap){
		return userHasTHobbyBaseDao.selectUserHasTHobbyCount(paramMap,true);
	}
	/**
	 * 往(用户爱好关系表)新增一条记录
	 * @param userHasTHobby
	 * @return
	 */
	@Override
	public int insertUserHasTHobby(UserHasTHobby userHasTHobby){
		return userHasTHobbyBaseDao.insertUserHasTHobby(userHasTHobby);
	}
	/**
	 * 批量新增(用户爱好关系表)
	 * @param userHasTHobbyList
	 * @return
	 */
	@Override
	public int insertUserHasTHobbyBatch(List<UserHasTHobby> userHasTHobbyList){
		return userHasTHobbyBaseDao.insertUserHasTHobbyBatch(userHasTHobbyList);
	}
	/**
	 * 更新(用户爱好关系表)信息
	 * @param userHasTHobby
	 * @return
	 */
	@Override
	public int updateUserHasTHobby(UserHasTHobby userHasTHobby){
		return userHasTHobbyBaseDao.updateUserHasTHobby(userHasTHobby);
	}
	/**
	 * 批量更新(用户爱好关系表)信息
	 * @param userHasTHobbyList
	 * @return
	 */
	@Override
	public int updateUserHasTHobbyBatch(List<UserHasTHobby> userHasTHobbyList){
		return userHasTHobbyBaseDao.updateUserHasTHobbyBatch(userHasTHobbyList);
	}
	/**
	 * 根据序列号删除(用户爱好关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasTHobbyLogic(java.math.BigInteger id){
		return userHasTHobbyBaseDao.deleteUserHasTHobbyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户爱好关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasTHobbyLogicBatch(List<java.math.BigInteger> idList){
		return userHasTHobbyBaseDao.deleteUserHasTHobbyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户爱好关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTHobby(java.math.BigInteger id){
//		return userHasTHobbyBaseDao.deleteUserHasTHobby(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户爱好关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTHobbyBatch(List<java.math.BigInteger> idList){
//		return userHasTHobbyBaseDao.deleteUserHasTHobbyBatch(idList);
//	}
	
}
