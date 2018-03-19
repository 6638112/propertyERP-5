package com.cnfantasia.server.domainbase.coupon.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.coupon.dao.ICouponBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.coupon.entity.Coupon;

/**
 * 描述:(消费券表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CouponBaseService implements ICouponBaseService{
	
	private ICouponBaseDao couponBaseDao;
	public void setCouponBaseDao(ICouponBaseDao couponBaseDao) {
		this.couponBaseDao = couponBaseDao;
	}
	/**
	 * 根据条件查询(消费券表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Coupon> getCouponByCondition(Map<String,Object> paramMap){
		return couponBaseDao.selectCouponByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(消费券表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Coupon> getCouponByConditionDim(Map<String,Object> paramMap){
		return couponBaseDao.selectCouponByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(消费券表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Coupon> getCouponByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return couponBaseDao.selectCouponByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(消费券表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Coupon> getCouponByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return couponBaseDao.selectCouponByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(消费券表)信息
	 * @param id
	 * @return
	 */
	@Override
	public Coupon getCouponBySeqId(java.math.BigInteger id){
		return couponBaseDao.selectCouponBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(消费券表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCouponCount(Map<String,Object> paramMap){
		return couponBaseDao.selectCouponCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(消费券表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCouponCountDim(Map<String,Object> paramMap){
		return couponBaseDao.selectCouponCount(paramMap,true);
	}
	/**
	 * 往(消费券表)新增一条记录
	 * @param coupon
	 * @return
	 */
	@Override
	public int insertCoupon(Coupon coupon){
		return couponBaseDao.insertCoupon(coupon);
	}
	/**
	 * 批量新增(消费券表)
	 * @param couponList
	 * @return
	 */
	@Override
	public int insertCouponBatch(List<Coupon> couponList){
		return couponBaseDao.insertCouponBatch(couponList);
	}
	/**
	 * 更新(消费券表)信息
	 * @param coupon
	 * @return
	 */
	@Override
	public int updateCoupon(Coupon coupon){
		return couponBaseDao.updateCoupon(coupon);
	}
	/**
	 * 批量更新(消费券表)信息
	 * @param couponList
	 * @return
	 */
	@Override
	public int updateCouponBatch(List<Coupon> couponList){
		return couponBaseDao.updateCouponBatch(couponList);
	}
	/**
	 * 根据序列号删除(消费券表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCouponLogic(java.math.BigInteger id){
		return couponBaseDao.deleteCouponLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(消费券表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCouponLogicBatch(List<java.math.BigInteger> idList){
		return couponBaseDao.deleteCouponLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(消费券表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCoupon(java.math.BigInteger id){
//		return couponBaseDao.deleteCoupon(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消费券表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCouponBatch(List<java.math.BigInteger> idList){
//		return couponBaseDao.deleteCouponBatch(idList);
//	}
	
}
