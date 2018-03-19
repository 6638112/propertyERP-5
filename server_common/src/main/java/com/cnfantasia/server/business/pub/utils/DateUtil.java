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
package com.cnfantasia.server.business.pub.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: DateUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年5月8日 上午6:52:44 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月8日 shiyl 1.0 1.0 Version
 */
public class DateUtil {
	public static void main(String[] args) throws ParseException {
//		 System.out.println(getMonthFirstDay("2014-03-08 14:50:01"));
//		 System.out.println(getCurrDay0000("2014-03-08 14:50:01"));
//		 System.out.println(getCurrDay2359("2014-03-08 14:50:01"));
//		 System.out.println(getCurrSysTimeStr());
//		 System.out.println(formatOnlyMonth.get().format(formatSecond.get().parse("2014-03-08 14:50:01")));
//		 System.out.println(getWeekOfDate("2014-09-23 14:50:01", formatDay.get()));
//		 System.out.println(formatMonth.get().format(getPreMonth(formatSecond.get().parse("2014-01-08 14:50:01"))));
		/*
		 * { System.out.println(System.currentTimeMillis()); Long timeLong =
		 * System.currentTimeMillis(); Date date = new Date(timeLong); String
		 * dateStr = formatSecond.format(date);
		 * System.out.println(timeToLong(dateStr)); }
		 */
		System.out.println(getNextMonth("201501", DateUtil.formatMonth.get(),1));
		System.out.println(getNextMonth("201502", DateUtil.formatMonth.get(),1));
		System.out.println(getNextMonth("201412", DateUtil.formatMonth.get(),1));
	}
	
//private static final SimpleDateFormat formatOnlyMonth = new SimpleDateFormat("MM");
//private static final SimpleDateFormat formatMonth = new SimpleDateFormat("yyyyMM");
//private static final SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");
//private static final SimpleDateFormat formatSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//private static final SimpleDateFormat formatFileName = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
//private static final SimpleDateFormat formatSecondTogether = new SimpleDateFormat("yyyyMMddhhmmss");
//private static SimpleDateFormat chineseMonthDay = new SimpleDateFormat("MM月dd日");
	
	/**解决线程同步问题*/
	public static final ThreadLocal<DateFormat> formatOnlyDay = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("dd");
		}
	};
	public static final ThreadLocal<DateFormat> formatOnlyMonth = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("MM");
		}
	};
	public static final ThreadLocal<DateFormat> formatOnlyYear = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy");
		}
	};
	public static final ThreadLocal<DateFormat> formatMonth = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMM");
		}
	};
	public static final ThreadLocal<DateFormat> formatMonth_Split = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM");
		}
	};
	public static final ThreadLocal<DateFormat> formatDay = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	public static final ThreadLocal<DateFormat> formatDay_lean = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd");
		}
	};
	
	public static final ThreadLocal<DateFormat> formatSecond = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	public static final ThreadLocal<DateFormat> formatMinutes = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
	};
	public static final ThreadLocal<DateFormat> formatFileName = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		}
	};
	public static final ThreadLocal<DateFormat> formatSecondTogether = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddhhmmss");
		}
	};
	public static final ThreadLocal<DateFormat> formatMinuteTogether = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmm");
		}
	};
	public static final ThreadLocal<DateFormat> chineseMonthDay = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("MM月dd日");
		}
	};
	
	public static final ThreadLocal<DateFormat> chineseYearMonth = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy年MM月");
		}
	};
	
