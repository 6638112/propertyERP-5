/**   
 * Filename:    OrderNoGenerator.java   
 * @version:    1.0  
 * Create at:   2014年6月9日 上午8:53:18   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月9日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.ebuy.util;

import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.DateUtil;

/**
 * Filename: OrderNoGenerator.java
 * 
 * @version: 1.0.0 Create at: 2014年6月9日 上午8:53:18 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月9日 shiyl 1.0 1.0 Version
 */
public class OrderNoGenerator {
	public static void main(String[] args) {
		System.out.println(getOrderNo(new BigInteger("999")));
	}
	
//	public static String getOrderNo(BigInteger orderId) {
//		// ---------------生成订单号 开始------------------------
//		// 当前时间 yyyyMMddHHmmss
//		Date now = new Date();
//		DateFormat outFormat = DateUtil.formatSecondTogether.get();
//		String currTime = outFormat.format(now);
//		// 四位随机数
//		String strRandom = RandomUtils.createRandom(true, 4) + "";
//		// ---------------生成订单号 结束------------------------
//		return new StringBuffer().append(currTime).append(strRandom).append(orderId).toString();
//	}
	
	public static String getOrderNo(BigInteger orderId) {
		// ---------------生成订单号 开始------------------------
		// 当前时间 yyyyMMddHHmmss
		Date now = new Date();
		DateFormat outFormat = DateUtil.formatMinuteTogether.get();
		String currTime = outFormat.format(now);
		// ---------------生成订单号 结束------------------------
		return new StringBuffer().append(currTime).append(orderId).toString();
	}
	
}
