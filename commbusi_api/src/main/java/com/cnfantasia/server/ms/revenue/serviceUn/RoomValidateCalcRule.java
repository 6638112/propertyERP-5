package com.cnfantasia.server.ms.revenue.serviceUn;

import java.math.BigDecimal;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;

/**
 * 门牌验证计算规则
 * 根据门牌认证数据计算收益规则
* Filename:    RoomValidateCalcRule.java
* @version:    1.0.0
* Create at:   2015年11月16日 下午5:14:03
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月16日       shiyl             1.0             1.0 Version
 */
public class RoomValidateCalcRule extends AbstractRevenueCalcRuleByType<Integer>{
	private IRevenueDao revenueDao;
	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	@Override
	public Integer getProjectType() {
		return RevenueDict.RevenueProject.RoomValidate;
	}
	
	/**
	 * 根据门牌认证数量计算收益结果信息
	 */
	@Override
	public BigDecimal calcMoney(Integer param,RevenueRole revenueRole,RevenueConfig revenueConfig) {
		Integer ruleType = revenueConfig.getRuleType();
		UserRole role = revenueRole.getRole();
		
		//1.获取收益配比
		Double percentValue = null;
		switch (role) {
			case PropertyAgent:
				percentValue = revenueConfig.getAgentValue();
				break;
			case PropertyCompany:
				percentValue = revenueConfig.getCompanyValue();
				break;
			/*case SysAdmin:
				percentValue = revenueConfig.getPlatformValue();
				break;*/
			default:
				throw new BusinessRuntimeException("RoomValidateCalcRule.calcMoney.roleNotConfig",new Object[]{role.getCode()});
		}
		if(percentValue==null){
			throw new BusinessRuntimeException("RoomValidateCalcRule.calcMoney.percentValue.null");
		}
		//2.根据配置类型计算收益
		BigDecimal resultMoney = null;
		if(RevenueDict.RuleType.ByCount.compareTo(ruleType)==0){
			resultMoney = new BigDecimal(param).multiply(new BigDecimal(percentValue));//.setScale(2,BigDecimal.ROUND_FLOOR).doubleValue();//保留两位小数，只舍不入
		}/*else if(RevenueDict.RuleType.ByPercent.compareTo(ruleType)==0){
			
		}*/else{
			throw new BusinessRuntimeException("RoomValidateCalcRule.calcMoney.ruleTypeNotSupport",new Object[]{ruleType});
		}
		return resultMoney;
	}
	
	/**
	 * 获取指定时间段内认证的门牌数量
	 */
	@Override
	public Integer getCalcInfo(RevenueRole revenueRole,String startTime, String endTime) {
		UserRole role = revenueRole.getRole();
		Integer resCount = null;
		switch (role) {
			case PropertyCompany://查询物业公司角色的验证门牌
				resCount = revenueDao.selectRoomValidCountByPropertyCompanyId(revenueRole,startTime,endTime);
				break;
			/*case PropertyAgent://查询代理商角色的验证门牌
				resCount = revenueDao.selectRoomValidCountByPropertyAgentId(roleId,startDate,endDate);
				break;*/
			default:
				throw new BusinessRuntimeException("RoomValidateCalcRule.getCalcInfo.roleNotSupport",new Object[]{role.getCode()});
		}
		return resCount;
	}
	
	
}
