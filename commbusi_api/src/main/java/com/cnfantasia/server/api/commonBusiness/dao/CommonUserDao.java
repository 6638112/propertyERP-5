/**   
* Filename:    CommonUserDao.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午4:58:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;
import com.cnfantasia.server.domainbase.userTmp.entity.UserTmp;

/**
 * Filename:    CommonUserDao.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午4:58:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
public class CommonUserDao extends AbstractBaseDao implements ICommonUserDao{

//	@Override
//	public Long selectUserTotalCountByUserId(BigInteger userId) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("userId", userId);
//		return sqlSession.selectOne("commonUser.select_User_TotalCount_ByUserId",tmpMap);
//	}
//	@Override
//	public Long selectUserTotalCount() {
//		return sqlSession.selectOne("commonUser.select_User_TotalCount");
//	}
//	
////	@Override
////	public int selectCurrUserCount(List<String> states) {
////		return sqlSession.selectOne("commonUser.select_userCount_byStates",states);
////	}
//
//	@Override
//	public int selectOnLineDays(BigInteger userId,String loginDay_START,String loginDay_END) {
//		LoginLogQuery loginLog = new LoginLogQuery();
//		loginLog.settUserFId(userId);
//		loginLog.setLoginDay_START(loginDay_START);
//		loginLog.setLoginDay_END(loginDay_END);
//		Map<String,Object> paramMap = MapConverter.toMap(loginLog);
//		paramMap.put(QUERY_TYPE_IF_DIM,false);
//		return sqlSession.selectOne("commonUser.select_userCount_byOnlineDays",paramMap);
//	}

	@Override
	public int selectUserCountByDeviceInfo(String deviceId, Long channelId) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deviceId", deviceId);
		paramMap.put("channelId", channelId);
		return sqlSession.selectOne("commonUser.select_UserCount_By_DeviceInfo",paramMap);
	}
	
	@Override
	public List<UserTmp> selectUserListByDeviceInfo(String deviceId,Long channelId){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deviceId", deviceId);
		paramMap.put("channelId", channelId);
		return sqlSession.selectList("commonUser.select_UserList_ByDeviceInfo",paramMap);
	}
	@Override
	public UserSimpleEntity selectUserSimpleByUserId(BigInteger userId) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return sqlSession.selectOne("commonUser.select_UserSimple_By_UserId",paramMap);
	}
	
	@Override
	public List<UserSimpleEntity> selectFamilyMembers(BigInteger userId) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return sqlSession.selectList("commonUser.select_FamilyMembers",paramMap);
	}
	
	@Override
	public List<UserSimpleEntity> selectFamilyMembersWithoutSelf(BigInteger userId, boolean isAtTheRoom) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
        paramMap.put("isAtTheRoom", isAtTheRoom);
		return sqlSession.selectList("commonUser.select_FamilyMembers_WithoutSelf",paramMap);
	}
	
	@Override
	public List<UserHasTRoom> selectFamilyUserHasTRoomWithBind(BigInteger userId) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return sqlSession.selectList("commonUser.select_Family_UserHasTRoom_WithBind",paramMap);
	}
	
}
