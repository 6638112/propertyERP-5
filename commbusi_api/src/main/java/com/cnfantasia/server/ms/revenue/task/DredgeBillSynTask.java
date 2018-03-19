package com.cnfantasia.server.ms.revenue.task;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.entity.DredgeWorkerRevenueConfig;
import com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.service.IDredgeWorkerRevenueConfigBaseService;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity.RevenueSignalAmountEbuy;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;
import com.cnfantasia.server.ms.revenue.constant.RevenueConstant;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.DredgeBillRevenue;
import com.cnfantasia.server.ms.revenue.entity.DredgeBillWithDetail;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.service.RevenueDredgeService;
import com.cnfantasia.server.ms.revenue.util.RevenueConfigUtil;

/**
 * 师傅相关收益实收定时任务
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       yewj             1.0             1.0 Version
 */
@Repository
public class DredgeBillSynTask implements ISynTask{
	
	private Log logger = LogFactory.getLog(getClass());
	
	private RevenueDredgeService revenueDredgeService;
	
	private IUserBaseService userBaseService;
	
	@Override
	public Integer execTask() {
		int size = 0;
		logger.debug("DredgeBillSynTask start:" + DateUtils.getCurrentDate());
		
		//避免极端情况下大量数据一次性处理，此处只会循环每次处理100条，在某次少于50(或者少于100)条即已经处理完成。
		while(true) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Date currentDate = new Date();
			paramMap.put("currentDate", currentDate);
			List<DredgeBillRevenue> dredgeRevenueList = revenueDredgeService.updateAndGetDredgeRevenueList(paramMap);
			
			int saveSize = saveDredgeRevenueList(dredgeRevenueList);
			size += saveSize;
			
			if(saveSize < 50) {
				break;
			}
		}
		
		logger.debug("DredgeBillSynTask end:" + DateUtils.getCurrentDate() + ";size:" + size);
		return size;
	}
	
	private int saveDredgeRevenueList(List<DredgeBillRevenue> dredgeRevenueList) {
		int i = 0;
		for(DredgeBillRevenue dredgeBillRevenue : dredgeRevenueList) {
			try {
				List<RevenueSignalAmount> saList = new ArrayList<RevenueSignalAmount>();
				List<RevenueSignalAmountEbuy> saEbuyList = new ArrayList<RevenueSignalAmountEbuy>();
				
				construct(dredgeBillRevenue, saList, saEbuyList);
				revenueDredgeService.saveRevenueEbuy(saList, saEbuyList);
				i++;
			} catch(Exception e) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("dredgeBillId", dredgeBillRevenue.getDredgeBillWithDetail().getId());
				paramMap.put("revenueStatusTo", -1);
				revenueDredgeService.updateDredgeRevenueStatus(paramMap);
				logger.error("dredge-task failed dredgeId:" + dredgeBillRevenue.getDredgeBillWithDetail().getId());
				logger.error(e.getMessage(), e);
			}
		}
		return i;
	}
	
	private void construct(DredgeBillRevenue dredgeBillRevenue, List<RevenueSignalAmount> saList, List<RevenueSignalAmountEbuy> saEbuyList) {
		RevenueSignalAmount revenueSignalAmount = new RevenueSignalAmount();
		RevenueSignalAmountEbuy revenueSignalAmountEbuy = new RevenueSignalAmountEbuy();
		revenueSignalAmount.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
		revenueSignalAmountEbuy.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount_ebuy));
		revenueSignalAmountEbuy.settRevenueSignalAmountId(revenueSignalAmount.getId());
		
		DredgeBillWithDetail dredgeBillWithDetail = dredgeBillRevenue.getDredgeBillWithDetail();
		revenueSignalAmount.setGoalId(dredgeBillWithDetail.getId());
		revenueSignalAmount.setGoalRevTime(dredgeBillRevenue.getRevenueTm());
		revenueSignalAmount.setGoalRevTimeStr("");
//		revenueSignalAmount.setGoalRevTime(DateUtils.getCurrentDate());
		revenueSignalAmount.setGoalType(RevenueDict.RevenueProject.ServiceOrder);
		revenueSignalAmount.setGoalDetailType(dredgeBillWithDetail.getFeeType());
		revenueSignalAmount.setMiniRoleId(dredgeBillWithDetail.gettWorkerFId());
		revenueSignalAmount.setMiniRoleType(RevenueDict.MiniRoleType.RepairMaster);
