/**   
* Filename:    PropertyPeriodCalculateUtil.java   
* @version:    1.0  
* Create at:   2014年12月2日 上午5:06:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月2日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.entity.StartEndDate;
import com.cnfantasia.server.api.commonBusiness.entity.StartEndTimeDescEntity;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.business.pub.utils.DateUtil;

/**
 * Filename:    PropertyPeriodCalculateUtil.java
 * @version:    1.0.0
 * Create at:   2014年12月2日 上午5:06:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月2日       shiyl             1.0             1.0 Version
 */
public class PropertyPeriodCalculateUtil {
	private static Log logger = LogFactory.getLog(PropertyPeriodCalculateUtil.class);
	
	public static void main(String[] args) throws ParseException {
		getDiscountMonthByNowTime(DateUtil.formatDay.get().parse("2014-1-02"), 20);
		getDiscountMonthByNowTime(DateUtil.formatDay.get().parse("2014-1-22"), 20);
		getDiscountMonthByNowTime(DateUtil.formatDay.get().parse("2014-1-22"), null);
//		System.out.println(DateUtil.formatSecond.get().format(resDate));
//		System.out.println(checkIsInPeriod(DateUtil.formatDay.get().parse("2014-1-05"), 5, 10));
//		System.out.println(checkIsInPeriod(DateUtil.formatDay.get().parse("2014-1-02"), 5, 10));
//		System.out.println(checkIsInPeriod(DateUtil.formatDay.get().parse("2014-1-06"), 5, 10));
//		System.out.println(checkIsInPeriod(DateUtil.formatDay.get().parse("2014-1-12"), 5, 10));
//		System.out.println(checkIsInPeriod(DateUtil.formatDay.get().parse("2014-1-02"), 18,17 ));
//		System.out.println(checkIsInPeriod(DateUtil.formatDay.get().parse("2014-1-06"), 18,17));
//		System.out.println(checkIsInPeriod(DateUtil.formatDay.get().parse("2014-1-12"), 18,17));
//		System.out.println(checkIsInPeriod(DateUtil.formatDay.get().parse("2014-1-10"), 18,17));
//		System.out.println(checkIsInPeriod(DateUtil.formatDay.get().parse("2014-1-9"), 18,17));
		System.out.println(getDiscountMonthByNowTimeStr("2014-2-21 11", 20));
		
		System.out.println(parseDiscountStartEndDate(DateUtil.formatMonth_Split.get().parse("2015-1"), 0));
		System.out.println(parseDiscountStartEndDate(DateUtil.formatMonth_Split.get().parse("2015-2"), 0));
		System.out.println(parseDiscountStartEndDate(DateUtil.formatMonth_Split.get().parse("2015-3"), 0));
//		System.out.println(getPropertyStartEndDate(DateUtil.formatDay.get().parse("2014-12-22"), 20));
//		System.out.println(getPropertyStartEndDate(DateUtil.formatDay.get().parse("2014-12-20"), 20));
//		System.out.println(getPropertyStartEndDate(DateUtil.formatDay.get().parse("2014-12-22"), null));
//		System.out.println(getPropertyStartEndDate(DateUtil.formatDay.get().parse("2014-12-21"), 20));
//		Calendar ca = Calendar.getInstance();
//		ca.setTime(DateUtil.formatDay.get().parse("2014-1-02"));
//		System.out.println(ca.get(Calendar.DAY_OF_MONTH));
//		System.out.println(ca.get(Calendar.MONTH));
		System.out.println(getStartEndTimeDescEntity(DateUtil.formatMonth_Split.get().parse("2015-2"), 1, 0).getStartDesc());
		System.out.println(getStartEndTimeDescEntity(DateUtil.formatMonth_Split.get().parse("2015-2"), 1, 0).getEndDesc());
	}
	
