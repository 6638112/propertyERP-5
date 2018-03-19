package com.cnfantasia.server.ms.cashSqpayBt.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.cashSqpayBt.dao.ICashSqpayBtDao;
import com.cnfantasia.server.ms.cashSqpayBt.entity.CashSqpayBtDto;

/**
 * 双乾支付优惠补贴
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 下午8:36:54
 */
public interface ICashSqpayBtOOSDao extends ICashSqpayBtDao{

	/**
	 * 查询双乾支付优惠补贴记录条数
	 * 
	 * @param paramMap
	 * @return
	 */
	public Integer selectCashSqpayBtCount(Map<String, Object> paramMap);
	
	/**
	 * 查询双乾支付优惠补贴记录
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CashSqpayBtDto> selectCashSqpayBtList(Map<String, Object> paramMap);
	
	/**
	 * 查询双乾支付优惠总补贴额
	 * 
	 * @param paramMap
	 * @return
	 */
	public Long selectCashSqpayBtForTotalAmountBt(Map<String, Object> paramMap);
}
