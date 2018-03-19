/**   
* Filename:    AchievementService.java   
* @version:    1.0  
* Create at:   2015年1月26日 上午10:40:05   
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
import com.cnfantasia.server.api.achievement.constant.AchievementDict;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.achievement.dao.IAchievementBaseDao;
import com.cnfantasia.server.domainbase.achievement.entity.Achievement;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userHasTAchievement.dao.IUserHasTAchievementBaseDao;
import com.cnfantasia.server.domainbase.userHasTAchievement.entity.UserHasTAchievement;

/**
 * Filename:    AchievementService.java
 * @version:    1.0.0
 * Create at:   2015年1月26日 上午10:40:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月26日       shiyl             1.0             1.0 Version
 */
public class AchievementService implements IAchievementService{
	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}
	
	private IUserHasTAchievementBaseDao userHasTAchievementBaseDao;
	public void setUserHasTAchievementBaseDao(IUserHasTAchievementBaseDao userHasTAchievementBaseDao) {
		this.userHasTAchievementBaseDao = userHasTAchievementBaseDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IAchievementBaseDao achievementBaseDao;
	public void setAchievementBaseDao(IAchievementBaseDao achievementBaseDao) {
		this.achievementBaseDao = achievementBaseDao;
	}

	@Override
	public void addAchievement(BigInteger userId, AchievementEnum currAchieve) {
		User currUser = userBaseDao.selectUserBySeqId(userId);
		BigInteger roomId = currUser.getDefaultRoomId();
		Boolean isExist = null;
		//校验是否已存在
		if(roomId!=null&&userId!=null){
			UserHasTAchievement userHasTAchievementQry = new UserHasTAchievement();
			userHasTAchievementQry.setRoomId(roomId);
			userHasTAchievementQry.settUserFId(userId);
			int resCount = userHasTAchievementBaseDao.selectUserHasTAchievementCount(MapConverter.toMap(userHasTAchievementQry), false);
			if(resCount>0){
				isExist = true;
			}else{
				isExist = false;
			}
		}
		//不存在则新增
		if(isExist!=null&&!isExist){
			UserHasTAchievement userHasTAchievementAdd = new UserHasTAchievement();
			BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_achievement);
			userHasTAchievementAdd.setId(addId);
			userHasTAchievementAdd.setIsAboutRoom(currAchieve.getIsAboutRoom()?AchievementDict.Achieve_IsAboutRoom.TURE:AchievementDict.Achieve_IsAboutRoom.FALSE);
			userHasTAchievementAdd.setRoomId(roomId);
			userHasTAchievementAdd.settAchievementFId(currAchieve.getRecordId());
			userHasTAchievementAdd.settUserFId(userId);
			
			Integer resCount = userHasTAchievementBaseDao.insertUserHasTAchievement(userHasTAchievementAdd);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("AchievementService.addAchievement.failed");
			}
		}
	}

	@Override
	public Achievement getAchievementById(BigInteger id) {
		return achievementBaseDao.selectAchievementBySeqId(id);
	}

}
