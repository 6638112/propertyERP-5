package com.cnfantasia.server.domainbase.userRecommendOrder.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userRecommendOrder.entity.UserRecommendOrder;

/**
 * 描述:(用户推荐产生的订单) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserRecommendOrderBaseService {
	
	/**
	 * 根据条件查询(用户推荐产生的订单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserRecommendOrder> getUserRecommendOrderByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户推荐产生的订单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserRecommendOrder> getUserRecommendOrderByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户推荐产生的订单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserRecommendOrder> getUserRecommendOrderByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户推荐产生的订单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserRecommendOrder> getUserRecommendOrderByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户推荐产生的订单)信息
	 * @param id
	 * @return
	 */
	public UserRecommendOrder getUserRecommendOrderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户推荐产生的订单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserRecommendOrderCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户推荐产生的订单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserRecommendOrderCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户推荐产生的订单)新增一条记录
	 * @param userRecommendOrder
	 * @return
	 */
	public int insertUserRecommendOrder(UserRecommendOrder userRecommendOrder);
	/**
	 * 批量新增(用户推荐产生的订单)
	 * @param userRecommendOrderList
	 * @return
	 */
	public int insertUserRecommendOrderBatch(List<UserRecommendOrder> userRecommendOrderList);
	/**
	 * 更新(用户推荐产生的订单)信息
	 * @param userRecommendOrder
	 * @return
	 */
	public int updateUserRecommendOrder(UserRecommendOrder userRecommendOrder);
	/**
	 * 批量更新(用户推荐产生的订单)信息
	 * @param userRecommendOrderList
	 * @return
	 */
	public int updateUserRecommendOrderBatch(List<UserRecommendOrder> userRecommendOrderList);
	/**
	 * 根据序列号删除(用户推荐产生的订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserRecommendOrderLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户推荐产生的订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserRecommendOrderLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户推荐产生的订单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserRecommendOrder(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户推荐产生的订单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserRecommendOrderBatch(List<java.math.BigInteger> idList);
	
}
