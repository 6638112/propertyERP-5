package com.cnfantasia.server.domainbase.userCoupon.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

/**
 * 描述:(用户所持消费券表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserCouponBaseService {
	
	/**
	 * 根据条件查询(用户所持消费券表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserCoupon> getUserCouponByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(用户所持消费券表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserCoupon> getUserCouponByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(用户所持消费券表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserCoupon> getUserCouponByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(用户所持消费券表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserCoupon> getUserCouponByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(用户所持消费券表)信息
	 * @param id
	 * @return
	 */
	public UserCoupon getUserCouponBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户所持消费券表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserCouponCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户所持消费券表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserCouponCountDim(Map<String, Object> paramMap);
	/**
	 * 往(用户所持消费券表)新增一条记录
	 * @param userCoupon
	 * @return
	 */
	public int insertUserCoupon(UserCoupon userCoupon);
	/**
	 * 批量新增(用户所持消费券表)
	 * @param userCouponList
	 * @return
	 */
	public int insertUserCouponBatch(List<UserCoupon> userCouponList);
	/**
	 * 更新(用户所持消费券表)信息
	 * @param userCoupon
	 * @return
	 */
	public int updateUserCoupon(UserCoupon userCoupon);
	/**
	 * 批量更新(用户所持消费券表)信息
	 * @param userCouponList
	 * @return
	 */
	public int updateUserCouponBatch(List<UserCoupon> userCouponList);
	/**
	 * 根据序列号删除(用户所持消费券表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserCouponLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户所持消费券表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserCouponLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户所持消费券表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserCoupon(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户所持消费券表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserCouponBatch(List<java.math.BigInteger> idList);
	
}
