package com.cnfantasia.server.ms.cashSqpayBt.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.cashSqpayBt.dao.CashSqpayBtDao;
import com.cnfantasia.server.ms.cashSqpayBt.entity.CashSqpayBtDto;

/**
 * 双乾支付优惠补贴
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 下午8:36:54
 */
public class CashSqpayBtOOSDao extends CashSqpayBtDao implements ICashSqpayBtOOSDao {
	
	/**
	 * 查询双乾支付优惠补贴记录条数
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public Integer selectCashSqpayBtCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("cashSqpayBtOOS.select_cashSqpayBt_count", paramMap);
	}
	
	/**
	 * 查询双乾支付优惠补贴记录
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<CashSqpayBtDto> selectCashSqpayBtList(Map<String, Object> paramMap){
		return sqlSession.selectList("cashSqpayBtOOS.select_cashSqpayBt_list", paramMap);
	}
	
	/**
	 * 查询双乾支付优惠总补贴额
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public Long selectCashSqpayBtForTotalAmountBt(Map<String, Object> paramMap){
		return sqlSession.selectOne("cashSqpayBtOOS.select_cashSqpayBt_with_totalAmountBt", paramMap);
	}
}
