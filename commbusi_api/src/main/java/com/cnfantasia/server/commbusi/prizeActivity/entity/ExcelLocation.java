/**   
* Filename:    ExcelLocation.java   
* @version:    1.0  
* Create at:   2015年9月16日 上午9:58:34   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import java.io.Serializable;

/**
 * Filename:    ExcelLocation.java
 * @version:    1.0.0
 * Create at:   2015年9月16日 上午9:58:34
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月16日       shiyl             1.0             1.0 Version
 */
public class ExcelLocation implements Serializable{
	private static final long serialVersionUID = -8010850975904831909L;
	/**起始0*/
	private int rowNum;
	/**起始0*/
	private int colNum;
	
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("行");
		sbf.append(rowNum+1);
		sbf.append("列");
		sbf.append(colNum+1);
		return sbf.toString();
	}
	public ExcelLocation(){}
	public ExcelLocation(int rowNum,int colNum){
		this.rowNum = rowNum;
		this.colNum = colNum;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	
}
