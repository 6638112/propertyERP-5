package com.cnfantasia.server.domainbase.couponProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.couponProduct.entity.CouponProduct;

/**
 * 描述:(消费券可抵扣商品) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICouponProductBaseDao {
	/**
	 * 根据条件查询(消费券可抵扣商品)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CouponProduct> selectCouponProductByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(消费券可抵扣商品)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CouponProduct> selectCouponProductByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(消费券可抵扣商品)信息
	 * @param id
	 * @return
	 */
	public CouponProduct selectCouponProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消费券可抵扣商品)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCouponProductCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(消费券可抵扣商品)新增一条记录
	 * @param couponProduct
	 * @return
	 */
	public int insertCouponProduct(CouponProduct couponProduct);
	
	/**
	 * 批量新增(消费券可抵扣商品)信息
	 * @param couponProductList
	 * @return
	 */
	public int insertCouponProductBatch(List<CouponProduct> couponProductList);
	
	/**
	 * 更新(消费券可抵扣商品)信息
	 * @param couponProduct
	 * @return
	 */
	public int updateCouponProduct(CouponProduct couponProduct);
	
	/**
	 * 批量更新(消费券可抵扣商品)信息
	 * @param couponProductList
	 * @return
	 */
	public int updateCouponProductBatch(List<CouponProduct> couponProductList);
	
	/**
	 * 根据序列号删除(消费券可抵扣商品)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCouponProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(消费券可抵扣商品)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCouponProductLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(消费券可抵扣商品)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCouponProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(消费券可抵扣商品)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCouponProductBatch(List<java.math.BigInteger> idList);
	
	
}
