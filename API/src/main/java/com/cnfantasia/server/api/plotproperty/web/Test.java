/**   
 * Filename:    Test.java   
 * @version:    1.0  
 * Create at:   2014年12月18日 上午1:39:26   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年12月18日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.plotproperty.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cnfantasia.server.api.commonBusiness.entity.StartEndDate;
import com.cnfantasia.server.api.commonBusiness.util.PropertyPeriodCalculateUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

/**
 * Filename: Test.java
 * 
 * @version: 1.0.0 Create at: 2014年12月18日 上午1:39:26 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年12月18日 shiyl 1.0 1.0 Version
 */
public class Test {
	public static void main(String[] args) throws ParseException {
		Boolean isInPayCycle = false;

		String nowTime = "2014-12-18 09:40:34";
		int payLimiteEnd = 17;
		StartEndDate startEndDate = getPreMonthStartEndByTime(nowTime, payLimiteEnd);
		System.out.println(startEndDate);
		// String payBillMonth = "2014-12-00 00:00:00";
		String payBillMonth = "2015-01-00 00:00:00";;
		Date payBillMonthDate = new SimpleDateFormat("yyyy-MM").parse(payBillMonth);
		
		if (payBillMonthDate != null && startEndDate != null && startEndDate.getStartDate() != null
				&& startEndDate.getEndDate() != null) {
			if (payBillMonthDate.getTime() >= startEndDate.getStartDate().getTime()
					&& payBillMonthDate.getTime() <= startEndDate.getEndDate().getTime()) {
				isInPayCycle = true;
			}
		}
		System.out.println(isInPayCycle);
		String proprietorName = "小明";
		System.out.println(proprietorName = proprietorName.substring(0, 1)+"**");
	}

	/**
	 * 根据当前时间获取对应物业周期起止日期
	 * 
	 * @param userId
	 * @param time
	 *          年月日
	 * @return
	 */
	private static StartEndDate getPreMonthStartEndByTime(String time, Integer payPeriodEnd) {
		StartEndDate tmpStartEndDate = null;
		Date nowDate = null;
		try {
			nowDate = DateUtil.formatDay.get().parse(time);
		} catch (ParseException e) {
			throw new BusinessRuntimeException("CommonPrizeService.getPreMonthStartEnd.parseMonth.error",
					new Object[] { time }, e);
		}
		if (payPeriodEnd != null) {
			Date currMonth = PropertyPeriodCalculateUtil.getDiscountMonthByNowTime(nowDate, payPeriodEnd);
			tmpStartEndDate = PropertyPeriodCalculateUtil.parseDiscountStartEndDate(currMonth, payPeriodEnd);
		} else {
			tmpStartEndDate = PropertyPeriodCalculateUtil.parseFirstLastDayByMonthDate(nowDate);
		}
		return tmpStartEndDate;
	}
}
