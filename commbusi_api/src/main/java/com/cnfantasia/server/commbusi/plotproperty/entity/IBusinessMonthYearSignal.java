package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.text.DateFormat;


/**
 * 单个月份的账单
* Filename:    IBusinessMonthYearSignal.java
* @version:    1.0.0
* Create at:   2015年12月11日 下午7:42:07
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月11日       shiyl             1.0             1.0 Version
 */
public interface IBusinessMonthYearSignal extends IBusinessMonthYear{
	
	/**
	 * 获取账单月份
	 * @return
	 */
	public ISectionDateSignal getBillMonth();
	
	/**
	 * 获取账单月份Long
	 * @return
	 */
	public Long getPropertyYearMonthLong();
	
	/**
	 * 获取折扣月份Long
	 * @return
	 */
	public Long getDiscountYearMonthLong();
	
	/**
	 * 获取指定格式的字符串
	 * @param dateFormat
	 * @return
	 */
	public String getBillMonthStr(DateFormat dateFormat);
	
}
