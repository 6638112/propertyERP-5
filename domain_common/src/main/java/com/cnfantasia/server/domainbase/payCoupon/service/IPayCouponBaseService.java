package com.cnfantasia.server.domainbase.payCoupon.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payCoupon.entity.PayCoupon;

/**
 * 描述:(优惠表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayCouponBaseService {
	
	/**
	 * 根据条件查询(优惠表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayCoupon> getPayCouponByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(优惠表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayCoupon> getPayCouponByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(优惠表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayCoupon> getPayCouponByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(优惠表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayCoupon> getPayCouponByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(优惠表)信息
	 * @param id
	 * @return
	 */
	public PayCoupon getPayCouponBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(优惠表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPayCouponCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(优惠表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPayCouponCountDim(Map<String,Object> paramMap);
	/**
	 * 往(优惠表)新增一条记录
	 * @param payCoupon
	 * @return
	 */
	public int insertPayCoupon(PayCoupon payCoupon);
	/**
	 * 批量新增(优惠表)
	 * @param payCouponList
	 * @return
	 */
	public int insertPayCouponBatch(List<PayCoupon> payCouponList);
	/**
	 * 更新(优惠表)信息
	 * @param payCoupon
	 * @return
	 */
	public int updatePayCoupon(PayCoupon payCoupon);
	/**
	 * 批量更新(优惠表)信息
	 * @param payCouponList
	 * @return
	 */
	public int updatePayCouponBatch(List<PayCoupon> payCouponList);
	/**
	 * 根据序列号删除(优惠表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePayCouponLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(优惠表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePayCouponLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(优惠表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayCoupon(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(优惠表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayCouponBatch(List<java.math.BigInteger> idList);
	
}
