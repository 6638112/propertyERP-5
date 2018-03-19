package com.cnfantasia.server.api.cashSqpayBt.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.cashSqpayBt.dao.ICashSqpayBtBaseDao;

/**
 * 双乾支付优惠补贴
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 下午5:05:04
 */
public interface ICashSqpayBtDao extends ICashSqpayBtBaseDao{

	/**
	 * 查询电商购物“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	public String selectGoalNameWithEbuy(BigInteger orderId);
	
	/**
	 * 查询物业费、其它代收费、物业代扣卡“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	public String selectGoalNameWithWy(BigInteger orderId);
	
	/**
	 * 查询停车费“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	public String selectGoalNameWithParking(BigInteger orderId);
	
	/**
	 * 查询师傅维修“业务对象名称”
	 * 
	 * @param orderId
	 * @return
	 */
	public String selectGoalNameWithRepair(BigInteger orderId);
}
