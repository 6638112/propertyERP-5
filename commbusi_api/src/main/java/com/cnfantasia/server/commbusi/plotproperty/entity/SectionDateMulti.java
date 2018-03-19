package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.plotproperty.util.SectionDateConvertUtil;

/**
 * 区间账单月份
* Filename:    SectionDateMulti.java
* @version:    1.0.0
* Create at:   2015年12月11日 下午4:24:51
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月11日       shiyl             1.0             1.0 Version
 */
public class SectionDateMulti implements ISectionDateMulti{
	/**开始月份*/
	private Date startDate;
	/**截止月份*/
	private Date endDate;
	
	protected SectionDateMulti(Date startDate,Date endDate){
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	@Override
	public String getPeriodDesc() {
		return SectionDateConvertUtil.getPeriodDesc(startDate)+"-"+SectionDateConvertUtil.getPeriodDesc(endDate);
	}

	@Override
	public Date getStartYearMonth() {
		DateFormat dateFormat = DateUtil.formatMonth.get();
		return SectionDateConvertUtil.formateYYYYMM(startDate,dateFormat);
	}

	@Override
	public Date getEndYearMonth() {
		DateFormat dateFormat = DateUtil.formatMonth.get();
		return SectionDateConvertUtil.formateYYYYMM(endDate,dateFormat);
	}

	@Override
	public String getStartYearMonthDay() {
		return getYearMonthDay(startDate);
	}

	@Override
	public String getEndYearMonthDay() {
		return getYearMonthDay(endDate);
	}
	
	private static String getYearMonthDay(Date resDate){
		StringBuffer resStr = null;
		if(resDate!=null){
			resStr = new StringBuffer();
			Calendar ca = Calendar.getInstance();
			ca.setTime(resDate);
			resStr.append(ca.get(Calendar.YEAR));
			resStr.append(DateUtil.formateMonth(ca.get(Calendar.MONTH)+1));//注意+1
			resStr.append(DateUtil.formateDay(ca.get(Calendar.DAY_OF_MONTH)));
		}
		return resStr==null?null:resStr.toString();
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	@Override
	public String getPeriodDescStart() {
		return SectionDateConvertUtil.getPeriodDescYueHao(startDate);
	}

	@Override
	public String getPeriodDescEnd() {
		return SectionDateConvertUtil.getPeriodDescYueHao(endDate);
	}

	/**
	 * 判断对应日期是否在当前时间区间内
	 * @param date
	 * @return
	 */
	@Override
	public boolean checkDayIsInInterval(Date date){
		boolean res = false;
		Date dayInfo = null;
		try {
			String dayStr = DateUtil.formatDay.get().format(date);
			dayInfo = DateUtil.formatDay.get().parse(dayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (dayInfo != null && startDate != null && endDate != null) {
			if (dayInfo.getTime() >= startDate.getTime()
					&& dayInfo.getTime() <= endDate.getTime()) {
				res = true;
			}
		}
		return res;
	}
	
}
