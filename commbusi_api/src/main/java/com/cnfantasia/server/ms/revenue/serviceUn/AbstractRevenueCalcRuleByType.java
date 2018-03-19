package com.cnfantasia.server.ms.revenue.serviceUn;

import java.math.BigDecimal;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountNoTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;


/**
 * 指定类别的收益计算规则抽象类
* Filename:    AbstractRevenueCalcRuleByType.java
* @version:    1.0.0
* Create at:   2015年11月16日 下午7:20:36
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月16日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractRevenueCalcRuleByType<T> implements IRevenueCalcRuleByType<T>{
	
	
	@Override
	public RevenueAmountNoTime calc(T param, RevenueRole revenueRole,RevenueConfig revenueConfig) {
		Integer projectType = getProjectType();
		BigDecimal money = calcMoney(param, revenueRole, revenueConfig);
		RevenueAmountNoTime revenueAmount = new RevenueAmountNoTime(revenueRole, revenueConfig, projectType, money);
		return revenueAmount;
	}

}
