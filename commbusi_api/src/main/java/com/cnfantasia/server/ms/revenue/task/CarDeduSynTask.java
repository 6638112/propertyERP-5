package com.cnfantasia.server.ms.revenue.task;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.CarDeduRevenue;
import com.cnfantasia.server.ms.revenue.service.RevenueCarDeduService;

/**
 * 停车宝抵扣收益定时任务
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       yewj             1.0             1.0 Version
 */
public class CarDeduSynTask implements ISynTask{
	
	private Log logger = LogFactory.getLog(getClass());
	
	private RevenueCarDeduService revenueCarDeduService;
	
	@Override
	public Integer execTask() {
		int size = 0;
		logger.debug("CarDeduSynTask start:" + DateUtils.getCurrentDate());
		
		//避免极端情况下大量数据一次性处理，此处只会循环每次处理300条，在某次少于50(或者少于100)条即已经处理完成。
		while(true) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Date currentDate = new Date();
			paramMap.put("currentDate", currentDate);
			List<CarDeduRevenue> revenueList = revenueCarDeduService.updateAndGetCarDeduRevenueList(paramMap);
			
			int saveSize = saveFinanceDeduRevenueList(revenueList);
			size += saveSize;
			
			if(saveSize < 50) {
				break;
			}
		}
		
		logger.debug("FinanceDeduSynTask end:" + DateUtils.getCurrentDate() + ";size:" + size);
		return size;
	}
	
	private int saveFinanceDeduRevenueList(List<CarDeduRevenue> revenueList) {
		int i = 0;
		for(CarDeduRevenue revenue : revenueList) {
			try {
				List<RevenueSignalAmount> rsaList = new ArrayList<RevenueSignalAmount>();
				construct(revenue, rsaList);
				revenueCarDeduService.saveRevenueFinance(rsaList);
				i++;
			} catch(Exception e) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", revenue.getGoalId());
				paramMap.put("revenueStatusTo", -1);
				revenueCarDeduService.updateRevenueStatus(paramMap);
				logger.error("FinanceDeduSynTask failed orderId:" + revenue.getGoalId());
				logger.error(e.getMessage(), e);
			}
		}
		return i;
	}
	
	private void construct(CarDeduRevenue revenue, List<RevenueSignalAmount> rsaList) {
//		RevenueSignalAmount revenueSignalAmount = (RevenueSignalAmount) carRevenue;
		revenue.setGoalType(RevenueDict.RevenueProject.CarDeduAmount);
		revenue.setSrcMoney(revenue.getAmount());
		revenue.setMoneyTime(DateUtils.getCurrentDate());
		revenue.setRevApplyId(null); //t_revenue_apply.f_id
		revenue.setRevConfigId(BigInteger.ZERO);
		revenue.setRevConfigJson("");
		revenue.setTkStatus(RevenueDict.TkStatus.Undo);
		revenue.setAmountPtbt(0.0); //平台补贴额，暂时不补贴
//		revenue.setGoalRevTime(DateUtils.formatTime(goalRevTime));
//		revenue.setGoalRevTimeStr(revenue.getGoalRevTime().substring(0, 7));
		revenue.setSys0AddTime(DateUtils.getCurrentDate());
		revenue.setSys0DelState(0);
		revenue.setAmountUsrReal(revenue.getAmount());
		revenue.setMiniRoleType(RevenueDict.MiniRoleType.PropertyCompany);
		revenue.settGroupBuildingId(revenue.getGbId());
		revenue.settPropertyManagementFId(revenue.gettPropertyManagementFId());
		
		Date goalRevTime = revenue.getDeduBeginDate();
		
		while(goalRevTime.before(revenue.getDeduEndDate())) {
			RevenueSignalAmount revenueSignalAmount = new RevenueSignalAmount();
			BeanUtils.copyProperties(revenue, revenueSignalAmount);
			revenueSignalAmount.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
			revenueSignalAmount.setGoalRevTime(DateUtils.formatTime(goalRevTime));
			revenueSignalAmount.setGoalRevTimeStr(revenueSignalAmount.getGoalRevTime().substring(0, 7));
			
			rsaList.add(revenueSignalAmount);
			goalRevTime = DateUtils.addMonths(goalRevTime, 1);
		}
	}

	public void setRevenueCarDeduService(RevenueCarDeduService revenueCarDeduService) {
		this.revenueCarDeduService = revenueCarDeduService;
	}

}
