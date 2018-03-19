package com.cnfantasia.server.ms.revenue.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.dao.ICommonLockDao;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.revenueGeneration.entity.RevenueGeneration;
import com.cnfantasia.server.domainbase.revenueGeneration.service.IRevenueGenerationBaseService;
import com.cnfantasia.server.domainbase.revenueSignalAmount.dao.IRevenueSignalAmountBaseDao;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.dao.RevenueFinanceDeduDao;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.FinanceDeduRevenue;
import com.cnfantasia.server.ms.revenue.entity.FinanceDeduRevenueSignalAmount;
public class RevenueFinanceDeduService {
	
	private IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao;
	
	private ICommonLockDao commonLockDao;
	
	private RevenueFinanceDeduDao revenueFinanceDeduDao;
	
	private IRevenueGenerationBaseService revenueGenerationBaseService;
	
	public List<FinanceDeduRevenue> updateAndGetFinanceDeduRevenueList(Map<String, Object> paramMap) {
		commonLockDao.getLock(Lock.FINACE_DEDU_SYN_TASK_EXEC_TASK);
		List<FinanceDeduRevenue> revenueList =  revenueFinanceDeduDao.selectRevenueList(paramMap);
		
		String currentDate = DateUtils.getCurrentDate();
		List<RevenueGeneration> revenueGenerationList = new ArrayList<RevenueGeneration>();
		List<BigInteger> updStatusList = new ArrayList<BigInteger>();
		Set<BigInteger> ids = new HashSet<BigInteger>();
		for(FinanceDeduRevenue revenue : revenueList) {
			if(!ids.contains(revenue.getGoalId())) {
				if(revenue.getRevenueStatus() == null) {
					RevenueGeneration revenueGeneration = new RevenueGeneration();
					revenueGeneration.setId(revenue.getGoalId());
					revenueGeneration.setType(2); //2类型表示物业表抵扣
					revenueGeneration.setRevenueStatus(9); //9状态为正在处理中
					revenueGeneration.setRevenueTm(currentDate);
					revenueGenerationList.add(revenueGeneration);
				} else {
					updStatusList.add(revenue.getGoalId());
				}
				ids.add(revenue.getGoalId());
			}
		}
		
		if(revenueGenerationList.size() > 0) {
			revenueGenerationBaseService.insertRevenueGenerationBatch(revenueGenerationList);
		}
		if(updStatusList.size() > 0) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("revenueStatusTo", 9);
			param.put("updStatusList", updStatusList);
			revenueFinanceDeduDao.updateRevenueStatus(param);
		}
		
		return revenueList;
	}
	
	public Integer updateRevenueStatus(Map<String, Object> paramMap) {
		return revenueFinanceDeduDao.updateRevenueStatus(paramMap);
	} 
	
	/**
	 * 一个师傅订单产生平台，物业，代理，师傅等几条收益
	 * @param revenueSignalAmountList
	 * @param revenueSignalAmountEbuyList
	 */
	public void saveRevenueFinance(RevenueSignalAmount revenueSignalAmount) {
		if(revenueSignalAmount != null) {
			revenueSignalAmountBaseDao.insertRevenueSignalAmount(revenueSignalAmount);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("goalId", revenueSignalAmount.getGoalId());
			paramMap.put("revenueStatusTo", 99);
			revenueFinanceDeduDao.updateRevenueStatus(paramMap);
		}
	}
	
	/**
	 * 以模板的形式导出数据
	 * @return
	 */
	public HSSFWorkbook createReport(List<FinanceDeduRevenueSignalAmount> financeDeduRevenueSignalAmountList) {
		if (financeDeduRevenueSignalAmountList.size() > 0) {
			
			String filePath = CnfantasiaCommbusiUtil.getFileServiceBasePath() + "docs/jfq_revenue_finance_dedu_template.xls";
			
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
			
			int dataFromRowIndex = 1; //从第2行开始是数据行1
			double totalAmouont = 0.00;
			int index = 1;//标记到哪一行
			
			for (int i = 0, j = 0; j < financeDeduRevenueSignalAmountList.size(); j++) {
				FinanceDeduRevenueSignalAmount bean = financeDeduRevenueSignalAmountList.get(j);
				HSSFRow row = sheet.getRow(j + dataFromRowIndex);
				if (row == null) {
					row = sheet.createRow(j + dataFromRowIndex);
				}
				
				i = 0;
				
				fillCellData(row, i++, bean.getRoleName(), styleStr);
				fillCellData(row, i++, bean.getGbName(), styleStr);
				fillCellData(row, i++, bean.getBuilding(), styleStr);
				fillCellData(row, i++, bean.getUnitName()==null?"":bean.getUnitName(), styleStr);
				fillCellData(row, i++, bean.getRoom(), styleStr);
				fillCellData(row, i++, bean.getFinanceType().equals("1")?"物业宝":"停车宝", styleStr);
				fillCellData(row, i++, bean.getOrderNo(), styleStr);
				fillCellData(row, i++, bean.getAmount(), style);
				fillCellData(row, i++, DateUtils.formatYearAndMonth(bean.getMonth()), styleStr);
				
				totalAmouont += bean.getAmount();
				index ++;
			}
			
			HSSFRow row = sheet.createRow(index);
			fillCellData(row, 0, "", styleStr);
			fillCellData(row, 1, "", styleStr);
			fillCellData(row, 2, "", styleStr);
			fillCellData(row, 3, "", styleStr);
			fillCellData(row, 4, "总计", styleStr);
			fillCellData(row, 5, "", styleStr);
			fillCellData(row, 6, "", styleStr);
			fillCellData(row, 7, totalAmouont, style);
			fillCellData(row, 8, "", styleStr);
			
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
	
	public List<FinanceDeduRevenueSignalAmount> selectRevenueSaList(Map<String, Object> paramMap) {
		return revenueFinanceDeduDao.selectRevenueSaList(paramMap);
	}

	public Integer selectRevenueSaCount(Map<String, Object> paramMap) {
		return revenueFinanceDeduDao.selectRevenueSaCount(paramMap);
	}

	public EbuyRevenueTotal selectRevenueTotal(Map<String, Object> paramMap) {
		return revenueFinanceDeduDao.selectRevenueTotal(paramMap);
	}

	public void setRevenueSignalAmountBaseDao(
			IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao) {
		this.revenueSignalAmountBaseDao = revenueSignalAmountBaseDao;
	}

	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}

	public void setRevenueFinanceDeduDao(RevenueFinanceDeduDao revenueFinanceDeduDao) {
		this.revenueFinanceDeduDao = revenueFinanceDeduDao;
	}

	public void setRevenueGenerationBaseService(
			IRevenueGenerationBaseService revenueGenerationBaseService) {
		this.revenueGenerationBaseService = revenueGenerationBaseService;
	}
	
}
