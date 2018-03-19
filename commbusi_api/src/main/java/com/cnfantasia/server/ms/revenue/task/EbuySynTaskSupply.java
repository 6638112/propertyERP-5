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

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.ebuy.constant.EbuySupplyMerchantConstant;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity.RevenueSignalAmountEbuy;
import com.cnfantasia.server.ms.revenue.constant.RevenueConstant;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.EbuyOrderRevenue;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;
import com.cnfantasia.server.ms.revenue.service.RevenueDredgeService;
import com.cnfantasia.server.ms.revenue.util.RevenueConfigUtil;

/**
 * 电商收益计算定时任务
* Filename:    EbuySynTaskSupply.java
* @version:    1.0.0
* Create at:   2015年11月19日 下午2:35:32
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       yewj             1.0             1.0 Version
 */
public class EbuySynTaskSupply implements ISynTask {
	
	private Log logger = LogFactory.getLog(getClass());
	
	private IRevenueService revenueService;
	
	private RevenueDredgeService revenueDredgeService;
	
	@Override
	public Integer execTask() {
		int size = 0;
		logger.debug("EbuySynTaskSupply start:" + DateUtils.getCurrentDate());
		
		//避免极端情况下大量数据一次性处理，此处只会循环每次处理100条，在某次少于50(或者少于100)条即已经处理完成。
		while(true) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Date currentDate = new Date();
			paramMap.put("currentDate", currentDate);
			List<EbuyOrderRevenue> ebuyOrderRevenueList = revenueService.updateAndGetEbuyRevenueList(paramMap);
			
			int saveSize = saveEbuyOrderRevenueList(ebuyOrderRevenueList);
			size += saveSize;
			
			if(saveSize < 50) {
				break;
			}
		}
		
		logger.debug("EbuySynTaskSupply end:" + DateUtils.getCurrentDate() + ";size:" + size);
		return size;
	}
	
	private int saveEbuyOrderRevenueList(List<EbuyOrderRevenue> revenueList) {
		int i = 0;
		for(EbuyOrderRevenue revenue : revenueList) {
			try {
				List<RevenueSignalAmount> saList = new ArrayList<RevenueSignalAmount>();
				List<RevenueSignalAmountEbuy> saEbuyList = new ArrayList<RevenueSignalAmountEbuy>();
				
				construct(revenue, saList, saEbuyList);
				revenueService.saveRevenueEbuy(saList, saEbuyList);
				i++;
			} catch(Exception e) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("deliverId", revenue.getDelivOrderId());
				paramMap.put("revenueStatusTo", -1);
				revenueService.updateDeliveryRevenueStatus(paramMap);
				logger.error(e.getMessage(), e);
			}
		}
		return i;
	}
	
	private void construct(EbuyOrderRevenue ebuyOrderRevenue, List<RevenueSignalAmount> saList, List<RevenueSignalAmountEbuy> saEbuyList) {
		RevenueSignalAmount revenueSignalAmount = new RevenueSignalAmount();
		RevenueSignalAmountEbuy revenueSignalAmountEbuy = new RevenueSignalAmountEbuy();
		revenueSignalAmount.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
		revenueSignalAmountEbuy.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount_ebuy));
		revenueSignalAmountEbuy.settRevenueSignalAmountId(revenueSignalAmount.getId());
		
		revenueSignalAmount.setGoalId(ebuyOrderRevenue.getDelivOrderId());
		revenueSignalAmount.setGoalRevTime(DateUtils.getCurrentDate());
		revenueSignalAmount.setGoalRevTimeStr("");
