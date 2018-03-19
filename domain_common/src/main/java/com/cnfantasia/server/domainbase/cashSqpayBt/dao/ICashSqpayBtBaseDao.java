package com.cnfantasia.server.domainbase.cashSqpayBt.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cashSqpayBt.entity.CashSqpayBt;

/**
 * 描述:(双乾支付优惠补贴明细表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICashSqpayBtBaseDao {
	/**
	 * 根据条件查询(双乾支付优惠补贴明细表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CashSqpayBt> selectCashSqpayBtByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(双乾支付优惠补贴明细表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CashSqpayBt> selectCashSqpayBtByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(双乾支付优惠补贴明细表)信息
	 * @param id
	 * @return
	 */
	public CashSqpayBt selectCashSqpayBtBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(双乾支付优惠补贴明细表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCashSqpayBtCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(双乾支付优惠补贴明细表)新增一条记录
	 * @param cashSqpayBt
	 * @return
	 */
	public int insertCashSqpayBt(CashSqpayBt cashSqpayBt);
	
	/**
	 * 批量新增(双乾支付优惠补贴明细表)信息
	 * @param cashSqpayBtList
	 * @return
	 */
	public int insertCashSqpayBtBatch(List<CashSqpayBt> cashSqpayBtList);
	
	/**
	 * 更新(双乾支付优惠补贴明细表)信息
	 * @param cashSqpayBt
	 * @return
	 */
	public int updateCashSqpayBt(CashSqpayBt cashSqpayBt);
	
	/**
	 * 批量更新(双乾支付优惠补贴明细表)信息
	 * @param cashSqpayBtList
	 * @return
	 */
	public int updateCashSqpayBtBatch(List<CashSqpayBt> cashSqpayBtList);
	
	/**
	 * 根据序列号删除(双乾支付优惠补贴明细表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteCashSqpayBtLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据Id 批量删除(双乾支付优惠补贴明细表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteCashSqpayBtLogicBatch(List<java.math.BigInteger> idList);
	 */
	
//	/**
//	 * 根据序列号删除(双乾支付优惠补贴明细表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCashSqpayBt(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(双乾支付优惠补贴明细表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCashSqpayBtBatch(List<java.math.BigInteger> idList);
	
	
}
