package com.cnfantasia.server.domainbase.cashSqpayBt.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.cashSqpayBt.dao.ICashSqpayBtBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cashSqpayBt.entity.CashSqpayBt;

/**
 * 描述:(双乾支付优惠补贴明细表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CashSqpayBtBaseService implements ICashSqpayBtBaseService{
	
	private ICashSqpayBtBaseDao cashSqpayBtBaseDao;
	public void setCashSqpayBtBaseDao(ICashSqpayBtBaseDao cashSqpayBtBaseDao) {
		this.cashSqpayBtBaseDao = cashSqpayBtBaseDao;
	}
	/**
	 * 根据条件查询(双乾支付优惠补贴明细表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CashSqpayBt> getCashSqpayBtByCondition(Map<String,Object> paramMap){
		return cashSqpayBtBaseDao.selectCashSqpayBtByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(双乾支付优惠补贴明细表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CashSqpayBt> getCashSqpayBtByConditionDim(Map<String,Object> paramMap){
		return cashSqpayBtBaseDao.selectCashSqpayBtByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(双乾支付优惠补贴明细表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CashSqpayBt> getCashSqpayBtByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return cashSqpayBtBaseDao.selectCashSqpayBtByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(双乾支付优惠补贴明细表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CashSqpayBt> getCashSqpayBtByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return cashSqpayBtBaseDao.selectCashSqpayBtByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(双乾支付优惠补贴明细表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CashSqpayBt getCashSqpayBtBySeqId(java.math.BigInteger id){
		return cashSqpayBtBaseDao.selectCashSqpayBtBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(双乾支付优惠补贴明细表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCashSqpayBtCount(Map<String,Object> paramMap){
		return cashSqpayBtBaseDao.selectCashSqpayBtCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(双乾支付优惠补贴明细表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCashSqpayBtCountDim(Map<String,Object> paramMap){
		return cashSqpayBtBaseDao.selectCashSqpayBtCount(paramMap,true);
	}
	/**
	 * 往(双乾支付优惠补贴明细表)新增一条记录
	 * @param cashSqpayBt
	 * @return
	 */
	@Override
	public int insertCashSqpayBt(CashSqpayBt cashSqpayBt){
		return cashSqpayBtBaseDao.insertCashSqpayBt(cashSqpayBt);
	}
	/**
	 * 批量新增(双乾支付优惠补贴明细表)
	 * @param cashSqpayBtList
	 * @return
	 */
	@Override
	public int insertCashSqpayBtBatch(List<CashSqpayBt> cashSqpayBtList){
		return cashSqpayBtBaseDao.insertCashSqpayBtBatch(cashSqpayBtList);
	}
	/**
	 * 更新(双乾支付优惠补贴明细表)信息
	 * @param cashSqpayBt
	 * @return
	 */
	@Override
	public int updateCashSqpayBt(CashSqpayBt cashSqpayBt){
		return cashSqpayBtBaseDao.updateCashSqpayBt(cashSqpayBt);
	}
	/**
	 * 批量更新(双乾支付优惠补贴明细表)信息
	 * @param cashSqpayBtList
	 * @return
	 */
	@Override
	public int updateCashSqpayBtBatch(List<CashSqpayBt> cashSqpayBtList){
		return cashSqpayBtBaseDao.updateCashSqpayBtBatch(cashSqpayBtList);
	}
	/**
	 * 根据序列号删除(双乾支付优惠补贴明细表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCashSqpayBtLogic(java.math.BigInteger id){
		return cashSqpayBtBaseDao.deleteCashSqpayBtLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(双乾支付优惠补贴明细表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCashSqpayBtLogicBatch(List<java.math.BigInteger> idList){
		return cashSqpayBtBaseDao.deleteCashSqpayBtLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(双乾支付优惠补贴明细表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCashSqpayBt(java.math.BigInteger id){
//		return cashSqpayBtBaseDao.deleteCashSqpayBt(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(双乾支付优惠补贴明细表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCashSqpayBtBatch(List<java.math.BigInteger> idList){
//		return cashSqpayBtBaseDao.deleteCashSqpayBtBatch(idList);
//	}
	
}
