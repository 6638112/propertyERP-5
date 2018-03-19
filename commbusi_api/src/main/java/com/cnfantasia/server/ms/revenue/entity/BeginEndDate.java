package com.cnfantasia.server.ms.revenue.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * 统一起止时间范围的存储类
* Filename:    BeginEndDate.java
* @version:    1.0.0
* Create at:   2015年12月2日 下午2:40:13
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月2日       shiyl             1.0             1.0 Version
 */
public class BeginEndDate {
	private Date begin;
	private Date end;
	public BeginEndDate(){}
	public BeginEndDate(String begin,String end,DateFormat format){
		try {
			if(!StringUtils.isEmpty(begin)){
				this.begin = format.parse(begin);
			}
		} catch (ParseException e) {}
		
		try {
			if(!StringUtils.isEmpty(end)){
				this.end = format.parse(end);
			}
		} catch (ParseException e) {}
	}
	
	public BeginEndDate(Date begin,Date end){
		this.begin = begin;
		this.end = end;
	}
	
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public String getBegin(DateFormat format) {
		return begin==null?null:format.format(begin);
	}
	public String getEnd(DateFormat format) {
		return end==null?null:format.format(end);
	}
	
	public String getBeginYYYY_MM() {
		return getBegin(DateUtil.formatMonth_Split.get());
	}
	public String getEndYYYY_MM() {
		return getEnd(DateUtil.formatMonth_Split.get());
	}
	
	public static boolean isAvailable(BeginEndDate beginEndDate){
		if(beginEndDate==null||beginEndDate.getEnd()==null){
			return false;
		}
		return true;
	}
	
}
