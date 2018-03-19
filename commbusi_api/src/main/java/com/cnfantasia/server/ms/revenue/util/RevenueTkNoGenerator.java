package com.cnfantasia.server.ms.revenue.util;

import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.DateUtil;

/**
 * 提款单号生成方式
* Filename:    RevenueTkNoGenerator.java
* @version:    1.0.0
* Create at:   2015年11月20日 下午2:09:20
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月20日       shiyl             1.0             1.0 Version
 */
public class RevenueTkNoGenerator {
	public static void main(String[] args) {
		System.out.println(getOrderNo(new BigInteger("999")));
	}
	
//	public static String getOrderNo(BigInteger orderId) {
//		// ---------------生成提款单号 开始------------------------
//		// 当前时间 yyyyMMddHHmmss
//		Date now = new Date();
//		DateFormat outFormat = DateUtil.formatSecondTogether.get();
//		String currTime = outFormat.format(now);
//		// 四位随机数
//		String strRandom = RandomUtils.createRandom(true, 4) + "";
//		// ---------------生成提款单号 结束------------------------
//		return new StringBuffer().append(currTime).append(strRandom).append(orderId).toString();
//	}
	
	public static String getOrderNo(BigInteger orderId) {
		// ---------------生成提款单号 开始------------------------
		// 当前时间 yyyyMMddHHmmss
		Date now = new Date();
		DateFormat outFormat = DateUtil.formatMinuteTogether.get();
		String currTime = outFormat.format(now);
		// ---------------生成提款单号 结束------------------------
		return new StringBuffer().append(currTime).append(orderId).toString();
	}
	
}
