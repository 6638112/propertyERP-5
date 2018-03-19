/**   
* Filename:    DeliveryNoGenerator.java   
* @version:    1.0  
* Create at:   2014年7月4日 上午7:40:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月4日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.util;

import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.DateUtil;

/**
 * Filename:    DeliveryNoGenerator.java
 * @version:    1.0.0
 * Create at:   2014年7月4日 上午7:40:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月4日       shiyl             1.0             1.0 Version
 */
public class DeliveryNoGenerator {
	public static void main(String[] args) {
		System.out.println(DeliveryNoGenerator.getDeliveryNo(new BigInteger("50001"),new BigInteger("50001")) );
	}
	public static String getDeliveryNo(BigInteger deliveryOrderId,BigInteger supplyMerchantId) {
		// ---------------生成配送单号 开始------------------------
		// 当前时间 yyyyMMddHHmmss
		Date now = new Date();
		DateFormat outFormat = DateUtil.formatMinuteTogether.get();
		String currTime = outFormat.format(now);
		// ---------------生成配送单号 结束------------------------
		return new StringBuffer().append("P").append(currTime).append(deliveryOrderId).toString();
	}
	
//	public static String getDeliveryNo(BigInteger deliveryOrderId,BigInteger supplyMerchantId) {
//		// ---------------生成配送单号 开始------------------------
//		// 当前时间 yyyyMMddHHmmss
//		Date now = new Date();
//		DateFormat outFormat = DateUtil.formatSecondTogether.get();
//		String currTime = outFormat.format(now);
//		// 四位随机数
//		String strRandom = RandomUtils.createRandom(true, 4) + "";
//		// ---------------生成配送单号 结束------------------------
//		return new StringBuffer().append("P").append(supplyMerchantId.toString()).append(currTime.substring(4)).append(strRandom).append(deliveryOrderId).toString();
//	}
	
}
