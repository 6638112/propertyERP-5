package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.util.Date;


/**
 * 折扣账单月份信息实体类
* Filename:    IBusinessMonthYear.java
* @version:    1.0.0
* Create at:   2015年12月11日 上午10:09:00
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月11日       shiyl             1.0             1.0 Version
 */
public interface IBusinessMonthYear {
	
//	/**
//	 * 获取指定类型的日期
//	 * @param dataType
//	 * @return
//	 */
//	public Date getTimeDate(DataType dataType);
	
	/**
	 * 获取账单月份
	 * @return
	 */
	DataType.ISectionDate getBillMonth();
	
	/**
	 * 获取折扣月份
	 * @return
	 */
	ISectionDateSignal getDiscountMonth();
	
	/**
	 * 获取日期差值，整月为单位，返回对应的时间毫秒表示
	 * 获取 折扣月份-账单月份 的Long具体
	 * @return
	 */
	Long getDistanceDisc2PropMonthLong();
	
	/**
	 * 获取相差月份数
	 * 物业折扣月份相差月份数 折扣月份=物业账单月份+相差月份数
	 * @return
	 */
	Integer getDistanceDisc2PropMonthCount();
	
	/**
	 * 是否可缴物业费
	 * @return
	 */
	boolean getCanPayProp();
	
	/**
	 * 获取折扣月份起止区间
	 * @return
	 */
	ISectionDateMulti getPayTime();
	
	/**
	 * 是否在缴费时间内
	 * @return
	 */
	boolean isInPayTime(Date date);
	
//	/**
//	 * 获取起止时间描述
//	 * @param dataType
//	 * @return
//	 */
//	public StartEndTimeDescEntity getStartEndTimeDescEntity(DataType dataType);
	
	/**
	 * 缴费开始时间描述
	 * @return
	 */
	String getPayTimeStartDesc();
	
	
	/**
	 * 缴费截止时间描述
	 * @return
	 */
	String getPayTimeEndDesc();
	
}
