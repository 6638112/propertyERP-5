/**   
* Filename:    TestTransaction.java   
* @version:    1.0  
* Create at:   2015年7月7日 上午9:11:54   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub;

import java.math.BigInteger;

import org.junit.Test;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.comments.service.ICommentsService;

/**
 * Filename:    TestTransaction.java
 * @version:    1.0.0
 * Create at:   2015年7月7日 上午9:11:54
 * Description:测试事务
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月7日       shiyl             1.0             1.0 Version
 */
public class TestTransaction extends BaseTest{
	
	@Test
	public void testTrans(){
		ICommentsService commentsService = ctx.getBean("commentsService", ICommentsService.class);
		commentsService.delComments(new BigInteger("50003"), new BigInteger("50001"));
	}
	
}
