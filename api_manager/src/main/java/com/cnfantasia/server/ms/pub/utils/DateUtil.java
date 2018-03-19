/**   
* Filename:    DateUtil.java   
* @version:    1.0  
* Create at:   2014年5月8日 上午6:52:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.pub.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Filename:    DateUtil.java
 * @version:    1.0.0
 * Create at:   2014年5月8日 上午6:52:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月8日       shiyl             1.0             1.0 Version
 */
public class DateUtil {
	public static void main(String[] args) {
		System.out.println(getMonthFirstDay("2014-03-08 14:50:01"));
		System.out.println(getCurrDay0000("2014-03-08 14:50:01"));
		System.out.println(getCurrDay2359("2014-03-08 14:50:01"));
		System.out.println(getCurrSysTimeStr());
	}
	public static final SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat formatSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat formatFileName = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
	public static String getCurrSysTimeStr(){
		Long timeLong = System.currentTimeMillis();
		Date date = new Date(timeLong);
		String dateStr = formatFileName.format(date);
		return dateStr;
	}
	/**
	 * 2014-03-08 14:50:01
	 * @param nowTime
	 * @return 2014-03-08
	 */
	public static String getMonthFirstDay(String nowTime){
		Date date = null;
		try {
			date = formatDay.parse(nowTime);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class+"Time parse error:"+e.getMessage()+",time is:"+nowTime);
		}
    Calendar ca = Calendar.getInstance();   
    ca.setTime(date);  
    ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH)); 
    String last = formatDay.format(ca.getTime());
    return last;
	}
	/**
	 * 2014-03-08 14:50:01
	 * @param nowTime
	 * @return 2014-03-08
	 */
	public static String getMonthLastDay(String nowTime){
		Date date = null;
		try {
			date = formatDay.parse(nowTime);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class+"Time parse error:"+e.getMessage()+",time is:"+nowTime);
		}
    Calendar ca = Calendar.getInstance();   
    ca.setTime(date);  
    ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
    String last = formatDay.format(ca.getTime());
    return last;
	}
	/**
	 * 获取对应时间的凌晨时间
	 * 2014-03-08 14:50:01
	 * @param nowTime
	 * @return 2014-03-08 00:00:00
	 */
	public static String getCurrDay0000(String nowTime){
		Date date = null;
		try {
			date = formatDay.parse(nowTime);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class+"Time parse error:"+e.getMessage()+",time is:"+nowTime);
		}
    return formatSecond.format(date);
	}
	/**
	 * 获取对应时间的当天最晚时间
	 * @param nowTime
	 * @return
	 */
	public static String getCurrDay2359(String nowTime){
		Calendar todayEnd = Calendar.getInstance();
		Date date = null;
		try {
			date = formatDay.parse(nowTime);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class+"Time parse error:"+e.getMessage()+",time is:"+nowTime);
		}
		todayEnd.setTime(date); 
    todayEnd.set(Calendar.HOUR, 23);  
    todayEnd.set(Calendar.MINUTE, 59);  
    todayEnd.set(Calendar.SECOND, 59);  
    todayEnd.set(Calendar.MILLISECOND, 999);  
    return formatSecond.format(todayEnd.getTime());
	}

	/**
	 * 判断系统时间是否大于指定日期
	 * @param desTime
	 * @return
     */
	public static boolean isSysTimeLaterThan(String desTime) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(desTime);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class+"Time parse error:"+e.getMessage()+",time is:"+desTime);
		}
		return new Date().getTime() - date.getTime() > 0;
	}
}
