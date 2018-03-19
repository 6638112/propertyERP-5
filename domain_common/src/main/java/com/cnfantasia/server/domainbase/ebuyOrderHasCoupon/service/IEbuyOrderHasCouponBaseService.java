package com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.entity.EbuyOrderHasCoupon;

/**
 * 描述:(订单使用消费券表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderHasCouponBaseService {
	
	/**
	 * 根据条件查询(订单使用消费券表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderHasCoupon> getEbuyOrderHasCouponByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(订单使用消费券表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderHasCoupon> getEbuyOrderHasCouponByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(订单使用消费券表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderHasCoupon> getEbuyOrderHasCouponByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(订单使用消费券表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderHasCoupon> getEbuyOrderHasCouponByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(订单使用消费券表)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderHasCoupon getEbuyOrderHasCouponBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单使用消费券表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderHasCouponCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(订单使用消费券表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderHasCouponCountDim(Map<String, Object> paramMap);
	/**
	 * 往(订单使用消费券表)新增一条记录
	 * @param ebuyOrderHasCoupon
	 * @return
	 */
	public int insertEbuyOrderHasCoupon(EbuyOrderHasCoupon ebuyOrderHasCoupon);
	/**
	 * 批量新增(订单使用消费券表)
	 * @param ebuyOrderHasCouponList
	 * @return
	 */
	public int insertEbuyOrderHasCouponBatch(List<EbuyOrderHasCoupon> ebuyOrderHasCouponList);
	/**
	 * 更新(订单使用消费券表)信息
	 * @param ebuyOrderHasCoupon
	 * @return
	 */
	public int updateEbuyOrderHasCoupon(EbuyOrderHasCoupon ebuyOrderHasCoupon);
	/**
	 * 批量更新(订单使用消费券表)信息
	 * @param ebuyOrderHasCouponList
	 * @return
	 */
	public int updateEbuyOrderHasCouponBatch(List<EbuyOrderHasCoupon> ebuyOrderHasCouponList);
	/**
	 * 根据序列号删除(订单使用消费券表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyOrderHasCouponLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(订单使用消费券表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyOrderHasCouponLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(订单使用消费券表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrderHasCoupon(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(订单使用消费券表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderHasCouponBatch(List<java.math.BigInteger> idList);
	
}
