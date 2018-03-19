package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.text.DateFormat;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.plotproperty.util.SectionDateConvertUtil;

/**
 * 单月的账单月份
* Filename:    SectionDateSignal.java
* @version:    1.0.0
* Create at:   2015年12月11日 下午4:23:24
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月11日       shiyl             1.0             1.0 Version
 */
public class SectionDateSignal implements ISectionDateSignal{
	private Date date;
	protected SectionDateSignal(Date date){
		this.date = date;
	}
	
	@Override
	public String getMonth() {
		return DateUtil.formatOnlyMonth.get().format(date);
	}

	@Override
	public String getYear() {
		return DateUtil.formatOnlyYear.get().format(date);
	}

	@Override
	public String getPeriodDesc() {
		return SectionDateConvertUtil.getPeriodDesc(date);
	}
	
	@Override
	public Long getYearMonthLong() {
		DateFormat dateFormat = DateUtil.formatMonth.get();
		return SectionDateConvertUtil.formateYYYYMM(date,dateFormat).getTime();
	}

	@Override
	public String getTimeStr(DateFormat dateFormat) {
		return dateFormat.format(date);
	}

}
