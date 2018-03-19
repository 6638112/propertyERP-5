/**   
* Filename:    ICommonUserDao.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午4:57:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;
import com.cnfantasia.server.domainbase.userTmp.entity.UserTmp;

/**
 * Filename:    ICommonUserDao.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午4:57:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
public interface ICommonUserDao {
//	/**
//	 * 查询总的可用的用户数,区分签约和非签约
//	 * @return
//	 */
//	public Long selectUserTotalCountByUserId(BigInteger userId);
//	/**
//	 * 查询总的可用的用户数
//	 * @return
//	 */
//	public Long selectUserTotalCount();
//	
////	/**
////	 * 查询当前正常的用户数
////	 * @param states 用户状态
////	 * @return
////	 */
////	public int selectCurrUserCount(List<String> states);
//	/**
//	 * 查询用户当前月在线天数
//	 * @param userId
//	 * @return
//	 */
//	public int selectOnLineDays(BigInteger userId,String loginDay_START,String loginDay_END);
	
	/**
	 * 根据设备Id和渠道类别查询其是否已经注册过
	 * @param deviceId
	 * @param channelId
	 * @return
	 */
	public int selectUserCountByDeviceInfo(String deviceId,Long channelId);
	
	/**
	 * 查询临时用户列表
	 * @param deviceId
	 * @param channelId
	 * @return
	 */
	public List<UserTmp> selectUserListByDeviceInfo(String deviceId,Long channelId);
	
	/**
	 * 根据用户Id查询对应的用户信息 包含是否为管理员
	 * @param userId
	 * @return
	 */
	public UserSimpleEntity selectUserSimpleByUserId(BigInteger userId);
	
	/**
	 * 查询用户的家庭成员列表
	 * @param userId
	 * @return
	 */
	public List<UserSimpleEntity> selectFamilyMembers(BigInteger userId);
	
	/**
	 * 查询用户的家庭成员列表 不包含自己
	 * @param userId
	 * @param isAtTheRoom 这个参数的精妙在于是否约束当前用户的当前门牌下的所有已验证的用户的当前门牌是不是也是该用户的当前门牌
	 * @return
	 */
	public List<UserSimpleEntity> selectFamilyMembersWithoutSelf(BigInteger userId, boolean isAtTheRoom);
	
	/**
	 * 查询用户默认门牌所在的家庭各成员及对应成员的绑定账号的所有的UserHasTRoom信息
	 * @param userId
	 * @return
	 */
	public List<UserHasTRoom> selectFamilyUserHasTRoomWithBind(BigInteger userId);
	
}
