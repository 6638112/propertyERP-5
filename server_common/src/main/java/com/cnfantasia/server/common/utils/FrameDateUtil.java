package com.cnfantasia.server.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * 类名：DateUtil  <br />
 *
 * 功能：
 *
 */
public class FrameDateUtil {

	// 缓存
	private static final Map<String, SimpleDateFormat> formats;
	
	static {
		formats = new HashMap<String, SimpleDateFormat>();
		
		formats.put("yyyyMMdd", new SimpleDateFormat("yyyyMMdd"));
		formats.put("HHmmss", new SimpleDateFormat("HHmmss"));
		formats.put("yyyyMMddHHmmss", new SimpleDateFormat("yyyyMMddHHmmss"));
	}
	
	/**
	 * 
	 * 功能：比较两个日期的顺序 <br/>
	 *
	 */
	public static int compare(Date one, Date two) {
		return one.compareTo(two);
	}
	
	/**
	 * 
	 * 功能： <br/>
	 *
	 */
	public static Date add(Date date, int field, int amount) {
		return add(date, field, amount, null);
	}
	
	/**
	 * 
	 * 功能： <br/>
	 *
	 */
	public static Date add(Date date, int field, int amount, TimeZone timeZone) {
		Calendar cal = (timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone));
		
		cal.setTime(date);
		
		cal.add(field, amount);
		
		return cal.getTime();
	}
	
	/**
	 * 
	 * 功能： <br/>
	 *
	 */
	public static Date parseDateTime(String source) throws ParseException {
		return parse(source, "yyyyMMddHHmmss");
	}
	
	/**
	 * 
	 * 功能： <br/>
	 *
	 */
	public static Date parseTime(String source) throws ParseException {
		return parse(source, "HHmmss");
	}
	
	/**
	 * 
	 * 功能： <br/>
	 *
	 * @throws ParseException 
	 */
	public static Date parseDate(String source) throws ParseException {
		return parse(source, "yyyyMMdd");
	}
	
	/**
	 * 
	 * 功能： <br/>
	 *
	 */
	public static Date parse(String source, String pattern) throws ParseException {
		return getFormat(pattern).parse(source);
	}
	
	/**
	 * 
	 * 功能： <br/>
	 *
	 */
	public static String formatDateTime(Date date) {
		return format(date, "yyyyMMddHHmmss");
	}
	
	/**
	 * 
	 * 功能：按默认规则格式化为时间 <br/>
	 *
	 */
	public static String formatTime(Date date) {
		return format(date, "HHmmss");
	}
	
	/**
	 * 
	 * 功能：按默认规则格式化日期 <br/>
	 *
	 */
	public static String formatDate(Date date) {
		return format(date, "yyyyMMdd");
	}
	
	/**
	 * 
	 * 功能： <br/>
	 *
	 */
	public static String format(Date date, String pattern) {
		return getFormat(pattern).format(date);
	}
	
	/**
	 * 
	 * 功能： <br/>
	 *
	 */
	private static SimpleDateFormat getFormat(String pattern) {
		SimpleDateFormat sdf = formats.get(pattern);
		
		if (null == sdf) {
			formats.put(pattern, sdf = new SimpleDateFormat(pattern));
		}
		
		// 强制格式匹配
		sdf.setLenient(false);
		
		return sdf;
	}
}
