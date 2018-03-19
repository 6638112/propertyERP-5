/**   
* Filename:    TestPrizeService.java   
* @version:    1.0  
* Create at:   2014年6月16日 上午9:10:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.math.BigInteger;

import org.junit.Test;

import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    TestPrizeService.java
 * @version:    1.0.0
 * Create at:   2014年6月16日 上午9:10:30
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月16日       shiyl             1.0             1.0 Version
 */
public class TestPrizeService extends BaseTest{
	
	@Test
	public void getPrizeDoneCount(){
		IPrizeService prizeService = ctx.getBean("prizeService", IPrizeService.class);
		BigInteger userId =new BigInteger("50001");
		Object res = prizeService.getCurrPrizeStateIsLogin(userId);
		System.out.println(res);
	}
	
//	@Test
//	public void getPrizeDoneCountNotLogin(){
//		IPrizeService prizeService = ctx.getBean("prizeService", IPrizeService.class);
//		String deviceId ="9A2E7ABD-5386-40D8-95AE-DE5B67088C99";
//		Long deviceType =2L;
//		int res = prizeService.getPrizeDoneCount(deviceId, deviceType);
//		System.out.println(res);
//	}
	
//	@Test
//	public void getPrizeRecordByMonth(){
//		IPrizeService prizeService = ctx.getBean("prizeService", IPrizeService.class);
//		BigInteger userId = new BigInteger("50018");
//		String month = "201407";
//		PageModel pageModel = new PageModel(1, 10);
//		List<PrizeRecordEntity> res = prizeService.getPrizeRecordByMonth(userId, month, pageModel);
//		System.out.println(res);
//	}
	
}
