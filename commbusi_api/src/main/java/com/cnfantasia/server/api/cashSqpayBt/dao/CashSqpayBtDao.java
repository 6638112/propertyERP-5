package com.cnfantasia.server.api.cashSqpayBt.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.cashSqpayBt.dao.CashSqpayBtBaseDao;

/**
 * 双乾支付优惠补贴
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 下午5:05:20
 */
public class CashSqpayBtDao extends CashSqpayBtBaseDao implements ICashSqpayBtDao{
	
	/**
	 * 查询电商购物“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public String selectGoalNameWithEbuy(BigInteger orderId){
		return sqlSession.selectOne("cashSqpayBt.select_goalName_with_ebuy", orderId);
	}
	
	/**
	 * 查询物业费、其它代收费、物业代扣卡“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public String selectGoalNameWithWy(BigInteger orderId){
		return sqlSession.selectOne("cashSqpayBt.select_goalName_with_wy", orderId);
	}
	
	/**
	 * 查询停车费“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public String selectGoalNameWithParking(BigInteger orderId){
		return sqlSession.selectOne("cashSqpayBt.select_goalName_with_parking", orderId);
	}
	
	/**
	 * 查询师傅维修“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public String selectGoalNameWithRepair(BigInteger orderId){
		return sqlSession.selectOne("cashSqpayBt.select_goalName_with_repair", orderId);
	}
}
