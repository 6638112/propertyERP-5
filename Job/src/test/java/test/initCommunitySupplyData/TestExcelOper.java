/**   
 * Filename:    TestExcelOper.java   
 * @version:    1.0  
 * Create at:   2014年9月16日 上午11:59:56   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年9月16日    shiyl      1.0         1.0 Version   
 */
package test.initCommunitySupplyData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: TestExcelOper.java
 * 
 * @version: 1.0.0 Create at: 2014年9月16日 上午11:59:56 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年9月16日 shiyl 1.0 1.0 Version
 */
public class TestExcelOper {
	public static void main(String[] args) throws IOException {
//		InputStream fileInputStream = new FileInputStream("src/test/java/test/initCommunitySupplyData/src.xls");
		InputStream fileInputStream = new FileInputStream("src/test/java/test/initCommunitySupplyData/test02.xls");
		// 读取Excel数据
		XSSFWorkbook srcWorkBook = new XSSFWorkbook(fileInputStream);
		for (int i = 1; i < 2; i++) {
			XSSFSheet sheet = srcWorkBook.getSheetAt(i);
			int rowStart = sheet.getFirstRowNum();
			int rowEnd = sheet.getLastRowNum();
			for (int j = rowStart; j < rowEnd; j++) {
				XSSFRow row = sheet.getRow(j);
				int firstCellNum = row.getFirstCellNum();
				int lastCellNum = row.getLastCellNum();
				for (int k = firstCellNum; k < lastCellNum; k++) {
					if (!StringUtils.isEmpty(row.getCell(k))) {
						System.out.print("i=" + i + ";j=" + j + ";k=" + k + ":"+row.getCell(k)+"_____");
					}
				}
				System.out.println();
			}
		}

	}

}
