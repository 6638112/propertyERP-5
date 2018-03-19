package com.cnfantasia.server.api.meterReading.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.meterReading.entity.MrFeeItemWithFormula;
import com.cnfantasia.server.api.meterReading.entity.RealRoomHasMrLastRecordEntity;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.ms.groupBuildingBillCycle.entity.GroupBuildingBillCycleEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailWithMrRecordEntity;

public class MeterReadingDao extends AbstractBaseDao {
	private Log logger = LogFactory.getLog(getClass());
	
	public List<GroupBuilding> selectGroupBuildingForList(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("meterReading.select_groupBuilding_forList", paramMap);
	}

	public int selectGroupBuildingForCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "meterReading.select_groupBuilding_forList", paramMap);
	}

	public int queryBillCycleCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "billcycle.select_billcycle_forList", paramMap);
	}

	public List<GroupBuildingBillCycleEntity> queryBillCycleForList(Map<String, Object> paramMap) {
		return sqlSession.selectList("billcycle.select_billcycle_forList", paramMap);
	}

	public List<MrFeeItemWithFormula> getMrFeeItemWithFormulaByGbId(String gbId) {
		return sqlSession.selectList("meterReading.selectMrFeeItemWithFormula", gbId);
	}

	public MrFeeItemWithFormula getMrFeeItemWithFormulaForPayBillEdit(BigInteger propertyFeeDetailId) {
		return sqlSession.selectOne("meterReading.selectMrFeeItemWithFormulaForPayBillEdit", propertyFeeDetailId);
	}

	public List<RealRoomHasMrLastRecordEntity> getRealRoomLastRecordByGbId(String gbId) {
		return sqlSession.selectList("meterReading.selectRealRoomHasMrLastRecord", gbId);
	}

	public String verifyImportPayBill(List<PayBillWithFeeDetailWithMrRecordEntity> payBills, List<RealRoomHasMrLastRecordEntity> realRoomLastRecordAddNewList) {
		if (payBills.size() == 0)
			return "无账单数据，请先在Excel中维护账单信息。";

		//检查Excel表中，当月有无重复的数据
		Set<String> payBillBriefSet = new HashSet<String>(); //账单月份 + 小区名 + 楼栋 + 单元 + 房间号
		for (int i = 0; i < payBills.size(); i++) {
			if (!payBillBriefSet.add(payBills.get(i).getMonth() + payBills.get(i).toString())) {
				logger.info("the repeat data is: " + payBills.get(i).toString());
				String resultMsg = (payBills.get(i).toString() + "的账单数据重复");
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
				sb.append(String.format("第" + (i + 4) + "行的 "));
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
						sb.append(String.format("第" + (j + 4) + "行的 "));
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
		//summaryInfoList =  《小区+楼栋+单元+房间号+“_”+房间ID+“_”+业主ID +“_”+物业账单提前月份数 》
		//summaryInfoList = concat(GB.f_name,'_',B.f_name,'_',IFNULL(RR.f_unit_name,''),'_',RR.f_num_tail, "_", RR.f_id, "_", PP.f_id, "_", case when not ISNULL(GB.f_property_month_change) then GB.f_property_month_change else 0 end)
		for (int i = 0; i < payBills.size(); i++) {
			for (int j = 0; j < summaryInfoList.size(); j++) {
				PayBillWithFeeDetailEntity payBillWithFeeDetailEntity = payBills.get(i);
				if (summaryInfoList.get(j).startsWith(payBillWithFeeDetailEntity.toString()+"_")) {
					String[] strings = summaryInfoList.get(j).split("_");
					payBillWithFeeDetailEntity.settRealRoomFId(new BigInteger(strings[4]));
					payBillWithFeeDetailEntity.setPropertyProprietorId(strings[5]);
					for(int k = 0; k< realRoomLastRecordAddNewList.size();k++){//绑定上期读数的关系
						String payBillFullString = payBillWithFeeDetailEntity.getGroupBuildingName() +"_"+ payBillWithFeeDetailEntity.getBuildingName() +"_" + payBillWithFeeDetailEntity.getRealRoomUnitName()+"_" + payBillWithFeeDetailEntity.getRealRoomNum();
						if(realRoomLastRecordAddNewList.get(k).getFullName().equals(payBillFullString))
							realRoomLastRecordAddNewList.get(k).settRealRoomFId(payBillWithFeeDetailEntity.gettRealRoomFId());
					}
					break;
				}
			}
		}
		// step3:end

		//去除账单金额为零的账单信息
		int zeroCount = 0;
		Iterator<PayBillWithFeeDetailWithMrRecordEntity> payBill = payBills.iterator();
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

	public int deleteMrCalculateRulAndStandardByFeeItemId(Map<String, Object> paraMap) {
		return sqlSession.update("meterReading.deleteMrCalculateRulAndStandardByFeeItemId", paraMap);
	}
}
