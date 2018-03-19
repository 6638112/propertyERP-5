/**   
 * Filename:    RelativeDateFormat.java   
 * @version:    1.0  
 * Create at:   2014年7月13日 上午10:42:57   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年7月13日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.commonBusiness.util;

/**
 * Filename:    RelativeDateFormat.java
 * @version:    1.0.0
 * Create at:   2014年7月13日 上午10:42:57
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月13日       shiyl             1.0             1.0 Version
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RelativeDateFormat {

	private static final long ONE_MINUTE = 60000L;
	private static final long ONE_HOUR = 3600000L;
	private static final long ONE_DAY = 86400000L;
	private static final long ONE_WEEK = 604800000L;

	private static final String ONE_SECOND_AGO = "秒前";
	private static final String ONE_MINUTE_AGO = "分钟前";
	private static final String ONE_HOUR_AGO = "小时前";
	private static final String ONE_DAY_AGO = "天前";
	private static final String ONE_MONTH_AGO = "月前";
	private static final String ONE_YEAR_AGO = "年前";

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
		Date nowDate = format.parse("2015-09-18 10:07:21");
		Date date2 = format.parse("2015-09-16 11:46:05");
//		System.out.println(format(date));//1405254194
//		System.out.println(format(nowDate.getTime()-date.getTime(),nowDate.getTime()));
		System.out.println(format(nowDate.getTime()-date2.getTime(),nowDate.getTime()));
	}
	public static String format(long delta,long nowTime) {
		long nowDayMinute = 0L;
		{
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(nowTime);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			nowDayMinute = nowTime-cal.getTimeInMillis();
		}
		if (delta < 1L * ONE_MINUTE) {
			long seconds = toSeconds(delta);
			return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
		}
		if (delta < 45L * ONE_MINUTE) {
			long minutes = toMinutes(delta);
			return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
		}
		if (delta < 24L * ONE_HOUR) {
			long hours = toHours(delta);
			return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
		}
		delta  = delta-nowDayMinute;//差值计算恢复到当前时间凌晨零点
		if (delta < 1L * ONE_DAY) {
			return "昨天";
		}
		if (delta < 2L * ONE_DAY) {
			return "前天";
		}
		if (delta < 30L * ONE_DAY) {
			long days = toDays(delta);
			return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
		}
		if (delta < 12L * 4L * ONE_WEEK) {
			long months = toMonths(delta);
			return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
		} else {
			long years = toYears(delta);
			return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
		}
	}
	public static String format(Date date) {
		long delta = new Date().getTime() - date.getTime();
		return format(delta,new Date().getTime());
	}

	private static long toSeconds(long date) {
		return date / 1000L;
	}

	private static long toMinutes(long date) {
		return toSeconds(date) / 60L;
	}

	private static long toHours(long date) {
		return toMinutes(date) / 60L;
	}

	private static long toDays(long date) {
		return toHours(date) / 24L;
	}

	private static long toMonths(long date) {
		return toDays(date) / 30L;
	}

	private static long toYears(long date) {
		return toMonths(date) / 365L;
	}

}