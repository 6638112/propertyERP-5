/**   
* Filename:    AddressBlockTest.java   
* @version:    1.0  
* Create at:   2014年6月23日 上午3:27:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.apibase.addressBlock.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.domainbase.achievement.dao.IAchievementBaseDao;
import com.cnfantasia.server.domainbase.achievement.entity.Achievement;

/**
 * Filename:    AddressBlockTest.java
 * @version:    1.0.0
 * Create at:   2014年6月23日 上午3:27:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月23日       shiyl             1.0             1.0 Version
 */
public class AddressBlockTest extends BaseTest{
	
//	@Test
//	public void testQry(){
//		IAddressBlockBaseService addressBlockBaseService = ctx.getBean("addressBlockBaseService", IAddressBlockBaseService.class);
//		addressBlockBaseService.getAddressBlockByCondition(null);
//	}
	
	@Test
	public void testInsert(){
		IAchievementBaseDao achievementBaseDao = ctx.getBean("achievementBaseDao", IAchievementBaseDao.class);
		List<Achievement> achievementList=new ArrayList<Achievement>();
		{
			Achievement achievement = new Achievement();
			achievement.setId(new BigInteger("17"));
//			achievement.setName("tt");
			achievement.setIconUrl(null);
			achievementList.add(achievement);
		}
		{
			Achievement achievement = new Achievement();
			achievement.setId(new BigInteger("18"));
			achievement.setName("tt");
			achievement.setIconUrl(null);
			achievementList.add(achievement);
		}
		
		achievementBaseDao.insertAchievementBatch(achievementList);
	}
	
}
