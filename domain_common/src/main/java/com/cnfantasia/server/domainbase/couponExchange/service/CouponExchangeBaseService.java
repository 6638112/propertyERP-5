package com.cnfantasia.server.domainbase.couponExchange.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.couponExchange.dao.ICouponExchangeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.couponExchange.entity.CouponExchange;

/**
 * 描述:(消费券兑换表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CouponExchangeBaseService implements ICouponExchangeBaseService{
	
	private ICouponExchangeBaseDao couponExchangeBaseDao;
	public void setCouponExchangeBaseDao(ICouponExchangeBaseDao couponExchangeBaseDao) {
		this.couponExchangeBaseDao = couponExchangeBaseDao;
	}
	/**
	 * 根据条件查询(消费券兑换表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CouponExchange> getCouponExchangeByCondition(Map<String,Object> paramMap){
		return couponExchangeBaseDao.selectCouponExchangeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(消费券兑换表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CouponExchange> getCouponExchangeByConditionDim(Map<String,Object> paramMap){
		return couponExchangeBaseDao.selectCouponExchangeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(消费券兑换表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CouponExchange> getCouponExchangeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return couponExchangeBaseDao.selectCouponExchangeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(消费券兑换表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CouponExchange> getCouponExchangeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return couponExchangeBaseDao.selectCouponExchangeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(消费券兑换表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CouponExchange getCouponExchangeBySeqId(java.math.BigInteger id){
		return couponExchangeBaseDao.selectCouponExchangeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(消费券兑换表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCouponExchangeCount(Map<String,Object> paramMap){
		return couponExchangeBaseDao.selectCouponExchangeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(消费券兑换表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCouponExchangeCountDim(Map<String,Object> paramMap){
		return couponExchangeBaseDao.selectCouponExchangeCount(paramMap,true);
	}
	/**
	 * 往(消费券兑换表)新增一条记录
	 * @param couponExchange
	 * @return
	 */
	@Override
	public int insertCouponExchange(CouponExchange couponExchange){
		return couponExchangeBaseDao.insertCouponExchange(couponExchange);
	}
	/**
	 * 批量新增(消费券兑换表)
	 * @param couponExchangeList
	 * @return
	 */
	@Override
	public int insertCouponExchangeBatch(List<CouponExchange> couponExchangeList){
		return couponExchangeBaseDao.insertCouponExchangeBatch(couponExchangeList);
	}
	/**
	 * 更新(消费券兑换表)信息
	 * @param couponExchange
	 * @return
	 */
	@Override
	public int updateCouponExchange(CouponExchange couponExchange){
		return couponExchangeBaseDao.updateCouponExchange(couponExchange);
	}
	/**
	 * 批量更新(消费券兑换表)信息
	 * @param couponExchangeList
	 * @return
	 */
	@Override
	public int updateCouponExchangeBatch(List<CouponExchange> couponExchangeList){
		return couponExchangeBaseDao.updateCouponExchangeBatch(couponExchangeList);
	}
	/**
	 * 根据序列号删除(消费券兑换表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCouponExchangeLogic(java.math.BigInteger id){
		return couponExchangeBaseDao.deleteCouponExchangeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(消费券兑换表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCouponExchangeLogicBatch(List<java.math.BigInteger> idList){
		return couponExchangeBaseDao.deleteCouponExchangeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(消费券兑换表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCouponExchange(java.math.BigInteger id){
//		return couponExchangeBaseDao.deleteCouponExchange(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消费券兑换表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCouponExchangeBatch(List<java.math.BigInteger> idList){
//		return couponExchangeBaseDao.deleteCouponExchangeBatch(idList);
//	}
	
}
