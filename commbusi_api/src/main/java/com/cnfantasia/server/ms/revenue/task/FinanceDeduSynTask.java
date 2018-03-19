package com.cnfantasia.server.ms.revenue.task;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.FinanceDeduRevenue;
import com.cnfantasia.server.ms.revenue.service.RevenueFinanceDeduService;

/**
 * 物业宝抵扣收益定时任务
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       yewj             1.0             1.0 Version
 */
public class FinanceDeduSynTask implements ISynTask{
	
	private Log logger = LogFactory.getLog(getClass());
	
	private RevenueFinanceDeduService revenueFinanceDeduService;
	
	@Override
	public Integer execTask() {
		int size = 0;
		logger.debug("FinanceDeduSynTask start:" + DateUtils.getCurrentDate());
		
		//避免极端情况下大量数据一次性处理，此处只会循环每次处理300条，在某次少于50(或者少于100)条即已经处理完成。
		while(true) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Date currentDate = new Date();
			paramMap.put("currentDate", currentDate);
			List<FinanceDeduRevenue> revenueList = revenueFinanceDeduService.updateAndGetFinanceDeduRevenueList(paramMap);
			
			int saveSize = saveFinanceDeduRevenueList(revenueList);
			size += saveSize;
			
			if(saveSize < 50) {
				break;
			}
		}
		
		logger.debug("FinanceDeduSynTask end:" + DateUtils.getCurrentDate() + ";size:" + size);
		return size;
	}
	
	private int saveFinanceDeduRevenueList(List<FinanceDeduRevenue> revenueList) {
		int i = 0;
		for(FinanceDeduRevenue revenue : revenueList) {
			try {
				construct(revenue);
				revenueFinanceDeduService.saveRevenueFinance(revenue);
				i++;
			} catch(Exception e) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", revenue.getGoalId());
				paramMap.put("revenueStatusTo", -1);
				revenueFinanceDeduService.updateRevenueStatus(paramMap);
				logger.error("FinanceDeduSynTask failed orderId:" + revenue.getGoalId());
				logger.error(e.getMessage(), e);
			}
		}
		return i;
	}
	
	private void construct(FinanceDeduRevenue revenue) {
//		RevenueSignalAmount revenueSignalAmount = (RevenueSignalAmount) carRevenue;
		revenue.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
		revenue.setGoalType(RevenueDict.RevenueProject.FinanceDeduAmount);
//		revenue.setSrcMoney(revenue.getAmount());
		revenue.setMoneyTime(DateUtils.getCurrentDate());
		revenue.setRevApplyId(null); //t_revenue_apply.f_id
		revenue.setRevConfigId(BigInteger.ZERO);
		revenue.setRevConfigJson("");
		revenue.setTkStatus(RevenueDict.TkStatus.Undo);
		revenue.setAmountPtbt(0.0); //平台补贴额，暂时不补贴
		revenue.setGoalRevTime(DateUtils.getCurrentDate());
		revenue.setGoalRevTimeStr(revenue.getGoalRevTime().substring(0, 7));
		revenue.setSys0AddTime(DateUtils.getCurrentDate());
		revenue.setSys0DelState(0);
		revenue.setAmountUsrReal(revenue.getAmount());
		revenue.setMiniRoleType(RevenueDict.MiniRoleType.PropertyCompany);
		revenue.settGroupBuildingId(revenue.getGbId());
	}

	public void setRevenueFinanceDeduService(
			RevenueFinanceDeduService revenueFinanceDeduService) {
		this.revenueFinanceDeduService = revenueFinanceDeduService;
	}

}
