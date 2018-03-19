package com.cnfantasia.server.business.pub.utils;


/**
 * 数字转换工具类
 * 
 * @author wenfq
 * 
 */
public class NumberUtils {
	/**
	 * double乘以100再转Long
	 * 
	 * @param d
	 *            double参数
	 * @return 乘以100再转Long
	 */
	public static Long doubleM100ToLong(double d) {
		return new Long(String.format("%.0f", d * 100 + 0.0001));
	}

	/**
	 * double转Long
	 * 
	 * @param d
	 *            double参数
	 * @return 4舍5入转Long
	 */
	public static Long doubleToLong(double d) {
		return new Long(String.format("%.0f", d + 0.0001));
	}
	
	public static long getLongValue(Long paramLong){
		return paramLong == null ? 0: paramLong.longValue();
	}
}
