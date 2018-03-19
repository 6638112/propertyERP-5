package com.cnfantasia.server.ms.revenue.task;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.entity.DredgeBillForRevenue;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountNoTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigByTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.serviceUn.IRevenueCalcRuleByType;
public class DredgeBill2RevSignal extends AbstractSyn2RevSignal<DredgeBillForRevenue,BigDecimal>{
	
	private IRevenueCalcRuleByType<BigDecimal> dredgePayAmoutCalcRule;
	public void setDredgePayAmoutCalcRule(
			IRevenueCalcRuleByType<BigDecimal> dredgePayAmoutCalcRule) {
		this.dredgePayAmoutCalcRule = dredgePayAmoutCalcRule;
	}

	@Override
	public List<RevenueConfigByTime> getActiveRevenueConfigList(Integer projectType, RevenueRole revenueRole){
		List<RevenueConfigByTime> activeRcList = revenueDao.selectActiveRevenueConfigList(revenueRole,projectType);
		return activeRcList;
	}
	
	@Override
	public List<DredgeBillForRevenue> getToSigList(Integer projectType, RevenueRole revenueRole,String configStartTime,String configEndTime){
		BigInteger masterUserId = revenueRole.getRoleId();
		List<DredgeBillForRevenue> toSigList = revenueDao.selectToSigDredgeBillList(masterUserId,projectType,configStartTime,configEndTime);
		return toSigList;
	}
	
	@Override
	public RevenueAmountNoTime getRevenueAmount(BigDecimal param, RevenueRole revenueRole,RevenueConfig revenueConfig){
		return dredgePayAmoutCalcRule.calc(param, revenueRole, revenueConfig);
	}
	
}
