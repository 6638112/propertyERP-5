package com.cnfantasia.server.domainbase.user.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * 描述:(用户表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserBaseService implements IUserBaseService{
	
	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}
	/**
	 * 根据条件查询(用户表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<User> getUserByCondition(Map<String,Object> paramMap){
		return userBaseDao.selectUserByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<User> getUserByConditionDim(Map<String,Object> paramMap){
		return userBaseDao.selectUserByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<User> getUserByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userBaseDao.selectUserByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<User> getUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userBaseDao.selectUserByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户表)信息
	 * @param id
	 * @return
	 */
	@Override
	public User getUserBySeqId(java.math.BigInteger id){
		return userBaseDao.selectUserBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserCount(Map<String,Object> paramMap){
		return userBaseDao.selectUserCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserCountDim(Map<String,Object> paramMap){
		return userBaseDao.selectUserCount(paramMap,true);
	}
	/**
	 * 往(用户表)新增一条记录
	 * @param user
	 * @return
	 */
	@Override
	public int insertUser(User user){
		return userBaseDao.insertUser(user);
	}
	/**
	 * 批量新增(用户表)
	 * @param userList
	 * @return
	 */
	@Override
	public int insertUserBatch(List<User> userList){
		return userBaseDao.insertUserBatch(userList);
	}
	/**
	 * 更新(用户表)信息
	 * @param user
	 * @return
	 */
	@Override
	public int updateUser(User user){
		return userBaseDao.updateUser(user);
	}
	/**
	 * 批量更新(用户表)信息
	 * @param userList
	 * @return
	 */
	@Override
	public int updateUserBatch(List<User> userList){
		return userBaseDao.updateUserBatch(userList);
	}
	/**
	 * 根据序列号删除(用户表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserLogic(java.math.BigInteger id){
		return userBaseDao.deleteUserLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserLogicBatch(List<java.math.BigInteger> idList){
		return userBaseDao.deleteUserLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUser(java.math.BigInteger id){
//		return userBaseDao.deleteUser(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserBatch(List<java.math.BigInteger> idList){
//		return userBaseDao.deleteUserBatch(idList);
//	}
	
}
