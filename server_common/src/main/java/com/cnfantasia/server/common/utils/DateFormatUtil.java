package com.cnfantasia.server.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 这是一个工具类，用户实现将"yyyy-MM-dd"格式的数据和Long数据互转。
 *
 */
public class DateFormatUtil {
	/*public static void main(String[] args) throws ParseException {
		System.out.println(timeToLong("2012-06-29 23:45:26"));
	}*/
	
	/**
	 * 将形如"yyyy-MM-dd"格式的日期转换成Long类型的数据返回
	 * @param time 要转换的时间
	 * @return 转换成功后的Long数据
	 * @throws ParseException
	 */
	public static Long timeToLong(String time,String formate) throws ParseException {
		if(time==null){
			return null;
		}
		return (new SimpleDateFormat(formate).parse(time)).getTime();
	}
	
	/**
	 * 将Long类型的数据转换成形如"yyyy-MM-dd"格式的日期字符串。
	 * @param time 要转换的Long时间
	 * @return 转换后的"yyyy-MM-dd"格式的string数据。
	 */
	public static String longToTime(Long time,String formate){
		if(time==null) return "";
		return new SimpleDateFormat(formate).format(new Date(time));
	}
	/**
	 * 查询当前时间的所在天的凌晨时间
	 * @param currTimeMill
	 * @return
	 */
	public static long getLingChengTimeByCurrTime(long currTimeMill){
		return currTimeMill/(24*60*60*1000)*(24*60*60*1000)-8*60*60*1000;
	}
	/**
	 * 查询当前时间的所在天的第二天凌晨时间
	 * @param currTimeMill
	 * @return
	 */
	public static long getLingChengTimeNextDayByCurrTime(long currTimeMill){
		return currTimeMill/(24*60*60*1000)*(24*60*60*1000)+16*60*60*1000;
	}
}
