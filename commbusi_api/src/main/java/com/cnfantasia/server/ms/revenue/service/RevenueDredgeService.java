package com.cnfantasia.server.ms.revenue.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.dao.ICommonLockDao;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.utils.PayUtils;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.domainbase.revenueSignalAmount.dao.IRevenueSignalAmountBaseDao;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.dao.IRevenueSignalAmountEbuyBaseDao;
import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity.RevenueSignalAmountEbuy;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.dao.RevenueDredgeDao;
import com.cnfantasia.server.ms.revenue.entity.DredgeBillRevenue;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;

public class RevenueDredgeService {
	
	private IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao;
	
	private IRevenueSignalAmountEbuyBaseDao revenueSignalAmountEbuyBaseDao;
	
	private ICommonLockDao commonLockDao;
	
	private RevenueDredgeDao revenueDredgeDao;
	
	private IRevenueDao revenueDao;
	
	public List<DredgeBillRevenue> updateAndGetDredgeRevenueList(Map<String, Object> paramMap) {
		commonLockDao.getLock(Lock.DREDGE_SYN_TASK_EXEC_TASK);
		revenueDredgeDao.updateForDredgeRevenue(paramMap);
		
		List<DredgeBillRevenue> dredgeBillRevenueList =  revenueDredgeDao.selectDredgeRevenueList(paramMap);
		
		paramMap.clear();
		paramMap.put("revenueStatusTo",  9);
		paramMap.put("revenueStatus",  1);
		revenueDredgeDao.updateDredgeRevenueStatus(paramMap);
		return dredgeBillRevenueList;
	}
	
