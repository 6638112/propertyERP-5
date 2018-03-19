package com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.entity.EbuyOrderHasCoupon;

/**
 * 描述:(订单使用消费券表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderHasCouponBaseDao {
	/**
	 * 根据条件查询(订单使用消费券表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderHasCoupon> selectEbuyOrderHasCouponByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(订单使用消费券表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderHasCoupon> selectEbuyOrderHasCouponByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(订单使用消费券表)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderHasCoupon selectEbuyOrderHasCouponBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单使用消费券表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyOrderHasCouponCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(订单使用消费券表)新增一条记录
	 * @param ebuyOrderHasCoupon
	 * @return
	 */
	public int insertEbuyOrderHasCoupon(EbuyOrderHasCoupon ebuyOrderHasCoupon);
	
	/**
	 * 批量新增(订单使用消费券表)信息
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
	 * 根据Id 批量删除(订单使用消费券表)信息_逻辑删除
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
