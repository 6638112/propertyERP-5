/**   
* Filename:    RedpointServiceTest.java   
* @version:    1.0  
* Create at:   2015年3月26日 上午8:26:07   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.service;

import java.math.BigInteger;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.redpoint.entity.RedpointContentEntity;

/**
 * Filename:    RedpointServiceTest.java
 * @version:    1.0.0
 * Create at:   2015年3月26日 上午8:26:07
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月26日       shiyl             1.0             1.0 Version
 */
public class RedpointServiceTest extends BaseTest{
	
//	@Test
//	public void addRedpointContent() {
//		IRedpointService redpointService = ctx.getBean("redpointService", IRedpointService.class);
//		BigInteger userId = new BigInteger("50000");
//		Integer userType = 1;
//		String modelCode = "B";
//		List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
////		sourceList.add(new BasicSourceEntity(1, new BigInteger("503")));
////		sourceList.add(new BasicSourceEntity(2, new BigInteger("501")));
//		RedpointContentEntity res = redpointService.addRedpointContent(userId, userType, modelCode, sourceList);
//		System.out.println(JSON.toJSONString(res));
//	}
	
	@Test
	public void clickRedpointInfo() {
		IRedpointService redpointService = ctx.getBean("redpointService", IRedpointService.class);
		BigInteger userId = new BigInteger("50000");
		Integer userType = 1;
		String modelCode = "EE";
		RedpointContentEntity res = redpointService.clickRedpointInfo(userId, userType, modelCode);
		System.out.println(JSON.toJSONString(res));
	}
	
}
