package com.cnfantasia.server.domainbase.userRecommendOrder.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userRecommendOrder.dao.IUserRecommendOrderBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userRecommendOrder.entity.UserRecommendOrder;

/**
 * 描述:(用户推荐产生的订单) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserRecommendOrderBaseService implements IUserRecommendOrderBaseService{
	
	private IUserRecommendOrderBaseDao userRecommendOrderBaseDao;
	public void setUserRecommendOrderBaseDao(IUserRecommendOrderBaseDao userRecommendOrderBaseDao) {
		this.userRecommendOrderBaseDao = userRecommendOrderBaseDao;
	}
	/**
	 * 根据条件查询(用户推荐产生的订单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserRecommendOrder> getUserRecommendOrderByCondition(Map<String,Object> paramMap){
		return userRecommendOrderBaseDao.selectUserRecommendOrderByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户推荐产生的订单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserRecommendOrder> getUserRecommendOrderByConditionDim(Map<String,Object> paramMap){
		return userRecommendOrderBaseDao.selectUserRecommendOrderByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户推荐产生的订单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserRecommendOrder> getUserRecommendOrderByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userRecommendOrderBaseDao.selectUserRecommendOrderByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户推荐产生的订单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserRecommendOrder> getUserRecommendOrderByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userRecommendOrderBaseDao.selectUserRecommendOrderByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户推荐产生的订单)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserRecommendOrder getUserRecommendOrderBySeqId(java.math.BigInteger id){
		return userRecommendOrderBaseDao.selectUserRecommendOrderBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户推荐产生的订单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserRecommendOrderCount(Map<String,Object> paramMap){
		return userRecommendOrderBaseDao.selectUserRecommendOrderCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户推荐产生的订单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserRecommendOrderCountDim(Map<String,Object> paramMap){
		return userRecommendOrderBaseDao.selectUserRecommendOrderCount(paramMap,true);
	}
	/**
	 * 往(用户推荐产生的订单)新增一条记录
	 * @param userRecommendOrder
	 * @return
	 */
	@Override
	public int insertUserRecommendOrder(UserRecommendOrder userRecommendOrder){
		return userRecommendOrderBaseDao.insertUserRecommendOrder(userRecommendOrder);
	}
	/**
	 * 批量新增(用户推荐产生的订单)
	 * @param userRecommendOrderList
	 * @return
	 */
	@Override
	public int insertUserRecommendOrderBatch(List<UserRecommendOrder> userRecommendOrderList){
		return userRecommendOrderBaseDao.insertUserRecommendOrderBatch(userRecommendOrderList);
	}
	/**
	 * 更新(用户推荐产生的订单)信息
	 * @param userRecommendOrder
	 * @return
	 */
	@Override
	public int updateUserRecommendOrder(UserRecommendOrder userRecommendOrder){
		return userRecommendOrderBaseDao.updateUserRecommendOrder(userRecommendOrder);
	}
	/**
	 * 批量更新(用户推荐产生的订单)信息
	 * @param userRecommendOrderList
	 * @return
	 */
	@Override
	public int updateUserRecommendOrderBatch(List<UserRecommendOrder> userRecommendOrderList){
		return userRecommendOrderBaseDao.updateUserRecommendOrderBatch(userRecommendOrderList);
	}
	/**
	 * 根据序列号删除(用户推荐产生的订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserRecommendOrderLogic(java.math.BigInteger id){
		return userRecommendOrderBaseDao.deleteUserRecommendOrderLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户推荐产生的订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserRecommendOrderLogicBatch(List<java.math.BigInteger> idList){
		return userRecommendOrderBaseDao.deleteUserRecommendOrderLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户推荐产生的订单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserRecommendOrder(java.math.BigInteger id){
//		return userRecommendOrderBaseDao.deleteUserRecommendOrder(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户推荐产生的订单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserRecommendOrderBatch(List<java.math.BigInteger> idList){
//		return userRecommendOrderBaseDao.deleteUserRecommendOrderBatch(idList);
//	}
	
}
