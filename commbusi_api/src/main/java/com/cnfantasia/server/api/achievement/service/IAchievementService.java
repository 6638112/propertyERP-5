/**   
* Filename:    IAchievementService.java   
* @version:    1.0  
* Create at:   2015年1月26日 上午10:32:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.achievement.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.achievement.constant.AchievementConstant.AchievementEnum;
import com.cnfantasia.server.domainbase.achievement.entity.Achievement;

/**
 * Filename:    IAchievementService.java
 * @version:    1.0.0
 * Create at:   2015年1月26日 上午10:32:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月26日       shiyl             1.0             1.0 Version
 */
public interface IAchievementService {
	
	/**
	 * 添加成就
	 * @param userId
	 * @param currAchieve
	 */
	public void addAchievement(BigInteger userId,AchievementEnum achievementEnum);
	
	/**
	 * 根据记录Id查询对应成就信息
	 * @param id
	 * @return
	 */
	public Achievement getAchievementById(BigInteger id);
}
