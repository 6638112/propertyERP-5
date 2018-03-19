package com.cnfantasia.server.ms.revenue.task;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.revenueApplyPush.service.RevenueApplyPushService;
import com.cnfantasia.server.api.revenueApplyPush.util.EASPushUtils;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.RevenueApplyEntity;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RevenueApplyPush2EASTask implements ISynTask {

    @Resource
    private IUuidManager uuidManager;
    
    @Resource
    private RevenueApplyPushService revenueApplyPushService;
    
    private static Log logger = LogFactory.getLog(EASPushUtils.class);
    
    /**
     * 合并提款单任务
     */
    public int mergeTask() {
    	return revenueApplyPushService.mergeRevenueApply();
    }

    @Override
    public Integer execTask() {
    	int is_enable_EAS_Push = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.Is_enable_EAS_Push, 0);
    	if(is_enable_EAS_Push == 0)
    		return 0;
    	
    	HashMap<String, Object> paramMap = new HashMap<String, Object>();
    	
    	String goleType = "goalType";
    	String miniRoleType = "miniRoleType";
    	
    	List<RevenueApplyEntity> raList = null;
    	//物业公司： 物业实收 + 停车代收费用 + 物业宝抵扣费用 + 停车宝抵扣费用  + 物业其它代收费用(实收）
    	try{
	    	paramMap.put(miniRoleType, RevenueDict.MiniRoleType.PropertyCompany);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.PropertyRealPayAmout);
	    	raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.CarAmount);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.FinanceDeduAmount);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.CarDeduAmount);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.PropertyOtherFee);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	for(RevenueApplyEntity ra: raList){
	    		if(ra.getGoalType().intValue() == RevenueDict.RevenueProject.PropertyOtherFee){
	    			ra.setAmountPtbt(0.0);//其它代收的补贴还不能生成报销单，因为下面还要与别的报销单合并，故将其设置为0
	    			if(ra.getEasBillNumbers()!=null && ra.getEasBillNumbers().contains("FK-")){//已推付款单
	    				ra.setAmountUsrReal(0.0);
	    			}
	    		}
	    	}
	    	EASPushUtils.pushBill2EAS_MultiplyToOneBill(raList);
    	}
    	catch (Exception e) {
    		logger.info(e.getMessage(), e);
		}
    	
    	//物业公司： 物业补贴 + 停车费补贴 + 物业其它代收费用  OK
    	try {
	    	paramMap.put(miniRoleType, RevenueDict.MiniRoleType.PropertyCompany);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.PropertySubsidyAmout);
	    	raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.CarAmountBt);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.PropertyOtherFee);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	for(RevenueApplyEntity ra: raList){
	    		if(ra.getGoalType().intValue() == RevenueDict.RevenueProject.PropertyOtherFee){
	    			ra.setApplyNo(ra.getApplyNo()+"BT");
	    			ra.setAmountUsrReal(0.0); //其它地方的付款单已合并，故将其设置为0
	    			if(ra.getEasBillNumbers()!=null && ra.getEasBillNumbers().contains("FHYN_LLL")){//已推报销单
	    				ra.setAmountPtbt(0.0);
	    			}
	    		}
	    	}
	    	EASPushUtils.pushBill2EAS_MultiplyToOneBill(raList);
    	}
    	catch (Exception e) {
    		logger.info(e.getMessage(), e);
		}
    	
    	
    	//推荐人： 维修费用，1张报销单
    	try{
	    	paramMap.put(miniRoleType, RevenueDict.MiniRoleType.Recommender);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.ServiceOrder);
	    	raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
	    	EASPushUtils.pushBill2EAS_OneByOneBill(raList);
    	}
    	catch (Exception e) {
    		logger.info(e.getMessage(), e);
		}
    	
    	try{
	    	//物业公司： 维修师傅 + 超市收益 + 物业宝佣金 + 停车宝佣金， 合并1张报销单，多个附件  OK
	    	paramMap.put(miniRoleType, RevenueDict.MiniRoleType.PropertyCompany);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.ServiceOrder);
	    	raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.MarketAmout);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.WuyebaoAmount);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.CarFinanceBaoAmout);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));

	    	EASPushUtils.pushBill2EAS_MultiplyToOneBill(raList);
    	}
    	catch (Exception e) {
    		logger.info(e.getMessage(), e);
		}
    	
    	// -- 物业管理处 start
    	// 物业实收: 物业实收 + 停车代收费用 + 物业宝抵扣费用 + 停车宝抵扣费用   + 物业其它代收费用(实收）
    	try{
	    	paramMap.put(miniRoleType, RevenueDict.MiniRoleType.PCManagement);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.PropertyRealPayAmout);
	    	raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.CarAmount);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.FinanceDeduAmount);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.CarDeduAmount);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.PropertyOtherFee);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	for(RevenueApplyEntity ra: raList){
	    		if(ra.getGoalType().intValue() == RevenueDict.RevenueProject.PropertyOtherFee){
	    			ra.setAmountPtbt(0.0);//其它代收的补贴还不能生成报销单，因为下面还要与别的报销单合并，故将其设置为0
	    			if(ra.getEasBillNumbers()!=null && ra.getEasBillNumbers().contains("FK-")){//已推付款单
	    				ra.setAmountUsrReal(0.0);
	    			}
	    		}
	    	}
	    	EASPushUtils.pushBill2EAS_MultiplyToOneBill(raList);
    	}
    	catch (Exception e) {
    		logger.info(e.getMessage(), e);
		}
    	
    	//管理处： 物业补贴 + 停车费补贴 +  物业其它代收费用(补贴）  OK
    	try {
	    	paramMap.put(miniRoleType, RevenueDict.MiniRoleType.PCManagement);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.PropertySubsidyAmout);
	    	raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.CarAmountBt);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.PropertyOtherFee);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	for(RevenueApplyEntity ra: raList){
	    		if(ra.getGoalType().intValue() == RevenueDict.RevenueProject.PropertyOtherFee){
	    			ra.setApplyNo(ra.getApplyNo()+"BT");
	    			ra.setAmountUsrReal(0.0); //其它地方的付款单已合并，故将其设置为0
	    			if(ra.getEasBillNumbers()!=null && ra.getEasBillNumbers().contains("FHYN_LLL")){//已推报销单
	    				ra.setAmountPtbt(0.0);
	    			}
	    		}
	    	}
	    	EASPushUtils.pushBill2EAS_MultiplyToOneBill(raList);
    	}
    	catch (Exception e) {
    		logger.info(e.getMessage(), e);
		}
    	
    	try{
    		//物业： 维修师傅 + 超市收益 + 物业宝佣金 + 停车宝佣金， 合并1张报销单，多个附件  OK
    		paramMap.put(miniRoleType, RevenueDict.MiniRoleType.PCManagement);
    		paramMap.put(goleType, RevenueDict.RevenueProject.ServiceOrder);
    		raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
    		paramMap.put(goleType, RevenueDict.RevenueProject.MarketAmout);
    		raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
    		paramMap.put(goleType, RevenueDict.RevenueProject.WuyebaoAmount);
    		raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
    		paramMap.put(goleType, RevenueDict.RevenueProject.CarFinanceBaoAmout);
    		raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
    		EASPushUtils.pushBill2EAS_MultiplyToOneBill(raList);
    	}
    	catch (Exception e) {
    		logger.info(e.getMessage(), e);
    	}    	
    	// -- 物业管理处 end

    	try{
	    	//代理: 维修师傅 + 超市收益 + 物业宝佣金 + 停车宝佣金，1张报销单 OK
	    	paramMap.put(miniRoleType, RevenueDict.MiniRoleType.PropertyAgent);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.ServiceOrder);
	    	raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.MarketAmout);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.WuyebaoAmount);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	paramMap.put(goleType, RevenueDict.RevenueProject.CarFinanceBaoAmout);
	    	raList.addAll(revenueApplyPushService.selectRevenueApplyList4Push(paramMap));
	    	EASPushUtils.pushBill2EAS_MultiplyToOneBill(raList);
    	}
    	catch (Exception e) {
    		logger.info(e.getMessage(), e);
		}
    	
    	try{
	    	//师傅： 维修费用：2张报销单，1张实收，另1张为补贴 OK
	    	paramMap.put(miniRoleType, RevenueDict.MiniRoleType.RepairMaster);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.ServiceOrder);
	    	raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
	    	EASPushUtils.pushBill2EAS_OneToMultiplyBill(raList);
	    }
		catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
	    	    	
    	try{
	    	//店铺： 楼下店铺收益， 一份提款单2张报销单
	    	paramMap.put(miniRoleType, RevenueDict.MiniRoleType.DownstairStore);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.MarketAmout);
	    	raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
	    	EASPushUtils.pushBill2EAS_OneToMultiplyBill(raList);
    	}
    	catch (Exception e) {
    		logger.info(e.getMessage(), e);
		}
    	
    	try{
	    	//购销供应商结算： 楼下店铺收益， 一份提款单2张报销单
	    	paramMap.put(miniRoleType, RevenueDict.MiniRoleType.DirectPurchase);
	    	paramMap.put(goleType, RevenueDict.RevenueProject.DirectPurchase);
	    	raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
	    	EASPushUtils.pushBill2EAS_OneToMultiplyBill(raList);
    	}
    	catch (Exception e) {
    		logger.info(e.getMessage(), e);
		}

		try{
			//生活缴费： 没有补贴， 多次提现合并为1张付款单
			paramMap.put(miniRoleType, RevenueDict.MiniRoleType.LivingFeePay);
			paramMap.put(goleType, RevenueDict.RevenueProject.LivingPayAmount);
			raList = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
			EASPushUtils.pushBill2EAS_MultiplyToOneBill(raList);
		}
		catch (Exception e) {
			logger.info(e.getMessage(), e);
		}

		return 0;
    }
}
