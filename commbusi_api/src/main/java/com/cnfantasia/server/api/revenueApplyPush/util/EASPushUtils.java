package com.cnfantasia.server.api.revenueApplyPush.util;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.parser.EASBizAccountPushParamParser;
import com.cnfantasia.server.api.commSysPara.parser.EASLoginAccountConfigParamParser;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.revenueApplyPush.bean.AttachmentEntity;
import com.cnfantasia.server.api.revenueApplyPush.bean.BizAccountEntity;
import com.cnfantasia.server.api.revenueApplyPush.bean.BizAccountEntryEntity;
import com.cnfantasia.server.api.revenueApplyPush.bean.EASBizAccountEntryParam;
import com.cnfantasia.server.api.revenueApplyPush.bean.EASBizAccountParam;
import com.cnfantasia.server.api.revenueApplyPush.bean.EASLoginAccountConfigParam;
import com.cnfantasia.server.api.revenueApplyPush.bean.EASPaymentEntryParam;
import com.cnfantasia.server.api.revenueApplyPush.bean.PaymentEntity;
import com.cnfantasia.server.api.revenueApplyPush.bean.PaymentEntryEntity;
import com.cnfantasia.server.api.revenueApplyPush.bean.ResultInfo;
import com.cnfantasia.server.api.revenueApplyPush.service.RevenueApplyPushService;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.easPushAccount.entity.EasPushAccount;
import com.cnfantasia.server.domainbase.easPushAccount.service.EasPushAccountBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.dao.GroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.propertyCompany.dao.PropertyCompanyBaseDao;
import com.cnfantasia.server.domainbase.propertyManagement.dao.PropertyManagementBaseDao;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.RevenueApplyEntity;
import com.cnfantasia.server.ms.revenue.entity.RevenueBatchParam;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;
import com.kingdee.eas.client.WSContext;
import com.kingdee.eas.login.EASLoginProxy;
import com.kingdee.eas.login.EASLoginProxyServiceLocator;
import com.kingdee.eas.payBill.WSPayBillServerInterFacadeSrvProxy;
import com.kingdee.eas.payBill.WSPayBillServerInterFacadeSrvProxyServiceLocator;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.FileNameMap;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

/**
 * EAS报销单和付款单推送
 * @author wenfq 2016-06-12
 *
 */
public class EASPushUtils {
	/*
		 角色	业务类型		预算编号  业务类别    费用类型
		物业2	物业费实收5	LLL.02	AL.01	Al.01.01
		物业2	物业费补贴15	LLL.03	AL.06	AL06.14
		物业2	停车费6	LLL.02	AL.01	Al.01.01
		物业2	超市收益4	LLL.0504	AL.06	AL06.03
		物业2	物业宝佣金3	LLL.0504	AL.06	AL06.03
		物业2	物业宝抵扣8	LLL.02	AL.01	Al.01.01
		物业2	停车宝佣金9	LLL.0504	AL.06	AL06.03
		物业2	停车宝抵扣10	LLL.02	AL.01	Al.01.01
		物业2	维修收益2	LLL.0504	AL.06	AL06.03
		代理3	超市收益4	LLL.0504	AL.06	AL06.03
		代理3	物业宝佣金3	LLL.0504	AL.06	AL06.03
		代理3	停车宝佣金9	LLL.0504	AL.06	AL06.03
		代理3	维修收益2	LLL.0504	AL.06	AL06.03
		师傅6	维修收益（实收）2.1	LLL.02	AL.01	Al.01.03
		师傅6	维修收益（补贴）2.2	LLL.04	AL.06	AL06.13
		店主5	超市收益（实收）4.1	LLL.02	AL.01	Al.01.02
		店主5	超市收益（补贴）4.2	LLL.04	AL.06	AL06.13
		推荐人7	维修收益（实收）2.1	LLL.02	AL.01	Al.01.03
		推荐人7	维修收益（补贴）2.2	LLL.04	AL.06	AL06.13
	 */
	private static final String[][] budgetConfig = {
									{"2","物业费实收", "5", "LLL.02", "AL.01", "Al.01.01"},
									{"2","物业费补贴","15", "LLL.03", "AL.06", "AL06.14"},
									{"2","其它代收费用(实收)","7.1", "LLL.02", "AL.01", "Al.01.01"},
									{"2","其它代收费用(补贴)","7.2", "LLL.03", "AL.06", "AL06.14"},
									{"2",  "停车费实收","6", "LLL.02", "AL.01", "Al.01.04"},
									{"2",  "停车费补贴","17", "LLL.03", "AL.06", "AL06.14"},
									{"2", "超市收益","4", "LLL.0504", "AL.06", "AL06.03"},
									{"2","物业宝佣金","3", "LLL.32", "AL.07", "AL07.01"},
									{"2",  "物业宝抵扣","8", "LLL.02", "AL.01", "Al.01.01"},
									{"2", "停车宝佣金","9", "LLL.0504", "AL.06", "AL06.03"},
									{"2", "停车宝抵扣","10", "LLL.02", "AL.01", "Al.01.01"},
									{"2","维修收益", "2", "LLL.32", "AL.07", "AL07.01"},
									
									{"13","物业费实收", "5", "LLL.02", "AL.01", "Al.01.01"},  //13 是管理处
									{"13","物业费补贴","15", "LLL.03", "AL.06", "AL06.14"},
									{"13","其它代收费用(实收)","7.1", "LLL.02", "AL.01", "Al.01.01"},
									{"13","其它代收费用(补贴)","7.2", "LLL.03", "AL.06", "AL06.14"},
									{"13",  "停车费实收","6", "LLL.02", "AL.01", "Al.01.04"},
									{"13",  "停车费补贴","17", "LLL.03", "AL.06", "AL06.14"},
									{"13", "超市收益","4", "LLL.0504", "AL.06", "AL06.03"},
									{"13","物业宝佣金","3", "LLL.32", "AL.07", "AL07.01"},
									{"13",  "物业宝抵扣","8", "LLL.02", "AL.01", "Al.01.01"},
									{"13", "停车宝佣金","9", "LLL.0504", "AL.06", "AL06.03"},
									{"13", "停车宝抵扣","10", "LLL.02", "AL.01", "Al.01.01"},
									{"13","维修收益", "2", "LLL.32", "AL.07", "AL07.01"},
									
									{"3", "超市收益","4", "LLL.0504", "AL.06", "AL06.03"},
									{"3", "物业宝佣金","3", "LLL.32", "AL.07", "AL07.01"},
									{"3", "停车宝佣金","9", "LLL.0504", "AL.06", "AL06.03"},
									{"3", "维修收益","2", "LLL.32", "AL.07", "AL07.01"},
									{"6", "维修费用（实收）","2.1", "LLL.02", "AL.01", "Al.01.03"},
									{"6", "维修费用（补贴）","2.2", "LLL.04", "AL.06", "AL06.13"},
									{"5", "楼下店费用（实收）","4.1", "LLL.02", "AL.01", "Al.01.02"},
									{"5", "楼下店费用（补贴）","4.2", "LLL.04", "AL.06", "AL06.13"},
									
									{"12", "超市供应商（实收）","16.1", "LLL.02", "AL.01", "Al.01.02"},
									{"12", "超市供应商（补贴）","16.2", "LLL.04", "AL.06", "AL06.13"},
	
									//{"7", "维修收益（实收）","2.1", "LLL.02", "AL.01", "Al.01.03"}, 没有实收，只有补贴
									{"7", "维修收益","2", "LLL.0504", "AL.06", "AL06.03"}};
	
	/**
	 * 付款单预算配置
	 */
	private static final String[][] paymentBudgetConfig = {
			/**
			 *  所有流出预算项目
			 	lll03.01	物业费代收费用支付	
				lll03.02	超市代收费用支付	
				lll03.03	代理商保证金退款	
				lll03.04	其他现金流出	
			 */
			
			/** 所有费用类型
			 Al.01.01	代收物业费	
			 Al.01.02	代收超市费用	
			 Al.01.03	代收师傅端费用	
			 Al.01.04	停车费实收	
			 */
			
			// 业务类型	费用类型	流出预算项目
			{"物业费实收", "5", "Al.01.01", "lll03.01"},
			{"生活缴费代收", "18", "Al.01.01", "lll03.01"},
			{"其它代收费用", "7", "Al.01.01", "lll03.01"},
			{"物业宝抵扣", "8", "Al.01.01", "lll03.01"},
			
			{"代收维修费用", "2", "Al.01.03", "lll03.04"},
			
			{"停车费实收", "6", "Al.01.04", "lll03.04"},
			{"停车宝抵扣", "10", "Al.01.04", "lll03.04"},
			
			{"超市费用（实收）","4", "Al.01.02", "lll03.02"},
			{"超市供应商（实收）","16", "Al.01.02", "lll03.02"},

			{"其它代收","-1", "Al.01.01", "lll03.04"},//默认配置：物业代收，流出预算科目 
		};
	