//	public static SimpleDateFormat formatOnlyMonth(){
//		return new SimpleDateFormat("MM");
//	}
//	public static SimpleDateFormat formatMonth(){
//		return new SimpleDateFormat("yyyyMM");
//	}
//	public static SimpleDateFormat formatDay(){
//		return new SimpleDateFormat("yyyy-MM-dd");
//	}
//	public static SimpleDateFormat formatSecond(){
//		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	}
//	public static SimpleDateFormat formatFileName(){
//		return new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
//	}
//	public static SimpleDateFormat formatSecondTogether(){
//		return new SimpleDateFormat("yyyyMMddhhmmss");
//	}
//	public static SimpleDateFormat chineseMonthDay(){
//		return new SimpleDateFormat("MM月dd日");
//	}
	
	public static Long timeToLong(String time,DateFormat dateFormat) {
		if (StringUtils.isEmpty(time)) {
			return null;
		}
		Date date = null;
		try {
			date = dateFormat.parse(time);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class + "Time parse error:" + e.getMessage() + ",time is:" + time);
		}
		return date.getTime();
	}
	
	public static Long timeToLong(String time) {
		if (StringUtils.isEmpty(time)) {
			return null;
		}
		Date date = null;
		try {
			date = formatSecond.get().parse(time);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class + "Time parse error:" + e.getMessage() + ",time is:" + time);
		}
		return date.getTime();// TODO ..
	}
	
	public static String strFormate(String time,DateFormat srcFromate,DateFormat goalFromate){
		if (StringUtils.isEmpty(time)) {
			return null;
		}
		Date date = null;
		try {
			date = srcFromate.parse(time);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class + "Time parse error:" + e.getMessage() + ",time is:" + time);
		}
		String dateStr = goalFromate.format(date);
		return dateStr;
	}
	
	public static String getCurrSysTimeStr() {
		Long timeLong = System.currentTimeMillis();
		Date date = new Date(timeLong);
		String dateStr = formatFileName.get().format(date);
		return dateStr;
	}

	/**
	 * 2014-03-08 14:50:01
	 * 
	 * @param nowTime
	 * @return 2014-03-08
	 */
	public static String getMonthFirstDay(String nowTime) {
		return getMonthFirstDay(formatDay.get(), nowTime);
	}
	public static String getMonthFirstDay(DateFormat dateFormate,String nowTime) {
		Date date = null;
		try {
			date = dateFormate.parse(nowTime);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class + "Time parse error:" + e.getMessage() + ",time is:" + nowTime);
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));
		String last = formatDay.get().format(ca.getTime());
		return last;
	}
	
	public static Date getPreMonth(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, -1);
		return ca.getTime();
	}
	
	/**
	 * 跳跃月份
	 * @param dateStr
	 * @param dateFormate
	 * @param length 可以为负数，表示之前月份
	 * @return
	 */
	public static String getNextMonth(String dateStr,DateFormat dateFormate,int length){
		return getNextDate(dateStr, dateFormate, Calendar.MONTH, length);
	}
	
	public static Date getNextMonth(Date date,int length){
		return getNextDate(date,Calendar.MONTH, length);
	}
	
	/**
	 * 日期加减
	 * @param dateStr
	 * @param dateFormate
	 * @param type
	 * @param length
	 * @return
	 */
	public static String getNextDate(String dateStr,DateFormat dateFormate,int type,int length){
		Date date = null;
		try {
			date = dateFormate.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class + "getPreMonth Time parse error:" + e.getMessage() + ",dateStr is:" + dateStr);
		}
		Date resDate = getNextDate(date, type, length);
		return dateFormate.format(resDate);
	}
	/**
	 * 日期加减
	 * @param date
	 * @param type
	 * @param length
	 * @return
	 */
	public static Date getNextDate(Date date,int type,int length){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(type, length);
		Date resDate = ca.getTime();
		return resDate;
	}
	
	/**
	 * 2014-03-08 14:50:01
	 * 
	 * @param nowTime
	 * @return 2014-03-08
	 */
	public static String getMonthLastDay(String nowTime) {
		return getMonthLastDay(formatDay.get(), nowTime);
	}
	public static String getMonthLastDay(DateFormat dateFormate,String nowTime) {
		Date date = null;
		try {
			date = dateFormate.parse(nowTime);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class + "Time parse error:" + e.getMessage() + ",time is:" + nowTime);
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = formatDay.get().format(ca.getTime());
		return last;
	}

	/**
	 * 获取对应时间的凌晨时间 2014-03-08 14:50:01
	 * 
	 * @param nowTime
	 * @return 2014-03-08 00:00:00
	 */
	public static String getCurrDay0000(String nowTime) {
		Date date = null;
		try {
			date = formatDay.get().parse(nowTime);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class + "Time parse error:" + e.getMessage() + ",time is:" + nowTime);
		}
		return formatSecond.get().format(date);
	}

	/**
	 * 获取对应时间的当天最晚时间
	 * 
	 * @param nowTime
	 * @return
	 */
	public static String getCurrDay2359(String nowTime) {
		Calendar todayEnd = Calendar.getInstance();
		Date date = null;
		try {
			date = formatDay.get().parse(nowTime);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class + "Time parse error:" + e.getMessage() + ",time is:" + nowTime);
		}
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return formatSecond.get().format(todayEnd.getTime());
	}
