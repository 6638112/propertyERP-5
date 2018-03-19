package com.cnfantasia.server.ms.revenue.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
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
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.domainbase.revenueSignalAmount.dao.IRevenueSignalAmountBaseDao;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.dao.RevenueFinanceDao;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.FinanceRevenue;
import com.cnfantasia.server.ms.revenue.entity.FinanceRevenueSignalAmount;

public class RevenueFinanceService {
	
	private IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao;
	
	private ICommonLockDao commonLockDao;
	
	private RevenueFinanceDao revenueFinanceDao;
	
	private IRevenueDao revenueDao;
	
	public List<FinanceRevenue> updateAndGetFinanceRevenueList(Map<String, Object> paramMap) {
		commonLockDao.getLock(Lock.FINANCE_SYN_TASK_EXEC_TASK);
		revenueFinanceDao.updateForFinanceRevenue(paramMap);
		
		List<FinanceRevenue> financeRevenueList =  revenueFinanceDao.selectFinanceRevenueList(paramMap);
		
		//paramMap.clear();
		paramMap.put("revenueStatusTo",  9);
		paramMap.put("revenueStatus",  1);
		revenueFinanceDao.updateFinanceRevenueStatus(paramMap);
		return financeRevenueList;
	}
	
	/**
	 * 一个师傅订单产生平台，物业，代理，师傅等几条收益
	 * @param revenueSignalAmountList
	 * @param revenueSignalAmountEbuyList
	 */
	public void saveRevenueFinance(List<RevenueSignalAmount> revenueSignalAmountList) {
		if(revenueSignalAmountList.size() > 0) {
			revenueSignalAmountBaseDao.insertRevenueSignalAmountBatch(revenueSignalAmountList);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("financeReqId", revenueSignalAmountList.get(0).getGoalId());
			paramMap.put("revenueStatusTo", 99);
			revenueFinanceDao.updateFinanceRevenueStatus(paramMap);
		}
	}
	
	public void updateFinanceRevenueStatus(BigInteger goalId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("financeReqId", goalId);
		paramMap.put("revenueStatusTo", 100);
		revenueFinanceDao.updateFinanceRevenueStatus(paramMap);
	}
	
