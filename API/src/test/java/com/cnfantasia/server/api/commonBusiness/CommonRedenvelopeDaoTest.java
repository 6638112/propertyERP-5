/**   
* Filename:    CommonRedenvelopeDaoTest.java   
* @version:    1.0  
* Create at:   2015年3月17日 上午3:04:13   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness;

import java.math.BigInteger;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRedenvelopeDao;

/**
 * Filename:    CommonRedenvelopeDaoTest.java
 * @version:    1.0.0
 * Create at:   2015年3月17日 上午3:04:13
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月17日       shiyl             1.0             1.0 Version
 */
public class CommonRedenvelopeDaoTest extends BaseTest{
	
	@Test
	public void test01(){
		ICommonRedenvelopeDao commonRedenvelopeDao = ctx.getBean("commonRedenvelopeDao", ICommonRedenvelopeDao.class);
		Object res = commonRedenvelopeDao.selectPayRedEnvelopeAvailable(new BigInteger("50000"), 0);
		System.out.println(JSON.toJSONString(res));
		Object res2 = commonRedenvelopeDao.selectTotalHbBalance(new BigInteger("50000"), 0);
		System.out.println(JSON.toJSONString(res2));
	}
	
}