//	/**
//	 * 获取上个月对应的年月
//	 * @param year
//	 * @param month 1-12
//	 * @return
//	 */
//	public static String getPreMonthYear(Integer year,Integer month){
//		month = month-1;
//		if(month==0){
//			month = 12;
//			year = year-1;
//		}
//		return formateYear(year)+formateMonth(month);
//	}
	public static String formateYear(Integer year){
		if(year<0){year=0;}
		String yearStr = year<=9?"000"+year:(year<=99?"00"+year:(year<=999?"0"+year:""+year));
		return yearStr;
	}
	public static String formateMonth(Integer month){
		if(month<0||month>12){month=0;}
		String monthStr = month<=9?"0"+month:""+month;
		return monthStr;
	}
	public static String formateDay(Integer day){
		if(day<0||day>31){day=0;}
		String dayStr = day<=9?"0"+day:""+day;
		return dayStr;
	}
	
	public static String getSeasion(Calendar today){
		int month = today.get(Calendar.MONTH)+1;//当前月份[1,12]
		if(month>=3&&month<=5){
			return "春";
		}else if(month>=6&&month<=8){
			return "夏";
		}else if(month>=9&&month<=11){
			return "秋";
		}else if(month==12||month>=1&&month<=2){
			return "冬";
		}else{
			return null;
		}
	}
	
	/**
	 * 时间转Linux
	 * @param timeLong
	 * @return
	 */
	public static Long time2Linux(Long timeLong){
		return timeLong/1000;
	}
	
	/**
   * 获取当前日期是星期几<br>
   * @param dt
   * @return 当前日期是星期几
   */
  public static String getWeekOfDate(Date dt) {
      String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
      Calendar cal = Calendar.getInstance();
      cal.setTime(dt);
      int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
      if (w < 0)
          w = 0;
      return weekDays[w];
  }
  public static String getWeekOfDate(String time,DateFormat dateFormat) {
  	Date date = null;
		try {
			date = dateFormat.parse(time);
		} catch (ParseException e) {
			throw new RuntimeException(DateUtil.class + "Time parse error:" + e.getMessage() + ",time is:" + time);
		}
		return getWeekOfDate(date);
  }
  
  /**
	 * 比较fromTime与nowTime
	 * @param fromTime
	 * @param nowTime
	 * @return 返回相差的天数
	 */
	public static Integer daysBetween(String fromTime,String nowTime){
		Integer res = null;
		try {
			Date oldTime = DateUtil.formatDay.get().parse(fromTime);
			Date today = DateUtil.formatDay.get().parse(nowTime);
			long between_days=(oldTime.getTime()-today.getTime())/(1000*3600*24);  
			res = Integer.parseInt(String.valueOf(between_days));
//		// 昨天 86400000=24*60*60*1000 一天
//			if ((today.getTime() - oldTime.getTime()) > 0 && (today.getTime() - oldTime.getTime()) <= 86400000) {
//				res =  -1;
//			} else if ((today.getTime() - oldTime.getTime()) <= 0 && (today.getTime() - oldTime.getTime())>-86400000 ) { //今天
//				res =  0;
//			} else { // 至少是前天 或者大于今天
//				res =  null;
//			}
		} catch (Exception e) {
			throw new RuntimeException(DateUtil.class + "Time parse error:" + e.getMessage() + ",fromTime is:" + fromTime+",nowTime is :"+nowTime);
		}
		return res;
	}
	
	
	public static final int SECONDS_IN_DAY = 60 * 60 * 24;
  public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;
  public static boolean isSameDayOfMillis(final long ms1, final long ms2) {
      final long interval = ms1 - ms2;
      return interval < MILLIS_IN_DAY
              && interval > -1L * MILLIS_IN_DAY
              && toDay(ms1) == toDay(ms2);
  }
  private static long toDay(long millis) {
      return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
  }

  public static long distance(String from,DateFormat fromDf,String to,DateFormat toDf){
		try {
			Date oldTime = fromDf.parse(from);
			Date today = toDf.parse(to);
			return oldTime.getTime()-today.getTime();
		} catch (Exception e) {
			throw new BusinessRuntimeException("dateutil.distance.formateError");
		}
	}
  
}
