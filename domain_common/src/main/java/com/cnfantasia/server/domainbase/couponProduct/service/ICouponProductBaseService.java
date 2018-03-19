package com.cnfantasia.server.domainbase.couponProduct.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.couponProduct.entity.CouponProduct;

/**
 * 描述:(消费券可抵扣商品) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICouponProductBaseService {
	
	/**
	 * 根据条件查询(消费券可抵扣商品)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CouponProduct> getCouponProductByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(消费券可抵扣商品)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CouponProduct> getCouponProductByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(消费券可抵扣商品)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CouponProduct> getCouponProductByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(消费券可抵扣商品)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CouponProduct> getCouponProductByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(消费券可抵扣商品)信息
	 * @param id
	 * @return
	 */
	public CouponProduct getCouponProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消费券可抵扣商品)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCouponProductCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(消费券可抵扣商品)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCouponProductCountDim(Map<String, Object> paramMap);
	/**
	 * 往(消费券可抵扣商品)新增一条记录
	 * @param couponProduct
	 * @return
	 */
	public int insertCouponProduct(CouponProduct couponProduct);
	/**
	 * 批量新增(消费券可抵扣商品)
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
	 * 根据序列号批量删除(消费券可抵扣商品)信息_逻辑删除
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
