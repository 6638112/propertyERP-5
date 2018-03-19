/**   
* Filename:    UserDaoTest.java   
* @version:    1.0  
* Create at:   2014年6月23日 下午12:37:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.user.dao;

import java.math.BigInteger;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    UserDaoTest.java
 * @version:    1.0.0
 * Create at:   2014年6月23日 下午12:37:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月23日       shiyl             1.0             1.0 Version
 */
public class UserDaoTest extends BaseTest{
//	@Test
	public void getUserById(){
		IUserDao userDao = ctx.getBean("userDao", IUserDao.class);
		System.out.println(JSON.toJSONString(userDao.selectUserById(new BigInteger("50018"))));
	}
}
