/**   
* Filename:    TestPrizeRecordTmpDataService.java   
* @version:    1.0  
* Create at:   2015年7月15日 上午11:51:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.math.BigInteger;

import org.junit.Test;

import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    TestPrizeRecordTmpDataService.java
 * @version:    1.0.0
 * Create at:   2015年7月15日 上午11:51:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月15日       shiyl             1.0             1.0 Version
 */
public class TestPrizeRecordTmpDataService extends BaseTest{
	
	@Test
	public void test(){
		IPrizeRecordTmpDataService prizeRecordTmpDataService = ctx.getBean("prizeRecordTmpDataService", IPrizeRecordTmpDataService.class);
		prizeRecordTmpDataService.transferTmpPrizeData2Formal(new BigInteger("123"));
//		new Thread(){
//			IPrizeRecordTmpDataService prizeRecordTmpDataService = ctx.getBean("prizeRecordTmpDataService", IPrizeRecordTmpDataService.class);
//			@Override
//			public void run() {
//				prizeRecordTmpDataService.transferTmpPrizeData2Formal(new BigInteger("123"));
//		}}.start();
//		
//		new Thread(){
//			IPrizeRecordTmpDataService prizeRecordTmpDataService = ctx.getBean("prizeRecordTmpDataService", IPrizeRecordTmpDataService.class);
//			@Override
//			public void run() {
//				prizeRecordTmpDataService.transferTmpPrizeData2Formal(new BigInteger("123"));
//		}}.start();
//		System.out.println("123");
//		try {
//			Thread.sleep(1000000000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
