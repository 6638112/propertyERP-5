/**   
* Filename:    CommonUserService.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午5:04:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.dao.ICommonUserDao;
import com.cnfantasia.server.api.commonBusiness.util.UserShowNameUtil;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    CommonUserService.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午5:04:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
public class CommonUserService implements ICommonUserService{
	private ICommonUserDao commonUserDao;
	public void setCommonUserDao(ICommonUserDao commonUserDao) {
		this.commonUserDao = commonUserDao;
	}
	
//	@Override
//	public Long getUserTotalCount(BigInteger userId) {
//		return commonUserDao.selectUserTotalCountByUserId(userId);
//	}
//	@Override
//	public Long getUserTotalCount() {
//		return commonUserDao.selectUserTotalCount();
//	}
//
//	@Override
//	public int selectOnLineDays(BigInteger userId, String loginDay_START, String loginDay_END) {
//		return commonUserDao.selectOnLineDays(userId, loginDay_START, loginDay_END);
//	}

	@Override
	public boolean isRegistByDeviceInfo(String deviceId, Long channelId) {
		int count =  commonUserDao.selectUserCountByDeviceInfo(deviceId, channelId);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String getUserShowNameById(BigInteger userId) {
		return UserShowNameUtil.getUserShowName(commonUserDao.selectUserSimpleByUserId(userId));
	}
	
//	@Override
//	public UserSimpleEntity getUserSimpleById(BigInteger userId){
//		UserSimpleEntity resUser = commonUserDao.selectUserSimpleByUserId(userId);
//		return resUser;
//	}

	@Override
	public List<UserSimpleEntity> getFamilyMembers(BigInteger userId) {
		return commonUserDao.selectFamilyMembers(userId);
	}

	@Override
	public List<UserSimpleEntity> getFamilyMembersWithoutSelf(BigInteger userId, boolean isAtTheRoom) {
		return commonUserDao.selectFamilyMembersWithoutSelf(userId, isAtTheRoom);
	}

	@Override
	public List<UserHasTRoom> getFamilyUserHasTRoomWithBind(BigInteger userId) {
		return commonUserDao.selectFamilyUserHasTRoomWithBind(userId);
	}

    @Override
    public UserSimpleEntity getUserInfoById(BigInteger userId) {
        return commonUserDao.selectUserSimpleByUserId(userId);
    }
}
