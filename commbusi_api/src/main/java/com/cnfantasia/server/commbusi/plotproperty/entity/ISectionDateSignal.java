package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.text.DateFormat;

public interface ISectionDateSignal extends DataType.ISectionDate {
	
	/**
	 * 获取月份
	 * @return
	 */
	String getMonth();
	
	/**
	 * 获取年份
	 * @return
	 */
	String getYear();
	
	/**
	 * 获取当前时间对应的月份long表示
	 * @return
	 */
	Long getYearMonthLong();
	
	String getTimeStr(DateFormat dateFormat);
	
}
