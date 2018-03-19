package com.cnfantasia.server.ms.revenue.service;


import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.dao.ICommonLockDao;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.revenueGeneration.entity.RevenueGeneration;
import com.cnfantasia.server.domainbase.revenueGeneration.service.IRevenueGenerationBaseService;
import com.cnfantasia.server.domainbase.revenueSignalAmount.dao.IRevenueSignalAmountBaseDao;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.dao.RevenueCarDao;
import com.cnfantasia.server.ms.revenue.entity.CarRevenue;
import com.cnfantasia.server.ms.revenue.entity.CarRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class RevenueCarService {
	
	private IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao;
	
	private ICommonLockDao commonLockDao;
	
	private RevenueCarDao revenueCarDao;
	
	private IRevenueGenerationBaseService revenueGenerationBaseService;
	
	public List<CarRevenue> updateAndGetCarRevenueList(Map<String, Object> paramMap) {
		commonLockDao.getLock(Lock.CAR_SYN_TASK_EXEC_TASK);
		List<CarRevenue> carRevenueList =  revenueCarDao.selectCarRevenueList(paramMap);
		
		String currentDate = DateUtils.getCurrentDate();
		List<RevenueGeneration> revenueGenerationList = new ArrayList<RevenueGeneration>();
		List<BigInteger> updStatusList = new ArrayList<BigInteger>();
		for(CarRevenue carRevenue : carRevenueList) {
			if(carRevenue.getRevenueStatus() == null) {
				RevenueGeneration revenueGeneration = new RevenueGeneration();
				revenueGeneration.setId(carRevenue.getGoalId());
				revenueGeneration.setType(1); //1类型表示车禁
				revenueGeneration.setRevenueStatus(9); //9状态为正在处理中
				revenueGeneration.setRevenueTm(currentDate);
				revenueGenerationList.add(revenueGeneration);
			} else {
				updStatusList.add(carRevenue.getGoalId());
			}
		}
		
		if(revenueGenerationList.size() > 0) {
			revenueGenerationBaseService.insertRevenueGenerationBatch(revenueGenerationList);
		}
		if(updStatusList.size() > 0) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("revenueStatusTo", 9);
			param.put("updStatusList", updStatusList);
			revenueCarDao.updateCarRevenueStatus(param);
		}
		
		return carRevenueList;
	}
	
	public Integer updateCarRevenueStatus(Map<String, Object> paramMap) {
		return revenueCarDao.updateCarRevenueStatus(paramMap);
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
			revenueCarDao.updateCarRevenueStatus(paramMap);
		}
	}
	
	public List<CarRevenueSignalAmount> selectCarRevenueSaList(Map<String, Object> paramMap) {
		return revenueCarDao.selectCarRevenueSaList(paramMap);
	}
	
	public Integer selectCarRevenueSaCount(Map<String, Object> paramMap) {
		return revenueCarDao.selectCarRevenueSaCount(paramMap);
	}
	
	public EbuyRevenueTotal selectCarRevenueTotal(Map<String, Object> paramMap) {
		return revenueCarDao.selectCarRevenueTotal(paramMap);
	}
	
	/**
	 * 以模板的形式导出数据
	 * @return
	 */
	public HSSFWorkbook createReport(List<CarRevenueSignalAmount> carRevenueSignalAmountList) {
		if (carRevenueSignalAmountList.size() > 0) {
			String fileServiceBasePath = CnfantasiaCommbusiUtil.getFileServiceBasePath();
			String filePath = fileServiceBasePath + ("docs/jfq_revenue_car_template.xls");
			
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
			
			NumberFormat nf = new DecimalFormat("#0.00##");
			int index = 1;//标记到哪一行
			double totalAmount = 0.0, totalAmountUsrReal = 0.0, totalAmountPtbt = 0.0;
			for (int i = 0, j = 0; j < carRevenueSignalAmountList.size(); j++) {
				CarRevenueSignalAmount bean = carRevenueSignalAmountList.get(j);
				HSSFRow row = sheet.getRow(j + 1);
				if (row == null) {
					row = sheet.createRow(j + 1);
				}
				i = 0;
				
				fillCellData(row, i++, bean.getRoleName(), styleStr);
				fillCellData(row, i++, bean.getGbName(), styleStr);
				fillCellData(row, i++, bean.getBuilding(), styleStr);
				fillCellData(row, i++, bean.getUnitName(), styleStr);
				fillCellData(row, i++, bean.getRoom(), styleStr);
				fillCellData(row, i++, bean.getCarNum(), styleStr);
				fillCellData(row, i++, DateUtils.formatMinuteTime(bean.getPayTime()), styleStr);
				if(bean.getPayNum() != null && bean.getPayNum() == 12) {
					fillCellData(row, i++, "年卡车", styleStr);
				} else if(bean.getPayNum() != null && bean.getPayNum() == 0) {
					fillCellData(row, i++, "次缴车", styleStr);
				} else {
					fillCellData(row, i++, "月卡车", styleStr);
				}
				fillCellData(row, i++, (bean.getPayNum() == null || bean.getPayNum() == 0) ? "" : bean.getPayNum().toString() + "个月", styleStr);
				fillCellData(row, i++, nf.format(bean.getAmount()), style);
				fillCellData(row, i++, nf.format(bean.getAmountUsrReal()), style);
				fillCellData(row, i++, nf.format(bean.getAmountPtbt()), style);
				fillCellData(row, i++, bean.getPayMethodStr(), styleStr);
				fillCellData(row, i++, bean.getPayFlowNo(), styleStr);
				
				totalAmount        += bean.getAmount();
				totalAmountUsrReal += bean.getAmountUsrReal();
				totalAmountPtbt    += bean.getAmountPtbt();
				index ++; 
			}
			
			HSSFRow row = sheet.createRow(index);
			for (int i = 0; i < 8; i++) {
				fillCellData(row, i, "", styleStr);
			}
			fillCellData(row, 8, "总计", styleStr);
			fillCellData(row, 9, nf.format(totalAmount), style);
			fillCellData(row, 10, nf.format(totalAmountUsrReal), style);
			fillCellData(row, 11, nf.format(totalAmountPtbt), style);
			
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
	
	public void setRevenueSignalAmountBaseDao(
			IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao) {
		this.revenueSignalAmountBaseDao = revenueSignalAmountBaseDao;
	}

	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}

	public void setRevenueCarDao(RevenueCarDao revenueCarDao) {
		this.revenueCarDao = revenueCarDao;
	}

	public void setRevenueGenerationBaseService(
			IRevenueGenerationBaseService revenueGenerationBaseService) {
		this.revenueGenerationBaseService = revenueGenerationBaseService;
	}

	/**
	 * 车禁收益若直接付款到物业公司，更新状态为已结算
	 * @return
	 */
	public int updateCarRevnueSignalAmountToSettled() {
		return  revenueCarDao.updateCarRevnueSignalAmountToSettled();
	}
}
