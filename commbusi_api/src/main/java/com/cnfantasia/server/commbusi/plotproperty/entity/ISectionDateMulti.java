package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.util.Date;

public interface ISectionDateMulti extends DataType.ISectionDate {
	/**
	 * 获取开始时间年月的date
	 * @return
	 */
	Date getStartYearMonth();
	/**
	 * 获取截止时间年月的date
	 * @return
	 */
	Date getEndYearMonth();
	
	/**
	 * 起始年月日表示 YYYYMMDD
	 * @return
	 */
	String getStartYearMonthDay();
	
	/**
	 * 截止年月日表示 YYYYMMDD
	 * @return
	 */
	String getEndYearMonthDay();
	
	/**
	 * 起始时间
	 * @return
	 */
	Date getStartDate();
	
	/**
	 * 截止时间
	 * @return
	 */
	Date getEndDate();
	
	/**
	 * 获取开始时间描述,例如5月6号
	 * @return
	 */
	String getPeriodDescStart();
	
	/**
	 * 获取截止时间描述,例如5月6号
	 * @return
	 */
	String getPeriodDescEnd();
	
	/**
	 * 判断是否在时间范围内
	 * @param date
	 * @return
	 */
	boolean checkDayIsInInterval(Date date);
}
