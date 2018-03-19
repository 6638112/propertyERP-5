package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.util.Date;


/**
 * 多月账单数据
* Filename:    BusinessMonthYearMulti.java
* @version:    1.0.0
* Create at:   2015年12月11日 下午8:29:47
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月11日       shiyl             1.0             1.0 Version
 */
public class BusinessMonthYearMulti implements IBusinessMonthYearMulti{
	/**所在账单月份*/
	private ISectionDateMulti billMonth;
	/**所在缴费区间*/
	private ISectionDateMulti payTime;
	
//	/**小区的账单月份与折扣月份的相差月份,账单月份-当前折扣月份= 返回值*/
//	private Integer monthDistance;
	
	/**用户传入的查询月份信息的对应的折扣月份*/
	public ISectionDateSignal discountMonth;
	
	/**是否可缴物业费*/
	private boolean canPayProp;
	
	@Override
	public ISectionDateMulti getBillMonth(){
		return billMonth;
	}
	
	public BusinessMonthYearMulti(ISectionDateMulti payTime,ISectionDateMulti billMonth,boolean canPayProp){
		this.payTime = payTime;
		this.billMonth = billMonth;
		this.canPayProp = canPayProp;
	}
	
	public BusinessMonthYearMulti(ISectionDateMulti payTime,ISectionDateMulti billMonth,boolean canPayProp,ISectionDateSignal discountMonth){
		this.payTime = payTime;
		this.billMonth = billMonth;
		this.canPayProp = canPayProp;
		this.discountMonth = discountMonth;
	}
	
	@Override
	public boolean getCanPayProp() {
		return canPayProp;
	}
	
	@Override
	public String getPayTimeStartDesc() {
		return payTime.getPeriodDescStart();
	}

	@Override
	public String getPayTimeEndDesc() {
		return payTime.getPeriodDescEnd();
	}

	@Override
	public Long getDistanceDisc2PropMonthLong() {
		return 0L;//TODO ..
	}

	@Override
	public Integer getDistanceDisc2PropMonthCount() {
		return 0;//TODO ..
	}

	@Override
	public ISectionDateMulti getPayTime() {
		return payTime;
	}

	@Override
	public ISectionDateSignal getDiscountMonth() {
		return discountMonth;
	}

	@Override
	public boolean isInPayTime(Date date) {
		return payTime.checkDayIsInInterval(date);
	}
	
}
