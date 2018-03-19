package com.cnfantasia.server.ms.revenue.serviceUn;

import java.util.List;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmount;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountNoTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;


public interface IRevenueCalcRule<T> {
	
	/**
	 * 根据收益项目及时间段获取对应参与计算的参数信息
	 * @param projectType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public T getCalcInfo(Integer projectType,RevenueRole revenueRole,String startTime, String endTime);
	
	/**
	 * 计算收益最小粒度方法定义,返回收益金额
	 * @param projectType
	 * @param revenueRole
	 * @param revenueConfigByTime
	 * @return
	 */
	public RevenueAmountNoTime calc(T param,Integer projectType, RevenueRole revenueRole,RevenueConfig revenueConfig);
	
	/**
	 * 根据时间段及收益项目类别计算收益额,返回各角色的总额
	 * @param roleList
	 * @param startTime精确到天
	 * @param endTime
	 * @param prjTypeList
	 * @return
	 */
	public List<RevenueAmount> calcByPrjType(List<RevenueRole> roleList,String startTime, String endTime, List<Integer> prjTypeList);
	
	/**
	 * 根据时间段及角色计算收益额,返回各收益项目的总额
	 * @param revenueRole
	 * @param startTime精确到天
	 * @param endTime
	 * @param prjTypeList
	 * @return
	 */
	public List<RevenueAmount> calcByRole(RevenueRole revenueRole, String startTime, String endTime, List<Integer> prjTypeList);
	
	
	/**
	 * 根据时间段计算收益额,返回总额
	 * @param projectType
	 * @param revenueRole
	 * @param startTime精确到天
	 * @param endTime
	 * @return
	 */
	public List<RevenueAmount> calcByTime(Integer projectType, RevenueRole revenueRole, String startTime, String endTime);
	
}
