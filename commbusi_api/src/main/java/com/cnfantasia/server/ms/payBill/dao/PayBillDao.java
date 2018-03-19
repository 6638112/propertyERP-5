package com.cnfantasia.server.ms.payBill.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.ms.payBill.entity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UnPaidPayBillEntity;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.payBill.dao.PayBillBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillTimeCfg.entity.PayBillTimeCfg;
import com.cnfantasia.server.domainbase.propertyGbAd.entity.PropertyGbAd;

public class PayBillDao extends PayBillBaseDao implements IPayBillDao {
	private Log logger = LogFactory.getLog(getClass());

	@Override
	public int getPayBill_byUserId_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "payBill.select_payBill_byOmsUserId_forCount", paramMap);
	}

	@Override
	public int getPayBill_byUserId_forCount_Revenue(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "payBill.getPayBill_byUserId_forCount_Revenue", paramMap);
	}

	@Override
	public List<PayBillEntity> getPayBill_byUserId_forPage(Map<String, Object> paramMap) {
		return sqlSession.selectList("payBill.select_payBill_byOmsUserId", paramMap);
	}

	@Override
	public List<PayBillEntity> getPayBill_byUserId_forRevenuePage(Map<String, Object> paramMap) {
		return sqlSession.selectList("payBill.select_payBill_byOmsUserId_forRevenuePage", paramMap);
	}

	@Override
	public PayBillTotalData selectTotalData(Map<String, Object> paramMap) {
		return sqlSession.selectOne("payBill.select_Total_Data", paramMap);
	}
	
	@Override
	public String verifyImportPayBill(List<PayBillWithFeeDetailEntity> payBills) {
		if (payBills.size() == 0)
			return "无账单数据，请先在Excel中维护账单信息。";

		//检查Excel表中，当月有无重复的数据
		Set<String> payBillBriefSet = new HashSet<String>(); //账单月份 + 小区名 + 楼栋 + 单元 + 房间号
		for (int i = 0; i < payBills.size(); i++) {
			if (!payBillBriefSet.add(payBills.get(i).getMonth() + payBills.get(i).toString())) {
				logger.info("the repeat data is: " + payBills.get(i).getMonth() + payBills.get(i).toString());
				String resultMsg = (payBills.get(i).getGroupBuildingName() + "的账单数据重复");
				payBills.clear();
				return resultMsg;
			}
		}

		int[] indexs = new int[payBills.size()]; //记录payBills中被删除情况，删除的指标上置1
		StringBuffer sb = new StringBuffer();
		sb.append("部分行未能导入，具体行如下：\\r\\r");
		List<String> paramBaseInfoList = new ArrayList<String>();//小区名 + 楼栋 + 单元 + 房间号
		for (int i = 0; i < payBills.size(); i++) {
			paramBaseInfoList.add(payBills.get(i).toString());
		}

		boolean isRemoveSomeOne = false;
		// step1: 根据账单的《小区名 + 楼栋 + 单元 + 房间号 》，去数据库中找看是否有对应的基本物业信息
		List<String> summaryInfoList = sqlSession.selectList("payBill.select_verifyInfo_for_ImportedPayBill_step1", paramBaseInfoList);
		Set<String> summarySet = new HashSet<String>();
		summarySet.addAll(summaryInfoList);

		for (int i = paramBaseInfoList.size() - 1; i >= 0; i--) {
			if (!summarySet.contains(paramBaseInfoList.get(i))) {
				//《小区名 + 楼栋 + 单元 + 房间号》 在数据库中不存在
				payBills.remove(i);//剔除不需要导入的
				indexs[i] = 1; //删除的指标上置1
				isRemoveSomeOne = true;
				sb.append(String.format("第" + (i + 5) + "行的 "));
				sb.append("《小区名 + 楼栋 + 单元 + 房间号》 信息不存在，请先维护基本信息\\r");
			}
		}
		if (payBills.size() == 0)
			return sb.toString();
		// step1:end

		//step2: 根据账单的《账单月份 + 小区名 + 楼栋 + 单元 + 房间号 》，去数据库中找看是否已经有账单
		List<String> paramYearMonthList = new ArrayList<String>();//《账单月份+小区名 + 楼栋 + 单元 + 房间号》
		for (int i = 0; i < payBills.size(); i++) {
			String summaryString = payBills.get(i).toString();
			summaryString = payBills.get(i).getYearFromMonthfiled() + payBills.get(i).getMonthFromMonthfiled() + summaryString;
			paramYearMonthList.add(summaryString);
		}
		summaryInfoList = sqlSession.selectList("payBill.select_verifyInfo_for_ImportedPayBill_step2", paramYearMonthList);
		summarySet.clear();
		summarySet.addAll(summaryInfoList);

		for (int i = paramYearMonthList.size() - 1; i >= 0; i--) {
			if (summarySet.contains(paramYearMonthList.get(i))) {
				//《账单月份+小区名 + 楼栋 + 单元 + 房间号》 在数据库中已存在
				for (int j = 0, k = -1; j < indexs.length; j++) {
					if (indexs[j] == 0) {//找未被删除的元素，即跳过被删除的元素
						k++;
					}
					if (k == i) {//找到导入数据所在行位置
						sb.append(String.format("第" + (j + 5) + "行的 "));
						sb.append("当月账单已存在 \\r");
						break;
					}
				}
				payBills.remove(i);//剔除不需要导入的
				isRemoveSomeOne = true;
			}
		}

		if (payBills.size() == 0)
			return sb.toString();
		// step2:end

		// step3: 对有效数据找到对应rrId 和 ppId，整理好数据，建立对应的房间账单
		paramBaseInfoList.clear(); //小区名 + 楼栋 + 单元 + 房间号 
		for (int i = 0; i < payBills.size(); i++) {
			paramBaseInfoList.add(payBills.get(i).toString());
		}

		// 《小区+楼栋+单元+房间号+“_”+房间ID+“_”+业主ID》
		summaryInfoList = sqlSession.selectList("payBill.select_verifyInfo_for_ImportedPayBill_step3", paramBaseInfoList);
		for (int i = 0; i < payBills.size(); i++) {
			for (int j = 0; j < summaryInfoList.size(); j++) {
				if (summaryInfoList.get(j).startsWith(payBills.get(i).toString()+"_")) {
					String[] strings = summaryInfoList.get(j).split("_");
					payBills.get(i).settRealRoomFId(new BigInteger(strings[4]));
					payBills.get(i).setPropertyProprietorId(strings[5]);
					
					{//syl-add-2015-2-14 10:14:32 增加折扣月份概念
						String propertyMonthChange = strings[6];
						if(propertyMonthChange!=null&&propertyMonthChange.trim().length()>0
								/*&&GroupBuildingDict.GroupBuilding_PropertyMonthChange.NextMonth.compareTo(Integer.valueOf(propertyMonthChange.trim()))==0*///syl-upd-2015-8-11 18:12:52 支持缴纳前一个月
							){
							String billMonth = payBills.get(i).getMonth();
//							String discountMonth = com.cnfantasia.server.business.pub.utils.DateUtil.getNextMonth(billMonth, com.cnfantasia.server.business.pub.utils.DateUtil.formatSecond.get(), -1);
							String discountMonth = com.cnfantasia.server.business.pub.utils.DateUtil.getNextMonth(billMonth, com.cnfantasia.server.business.pub.utils.DateUtil.formatSecond.get(), -1*Integer.valueOf(propertyMonthChange.trim()));
							payBills.get(i).setBillMonth(billMonth);
							payBills.get(i).setMonth(discountMonth);//折扣月份-1
						}else{
							String billMonth = payBills.get(i).getMonth();
							payBills.get(i).setBillMonth(billMonth);
							payBills.get(i).setMonth(billMonth);//折扣月份和物业月份相同
						}
					}
					break;
				}
			}
		}
		// step3:end

		//去除账单金额为零的账单信息
		int zeroCount = 0;
		Iterator<PayBillWithFeeDetailEntity> payBill = payBills.iterator();
		while (payBill.hasNext()) {
			PayBillWithFeeDetailEntity bill = payBill.next();
			if (bill.getAmount() == null || (bill.getAmount() != null && bill.getAmount() == 0)) {
				payBill.remove();
				zeroCount ++;
			}
		}

		if (!isRemoveSomeOne) {//全部是有效数据
			sb = new StringBuffer("全部成功导入。");
		}

		if (zeroCount > 0) {//存在账单金额为零的账单
			sb = new StringBuffer("共计"+zeroCount+"条合计为0的账单，不进行导入");
		}

		return sb.toString();
	}

	@Override
	public List<PayBillWithFeeDetailEntity> getExportedPayBill(List<BigInteger> payBillIdList,boolean isRevenue,HashMap<String, Object> paramMap) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("payBillIdList", payBillIdList);
		tmpMap.put("isRevenue", isRevenue);
		tmpMap.put("isPay", paramMap.get("isPay"));
		tmpMap.put("financeStatus", paramMap.get("financeStatus"));
		return sqlSession.selectList("payBill.select_paybill_for_export", tmpMap);
	}

	@Override
	public List<PayBillWithFeeDetailEntity> getExportedPayBillFroReven(List<BigInteger> payBillIdList, boolean isRevenue, HashMap<String, Object> paramMap) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("payBillIdList", payBillIdList);
		tmpMap.put("isRevenue", isRevenue);
		tmpMap.put("isPay", paramMap.get("isPay"));
		tmpMap.put("financeStatus", paramMap.get("financeStatus"));
		return sqlSession.selectList("payBill.select_paybill_for_export_revenue", tmpMap);
	}

	@Override
	public PayBillWithFeeDetailEntity getPayBillForDetail(PayBill payBill) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("id", payBill.getId());
		tmpMap.put("isPay", payBill.getIsPay());
		tmpMap.put("systemStartTime", CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.SYSTEMSTARTBILLTIME));
		return sqlSession.selectOne("payBill.select_paybill_for_detail", tmpMap);
	}

	@Override
	public List<HashMap<String, Object>> select_payBill_with_payRecord(String id) {
		return sqlSession.selectList("payBill.select_payBill_with_payRecord", id);
	}
	
	@Override
	public List<BillMrDetail> selectBillDetailForMr(BigInteger payBillId) {
		return sqlSession.selectList("payBill.select_bill_detail_with_mr", payBillId);
	}

	@Override
	public int markByManual(String id, BigInteger omsUserId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", omsUserId);
		paramMap.put("id", id);
		return sqlSession.update("payBill.markByManual", paramMap);
	}
	
	public com.cnfantasia.server.api.plotproperty.entity.PayBillEntity selectPayBillEntity(Map<String, Object> paramMap) {
		return sqlSession.selectOne("payBill.select_paybill_with_realRoom", paramMap);
	}
	
	public String getPropertyCompanyName(Long groupBuildingId) {
		return sqlSession.selectOne("payBill.getPropertyCompanyName", groupBuildingId);
	}
	
	public List<GroupBuilding> getGroupBuildingByOmsId (Map<String, Object> paramMap) {
		return sqlSession.selectList("select_group_building_by_omsId", paramMap);
	}
	
	@Override
	public PropertyGbAd selectConfigAdUrl(BigInteger gbId) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		return sqlSession.selectOne("payBill.select_Config_AdUrl", tmpMap);
	}

	@Override
	public String verifyImportPayBillPeriod(List<PayBillWithFeeDetailEntity> payBills) {
		if (payBills.size() == 0)
			return "无账单数据，请先在Excel中维护账单信息。";

		//检查Excel表中，当月有无重复的数据
		Set<String> payBillBriefSet = new HashSet<String>(); //账单月份 + 小区名 + 楼栋 + 单元 + 房间号
		for (int i = 0; i < payBills.size(); i++) {
			if (!payBillBriefSet.add(payBills.get(i).getMonth() + payBills.get(i).toString())) {
				logger.info("the repeat data is: " + payBills.get(i).toString());
				String resultMsg = (payBills.get(i).getGroupBuildingName() + "的账单数据重复");
				payBills.clear();
				return resultMsg;
			}
		}

		int[] indexs = new int[payBills.size()]; //记录payBills中被删除情况，删除的指标上置1
		StringBuffer sb = new StringBuffer();
		sb.append("部分行未能导入，具体行如下：\\r\\r");
		List<String> paramBaseInfoList = new ArrayList<String>();//小区名 + 楼栋 + 单元 + 房间号
		for (int i = 0; i < payBills.size(); i++) {
			paramBaseInfoList.add(payBills.get(i).toString());
		}

		boolean isRemoveSomeOne = false;
		// step1: 根据账单的《小区名 + 楼栋 + 单元 + 房间号 》，去数据库中找看是否有对应的基本物业信息
		List<String> summaryInfoList = sqlSession.selectList("payBill.select_verifyInfo_for_ImportedPayBill_step1", paramBaseInfoList);
		Set<String> summarySet = new HashSet<String>();
		summarySet.addAll(summaryInfoList);

		for (int i = paramBaseInfoList.size() - 1; i >= 0; i--) {
			if (!summarySet.contains(paramBaseInfoList.get(i))) {
				//《小区名 + 楼栋 + 单元 + 房间号》 在数据库中不存在
				payBills.remove(i);//剔除不需要导入的
				indexs[i] = 1; //删除的指标上置1
				isRemoveSomeOne = true;
				sb.append(String.format("第" + (i + 6) + "行的 "));
				sb.append("《小区名 + 楼栋 + 单元 + 房间号》 信息不存在，请先维护基本信息\\r");
			}
		}
		if (payBills.size() == 0)
			return sb.toString();
		// step1:end

		//step2: 根据账单的《账单开始日期 + 账单截止日期  + 小区名 + 楼栋 + 单元 + 房间号 》，去数据库中找看是否已经有账单
		List<String> paramSummaryList = new ArrayList<String>();
		for (int i = 0; i < payBills.size(); i++) {
			PayBillWithFeeDetailEntity payBillWithFeeDetailEntity = payBills.get(i);
			paramSummaryList.add(payBillWithFeeDetailEntity.getPeriodSummaryString());
		}

		summaryInfoList = sqlSession.selectList("payBill.select_verifyInfo_for_ImportedPayBill_period_step2", paramSummaryList);
		summarySet.clear();
		summarySet.addAll(summaryInfoList);

		for (int i = paramSummaryList.size() - 1; i >= 0; i--) {
			if (summarySet.contains(paramSummaryList.get(i))) {
				//《账单开始日期 + 账单截止日期 +小区名 + 楼栋 + 单元 + 房间号》 在数据库中已存在
				for (int j = 0, k = -1; j < indexs.length; j++) {
					if (indexs[j] == 0) {//找未被删除的元素，即跳过被删除的元素
						k++;
					}
					if (k == i) {//找到导入数据所在行位置
						sb.append(String.format("第" + (j + 6) + "行的 "));
						sb.append("周期账单已存在 \\r");
						break;
					}
				}
				payBills.remove(i);//剔除不需要导入的
				isRemoveSomeOne = true;
			}
		}

		if (payBills.size() == 0)
			return sb.toString();
		// step2:end

		// step3: 对有效数据找到对应rrId 和 ppId，整理好数据，建立对应的房间账单
		paramBaseInfoList.clear(); //小区名 + 楼栋 + 单元 + 房间号 
		for (int i = 0; i < payBills.size(); i++) {
			paramBaseInfoList.add(payBills.get(i).toString());
		}

		// 《小区+楼栋+单元+房间号+“_”+房间ID+“_”+业主ID》
		summaryInfoList = sqlSession.selectList("payBill.select_verifyInfo_for_ImportedPayBill_step3", paramBaseInfoList);
		//summaryInfoList =  《小区+楼栋+单元+房间号+业主名称+“_”+房间ID+“_”+业主ID +“_”+物业账单提前月份数 》
		//summaryInfoList = concat(GB.f_name,'_',B.f_name,'_',IFNULL(RR.f_unit_name,''),'_',RR.f_num_tail, "_", RR.f_id, "_", PP.f_id, "_", case when not ISNULL(GB.f_property_month_change) then GB.f_property_month_change else 0 end)
		for (int i = 0; i < payBills.size(); i++) {
			for (int j = 0; j < summaryInfoList.size(); j++) {
				if (summaryInfoList.get(j).startsWith(payBills.get(i).toString()+"_")) {
					String[] strings = summaryInfoList.get(j).split("_");
					payBills.get(i).settRealRoomFId(new BigInteger(strings[4]));
					payBills.get(i).setPropertyProprietorId(strings[5]);
					break;
				}
			}
		}
		// step3:end

		//去除账单金额为零的账单信息
		int zeroCount = 0;
		Iterator<PayBillWithFeeDetailEntity> payBill = payBills.iterator();
		while (payBill.hasNext()) {
			PayBillWithFeeDetailEntity bill = payBill.next();
			if (bill.getAmount() == null || (bill.getAmount() != null && bill.getAmount() == 0)) {
				payBill.remove();
				zeroCount ++;
			}
		}

		if (!isRemoveSomeOne) {//全部是有效数据
			sb = new StringBuffer("全部成功导入。");
		}

		if (zeroCount > 0) {//存在账单金额为零的账单
			sb = new StringBuffer("共计"+zeroCount+"条合计为0的账单，不进行导入");
		}

		return sb.toString();
	}

	@Override
	public int selectCountCrossTimeConfig(PayBillTimeCfg payBillTimeCfg) {
		return sqlSession.selectOne("payBill.selectCountCrossTimeConfig", payBillTimeCfg);
	}

	@Override
	public List<PayBillPeriod4Export> getExportedPayBillPeriod(List<BigInteger> payBillIdList) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("payBillIdList", payBillIdList);
		return sqlSession.selectList("payBill.select_paybill_period_for_export", tmpMap);
	}

	@Override
	public List<PayBillWithFeeDetailEntity> getExportedPayBillWy(List<BigInteger> payBillIdList, boolean isRevenue) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("payBillIdList", payBillIdList);
		tmpMap.put("isRevenue", isRevenue);
		return sqlSession.selectList("payBill.select_paybill_for_export_wy", tmpMap);
	}
	
	@Override
	public int getPayBillByGbIdForCount(BigInteger gbId, BigInteger id) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("isPay", 1);//缴费完成
		tmpMap.put("billCycleId", id);//缴费完成
		return sqlSession.selectOne("payBill.select_payBill_byGbId_count", tmpMap);
	}

	@Override
	public int updatePayBillBillMonth(HashMap<String, Object> updateMap) {
		return sqlSession.update("payBill.update_payBill_by_billCycleId", updateMap);
	}

	@Override
	public int delPayBill(BigInteger billId, BigInteger delUser) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("billId", billId);
		tmpMap.put("delUser", delUser);
		tmpMap.put("delTime", DateUtils.getCurrentDate());
		return sqlSession.update("payBill.delete_payBill_by_billId", tmpMap);
	}

	@Override
	public List<Map<String, Object>> getGroupbuildingAvgAmount(HashMap<String, Object> paraMap) {
		return sqlSession.selectList("payBill.select_paybillAvg_for_groupBuilding", paraMap);
	}

	@Override
	public int insertAlterPeriodPayBill(PayBill payBill) {
		return sqlSession.insert("payBill.insert_payBill",payBill);
	}

	@Override
	public int updateBillCycle(BigInteger gbId, BigInteger payBillTypeId, int operateStatus) {
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("gbId",gbId);
		paraMap.put("billTypeId",payBillTypeId);
		paraMap.put("operateStatus",operateStatus);
		return sqlSession.update("billcycle.freeze_billcycle_by_type_id", paraMap);
	}

	@Override
	public List<BigInteger> getGroupBuildingBillCycleByTypeId(BigInteger gbId, BigInteger payBillTypeId) {
		Map<String,Object> paraMap = new HashMap<String, Object>();
		paraMap.put("gbId",gbId);
		paraMap.put("payBillTypeId",payBillTypeId);
		return sqlSession.selectList("billcycle.getGroupBuildingBillCycleByTypeId", paraMap);
	}

	@Override
	public int updatePayBillByCycleIds(List<BigInteger> billCycleIds) {
		return sqlSession.update("billcycle.updatePayBillByCycleIds", billCycleIds);
	}

	@Override
	public List<BigInteger> getCompayIdUserManageByOmsUser(Map<String, Object> paraMap) {
		return sqlSession.selectList("payBill.getCompayIdUserManageByOmsUser", paraMap);
	}

	@Override
	public List<BigInteger> filterNotSendPayBillMsgUser(List<BigInteger> userIdList) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userIdList", userIdList);
		return sqlSession.selectList("payBill.filterNotSendPayBillMsgUser", param);
	}
	
	public List<MsgForPaybill> getMsgForPaybillEnd() {
		return sqlSession.selectList("payBill.getMsgForPaybillEnd");
	}

	public List<MsgForPaybill> getMsgForPaybillStart() {
		return sqlSession.selectList("payBill.getMsgForPaybillStart");
	}

	public List<MsgForPaybill> getMsgForPaybillStartUnRegister() {
		return sqlSession.selectList("payBill.getMsgForPaybillStartUnRegister");
	}

	public List<MsgForPaybill> getMsgForPaybillBeforeBank() {
		return sqlSession.selectList("payBill.getMsgForPaybillBeforeBank");
	}

	public List<MsgForPaybill> getMsgForPaybillAfterBank() {
		return sqlSession.selectList("payBill.getMsgForPaybillAfterBank");
	}
	
	public List<MsgForPaybill> getMsgForPaybillAfterBankSuccess() {
		return sqlSession.selectList("payBill.getMsgForPaybillAfterBankSuccess");
	}

	@Override
	public List<ExportBillDetailHead> selectFeeDetailNameByGbId(BigInteger gbId, Integer feeType, List<BigInteger> billIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("gbId", gbId);
		param.put("feeType", feeType);
		param.put("billIds", billIds);
		return sqlSession.selectList("payBill.selectFeeDetailNameByGbId", param);
	}

	@Override
	public int insertPayBillAllCum(PayBill payBill) {
		return sqlSession.insert("payBill.insert_payBill_AllCum",payBill);
	}

	@Override
	public Long getUnpaidAmtById(BigInteger id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return sqlSession.selectOne("payBill.getUnpaidAmtById", param);
	}

	@Override
	public List<UnPaidPayBillEntity> getUnpaidListById(BigInteger payBillId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", payBillId);
		return sqlSession.selectList("payBill.getUnpaidListById", param);
	}

	@Override
	public Map<String, BigDecimal> getPayBillsAmounts(Map<String, Object> paramMap) {
		Map<String, Object> amtMap1 = sqlSession.selectOne("payBill.getPayBillsAmounts", paramMap);
		/*
		 * 修复Bug：
		 * java.lang.ClassCastException: java.lang.Double cannot be cast to java.math.BigDecimal
			at com.cnfantasia.server.ms.payBill.web.PayBillController.listPeriodPayBillView(PayBillController.java:2154)
		 */
		Map<String, BigDecimal> amtMap = new HashMap<String, BigDecimal>();
		if (amtMap1 != null) {
			for (Map.Entry<String, Object> entry : amtMap1.entrySet()) {
				amtMap.put(entry.getKey(), entry.getValue() == null ? null : new BigDecimal(entry.getValue() + ""));
			}
		}
		
		return amtMap;
	}

	@Override
	public List<Map<String, Object>> getNeedFixedUpdate(BigInteger payBillId) {
		return sqlSession.selectList("fixedFeeCfg.getNeedAlterUpdate", payBillId);
	}

	@Override
	public int isBankCollectionByPayBillId(BigInteger payBillId) {
		return sqlSession.selectOne("payBill.isBankCollectionByPayBillId", payBillId);
	}
}