//		revenueSignalAmount.setGoalRevTime(DateUtils.getCurrentDate());
		revenueSignalAmount.setGoalType(RevenueDict.RevenueProject.MarketAmout);
		revenueSignalAmount.setMiniRoleId(ebuyOrderRevenue.getSupplyId());
		revenueSignalAmount.setMiniRoleType(RevenueDict.MiniRoleType.DownstairStore);
		revenueSignalAmount.setSrcMoney(BigDecimalUtil.div100(ebuyOrderRevenue.getAllMount()).doubleValue());
		revenueSignalAmount.setMoneyTime(DateUtils.getCurrentDate());
		revenueSignalAmount.setRevApplyId(null); //t_revenue_apply.f_id
		revenueSignalAmount.setRevConfigId(BigInteger.ZERO);
		revenueSignalAmount.setRevConfigJson("");
		revenueSignalAmount.setTkStatus(RevenueDict.TkStatus.Undo);
		Long delivRealPay = ebuyOrderRevenue.getAmountTotal() - ebuyOrderRevenue.getDiscountMoney();
		revenueSignalAmount.setAmountPtbt(BigDecimalUtil.div100(ebuyOrderRevenue.getDiscountMoney()).doubleValue());
		revenueSignalAmount.setAmountUsrReal(BigDecimalUtil.div100(delivRealPay).doubleValue());
		revenueSignalAmount.setRoleName(ebuyOrderRevenue.getSupplyName());
		revenueSignalAmount.setSys0AddTime(DateUtils.getCurrentDate());
		revenueSignalAmount.setSys0DelState(0);
		revenueSignalAmount.settGroupBuildingId(ebuyOrderRevenue.getGroupBuildingId());
		revenueSignalAmount.settPropertyManagementFId(ebuyOrderRevenue.getPropertyManagementId());
		
		
		revenueSignalAmountEbuy.setPayMethod(ebuyOrderRevenue.getPayType()); //支付渠道 支付方式=={"1":"微信支付","2":"支付宝","3":"银联支付","4":"纯粮票支付","5":"纯积分支付","6":"微信轻应用支付","7":"纯优惠券支付"}
		revenueSignalAmountEbuy.setFlowNo(ebuyOrderRevenue.getFlowNo());
		
		revenueSignalAmountEbuy.setAmountDiscount(BigDecimalUtil.div100(ebuyOrderRevenue.getDiscountMoney()).doubleValue());
		revenueSignalAmountEbuy.setAmountReal(BigDecimalUtil.div100(delivRealPay).doubleValue());
		revenueSignalAmountEbuy.setAmountOrderReal(BigDecimalUtil.div100(ebuyOrderRevenue.getAmountOrderReal()).doubleValue()); //订单实付
		revenueSignalAmountEbuy.setAmountTotal(BigDecimalUtil.div100(ebuyOrderRevenue.getAmountTotal()).doubleValue()); //运单总额
		revenueSignalAmountEbuy.setPayMethod(ebuyOrderRevenue.getPayType()); //支付渠道 支付方式=={"1":"微信支付","2":"支付宝","3":"银联支付","4":"纯粮票支付","5":"纯积分支付","6":"微信轻应用支付","7":"纯优惠券支付"}
		revenueSignalAmountEbuy.setFlowNo(ebuyOrderRevenue.getFlowNo());
		revenueSignalAmountEbuy.setPayTm(DateUtils.formatTime(ebuyOrderRevenue.getPayTime()));
		revenueSignalAmountEbuy.setAmountRefund(BigDecimalUtil.div100(ebuyOrderRevenue.getRefundMoney()).doubleValue()); //退款金额
		revenueSignalAmountEbuy.settGroupBuildingId(ebuyOrderRevenue.getGroupBuildingId());
		revenueSignalAmountEbuy.setGroupBuildingName(ebuyOrderRevenue.getGroupBuildingName());
		revenueSignalAmountEbuy.setPcName(ebuyOrderRevenue.getPcName());
		revenueSignalAmountEbuy.settPcId(ebuyOrderRevenue.getPcId());
		revenueSignalAmountEbuy.setAgentName(ebuyOrderRevenue.getAgentName());
		revenueSignalAmountEbuy.settAgentId(ebuyOrderRevenue.getAgentId());
		revenueSignalAmountEbuy.settUserId(ebuyOrderRevenue.getUserId());
		revenueSignalAmountEbuy.setHuaId(ebuyOrderRevenue.getHuaId());
		revenueSignalAmountEbuy.setAmoutDeduct(0.0);
		
		//根据物业公司的ID和收益的时间取得相应的配置，原则上这个配置只有一个。前端有作校验，当然出现难于预料的多个时也只能取其中一个
		double totalAmout = BigDecimalUtil.div100(ebuyOrderRevenue.getAllMount()).doubleValue();
		RevenueConfig rc = null;
		if(ebuyOrderRevenue.getPcId() == null) {
			ebuyOrderRevenue.setPcId(RevenueConstant.DEFAULT_REVENUE_COMPANY_ID);
			ebuyOrderRevenue.setPcName("解放区物业公司");
		}
		if(ebuyOrderRevenue.getPcId() != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pcId", ebuyOrderRevenue.getPcId());
			paramMap.put("goalRevTime", ebuyOrderRevenue.getPayTime());
			paramMap.put("projectType", RevenueDict.RevenueProject.MarketAmout);
			rc = revenueDredgeService.selectRevenueConfig(paramMap);
		}
		
		if(rc == null || !rc.getRuleType().equals(RevenueDict.RuleType.ByPercent) || ebuyOrderRevenue.getSmRevenueRate() == null
				|| EbuySupplyMerchantConstant.RevenueType.BUY == ebuyOrderRevenue.getSmRevenueType()) { //无配置时，只生成店主的收益
			revenueSignalAmount.setAmount(totalAmout);
//			revenueSignalAmount.setAmountUsrReal(totalAmout);
			revenueSignalAmountEbuy.setAmoutDeduct(0.0);
			saList.add(revenueSignalAmount);
			saEbuyList.add(revenueSignalAmountEbuy);
		} else {
			Double platformProfit = BigDecimalUtil.mul(totalAmout, ebuyOrderRevenue.getSmRevenueRate(), 2); //平台收入
			if(platformProfit < 0) {
				platformProfit = 0.0;
			}
			Double propertyProfit = RevenueConfigUtil.getPropertyProfit2(rc, platformProfit); //物业收益
			Double agentProfit = RevenueConfigUtil.getAgentProfit2(rc, platformProfit); //代理收益
			
//			Double propertyProfit = RevenueConfigUtil.getPropertyProfit(rc, totalAmout); //物业收益
//			Double agentProfit = RevenueConfigUtil.getAgentProfit(rc, totalAmout); //代理收益
//			Double platformProfit = RevenueConfigUtil.getPlatformProfit(rc, totalAmout); //平台收益
//			propertyProfit = BigDecimalUtil.mul(propertyProfit, ebuyOrderRevenue.getSmRevenueRate(), 2);
//			agentProfit = BigDecimalUtil.mul(agentProfit, ebuyOrderRevenue.getSmRevenueRate(), 2);
//			platformProfit = BigDecimalUtil.mul(platformProfit, ebuyOrderRevenue.getSmRevenueRate(), 2);
			
			Double ebuyProfit = totalAmout - platformProfit; //店主收益
			revenueSignalAmount.setRevConfigId(rc.getId());
			revenueSignalAmount.setRevConfigJson(JSON.toJSONString(rc));
			
			//生成物业公司的收益
			if(propertyProfit != null && propertyProfit >= 0.01 ) {
				platformProfit = BigDecimalUtil.sub(platformProfit, propertyProfit);
				RevenueSignalAmount ppSa = new EbuyRevenueSignalAmount();
				RevenueSignalAmountEbuy ppSaEbuy = new RevenueSignalAmountEbuy();
				BeanUtils.copyProperties(revenueSignalAmount, ppSa);
				BeanUtils.copyProperties(revenueSignalAmountEbuy, ppSaEbuy);
				
				ppSa.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
				ppSaEbuy.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount_ebuy));
				ppSaEbuy.settRevenueSignalAmountId(ppSa.getId());
				
				ppSa.setAmount(propertyProfit);
