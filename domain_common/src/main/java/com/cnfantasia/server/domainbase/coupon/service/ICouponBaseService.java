package com.cnfantasia.server.domainbase.coupon.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;

/**
 * 描述:(消费券表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICouponBaseService {
	
	/**
	 * 根据条件查询(消费券表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<Coupon> getCouponByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(消费券表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<Coupon> getCouponByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(消费券表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Coupon> getCouponByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(消费券表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Coupon> getCouponByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(消费券表)信息
	 * @param id
	 * @return
	 */
	public Coupon getCouponBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消费券表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCouponCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(消费券表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCouponCountDim(Map<String, Object> paramMap);
	/**
	 * 往(消费券表)新增一条记录
	 * @param coupon
	 * @return
	 */
	public int insertCoupon(Coupon coupon);
	/**
	 * 批量新增(消费券表)
	 * @param couponList
	 * @return
	 */
	public int insertCouponBatch(List<Coupon> couponList);
	/**
	 * 更新(消费券表)信息
	 * @param coupon
	 * @return
	 */
	public int updateCoupon(Coupon coupon);
	/**
	 * 批量更新(消费券表)信息
	 * @param couponList
	 * @return
	 */
	public int updateCouponBatch(List<Coupon> couponList);
	/**
	 * 根据序列号删除(消费券表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteCouponLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(消费券表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteCouponLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(消费券表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCoupon(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(消费券表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCouponBatch(List<java.math.BigInteger> idList);
	
}
