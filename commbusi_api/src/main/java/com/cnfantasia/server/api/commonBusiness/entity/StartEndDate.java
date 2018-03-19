/**   
* Filename:    StartEndDate.java   
* @version:    1.0  
* Create at:   2014年12月1日 上午9:57:18   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.DateUtil;

/**
 * Filename:    StartEndDate.java
 * @version:    1.0.0
 * Create at:   2014年12月1日 上午9:57:18
 * Description: 起止日期
 * 
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月1日       shiyl             1.0             1.0 Version
 */
public class StartEndDate {
	private Date startDate;
	private Date endDate;
	
	/**
	 * 判断对应日期是否在当前时间区间内
	 * @param date
	 * @return
	 */
	public boolean checkIsInInterval(Date date){
		boolean res = false;
		if (date != null && startDate != null && endDate != null) {
			if (date.getTime() >= startDate.getTime()
					&& date.getTime() <= endDate.getTime()) {
				res = true;
			}
		}
		return res;
	}
	
	public String getStartYearMonthDay(){
		return getYearMonthDay(startDate);
	}
	
	public String getEndYearMonthDay(){
		return getYearMonthDay(endDate);
	}
	
	public static void main(String[] args) throws ParseException {
		Date date = DateUtil.formatDay.get().parse("2014-1-02");
		System.out.println(getYearMonthDay(date));
		System.out.println(date.getTime());
		System.out.println(DateUtil.timeToLong(getYearMonthDay(date), new SimpleDateFormat("yyyyMMdd")));
	}
	
	public Long getStartYearMonthDayLong(){
		return startDate==null?null:startDate.getTime();
	}
	
	public Long getEndYearMonthDayLong(){
		return endDate==null?null:endDate.getTime();
	}
	
	@Override
	public String toString() {
		return getStartYearMonthDay()+"~"+getEndYearMonthDay();
	}
	public StartEndDate(Date startDate,Date endDate){
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	private static String getYearMonthDay(Date resDate){
		StringBuffer resStr = null;
		if(resDate!=null){
			resStr = new StringBuffer();
			Calendar ca = Calendar.getInstance();
			ca.setTime(resDate);
			resStr.append(ca.get(Calendar.YEAR));
			resStr.append(DateUtil.formateMonth(ca.get(Calendar.MONTH)+1));//注意+1
			resStr.append(DateUtil.formateDay(ca.get(Calendar.DAY_OF_MONTH)));//注意+1
		}
		return resStr==null?null:resStr.toString();
	}
}