//				ppSa.setAmountPtbt(propertyProfit);
				ppSa.setAmountUsrReal(0.0);
				ppSa.setAmountPtbt(propertyProfit);
				ppSa.setMiniRoleType(RevenueDict.MiniRoleType.PropertyCompany);
				ppSa.setMiniRoleId(ebuyOrderRevenue.getPcId());
				ppSa.setRoleName(ebuyOrderRevenue.getPcName());
				ppSa.settGroupBuildingId(ebuyOrderRevenue.getGbId());
				
				saList.add(ppSa);
				saEbuyList.add(ppSaEbuy);
			}
			
			//生成代理商的收益
			if(agentProfit != null && agentProfit >= 0.01 && ebuyOrderRevenue.getAgentId() != null ) {
				platformProfit = BigDecimalUtil.sub(platformProfit, agentProfit);
				RevenueSignalAmount ppSa = new EbuyRevenueSignalAmount();
				RevenueSignalAmountEbuy ppSaEbuy = new RevenueSignalAmountEbuy();
				BeanUtils.copyProperties(revenueSignalAmount, ppSa);
				BeanUtils.copyProperties(revenueSignalAmountEbuy, ppSaEbuy);
				
				ppSa.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
				ppSaEbuy.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount_ebuy));
				ppSaEbuy.settRevenueSignalAmountId(ppSa.getId());
				
				ppSa.setAmount(agentProfit);
				ppSa.setAmountUsrReal(0.0);
				ppSa.setAmountPtbt(agentProfit);
				ppSa.setMiniRoleType(RevenueDict.MiniRoleType.PropertyAgent);
				ppSa.setMiniRoleId(ebuyOrderRevenue.getAgentId());
				ppSa.setRoleName(ebuyOrderRevenue.getAgentName());
				ppSa.settGroupBuildingId(ebuyOrderRevenue.getGbId());
				
				saList.add(ppSa);
				saEbuyList.add(ppSaEbuy);
			}
			
			//生成平台的收益
			if(platformProfit != null && platformProfit >= 0.01 ) {
				RevenueSignalAmount ppSa = new EbuyRevenueSignalAmount();
				RevenueSignalAmountEbuy ppSaEbuy = new RevenueSignalAmountEbuy();
				BeanUtils.copyProperties(revenueSignalAmount, ppSa);
				BeanUtils.copyProperties(revenueSignalAmountEbuy, ppSaEbuy);
				
				ppSa.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
				ppSaEbuy.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount_ebuy));
				ppSaEbuy.settRevenueSignalAmountId(ppSa.getId());
				
				ppSa.setAmount(platformProfit);
