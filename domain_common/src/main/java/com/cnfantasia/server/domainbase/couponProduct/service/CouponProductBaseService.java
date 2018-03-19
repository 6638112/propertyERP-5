package com.cnfantasia.server.domainbase.couponProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.couponProduct.dao.ICouponProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.couponProduct.entity.CouponProduct;

/**
 * 描述:(消费券可抵扣商品) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CouponProductBaseService implements ICouponProductBaseService{
	
	private ICouponProductBaseDao couponProductBaseDao;
	public void setCouponProductBaseDao(ICouponProductBaseDao couponProductBaseDao) {
		this.couponProductBaseDao = couponProductBaseDao;
	}
	/**
	 * 根据条件查询(消费券可抵扣商品)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CouponProduct> getCouponProductByCondition(Map<String,Object> paramMap){
		return couponProductBaseDao.selectCouponProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(消费券可抵扣商品)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CouponProduct> getCouponProductByConditionDim(Map<String,Object> paramMap){
		return couponProductBaseDao.selectCouponProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(消费券可抵扣商品)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CouponProduct> getCouponProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return couponProductBaseDao.selectCouponProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(消费券可抵扣商品)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CouponProduct> getCouponProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return couponProductBaseDao.selectCouponProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(消费券可抵扣商品)信息
	 * @param id
	 * @return
	 */
	@Override
	public CouponProduct getCouponProductBySeqId(java.math.BigInteger id){
		return couponProductBaseDao.selectCouponProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(消费券可抵扣商品)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCouponProductCount(Map<String,Object> paramMap){
		return couponProductBaseDao.selectCouponProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(消费券可抵扣商品)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCouponProductCountDim(Map<String,Object> paramMap){
		return couponProductBaseDao.selectCouponProductCount(paramMap,true);
	}
	/**
	 * 往(消费券可抵扣商品)新增一条记录
	 * @param couponProduct
	 * @return
	 */
	@Override
	public int insertCouponProduct(CouponProduct couponProduct){
		return couponProductBaseDao.insertCouponProduct(couponProduct);
	}
	/**
	 * 批量新增(消费券可抵扣商品)
	 * @param couponProductList
	 * @return
	 */
	@Override
	public int insertCouponProductBatch(List<CouponProduct> couponProductList){
		return couponProductBaseDao.insertCouponProductBatch(couponProductList);
	}
	/**
	 * 更新(消费券可抵扣商品)信息
	 * @param couponProduct
	 * @return
	 */
	@Override
	public int updateCouponProduct(CouponProduct couponProduct){
		return couponProductBaseDao.updateCouponProduct(couponProduct);
	}
	/**
	 * 批量更新(消费券可抵扣商品)信息
	 * @param couponProductList
	 * @return
	 */
	@Override
	public int updateCouponProductBatch(List<CouponProduct> couponProductList){
		return couponProductBaseDao.updateCouponProductBatch(couponProductList);
	}
	/**
	 * 根据序列号删除(消费券可抵扣商品)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCouponProductLogic(java.math.BigInteger id){
		return couponProductBaseDao.deleteCouponProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(消费券可抵扣商品)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCouponProductLogicBatch(List<java.math.BigInteger> idList){
		return couponProductBaseDao.deleteCouponProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(消费券可抵扣商品)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCouponProduct(java.math.BigInteger id){
//		return couponProductBaseDao.deleteCouponProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消费券可抵扣商品)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCouponProductBatch(List<java.math.BigInteger> idList){
//		return couponProductBaseDao.deleteCouponProductBatch(idList);
//	}
	
}