//		revenueSignalAmount.setSrcMoney(BigDecimalUtil.div100(dredgeBillWithDetail.getPayAmount()).doubleValue());
		revenueSignalAmount.setSrcMoney(BigDecimalUtil.div100(dredgeBillWithDetail.getPayAmountDetail()).doubleValue());
		revenueSignalAmount.setMoneyTime(DateUtils.getCurrentDate());
		revenueSignalAmount.setRevApplyId(null); //t_revenue_apply.f_id
		revenueSignalAmount.setRevConfigId(BigInteger.ZERO);
		revenueSignalAmount.setRevConfigJson("");
		revenueSignalAmount.setTkStatus(RevenueDict.TkStatus.Undo);
//		revenueSignalAmount.setAmountPtbt(BigDecimalUtil.div100(dredgeBillRevenue.getDiscountMoney()).doubleValue()); //平台补贴额，暂时不补贴
//		revenueSignalAmount.setAmountUsrReal(BigDecimalUtil.div100(dredgeBillRevenue.getAmountUsrReal()).doubleValue());
//		revenueSignalAmount.setAmountUsrReal(0.0);
		revenueSignalAmount.setRoleName(dredgeBillRevenue.getDredgeUserName() == null ? "" :dredgeBillRevenue.getDredgeUserName()); //师傅名称
		revenueSignalAmount.setSys0AddTime(DateUtils.getCurrentDate());
		revenueSignalAmount.setSys0DelState(0);
		revenueSignalAmount.setPayFlowNo(dredgeBillRevenue.getFlowNo());
		revenueSignalAmount.setPayMethod(dredgeBillRevenue.getPayType());
		revenueSignalAmount.settPropertyManagementFId(dredgeBillRevenue.getPropertyManagementId());
		revenueSignalAmount.settGroupBuildingId(dredgeBillRevenue.getGbId());
		
