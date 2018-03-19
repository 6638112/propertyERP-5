package com.cnfantasia.server.ms.door.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cnfantasia.server.ms.door.dao.IDoorDao;
import com.cnfantasia.server.ms.door.entity.DoorVerifyAndPaymentDto;

/**
 * 门牌验证缴费
 * 
 * @author liyulin
 * @version 1.0 2016年7月14日 上午10:58:39
 */
public class DoorService implements IDoorService {
	
	private IDoorDao doorDao;

	public void setDoorDao(IDoorDao doorDao) {
		this.doorDao = doorDao;
	}

	/**
	 * 查询门牌验证缴费情况记录条数
	 * 
	 * @param paramMap
	 * @return int
	 */
	@Override
	public int selectDoorVerifyAndPaymentForCount(Map<String, Object> paramMap) {
		return doorDao.selectDoorVerifyAndPaymentForCount(paramMap);
	}

	/**
	 * 查询门牌验证缴费情况
	 * 
	 * @param paramMap
	 * @return List<DoorVerifyAndPaymentDto>
	 */
	@Override
	public List<DoorVerifyAndPaymentDto> selectDoorVerifyAndPaymentForList(Map<String, Object> paramMap) {
		return doorDao.selectDoorVerifyAndPaymentForList(paramMap);
	}

	/**
	 * 导出门牌验证缴费情况
	 * 
	 * @param paramMap
	 * @return HSSFWorkbook
	 */
	@Override
	public HSSFWorkbook exportDoorVerifyAndPayment(Map<String, Object> paramMap) {
		List<DoorVerifyAndPaymentDto> exportList = doorDao.selectDoorVerifyAndPaymentForList(paramMap);
		
		String[] sheetTitle = {"所在省", "所在市", "物业公司", "代理商", "小区名称", "楼栋号", "单元", "房间号", "注册状态", "认证状态", "认证时间", "缴费状态", "缴费时间"};
		int[]    cellWidth  = {4000,   5000,  10000,   10000,  12000,   4000,  4000,  4000,  3000,    3000,    4000,    3000,   4000};
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
			for(int i=0, size=exportList.size(); i<size; i++){
				DoorVerifyAndPaymentDto bean = exportList.get(i);
				row = sheet.createRow((i+1));
				row.setHeight((short)400);
				for(int k=0, cellSize=sheetTitle.length; k<cellSize; k++){
					sheet.setColumnWidth(k, cellWidth[k]);
					HSSFCell cell = row.createCell(k);
					cell.setCellStyle(style);
					switch (k) {
						case 0:
							cell.setCellValue(bean.getProvince());
							break;
						case 1:
							cell.setCellValue(bean.getCity());
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
							if(bean.getRegisterState()!=null && bean.getRegisterState()==1){
								cell.setCellValue("已注册");
							} else {
								cell.setCellValue("未注册");
							}
							break;
						case 9:
							if(bean.getVerifyState()!=null && bean.getVerifyState()==1){
								cell.setCellValue("已认证");
							} else {
								cell.setCellValue("未认证");
							}
							break;
						case 10:
							cell.setCellValue(bean.getVerifyTime());
							break;
						case 11:
							if(bean.getPayState()!=null && bean.getPayState()==1){
								cell.setCellValue("已缴费");
							} else {
								cell.setCellValue("未缴费");
							}
							break;
						case 12:
							cell.setCellValue(bean.getPayTime());
							break;
	
						default:
							cell.setCellValue("");
					}
				}
			}
		}
		return wb;
	}

}
