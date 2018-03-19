package com.cnfantasia.server.domainbase.userCoupon.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

/**
 * 描述:(用户所持消费券表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserCouponBaseDao {
	/**
	 * 根据条件查询(用户所持消费券表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserCoupon> selectUserCouponByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(用户所持消费券表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserCoupon> selectUserCouponByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(用户所持消费券表)信息
	 * @param id
	 * @return
	 */
	public UserCoupon selectUserCouponBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户所持消费券表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserCouponCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(用户所持消费券表)新增一条记录
	 * @param userCoupon
	 * @return
	 */
	public int insertUserCoupon(UserCoupon userCoupon);
	
	/**
	 * 批量新增(用户所持消费券表)信息
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
	 * 根据Id 批量删除(用户所持消费券表)信息_逻辑删除
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
