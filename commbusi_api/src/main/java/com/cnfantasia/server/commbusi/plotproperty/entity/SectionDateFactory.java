package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.text.DateFormat;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.plotproperty.util.SectionDateConvertUtil;

/**
 * 账单月份工厂类
* Filename:    BillMonthFactory.java
* @version:    1.0.0
* Create at:   2015年12月11日 下午4:45:22
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月11日       shiyl             1.0             1.0 Version
 */
public class SectionDateFactory {
	/**
	 * 创建账单月份,精确到月
	 */
	public static ISectionDateSignal newInstanceForBillMonth(Date date){
		DateFormat dateFormat = DateUtil.formatMonth.get();
		Date dateRes = ps(date, dateFormat);
		return new SectionDateSignal(dateRes);
	}
	public static ISectionDateSignal newInstanceForBillMonth(String yyyyMM){
		DateFormat dateFormat = DateUtil.formatMonth.get();
		Date dateRes = ps(yyyyMM, dateFormat);
		return new SectionDateSignal(dateRes);
	}
	public static ISectionDateMulti newInstanceForBillMonth(Date startDate,Date endDate){
		DateFormat dateFormat = DateUtil.formatMonth.get();
		Date startDateRes =  ps(startDate, dateFormat);
		Date endDateRes =  ps(endDate, dateFormat);
		return new SectionDateMulti(startDateRes, endDateRes);
	}
	public static ISectionDateMulti newInstanceForBillMonth(String yyyyMMStart,String yyyyMMEnd){
		DateFormat dateFormat = DateUtil.formatMonth.get();
		Date startDateRes = ps(yyyyMMStart, dateFormat);
		Date endDateRes = ps(yyyyMMEnd, dateFormat);
		return new SectionDateMulti(startDateRes, endDateRes);
	}
	
	/**
	 * 创建折扣月份,精确到月
	 */
	public static ISectionDateSignal newInstanceForDiscount(Date date){
		DateFormat dateFormat = DateUtil.formatMonth.get();
		Date dateRes = ps(date, dateFormat);
		return new SectionDateSignal(dateRes);
	}
	public static ISectionDateSignal newInstanceForDiscount(String yyyyMM){
		DateFormat dateFormat = DateUtil.formatMonth.get();
		Date dateRes = ps(yyyyMM, dateFormat);
		return new SectionDateSignal(dateRes);
	}
	
	/**
	 * 创建缴费区间,精确到日
	 */
	public static ISectionDateMulti newInstanceForPayDay(Date startDate,Date endDate){
		DateFormat dateFormat = DateUtil.formatDay.get();
		Date startDateRes =  ps(startDate, dateFormat);
		Date endDateRes =  ps(endDate, dateFormat);
		return new SectionDateMulti(startDateRes, endDateRes);
	}
	public static ISectionDateMulti newInstanceForPayDay(String yyyyMMStart,String yyyyMMEnd){
		DateFormat dateFormat = DateUtil.formatDay.get();
		Date startDateRes = ps(yyyyMMStart, dateFormat);
		Date endDateRes = ps(yyyyMMEnd, dateFormat);
		return new SectionDateMulti(startDateRes, endDateRes);
	}
	
	/**
	 * 创建折扣区间,精确到日
	 */
	public static ISectionDateMulti newInstanceForDiscountDay(Date startDate,Date endDate){
		DateFormat dateFormat = DateUtil.formatDay.get();
		Date startDateRes =  ps(startDate, dateFormat);
		Date endDateRes =  ps(endDate, dateFormat);
		return new SectionDateMulti(startDateRes, endDateRes);
	}
	
	
	private static Date ps(String yyyyMMStart,DateFormat dateFormat){
		return SectionDateConvertUtil.formateYYYYMM(yyyyMMStart,dateFormat);
	}
	private static Date ps(Date date,DateFormat dateFormat){
		return SectionDateConvertUtil.formateYYYYMM(date,dateFormat);
	}
	
}
