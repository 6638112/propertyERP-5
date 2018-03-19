package com.cnfantasia.server.domainbase.payCoupon.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payCoupon.dao.IPayCouponBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payCoupon.entity.PayCoupon;

/**
 * 描述:(优惠表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayCouponBaseService implements IPayCouponBaseService{
	
	private IPayCouponBaseDao payCouponBaseDao;
	public void setPayCouponBaseDao(IPayCouponBaseDao payCouponBaseDao) {
		this.payCouponBaseDao = payCouponBaseDao;
	}
	/**
	 * 根据条件查询(优惠表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayCoupon> getPayCouponByCondition(Map<String,Object> paramMap){
		return payCouponBaseDao.selectPayCouponByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(优惠表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayCoupon> getPayCouponByConditionDim(Map<String,Object> paramMap){
		return payCouponBaseDao.selectPayCouponByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(优惠表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayCoupon> getPayCouponByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payCouponBaseDao.selectPayCouponByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(优惠表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayCoupon> getPayCouponByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payCouponBaseDao.selectPayCouponByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(优惠表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayCoupon getPayCouponBySeqId(java.math.BigInteger id){
		return payCouponBaseDao.selectPayCouponBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(优惠表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayCouponCount(Map<String,Object> paramMap){
		return payCouponBaseDao.selectPayCouponCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(优惠表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayCouponCountDim(Map<String,Object> paramMap){
		return payCouponBaseDao.selectPayCouponCount(paramMap,true);
	}
	/**
	 * 往(优惠表)新增一条记录
	 * @param payCoupon
	 * @return
	 */
	@Override
	public int insertPayCoupon(PayCoupon payCoupon){
		return payCouponBaseDao.insertPayCoupon(payCoupon);
	}
	/**
	 * 批量新增(优惠表)
	 * @param payCouponList
	 * @return
	 */
	@Override
	public int insertPayCouponBatch(List<PayCoupon> payCouponList){
		return payCouponBaseDao.insertPayCouponBatch(payCouponList);
	}
	/**
	 * 更新(优惠表)信息
	 * @param payCoupon
	 * @return
	 */
	@Override
	public int updatePayCoupon(PayCoupon payCoupon){
		return payCouponBaseDao.updatePayCoupon(payCoupon);
	}
	/**
	 * 批量更新(优惠表)信息
	 * @param payCouponList
	 * @return
	 */
	@Override
	public int updatePayCouponBatch(List<PayCoupon> payCouponList){
		return payCouponBaseDao.updatePayCouponBatch(payCouponList);
	}
	/**
	 * 根据序列号删除(优惠表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayCouponLogic(java.math.BigInteger id){
		return payCouponBaseDao.deletePayCouponLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(优惠表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayCouponLogicBatch(List<java.math.BigInteger> idList){
		return payCouponBaseDao.deletePayCouponLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(优惠表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayCoupon(java.math.BigInteger id){
//		return payCouponBaseDao.deletePayCoupon(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(优惠表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayCouponBatch(List<java.math.BigInteger> idList){
//		return payCouponBaseDao.deletePayCouponBatch(idList);
//	}
	
}
