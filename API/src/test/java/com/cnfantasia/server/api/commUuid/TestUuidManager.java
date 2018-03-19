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
package com.cnfantasia.server.api.commUuid;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.domainbase.achievement.dao.IAchievementBaseDao;
import com.cnfantasia.server.domainbase.achievement.entity.Achievement;

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
	
//	@Test
//	public void aaTest(){
//		IUuidManager uuidManager = ctx.getBean("uuidManager", IUuidManager.class);
//		Object res01 = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant);
//		System.out.println(JSON.toJSONString(res01));//数据库存 100012 输出 [100012,100013],数据库存100014 输出100014
//	}
	
	@Test
	public void testUpdate(){
		IAchievementBaseDao achievementBaseDao = ctx.getBean("achievementBaseDao", IAchievementBaseDao.class);
		
//		Achievement achievement01= new Achievement();
//		achievement01.setId(new BigInteger("2011"));
//		achievement01.setName("name01");
//		achievement01.setDesc("wfee");
//		achievementBaseDao.updateAchievement(achievement01);
		
		{
			Achievement achievement02= new Achievement();
			achievement02.setId(new BigInteger("2012"));
			achievement02.setName("name02");
			Achievement achievement03= new Achievement();
			achievement03.setId(new BigInteger("2011"));
			achievement03.setName("name03");
			List<Achievement> list = new ArrayList<Achievement>();
			list.add(achievement02);
			list.add(achievement03);
			achievementBaseDao.updateAchievementBatch(list);
		}
		
	}
}
