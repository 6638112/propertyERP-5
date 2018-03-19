/**   
* Filename:    TestAddressOperationService.java   
* @version:    1.0  
* Create at:   2015年7月7日 上午8:41:21   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation;

import java.math.BigInteger;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;

/**
 * Filename:    TestAddressOperationService.java
 * @version:    1.0.0
 * Create at:   2015年7月7日 上午8:41:21
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月7日       shiyl             1.0             1.0 Version
 */
public class TestAddressOperationService extends BaseTest{
	
//	@Test
//	public void testGetEbuySupplyMerchantList(){
//		IAddressOperationService addressOperationService = ctx.getBean("addressOperationService", IAddressOperationService.class);
//		BigInteger gbId = new BigInteger("1");
//		Object res = addressOperationService.getEbuySupplyMerchantList(null, null, null, null, gbId);
//		System.out.println(JSON.toJSONString(res));
//	}
	
	@Test
	public void testGetEbuySupplyMerchantList02(){
		IAddressOperationService addressOperationService = ctx.getBean("addressOperationService", IAddressOperationService.class);
		BigInteger provinceId = new BigInteger("19");
		Object res = addressOperationService.getEbuySupplyMerchantList(null, provinceId, null, null, null);
		System.out.println(JSON.toJSONString(res));
	}
	
}
