/**   
* Filename:    CommonRoomDaoTest.java   
* @version:    1.0  
* Create at:   2014年6月23日 下午12:06:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.common;

import java.math.BigInteger;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRoomDao;

/**
 * Filename:    CommonRoomDaoTest.java
 * @version:    1.0.0
 * Create at:   2014年6月23日 下午12:06:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月23日       shiyl             1.0             1.0 Version
 */
public class CommonRoomDaoTest extends BaseTest{
//@Test
	public void test01(){
		ICommonRoomDao commonRoomDao = ctx.getBean("commonRoomDao", ICommonRoomDao.class);
		System.out.println(JSON.toJSONString(commonRoomDao.selectRoomEntityById(new BigInteger("50012"))));
	}
}
