package com.cnfantasia.server.ms.revenue.serviceUn;

import java.math.BigDecimal;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountNoTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;

/**
 * 指定类别的收益计算规则
* Filename:    IRevenueCalcRuleByType.java
* @version:    1.0.0
* Create at:   2015年11月16日 下午7:20:56
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月16日       shiyl             1.0             1.0 Version
 */
public interface IRevenueCalcRuleByType<T>{
	
	/**
	 * 获取收益项目类别
	 * @return
	 */
	public Integer getProjectType();
	
	/**
	 * 根据配置及参数信息计算收益金额
	 * @param param
	 * @param revenueConfigByTime
	 * @return
	 */
	public BigDecimal calcMoney(T param,RevenueRole revenueRole,RevenueConfig revenueConfig);
	
	/**
	 * 根据收益人角色及时间配置计算收益总额
	 * @param param
	 * @param revenueRole
	 * @param revenueConfig
	 * @return
	 */
	public RevenueAmountNoTime calc(T param,RevenueRole revenueRole,RevenueConfig revenueConfig);
	
	/**
	 * 根据收益项目及时间段获取对应参与计算的参数信息
	 * @param revenueRole
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public T getCalcInfo(RevenueRole revenueRole,String startTime, String endTime);
	
}
