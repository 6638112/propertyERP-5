package com.cnfantasia.server.ms.revenue.serviceUn;

import java.math.BigDecimal;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;

/**
 * 维修费用金额收益计算
 * @author wenfq 2015-11-26
 */
public class DredgePayAmoutCalcRule extends AbstractRevenueCalcRuleByType<BigDecimal>{
	private IRevenueDao revenueDao;
	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	@Override
	public Integer getProjectType() {
		return RevenueDict.RevenueProject.ServiceOrder;
	}

	@Override
	public BigDecimal calcMoney(BigDecimal param, RevenueRole revenueRole, RevenueConfig revenueConfig) {
		Integer ruleType = revenueConfig.getRuleType();
		UserRole role = revenueRole.getRole();
		
		//1.获取收益配比
		Double percentValue = null;
		switch (role) {
			case RepairMaster:
				percentValue = revenueConfig.getCompanyValue();
				break;
			default:
				throw new BusinessRuntimeException("DredgePayAmoutCalcRule.calcMoney.roleNotConfig",new Object[]{role.getCode()});
		}
		if(percentValue==null){
			throw new BusinessRuntimeException("DredgePayAmoutCalcRule.calcMoney.percentValue.null");
		}
		//2.根据配置类型计算收益
		BigDecimal resultMoney = null;
		/*if(RevenueDict.RuleType.ByCount.compareTo(ruleType)==0){
		 * 
		}else*/ if(RevenueDict.RuleType.ByPercent.compareTo(ruleType)==0){
			resultMoney = param.multiply(new BigDecimal(percentValue).divide(new BigDecimal(100)));//.setScale(2,BigDecimal.ROUND_FLOOR).doubleValue();//保留两位小数，只舍不入
		}else{
			throw new BusinessRuntimeException("DredgePayAmoutCalcRule.calcMoney.ruleTypeNotSupport",new Object[]{ruleType});
		}
		return resultMoney;
	}

	@Override
	public BigDecimal getCalcInfo(RevenueRole revenueRole,String startTime, String endTime) {
		UserRole role = revenueRole.getRole();
		BigDecimal resCount = BigDecimal.ZERO;
		switch (role) {
			case RepairMaster://查询物业公司对应某时间段的用户实际缴费金额总额
				Double realPayMoneyTotal = revenueDao.selectDredgePayMoneyTotal(revenueRole,startTime,endTime);
				if(realPayMoneyTotal!=null){
					resCount = new BigDecimal(realPayMoneyTotal);
				}
				break;
			/*case PropertyAgent://查询代理商角色的验证门牌
				resCount = revenueDao.selectRoomValidCountByPropertyAgentId(roleId,startDate,endDate);
				break;*/
			default:
				throw new BusinessRuntimeException("DredgePayAmoutCalcRule.getCalcInfo.roleNotSupport",new Object[]{role.getCode()});
		}
		return resCount;
	}
	
}
