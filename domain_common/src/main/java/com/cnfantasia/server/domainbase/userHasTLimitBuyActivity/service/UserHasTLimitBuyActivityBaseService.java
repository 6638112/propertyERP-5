package com.cnfantasia.server.domainbase.userHasTLimitBuyActivity.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userHasTLimitBuyActivity.dao.IUserHasTLimitBuyActivityBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTLimitBuyActivity.entity.UserHasTLimitBuyActivity;

/**
 * 描述:(用户在限时促销购买情况表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserHasTLimitBuyActivityBaseService implements IUserHasTLimitBuyActivityBaseService{
	
	private IUserHasTLimitBuyActivityBaseDao userHasTLimitBuyActivityBaseDao;
	public void setUserHasTLimitBuyActivityBaseDao(IUserHasTLimitBuyActivityBaseDao userHasTLimitBuyActivityBaseDao) {
		this.userHasTLimitBuyActivityBaseDao = userHasTLimitBuyActivityBaseDao;
	}
	/**
	 * 根据条件查询(用户在限时促销购买情况表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasTLimitBuyActivity> getUserHasTLimitBuyActivityByCondition(Map<String,Object> paramMap){
		return userHasTLimitBuyActivityBaseDao.selectUserHasTLimitBuyActivityByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户在限时促销购买情况表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserHasTLimitBuyActivity> getUserHasTLimitBuyActivityByConditionDim(Map<String,Object> paramMap){
		return userHasTLimitBuyActivityBaseDao.selectUserHasTLimitBuyActivityByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户在限时促销购买情况表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasTLimitBuyActivity> getUserHasTLimitBuyActivityByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasTLimitBuyActivityBaseDao.selectUserHasTLimitBuyActivityByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户在限时促销购买情况表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserHasTLimitBuyActivity> getUserHasTLimitBuyActivityByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userHasTLimitBuyActivityBaseDao.selectUserHasTLimitBuyActivityByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户在限时促销购买情况表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasTLimitBuyActivity getUserHasTLimitBuyActivityBySeqId(java.math.BigInteger id){
		return userHasTLimitBuyActivityBaseDao.selectUserHasTLimitBuyActivityBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户在限时促销购买情况表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasTLimitBuyActivityCount(Map<String,Object> paramMap){
		return userHasTLimitBuyActivityBaseDao.selectUserHasTLimitBuyActivityCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户在限时促销购买情况表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserHasTLimitBuyActivityCountDim(Map<String,Object> paramMap){
		return userHasTLimitBuyActivityBaseDao.selectUserHasTLimitBuyActivityCount(paramMap,true);
	}
	/**
	 * 往(用户在限时促销购买情况表)新增一条记录
	 * @param userHasTLimitBuyActivity
	 * @return
	 */
	@Override
	public int insertUserHasTLimitBuyActivity(UserHasTLimitBuyActivity userHasTLimitBuyActivity){
		return userHasTLimitBuyActivityBaseDao.insertUserHasTLimitBuyActivity(userHasTLimitBuyActivity);
	}
	/**
	 * 批量新增(用户在限时促销购买情况表)
	 * @param userHasTLimitBuyActivityList
	 * @return
	 */
	@Override
	public int insertUserHasTLimitBuyActivityBatch(List<UserHasTLimitBuyActivity> userHasTLimitBuyActivityList){
		return userHasTLimitBuyActivityBaseDao.insertUserHasTLimitBuyActivityBatch(userHasTLimitBuyActivityList);
	}
	/**
	 * 更新(用户在限时促销购买情况表)信息
	 * @param userHasTLimitBuyActivity
	 * @return
	 */
	@Override
	public int updateUserHasTLimitBuyActivity(UserHasTLimitBuyActivity userHasTLimitBuyActivity){
		return userHasTLimitBuyActivityBaseDao.updateUserHasTLimitBuyActivity(userHasTLimitBuyActivity);
	}
	/**
	 * 批量更新(用户在限时促销购买情况表)信息
	 * @param userHasTLimitBuyActivityList
	 * @return
	 */
	@Override
	public int updateUserHasTLimitBuyActivityBatch(List<UserHasTLimitBuyActivity> userHasTLimitBuyActivityList){
		return userHasTLimitBuyActivityBaseDao.updateUserHasTLimitBuyActivityBatch(userHasTLimitBuyActivityList);
	}
	/**
	 * 根据序列号删除(用户在限时促销购买情况表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasTLimitBuyActivityLogic(java.math.BigInteger id){
		return userHasTLimitBuyActivityBaseDao.deleteUserHasTLimitBuyActivityLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户在限时促销购买情况表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasTLimitBuyActivityLogicBatch(List<java.math.BigInteger> idList){
		return userHasTLimitBuyActivityBaseDao.deleteUserHasTLimitBuyActivityLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户在限时促销购买情况表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTLimitBuyActivity(java.math.BigInteger id){
//		return userHasTLimitBuyActivityBaseDao.deleteUserHasTLimitBuyActivity(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户在限时促销购买情况表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTLimitBuyActivityBatch(List<java.math.BigInteger> idList){
//		return userHasTLimitBuyActivityBaseDao.deleteUserHasTLimitBuyActivityBatch(idList);
//	}
	
}