	/**
	 * 制单人配置：
	 * 	师傅：yuanx
		店铺：liliang
		物业&推荐人：wangchong
		停车费：wangchong //也用王冲
	 */
	private static List<EasPushAccount> easPushAccountList = null;
	
	private static Log logger = LogFactory.getLog(EASPushUtils.class);
	
	/**
	 * 推送到EAS，一张提款单生成一份报销单
	 * @param raList
	 */
	public static ResultInfo pushBill2EAS_OneByOneBill(List<RevenueApplyEntity> raList){
		List<BizAccountEntity> bizAccountEntityList = getBizAccountList_OneByOne(raList);
//		if(bizAccountEntityList.size() == 0)
//			return new ResultInfo("0001", "没有符合条件的数据");
				
		List<PaymentEntity> paymentEntityList = getPaymentList_OneByOne(raList);
		
		if(bizAccountEntityList.size() == 0 && paymentEntityList.size() == 0){
			return new  ResultInfo("0001", "没有符合条件的数据");
		}
		
		ResultInfo	resultInfo = null;
		
		// 1 推报销单
		for(BizAccountEntity bae: bizAccountEntityList){//需要逐个推送，因为有可能每个报销单的制单人都不相同，登录人也不同
			resultInfo = pushOneBizAccount(resultInfo, bae);
		}
		
		// 2 推付款单
		for(PaymentEntity pe:paymentEntityList){
			resultInfo = pushOnePayment(resultInfo, pe);
		}
		
		return resultInfo;
	}
	
	/**
	 * 推送到EAS，多张提款单生成一份报销单
	 * @param raList
	 */
	public static ResultInfo pushBill2EAS_MultiplyToOneBill(List<RevenueApplyEntity> raList){
		List<BizAccountEntity> bizAccountEntityList = getBizAccountList_MultiplyToOne(raList);
		List<PaymentEntity> paymentEntityList = getPaymentList_MultiplyToOne(raList);
		
		if (bizAccountEntityList.size() == 0 && paymentEntityList.size() == 0)
			return new ResultInfo("0001", "没有符合条件的数据");
		
		ResultInfo	resultInfo = null;
		
		// 1 推 报销单
		for(BizAccountEntity bae: bizAccountEntityList){//需要逐个推送，因为有可能每个报销单的制单人都不相同，登录人也不同
			resultInfo = pushOneBizAccount(resultInfo, bae);
		}
		
		// 2 推 付款单
		for(PaymentEntity pe:paymentEntityList){
			resultInfo = pushOnePayment(resultInfo, pe);
		}
		
		return resultInfo;
	}

	/**
	 * 推送到EAS，一张提款单生成2份报销单
	 * @param raList
	 */
	public static ResultInfo pushBill2EAS_OneToMultiplyBill(List<RevenueApplyEntity> raList){
		List<BizAccountEntity> bizAccountEntityList = getBizAccountList_OneToMultiply(raList);
		List<PaymentEntity> paymentEntityList = getPaymentEntityList_OneToMultiply(raList);
		
		if(bizAccountEntityList.size() == 0 && paymentEntityList.size() == 0)
			return new ResultInfo("0001", "没有符合条件的数据");
		
		List<BizAccountEntity> bizAccountEntityList_toBePush = new ArrayList<BizAccountEntity>();
		ResultInfo resultInfo = null;
		
		for(int i= 0; i< bizAccountEntityList.size(); i++){
			BizAccountEntity bizAccountEntity = bizAccountEntityList.get(i);
			bizAccountEntityList_toBePush.add(bizAccountEntity);
			if (i == bizAccountEntityList.size() - 1 
					||!bizAccountEntityList.get(i+1).getEasUserAccount().equals(bizAccountEntity.getEasUserAccount())){
				//要开始推了，先登录EAS
				try{
					WSContext context = easLogin(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.EAS_Server_URL), bizAccountEntity.getEasUserAccount(), bizAccountEntity.getEasUserPassword());
					if (null == context || context.getSessionId().length() == 0) {
						logger.info("EAS登录不成功");
					} else {
						logger.info("登录成功。ID是" + context.getSessionId().toString());
						
						resultInfo = pushData(JSON.toJSONString(bizAccountEntityList_toBePush), "");
						
						//回填推送记录
						RevenueApplyPushService revenueApplyPushService = (RevenueApplyPushService) CnfantasiaCommbusiUtil.getBeanManager("revenueApplyPushService");
						revenueApplyPushService.addPushRecordAfterPush(resultInfo, bizAccountEntity.getSrcBillNumber());
					}
				}catch (Exception e) {
					logger.error(e.getMessage(), e);
					resultInfo.setCode("0001");
					resultInfo.setMessage("推送过程异常");
				}
				bizAccountEntityList_toBePush.clear();
			}
			
		}
		
