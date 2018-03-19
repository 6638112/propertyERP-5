package com.cnfantasia.server.ms.revenue.task;

import java.util.List;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountNoTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigByTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RoomValidateForRevenue;
import com.cnfantasia.server.ms.revenue.serviceUn.IRevenueCalcRuleByType;

/**
 * 门牌验证收益2RevSignal
* Filename:    RoomValidate2RevSignal.java
* @version:    1.0.0
* Create at:   2015年11月19日 下午12:45:09
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       shiyl             1.0             1.0 Version
 */
public class RoomValidate2RevSignal extends AbstractSyn2RevSignal<RoomValidateForRevenue,Integer>{
	private IRevenueCalcRuleByType<Integer> roomValidateCalcRule;
	public void setRoomValidateCalcRule(IRevenueCalcRuleByType<Integer> roomValidateCalcRule) {
		this.roomValidateCalcRule = roomValidateCalcRule;
	}
	
	@Override
	public List<RevenueConfigByTime> getActiveRevenueConfigList(Integer projectType, RevenueRole revenueRole) {
		List<RevenueConfigByTime> activeRcList = revenueDao.selectActiveRevenueConfigList(revenueRole,projectType);
		return activeRcList;
	}

	@Override
	public List<RoomValidateForRevenue> getToSigList(Integer projectType, RevenueRole revenueRole, String configStartTime, String configEndTime) {
		List<RoomValidateForRevenue> toSigList = revenueDao.selectToSigRoomValidateList(revenueRole,projectType,configStartTime,configEndTime);
		return toSigList;
	}

	@Override
	public RevenueAmountNoTime getRevenueAmount(Integer param, RevenueRole revenueRole, RevenueConfig revenueConfig) {
		return roomValidateCalcRule.calc(param, revenueRole, revenueConfig);
	}

	
}
