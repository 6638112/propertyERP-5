package com.cnfantasia.server.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

public final class HSSFCellUtil {
	
	public static String getStringValue(HSSFCell cell) {
		if (cell == null)
			return "";

		String cellValue = "";

		switch (cell.getCellType()) {

		case HSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			// 用来处理手机号以数字存储的问题，需要加0.00000001再取整，其它数字也按此取整读取
			cellValue = (long) (cell.getNumericCellValue() + 0.00000001) + "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			cellValue = cell.getBooleanCellValue() + "";
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = "";
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			cellValue = cell.getNumericCellValue() + "";
			break;
		default:
			throw new java.lang.IllegalStateException("行"
					+ (cell.getRowIndex() + 1) + ", 列"
					+ (cell.getColumnIndex() + 1) + "无法转换为字符串");
		}

		return cellValue.trim();
	}

	public static double getNumbericValue(HSSFCell cell) {
		if (cell == null)
			return 0;

		double cellValue = 0;
		switch (cell.getCellType()) {

		case HSSFCell.CELL_TYPE_STRING:
			try {
				cellValue = Double
						.parseDouble(cell.getStringCellValue().trim());
			} catch (NumberFormatException e) {
				throw new java.lang.IllegalStateException("行"
						+ (cell.getRowIndex() + 1) + ", 列"
						+ (cell.getColumnIndex() + 1) + "包含中英文或特殊字符，请录入数值");
			}
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			cellValue = cell.getBooleanCellValue() ? 1 : 0;
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = 0;
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			cellValue = cell.getNumericCellValue();
			break;
		default:
			throw new java.lang.IllegalStateException("行"
					+ (cell.getRowIndex() + 1) + ", 列"
					+ (cell.getColumnIndex() + 1) + "无法转换为数字");
		}

		return cellValue;
	}

	public static String getDate(HSSFCell cell) {
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
			}
			DecimalFormat df = new DecimalFormat("#");
			return df.format(cell.getNumericCellValue());
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case HSSFCell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case HSSFCell.CELL_TYPE_BLANK:
			return "";
		default:
			throw new java.lang.IllegalStateException("行"
					+ (cell.getRowIndex() + 1) + ", 列"
					+ (cell.getColumnIndex() + 1) + "无法转换为日期");
		}
	}
	
	/**
	 * 获取excel表格单元的值
	 * 
	 * @param cell
	 * @return String
	 */
	public static String getStringValue(Cell cell) {
		String cellValue = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			cellValue = StringUtils.EMPTY;
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			cellValue = String.valueOf(cell.getErrorCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		default:
			throw new java.lang.IllegalStateException("行"
					+ (cell.getRowIndex() + 1) + ", 列"
					+ (cell.getColumnIndex() + 1) + "无法转换为字符串");
		}
		return cellValue;
	}
	
	/**
	 * 获取excel表格单元的值
	 * 
	 * @param cell
	 * @return String
	 */
	public static double getNumbericValue(Cell cell) {
		double cellValue = 0;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			cellValue = 0;
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = cell.getBooleanCellValue() ? 1 : 0;
			break;
		case Cell.CELL_TYPE_ERROR:
			cellValue = 0;
			break;
		case Cell.CELL_TYPE_FORMULA:
			cellValue = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_STRING:
			try {
				cellValue = Double
						.parseDouble(cell.getStringCellValue().trim());
			} catch (NumberFormatException e) {
				throw new java.lang.IllegalStateException("行"
						+ (cell.getRowIndex() + 1) + ", 列"
						+ (cell.getColumnIndex() + 1) + "包含中英文或特殊字符，请录入数值");
			}
			break;
		default:
			throw new java.lang.IllegalStateException("行"
					+ (cell.getRowIndex() + 1) + ", 列"
					+ (cell.getColumnIndex() + 1) + "无法转换为字符串");
		}
		return cellValue;
	}
}
