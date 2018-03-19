package com.cnfantasia.server.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelUtil {
	public static void parseExcel(String fileName,String dataPosition,Object dataValue) throws FileNotFoundException, IOException{
		Workbook workbook = null;
   	 try
	    {
	      //解析Excel2007 
	    	workbook = new XSSFWorkbook(new FileInputStream(fileName));
	    }catch (Exception e) {
	      //解析excel2003
	      workbook = new HSSFWorkbook(new FileInputStream(fileName));
	    }
	    int rowPosition=Integer.parseInt(dataPosition.substring(0,1));
	    int cellPosition=Integer.parseInt(dataPosition.substring(1));
	    //获得第一个sheet项
	    Sheet sheet=workbook.getSheetAt(0);
	    //获得指定的行
	    Row row=sheet.getRow(rowPosition);
	    //获得指定的列
	    Cell cell=row.getCell(cellPosition); 
	    cell.setCellValue((String)dataValue);
	    String cellData=getData(cell);
	    if(cellData!=null){
	    	
	    }
	    FileOutputStream out=new FileOutputStream(fileName);
	    workbook.write(out);
	    out.close();
	}
	
	 //获得列的值
	  public static String getData(org.apache.poi.ss.usermodel.Cell cell) {
			// System.out.println(cell.getCellType());
			String value = "";
			if (cell == null)
				return value;
			switch (cell.getCellType()) {
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC: // 数值型
				if (DateUtil.isCellDateFormatted(cell)) {
					// 如果是date类型则 ，获取该cell的date值
					value = DateUtil.getJavaDate(cell.getNumericCellValue())
							.toString();
				} else {// 纯数字
					value = String.valueOf((long) cell.getNumericCellValue());
				}
				break;
			/* 此行表示单元格的内容为string类型 */
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING: // 字符串型
				value = cell.getRichStringCellValue().toString();
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:// 公式型
				// 读公式计算值
				value = String.valueOf(cell.getNumericCellValue());
				if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
					value = cell.getRichStringCellValue().toString();
				}
				// cell.getCellFormula();读公式
//				CellValue cellValue=evaluator.evaluate(cell);
//				Cell cellf=evaluator.evaluateInCell(cell);
//				try { 
//					value = cellf.getStringCellValue();
//					System.err.println("字符串："+value);
//				} catch (IllegalStateException e) {  
//					System.err.println("2");
//					value ="#N/A";
//					System.err.println("数字："+value);
//				}  
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:// 布尔
				value = " " + cell.getBooleanCellValue();
				break;
			/* 此行表示该单元格值为空 */
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK: // 空值
				value = "";
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR: // 故障
				value = "";
				break;
			default:
				value = cell.getRichStringCellValue().toString();
			}
			return value;
		}
	public static void main(String[] args) {
		try {
			POIFSFileSystem fs =new POIFSFileSystem(new FileInputStream("H:\\机票申请\\机票审批单.xls"));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = sheet.getRow(1); 
			@SuppressWarnings("deprecation")
			HSSFCell cell = row.getCell((short)0);
			cell.setCellValue("a test");
			FileOutputStream fileOut = new FileOutputStream("H:\\机票申请\\机票审批单.xls");
			wb.write(fileOut);   
			fileOut.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
