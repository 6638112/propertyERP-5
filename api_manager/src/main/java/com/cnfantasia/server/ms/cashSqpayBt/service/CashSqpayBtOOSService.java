package com.cnfantasia.server.ms.cashSqpayBt.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cnfantasia.server.api.cashSqpayBt.constants.CashSqpayBtConstants;
import com.cnfantasia.server.api.cashSqpayBt.service.CashSqpayBtService;
import com.cnfantasia.server.ms.cashSqpayBt.dao.ICashSqpayBtOOSDao;
import com.cnfantasia.server.ms.cashSqpayBt.entity.CashSqpayBtDto;

/**
 * 双乾支付优惠补贴
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 下午8:44:40
 */
public class CashSqpayBtOOSService extends CashSqpayBtService implements ICashSqpayBtOOSService{
	
	private ICashSqpayBtOOSDao cashSqpayBtOOSDao;
	public void setCashSqpayBtOOSDao(ICashSqpayBtOOSDao cashSqpayBtOOSDao) {
		this.cashSqpayBtOOSDao = cashSqpayBtOOSDao;
	}

	/**
	 * 查询双乾支付优惠补贴记录条数
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public Integer selectCashSqpayBtCount(Map<String, Object> paramMap){
		return cashSqpayBtOOSDao.selectCashSqpayBtCount(paramMap);
	}
	
	/**
	 * 查询双乾支付优惠补贴记录
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<CashSqpayBtDto> selectCashSqpayBtList(Map<String, Object> paramMap){
		return cashSqpayBtOOSDao.selectCashSqpayBtList(paramMap);
	}
	
	/**
	 * 查询双乾支付优惠总补贴额
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public Long selectCashSqpayBtForTotalAmountBt(Map<String, Object> paramMap){
		return cashSqpayBtOOSDao.selectCashSqpayBtForTotalAmountBt(paramMap);
	}
	
	/**
	 * 导出双乾支付优惠补贴记录
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public HSSFWorkbook exportCashSqpayBts(Map<String, Object> paramMap){
		String[] sheetTitle = {"业务对象名称", "业务类型", "双乾实付金额（元）", "补贴金额（元）", "支付时间", "流水号"};
		int[]    cellWidth  = {10000,     5000,    5000,    5000,    4000,   10000};
		List<CashSqpayBtDto> exportList = cashSqpayBtOOSDao.selectCashSqpayBtList(paramMap);
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("双乾支付补贴明细");
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
			long totalAmount = 0L;
			long totalAmountBt = 0L;
			int i=0;
			for(int size=exportList.size(); i<size; i++){
				CashSqpayBtDto bean = exportList.get(i);
				row = sheet.createRow((i+1));
				row.setHeight((short)400);
				for(int k=0, cellSize=sheetTitle.length; k<cellSize; k++){
					sheet.setColumnWidth(k, cellWidth[k]);
					HSSFCell cell = row.createCell(k);
					cell.setCellStyle(style);
					switch (k) {
						case 0:
							cell.setCellValue(bean.getGoalName());
							break;
						case 1:
							cell.setCellValue(getOrderTypeName(bean.getOrderType()));
							break;
						case 2:
							cell.setCellValue(df.format(bean.getAmount()*1.0/100));
							totalAmount += bean.getAmount();
							break;
						case 3:
							cell.setCellValue(df.format(bean.getAmountBt()*1.0/100));
							totalAmountBt += bean.getAmountBt();
							break;
						case 4:
							cell.setCellValue(bean.getPayTime());
							break;
						case 5:
							cell.setCellValue(bean.getOrderNo());
							break;
						default:
							cell.setCellValue("");
					}
				}
			}
			// 总金额统计
			row = sheet.createRow((i+1));
			row.setHeight((short)400);
			HSSFCell cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue("总金额：");
			
			HSSFCell totalAmountCell = row.createCell(2);
			totalAmountCell.setCellStyle(style);
			totalAmountCell.setCellValue(df.format(totalAmount*1.0/100));
			
			HSSFCell totalAmountBtCell = row.createCell(3);
			totalAmountBtCell.setCellStyle(style);
			totalAmountBtCell.setCellValue(df.format(totalAmountBt*1.0/100));
		}
		return wb;
	}
	
	/**
	 * 获取“订单类型”=={"1":"购物","2":物业费","3":"维修","4":"停车费","5":"其他代收费","6":"物业代扣卡"}
	 * 
	 * @param orderType
	 * @return
	 */
	private final String getOrderTypeName(Integer orderType){
		if(orderType==null){
			return "";
		} else if (orderType.compareTo(CashSqpayBtConstants.OrderType.EBUY_FEE) == 0) {
			return "购物";
		} else if (orderType.compareTo(CashSqpayBtConstants.OrderType.WY_FEE) == 0) {
			return "物业费";
		}  else if (orderType.compareTo(CashSqpayBtConstants.OrderType.REPAIR_FEE) == 0) {
			return "维修";
		} else if (orderType.compareTo(CashSqpayBtConstants.OrderType.PARKING_FEE) == 0) {
			return "停车费";
		} else if (orderType.compareTo(CashSqpayBtConstants.OrderType.OTHER_FEE) == 0) {
			return "其他代收费";
		} else if (orderType.compareTo(CashSqpayBtConstants.OrderType.WY_CARD_FEE) == 0) {
			return "物业代扣卡";
		} 
		return "";
	}
}
