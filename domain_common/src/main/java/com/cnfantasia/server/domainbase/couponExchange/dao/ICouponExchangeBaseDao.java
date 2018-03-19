package com.cnfantasia.server.domainbase.couponExchange.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.couponExchange.entity.CouponExchange;

/**
 * 描述:(消费券兑换表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICouponExchangeBaseDao {
	/**
	 * 根据条件查询(消费券兑换表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CouponExchange> selectCouponExchangeByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(消费券兑换表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CouponExchange> selectCouponExchangeByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(消费券兑换表)信息
	 * @param id
	 * @return
	 */
	public CouponExchange selectCouponExchangeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消费券兑换表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCouponExchangeCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(消费券兑换表)新增一条记录
	 * @param couponExchange
	 * @return
	 */
	public int insertCouponExchange(CouponExchange couponExchange);
	
	/**
	 * 批量新增(消费券兑换表)信息
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
	 * 根据Id 批量删除(消费券兑换表)信息_逻辑删除
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
