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
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.constant.RevenueConstant;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.FinanceRevenue;
import com.cnfantasia.server.ms.revenue.service.RevenueFinanceService;
import com.cnfantasia.server.ms.revenue.util.RevenueConfigUtil;

/**
 * 停车宝收益实收定时任务
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       yewj             1.0             1.0 Version
 */
@Repository
public class CarFinanceSynTask implements ISynTask{
	
	private Log logger = LogFactory.getLog(getClass());
	
	private RevenueFinanceService revenueFinanceService;
	
	@Override
	public Integer execTask() {
		int size = 0;
		logger.debug("FinanceSynTask start:" + DateUtils.getCurrentDate());
		
		//避免极端情况下大量数据一次性处理，此处只会循环每次处理100条，在某次少于50(或者少于100)条即已经处理完成。
		while(true) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Date currentDate = new Date();
			paramMap.put("currentDate", currentDate);
			paramMap.put("financeType", 2);//停车宝
			List<FinanceRevenue> financeRevenueList = revenueFinanceService.updateAndGetFinanceRevenueList(paramMap);
			
			int saveSize = saveFinanceRevenueList(financeRevenueList);
			size += saveSize;
			
			if(saveSize < 50) {
				break;
			}
		}
		
		logger.debug("FinanceSynTask end:" + DateUtils.getCurrentDate() + ";size:" + size);
		return size;
	}
	
	private int saveFinanceRevenueList(List<FinanceRevenue> financeRevenueList) {
		int i = 0;
		for(FinanceRevenue financeRevenue : financeRevenueList) {
			try {
				List<RevenueSignalAmount> saList = new ArrayList<RevenueSignalAmount>();
				
				construct(financeRevenue, saList);
				revenueFinanceService.saveRevenueFinance(saList);
				i++;
			} catch(Exception e) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("financeReqId", financeRevenue.getFinaceReqId());
				paramMap.put("revenueStatusTo", -1);
				revenueFinanceService.updateFinanceRevenueStatus(paramMap);
				logger.error("finance-task failed financeReqId:" + financeRevenue.getFinaceReqId());
				logger.error(e.getMessage(), e);
			}
		}
		return i;
	}
	
	private void construct(FinanceRevenue financeRevenue, List<RevenueSignalAmount> saList) {
		RevenueSignalAmount revenueSignalAmount = new RevenueSignalAmount();
		revenueSignalAmount.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
		
		revenueSignalAmount.setGoalId(BigInteger.valueOf(financeRevenue.getFinaceReqId()));
		revenueSignalAmount.setGoalType(RevenueDict.RevenueProject.CarFinanceBaoAmout);
		revenueSignalAmount.setSrcMoney(financeRevenue.getBuyMoney().doubleValue());
		revenueSignalAmount.setMoneyTime(DateUtils.getCurrentDate());
		revenueSignalAmount.setRevApplyId(null); //t_revenue_apply.f_id
		revenueSignalAmount.setRevConfigId(BigInteger.ZERO);
		revenueSignalAmount.setRevConfigJson("");
		revenueSignalAmount.setTkStatus(RevenueDict.TkStatus.Undo);
		revenueSignalAmount.setAmountPtbt(0.0); //平台补贴额，暂时不补贴
		revenueSignalAmount.setSys0AddTime(DateUtils.getCurrentDate());
		revenueSignalAmount.setSys0DelState(0);
		revenueSignalAmount.settGroupBuildingId(financeRevenue.getGbId());
		revenueSignalAmount.settPropertyManagementFId(financeRevenue.getPropManagementId());
//		revenueSignalAmount.setPayFlowNo(dredgeBillRevenue.getFlowNo());
//		revenueSignalAmount.setPayMethod(dredgeBillRevenue.getPayType());
		
		//根据物业公司的ID和收益的时间取得相应的配置，原则上这个配置只有一个。前端有作校验，当然出现难于预料的多个时也只能取其中一个
//		String financeAllProfit = CnfantasiaCommbusiUtil.getSysParaValue("financeAllProfit");
//		BigDecimal financeAllProfitBd = BigDecimalUtil.div100(new BigDecimal(financeAllProfit), 4);
//		double totalAmout = financeAllProfitBd.doubleValue();
		double totalAmout = financeRevenue.getBuyMoney().doubleValue();
		RevenueConfig rc = null;
		if(financeRevenue.getPropertyCompanyId() == null) {
			financeRevenue.setPropertyCompanyId(RevenueConstant.DEFAULT_REVENUE_COMPANY_ID.longValue());
			financeRevenue.setPropertyCompanyName("解放区物业公司");
		}
		if(financeRevenue.getPropertyCompanyId() != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pcId", financeRevenue.getPropertyCompanyId());
			paramMap.put("goalRevTime", financeRevenue.getBuyTime());
			paramMap.put("projectType", RevenueDict.RevenueProject.CarFinanceBaoAmout);
			rc = revenueFinanceService.selectRevenueConfig(paramMap);
		}
		
		int financeProfitDate = CnfantasiaCommbusiUtil.getSysParaValueInt("financeProfitDate", 25);
		if(rc == null) { //无配置时，只生成平台的收益
			revenueFinanceService.updateFinanceRevenueStatus(revenueSignalAmount.getGoalId());
//			revenueSignalAmount.setAmount(BigDecimalUtil.div(totalAmout, 12, 4));
//			revenueSignalAmount.setAmountUsrReal(revenueSignalAmount.getAmount());
//			revenueSignalAmount.setMiniRoleType(RevenueDict.MiniRoleType.SysAdmin);
//			revenueSignalAmount.setMiniRoleId(RevenueConstant.PLATFORM);
//			revenueSignalAmount.setRoleName("平台收益");
//			
//			List<BigInteger> idList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount, 12);
//			for(int i=1; i <= 12; i++) { //从下个月开始，分12个月返回
//				RevenueSignalAmount revenueSignalAmount2 = new EbuyRevenueSignalAmount();
//				BeanUtils.copyProperties(revenueSignalAmount, revenueSignalAmount2);
//				revenueSignalAmount2.setGoalRevTime(DateUtils.getDayByMonthAndDateStr(financeRevenue.getBuyTime(), i, financeProfitDate));
//				revenueSignalAmount2.setId(idList.get(i-0));
//				saList.add(revenueSignalAmount2);
//			}
		} else {
			Double propertyProfit = RevenueConfigUtil.getPropertyProfit(rc, totalAmout);
			Double agentProfit = RevenueConfigUtil.getAgentProfit(rc, totalAmout);
			Double platformProfit = RevenueConfigUtil.getPlatformProfit(rc, totalAmout);
			revenueSignalAmount.setRevConfigId(rc.getId());
			revenueSignalAmount.setRevConfigJson(JSON.toJSONString(rc));
			
			//生成物业公司的收益
			int deductionCount = financeRevenue.getDeductionCount();
			if(propertyProfit != null && propertyProfit >= 0 && financeRevenue.getPropertyCompanyId() != null) {
//				platformProfit = BigDecimalUtil.sub(platformProfit, propertyProfit);
				RevenueSignalAmount ppSa = new EbuyRevenueSignalAmount();
				BeanUtils.copyProperties(revenueSignalAmount, ppSa);
				
				ppSa.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
				ppSa.setAmount(BigDecimalUtil.div(propertyProfit, deductionCount, 4));
				ppSa.setAmountUsrReal(0.0);
				ppSa.setAmountPtbt(ppSa.getAmount());
				ppSa.setMiniRoleType(RevenueDict.MiniRoleType.PropertyCompany);
				ppSa.setMiniRoleId(BigInteger.valueOf(financeRevenue.getPropertyCompanyId()));
				ppSa.setRoleName(financeRevenue.getPropertyCompanyName() == null ? "" : financeRevenue.getPropertyCompanyName());
				ppSa.settGroupBuildingId(financeRevenue.getGbId());
				
				List<BigInteger> idList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount, deductionCount);
				for(int i=1; i <= deductionCount; i++) { //从下个月开始，分12个月返回
					RevenueSignalAmount ppSa2 = new EbuyRevenueSignalAmount();
					BeanUtils.copyProperties(ppSa, ppSa2);
					ppSa2.setGoalRevTime(DateUtils.getDayByMonthAndDateStr(financeRevenue.getBuyTime(), i, financeProfitDate));
					ppSa2.setGoalRevTimeStr(ppSa2.getGoalRevTime().substring(0, 7));
					ppSa2.setId(idList.get(i-1));
					saList.add(ppSa2);
				}
			} else {//如果物业没有收益，则归结到平台上去
				platformProfit += propertyProfit;
			}
			
			//生成代理商的收益
			if(agentProfit != null && agentProfit > 0 && financeRevenue.getChannelPartnerId() != null ) {
//				platformProfit = BigDecimalUtil.sub(platformProfit, agentProfit);
				RevenueSignalAmount ppSa = new EbuyRevenueSignalAmount();
				BeanUtils.copyProperties(revenueSignalAmount, ppSa);
				
				ppSa.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
				ppSa.setAmount(BigDecimalUtil.div(agentProfit, deductionCount, 4));
				ppSa.setAmountUsrReal(0.0);
				ppSa.setAmountPtbt(ppSa.getAmount());
				ppSa.setMiniRoleType(RevenueDict.MiniRoleType.PropertyAgent);
				ppSa.setMiniRoleId(BigInteger.valueOf(financeRevenue.getChannelPartnerId()));
				ppSa.setRoleName(financeRevenue.getChannelPartnerName() == null ? "" : financeRevenue.getChannelPartnerName());
				ppSa.settGroupBuildingId(financeRevenue.getGbId());
				
				List<BigInteger> idList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount, deductionCount);
				for(int i=1; i <= deductionCount; i++) { //从下个月开始，分12个月返回
					RevenueSignalAmount ppSa2 = new EbuyRevenueSignalAmount();
					BeanUtils.copyProperties(ppSa, ppSa2);
					ppSa2.setGoalRevTime(DateUtils.getDayByMonthAndDateStr(financeRevenue.getBuyTime(), i, financeProfitDate));
					ppSa2.setGoalRevTimeStr(ppSa2.getGoalRevTime().substring(0, 7));
					ppSa2.setId(idList.get(i-1));
					saList.add(ppSa2);
				}
			} else {//如果代理没有收益，则归结到平台上去
				platformProfit += agentProfit;
			}
			
			//生成平台的收益
			if(platformProfit != null && platformProfit > 0 ) {
				RevenueSignalAmount ppSa = new EbuyRevenueSignalAmount();
				BeanUtils.copyProperties(revenueSignalAmount, ppSa);
				
				ppSa.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
				ppSa.setAmount(BigDecimalUtil.div(platformProfit, deductionCount, 4));
				ppSa.setAmountUsrReal(0.0);
				ppSa.setAmountPtbt(ppSa.getAmount());
				ppSa.setMiniRoleType(RevenueDict.MiniRoleType.SysAdmin);
				ppSa.setMiniRoleId(RevenueConstant.PLATFORM);
				ppSa.setRoleName("平台收益");
				ppSa.settGroupBuildingId(financeRevenue.getGbId());
				
				List<BigInteger> idList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount, deductionCount);
				for(int i=1; i <= deductionCount; i++) { //从下个月开始，分12个月返回
					RevenueSignalAmount ppSa2 = new EbuyRevenueSignalAmount();
					BeanUtils.copyProperties(ppSa, ppSa2);
					ppSa2.setGoalRevTime(DateUtils.getDayByMonthAndDateStr(financeRevenue.getBuyTime(), i, financeProfitDate));
					ppSa2.setGoalRevTimeStr(ppSa2.getGoalRevTime().substring(0, 7));
					ppSa2.setId(idList.get(i-1));
					saList.add(ppSa2);
				}
			}
		}
	}

	public void setRevenueFinanceService(RevenueFinanceService revenueFinanceService) {
		this.revenueFinanceService = revenueFinanceService;
	}

}