//				ppSa.setAmountPtbt(platformProfit);
//				ppSa.setAmountUsrReal(platformProfit);
				ppSa.setAmountUsrReal(0.0);
				ppSa.setAmountPtbt(platformProfit);
				ppSa.setMiniRoleType(RevenueDict.MiniRoleType.SysAdmin);
				ppSa.setMiniRoleId(RevenueConstant.PLATFORM);
				ppSa.setRoleName("平台收益");
				ppSa.settGroupBuildingId(ebuyOrderRevenue.getGbId());
				
				saList.add(ppSa);
				saEbuyList.add(ppSaEbuy);
			}
			
			//生成店主的收益，总的收益扣除所有其它的收益则为店的收益
//			if(ebuyProfit >= 0) {
				revenueSignalAmount.setAmount(ebuyProfit);
//				revenueSignalAmount.setAmountUsrReal(ebuyProfit - revenueSignalAmount.getAmountPtbt());
				
				saList.add(revenueSignalAmount);
				saEbuyList.add(revenueSignalAmountEbuy);
//			} else {
//				revenueSignalAmount.setAmount(0.0);
//				revenueSignalAmount.setAmountUsrReal(0.0);
//				revenueSignalAmount.setAmountPtbt(0.0);
//				
//				saList.add(revenueSignalAmount);
//				saEbuyList.add(revenueSignalAmountEbuy);
//			}
			
			for(RevenueSignalAmountEbuy saEbuy : saEbuyList) {
				if((totalAmout - ebuyProfit) > 0) {
					saEbuy.setAmoutDeduct(totalAmout - ebuyProfit); //平台+物业+代理总收益
				} else {
					saEbuy.setAmoutDeduct(0.0);
				}
			}
			
		}
	}
	
	public void setRevenueService(IRevenueService revenueService) {
		this.revenueService = revenueService;
	}

	public void setRevenueDredgeService(RevenueDredgeService revenueDredgeService) {
		this.revenueDredgeService = revenueDredgeService;
	}

}
