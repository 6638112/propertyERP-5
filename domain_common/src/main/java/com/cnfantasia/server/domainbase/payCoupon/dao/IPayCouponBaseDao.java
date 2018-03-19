package com.cnfantasia.server.domainbase.payCoupon.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payCoupon.entity.PayCoupon;

/**
 * 描述:(优惠表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayCouponBaseDao {
	/**
	 * 根据条件查询(优惠表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayCoupon> selectPayCouponByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(优惠表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayCoupon> selectPayCouponByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(优惠表)信息
	 * @param id
	 * @return
	 */
	public PayCoupon selectPayCouponBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(优惠表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPayCouponCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(优惠表)新增一条记录
	 * @param payCoupon
	 * @return
	 */
	public int insertPayCoupon(PayCoupon payCoupon);
	
	/**
	 * 批量新增(优惠表)信息
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
	 * 根据Id 批量删除(优惠表)信息_逻辑删除
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