	/**
	 * 根据当前时间获取对应物业周期起止日期
	 * @param userId
	 * @param time 年月日
	 * @return
	 */
	public static StartEndDate getPreMonthStartEndByTime(String time, Integer payPeriodEnd) {
		Date nowDate = null;
		try {
			nowDate = DateUtil.formatDay.get().parse(time);
		} catch (ParseException e) {
			logger.debug("PropertyPeriodCalculateUtil.getPreMonthStartEndByTime.parse cause exception,time is "+time+",payPeriodEnd is "+payPeriodEnd,e);
		}
		return getPreMonthStartEndByTime(nowDate, payPeriodEnd);
	}
	/**
	 * 根据当前时间获取对应物业周期起止日期
	 * @param userId
	 * @param time 年月日
	 * @return
	 */
	public static StartEndDate getPreMonthStartEndByTime(Date nowDate, Integer payPeriodEnd) {
		StartEndDate tmpStartEndDate = null;
		if (payPeriodEnd != null) {
			Date currMonth = getDiscountMonthByNowTime(nowDate, payPeriodEnd);
			tmpStartEndDate = parseDiscountStartEndDate(currMonth, payPeriodEnd);
		} else {
			tmpStartEndDate = parseFirstLastDayByMonthDate(nowDate);
		}
		return tmpStartEndDate;
	}
	
	/**
	 * 通过当前时间获取物业周期对应的月份
	 * @param nowDate
	 * @param periodEnd
	 * @return
	 */
	public static Date getDiscountMonthByNowTime(Date nowDate,Integer periodEnd){
		if(periodEnd==null||periodEnd.compareTo(PlotpropertyConstant.PERID_GBCFG_LIMITEND_DEFAULT)==0){return nowDate;}
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowDate);
		Integer dayInt = ca.get(Calendar.DAY_OF_MONTH);
		if(dayInt>periodEnd){
			ca.add(Calendar.MONTH, 1);
		}
		Date resDate = ca.getTime();
		return resDate;
	}
//	/**
//	 * 通过当前时间获取对应月份的物业周期的起止日期
//	 * @param monthDate
//	 * @param periodEnd
//	 * @return
//	 */
//	public static StartEndDate getPropertyStartEndDate(Date nowDate,Integer periodEnd){
//		StartEndDate resStartEndDate = null;
//		Date currMonth = getPropertyMonthByNowTime(nowDate, periodEnd);
//		resStartEndDate = parsePropertyStartEndDate(currMonth, periodEnd);
//		return resStartEndDate;
//	}
	/**
	 * 将指定月份根据物业周期解析为起止日期
	 * @param currMonth
	 * @param periodEnd
	 * @return
	 */
	public static StartEndDate parseDiscountStartEndDate(Date currMonth,Integer periodEnd){
		StartEndDate resStartEndDate = null;
		if(currMonth!=null&&periodEnd!=null){
			if(periodEnd==null || periodEnd.compareTo(PlotpropertyConstant.PERID_GBCFG_LIMITEND_DEFAULT)==0){
				Date startDate = null;
				{
					Calendar ca = Calendar.getInstance();
					ca.setTime(currMonth);
					ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));
					startDate = ca.getTime();
				}
				Date endDate = null;
				{
					Calendar ca = Calendar.getInstance();
					ca.setTime(currMonth);
					ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
					endDate = ca.getTime();
				}
				resStartEndDate = new StartEndDate(startDate, endDate);
			}else{
				Date startDate = null;
				{
					Calendar ca = Calendar.getInstance();
					ca.setTime(currMonth);
					ca.set(Calendar.DAY_OF_MONTH, periodEnd+1);
					ca.add(Calendar.MONTH, -1);
					startDate = ca.getTime();
				}
				Date endDate = null;
				{
					Calendar ca = Calendar.getInstance();
					ca.setTime(currMonth);
					ca.set(Calendar.DAY_OF_MONTH, periodEnd);
					endDate = ca.getTime();
				}
				resStartEndDate = new StartEndDate(startDate, endDate);
			}
			
		}
		return resStartEndDate;
	}
	/**
	 * 获取自然月的起止日期
	 * @param monthDate
	 * @return
	 */
	public static StartEndDate parseFirstLastDayByMonthDate(Date monthDate){
		StartEndDate resStartEndDate = null;
		if(monthDate!=null){
			Date startDate = null;
			{
				Calendar ca = Calendar.getInstance();
				ca.setTime(monthDate);
				ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));
				startDate = ca.getTime();
			}
			Date endDate = null;
			{
				Calendar ca = Calendar.getInstance();
				ca.setTime(monthDate);
				ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
				endDate = ca.getTime();
			}
			resStartEndDate = new StartEndDate(startDate, endDate);
		}
		return resStartEndDate;
	}
	
	public static Date getDiscountMonthByNowTime(String nowTime,Integer periodEnd){
		Date nowDate = null;
		try {
			nowDate = DateUtil.formatDay.get().parse(nowTime);
		} catch (ParseException e) {
			logger.debug("PropertyPeriodCalculateUtil.getDiscountMonthByNowTime.parse cause exception",e);
		}
		return getDiscountMonthByNowTime(nowDate, periodEnd);
	}
	/**
	 * 
	 * @param nowTime
	 * @param periodEnd
	 * @return 返回年月例如 201401 201412
	 */
	public static String getDiscountMonthByNowTimeStr(String nowTime,Integer periodEnd){
		Date resDate = getDiscountMonthByNowTime(nowTime, periodEnd);
		return getYearMonth(resDate);
	}
	public static String getYearMonth(Date resDate){
		StringBuffer resStr = null;
		if(resDate!=null){
			resStr = new StringBuffer();
			Calendar ca = Calendar.getInstance();
			ca.setTime(resDate);
			resStr.append(ca.get(Calendar.YEAR));
			resStr.append(DateUtil.formateMonth(ca.get(Calendar.MONTH)+1));//注意+1
		}
		return resStr==null?null:resStr.toString();
	}
	
