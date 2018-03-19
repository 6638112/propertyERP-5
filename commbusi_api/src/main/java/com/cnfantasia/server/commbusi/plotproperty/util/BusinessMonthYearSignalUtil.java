package com.cnfantasia.server.commbusi.plotproperty.util;

import java.util.Calendar;
import java.util.Date;

import com.cnfantasia.server.api.commonBusiness.entity.StartEndDate;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.api.room.constant.GroupBuildingDict;
import com.cnfantasia.server.commbusi.plotproperty.entity.DataType;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

public class BusinessMonthYearSignalUtil {
	
	
	/**
	 * 计算不同类型月份的差值
	 * @param groupBuilding
	 * @param srcDataType
	 * @param goalDataType
	 * @return 原始-目标=结果
	 */
	public static Integer calculateMonthDistance(GroupBuilding groupBuilding,DataType srcDataType,DataType goalDataType){
		Integer distance = null;
		switch (srcDataType) {
//		case base:
		case discount:
			switch (goalDataType){
//			case base:
			case discount:
				distance = 0;
				break;
			case property:
				distance = (1)*getMonthDistance(groupBuilding);
				break;
			}
			break;
		case property:
			switch (goalDataType){
//			case base:
			case discount:
				distance = (-1)*getMonthDistance(groupBuilding);
				break;
			case property:
				distance = 0;
				break;
			}
			break;
		}
		return distance;
	}

	
	/**
	 * 获取小区的账单月份与折扣月份的相差月份
	 * @return 账单月份-当前折扣月份= 返回值
	 */
	public static Integer getMonthDistance(GroupBuilding currGroupBuilding){
		Integer res = 0;
		//动态判断物业周期
		if(currGroupBuilding!=null&&currGroupBuilding.getPropertyMonthChange()!=null){
			//目前限定最多只支持跨一个月，其他均视为不跨月,后续可拓展
			/*if(GroupBuildingDict.GroupBuilding_PropertyMonthChange.NextMonth.compareTo(currGroupBuilding.getPropertyMonthChange())==0
						||GroupBuildingDict.GroupBuilding_PropertyMonthChange.PreMonth.compareTo(currGroupBuilding.getPropertyMonthChange())==0){
				res = currGroupBuilding.getPropertyMonthChange();
			}else{//其它的也支持
				res = currGroupBuilding.getPropertyMonthChange();
			}*/
			res = currGroupBuilding.getPropertyMonthChange();
		}
		return res;
	}
	
//	public static void main(String[] args) throws ParseException {
//		Date currMonth = DateUtil.formatMonth.get().parse("201503");
//		StartEndDate StartEndDate = getStartEndDate(currMonth, 9, 6);
//		System.out.println(DateUtil.formatDay.get().format(StartEndDate.getStartDate()));
//		System.out.println(DateUtil.formatDay.get().format(StartEndDate.getEndDate()));
//	}
	
	public static StartEndDate getStartEndDate(Date currMonth,Integer payLimiteStart,Integer payLimiteEnd){
//		if(currMonth==null||payLimiteStart==null||payLimiteEnd==null){return null;}
		if (currMonth == null) {
			return null;
		}
		/**修复6月份只有三十天，但是配置缴费窗口为1-31的问题，对payLimiteEnd做一个限制*/
		int currentMonthDay = getCurrentMonthDay();
		if(payLimiteEnd!=null && payLimiteEnd > currentMonthDay) {
			payLimiteEnd = currentMonthDay;
		}
		
		StartEndDate resStartEndDate = null;
		Date startDate = null;
		Date endDate = null;
		if (payLimiteStart == null || payLimiteEnd == null
				|| payLimiteEnd.compareTo(PlotpropertyConstant.PERID_GBCFG_LIMITEND_DEFAULT) == 0) {
			Calendar srcCa = Calendar.getInstance();
			srcCa.setTime(currMonth);
			srcCa.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
			startDate = srcCa.getTime();
			srcCa.set(Calendar.DAY_OF_MONTH, srcCa.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate = srcCa.getTime();
		} else if (payLimiteStart < payLimiteEnd) {//nowMonth,payLimiteStart->nowMonth,payLimiteEnd
			Calendar srcCa = Calendar.getInstance();
			srcCa.setTime(currMonth);
			srcCa.set(Calendar.DAY_OF_MONTH, payLimiteStart);
			startDate = srcCa.getTime();
			srcCa.set(Calendar.DAY_OF_MONTH, payLimiteEnd);
			endDate = srcCa.getTime();
		} else {//preMonth,payLimiteStart->nowMonth,payLimiteEnd
			Calendar srcCa = Calendar.getInstance();
			srcCa.setTime(currMonth);
			srcCa.add(Calendar.MONTH, -1);//上月
			srcCa.set(Calendar.DAY_OF_MONTH, payLimiteStart);
			startDate = srcCa.getTime();
			srcCa.add(Calendar.MONTH, 1);//恢复本月
			srcCa.set(Calendar.DAY_OF_MONTH, payLimiteEnd);
			endDate = srcCa.getTime();
		}
		resStartEndDate = new StartEndDate(startDate, endDate);
		return resStartEndDate;
	}
	
	/** 
     * 获取当月的 天数 
     * */  
    public static int getCurrentMonthDay() {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
	
}
