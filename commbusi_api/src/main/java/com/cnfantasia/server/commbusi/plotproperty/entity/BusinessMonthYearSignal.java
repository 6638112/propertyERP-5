package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.text.DateFormat;
import java.util.Date;

/**
 * 单月账单对应数据
* Filename:    BusinessMonthYearSignal.java
* @version:    1.0.0
* Create at:   2015年12月11日 下午8:29:14
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月11日       shiyl             1.0             1.0 Version
 */
public class BusinessMonthYearSignal implements IBusinessMonthYearSignal{
	/**所在账单月份*/
	private ISectionDateSignal billMonth;
	/**所在缴费区间*/
	private ISectionDateMulti payTime;
	/**所在折扣月份*/
	private ISectionDateSignal discountMonth;
	
	/**小区的账单月份与折扣月份的相差月份,账单月份-当前折扣月份= 返回值*/
	private Integer monthDistance;
	
	/**是否可缴物业费*/
	private boolean canPayProp;

	@Override
	public ISectionDateSignal getBillMonth(){
		return billMonth;
	}
	
//	private Date getTimeDate(DataType dataType){//TODO 部分业务需要调整
//		GroupBuilding groupBuilding = this.groupBuilding;
//		Integer distance = BusinessMonthYearSignalUtil.calculateMonthDistance(groupBuilding, this.dataType, dataType);
//		Date resDate = DateUtil.getNextMonth(this.date,distance);
//		return resDate;
//	}
	
	@Override
	public Long getPropertyYearMonthLong(){
		return billMonth.getYearMonthLong();
//		return this.getTimeDate(DataType.property).getTime();//部分业务需要调整
	}
	
	@Override
	public Long getDiscountYearMonthLong(){
		return discountMonth.getYearMonthLong();
//		return this.getTimeDate(DataType.discount).getTime();//部分业务需要调整
	}
	
	public BusinessMonthYearSignal(ISectionDateMulti payTime,ISectionDateSignal billMonth,boolean canPayProp,Integer monthDistance,ISectionDateSignal discountMonth){
		this.payTime = payTime;
		this.billMonth = billMonth;
		this.canPayProp = canPayProp;
		this.monthDistance = monthDistance;
		this.discountMonth = discountMonth;
	}
	
	
//	private Date date;
//	private DataType dataType;
//	
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
//	public DataType getDataType() {
//		return dataType;
//	}
//	public void setDataType(DataType dataType) {
//		this.dataType = dataType;
//	}
	

	@Override
	public Long getDistanceDisc2PropMonthLong() {
//		Date discountDate = this.getTimeDate(DataType.discount);
//		Date propertyDate = this.getTimeDate(DataType.property);
//		return (discountDate!=null&&propertyDate!=null)?(discountDate.getTime()-propertyDate.getTime()):0L;
		return discountMonth.getYearMonthLong()-billMonth.getYearMonthLong();
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
	public ISectionDateMulti getPayTime() {
//		StartEndDate tmpStartEndDate = null;
//		Date nowMonth = getTimeDate(DataType.discount);//使用折扣月份
//		GroupBuilding groupBuilding = this.groupBuilding;
//		if(groupBuilding!=null&&groupBuilding.getSignStatus()!=null&&groupBuilding.getSignStatus().compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0
//				&&groupBuilding.getPayPeriodStart()!=null&&groupBuilding.getPayPeriodEnd()!=null){
//			tmpStartEndDate = PropertyPeriodCalculateUtil.parseDiscountStartEndDate(nowMonth, groupBuilding.getPayPeriodEnd());
//		}else{
//			tmpStartEndDate = PropertyPeriodCalculateUtil.parseFirstLastDayByMonthDate(nowMonth);
//		}
//		return SectionDateFactory.newInstanceForDiscountDay(tmpStartEndDate.getStartDate(), tmpStartEndDate.getEndDate());
		return payTime;
	}
	
	@Override
	public boolean getCanPayProp() {
		return canPayProp;
	}
	
//	@Override
//	public String getTimeStr(DataType dataType){
//		GroupBuilding groupBuilding = this.groupBuilding;
//		return DateUtil.formatMonth.get().format(getTimeDate(groupBuilding, dataType));
//	}
//	
//	@Override
//	public StartEndTimeDescEntity getStartEndTimeDescEntity(DataType dataType){
//		return getStartEndTimeDescEntity(groupBuilding,dataType);
//	}
	
	@Override
	public Integer getDistanceDisc2PropMonthCount(){
		return monthDistance;
	}

	@Override
	public String getBillMonthStr(DateFormat dateFormat) {
		return billMonth.getTimeStr(dateFormat);
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
