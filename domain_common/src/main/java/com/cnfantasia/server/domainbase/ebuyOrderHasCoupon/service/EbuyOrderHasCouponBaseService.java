package com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.dao.IEbuyOrderHasCouponBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.entity.EbuyOrderHasCoupon;

/**
 * 描述:(订单使用消费券表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyOrderHasCouponBaseService implements IEbuyOrderHasCouponBaseService{
	
	private IEbuyOrderHasCouponBaseDao ebuyOrderHasCouponBaseDao;
	public void setEbuyOrderHasCouponBaseDao(IEbuyOrderHasCouponBaseDao ebuyOrderHasCouponBaseDao) {
		this.ebuyOrderHasCouponBaseDao = ebuyOrderHasCouponBaseDao;
	}
	/**
	 * 根据条件查询(订单使用消费券表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderHasCoupon> getEbuyOrderHasCouponByCondition(Map<String,Object> paramMap){
		return ebuyOrderHasCouponBaseDao.selectEbuyOrderHasCouponByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(订单使用消费券表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderHasCoupon> getEbuyOrderHasCouponByConditionDim(Map<String,Object> paramMap){
		return ebuyOrderHasCouponBaseDao.selectEbuyOrderHasCouponByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(订单使用消费券表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderHasCoupon> getEbuyOrderHasCouponByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderHasCouponBaseDao.selectEbuyOrderHasCouponByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(订单使用消费券表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderHasCoupon> getEbuyOrderHasCouponByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderHasCouponBaseDao.selectEbuyOrderHasCouponByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(订单使用消费券表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderHasCoupon getEbuyOrderHasCouponBySeqId(java.math.BigInteger id){
		return ebuyOrderHasCouponBaseDao.selectEbuyOrderHasCouponBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(订单使用消费券表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderHasCouponCount(Map<String,Object> paramMap){
		return ebuyOrderHasCouponBaseDao.selectEbuyOrderHasCouponCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(订单使用消费券表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderHasCouponCountDim(Map<String,Object> paramMap){
		return ebuyOrderHasCouponBaseDao.selectEbuyOrderHasCouponCount(paramMap,true);
	}
	/**
	 * 往(订单使用消费券表)新增一条记录
	 * @param ebuyOrderHasCoupon
	 * @return
	 */
	@Override
	public int insertEbuyOrderHasCoupon(EbuyOrderHasCoupon ebuyOrderHasCoupon){
		return ebuyOrderHasCouponBaseDao.insertEbuyOrderHasCoupon(ebuyOrderHasCoupon);
	}
	/**
	 * 批量新增(订单使用消费券表)
	 * @param ebuyOrderHasCouponList
	 * @return
	 */
	@Override
	public int insertEbuyOrderHasCouponBatch(List<EbuyOrderHasCoupon> ebuyOrderHasCouponList){
		return ebuyOrderHasCouponBaseDao.insertEbuyOrderHasCouponBatch(ebuyOrderHasCouponList);
	}
	/**
	 * 更新(订单使用消费券表)信息
	 * @param ebuyOrderHasCoupon
	 * @return
	 */
	@Override
	public int updateEbuyOrderHasCoupon(EbuyOrderHasCoupon ebuyOrderHasCoupon){
		return ebuyOrderHasCouponBaseDao.updateEbuyOrderHasCoupon(ebuyOrderHasCoupon);
	}
	/**
	 * 批量更新(订单使用消费券表)信息
	 * @param ebuyOrderHasCouponList
	 * @return
	 */
	@Override
	public int updateEbuyOrderHasCouponBatch(List<EbuyOrderHasCoupon> ebuyOrderHasCouponList){
		return ebuyOrderHasCouponBaseDao.updateEbuyOrderHasCouponBatch(ebuyOrderHasCouponList);
	}
	/**
	 * 根据序列号删除(订单使用消费券表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderHasCouponLogic(java.math.BigInteger id){
		return ebuyOrderHasCouponBaseDao.deleteEbuyOrderHasCouponLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(订单使用消费券表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderHasCouponLogicBatch(List<java.math.BigInteger> idList){
		return ebuyOrderHasCouponBaseDao.deleteEbuyOrderHasCouponLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(订单使用消费券表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderHasCoupon(java.math.BigInteger id){
//		return ebuyOrderHasCouponBaseDao.deleteEbuyOrderHasCoupon(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单使用消费券表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderHasCouponBatch(List<java.math.BigInteger> idList){
//		return ebuyOrderHasCouponBaseDao.deleteEbuyOrderHasCouponBatch(idList);
//	}
	
}
