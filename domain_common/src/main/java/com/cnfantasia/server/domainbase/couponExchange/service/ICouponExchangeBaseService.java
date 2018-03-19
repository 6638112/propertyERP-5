package com.cnfantasia.server.domainbase.couponExchange.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.couponExchange.entity.CouponExchange;

/**
 * 描述:(消费券兑换表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICouponExchangeBaseService {
	
	/**
	 * 根据条件查询(消费券兑换表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CouponExchange> getCouponExchangeByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(消费券兑换表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CouponExchange> getCouponExchangeByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(消费券兑换表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CouponExchange> getCouponExchangeByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(消费券兑换表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CouponExchange> getCouponExchangeByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(消费券兑换表)信息
	 * @param id
	 * @return
	 */
	public CouponExchange getCouponExchangeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消费券兑换表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCouponExchangeCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(消费券兑换表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCouponExchangeCountDim(Map<String, Object> paramMap);
	/**
	 * 往(消费券兑换表)新增一条记录
	 * @param couponExchange
	 * @return
	 */
	public int insertCouponExchange(CouponExchange couponExchange);
	/**
	 * 批量新增(消费券兑换表)
	 * @param couponExchangeList
	 * @return
	 */
	public int insertCouponExchangeBatch(List<CouponExchange> couponExchangeList);
	/**
	 * 更新(消费券兑换表)信息
	 * @param couponExchange
	 * @return
	 */
	public int updateCouponExchange(CouponExchange couponExchange);
	/**
	 * 批量更新(消费券兑换表)信息
	 * @param couponExchangeList
	 * @return
	 */
	public int updateCouponExchangeBatch(List<CouponExchange> couponExchangeList);
	/**
	 * 根据序列号删除(消费券兑换表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCouponExchangeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(消费券兑换表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCouponExchangeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(消费券兑换表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCouponExchange(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(消费券兑换表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCouponExchangeBatch(List<java.math.BigInteger> idList);
	
}
