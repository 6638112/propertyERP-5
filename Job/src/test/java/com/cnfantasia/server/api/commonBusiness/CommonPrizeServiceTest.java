/**   
* Filename:    CommonPrizeServiceTest.java   
* @version:    1.0  
* Create at:   2015年1月19日 上午11:46:03   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness;

import java.math.BigInteger;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPrizeService;

/**
 * Filename:    CommonPrizeServiceTest.java
 * @version:    1.0.0
 * Create at:   2015年1月19日 上午11:46:03
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月19日       shiyl             1.0             1.0 Version
 */
public class CommonPrizeServiceTest extends BaseTest{
	
//	@Test
//	public void getPrizeRecordByMonth(){
//		ICommonPrizeService commonPrizeService = ctx.getBean("commonPrizeService", ICommonPrizeService.class);
//		System.out.println(commonPrizeService.getPrizeCountSigned());
//		System.out.println(commonPrizeService.getPrizeCountUnSigned());
//	}
	
	@Test
	public void getPrizeRecordByMonth(){
		ICommonPrizeService commonPrizeService = ctx.getBean("commonPrizeService", ICommonPrizeService.class);
		BigInteger userId = new BigInteger("50004");
		Object res = commonPrizeService.getPreMonthStartEndByMonth(userId, "201502");
		Object res2 = commonPrizeService.getPreMonthStartEndByMonth(userId, "201501");
		System.out.println(JSON.toJSONString(res));
		System.out.println(JSON.toJSONString(res2));
		
		
	}
	
//	@Test
//	public void getPrizeFortunesByMonthYear(){
//		IPrizeService prizeService = ctx.getBean("prizeService", IPrizeService.class);
//		BigInteger userId = new BigInteger("50004");
//		PrizeFortunesEntity prizeFortunesEntity = prizeService.getPrizeFortunesByMonthYear(userId,"201501");
//		System.out.println(JSON.toJSONString(prizeFortunesEntity));
//	}
}
