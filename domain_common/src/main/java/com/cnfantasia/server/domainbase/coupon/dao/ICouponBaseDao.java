package com.cnfantasia.server.domainbase.coupon.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.coupon.entity.Coupon;

/**
 * 描述:(消费券表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICouponBaseDao {
	/**
	 * 根据条件查询(消费券表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Coupon> selectCouponByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(消费券表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Coupon> selectCouponByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(消费券表)信息
	 * @param id
	 * @return
	 */
	public Coupon selectCouponBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消费券表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCouponCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(消费券表)新增一条记录
	 * @param coupon
	 * @return
	 */
	public int insertCoupon(Coupon coupon);
	
	/**
	 * 批量新增(消费券表)信息
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
	 * 根据Id 批量删除(消费券表)信息_逻辑删除
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
