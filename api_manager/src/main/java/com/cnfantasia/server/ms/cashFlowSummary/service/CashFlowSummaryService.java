package com.cnfantasia.server.ms.cashFlowSummary.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cnfantasia.server.ms.cashFlowSummary.dao.ICashFlowSummaryDao;
import com.cnfantasia.server.ms.cashFlowSummary.entity.CashFlowSummaryDto;

/**
 * 现金流量汇总
 * 
 * @author liyulin
 * @version 1.0 2016年7月15日 下午2:27:54
 */
public class CashFlowSummaryService implements ICashFlowSummaryService {
	
	private ICashFlowSummaryDao cashFlowSummaryDao;

	public void setCashFlowSummaryDao(ICashFlowSummaryDao cashFlowSummaryDao) {
		this.cashFlowSummaryDao = cashFlowSummaryDao;
	}

	/**
	 * 查询现金流量汇总记录条数
	 * 
	 * @param paramMap
	 * @return int
	 */
	@Override
	public int selectCashFlowSummaryForCount(Map<String, Object> paramMap) {
		return cashFlowSummaryDao.selectCashFlowSummaryForCount(paramMap);
	}

	/**
	 * 查询现金流量汇总
	 * 
	 * @param paramMap
	 * @return List<CashFlowSummaryDto>
	 */
	@Override
	public List<CashFlowSummaryDto> selectCashFlowSummaryForList(Map<String, Object> paramMap) {
		return cashFlowSummaryDao.selectCashFlowSummaryForList(paramMap);
	}

	/**
	 * 查询现金流量汇总的总金额
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public BigDecimal selectCashFlowSummaryForTotalAmount(Map<String, Object> paramMap) {
		return cashFlowSummaryDao.selectCashFlowSummaryForTotalAmount(paramMap);
	}
	
	/**
	 * 导出现金流量汇总
	 * 
	 * @param paramMap
	 * @return HSSFWorkbook
	 */
	@Override
	public HSSFWorkbook exportCashFlowSummary(Map<String, Object> paramMap) {
		List<CashFlowSummaryDto> exportList = cashFlowSummaryDao.selectCashFlowSummaryForList(paramMap);
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("门牌验证缴费情况查询表");
		HSSFFont font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// head加粗
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置字体
		style.setFont(font);
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		// 设定行的高度
		row.setHeight((short)300);
		if(paramMap.get("summaryType")==null || paramMap.get("summaryType").toString().trim().length()==0){// 导出所有
			exportCashFlowSummaryWithAll(sheet, exportList, style, font, row);
		} else if("1".equals(paramMap.get("summaryType").toString())){// 按小区汇总
			exportCashFlowSummaryWithGB(sheet, exportList, style, font, row);
		} else if("2".equals(paramMap.get("summaryType").toString())){// 按门牌汇总
			exportCashFlowSummaryWithRoom(sheet, exportList, style, font, row);
		}
		
		return wb;
	}
	
	/**
	 * 默认汇总，导出所有
	 * 
	 * @param sheet
	 * @param exportList
	 * @param style
	 * @param font
	 * @param row
	 */
	private void exportCashFlowSummaryWithAll(HSSFSheet sheet, List<CashFlowSummaryDto> exportList, HSSFCellStyle style, HSSFFont font, HSSFRow row){
		String[] sheetTitle = {"所在省", "所在市", "物业公司", "代理商", "小区名称", "楼栋号", "单元", "房间号", "认证状态", "业务类型", "金额", "时间"};
		int[]    cellWidth  = {4000,   5000,  10000,   10000,  12000,   4000,  4000,  4000,  3000,    4000,    3000, 4000};
		// title row
		for (int i = 0; i < sheetTitle.length; i++) {
			// 设置excel每列宽度
			sheet.setColumnWidth(i, cellWidth[i]);
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(sheetTitle[i]);
		}
		// content row
		if(exportList!=null && exportList.size()>0){
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			style.setFont(font);
			DecimalFormat df = new DecimalFormat("0.00");
			BigDecimal percent = new BigDecimal("100");
			for(int i=0, size=exportList.size(); i<size; i++){
				CashFlowSummaryDto bean = exportList.get(i);
				row = sheet.createRow((i+1));
				row.setHeight((short)400);
				for(int k=0, cellSize=sheetTitle.length; k<cellSize; k++){
					sheet.setColumnWidth(k, cellWidth[k]);
					HSSFCell cell = row.createCell(k);
					cell.setCellStyle(style);
					switch (k) {
						case 0:
							cell.setCellValue(bean.getProvinceName());
							break;
						case 1:
							cell.setCellValue(bean.getCityName());
							break;
						case 2:
							cell.setCellValue(bean.getPcName());
							break;
						case 3:
							cell.setCellValue(bean.getCpCompanyName());
							break;
						case 4:
							cell.setCellValue(bean.getGbName());
							break;
						case 5:
							cell.setCellValue(bean.getBuildingName());
							break;
						case 6:
							cell.setCellValue(bean.getUnit());
							break;
						case 7:
							cell.setCellValue(bean.getRoomNum());
							break;
						case 8:
							if(bean.getVerifyState()!=null && bean.getVerifyState()==5){
								cell.setCellValue("已认证");
							} else {
								cell.setCellValue("未认证");
							}
							break;
						case 9:
							cell.setCellValue(getBillType(bean.getBillType()));
							break;
						case 10:
							BigDecimal amount = new BigDecimal(bean.getAmount());
							cell.setCellValue(df.format(amount.divide(percent)));
							break;
						case 11:
							cell.setCellValue(bean.getBillTime());
							break;
	
						default:
							cell.setCellValue("");
					}
				}
			}
		}
	}
	
