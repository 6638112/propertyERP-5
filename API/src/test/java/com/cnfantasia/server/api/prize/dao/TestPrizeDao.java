/**   
* Filename:    TestPrizeDao.java   
* @version:    1.0  
* Create at:   2014年6月16日 上午9:10:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.dao;

import java.math.BigInteger;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    TestPrizeDao.java
 * @version:    1.0.0
 * Create at:   2014年6月16日 上午9:10:30
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月16日       shiyl             1.0             1.0 Version
 */
public class TestPrizeDao extends BaseTest{
	
	@Test
	public void getPrizeRecordByMonth(){
		IPrizeDao prizeDao = ctx.getBean("prizeDao", IPrizeDao.class);
		BigInteger userId = new BigInteger("50018");
		Object res = prizeDao.doPrizeFromDB(userId, 1);
		System.out.println(JSON.toJSONString(res));
	}
	
}
