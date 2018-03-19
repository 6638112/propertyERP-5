/**   
* Filename:    TestCommRoomDao.java   
* @version:    1.0  
* Create at:   2015年7月16日 上午10:05:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness;

import java.math.BigInteger;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRoomDao;

/**
 * Filename:    TestCommRoomDao.java
 * @version:    1.0.0
 * Create at:   2015年7月16日 上午10:05:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月16日       shiyl             1.0             1.0 Version
 */
public class TestCommRoomDao extends BaseTest{
	
//	@Test
//	public void test(){
//		ICommonRoomDao commonRoomDao = ctx.getBean("commonRoomDao", ICommonRoomDao.class);
//		BigInteger gbId = new BigInteger("-1");
//		BigInteger userId = new BigInteger("50001");
//		Object res = commonRoomDao.selectRoomCount(gbId, userId);
//		System.out.println(JSON.toJSONString(res));
//	}
	
	@Test
	public void test(){
		ICommonRoomDao commonRoomDao = ctx.getBean("commonRoomDao", ICommonRoomDao.class);
		Object res = commonRoomDao.selectRealRoomByRoomId(new BigInteger("50002"));
		System.out.println(JSON.toJSONString(res));
	}
	
}
