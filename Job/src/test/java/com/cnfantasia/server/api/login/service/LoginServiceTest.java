/**   
* Filename:    LoginServiceTest.java   
* @version:    1.0  
* Create at:   2014年6月25日 上午4:52:39   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.service;

import java.math.BigInteger;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    LoginServiceTest.java
 * @version:    1.0.0
 * Create at:   2014年6月25日 上午4:52:39
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月25日       shiyl             1.0             1.0 Version
 */
public class LoginServiceTest extends BaseTest{
//	@Test
	public void testGetLastRecord(){
		ILoginService loginService = ctx.getBean("loginService", ILoginService.class);
		System.out.println(JSON.toJSONString(loginService.getLastRecord(new BigInteger("50015"))));
		
	}
}