	/**
	 * 按小区汇总
	 * 
	 * @param sheet
	 * @param exportList
	 * @param style
	 * @param font
	 * @param row
	 */
	private void exportCashFlowSummaryWithGB(HSSFSheet sheet, List<CashFlowSummaryDto> exportList, HSSFCellStyle style, HSSFFont font, HSSFRow row){
		String[] sheetTitle = {"物业公司", "代理商", "小区名称", "业务类型", "订单数", "金额"};
		int[]    cellWidth  = {10000,   10000,  12000,  4000,    3000,   3000};
		// title row
		for (int i = 0; i < sheetTitle.length; i++) {
			// 设置excel每列宽度
			sheet.setColumnWidth(i, cellWidth[i]);
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(sheetTitle[i]);
		}
		// content row
		if(exportList!=null && exportList.size()>0){
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			style.setFont(font);
			DecimalFormat df = new DecimalFormat("0.00");
			BigDecimal percent = new BigDecimal("100");
			for(int i=0, size=exportList.size(); i<size; i++){
				CashFlowSummaryDto bean = exportList.get(i);
				row = sheet.createRow((i+1));
				row.setHeight((short)400);
				for(int k=0, cellSize=sheetTitle.length; k<cellSize; k++){
					sheet.setColumnWidth(k, cellWidth[k]);
					HSSFCell cell = row.createCell(k);
					cell.setCellStyle(style);
					switch (k) {
						case 0:
							cell.setCellValue(bean.getPcName());
							break;
						case 1:
							cell.setCellValue(bean.getCpCompanyName());
							break;
						case 2:
							cell.setCellValue(bean.getGbName());
							break;
						case 3:
							cell.setCellValue(getBillType(bean.getBillType()));
							break;
						case 4:
							cell.setCellValue(bean.getOrderNum());
							break;
						case 5:
							BigDecimal amount = new BigDecimal(bean.getAmount());
							cell.setCellValue(df.format(amount.divide(percent)));
							break;
						default:
							cell.setCellValue("");
					}
				}
			}
		}
	}
	
	/**
	 * 按门牌汇总
	 * 
	 * @param sheet
	 * @param exportList
	 * @param style
	 * @param font
	 * @param row
	 */
	private void exportCashFlowSummaryWithRoom(HSSFSheet sheet, List<CashFlowSummaryDto> exportList, HSSFCellStyle style, HSSFFont font, HSSFRow row){
		String[] sheetTitle = {"小区名称", "楼栋号", "单元", "房间号", "业务类型", "订单数", "金额"};
		int[]    cellWidth  = {12000,   4000,  4000,  4000,   4000,    3000,  3000};
		// title row
		for (int i = 0; i < sheetTitle.length; i++) {
			// 设置excel每列宽度
			sheet.setColumnWidth(i, cellWidth[i]);
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(sheetTitle[i]);
		}
		// content row
		if(exportList!=null && exportList.size()>0){
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			style.setFont(font);
			DecimalFormat df = new DecimalFormat("0.00");
			BigDecimal percent = new BigDecimal("100");
			for(int i=0, size=exportList.size(); i<size; i++){
				CashFlowSummaryDto bean = exportList.get(i);
				row = sheet.createRow((i+1));
				row.setHeight((short)400);
				for(int k=0, cellSize=sheetTitle.length; k<cellSize; k++){
					sheet.setColumnWidth(k, cellWidth[k]);
					HSSFCell cell = row.createCell(k);
					cell.setCellStyle(style);
					switch (k) {
						case 0:
							cell.setCellValue(bean.getGbName());
							break;
						case 1:
							cell.setCellValue(bean.getBuildingName());
							break;
						case 2:
							cell.setCellValue(bean.getUnit());
							break;
						case 3:
							cell.setCellValue(bean.getRoomNum());
							break;
						case 4:
							cell.setCellValue(getBillType(bean.getBillType()));
							break;
						case 5:
							cell.setCellValue(bean.getOrderNum());
							break;
						case 6:
							BigDecimal amount = new BigDecimal(bean.getAmount());
							cell.setCellValue(df.format(amount.divide(percent)));
							break;
						default:
							cell.setCellValue("");
					}
				}
			}
		}
	}

	/**
	 * 获取账单类型
	 * 
	 * @param billType
	 * @return
	 */
	private String getBillType(Integer billType){
		switch (billType) {
			case 1:
				return "物业费";
			case 2:
				return "停车费";
			case 3:
				return "其他代收费用";
			case 4:
				return "楼下店";
			case 5:
				return "自营超市";
			case 6:
				return "上门维修";
			case 7:
				return "物业宝";
			case 8:
				return "停车宝";
			default:
				return "";
		}
	}
}
