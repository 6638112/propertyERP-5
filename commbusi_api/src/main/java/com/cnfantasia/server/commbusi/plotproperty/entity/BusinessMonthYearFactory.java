package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.entity.StartEndDate;
import com.cnfantasia.server.api.commonBusiness.util.PropertyPeriodCalculateUtil;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.util.PayBillShowUtil;
import com.cnfantasia.server.api.plotproperty.util.PropPayUtil;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.plotproperty.util.BusinessMonthYearSignalUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;


public class BusinessMonthYearFactory{
	private static Log logger = LogFactory.getLog(BusinessMonthYearFactory.class);
	
	public static IBusinessMonthYear createByPayBill(PayBill payBillEntity,GroupBuilding groupBuilding){
		if(payBillEntity==null){return null;}
		IBusinessMonthYear resBMY = null;
		if(PlotpropertyDict.PayBillType_PaytimeType.MONTH.compareTo(payBillEntity.getPaytimeType())==0){//如果为月缴
			resBMY = BusinessMonthYearFactory.newInstanceByBillMonth(PayBillShowUtil.getBillShowMonth(payBillEntity), groupBuilding);//根据账单月份
		}else{//如果为周期缴费
			try {
				Date billStart = DateUtil.formatSecond.get().parse(payBillEntity.getBillMonthStart());
				Date billEnd = DateUtil.formatSecond.get().parse(payBillEntity.getBillMonthEnd());
				Date payStart = DateUtil.formatSecond.get().parse(payBillEntity.getPayDayStart());
				Date payEnd = DateUtil.formatSecond.get().parse(payBillEntity.getPayDayEnd());
				resBMY = BusinessMonthYearFactory.newInstance(billStart, billEnd, payStart, payEnd, groupBuilding);
			} catch (Exception e) {logger.error("BusinessMonthYearFactory.createByPayBill failed;billId:"+payBillEntity.getId(),e);}
		}
		return resBMY;
	}
	
	private static BusinessMonthYearSignal createByPayMonth(Date srcDate,GroupBuilding groupBuilding){
		//syl-add-根据缴费时间获取对应的折扣月份
//		Date discountMonthDate = PropertyPeriodCalculateUtil.getDiscountMonthByNowTime(srcDate, groupBuilding.getPayPeriodEnd());
		Date discountMonthDate = srcDate;
		
		BusinessMonthYearSignal bmy = null;
		//折扣月份
		ISectionDateSignal discountMonth = SectionDateFactory.newInstanceForDiscount(discountMonthDate);
		//账单月份
		Integer distance = BusinessMonthYearSignalUtil.calculateMonthDistance(groupBuilding, DataType.discount, DataType.property);
		ISectionDateSignal billMonth = SectionDateFactory.newInstanceForBillMonth(DateUtil.getNextMonth(discountMonthDate,distance));
		//缴费月份
		Integer payLimiteStart = groupBuilding.getPayPeriodStart();
		Integer payLimiteEnd = groupBuilding.getPayPeriodEnd();
		StartEndDate startEndDate = BusinessMonthYearSignalUtil.getStartEndDate(discountMonthDate, payLimiteStart, payLimiteEnd);
		ISectionDateMulti payTime = SectionDateFactory.newInstanceForPayDay(startEndDate.getStartDate(), startEndDate.getEndDate());
		//其它信息
		boolean canPayProp = PropPayUtil.canPayProp(groupBuilding);
		Integer monthDistance = BusinessMonthYearSignalUtil.getMonthDistance(groupBuilding);
		bmy = new BusinessMonthYearSignal(payTime, billMonth, canPayProp,monthDistance,discountMonth);
		return bmy;
	}
	
	private static BusinessMonthYearSignal createByBillMonth(Date srcDate,GroupBuilding groupBuilding){
		BusinessMonthYearSignal bmy = null;
		//账单月份
		ISectionDateSignal billMonth = SectionDateFactory.newInstanceForBillMonth(srcDate);
		//
		Integer distance = BusinessMonthYearSignalUtil.calculateMonthDistance(groupBuilding, DataType.property, DataType.discount);
		Date discountDate = DateUtil.getNextMonth(srcDate,distance);
		//折扣月份
		ISectionDateSignal discountMonth = SectionDateFactory.newInstanceForDiscount(discountDate);
		//缴费月份
		Integer payLimiteStart = groupBuilding.getPayPeriodStart();
		Integer payLimiteEnd = groupBuilding.getPayPeriodEnd();
		StartEndDate startEndDate = BusinessMonthYearSignalUtil.getStartEndDate(discountDate, payLimiteStart, payLimiteEnd);
		ISectionDateMulti payTime = SectionDateFactory.newInstanceForPayDay(startEndDate.getStartDate(), startEndDate.getEndDate());
		
		boolean canPayProp = PropPayUtil.canPayProp(groupBuilding);
		Integer monthDistance = BusinessMonthYearSignalUtil.getMonthDistance(groupBuilding);
		bmy = new BusinessMonthYearSignal(payTime, billMonth, canPayProp,monthDistance,discountMonth);
		return bmy;
	}
	
	
	public static IBusinessMonthYearSignal newInstanceByBillMonth(String yyyy_mm_dd,GroupBuilding groupBuilding){
		DataType dataType = DataType.property;
		return newInstance(yyyy_mm_dd, dataType, groupBuilding);
	}
	public static IBusinessMonthYearSignal newInstanceByBillMonth(Date srcDate,GroupBuilding groupBuilding){
		DataType dataType = DataType.property;
		return newInstance(srcDate, dataType, groupBuilding);
	}
	
