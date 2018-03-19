package com.cnfantasia.server.api.cashSqpayBt.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.cashSqpayBt.dao.ICashSqpayBtDao;
import com.cnfantasia.server.domainbase.cashSqpayBt.service.CashSqpayBtBaseService;

/**
 * 双乾支付优惠补贴
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 下午5:11:44
 */
public class CashSqpayBtService extends CashSqpayBtBaseService implements
		ICashSqpayBtService {
	
	private ICashSqpayBtDao cashSqpayBtDao;

	public void setCashSqpayBtDao(ICashSqpayBtDao cashSqpayBtDao) {
		this.cashSqpayBtDao = cashSqpayBtDao;
	}

	/**
	 * 查询电商购物“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public String selectGoalNameWithEbuy(BigInteger orderId) {
		return cashSqpayBtDao.selectGoalNameWithEbuy(orderId);
	}

	/**
	 * 查询物业费、其它代收费、物业代扣卡“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public String selectGoalNameWithWy(BigInteger orderId) {
		return cashSqpayBtDao.selectGoalNameWithWy(orderId);
	}

	/**
	 * 查询停车费“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public String selectGoalNameWithParking(BigInteger orderId) {
		return cashSqpayBtDao.selectGoalNameWithParking(orderId);
	}

	/**
	 * 查询师傅维修“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public String selectGoalNameWithRepair(BigInteger orderId) {
		return cashSqpayBtDao.selectGoalNameWithRepair(orderId);
	}
}
