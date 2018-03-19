package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.cashSqpayBt.constants.CashSqpayBtConstants;
import com.cnfantasia.server.api.cashSqpayBt.dao.CashSqpayBtDao;
import com.cnfantasia.server.api.cashSqpayBt.dao.ICashSqpayBtDao;
import com.cnfantasia.server.api.payment.entity.SqPayBtInfoDto;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.cashSqpayBt.dao.CashSqpayBtBaseDao;
import com.cnfantasia.server.domainbase.cashSqpayBt.dao.ICashSqpayBtBaseDao;
import com.cnfantasia.server.domainbase.cashSqpayBt.entity.CashSqpayBt;

/**
 * 双乾支付优惠补贴明细写入
 * 
 * @author liyulin
 * @version 1.0 2016年9月8日 下午9:12:14
 */
public class SqPayBtRunnable implements Runnable{
	private List<SqPayBtInfoDto> sqPayBtInfos;
	
	public SqPayBtRunnable(List<SqPayBtInfoDto> sqPayBtInfos){
		this.sqPayBtInfos   = sqPayBtInfos;
	}

	@Override
	public void run() {
		insertSqPayBt();
	}

	/**
	 * 插入双乾支付优惠补贴数据
	 */
	private final void insertSqPayBt(){
		List<CashSqpayBt> cashSqpayBts = new ArrayList<CashSqpayBt>();
		List<BigInteger> sqpbIds =  ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_cash_sqpay_bt, sqPayBtInfos.size());
		for(int i=0; i<sqPayBtInfos.size(); i++){
			SqPayBtInfoDto sqPayBtInfo = sqPayBtInfos.get(i);
			String goalName = getGoalName(sqPayBtInfo.getOrderId(), sqPayBtInfo.getOrderType());
			
			CashSqpayBt cashSqpayBt = new CashSqpayBt();
			cashSqpayBt.setId(sqpbIds.get(i));
			cashSqpayBt.setGoalName(goalName);
			cashSqpayBt.settEbuyOrderFId(sqPayBtInfo.getOrderId());
			cashSqpayBt.setOrderType(sqPayBtInfo.getOrderType());
			cashSqpayBt.setAmountBt(sqPayBtInfo.getAmountBt().longValue());
			
			cashSqpayBts.add(cashSqpayBt);
		}
		
		
		ICashSqpayBtBaseDao cashSqpayBtBaseDao = ApplicationContextBothUtil.getContext().getBean("cashSqpayBtBaseDao", CashSqpayBtBaseDao.class);
		cashSqpayBtBaseDao.insertCashSqpayBtBatch(cashSqpayBts);
	}
	
	/**
	 * 获取业务对象名称
	 * 
	 * @param orderId
	 * @param orderType
	 * @return
	 */
	private final String getGoalName(BigInteger orderId, Integer orderType) {
		ICashSqpayBtDao cashSqpayBtDao = ApplicationContextBothUtil.getContext().getBean("cashSqpayBtDao", CashSqpayBtDao.class);
		
		if (orderType.compareTo(CashSqpayBtConstants.OrderType.EBUY_FEE) == 0) {
			return cashSqpayBtDao.selectGoalNameWithEbuy(orderId);
		} else if (orderType.compareTo(CashSqpayBtConstants.OrderType.WY_FEE) == 0
				|| orderType.compareTo(CashSqpayBtConstants.OrderType.OTHER_FEE) == 0
				|| orderType.compareTo(CashSqpayBtConstants.OrderType.WY_CARD_FEE) == 0) {
			return cashSqpayBtDao.selectGoalNameWithWy(orderId);
		} else if (orderType.compareTo(CashSqpayBtConstants.OrderType.REPAIR_FEE) == 0) {
			return cashSqpayBtDao.selectGoalNameWithRepair(orderId);
		} else if (orderType.compareTo(CashSqpayBtConstants.OrderType.PARKING_FEE) == 0) {
			return cashSqpayBtDao.selectGoalNameWithParking(orderId);
		}
		return null;
	}
}