//		revenueSignalAmountEbuy.setAmountDiscount(BigDecimalUtil.div100(dredgeBillRevenue.getDiscountMoney()).doubleValue());
//		revenueSignalAmountEbuy.setAmountOrderReal(BigDecimalUtil.div100(dredgeBillRevenue.getAmountUsrReal()).doubleValue());
		revenueSignalAmountEbuy.setPcName(dredgeBillRevenue.getPcName());
		revenueSignalAmountEbuy.settPcId(dredgeBillRevenue.getPcId());
		revenueSignalAmountEbuy.setAgentName(dredgeBillRevenue.getAgentName());
		revenueSignalAmountEbuy.settAgentId(dredgeBillRevenue.getAgentId());
		revenueSignalAmountEbuy.setGbName(dredgeBillRevenue.getGbName());
		revenueSignalAmountEbuy.setGroupBuildingName(dredgeBillRevenue.getGbName());
		revenueSignalAmountEbuy.settGroupBuildingId(dredgeBillRevenue.getGbId());
		revenueSignalAmountEbuy.setPayMethod(dredgeBillRevenue.getPayType());
		revenueSignalAmountEbuy.setPayTm(DateUtils.formatTime(dredgeBillRevenue.getPayTm()));
		revenueSignalAmountEbuy.setDredgeServiceName(dredgeBillRevenue.getDredgeServiceName());
		revenueSignalAmountEbuy.setHuaId(dredgeBillRevenue.getHuaId());
		
		double totalAmount = BigDecimalUtil.div100(dredgeBillWithDetail.getPayAmountDetail()).doubleValue();
		if(dredgeBillWithDetail.getIsIncludePlatformFee() == 0) {//未含平台费，不需要抽佣
			revenueSignalAmount.setAmountPtbt(0.0);
			revenueSignalAmount.setAmountUsrReal(totalAmount);
			revenueSignalAmountEbuy.setAmountDiscount(0.0);
			revenueSignalAmountEbuy.setAmountOrderReal(totalAmount);
			
			//未含平台费，不需要抽佣
			revenueSignalAmount.setAmount(totalAmount);
			saList.add(revenueSignalAmount);
			saEbuyList.add(revenueSignalAmountEbuy);
			
			return;//未含平台费，不需要抽佣，全分配给师傅，直接返回
		} else {
			revenueSignalAmount.setAmountPtbt(BigDecimalUtil.div100(dredgeBillRevenue.getDiscountMoney()).doubleValue());
			revenueSignalAmount.setAmountUsrReal(totalAmount - revenueSignalAmount.getAmountPtbt());
			revenueSignalAmountEbuy.setAmountDiscount(BigDecimalUtil.div100(dredgeBillRevenue.getDiscountMoney()).doubleValue());
			revenueSignalAmountEbuy.setAmountOrderReal(totalAmount - revenueSignalAmount.getAmountPtbt());
		}
		
		//根据物业公司的ID和收益的时间取得相应的配置，原则上这个配置只有一个。前端有作校验，当然出现难于预料的多个时也只能取其中一个
		RevenueConfig rc = null;
		if(dredgeBillRevenue.getPcId() == null) {
			dredgeBillRevenue.setPcId(RevenueConstant.DEFAULT_REVENUE_COMPANY_ID);
			dredgeBillRevenue.setPcName("解放区物业公司");
		}
		if(dredgeBillRevenue.getPcId() != null && dredgeBillRevenue.getDredgeBillWithDetail().getIsIncludePlatformFee() == 1) {//0人工费 1其它费 ; 是否包含平台费=={"0":"未含","1":"包含,即人工费"}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pcId", dredgeBillRevenue.getPcId());
			paramMap.put("goalRevTime", dredgeBillRevenue.getPayTm());
			rc = revenueDredgeService.selectRevenueConfig(paramMap);
		} else if(dredgeBillWithDetail.getIsIncludePlatformFee() == 0) {
//			revenueSignalAmount.setAmountPtbt(0.0); //平台补贴额，暂时不补贴
//			revenueSignalAmount.setAmountUsrReal(BigDecimalUtil.div100(dredgeBillWithDetail.getPayAmountDetail()).doubleValue());
		}
		
		DredgeWorkerRevenueConfig dwrc = getDredgeWorkerRevenueConfig(dredgeBillWithDetail);
		
		if(dwrc == null){//订单全生成师傅的收益
			//生成师傅的收益，总的收益扣除所有其它的收益则为师傅的收益
			if(totalAmount >= 0.01) {
				revenueSignalAmount.setAmount(totalAmount);
				saList.add(revenueSignalAmount);
				saEbuyList.add(revenueSignalAmountEbuy);
			} else {
				revenueSignalAmount.setAmount(0.0);
				saList.add(revenueSignalAmount);
				saEbuyList.add(revenueSignalAmountEbuy);
			}
		}else if(dwrc != null && rc == null){//师傅有收益配置，角色无收益配置，先分给师傅，剩下的分给平台
			Double dredgeUserProfit = totalAmount * (1 - dwrc.getPlatformValue() / 100);//先分给师傅
			Double platformProfit = totalAmount - dredgeUserProfit;// 平台收益=订单金额-师傅收益
			Double recommenderProfit = 0.0;// 推荐人收益

			if(dredgeUserProfit >= 0.01) {
				revenueSignalAmount.setAmount(dredgeUserProfit);
				saList.add(revenueSignalAmount);
				saEbuyList.add(revenueSignalAmountEbuy);
			} else {
				revenueSignalAmount.setAmount(0.0);
				saList.add(revenueSignalAmount);
				saEbuyList.add(revenueSignalAmountEbuy);
			}

			//推荐人收益： 有推荐人且无角色收益配置，也要分2块给奖励给推荐人
			if(StringUtils.isNotEmpty(dredgeBillWithDetail.getReferrerMobile())) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("mobile", dredgeBillWithDetail.getReferrerMobile());
				List<User> userList = userBaseService.getUserByCondition(paramMap);
				if(userList!=null && userList.size() > 0) {
					recommenderProfit = Math.min(2.0, platformProfit);
					platformProfit = platformProfit - recommenderProfit;
					if(recommenderProfit != null && recommenderProfit >= 0.01) {
						RevenueSignalAmount ppSa = new EbuyRevenueSignalAmount();
						RevenueSignalAmountEbuy ppSaEbuy = new RevenueSignalAmountEbuy();
						BeanUtils.copyProperties(revenueSignalAmount, ppSa);
						BeanUtils.copyProperties(revenueSignalAmountEbuy, ppSaEbuy);

						ppSa.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
						ppSaEbuy.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount_ebuy));
						ppSaEbuy.settRevenueSignalAmountId(ppSa.getId());

						ppSa.setAmount(recommenderProfit);
						ppSa.setAmountUsrReal(0.0);
						ppSa.setAmountPtbt(recommenderProfit);
						ppSa.setMiniRoleType(RevenueDict.MiniRoleType.Recommender);
						ppSa.setMiniRoleId(userList.get(0).getId());
						ppSa.setRoleName(dredgeBillRevenue.getDredgeBillWithDetail().getReferrerMobile());
						ppSa.setGoalRevTime(DateUtils.getCurrentDate());
						ppSa.settGroupBuildingId(dredgeBillRevenue.getGbId());

						saList.add(ppSa);
						saEbuyList.add(ppSaEbuy);
					}
				}
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
				ppSa.setAmountUsrReal(0.0);
				ppSa.setAmountPtbt(platformProfit);
				ppSa.setMiniRoleType(RevenueDict.MiniRoleType.SysAdmin);
				ppSa.setMiniRoleId(RevenueConstant.PLATFORM);
				ppSa.setRoleName("平台收益");
				ppSa.setGoalRevTime(DateUtils.getCurrentDate());
				ppSa.settGroupBuildingId(dredgeBillRevenue.getGbId());
				
				saList.add(ppSa);
				saEbuyList.add(ppSaEbuy);
			}
		}else if(dwrc != null && rc!=null) {
			//按新的配置规则计算，师傅收益=订单金额*（1-平台抽佣比例）
			Double dredgeUserProfit = totalAmount * (1 - dwrc.getPlatformValue() / 100);//先分给师傅
			
			//剩下的各个角色去分配
			double leftAmount = totalAmount - dredgeUserProfit;

			Double recommenderProfit = RevenueConfigUtil.getRecommenderProfit2(rc, leftAmount);
			Double propertyProfit = RevenueConfigUtil.getPropertyProfit2(rc, leftAmount);
			Double agentProfit = RevenueConfigUtil.getAgentProfit2(rc, leftAmount);
			Double platformProfit = leftAmount - propertyProfit - agentProfit - recommenderProfit;
			
			revenueSignalAmount.setRevConfigId(rc.getId());
			revenueSignalAmount.setRevConfigJson(JSON.toJSONString(rc));

			//推荐人收益
			if(StringUtils.isNotEmpty(dredgeBillWithDetail.getReferrerMobile())) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("mobile", dredgeBillWithDetail.getReferrerMobile());
				List<User> userList = userBaseService.getUserByCondition(paramMap);
				if(userList!=null && userList.size() > 0) {
					if (recommenderProfit <= 0) {//洗车活动需要固定给推荐人2块钱, 但是按比例生成推荐人收益》0，那就按实际来存
						recommenderProfit = Math.min(2.0, leftAmount);

						{//由于固定给推荐人2块，所以其它角色要重新分配收益 added by wenfq 2017-09-15
							leftAmount = leftAmount - recommenderProfit;
							propertyProfit = RevenueConfigUtil.getPropertyProfit2(rc, leftAmount);
							agentProfit = RevenueConfigUtil.getAgentProfit2(rc, leftAmount);
							platformProfit = leftAmount - propertyProfit - agentProfit;
						}
					}

					if(recommenderProfit != null && recommenderProfit >= 0.01) {
						RevenueSignalAmount ppSa = new EbuyRevenueSignalAmount();
						RevenueSignalAmountEbuy ppSaEbuy = new RevenueSignalAmountEbuy();
						BeanUtils.copyProperties(revenueSignalAmount, ppSa);
						BeanUtils.copyProperties(revenueSignalAmountEbuy, ppSaEbuy);

						ppSa.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
						ppSaEbuy.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount_ebuy));
						ppSaEbuy.settRevenueSignalAmountId(ppSa.getId());

						ppSa.setAmount(recommenderProfit);
						ppSa.setAmountUsrReal(0.0);
						ppSa.setAmountPtbt(recommenderProfit);
						ppSa.setMiniRoleType(RevenueDict.MiniRoleType.Recommender);
						ppSa.setMiniRoleId(userList.get(0).getId());
						ppSa.setRoleName(dredgeBillRevenue.getDredgeBillWithDetail().getReferrerMobile());
						ppSa.setGoalRevTime(DateUtils.getCurrentDate());
						ppSa.settGroupBuildingId(dredgeBillRevenue.getGbId());

						saList.add(ppSa);
						saEbuyList.add(ppSaEbuy);
					}
				}else{//没有推荐人，推荐人收益归结到平台上
					platformProfit += recommenderProfit;
				}
			}else{//没有推荐人，推荐人收益归结到平台上
				platformProfit += recommenderProfit;
			}
			
			//生成物业公司的收益
			if(propertyProfit != null && propertyProfit >= 0.01 && dredgeBillRevenue.getPcId() != null) {
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
				ppSa.setMiniRoleId(dredgeBillRevenue.getPcId());
				ppSa.settGroupBuildingId(dredgeBillRevenue.getGbId());
				ppSa.setRoleName(dredgeBillRevenue.getPcName());
				ppSa.setGoalRevTime(DateUtils.getCurrentDate());
				
				saList.add(ppSa);
				saEbuyList.add(ppSaEbuy);
			}else{
				platformProfit += propertyProfit;
			}
			
			//生成代理商的收益
			if(agentProfit != null && agentProfit >= 0.01 && dredgeBillRevenue.getAgentId() != null ) {
				RevenueSignalAmount ppSa = new EbuyRevenueSignalAmount();
				RevenueSignalAmountEbuy ppSaEbuy = new RevenueSignalAmountEbuy();
				BeanUtils.copyProperties(revenueSignalAmount, ppSa);
				BeanUtils.copyProperties(revenueSignalAmountEbuy, ppSaEbuy);
				
				ppSa.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount));
				ppSaEbuy.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount_ebuy));
				ppSaEbuy.settRevenueSignalAmountId(ppSa.getId());
				
				ppSa.setAmount(agentProfit);
