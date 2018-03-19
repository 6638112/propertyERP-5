package com.cnfantasia.server.ms.payBill.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.domainbase.payBill.dao.PayBillBaseDao;
import com.cnfantasia.server.ms.payBill.entity.PayBillEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;
import com.cnfantasia.server.ms.pub.pagination.TotalCountGetter;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class PayBillDao extends PayBillBaseDao implements IPayBillDao {

	@Override
	public int getPayBill_byUserId_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "payBill.select_payBill_byOmsUserId", paramMap);
	}

	@Override
	public List<PayBillEntity> getPayBill_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("payBill.select_payBill_byOmsUserId", paramMap);
	}

	@Override
	public String vierfyImportPayBill(List<PayBillWithFeeDetailEntity> payBills) {
		if (payBills.size() == 0)
			return "无账单数据，请先在Excel中维护账单信息。";

		int[] indexs = new int[payBills.size()]; //记录payBills中被删除情况，删除的指标上置1
		StringBuffer sb = new StringBuffer();
		sb.append("部分行未能导入，具体行如下：\\r\\r");
		List<String> paramBaseInfoList = new ArrayList<String>();//小区名 + 楼栋 + 单元 + 房间号 + 业主姓名
		for (int i = 0; i < payBills.size(); i++) {
			paramBaseInfoList.add(payBills.get(i).toString());
		}

		boolean isRemoveSomeOne = false;
		// step1: 根据账单的《小区名 + 楼栋 + 单元 + 房间号 + 业主姓名》，去数据库中找看是否有对应的基本物业信息
		List<String> summaryInfoList = sqlSession.selectList("payBill.select_verifyInfo_for_ImportedPayBill_step1", paramBaseInfoList);
		Set<String> summarySet = new HashSet<String>();
		summarySet.addAll(summaryInfoList);

		for (int i = paramBaseInfoList.size() - 1; i >= 0; i--) {
			if (!summarySet.contains(paramBaseInfoList.get(i))) {
				//《小区名 + 楼栋 + 单元 + 房间号 + 业主姓名》 在数据库中不存在
				payBills.remove(i);//剔除不需要导入的
				indexs[i] = 1; //删除的指标上置1
				isRemoveSomeOne = true;
				sb.append(String.format("第" + (i + 5) + "行的 "));
				sb.append("《小区名 + 楼栋 + 单元 + 房间号 + 业主姓名》 信息不存在，请先维护基本信息\\r");
			}
		}
		if (payBills.size() == 0)
			return sb.toString();
		// step1:end

		//step2: 根据账单的《账单月份 + 小区名 + 楼栋 + 单元 + 房间号 + 业主姓名》，去数据库中找看是否已经有账单
		List<String> paramYearMonthList = new ArrayList<String>();//《账单月份+小区名 + 楼栋 + 单元 + 房间号 + 业主姓名》
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
				//《账单月份+小区名 + 楼栋 + 单元 + 房间号 + 业主姓名》 在数据库中已存在
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

		// step3: 对有效数据找到对应rrId 和 ppId，整理好数据，建立对应的房间业主账单
		paramBaseInfoList.clear(); //小区名 + 楼栋 + 单元 + 房间号 + 业主姓名
		for (int i = 0; i < payBills.size(); i++) {
			paramBaseInfoList.add(payBills.get(i).toString());
		}
		summaryInfoList = sqlSession.selectList("payBill.select_verifyInfo_for_ImportedPayBill_step3", paramBaseInfoList);
		for (int i = 0; i < payBills.size(); i++) {
			for (int j = 0; j < summaryInfoList.size(); j++) {
				if (summaryInfoList.get(j).startsWith(payBills.get(i).toString())) {
					String[] strings = summaryInfoList.get(j).split("_");
					payBills.get(i).settRealRoomFId(new BigInteger(strings[1]));
					payBills.get(i).setPropertyProprietorId(strings[2]);
					break;
				}
			}
		}
		// step3:end

		if (!isRemoveSomeOne) {//全部是有效数据
			sb = new StringBuffer("全部成功导入。");
		}

		return sb.toString();
	}

	@Override
	public List<PayBillWithFeeDetailEntity> getExportedPayBill(List<BigInteger> payBillIdList) {
		return sqlSession.selectList("payBill.select_paybill_for_export", payBillIdList);
	}

	@Override
	public PayBillWithFeeDetailEntity getPayBillForDetail(BigInteger id) {
		return sqlSession.selectOne("payBill.select_paybill_for_detail", id);
	}

	@Override
	public List<HashMap<String, Object>> select_payBill_with_payRecord(String id) {
		return sqlSession.selectList("payBill.select_payBill_with_payRecord", id);
	}

	@Override
	public int markByManual(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		paramMap.put("id", id);
		return sqlSession.update("payBill.markByManual", paramMap);
	}
}
