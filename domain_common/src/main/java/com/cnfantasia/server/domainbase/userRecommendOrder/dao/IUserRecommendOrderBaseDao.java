package com.cnfantasia.server.domainbase.userRecommendOrder.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userRecommendOrder.entity.UserRecommendOrder;

/**
 * 描述:(用户推荐产生的订单) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserRecommendOrderBaseDao {
	/**
	 * 根据条件查询(用户推荐产生的订单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserRecommendOrder> selectUserRecommendOrderByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户推荐产生的订单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserRecommendOrder> selectUserRecommendOrderByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户推荐产生的订单)信息
	 * @param id
	 * @return
	 */
	public UserRecommendOrder selectUserRecommendOrderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户推荐产生的订单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserRecommendOrderCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户推荐产生的订单)新增一条记录
	 * @param userRecommendOrder
	 * @return
	 */
	public int insertUserRecommendOrder(UserRecommendOrder userRecommendOrder);
	
	/**
	 * 批量新增(用户推荐产生的订单)信息
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
	 * 根据Id 批量删除(用户推荐产生的订单)信息_逻辑删除
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
