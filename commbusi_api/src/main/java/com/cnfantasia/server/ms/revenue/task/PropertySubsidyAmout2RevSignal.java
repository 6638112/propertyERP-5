package com.cnfantasia.server.ms.revenue.task;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.entity.PayBillForRevenueSubsidy;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountNoTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigByTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.serviceUn.IRevenueCalcRuleByType;

/**
 * 补贴信息2sig
* Filename:    PropertySubsidyAmout2RevSignal.java
* @version:    1.0.0
* Create at:   2015年11月24日 下午1:32:58
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月24日       shiyl             1.0             1.0 Version
 */
public class PropertySubsidyAmout2RevSignal extends AbstractSyn2RevSignal<PayBillForRevenueSubsidy,BigDecimal>{

	private IRevenueCalcRuleByType<BigDecimal> propertySubsidyAmoutCalcRule;
	public void setPropertySubsidyAmoutCalcRule(
			IRevenueCalcRuleByType<BigDecimal> propertySubsidyAmoutCalcRule) {
		this.propertySubsidyAmoutCalcRule = propertySubsidyAmoutCalcRule;
	}

	@Override
	public List<RevenueConfigByTime> getActiveRevenueConfigList(Integer projectType, RevenueRole revenueRole){
		List<RevenueConfigByTime> activeRcList = revenueDao.selectActiveRevenueConfigList(revenueRole,projectType);
		return activeRcList;
	}
	
	@Override
	public List<PayBillForRevenueSubsidy> getToSigList(Integer projectType, RevenueRole revenueRole,String configStartTime,String configEndTime){
		BigInteger companyId = revenueRole.getRoleId();
		List<PayBillForRevenueSubsidy> toSigList = revenueDao.selectToSigPayBillSubsidyList(companyId,projectType,configStartTime,configEndTime);
		return toSigList;
	}
	
	@Override
	public RevenueAmountNoTime getRevenueAmount(BigDecimal param, RevenueRole revenueRole,RevenueConfig revenueConfig){
		return propertySubsidyAmoutCalcRule.calc(param, revenueRole, revenueConfig);
	}
	
}
