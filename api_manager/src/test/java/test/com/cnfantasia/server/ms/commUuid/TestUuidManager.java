/**   
* Filename:    TestUuidManager.java   
* @version:    1.0  
* Create at:   2014年12月16日 上午2:07:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月16日    shiyl      1.0         1.0 Version   
*/
package test.com.cnfantasia.server.ms.commUuid;

import org.junit.Test;

import test.com.cnfantasia.server.ms.BaseTest;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;

/**
 * Filename:    TestUuidManager.java
 * @version:    1.0.0
 * Create at:   2014年12月16日 上午2:07:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月16日       shiyl             1.0             1.0 Version
 */
public class TestUuidManager extends BaseTest{
	//API--数据库存 100012 输出 [100012,100013],数据库更新到100014
	//API--数据库存100014 输出100014,数据库更新到100015
	@Test
	public void aaTest(){
		IUuidManager uuidManager = ctx.getBean("uuidManager", IUuidManager.class);
		Object res01 = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant);
		System.out.println(JSON.toJSONString(res01));
	}
	
}