//				ppSa.setAmountPtbt(agentProfit);
//				ppSa.setAmountUsrReal(agentProfit);
				ppSa.setAmountUsrReal(0.0);
				ppSa.setAmountPtbt(agentProfit);
				ppSa.setMiniRoleType(RevenueDict.MiniRoleType.PropertyAgent);
				ppSa.setMiniRoleId(dredgeBillRevenue.getAgentId());
				ppSa.setRoleName(dredgeBillRevenue.getAgentName());
				ppSa.setGoalRevTime(DateUtils.getCurrentDate());
				ppSa.settGroupBuildingId(dredgeBillRevenue.getGbId());
				
				saList.add(ppSa);
				saEbuyList.add(ppSaEbuy);
			}else{
				platformProfit += agentProfit;
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
				ppSa.setGoalRevTime(DateUtils.getCurrentDate());
				ppSa.settGroupBuildingId(dredgeBillRevenue.getGbId());
				
				saList.add(ppSa);
				saEbuyList.add(ppSaEbuy);
			}
			
			//生成师傅的收益，按新规则来，师傅收益=订单金额*（1-平台抽佣比例）
			if(dredgeUserProfit >= 0.01) {
				revenueSignalAmount.setAmount(dredgeUserProfit);
				saList.add(revenueSignalAmount);
				saEbuyList.add(revenueSignalAmountEbuy);
			} else {
				revenueSignalAmount.setAmount(0.0);
				saList.add(revenueSignalAmount);
				saEbuyList.add(revenueSignalAmountEbuy);
			}
		}
	}

	/**
	 * 获取师傅的收益配置
	 * @param dredgeBillWithDetail
	 * @return
	 */
	private DredgeWorkerRevenueConfig getDredgeWorkerRevenueConfig(DredgeBillWithDetail dredgeBillWithDetail) {
		DredgeWorkerRevenueConfig dwrcQry = new DredgeWorkerRevenueConfig();
		dwrcQry.setActiveStatus(1);//开启
		dwrcQry.settUserFId(dredgeBillWithDetail.gettWorkerFId());
		IDredgeWorkerRevenueConfigBaseService dredgeWorkerRevenueConfigBaseService = (IDredgeWorkerRevenueConfigBaseService) CnfantasiaCommbusiUtil.getBeanManager("dredgeWorkerRevenueConfigBaseService");
		List<DredgeWorkerRevenueConfig> dwrcList = dredgeWorkerRevenueConfigBaseService.getDredgeWorkerRevenueConfigByCondition(MapConverter.convertBean(dwrcQry));
		if(dwrcList.size()==1){
			return dwrcList.get(0);
		}else{
			return null;
		}
	}

	public void setRevenueDredgeService(RevenueDredgeService revenueDredgeService) {
		this.revenueDredgeService = revenueDredgeService;
	}

	public void setUserBaseService(IUserBaseService userBaseService) {
		this.userBaseService = userBaseService;
	}

}
