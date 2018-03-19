/**   
* Filename:    TestXanadu.java   
* @version:    1.0  
* Create at:   2014年12月1日 上午5:15:36   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.xanadu.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    TestXanadu.java
 * @version:    1.0.0
 * Create at:   2014年12月1日 上午5:15:36
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月1日       shiyl             1.0             1.0 Version
 */
public class TestXanadu extends BaseTest{
	
//	@Test
	public void test001(){
		XanaduService tmpXanaduService = ctx.getBean("xanaduService", XanaduService.class);
		System.out.println(tmpXanaduService.changeXanaduStatus(new BigInteger("50001"), false));
	}
	
}
