/**   
* Filename:    MonthYear.java   
* @version:    1.0  
* Create at:   2014年8月1日 上午3:59:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.business.pub.utils;

import java.io.Serializable;

/**
 * Filename:    MonthYear.java
 * @version:    1.0.0
 * Create at:   2014年8月1日 上午3:59:22
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月1日       shiyl             1.0             1.0 Version
 */
public class MonthYear implements Serializable{
//	public static void main(String[] args) {
//		MonthYear currMonthYear = new MonthYear(Integer.parseInt("1992"), Integer.parseInt("2"));
////	String yearMonth = DateUtil.getPreMonthYear(Integer.parseInt(spltTime[0]), Integer.parseInt(spltTime[1]));
//	String yearMonth = currMonthYear.getPreMonthYear().getPreMonthYear().toStringFormate();
//	System.out.println(yearMonth);
//	}
	private static final long serialVersionUID = 1L;
	private Integer year;
	private Integer month;
	
	public MonthYear(Integer year,Integer month){
		this.year = year;
		this.month = month;
	}
	
	public String toStringFormate(){
		return DateUtil.formateYear(year)+DateUtil.formateMonth(month);
	}
	
	public MonthYear getPreMonthYear(){
		Integer month = null;
		Integer year = null;
		month = this.month-1;
		if(month==0){
			month = 12;
			year = this.year-1;
		}else{
			year = this.year;
		}
		return new MonthYear(year,month);
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	
	
}