		List<PaymentEntity> paymentEntityEntityList_toBePush = new ArrayList<PaymentEntity>();
		for(int i = 0; i<paymentEntityList.size(); i++){
			PaymentEntity pe = paymentEntityList.get(i);
			paymentEntityEntityList_toBePush.add(pe);
			if (i == paymentEntityList.size() - 1 
					||!paymentEntityList.get(i+1).getEasUserAccount().equals(pe.getEasUserAccount())){
				//要开始推了，先登录EAS
				
				try{
					WSContext context = easLogin(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.EAS_Server_URL), pe.getEasUserAccount(), pe.getEasUserPassword());
					if (null == context || context.getSessionId().length() == 0) {
						logger.info("EAS登录不成功");
					} else {
						logger.info("登录成功。ID是" + context.getSessionId().toString());
						
						resultInfo = pushData("", JSON.toJSONString(paymentEntityEntityList_toBePush));
						
						//回填推送记录
						RevenueApplyPushService revenueApplyPushService = (RevenueApplyPushService) CnfantasiaCommbusiUtil.getBeanManager("revenueApplyPushService");
						revenueApplyPushService.addPushRecordAfterPush(resultInfo, pe.getSrcBillNumber());
					}
				}catch (Exception e) {
					logger.error(e.getMessage(), e);
					resultInfo.setCode("0001");
					resultInfo.setMessage("推送过程异常");
				}
				
				paymentEntityEntityList_toBePush.clear();
			}
		}
		
		return resultInfo;
	}
	
	/**
	 * 登录EAS 并推一个付款单
	 * @param resultInfo
	 * @param pe
	 * @return
	 */
	private static ResultInfo pushOnePayment(ResultInfo resultInfo, PaymentEntity pe) {
		try {
			WSContext context = easLogin(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.EAS_Server_URL), pe.getEasUserAccount(), pe.getEasUserPassword());
			if (null == context || context.getSessionId().length() == 0) {
				logger.info("EAS登录不成功");
			} else {
				logger.info("登录成功。ID是" + context.getSessionId().toString());
				List<PaymentEntity> peList_toBePush = new ArrayList<PaymentEntity>();
				peList_toBePush.add(pe);
				resultInfo = pushData("", JSON.toJSONString(peList_toBePush));
				
				//回填推送记录
				RevenueApplyPushService revenueApplyPushService = (RevenueApplyPushService) CnfantasiaCommbusiUtil.getBeanManager("revenueApplyPushService");
				revenueApplyPushService.addPushRecordAfterPush(resultInfo, pe.getSrcBillNumber());
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultInfo.setCode("0001");
			resultInfo.setMessage("推送过程异常");
		}
		return resultInfo;
	}

	/**
	 * 登录EAS， 并推一个报销单
	 * @param resultInfo
	 * @param bae
	 * @return
	 */
	private static ResultInfo pushOneBizAccount(ResultInfo resultInfo, BizAccountEntity bae) {
		try{
			WSContext context = easLogin(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.EAS_Server_URL), bae.getEasUserAccount(), bae.getEasUserPassword());
			if (null == context || context.getSessionId().length() == 0) {
				logger.info("EAS登录不成功");
			} else {
				logger.info("登录成功。ID是" + context.getSessionId().toString());
				
				List<BizAccountEntity> baeList_toBePush = new ArrayList<BizAccountEntity>();
				baeList_toBePush.add(bae);
				resultInfo = pushData(JSON.toJSONString((baeList_toBePush)), "");
				
				//回填推送记录
				RevenueApplyPushService revenueApplyPushService = (RevenueApplyPushService) CnfantasiaCommbusiUtil.getBeanManager("revenueApplyPushService");
				revenueApplyPushService.addPushRecordAfterPush(resultInfo, bae.getSrcBillNumber());
			}
		}catch  (Exception e) {
			logger.error(e.getMessage(), e);
			resultInfo.setCode("0001");
			resultInfo.setMessage("推送过程异常");
		}
		return resultInfo;
	}
	
	/**
	 * 获取报销单分录的预算编码，业务类别，费用类型，以及费用说明
	 * @param ra
	 * @return
	 */
	private static EASBizAccountEntryParam getBizAccountEntryParamByRevenueApply(RevenueApplyEntity ra, Boolean isUserRealPay){
		EASBizAccountEntryParam easBizAccountEntryParam = new EASBizAccountEntryParam();
		for(int i = 0; i < budgetConfig.length; i++){
			String goalTypeStr = ra.getGoalType().toString();
		
			if(budgetConfig[i][0].equals(ra.getMiniRoleType().toString()) && budgetConfig[i][2].equals(goalTypeStr)){
				easBizAccountEntryParam.setBudgetNumber(budgetConfig[i][3]);
				easBizAccountEntryParam.setOperationType(budgetConfig[i][4]);
				easBizAccountEntryParam.setExpenseType(budgetConfig[i][5]);
				easBizAccountEntryParam.setPurpose(budgetConfig[i][1]);
				break;
			}
			
			if (isUserRealPay != null) {//如果budgetConfig[i][0].equals(ra.getMiniRoleType().toString())没匹配到，尝试加后缀再来一次匹配
				goalTypeStr = isUserRealPay ? goalTypeStr + ".1" : goalTypeStr + ".2";
			}
			
			if(budgetConfig[i][0].equals(ra.getMiniRoleType().toString()) && budgetConfig[i][2].equals(goalTypeStr)){
				easBizAccountEntryParam.setBudgetNumber(budgetConfig[i][3]);
				easBizAccountEntryParam.setOperationType(budgetConfig[i][4]);
				easBizAccountEntryParam.setExpenseType(budgetConfig[i][5]);
				easBizAccountEntryParam.setPurpose(budgetConfig[i][1]);
				break;
			}
		}
		
		return easBizAccountEntryParam;
	}
	
	/**
	 * 获取 付款单 分录的预算编码，业务类别，费用类型，以及费用说明
	 * @param ra
	 * @return
	 */
	private static EASPaymentEntryParam getPaymentEntryParamByRevenueApply(RevenueApplyEntity ra){
		EASPaymentEntryParam easBizAccountEntryParam = new EASPaymentEntryParam();
		
		// {"物业费实收", "5", "Al.01.01", "lll03.01"},
		
		for(int i = 0; i < paymentBudgetConfig.length; i++){
			String goalTypeStr = ra.getGoalType().toString();
			
			if(paymentBudgetConfig[i][1].equals(goalTypeStr)){
				easBizAccountEntryParam.setRoleName(paymentBudgetConfig[i][0]);
				easBizAccountEntryParam.setExpenseType(paymentBudgetConfig[i][2]);
				easBizAccountEntryParam.setBudgetNumber(paymentBudgetConfig[i][3]);
				break;
			}
		}
		
		if(StringUtils.isEmpty(easBizAccountEntryParam.getExpenseType())){//没有找到对应的配置，给一个默认值
			int lastIndex =paymentBudgetConfig.length-1;
			easBizAccountEntryParam.setRoleName(paymentBudgetConfig[lastIndex][0]);
			easBizAccountEntryParam.setExpenseType(paymentBudgetConfig[lastIndex][2]);
			easBizAccountEntryParam.setBudgetNumber(paymentBudgetConfig[lastIndex][3]);
		}
		
		return easBizAccountEntryParam;
	}
	
	/**
	 * 检查提款单的银行账户信息是否为空
	 * @param ra
	 * @return
	 */
	public static boolean isBankInfoIsNull(RevenueApplyEntity ra){
		if (StringUtils.isEmpty(ra.getAccountName()) || StringUtils.isEmpty(ra.getBankBranch()) 
				|| StringUtils.isEmpty(ra.getbBankName()) || StringUtils.isEmpty(ra.getbBankNo())) {
			logger.info("RevenueApplyEntity bank info is null");
			logger.info("RevenueApplyEntity is:" + ra.toString());
			return true;
		}
		
		return false;
	}
	
	/**
	 * 根据提款单 获得付款单 ， 一对一生成
	 * @param raList 提款单 
	 * @return
	 */
	private static List<PaymentEntity> getPaymentList_OneByOne(List<RevenueApplyEntity> raList) {
		List<PaymentEntity> paymentEntityList = new ArrayList<PaymentEntity>(raList.size());
		for(RevenueApplyEntity ra: raList){
			PaymentEntity paymentEntity = getOnePaymentWithRevenueApply(ra);
			if(paymentEntity != null){
				paymentEntityList.add(paymentEntity);
			}
		}
		return paymentEntityList;
	}

	/**
	 * 根据提款单 获得报销单列表 ， 一对一生成
	 * @param raList 提款单 
	 * @return
	 */
	private static List<BizAccountEntity> getBizAccountList_OneByOne(List<RevenueApplyEntity> raList) {
		EASBizAccountPushParamParser bizAccountPushParamParser = (EASBizAccountPushParamParser) CnfantasiaCommbusiUtil.getBeanManager("easBizAccountPushParamParser");
		EASBizAccountParam easBizAccountParam = bizAccountPushParamParser.parseParamValue();
		List<BizAccountEntity> bizAccountEntityList = new ArrayList<BizAccountEntity>(raList.size());
		for(RevenueApplyEntity ra: raList){
			
			if(ra.getAmountPtbt() <= 0){// 金额大于0才需要推送报销单
				continue;
			}
			
			BizAccountEntity bizAccountEntity = new BizAccountEntity();
			
			bizAccountEntity.setSrcBillNumber(ra.getApplyNo());			// 原系统编码
			bizAccountEntity.setBillStatus("提交");				// 单据状态(保存、提交)
			bizAccountEntity.setTheme(getPropertyCompanyNameAndGbname(ra)+ getTheme(ra, false));					// 主题
			bizAccountEntity.setAppDate(ra.getApplyTime());					// 申请日期
			bizAccountEntity.setApplierCompany(easBizAccountParam.getApplierCompany());			// 报销人公司
//			bizAccountEntity.setApplierDept(easBizAccountParam.getApplierDept());				// 报销人部门
//			bizAccountEntity.setApplier(getEasPushAccount(ra).getEasUserAccount());					// 报销人
//			bizAccountEntity.setPosition(easBizAccountParam.getPosition());				// 职位
			bizAccountEntity.setPayCompany(easBizAccountParam.getPayCompany());				// 费用支付公司
			bizAccountEntity.setCurrency("BB01");				// 币别
			bizAccountEntity.setPayMode("00");					// 支付方式:00网银转账
			bizAccountEntity.setPrior(easBizAccountParam.getPrior());					// 紧急程度(HIGH、MIDDLE、LOW)
			if(isBankInfoIsNull(ra)){
				continue;
			}
			bizAccountEntity.setRevDept(ra.getAccountName());					// 收款单位
			bizAccountEntity.setRevAccount(ra.getbBankNo());				// 收款方账号
			bizAccountEntity.setRevOpenBank(ra.getbBankName()+ ra.getBankBranch());				// 收款方开户行
			bizAccountEntity.setIsonlyperson(ra.getAccountName().length() <= 6 ? "1" : "0");		//是否是个人收款  Fixme 暂要按物业公司或个人来判断？收款人账户名》6个字，即为公司
			
			bizAccountEntity.setCause(getEasPushAccount(ra).getEasUserName() + "报销-" + getPropertyCompanyNameAndGbname(ra) + getTheme(ra, false));					// 事由
//			EASLoginAccountConfigParamParser easLoginAccountConfigParamParser = (EASLoginAccountConfigParamParser) CnfantasiaCommbusiUtil.getBeanManager("easLoginAccountConfigParamParser");
//			EASLoginAccountConfigParam easLoginAccountConfigParam = easLoginAccountConfigParamParser.parseParamValue();
			bizAccountEntity.setBiller(getBiller(ra));					//制单人
			bizAccountEntity.setEasUserAccount(getEasPushAccount(ra).getEasUserAccount());
			bizAccountEntity.setEasUserPassword(getEasPushAccount(ra).getEasUserPassword());
			bizAccountEntity.setDesc("附件为明细");					// 备注
			
			List<BizAccountEntryEntity> bizAccountEntryList = new ArrayList<BizAccountEntryEntity>() ;	// 分录数据
			BizAccountEntryEntity bizAccountEntry = new BizAccountEntryEntity();
			
			EASBizAccountEntryParam easBizAccountEntryParam = getBizAccountEntryParamByRevenueApply(ra,null);
			
			bizAccountEntry.setBudgetNumber(easBizAccountEntryParam.getBudgetNumber());			// 预算编号
			bizAccountEntry.setOperationType(easBizAccountEntryParam.getOperationType());				// 业务类别
			bizAccountEntry.setExpenseType(easBizAccountEntryParam.getExpenseType());					// 费用类型
			bizAccountEntry.setPurpose(easBizAccountEntryParam.getPurpose());						// 费用说明
			bizAccountEntry.setHappenTime(ra.getApplyTime());					// 发生时间
			
//			if(ra.getGoalType().intValue() == RevenueDict.RevenueProject.PropertyRealPayAmout //物业实收
//					|| ra.getGoalType().intValue() == RevenueDict.RevenueProject.PropertyOtherFee //物业代收
//					|| ra.getGoalType().intValue() == RevenueDict.RevenueProject.CarAmount){//停车费代收
//				bizAccountEntry.setOriginalAmount(new BigDecimal(ra.getAmountUsrReal()));			// 原币金额
//				bizAccountEntry.setAmount(new BigDecimal(ra.getAmountUsrReal()));					// 金额(人民币)
//				bizAccountEntry.setApprovedAmount(new BigDecimal(ra.getAmountUsrReal()));			// 核定金额
//			}
			
		/*	if(ra.getGoalType().intValue() == RevenueDict.RevenueProject.PropertySubsidyAmout //物业补贴
					|| ra.getGoalType().intValue() == RevenueDict.RevenueProject.CarAmountBt ){ //停车费补贴 
				bizAccountEntry.setOriginalAmount(new BigDecimal(ra.getAmountPtbt()));			// 原币金额
				bizAccountEntry.setAmount(new BigDecimal(ra.getAmountPtbt()));					// 金额(人民币)
				bizAccountEntry.setApprovedAmount(new BigDecimal(ra.getAmountPtbt()));			// 核定金额
			}*/
			
//			if(ra.getMiniRoleType().intValue() == RevenueDict.MiniRoleType.Recommender
//					&& (ra.getGoalType().intValue() == RevenueDict.RevenueProject.ServiceOrder)){ //上门服务
//				bizAccountEntry.setOriginalAmount(new BigDecimal(ra.getAmountUsrReal() + ra.getAmountPtbt()));			// 原币金额
//				bizAccountEntry.setAmount(new BigDecimal(ra.getAmountUsrReal()+ra.getAmountPtbt()));					// 金额(人民币)
//				bizAccountEntry.setApprovedAmount(new BigDecimal(ra.getAmountUsrReal()+ra.getAmountPtbt()));			// 核定金额
//			}
			
//			if(bizAccountEntry.getOriginalAmount() == null){ // 如果前面还没有设置好分录金额，再来设置一次，取补贴和实收总额
//				bizAccountEntry.setOriginalAmount(new BigDecimal(ra.getAmountUsrReal() + ra.getAmountPtbt()));			// 原币金额
//				bizAccountEntry.setAmount(new BigDecimal(ra.getAmountUsrReal()+ra.getAmountPtbt()));					// 金额(人民币)
//				bizAccountEntry.setApprovedAmount(new BigDecimal(ra.getAmountUsrReal()+ra.getAmountPtbt()));			// 核定金额
//			}
			
			
			bizAccountEntry.setOriginalAmount(new BigDecimal(ra.getAmountPtbt()));			// 原币金额
			bizAccountEntry.setAmount(new BigDecimal(ra.getAmountPtbt()));					// 金额(人民币)
			bizAccountEntry.setApprovedAmount(new BigDecimal(ra.getAmountPtbt()));			// 核定金额
			
			bizAccountEntry.setComment(ra.getApplyNo());						// 备注
			bizAccountEntryList.add(bizAccountEntry);
			bizAccountEntity.setBizAccountEntry(bizAccountEntryList);
			
			appendAttachmentFile(bizAccountEntity, ra, false);
			bizAccountEntityList.add(bizAccountEntity);
		}
		return bizAccountEntityList;
	}

	private static void appendAttachmentFile(BizAccountEntity bizAccountEntity, RevenueApplyEntity ra, Boolean isUserRealPay) {
		IRevenueService revenueService = (IRevenueService)(CnfantasiaCommbusiUtil.getBeanManager("revenueService"));
		RevenueBatchParam revenueBatchParam = new RevenueBatchParam();
		{
			revenueBatchParam.setGoalType(ra.getGoalType().toString());
			revenueBatchParam.setApplyId(ra.getId().toString());
			revenueBatchParam.setMiniRoleId(ra.getMiniRoleId().toString());
			revenueBatchParam.setMiniRoleType(ra.getMiniRoleType().toString());
		}
		HSSFWorkbook wb  = revenueService.getRevenueHSSFWorkbook(revenueBatchParam);
		if(wb == null) return;
		
		List<AttachmentEntity> attachmentList = new ArrayList<AttachmentEntity>();				// 附件
		AttachmentEntity attachmentEntity = new AttachmentEntity();
		attachmentEntity.setSimpleName("xls");
		attachmentEntity.setFileName(getCauseName(ra, isUserRealPay));
		try {
			attachmentEntity.ioToBase64(wb);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info(e.getMessage(), e);
		}
		attachmentList.add(attachmentEntity);
		
		bizAccountEntity.getAttachment().add(attachmentEntity);
	}
	
	private static void appendAttachmentFile(PaymentEntity paymentEntity, RevenueApplyEntity ra, Boolean isUserRealPay) {
		IRevenueService revenueService = (IRevenueService)(CnfantasiaCommbusiUtil.getBeanManager("revenueService"));
		RevenueBatchParam revenueBatchParam = new RevenueBatchParam();
		{
			revenueBatchParam.setGoalType(ra.getGoalType().toString());
			revenueBatchParam.setApplyId(ra.getId().toString());
			revenueBatchParam.setMiniRoleId(ra.getMiniRoleId().toString());
			revenueBatchParam.setMiniRoleType(ra.getMiniRoleType().toString());
		}
		HSSFWorkbook wb  = revenueService.getRevenueHSSFWorkbook(revenueBatchParam);
		if(wb == null) return;
		
		List<AttachmentEntity> attachmentList = new ArrayList<AttachmentEntity>();				// 附件
		AttachmentEntity attachmentEntity = new AttachmentEntity();
		attachmentEntity.setSimpleName("xls");
		attachmentEntity.setFileName(getCauseName(ra, isUserRealPay));
		try {
			attachmentEntity.ioToBase64(wb);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info(e.getMessage(), e);
		}
		attachmentList.add(attachmentEntity);
		
		paymentEntity.getAttachment().add(attachmentEntity);
	}

	/**
	 * 根据提款单 获得报销单列表 ， 一对多生成
	 * @param raList 提款单 
	 * @return
	 */
	private static List<BizAccountEntity> getBizAccountList_OneToMultiply(List<RevenueApplyEntity> raList) {
		EASBizAccountPushParamParser bizAccountPushParamParser = (EASBizAccountPushParamParser) CnfantasiaCommbusiUtil.getBeanManager("easBizAccountPushParamParser");
		EASBizAccountParam easBizAccountParam = bizAccountPushParamParser.parseParamValue();
		List<BizAccountEntity> bizAccountEntityList = new ArrayList<BizAccountEntity>(raList.size());
		for(RevenueApplyEntity ra: raList){
			if(isBankInfoIsNull(ra)){//没有收款账户也不需要推送
				continue;
			}
			
//			if(ra.getAmountUsrReal()>0){//有金额才需要生成报销单
//				bizAccountEntityList.add(getOneBizAccount(easBizAccountParam, ra, true));
//			}
			if(ra.getAmountPtbt()>0){
				bizAccountEntityList.add(getOneBizAccount(easBizAccountParam, ra, false));
			}
		}
		return bizAccountEntityList;
	}
	
	/**
	 * 根据提款单 获得付款单列表 ， 一对多生成
	 * @param raList 提款单 
	 * @return
	 */
	private static List<PaymentEntity> getPaymentEntityList_OneToMultiply(List<RevenueApplyEntity> raList) {
		List<PaymentEntity> paymentEntityList = new ArrayList<PaymentEntity>(raList.size());
		for(RevenueApplyEntity ra: raList){
			if(isBankInfoIsNull(ra)){//没有收款账户也不需要推送
				continue;
			}
			
			if(ra.getAmountUsrReal()>0){//有金额才需要生成报销单
				paymentEntityList.add(getOnePaymentWithRevenueApply(ra));
			}
		}
		return paymentEntityList;
	}

	/**
	 * 产生一笔报销单
	 * @param easBizAccountParam 动态配置参数
	 * @param ra 提款单
	 * @param isUserRealPay 是否是用户实付
	 * @return
	 */
	private static BizAccountEntity getOneBizAccount(EASBizAccountParam easBizAccountParam, RevenueApplyEntity ra, boolean isUserRealPay) {
		BizAccountEntity bizAccountEntity = new BizAccountEntity();
		
		bizAccountEntity.setSrcBillNumber(ra.getApplyNo());			// 原系统编码
		bizAccountEntity.setBillStatus("提交");				// 单据状态(保存、提交)
		bizAccountEntity.setTheme(getPropertyCompanyNameAndGbname(ra)+ getTheme(ra, isUserRealPay));					// 主题
		bizAccountEntity.setAppDate(ra.getApplyTime());					// 申请日期
		bizAccountEntity.setApplierCompany(easBizAccountParam.getApplierCompany());			// 报销人公司
		//bizAccountEntity.setApplierDept(easBizAccountParam.getApplierDept());				// 报销人部门
//		bizAccountEntity.setApplier(getEasPushAccount(ra).getEasUserAccount());					// 报销人
//		bizAccountEntity.setPosition(easBizAccountParam.getPosition());				// 职位
		bizAccountEntity.setPayCompany(easBizAccountParam.getPayCompany());				// 费用支付公司
		bizAccountEntity.setCurrency("BB01");				// 币别
		bizAccountEntity.setPayMode("00");					// 支付方式:00网银转账
		bizAccountEntity.setPrior(easBizAccountParam.getPrior());					// 紧急程度(HIGH、MIDDLE、LOW)
		bizAccountEntity.setRevDept(ra.getAccountName());					// 收款单位
		bizAccountEntity.setRevAccount(ra.getbBankNo());				// 收款方账号
		bizAccountEntity.setRevOpenBank(ra.getbBankName()+ ra.getBankBranch());				// 收款方开户行
		bizAccountEntity.setIsonlyperson(ra.getAccountName().length() <= 6 ? "1" : "0");		//是否是个人收款  Fixme 暂要按物业公司或个人来判断？收款人账户名》6个字，即为公司
		
		bizAccountEntity.setCause(getEasPushAccount(ra).getEasUserName() + "报销-" +getPropertyCompanyNameAndGbname(ra) + getTheme(ra, isUserRealPay));					// 事由
//		EASLoginAccountConfigParamParser easLoginAccountConfigParamParser = (EASLoginAccountConfigParamParser) CnfantasiaCommbusiUtil.getBeanManager("easLoginAccountConfigParamParser");
//		EASLoginAccountConfigParam easLoginAccountConfigParam = easLoginAccountConfigParamParser.parseParamValue();
		bizAccountEntity.setBiller(getBiller(ra));					//制单人
		bizAccountEntity.setEasUserAccount(getEasPushAccount(ra).getEasUserAccount());
		bizAccountEntity.setEasUserPassword(getEasPushAccount(ra).getEasUserPassword());
		bizAccountEntity.setDesc("附件为明细");					// 备注
		
		EASBizAccountEntryParam easBizAccountEntryParam = getBizAccountEntryParamByRevenueApply(ra, isUserRealPay);
		
		List<BizAccountEntryEntity> bizAccountEntryList = new ArrayList<BizAccountEntryEntity>() ;	// 分录数据
		BizAccountEntryEntity bizAccountEntry = new BizAccountEntryEntity();			
		bizAccountEntry.setBudgetNumber(easBizAccountEntryParam.getBudgetNumber());			// 预算编号
		bizAccountEntry.setOperationType(easBizAccountEntryParam.getOperationType());				// 业务类别
		bizAccountEntry.setExpenseType(easBizAccountEntryParam.getExpenseType());					// 费用类型
		bizAccountEntry.setPurpose(easBizAccountEntryParam.getPurpose());						// 费用说明
		bizAccountEntry.setHappenTime(ra.getApplyTime());					// 发生时间
		bizAccountEntry.setComment(ra.getApplyNo());					// 备注
		
		if (isUserRealPay) {//用户实付
			bizAccountEntry.setOriginalAmount(new BigDecimal(ra.getAmountUsrReal())); // 原币金额
			bizAccountEntry.setAmount(new BigDecimal(ra.getAmountUsrReal())); // 金额(人民币)
			bizAccountEntry.setApprovedAmount(new BigDecimal(ra.getAmountUsrReal())); // 核定金额
		} else {//补贴部分
			bizAccountEntry.setOriginalAmount(new BigDecimal(ra.getAmountPtbt())); // 原币金额
			bizAccountEntry.setAmount(new BigDecimal(ra.getAmountPtbt())); // 金额(人民币)
			bizAccountEntry.setApprovedAmount(new BigDecimal(ra.getAmountPtbt())); // 核定金额
		}
		
		bizAccountEntryList.add(bizAccountEntry);
		bizAccountEntity.setBizAccountEntry(bizAccountEntryList);
		
		appendAttachmentFile(bizAccountEntity, ra, isUserRealPay);
		return bizAccountEntity;
	}
	
	/**
	 * 根据申请单 产生一笔 付款单
	 * @param ra 提款单
	 * @return 返回待推的付款单，如果实付金额<=0时，则返回null
	 */
	private static PaymentEntity getOnePaymentWithRevenueApply(RevenueApplyEntity ra) {
		if(ra.getAmountUsrReal() <=0 ){
			return null;
		}
		
		EASBizAccountPushParamParser bizAccountPushParamParser = (EASBizAccountPushParamParser) CnfantasiaCommbusiUtil.getBeanManager("easBizAccountPushParamParser");
		EASBizAccountParam easBizAccountParam = bizAccountPushParamParser.parseParamValue();
		
		PaymentEntity paymentEntity = new PaymentEntity();
		
		paymentEntity.setMiniRoleId(ra.getMiniRoleId());
		paymentEntity.setSrcBillNumber(ra.getApplyNo());			// 原系统编码
		paymentEntity.setBillStatus("保存");				// 单据状态(保存、提交)
		paymentEntity.setMainOrgCompany(easBizAccountParam.getApplierCompany());			// 公司
		paymentEntity.setBizDate(DateUtils.getCurrentDate());					// 业务日期
		paymentEntity.setPayBillType("211");				// 付款类型
		paymentEntity.setCurrency("BB01");				// 付款币别
		paymentEntity.setPayerAccountBank("554277");		// 付款账户, 玉梅说写死： 554277	深圳前海邻里乐科技服务有限公司	人民币	400002531920 0554277	中国工商银行深圳车公庙支行	深圳前海邻里乐科技服务有限公司	2015-12-16	HYN_LLL	收支户	否	否			活期
		//paymentEntity.setPayerBank("乾多多");				// 付款银行
		paymentEntity.setPayerBank("12.01");	// 从付款账户那里带过来			// 付款银行
		paymentEntity.setPayerAccount("1002.01");			// 付款科目
		paymentEntity.setSettlementType("00");			// 结算方式
		//paymentEntity.setAdminOrgUnit(easBizAccountParam.getApplierDept());			//，应该根据制单人的来， 部门
		//paymentEntity.setCostCenter("HYN_LLL_01");				// 成本中心 //，
		paymentEntity.setPayeeType("00002");				// 收款人类型   --- 00002 供应商
		paymentEntity.setPayee(ra.getAccountName());					// 收款人名称
		paymentEntity.setPayeeAccountBank(ra.getbBankNo());		// 收款账号
		paymentEntity.setPayeeBank(ra.getbBankName()+ ra.getBankBranch());				// 收银银行
		paymentEntity.setPaymentType("网银转账");				//付款方式
		paymentEntity.setSourceType("CASH");				//付款单来源
		paymentEntity.setDesc(getEasPushAccount(ra).getEasUserName() + "报销-" + getPropertyCompanyNameAndGbname(ra)+ getTheme(ra, true));					// 摘要
		paymentEntity.setCreator(getBiller(ra));
		
		paymentEntity.setEasUserAccount(getEasPushAccount(ra).getEasUserAccount());
		paymentEntity.setEasUserPassword(getEasPushAccount(ra).getEasUserPassword());
		
//		if(isBankInfoIsNull(ra)){
//			continue;
//		}
		
		List<PaymentEntryEntity> paymentEntryList = new ArrayList<PaymentEntryEntity>() ;	// 分录数据
		PaymentEntryEntity paymentEntryEntity = new PaymentEntryEntity();
		
		EASPaymentEntryParam easPaymentEntryParam = getPaymentEntryParamByRevenueApply(ra);

		paymentEntryEntity.setActPayAmt(new BigDecimal(ra.getAmountUsrReal()).setScale(2, RoundingMode.HALF_UP));
		paymentEntryEntity.setActPayLocAmt(new BigDecimal(ra.getAmountUsrReal()).setScale(2, RoundingMode.HALF_UP));
		//paymentEntryEntity.setEntryCostCenter("HYN_LLL_01");//要根据制单人动态获取
		paymentEntryEntity.setExpenseType(easPaymentEntryParam.getExpenseType());
		paymentEntryEntity.setOutBgItem(easPaymentEntryParam.getBudgetNumber());
		paymentEntryEntity.setRemark(easPaymentEntryParam.getRoleName());		
		
		paymentEntryList.add(paymentEntryEntity);
		
		paymentEntity.setCasPaymentEntry(paymentEntryList);
		
		appendAttachmentFile(paymentEntity, ra, true);
			
		return paymentEntity;
	}
	
	/**
	 * 根据提款单 获得报销单列表， 多对一生成，分录要有多行
	 * @param raList 提款单 
	 * @return
	 */
	private static List<BizAccountEntity> getBizAccountList_MultiplyToOne(List<RevenueApplyEntity> raList) {
		EASBizAccountPushParamParser bizAccountPushParamParser = (EASBizAccountPushParamParser) CnfantasiaCommbusiUtil.getBeanManager("easBizAccountPushParamParser");
		EASBizAccountParam easBizAccountParam = bizAccountPushParamParser.parseParamValue();
		List<BizAccountEntity> bizAccountEntityList = new ArrayList<BizAccountEntity>(raList.size());
		outterLoop: for(int i = 0; i < raList.size(); i++){
			RevenueApplyEntity ra =  raList.get(i);
			
			if(ra.getAmountPtbt() <= 0) //报销单只取补贴部分
				continue;
			
			BigDecimal raTotalAmt = new BigDecimal(ra.getAmountPtbt());
			for (BizAccountEntity bizAccountEntity : bizAccountEntityList) {//多对一，归并为一条报销单，分录要有多行, 附件也要有多个
				if (ra.getMiniRoleId().equals(bizAccountEntity.getMiniRoleId())) {
					bizAccountEntity.setSrcBillNumber(bizAccountEntity.getSrcBillNumber() + ";" + ra.getApplyNo());
					
					EASBizAccountEntryParam easBizAccountEntryParam = getBizAccountEntryParamByRevenueApply(ra, false);
					BizAccountEntryEntity bizAccountEntry = new BizAccountEntryEntity();
					bizAccountEntry.setBudgetNumber(easBizAccountEntryParam.getBudgetNumber());			// 预算编号
					bizAccountEntry.setOperationType(easBizAccountEntryParam.getOperationType());				// 业务类别
					bizAccountEntry.setExpenseType(easBizAccountEntryParam.getExpenseType());					// 费用类型
					bizAccountEntry.setPurpose(easBizAccountEntryParam.getPurpose());						// 费用说明
					bizAccountEntry.setHappenTime(ra.getApplyTime());					// 发生时间
					bizAccountEntry.setOriginalAmount(raTotalAmt);			// 原币金额
					bizAccountEntry.setAmount(raTotalAmt);					// 金额(人民币)
					bizAccountEntry.setApprovedAmount(raTotalAmt);			// 核定金额
					bizAccountEntry.setComment(ra.getApplyNo());						// 备注
					bizAccountEntity.getBizAccountEntry().add(bizAccountEntry);
					
					logger.info("bizAccountEntity 3 个金额 : " +bizAccountEntry.getOriginalAmount() 
							+ "\t" + bizAccountEntry.getAmount() +"\t" + bizAccountEntry.getApprovedAmount());
					
					appendAttachmentFile(bizAccountEntity, ra, false);
					
					continue outterLoop;
				}
			}
			 
			BizAccountEntity bizAccountEntity = new BizAccountEntity();
			
			bizAccountEntity.setMiniRoleId(ra.getMiniRoleId());
			bizAccountEntity.setSrcBillNumber(ra.getApplyNo());			// 原系统编码
			bizAccountEntity.setBillStatus("提交");				// 单据状态(保存、提交)
			bizAccountEntity.setTheme(getPropertyCompanyNameAndGbname(ra)+ getTheme(ra, false));					// 主题
			bizAccountEntity.setAppDate(ra.getApplyTime());					// 申请日期
			bizAccountEntity.setApplierCompany(easBizAccountParam.getApplierCompany());			// 报销人公司
//			bizAccountEntity.setApplierDept(easBizAccountParam.getApplierDept());				// 报销人部门
//			bizAccountEntity.setApplier(getEasPushAccount(ra).getEasUserAccount());					// 报销人
//			bizAccountEntity.setPosition(easBizAccountParam.getPosition());				// 职位
			bizAccountEntity.setPayCompany(easBizAccountParam.getPayCompany());				// 费用支付公司
			bizAccountEntity.setCurrency("BB01");				// 币别
			bizAccountEntity.setPayMode("00");					// 支付方式:00网银转账
			bizAccountEntity.setPrior(easBizAccountParam.getPrior());					// 紧急程度(HIGH、MIDDLE、LOW)
			if(isBankInfoIsNull(ra)){//没有收款账户也不需要推送
				continue;
			}
			bizAccountEntity.setRevDept(ra.getAccountName());					// 收款单位
			bizAccountEntity.setRevAccount(ra.getbBankNo());				// 收款方账号
			bizAccountEntity.setRevOpenBank(ra.getbBankName()+ ra.getBankBranch());				// 收款方开户行
			bizAccountEntity.setIsonlyperson(ra.getAccountName().length() <= 6 ? "1" : "0");		//是否是个人收款  Fixme 暂要按物业公司或个人来判断？收款人账户名》6个字，即为公司
			
			
			bizAccountEntity.setCause(getEasPushAccount(ra).getEasUserName() + "报销-" +getPropertyCompanyNameAndGbname(ra) + getTheme(ra, false));					// 事由
			
//			EASLoginAccountConfigParamParser easLoginAccountConfigParamParser = (EASLoginAccountConfigParamParser) CnfantasiaCommbusiUtil.getBeanManager("easLoginAccountConfigParamParser");
//			EASLoginAccountConfigParam easLoginAccountConfigParam = easLoginAccountConfigParamParser.parseParamValue();
			bizAccountEntity.setBiller(getBiller(ra));					//制单人
			bizAccountEntity.setEasUserAccount(getEasPushAccount(ra).getEasUserAccount());
			bizAccountEntity.setEasUserPassword(getEasPushAccount(ra).getEasUserPassword());
			bizAccountEntity.setDesc("附件为明细");					// 备注
			
			List<BizAccountEntryEntity> bizAccountEntryList = new ArrayList<BizAccountEntryEntity>() ;	// 分录数据
			BizAccountEntryEntity bizAccountEntry = new BizAccountEntryEntity();
			
			EASBizAccountEntryParam easBizAccountEntryParam = getBizAccountEntryParamByRevenueApply(ra, false);
			
			bizAccountEntry.setBudgetNumber(easBizAccountEntryParam.getBudgetNumber());			// 预算编号
			bizAccountEntry.setOperationType(easBizAccountEntryParam.getOperationType());				// 业务类别
			bizAccountEntry.setExpenseType(easBizAccountEntryParam.getExpenseType());					// 费用类型
			bizAccountEntry.setPurpose(easBizAccountEntryParam.getPurpose());						// 费用说明
			bizAccountEntry.setHappenTime(ra.getApplyTime());					// 发生时间
			bizAccountEntry.setOriginalAmount(raTotalAmt);			// 原币金额
			bizAccountEntry.setAmount(raTotalAmt);					// 金额(人民币)
			bizAccountEntry.setApprovedAmount(raTotalAmt);			// 核定金额
			bizAccountEntry.setComment(ra.getApplyNo());						// 备注
			bizAccountEntryList.add(bizAccountEntry);
			bizAccountEntity.setBizAccountEntry(bizAccountEntryList);
			
			appendAttachmentFile(bizAccountEntity, ra, false);
			bizAccountEntityList.add(bizAccountEntity);
		}
		return bizAccountEntityList;
	}
	
	
	/**
	 * 根据提款单 获得 付款单 列表， 多对一生成，分录要有多行
	 * @param raList 提款单 
	 * @return 付款单 列表
	 */
	private static List<PaymentEntity> getPaymentList_MultiplyToOne(List<RevenueApplyEntity> raList) {
		List<PaymentEntity> paymentEntityList = new ArrayList<PaymentEntity>(raList.size());
		outterLoop: for(int i = 0; i < raList.size(); i++){
			RevenueApplyEntity ra =  raList.get(i);
			
			if(ra.getAmountUsrReal() <= 0)
				continue;
			
			for (PaymentEntity paymentEntity: paymentEntityList) {//多对一，归并为一条 付款单，分录要有多行, 附件也要有多个
				if (ra.getMiniRoleId().equals(paymentEntity.getMiniRoleId())) {
					paymentEntity.setSrcBillNumber(paymentEntity.getSrcBillNumber() + ";" + ra.getApplyNo());
					
					PaymentEntryEntity paymentEntryEntity = new PaymentEntryEntity();
					
					EASPaymentEntryParam easPaymentEntryParam = getPaymentEntryParamByRevenueApply(ra);

					paymentEntryEntity.setActPayAmt(new BigDecimal(ra.getAmountUsrReal()));
					paymentEntryEntity.setActPayLocAmt(new BigDecimal(ra.getAmountUsrReal()));
					//paymentEntryEntity.setEntryCostCenter("HYN_LLL_01");// 要根据制单人动态获取
					paymentEntryEntity.setExpenseType(easPaymentEntryParam.getExpenseType());
					paymentEntryEntity.setOutBgItem(easPaymentEntryParam.getBudgetNumber());
					paymentEntryEntity.setRemark(easPaymentEntryParam.getRoleName());		
					paymentEntity.getCasPaymentEntry().add(paymentEntryEntity);
					
					
					logger.info("paymentEntity 2 个金额 : " + paymentEntryEntity.getActPayAmt()
							+ "\t" + paymentEntryEntity.getActPayLocAmt());
					
					appendAttachmentFile(paymentEntity, ra, true);
					
					continue outterLoop;
				}
			}
			 
			PaymentEntity paymentEntity = getOnePaymentWithRevenueApply(ra);
			if (paymentEntity != null) {
				paymentEntityList.add(paymentEntity);
			}
		}
		return paymentEntityList;
	}
	
	/**
	 * 获得报销单主题，付款单的事由
	 * @param ra
	 * @param isUserRealPay 实收或补贴，必须要给定参数
	 * @return
	 */
	private static String getTheme(RevenueApplyEntity ra, Boolean isUserRealPay){
//		{"1":"认证门牌","2":"服务类订单","3":"物业宝佣金","4":"超市收益","5":"物业费实收","6":"车禁费用",
//		"7":"其他代收费用","8":"物业宝抵扣收益","9":"停车宝佣金","10":"停车宝抵扣收益","15":"物业补贴","16":"购销电商供应商"}
	
		String theme = "未知费用 ";
		int miniRoleType = ra.getMiniRoleType();
		int goalType = ra.getGoalType();
		String roleName= ra.getRoleName();
		
		if(miniRoleType == RevenueDict.MiniRoleType.PropertyCompany
				|| miniRoleType == RevenueDict.MiniRoleType.PCManagement){
			if(goalType == RevenueDict.RevenueProject.PropertyRealPayAmout
					|| goalType == RevenueDict.RevenueProject.CarAmount
					|| goalType == RevenueDict.RevenueProject.CarDeduAmount
					|| goalType == RevenueDict.RevenueProject.FinanceDeduAmount
					|| (goalType == RevenueDict.RevenueProject.PropertyOtherFee && isUserRealPay)){
				theme = "物业代收";
			} else if (goalType == RevenueDict.RevenueProject.PropertySubsidyAmout
					|| goalType == RevenueDict.RevenueProject.CarAmountBt
					|| (goalType == RevenueDict.RevenueProject.PropertyOtherFee && !isUserRealPay)) {
				theme = "物业补贴";
			} else if (goalType == RevenueDict.RevenueProject.WuyebaoAmount 
					||goalType == RevenueDict.RevenueProject.CarFinanceBaoAmout
					|| goalType == RevenueDict.RevenueProject.MarketAmout 
					||goalType == RevenueDict.RevenueProject.ServiceOrder) {
				theme = "物业收益";
			}
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.PropertyAgent){
			if(goalType == RevenueDict.RevenueProject.WuyebaoAmount
					||goalType == RevenueDict.RevenueProject.CarFinanceBaoAmout
					||goalType == RevenueDict.RevenueProject.MarketAmout
					||goalType == RevenueDict.RevenueProject.ServiceOrder){
				theme = roleName+"代理商收益";
			}
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.RepairMaster){
			if(goalType == RevenueDict.RevenueProject.ServiceOrder){
				if(isUserRealPay){
					theme = roleName+"代收维修费用";
				}else{
					theme = roleName+"维修补贴";
				}
			}
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.DownstairStore){
			if(goalType == RevenueDict.RevenueProject.MarketAmout){
				if(isUserRealPay){
					theme = roleName+"代收超市费用"; 
				}else{
					theme = roleName+"超市补贴";
				}
			}
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.Recommender){
			if(goalType == RevenueDict.RevenueProject.ServiceOrder){
				theme = roleName+"维修收益";
			}
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.DirectPurchase){
			if(goalType == RevenueDict.RevenueProject.DirectPurchase){
				if(isUserRealPay){
					theme = roleName+"代收供应商费用";
				}else{
					theme = roleName+"供应商补贴";
				}
			}
		}

		if(miniRoleType == RevenueDict.MiniRoleType.LivingFeePay){
			if(goalType == RevenueDict.RevenueProject.LivingPayAmount){
				theme = roleName+"生活缴费代收";
			}
		}
		return theme;
	}
	
	/**
	 * 动态获取制单人
	 * @param ra 提款单
	 * @return 制单人在EAS中的账号
	 */
	private static String getBiller(RevenueApplyEntity ra){
		//String biller = "wangchong";
		
		if(easPushAccountList == null){ // 第一次调用从数据库中取数，非首次用缓存，若数据表有更新数据，需要重启服务
			EasPushAccountBaseService easPushAccountBaseService = (EasPushAccountBaseService) CnfantasiaCommbusiUtil.getBeanManager("easPushAccountBaseService");
			easPushAccountList = easPushAccountBaseService.getEasPushAccountByCondition(null);
		}
		
		for(int i = 0; i < easPushAccountList.size(); i++){
			EasPushAccount elment = easPushAccountList.get(i);
			if(ra.getMiniRoleType().intValue() == elment.getMiniRoleType()
					&& ra.getGoalType().intValue() == elment.getGoalType()){
				return elment.getEasUserAccount();
			}
		}
		
		return null;
	}
	
	/**
	 * 动态获取制单
	 * @param ra 提款单
	 * @return 制单人在EAS中的账号
	 */
	private static EasPushAccount getEasPushAccount(RevenueApplyEntity ra){
		if(easPushAccountList == null){ // 第一次调用从数据库中取数，非首次用缓存，若数据表有更新数据，需要重启服务
			EasPushAccountBaseService easPushAccountBaseService = (EasPushAccountBaseService) CnfantasiaCommbusiUtil.getBeanManager("easPushAccountBaseService");
			easPushAccountList = easPushAccountBaseService.getEasPushAccountByCondition(null);
		}
		
		for(int i = 0; i < easPushAccountList.size(); i++){
			EasPushAccount elment = easPushAccountList.get(i);
			if(ra.getMiniRoleType().intValue() == elment.getMiniRoleType()
					&& ra.getGoalType().intValue() == elment.getGoalType()){
				return elment;
			}
		}
		
//		EasPushAccount easPushAccount = new EasPushAccount();
//		easPushAccount.setEasUserAccount("wangchong");
//		easPushAccount.setEasUserPassword("123456");
		return null;
	}
	
	/**
	 * 获得物业公司名称+小区名称
	 * @param ra
	 * @return
	 */
	private static String getPropertyCompanyNameAndGbname(RevenueApplyEntity ra){
		String pcNameAndGbName = "";
		int miniRoleType = ra.getMiniRoleType();
		BigInteger miniRoleId = ra.getMiniRoleId();
		if(miniRoleType != RevenueDict.MiniRoleType.PropertyCompany
				&& miniRoleType != RevenueDict.MiniRoleType.PCManagement){
			return pcNameAndGbName;
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.PropertyCompany){
			pcNameAndGbName += ra.getRoleName();//物业公司提款直接取，不要走DB
		}else if(miniRoleType == RevenueDict.MiniRoleType.PCManagement){
			PropertyManagementBaseDao propertyManagementBaseDao = (PropertyManagementBaseDao) CnfantasiaCommbusiUtil.getBeanManager("propertyManagementBaseDao");
			BigInteger pcId = propertyManagementBaseDao.selectPropertyManagementBySeqId(miniRoleId).gettPropertyCompanyFId();
			PropertyCompanyBaseDao propertyCompanyBaseDao = (PropertyCompanyBaseDao) CnfantasiaCommbusiUtil.getBeanManager("propertyCompanyBaseDao");
			pcNameAndGbName += propertyCompanyBaseDao.selectPropertyCompanyBySeqId(pcId).getName();
		}
		
		BigInteger gbId = ra.getRevenueSignalAmountList().get(0).gettGroupBuildingId();
		if(gbId!=null){//测试上可能存在gbId为空的数据，若为空，gbName就不取了
			GroupBuildingBaseDao groupBuildingBaseDao = (GroupBuildingBaseDao) CnfantasiaCommbusiUtil.getBeanManager("groupBuildingBaseDao");
			pcNameAndGbName += groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId).getName();
		}
		
		return pcNameAndGbName;
	}
	
	/**
	 * 获取报销单的事由 
	 * @param miniRoleType
	 * @param goalType
	 * @param isUserRealPay 用户补缴还是补贴，如果不区分可以传null
	 * @param roleName 申请对象名称
	 * @return
	 */
	private static String getCauseName(RevenueApplyEntity ra,Boolean isUserRealPay){
//		{"1":"认证门牌","2":"服务类订单","3":"物业宝佣金","4":"超市收益","5":"物业费实收","6":"车禁费用",
//			"7":"其他代收费用","8":"物业宝抵扣收益","9":"停车宝佣金","10":"停车宝抵扣收益","15":"物业补贴","16":"购销电商供应商"}
		
		String cause = "未知费用 ";
		int miniRoleType = ra.getMiniRoleType();
		int goalType = ra.getGoalType();
		String roleName= ra.getRoleName();
		
		if(miniRoleType == RevenueDict.MiniRoleType.PropertyCompany
				|| miniRoleType == RevenueDict.MiniRoleType.PCManagement){
			if(goalType == RevenueDict.RevenueProject.PropertyRealPayAmout){
				cause = "代收物业费";
			}else if(goalType == RevenueDict.RevenueProject.PropertyOtherFee){
				if(isUserRealPay){
					cause = "其他代收费用";
				}else{
					cause = "其他代收费用补贴";
				}
			} else if (goalType == RevenueDict.RevenueProject.PropertySubsidyAmout) {
				cause = "物业费补贴";
			} else if (goalType == RevenueDict.RevenueProject.CarAmount) {
				cause = "代收停车费实收";
			} else if (goalType == RevenueDict.RevenueProject.CarAmountBt) {
				cause = "代收停车费补贴";
			} else if (goalType == RevenueDict.RevenueProject.WuyebaoAmount) {
				cause = "物业宝佣金";
			} else if (goalType == RevenueDict.RevenueProject.CarFinanceBaoAmout) {
				cause = "停车宝佣金";
			} else if (goalType == RevenueDict.RevenueProject.MarketAmout) {
				cause = "超市收益";
			} else if (goalType == RevenueDict.RevenueProject.ServiceOrder) {
				cause = "维修收益";
			} else if (goalType == RevenueDict.RevenueProject.CarDeduAmount
					|| goalType == RevenueDict.RevenueProject.FinanceDeduAmount) {
				cause = "理财抵扣费用";
			}
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.PropertyAgent){
			if(goalType == RevenueDict.RevenueProject.WuyebaoAmount
					||goalType == RevenueDict.RevenueProject.CarFinanceBaoAmout
					||goalType == RevenueDict.RevenueProject.MarketAmout
					||goalType == RevenueDict.RevenueProject.ServiceOrder){
				cause = roleName+"代理商收益";
			}
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.RepairMaster){
			if(goalType == RevenueDict.RevenueProject.ServiceOrder){
				if(isUserRealPay){
					cause = roleName+"代收维修费用";
				}else{
					cause = roleName+"维修补贴";
				}
			}
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.DownstairStore){
			if(goalType == RevenueDict.RevenueProject.MarketAmout){
				if(isUserRealPay){
					cause = roleName+"代收超市费用"; 
				}else{
					cause = roleName+"超市补贴";
				}
			}
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.Recommender){
			if(goalType == RevenueDict.RevenueProject.ServiceOrder){
				cause = roleName+"维修收益";
			}
		}
		
		if(miniRoleType == RevenueDict.MiniRoleType.LivingFeePay){
			if(goalType == RevenueDict.RevenueProject.LivingPayAmount){
				cause = roleName+"生活缴费代收";
			}
		}

		if(miniRoleType == RevenueDict.MiniRoleType.DirectPurchase){
			if(goalType == RevenueDict.RevenueProject.DirectPurchase){
				if(isUserRealPay){
					cause = roleName+"代收供应商费用";
				}else{
					cause = roleName+"供应商补贴";
				}
			}
		}
		return cause + "明细";
	}
	
	public static void main(String[] args) {
		RevenueApplyEntity ra = new RevenueApplyEntity();
		ra.setApplyTime("2016-06-30 12:00:00");
		ra.setAccountName("owen");
		ra.setbBankNo("4392268225223115511");
		ra.setBankBranch("科技园支行");
		ra.setbBankName("招商银行");
		List<RevenueApplyEntity> raList = new ArrayList<RevenueApplyEntity>();
		raList.add(ra);
		pushBill2EAS_OneByOneBill(raList);
	}

	public static String getMimeType(String fileUrl) throws java.io.IOException {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(fileUrl);

		return type;
	}

	/**
	 * 推送数据
	 * @param bizAccountStr 报销单，若为空传入""
	 * @param payBillStr 付款单，若为空传入""
	 * @return 
	 */
	public static ResultInfo pushData(String bizAccountStr, String payBillStr) {
//		WSContext context = easLogin(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.EAS_Server_URL));
//		if (null == context || context.getSessionId().length() == 0) {
//			logger.info("EAS登录不成功");
//		} else {
//			logger.info("登录成功。ID是" + context.getSessionId().toString());
//		}
		try {
			String resultInfo = paybillserver(bizAccountStr, payBillStr);
			logger.info(resultInfo);				
			return JSON.parseObject(resultInfo, ResultInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	private static WSContext easLogin(String address, String easUserAccount, String easUserPwd) {
		try {
			URL uri = new URL(address + "/ormrpc/services/EASLogin?wsdl\n");
			logger.info(uri + "--------------");
			EASLoginProxy easLoginProxy = null;
			easLoginProxy = new EASLoginProxyServiceLocator().getEASLogin(uri);
			EASLoginAccountConfigParamParser easLoginAccountConfigParamParser = (EASLoginAccountConfigParamParser) CnfantasiaCommbusiUtil.getBeanManager("easLoginAccountConfigParamParser");
			EASLoginAccountConfigParam easLoginAccountConfigParam = easLoginAccountConfigParamParser.parseParamValue();
			
			WSContext context = easLoginProxy.login(easUserAccount, easUserPwd, 
					"eas", easLoginAccountConfigParam.getDataCenter(), "L2", 2, "BaseDB");
			return context;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String paybillserver(String bizAccountStr, String payBillStr) {
		try {
			URL uri = new URL(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.EAS_Server_URL) + "/ormrpc/services/WSPayBillServerInterFacade?wsdl\n");
			WSPayBillServerInterFacadeSrvProxy billServerInterFacadeSrvProxy = new WSPayBillServerInterFacadeSrvProxyServiceLocator().getWSPayBillServerInterFacade(uri);
			logger.info("bizAccountStr is:" + bizAccountStr);
			logger.info("payBillStr is:" + payBillStr);
			return billServerInterFacadeSrvProxy.importPayBillServer(bizAccountStr, payBillStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