//	/**
//	 * 校验是否在缴费周期内
//	 * @param nowDate
//	 * @param periodStart
//	 * @param periodEnd
//	 * @return
//	 */
//	public static Boolean checkIsInPeriod(Date nowDate,Integer periodStart,Integer periodEnd){
//		Boolean isInPeriod = null;
//		if(nowDate==null||periodStart==null||periodEnd==null){
//			isInPeriod = false;
//		}else{
//			Calendar ca = Calendar.getInstance();
//			ca.setTime(nowDate);
//			Integer dayInt = ca.get(Calendar.DAY_OF_MONTH);
//			if(periodStart<=periodEnd){
//				if(dayInt>=periodStart&&dayInt<=periodEnd){
//					isInPeriod = true;
//				}else{
//					isInPeriod = false;
//				}
//			}else{
//				if(dayInt>=periodStart||dayInt<=periodEnd){
//					isInPeriod = true;
//				}else{
//					isInPeriod = false;
//				}
//			}
//		}
//		return isInPeriod;
//	}
	
//	/**
//	 * 校验是否在缴费周期内
//	 * @param nowDate
//	 * @param periodStart
//	 * @param periodEnd
//	 * @return
//	 */
//	public static Boolean checkIsInPeriod(String nowTime,Integer periodStart,Integer periodEnd){
//		Date nowDate = null;
//		try {
//			nowDate = DateUtil.formatSecond.get().parse(nowTime);
//		} catch (ParseException e) {
//			logger.debug("PropertyPeriodCalculateUtil.checkIsInPeriod.parse cause exception",e);
//		}
//		return checkIsInPeriod(nowDate, periodStart, periodEnd);
//	}
	
	public static StartEndTimeDescEntity getStartEndTimeDescEntity(String date,DateFormat dateFormat,Integer payLimiteStart,Integer payLimiteEnd){
		if(date==null||dateFormat==null){return null;}
		Date currMonth = null;
		try {
			currMonth = dateFormat.parse(date);
		} catch (ParseException e) {}
		if(currMonth==null){return null;}
		return getStartEndTimeDescEntity(currMonth, payLimiteStart, payLimiteEnd);
	}
	
	public static StartEndTimeDescEntity getStartEndTimeDescEntity(Date currMonth,Integer payLimiteStart,Integer payLimiteEnd){
		if(currMonth==null||payLimiteStart==null||payLimiteEnd==null){return null;}
		StartEndTimeDescEntity resStartEndTimeDescEntity = null;
		if(currMonth!=null){
			Integer preMonth = null;
			Integer nowMonth = null;
			Calendar ca = Calendar.getInstance();
			ca.setTime(currMonth);
			nowMonth = ca.get(Calendar.MONTH)+1;
			ca.add(Calendar.MONTH, -1);
			preMonth = ca.get(Calendar.MONTH)+1;
			String startTimeDesc = null;
			String endTimeDesc = null;
			if(payLimiteStart<payLimiteEnd){
				startTimeDesc = nowMonth+"月"+payLimiteStart+"号";
				endTimeDesc = nowMonth+"月"+payLimiteEnd+"号";
			}else{
				startTimeDesc = preMonth+"月"+payLimiteStart+"号";
				endTimeDesc = nowMonth+"月"+payLimiteEnd+"号";
			}
			resStartEndTimeDescEntity = new StartEndTimeDescEntity(startTimeDesc, endTimeDesc);
		}
		return resStartEndTimeDescEntity;
	}
	
	
	
}
