package com.cnfantasia.server.domainbase.userCoupon.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userCoupon.dao.IUserCouponBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

/**
 * 描述:(用户所持消费券表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserCouponBaseService implements IUserCouponBaseService{
	
	private IUserCouponBaseDao userCouponBaseDao;
	public void setUserCouponBaseDao(IUserCouponBaseDao userCouponBaseDao) {
		this.userCouponBaseDao = userCouponBaseDao;
	}
	/**
	 * 根据条件查询(用户所持消费券表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserCoupon> getUserCouponByCondition(Map<String,Object> paramMap){
		return userCouponBaseDao.selectUserCouponByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户所持消费券表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserCoupon> getUserCouponByConditionDim(Map<String,Object> paramMap){
		return userCouponBaseDao.selectUserCouponByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户所持消费券表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserCoupon> getUserCouponByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userCouponBaseDao.selectUserCouponByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户所持消费券表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserCoupon> getUserCouponByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userCouponBaseDao.selectUserCouponByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户所持消费券表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserCoupon getUserCouponBySeqId(java.math.BigInteger id){
		return userCouponBaseDao.selectUserCouponBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户所持消费券表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserCouponCount(Map<String,Object> paramMap){
		return userCouponBaseDao.selectUserCouponCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户所持消费券表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserCouponCountDim(Map<String,Object> paramMap){
		return userCouponBaseDao.selectUserCouponCount(paramMap,true);
	}
	/**
	 * 往(用户所持消费券表)新增一条记录
	 * @param userCoupon
	 * @return
	 */
	@Override
	public int insertUserCoupon(UserCoupon userCoupon){
		return userCouponBaseDao.insertUserCoupon(userCoupon);
	}
	/**
	 * 批量新增(用户所持消费券表)
	 * @param userCouponList
	 * @return
	 */
	@Override
	public int insertUserCouponBatch(List<UserCoupon> userCouponList){
		return userCouponBaseDao.insertUserCouponBatch(userCouponList);
	}
	/**
	 * 更新(用户所持消费券表)信息
	 * @param userCoupon
	 * @return
	 */
	@Override
	public int updateUserCoupon(UserCoupon userCoupon){
		return userCouponBaseDao.updateUserCoupon(userCoupon);
	}
	/**
	 * 批量更新(用户所持消费券表)信息
	 * @param userCouponList
	 * @return
	 */
	@Override
	public int updateUserCouponBatch(List<UserCoupon> userCouponList){
		return userCouponBaseDao.updateUserCouponBatch(userCouponList);
	}
	/**
	 * 根据序列号删除(用户所持消费券表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserCouponLogic(java.math.BigInteger id){
		return userCouponBaseDao.deleteUserCouponLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户所持消费券表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserCouponLogicBatch(List<java.math.BigInteger> idList){
		return userCouponBaseDao.deleteUserCouponLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户所持消费券表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserCoupon(java.math.BigInteger id){
//		return userCouponBaseDao.deleteUserCoupon(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户所持消费券表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserCouponBatch(List<java.math.BigInteger> idList){
//		return userCouponBaseDao.deleteUserCouponBatch(idList);
//	}
	
}