	public static IBusinessMonthYearSignal newInstanceByPayTime(Date srcDate,GroupBuilding groupBuilding){
		DataType dataType = DataType.discount;
		return newInstance(srcDate, dataType, groupBuilding);
	}
//	public static IBusinessMonthYearSignal newInstanceByPayTime(String yearMonthStr,GroupBuilding groupBuilding){
//		DataType dataType = DataType.discount;
//		return newInstance(yearMonthStr, dataType, groupBuilding);
//	}

	
	private static IBusinessMonthYearSignal newInstance(String yyyy_mm_dd,DataType dataType,GroupBuilding groupBuilding){
		Date srcDate = null;
		try {
			srcDate = DateUtil.formatDay.get().parse(yyyy_mm_dd);
		} catch (ParseException e) {
			logger.info("BusinessMonthYear.create cause exception,yearMonthStr is:"+yyyy_mm_dd+",dataType is:"+dataType,e);
			throw new BusinessRuntimeException(e);
		}
		return newInstance(srcDate, dataType, groupBuilding);
	}
	
	private static IBusinessMonthYearSignal newInstance(Date srcDate,DataType dataType,GroupBuilding groupBuilding){
		BusinessMonthYearSignal bmy = null;
		switch (dataType) {
			case discount://折扣月份
				bmy = createByPayMonth(srcDate, groupBuilding);
				break;
			case property://账单月份
				bmy = createByBillMonth(srcDate, groupBuilding);
				break;
		}
		return bmy;
	}
	
	/**
	 * 根据缴费时间获取对应的折扣月份
	 * @param nowTime 格式例如 2015-5-12 18:26:34 或者 2015-5-12
	 * @param periodEnd
	 */
	public static IBusinessMonthYearSignal newInstance(String nowTimeOrMonth,GroupBuilding groupBuilding,MonthOrTime monthOrTime){
		Integer payPeriodEnd = null;
		if(groupBuilding!=null&&groupBuilding.getSignStatus()!=null&&groupBuilding.getSignStatus().compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0
				&&groupBuilding.getPayPeriodStart()!=null&&groupBuilding.getPayPeriodEnd()!=null){
			payPeriodEnd = groupBuilding.getPayPeriodEnd();
		}else{
			
		}
		//获取折扣月份
		Date resDate = null;
		switch (monthOrTime) {
		case time:
			resDate = PropertyPeriodCalculateUtil.getDiscountMonthByNowTime(nowTimeOrMonth, payPeriodEnd);
			break;
		case month:
			try {
				resDate = DateUtil.formatMonth.get().parse(nowTimeOrMonth);
			} catch (ParseException e) {
				logger.info("BusinessMonthYearWithGB.create cause exception,nowTimeOrMonth is:"+nowTimeOrMonth+",monthOrTime is:"+monthOrTime,e);
				throw new BusinessRuntimeException(e);
			}
			break;
		}
		
		BusinessMonthYearSignal bmy = createByPayMonth(resDate, groupBuilding);
		return bmy;
	}
	
	
	/**
	 * 创建周期缴月份信息
	 * @param billStart
	 * @param billEnd
	 * @param payStart
	 * @param payEnd
	 * @param groupBuilding
	 * @return
	 */
	public static IBusinessMonthYearMulti newInstance(Date billStart,Date billEnd,Date payStart,Date payEnd,GroupBuilding groupBuilding){
		ISectionDateMulti billMonth = SectionDateFactory.newInstanceForBillMonth(billStart, billEnd);
		ISectionDateMulti payTime = SectionDateFactory.newInstanceForPayDay(payStart, payEnd);
		boolean canPayProp = PropPayUtil.canPayProp(groupBuilding);
		return new BusinessMonthYearMulti(payTime, billMonth, canPayProp);
	}
	
	public static IBusinessMonthYearMulti newInstance(Date billStart,Date billEnd,Date payStart,Date payEnd,GroupBuilding groupBuilding,Date payTimeParam){
		ISectionDateMulti billMonth = SectionDateFactory.newInstanceForBillMonth(billStart, billEnd);
		ISectionDateMulti payTime = SectionDateFactory.newInstanceForPayDay(payStart, payEnd);
		ISectionDateSignal discountMonth = SectionDateFactory.newInstanceForDiscount(payTimeParam);
		boolean canPayProp = PropPayUtil.canPayProp(groupBuilding);
		return new BusinessMonthYearMulti(payTime, billMonth, canPayProp,discountMonth);
	}
	
}