	/**
	 * 以模板的形式导出数据
	 * @return
	 */
	public HSSFWorkbook createReport(List<FinanceRevenueSignalAmount> financeRevenueSignalAmountList) {
		if (financeRevenueSignalAmountList.size() > 0) {
			String fileServiceBasePath = CnfantasiaCommbusiUtil.getFileServiceBasePath();
			int revenueProject = financeRevenueSignalAmountList.get(0).getGoalType();
			String filePath = "";
			if(revenueProject == RevenueDict.RevenueProject.WuyebaoAmount.intValue() ){
				filePath = fileServiceBasePath + ("docs/jfq_revenue_finance_template.xls");
			}else if(revenueProject == RevenueDict.RevenueProject.CarFinanceBaoAmout.intValue()){
				filePath = fileServiceBasePath + ("docs/jfq_revenue_carFinance_template.xls");
			}
			
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
			
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
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
			
			HSSFCellStyle styleRate = wb.createCellStyle();//百分比样式
			HSSFDataFormat format = wb.createDataFormat();  
			styleRate.setDataFormat(format.getFormat("0.00%"));
			styleRate.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			styleRate.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleRate.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleRate.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleRate.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			int index = 1;
			double totalBuyMoney = 0.00;//投资金额总计
			double totalAmountPlat = 0.00;//平台收入
			double totalAmountWy = 0.00;//物业收入
			double totalAmountAgent = 0.00;//代理收入
			
			for (int i = 0, j = 0; j < financeRevenueSignalAmountList.size(); j++) {
				FinanceRevenueSignalAmount bean = financeRevenueSignalAmountList.get(j);
				HSSFRow row = sheet.getRow(j + 1);
				if (row == null) {
					row = sheet.createRow(j + 1);
				}
				i = 0;
				
				fillCellData(row, i++, bean.getFinanceBuyEntity().getPropertyCompanyName(), styleStr);
				fillCellData(row, i++, bean.getFinanceBuyEntity().getChannelPartnerName(), styleStr);
				fillCellData(row, i++, bean.getFinanceBuyEntity().getFinanceReqEntity().getResidential(), styleStr);
				fillCellData(row, i++, bean.getFinanceBuyEntity().getFinanceReqEntity().getBuilding(), styleStr);
				if(bean.getGoalType() == RevenueDict.RevenueProject.WuyebaoAmount.intValue()){
					fillCellData(row, i++, bean.getFinanceBuyEntity().getFinanceReqEntity().getRoomNum(), styleStr);
					fillCellData(row, i++, "物业宝", styleStr);
				}else if(bean.getGoalType() == RevenueDict.RevenueProject.CarFinanceBaoAmout.intValue()){
					fillCellData(row, i++, bean.getFinanceBuyEntity().getCarNum(), styleStr);
					fillCellData(row, i++, "停车宝", styleStr);
				}

				fillCellData(row, i++, bean.getFinanceBuyEntity().getOrderNo(), styleStr);
				fillCellData(row, i++, BigDecimalUtil.roundForDouble(bean.getFinanceBuyEntity().getBuyMoney(), 2), style);
				double platfromProfit = convertDouble2double(bean.getAmountPlatform()) + convertDouble2double(bean.getAmountWy()) + convertDouble2double(bean.getAmountAgent());
				fillCellData(row, i++, platfromProfit, style);
				
				if((bean.getAmountWy() != 0.00 && bean.getAmountWy() != null) && (platfromProfit != 0.00)) {
					fillCellData(row, i++, BigDecimalUtil.div(bean.getAmountWy(), platfromProfit, 6), styleRate);
				} else {
					fillCellData(row, i++, 0.00, styleRate);
				}
				fillCellData(row, i++, bean.getAmountWy(), style);
				
				if((bean.getAmountAgent() != null && bean.getAmountAgent() != 0.00 ) && (platfromProfit != 0.00)) {
					fillCellData(row, i++, BigDecimalUtil.div(bean.getAmountAgent(), platfromProfit, 6), styleRate);
				} else {
					fillCellData(row, i++, 0.00, styleRate);
				}
				fillCellData(row, i++, convertDouble2double(bean.getAmountAgent()), style);
				
				totalAmountPlat += platfromProfit;
				totalBuyMoney += BigDecimalUtil.roundForDouble(bean.getFinanceBuyEntity().getBuyMoney(), 2);
				totalAmountWy += bean.getAmountWy();
				totalAmountAgent += convertDouble2double(bean.getAmountAgent());
				
				index ++;
			}
			
			//增加数据合计信息
			HSSFRow row = sheet.createRow((index));
			fillCellData(row, 0, "",styleStr);
			fillCellData(row, 1, "",styleStr);
			fillCellData(row, 2, "",styleStr);
			fillCellData(row, 3, "",styleStr);
			fillCellData(row, 4, "",styleStr);
			fillCellData(row, 5, "总计",styleStr);
			fillCellData(row, 6, "",styleStr);
			fillCellData(row, 7, totalBuyMoney,style);
			fillCellData(row, 8, totalAmountPlat,style);
			fillCellData(row, 9, "",styleStr);
			fillCellData(row, 10, totalAmountWy,style);
			fillCellData(row, 11, "",styleStr);
			fillCellData(row, 12, totalAmountAgent,style);
			
			return wb;
		}
		return null;
	}
	
	private void fillCellData(HSSFRow row,int index,String value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	private void fillCellData(HSSFRow row,int index,Double value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	public Integer updateFinanceRevenueStatus(Map<String, Object> paramMap) {
		return revenueFinanceDao.updateFinanceRevenueStatus(paramMap);
	}
	
	public RevenueConfig selectRevenueConfig(Map<String, Object> paramMap) {
		return revenueFinanceDao.selectRevenueConfig(paramMap);
	}
	
	public List<BigInteger> selectRevConfigCompanyIdList(Integer projectType,UserRole role) {
		return revenueDao.selectRevConfigCompanyIdList(projectType, role);
	}
	
	public List<BigInteger> selectRevConfigAgentIdList(Integer projectType,UserRole role) {
		return revenueDao.selectRevConfigAgentIdList(projectType, role);
	}
	
	public List<FinanceRevenueSignalAmount> selectFinanceRevenueSaList(Map<String, Object> paramMap) {
		return revenueFinanceDao.selectFinanceRevenueSaList(paramMap);
	}
	
	public int selectFinanceRevenueSaCount(Map<String, Object> paramMap) {
		return revenueFinanceDao.selectFinanceRevenueSaCount(paramMap);
	}
	
	public EbuyRevenueTotal selectFinanceRevenueTotal(Map<String, Object> paramMap) {
		return revenueFinanceDao.selectFinanceRevenueTotal(paramMap);
	}
	
	public void setRevenueSignalAmountBaseDao(
			IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao) {
		this.revenueSignalAmountBaseDao = revenueSignalAmountBaseDao;
	}

	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}

	public void setRevenueFinanceDao(RevenueFinanceDao revenueFinanceDao) {
		this.revenueFinanceDao = revenueFinanceDao;
	}

	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	private double convertDouble2double(Double d) {
		return d == null ? 0 : d;
	}
}
