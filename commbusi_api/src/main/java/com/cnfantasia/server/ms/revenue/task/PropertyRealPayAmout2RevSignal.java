package com.cnfantasia.server.ms.revenue.task;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.entity.PayBillForRevenue;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountNoTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigByTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.serviceUn.IRevenueCalcRuleByType;
public class PropertyRealPayAmout2RevSignal extends AbstractSyn2RevSignal<PayBillForRevenue,BigDecimal>{
	
	/*public RevenueRole getUserRole(){
		Integer projectType = RevenueDict.RevenueProject.PropertyRealPayAmout;
		UserRole role = UserRole.PropertyCompany;
		RevenueRole revenueRole = new RevenueRole(role, companyId);
		return revenueRole;
	}*/
	
	private IRevenueCalcRuleByType<BigDecimal> propertyRealPayAmoutCalcRule;
	public void setPropertyRealPayAmoutCalcRule(IRevenueCalcRuleByType<BigDecimal> propertyRealPayAmoutCalcRule) {
		this.propertyRealPayAmoutCalcRule = propertyRealPayAmoutCalcRule;
	}

	@Override
	public List<RevenueConfigByTime> getActiveRevenueConfigList(Integer projectType, RevenueRole revenueRole){
		List<RevenueConfigByTime> activeRcList = revenueDao.selectActiveRevenueConfigList(revenueRole,projectType);
		return activeRcList;
	}
	
	@Override
	public List<PayBillForRevenue> getToSigList(Integer projectType, RevenueRole revenueRole,String configStartTime,String configEndTime){
		BigInteger companyId = revenueRole.getRoleId();
		List<PayBillForRevenue> toSigList = revenueDao.selectToSigPayBillList(companyId,projectType,configStartTime,configEndTime);
		return toSigList;
	}
	
	@Override
	public RevenueAmountNoTime getRevenueAmount(BigDecimal param, RevenueRole revenueRole,RevenueConfig revenueConfig){
		return propertyRealPayAmoutCalcRule.calc(param, revenueRole, revenueConfig);
	}
	
}
