package com.cnfantasia.server.ms.revenue.task;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.ms.revenue.entity.PayBillForRevenue;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
public class PropertyRealPayAmout2RevSignal4BillMonth extends PropertyRealPayAmout2RevSignal{
	
	@Override
	public List<PayBillForRevenue> getToSigList(Integer projectType, RevenueRole revenueRole,String configStartTime,String configEndTime){
		BigInteger companyId = revenueRole.getRoleId();
		List<PayBillForRevenue> toSigList = revenueDao.selectToSigPayBillList4BillMonth(companyId,projectType,configStartTime,configEndTime);
		return toSigList;
	}
	
	
}
