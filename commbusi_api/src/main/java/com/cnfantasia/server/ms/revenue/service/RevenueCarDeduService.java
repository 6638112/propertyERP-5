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
import com.cnfantasia.server.ms.revenue.dao.RevenueCarDeduDao;
import com.cnfantasia.server.ms.revenue.entity.CarDeduRevenue;
import com.cnfantasia.server.ms.revenue.entity.CarDeduRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
public class RevenueCarDeduService {
	
	private IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao;
	
	private ICommonLockDao commonLockDao;
	
	private RevenueCarDeduDao revenueCarDeduDao;
	
	private IRevenueGenerationBaseService revenueGenerationBaseService;
	
	public List<CarDeduRevenue> updateAndGetCarDeduRevenueList(Map<String, Object> paramMap) {
		commonLockDao.getLock(Lock.CAR_DEDU_SYN_TASK_EXEC_TASK);
		List<CarDeduRevenue> revenueList =  revenueCarDeduDao.selectRevenueList(paramMap);
		
		String currentDate = DateUtils.getCurrentDate();
		List<RevenueGeneration> revenueGenerationList = new ArrayList<RevenueGeneration>();
		List<BigInteger> updStatusList = new ArrayList<BigInteger>();
		Set<BigInteger> ids = new HashSet<BigInteger>();
		for(CarDeduRevenue revenue : revenueList) {
			if(!ids.contains(revenue.getGoalId())) {
				if(revenue.getRevenueStatus() == null) {
					RevenueGeneration revenueGeneration = new RevenueGeneration();
					revenueGeneration.setId(revenue.getGoalId());
					revenueGeneration.setType(3); //3类型表示停车宝抵扣
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
			revenueCarDeduDao.updateRevenueStatus(param);
		}
		
		return revenueList;
	}
	
	public Integer updateRevenueStatus(Map<String, Object> paramMap) {
		return revenueCarDeduDao.updateRevenueStatus(paramMap);
	} 
	
	/**
	 * @param revenueSignalAmountList
	 * @param revenueSignalAmountEbuyList
	 */
	public void saveRevenueFinance(List<RevenueSignalAmount> revenueSignalAmountList) {
		if(revenueSignalAmountList != null && revenueSignalAmountList.size() > 0) {
			revenueSignalAmountBaseDao.insertRevenueSignalAmountBatch(revenueSignalAmountList);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("goalId", revenueSignalAmountList.get(0).getGoalId());
			paramMap.put("revenueStatusTo", 99);
			revenueCarDeduDao.updateRevenueStatus(paramMap);
		}
	}
	
	/**
	 * 以模板的形式导出数据
	 * @return
	 */
	public HSSFWorkbook createReport(List<CarDeduRevenueSignalAmount> carDeduRevenueSignalAmountList) {
		if (carDeduRevenueSignalAmountList.size() > 0) {
			String fileServiceBasePath = CnfantasiaCommbusiUtil.getFileServiceBasePath();
			String filePath = fileServiceBasePath + ("docs/jfq_revenue_carFinance_dedu_template.xls");
			
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
			
			for (int i = 0, j = 0; j < carDeduRevenueSignalAmountList.size(); j++) {
				CarDeduRevenueSignalAmount bean = carDeduRevenueSignalAmountList.get(j);
				HSSFRow row = sheet.getRow(j + dataFromRowIndex);
				if (row == null) {
					row = sheet.createRow(j + dataFromRowIndex);
				}
				
				i = 0;
				
				fillCellData(row, i++, bean.getRoleName(), styleStr);
				fillCellData(row, i++, bean.getGbName(), styleStr);
				fillCellData(row, i++, bean.getCarNum(), styleStr);
				fillCellData(row, i++, "停车宝", styleStr);
				fillCellData(row, i++, bean.getOrderNo(), styleStr);
				fillCellData(row, i++, bean.getAmount(), style);
				fillCellData(row, i++, bean.getGoalRevTimeStr(), styleStr);
				
				totalAmouont += bean.getAmount();
				index ++;
			}
			
			HSSFRow row = sheet.createRow(index);
			int i = 0;
			fillCellData(row, i++, "", styleStr);
			fillCellData(row, i++, "", styleStr);
			fillCellData(row, i++, "总计", styleStr); //2总计
			fillCellData(row, i++, "", styleStr);
			fillCellData(row, i++, "", styleStr);
			fillCellData(row, i++, totalAmouont, style);
			fillCellData(row, i++, "", styleStr);
			
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
	
	public List<CarDeduRevenueSignalAmount> selectRevenueSaList(Map<String, Object> paramMap) {
		return revenueCarDeduDao.selectRevenueSaList(paramMap);
	}

	public Integer selectRevenueSaCount(Map<String, Object> paramMap) {
		return revenueCarDeduDao.selectRevenueSaCount(paramMap);
	}

	public EbuyRevenueTotal selectRevenueTotal(Map<String, Object> paramMap) {
		return revenueCarDeduDao.selectRevenueTotal(paramMap);
	}

	public void setRevenueSignalAmountBaseDao(
			IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao) {
		this.revenueSignalAmountBaseDao = revenueSignalAmountBaseDao;
	}

	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}

	public void setRevenueCarDeduDao(RevenueCarDeduDao revenueCarDeduDao) {
		this.revenueCarDeduDao = revenueCarDeduDao;
	}

	public void setRevenueGenerationBaseService(
			IRevenueGenerationBaseService revenueGenerationBaseService) {
		this.revenueGenerationBaseService = revenueGenerationBaseService;
	}
	
}