	/**
	 * 一个师傅订单产生平台，物业，代理，师傅等几条收益
	 * @param revenueSignalAmountList
	 * @param revenueSignalAmountEbuyList
	 */
	public void saveRevenueEbuy(List<RevenueSignalAmount> revenueSignalAmountList, List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList) {
		revenueSignalAmountBaseDao.insertRevenueSignalAmountBatch(revenueSignalAmountList);
		revenueSignalAmountEbuyBaseDao.insertRevenueSignalAmountEbuyBatch(revenueSignalAmountEbuyList);
		
		if(revenueSignalAmountList.size() > 0) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("dredgeBillId", revenueSignalAmountList.get(0).getGoalId());
			paramMap.put("revenueStatusTo", 99);
			revenueDredgeDao.updateDredgeRevenueStatus(paramMap);
		}
	}
	
	public Integer updateDredgeRevenueStatus(Map<String, Object> paramMap) {
		return revenueDredgeDao.updateDredgeRevenueStatus(paramMap);
	}
	
	public RevenueConfig selectRevenueConfig(Map<String, Object> paramMap) {
		return revenueDredgeDao.selectRevenueConfig(paramMap);
	}
	
	public List<BigInteger> selectRevConfigCompanyIdList(Integer projectType,UserRole role) {
		return revenueDao.selectRevConfigCompanyIdList(projectType, role);
	}
	
	public List<BigInteger> selectRevConfigAgentIdList(Integer projectType,UserRole role) {
		return revenueDao.selectRevConfigAgentIdList(projectType, role);
	}
	
	public List<EbuyRevenueSignalAmount> selectDredgeRevenueSaList(Map<String, Object> paramMap) {
		return revenueDredgeDao.selectDredgeRevenueSaList(paramMap);
	}
	
	public int selectDredgeRevenueSaCount(Map<String, Object> paramMap) {
		return revenueDredgeDao.selectDredgeRevenueSaCount(paramMap);
	}
	
	public EbuyRevenueTotal selectDredgeRevenueTotal(Map<String, Object> paramMap) {
		return revenueDredgeDao.selectDredgeRevenueTotal(paramMap);
	}
	
	/**
	 * 导出功能
	 * @param request
	 * @return
	*/
	public HSSFWorkbook createReport(List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList) {
		if(ebuyRevenueSignalAmountList != null) {
			String fileServiceBasePath = CnfantasiaCommbusiUtil.getFileServiceBasePath();
			String filePath = fileServiceBasePath + ("docs/jfq_revenue_dredge_template.xls");
			FileInputStream fin = null;
			try {
				fin = new FileInputStream(new File(filePath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			HSSFWorkbook wb = null;
			try {
				wb = new HSSFWorkbook(fin);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HSSFSheet sheet = wb.getSheetAt(0);
			sheet.setForceFormulaRecalculation(true);
			HSSFCellStyle style = wb.createCellStyle();
			style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
			
			HSSFCellStyle styleStr = wb.createCellStyle();
			styleStr.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleStr.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleStr.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleStr.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleStr.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			HSSFCellStyle styleRate = wb.createCellStyle();
			styleRate.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
			styleRate.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			styleRate.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleRate.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleRate.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleRate.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleRate.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
			
			NumberFormat nf = new DecimalFormat("#0.00##");
			NumberFormat nf2 = new DecimalFormat("#0.00%");
			int count = 2;//标记到哪一行
			//double totalOrderAmount = 0.00;//订单总金额
			double totalAmountDiscount = 0.00;//优惠总金额
			double totalAmountOrderReal = 0.00;//支付总金额
			double totalAmountRepair = 0.00;//代收总金额
			double totalRedMoney = 0.00;//粮票补贴
			double totalAmountPlatform = 0.00;//平台总收入
			double totalAmountWy = 0.00;//物业总收入
			double totalAmountAgent = 0.00;//代理总收入
			double totalAmountRecommender = 0.00;//推荐人总收入
			double totalSrcMoney = 0.00;//费用金额
			
			for (int i = 0, j = 0; j < ebuyRevenueSignalAmountList.size(); j++) {
				HSSFRow row = sheet.createRow((j+2));
				EbuyRevenueSignalAmount bean = ebuyRevenueSignalAmountList.get(j);
				i = 0;
//				{"物业公司","代理商","小区名称","预约解放号","预约服务","粮票金额","支付金额","支付渠道","流水号", "订单完成时间", " 收益金额", "结算状态"}
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getPcName(),styleStr);
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getAgentName(),styleStr);
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getGroupBuildingName(),styleStr);
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getHuaId(),styleStr);
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getDredgeServiceName(),styleStr);
				
				fillCellData(row, i++, bean.getGoalDetailTypeStr(),styleStr);//人工费用类型
				fillCellData(row, i++, bean.getSrcMoney(),style);//费用金额
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getAmountDiscount(),style);//优惠金额
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getAmountOrderReal(),style);//支付金额--实际支付金额
				
				fillCellData(row, i++, bean.getRecommenderName(),styleStr);//推荐人手机
				fillCellData(row, i++, bean.getPayAmount()/100.00,style);//订单金额
				fillCellData(row, i++, PayUtils.getPayMethodStr(bean.getRevenueSignalAmountEbuy().getPayMethod()),styleStr);
				fillCellData(row, i++, bean.getPayFlowNo(),styleStr);
				fillCellData(row, i++, com.cnfantasia.server.common.utils.DateUtils.formatMinuteTime(bean.getPayTm()),styleStr);
				
				//"师傅收入","平台收入","与平台收入比（物业）", "物业收入", "与平台收入比（代理）","代理收入","与平台收入比（推荐人）", "推荐人收入"
				Double platfromProfit = bean.getSrcMoney() - bean.getAmountRepair();
				//if(实际支付>=平台收入)代收=实付-平台收入;粮票=订单金额-平台收入-代收  else 代收=0;粮票=订单金额-平台收入
				double amountRepair = 0.00;//代收金额
				double redMoney = 0.00;//粮票补贴
				if(bean.getRevenueSignalAmountEbuy().getAmountOrderReal() >= platfromProfit) {
					amountRepair = bean.getRevenueSignalAmountEbuy().getAmountOrderReal() - platfromProfit;
					redMoney = bean.getSrcMoney() - platfromProfit - amountRepair;
				} else {
					redMoney = bean.getSrcMoney() - platfromProfit;
				}
				fillCellData(row, i++, amountRepair,style);
				fillCellData(row, i++, redMoney,style);
				fillCellData(row, i++, platfromProfit == null ? 0.00 : platfromProfit,style);
				fillCellData(row, i++, bean.getAmountWy() == null || platfromProfit == 0.0 ? 0.0 : BigDecimalUtil.div(bean.getAmountWy(), platfromProfit, 4),styleRate);//"与平台收入比（物业）
				fillCellData(row, i++, bean.getAmountWy() == null ? 0.00 : bean.getAmountWy(),style); //"物业收入"
				fillCellData(row, i++, bean.getAmountAgent() == null || platfromProfit == 0.0 ? 0.0 : BigDecimalUtil.div(bean.getAmountAgent(), platfromProfit, 4),styleRate); //"与平台收入比（代理）
				fillCellData(row, i++, bean.getAmountAgent() == null ? 0.00 : bean.getAmountAgent(),style); //"代理收入"
				fillCellData(row, i++, bean.getAmountRecommender() == null || platfromProfit == 0.0 ? 0.0 : BigDecimalUtil.div(bean.getAmountRecommender(), platfromProfit, 4),styleRate); //"与平台收入比（推荐人）"
				fillCellData(row, i++, bean.getAmountRecommender() == null ? 0.00 : bean.getAmountRecommender(),style); //"推荐人收入"
				
				//totalOrderAmount += bean.getSrcMoney();
				totalAmountDiscount += bean.getRevenueSignalAmountEbuy().getAmountDiscount();
				totalAmountOrderReal += bean.getRevenueSignalAmountEbuy().getAmountOrderReal();
				totalAmountRepair += amountRepair;
				totalRedMoney += redMoney;
				totalAmountPlatform += platfromProfit == null ? 0.00 : platfromProfit;
				totalAmountWy += bean.getAmountWy() == null ? 0.00 : bean.getAmountWy();
				totalAmountAgent += bean.getAmountAgent() == null ? 0.00 : bean.getAmountAgent();
				totalAmountRecommender += bean.getAmountRecommender() == null ? 0.00 : bean.getAmountRecommender();
				totalSrcMoney += bean.getSrcMoney();
				count ++;
			}
			
			HSSFRow row = sheet.createRow((count));
			fillCellData(row, 0, "", styleStr);
			fillCellData(row, 1, "", styleStr);
			fillCellData(row, 2, "", styleStr);
			fillCellData(row, 3, "", styleStr);
			fillCellData(row, 4, "总计", styleStr);
			fillCellData(row, 5, "", styleStr);
			fillCellData(row, 6, nf.format(totalSrcMoney), style);
			fillCellData(row, 7, totalAmountDiscount,style);
			fillCellData(row, 8, nf.format(totalAmountOrderReal),style);
			fillCellData(row, 9, "",styleStr);
			fillCellData(row, 10, "",styleStr);
			fillCellData(row, 11, "", styleStr);
			fillCellData(row, 12, "", styleStr);
			fillCellData(row, 13, "", styleStr);
			fillCellData(row, 14, totalAmountRepair,style);
			fillCellData(row, 15, totalRedMoney,style);
			fillCellData(row, 16, totalAmountPlatform,style);
			fillCellData(row, 17, "",styleStr);
			fillCellData(row, 18, totalAmountWy,style);
			fillCellData(row, 19, "", styleStr);
			fillCellData(row, 20, totalAmountAgent, style);
			fillCellData(row, 21, "", styleStr);
			fillCellData(row, 22, totalAmountRecommender,style);
			
			return wb;
		}
		return null;
	}
	
	private void fillCellData(HSSFRow row,int index,String value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	private void fillCellData(HSSFRow row,int index,double value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	public void setRevenueSignalAmountBaseDao(
			IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao) {
		this.revenueSignalAmountBaseDao = revenueSignalAmountBaseDao;
	}

	public void setRevenueSignalAmountEbuyBaseDao(
			IRevenueSignalAmountEbuyBaseDao revenueSignalAmountEbuyBaseDao) {
		this.revenueSignalAmountEbuyBaseDao = revenueSignalAmountEbuyBaseDao;
	}

	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}

	public void setRevenueDredgeDao(RevenueDredgeDao revenueDredgeDao) {
		this.revenueDredgeDao = revenueDredgeDao;
	}

	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}

	public List<EbuyRevenueSignalAmount> selectDredgeRevenueSaManagementList(Map<String, Object> paramMap) {
		return revenueDredgeDao.selectDredgeRevenueSaManagementList(paramMap);
	}

	public int selectDredgeRevenueSaManagementCount(Map<String, Object> paramMap) {
		return revenueDredgeDao.selectDredgeRevenueSaManagementCount(paramMap);
	}

	public EbuyRevenueTotal selectDredgeRevenueManagementTotal(Map<String, Object> paramMap) {
		return revenueDredgeDao.selectDredgeRevenueManagementTotal(paramMap);
	}
}
