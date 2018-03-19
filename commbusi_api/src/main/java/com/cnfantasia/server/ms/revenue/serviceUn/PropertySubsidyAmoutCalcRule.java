package com.cnfantasia.server.ms.revenue.serviceUn;

import java.math.BigDecimal;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;

/**
 * 物业费补贴金额计算规则
* Filename:    PropertySubsidyAmoutCalcRule.java
* @version:    1.0.0
* Create at:   2015年11月24日 下午1:21:56
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月24日       shiyl             1.0             1.0 Version
 */
public class PropertySubsidyAmoutCalcRule extends AbstractRevenueCalcRuleByType<BigDecimal>{
	private IRevenueDao revenueDao;
	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	@Override
	public Integer getProjectType() {
		return RevenueDict.RevenueProject.PropertySubsidyAmout;
	}

	@Override
	public BigDecimal calcMoney(BigDecimal param, RevenueRole revenueRole,
			RevenueConfig revenueConfig) {
		Integer ruleType = revenueConfig.getRuleType();
		UserRole role = revenueRole.getRole();
		
		//1.获取收益配比
		Double percentValue = null;
		switch (role) {
			/*case PropertyAgent:
				percentValue = revenueConfig.getAgentValue();
				break;*/
			case PropertyCompany:
				percentValue = revenueConfig.getCompanyValue();
				break;
			/*case SysAdmin:
				percentValue = revenueConfig.getPlatformValue();
				break;*/
			default:
				throw new BusinessRuntimeException("PropertySubsidyAmoutCalcRule.calcMoney.roleNotConfig",new Object[]{role.getCode()});
		}
		if(percentValue==null){
			throw new BusinessRuntimeException("PropertySubsidyAmoutCalcRule.calcMoney.percentValue.null");
		}
		//2.根据配置类型计算收益
		BigDecimal resultMoney = null;
		/*if(RevenueDict.RuleType.ByCount.compareTo(ruleType)==0){
		 * 
		}else*/ if(RevenueDict.RuleType.ByPercent.compareTo(ruleType)==0){
			resultMoney = param.multiply(new BigDecimal(percentValue).divide(new BigDecimal(100)));//.setScale(2,BigDecimal.ROUND_FLOOR).doubleValue();//保留两位小数，只舍不入
		}else{
			throw new BusinessRuntimeException("PropertySubsidyAmoutCalcRule.calcMoney.ruleTypeNotSupport",new Object[]{ruleType});
		}
		return resultMoney;
	}

	@Override
	public BigDecimal getCalcInfo(RevenueRole revenueRole, String startTime,
			String endTime) {
		UserRole role = revenueRole.getRole();
		BigDecimal resCount = BigDecimal.ZERO;
		switch (role) {
			case PropertyCompany://查询物业公司对应某时间段的用户实际缴费金额总额
				Double realPayMoneyTotal = revenueDao.selectPayBillSubsidyMoneyTotal(revenueRole,startTime,endTime);
				if(realPayMoneyTotal!=null){
					resCount = new BigDecimal(realPayMoneyTotal);
				}
				break;
			/*case PropertyAgent://查询代理商角色的验证门牌
				resCount = revenueDao.selectRoomValidCountByPropertyAgentId(roleId,startDate,endDate);
				break;*/
			default:
				throw new BusinessRuntimeException("PropertySubsidyAmoutCalcRule.getCalcInfo.roleNotSupport",new Object[]{role.getCode()});
		}
		return resCount;
	}

}
