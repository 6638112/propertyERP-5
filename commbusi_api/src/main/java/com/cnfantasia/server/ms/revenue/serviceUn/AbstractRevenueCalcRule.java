package com.cnfantasia.server.ms.revenue.serviceUn;

import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmount;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountNoTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigByTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;


public abstract class AbstractRevenueCalcRule<T> implements IRevenueCalcRule<T>{
	
	/**
	 * 根据收益项目获取对应的配置规则
	 * @param projectType
	 * @return
	 */
	public abstract IRevenueCalcRuleByType<T> getCalcRule(Integer projectType);
	
	/**
	 * 根据时间段获取配置规则列表
	 * @param projectType
	 * @param revenueRole
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public abstract List<RevenueConfigByTime> getRuleConfig(Integer projectType,RevenueRole revenueRole,String startTime, String endTime);
	
	/**
	 * 根据用户角色查询管理的直接下级角色列表
	 * @param revenueRole
	 * @return
	 */
	public abstract List<RevenueRole> getSubRoleList(RevenueRole revenueRole);
	
	@Override
	public T getCalcInfo(Integer projectType,RevenueRole revenueRole,String startTime, String endTime){
		IRevenueCalcRuleByType<T> currRule = getCalcRule(projectType);
		return currRule.getCalcInfo(revenueRole, startTime, endTime);
	}
	
	@Override
	public RevenueAmountNoTime calc(T param,Integer projectType, RevenueRole revenueRole,RevenueConfig revenueConfig) {
		IRevenueCalcRuleByType<T> currRule = getCalcRule(projectType);
		return currRule.calc(param, revenueRole,revenueConfig);
	}
	
	@Override
	public List<RevenueAmount> calcByTime(Integer projectType, RevenueRole revenueRole, String startTime, String endTime){
		List<RevenueAmount> amountList = new ArrayList<RevenueAmount>();
		UserRole role = revenueRole.getRole();
		if(role.isBottom()){
			List<RevenueConfigByTime> configList = getRuleConfig(projectType, revenueRole, startTime, endTime);
			if(configList!=null&&configList.size()>0){
				amountList = new ArrayList<RevenueAmount>();
				for(RevenueConfigByTime tmpConfig:configList){
//					T param = getCalcInfo(projectType,revenueRole,startTime, endTime);
					T param = getCalcInfo(projectType,revenueRole,tmpConfig.getStartTime(), tmpConfig.getEndTime());
					RevenueAmountNoTime noTime = calc(param, projectType, revenueRole,tmpConfig.getRevenueConfig());
					amountList.add(new RevenueAmount(noTime, tmpConfig.getStartTime(), tmpConfig.getEndTime()));
				}
			}
		}else{
			List<RevenueRole> subRoleList = getSubRoleList(revenueRole);
			if(subRoleList!=null&&subRoleList.size()>0){
				for(RevenueRole subRole:subRoleList){
					List<RevenueAmount> subAmountList = calcByTime(projectType, subRole, startTime, endTime);//递归调用
					if(subAmountList!=null&&subAmountList.size()>0){
						amountList.addAll(subAmountList);
					}
				}
			}
		}
		return amountList;
	}
	
	@Override
	public List<RevenueAmount> calcByRole(RevenueRole revenueRole, String startTime, String endTime, List<Integer> prjTypeList){
		List<RevenueAmount> resList = new ArrayList<RevenueAmount>();
		if(prjTypeList!=null&&prjTypeList.size()>0){
			for(Integer projectType:prjTypeList){
				List<RevenueAmount> amountList = calcByTime(projectType, revenueRole, startTime, endTime);
				if(amountList!=null&&amountList.size()>0){
					resList.addAll(amountList);
				}
			}
		}
		return resList;
	}
	
	@Override
	public List<RevenueAmount> calcByPrjType(List<RevenueRole> roleList,String startTime, String endTime, List<Integer> prjTypeList){
		List<RevenueAmount> resList = new ArrayList<RevenueAmount>();
		if(roleList!=null&&roleList.size()>0){
			for(RevenueRole revenueRole:roleList){
				List<RevenueAmount> amountList = calcByRole(revenueRole, startTime, endTime, prjTypeList);
				resList.addAll(amountList);
			}
		}
		return resList;
	}

}
