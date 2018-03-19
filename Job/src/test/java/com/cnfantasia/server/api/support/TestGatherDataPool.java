/**   
* Filename:    TestGatherDataPool.java   
* @version:    1.0  
* Create at:   2015年7月20日 上午11:05:12   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月20日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.support;

import java.math.BigInteger;

import org.junit.Test;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.commonBusiness.queue.GatherDataPool;
import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;

/**
 * Filename:    TestGatherDataPool.java
 * @version:    1.0.0
 * Create at:   2015年7月20日 上午11:05:12
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月20日       shiyl             1.0             1.0 Version
 */
public class TestGatherDataPool extends BaseTest{
		
	@Test
	public void testSupport(){
		BigInteger goalId = new BigInteger("1001");
		Integer goalType = 11;
		CommonGatherData tmpCommonGatherData = new CommonGatherData();
		tmpCommonGatherData.setTargetId(goalId);
		tmpCommonGatherData.setTargetType(goalType);
		tmpCommonGatherData.setTotalCollectCount((long)1);
		tmpCommonGatherData.setTotalCommentCount((long)0);
		tmpCommonGatherData.setTotalSupportCount((long)2);
		GatherDataPool.addGatherData(tmpCommonGatherData);
		GatherDataPool.addGatherData(tmpCommonGatherData);
		try {
			Thread.sleep(5000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
