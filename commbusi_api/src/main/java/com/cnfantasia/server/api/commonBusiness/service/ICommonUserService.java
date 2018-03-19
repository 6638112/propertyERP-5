/**   
* Filename:    ICommonUserService.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午4:55:31   
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

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    ICommonUserService.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午4:55:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
public interface ICommonUserService {
	
//	/**
//	 * 查询总的可用的用户数
//	 * @param userId 当前用户Id
//	 * @return
//	 */
//	public Long getUserTotalCount(BigInteger userId);
//	public Long getUserTotalCount();
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
	public boolean isRegistByDeviceInfo(String deviceId,Long channelId);
	
	/**
	 * 通过用户Id查询对应的显示名称
	 * @param userId
	 * @return
	 */
	public String getUserShowNameById(BigInteger userId);
	
//	/**
//	 * 通过用户Id获取对应的是否为门牌管理员的信息
//	 * @param userId
//	 * @return
//	 */
//	public UserSimpleEntity getUserSimpleById(BigInteger userId);
	
	/**
	 * 查询家庭成员列表
	 * @param userId
	 * @return
	 */
	public List<UserSimpleEntity> getFamilyMembers(BigInteger userId);
	
	/**
	 * 查询家庭成员列表 不包含自己
	 * @param userId
	 * @param isAtTheRoom 这个参数的精妙在于是否约束当前用户的当前门牌下的所有已验证的用户的当前门牌是不是也是该用户的当前门牌
	 * @return
	 */
	public List<UserSimpleEntity> getFamilyMembersWithoutSelf(BigInteger userId, boolean isAtTheRoom);
	
	/**
	 * 查询用户默认门牌
	 * 所在的家庭各成员及对应成员的绑定账号的所有的UserHasTRoom信息
	 * @param userId
	 * @return
	 */
	public List<UserHasTRoom> getFamilyUserHasTRoomWithBind(BigInteger userId);
	
	/**
	 * @author wangzhe
	 * @date 2015年10月16日
	 * @description 由ID得user实例
	 *
	 * @param userId
	 * @return UserSimpleEntity
	 */
	public UserSimpleEntity getUserInfoById(BigInteger userId);
}
