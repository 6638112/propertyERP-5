package com.cnfantasia.server.commbusi.plotproperty.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

/**
 * 日期转换工具类
* Filename:    SectionDateConvertUtil.java
* @version:    1.0.0
* Create at:   2015年12月11日 下午6:52:18
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月11日       shiyl             1.0             1.0 Version
 */
public class SectionDateConvertUtil {
	public static Date formateYYYYMM(Date srcDate,DateFormat dateFormat){
		String yyyyMM = dateFormat.format(srcDate);
		Date date = null;
		try {
			date =  dateFormat.parse(yyyyMM);
		} catch (ParseException e) {
			throw new BusinessRuntimeException(e);
		}
		return date;
	}
	
	public static Date formateYYYYMM(String yyyyMM,DateFormat dateFormat){
		Date date = null;
		try {
			date =  dateFormat.parse(yyyyMM);
		} catch (ParseException e) {
			throw new BusinessRuntimeException(e);
		}
		return date;
	}
	
	/**
	 * 2015.05
	 * @param tmpDate
	 * @return
	 */
	public static String getPeriodDesc(Date tmpDate) {
		Integer nowYear = null;
		Integer nowMonth = null;
		Calendar ca = Calendar.getInstance();
		ca.setTime(tmpDate);
		nowMonth = ca.get(Calendar.MONTH)+1;
		nowYear = ca.get(Calendar.YEAR);
		String desc = nowYear+"."+DateUtil.formateMonth(nowMonth);
		return desc;
	}
	
	/**
	 * 6月5号
	 * @param tmpDate
	 * @return
	 */
	public static String getPeriodDescYueHao(Date tmpDate){
//		Integer nowMonth = null;
//		Integer nowDay = null;
//		Calendar ca = Calendar.getInstance();
//		ca.setTime(tmpDate);
//		nowMonth = ca.get(Calendar.MONTH)+1;
//		ca.add(Calendar.MONTH, -1);
//		nowDay = ca.get(Calendar.DAY_OF_MONTH);
//		String desc = nowMonth+"月"+nowDay+"号";
//		return desc;
		return DateUtil.formatDay_lean.get().format(tmpDate);
	}
	
}
