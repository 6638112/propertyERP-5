/**   
* Filename:    CommonRoomDao.java   
* @version:    1.0  
* Create at:   2014年6月23日 上午10:50:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.api.commonBusiness.entity.UserHasTRoomWithRoomSimpleEntity;
import com.cnfantasia.server.api.commonBusiness.entity.UserRoomApplyEntity;
import com.cnfantasia.server.api.communitySupply.entity.BaiduLocation;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.room.entity.GbQueryAddressParam;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntityWithDistance;
import com.cnfantasia.server.api.room.entity.GroupBuildingSimpleWithDistance;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.room.entity.Room;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    CommonRoomDao.java
 * @version:    1.0.0
 * Create at:   2014年6月23日 上午10:50:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月23日       shiyl             1.0             1.0 Version
 */
@Repository
public class CommonRoomDao extends AbstractBaseDao implements ICommonRoomDao{
	
	@Override
	public RoomEntityWithValidate selectRoomEntityById(BigInteger roomId) {
		return sqlSession.selectOne("commonRoom.select_room_bySeqId",roomId);
	}
	@Override
	public RoomEntityWithValidate selectRoomEntityByIdWithSupport(BigInteger roomId,BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("roomId", roomId);
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("commonRoom.select_room_bySeqId_WithSupport",tmpMap);
	}
	@Override
	public RoomEntityWithValidate selectRoomEntityByIdWithSummon(BigInteger roomId,BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("roomId", roomId);
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("commonRoom.select_room_bySeqId_WithSummon",tmpMap);
	}
	
//	@Override
//	public List<BigInteger> selectAdminIdsByRoomId(BigInteger roomId) {
//		return sqlSession.selectList("commonRoom.select_admin_Ids_byRoomId", roomId);
//	}

	@Override
	public GroupBuilding selectGroupBuildingByRealRoomId(BigInteger realRoomId) {
		return sqlSession.selectOne("commonRoom.select_groupbuilding_by_realroomId", realRoomId);
	}

//	@Override
//	public int selectFirstHbconvertStateRoomCount(BigInteger userId) {
//		return sqlSession.selectOne("commonRoom.select_First_HbconvertState_Room_Count", userId);
//	}
//
//	@Override
//	public int updateRoomAsFirstHbconvertState(BigInteger userId) {
//		return sqlSession.update("commonRoom.update_Room_As_First_HbconvertState", userId);
//	}
	
	@Override
	public GroupBuilding selectGroupBuildingByUserId(BigInteger userId) {
		return sqlSession.selectOne("commonRoom.select_GroupBuilding_By_UserId", userId);
	}
	
	@Override
	public Integer selectGroupBuildingStatus(BigInteger userId) {
		return sqlSession.selectOne("commonRoom.select_groupbuilding_status", userId);
	}
	
	@Override
	public BigInteger selectDefaultRoomIdByUserId(BigInteger userId) {
		return sqlSession.selectOne("commonRoom.select_Default_RoomId_By_UserId", userId);
	}
	
	@Override
	public BigInteger selectMainRoomIdByUserId(BigInteger userId) {
		return sqlSession.selectOne("commonRoom.select_Main_RoomId_By_UserId", userId);
	}
	@Override
	public Integer updateMainRoomIdByUserId(BigInteger userId,BigInteger mainRoomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("mainRoomId", mainRoomId);
		return sqlSession.update("commonRoom.update_Main_RoomId_By_UserId", tmpMap);
	}
	
	@Override
	public List<UserSimpleEntity> selectUserListByUserIds(List<BigInteger> userIdList) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userIdList", userIdList);
		return sqlSession.selectList("commonRoom.select_User_List_By_UserIds", tmpMap);
	}
	
	@Override
	public List<UserSimpleEntity> selectUserListByGroupBuildIds(List<BigInteger> groupBuildIdList) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("groupBuildIdList", groupBuildIdList);
		return sqlSession.selectList("commonRoom.select_User_List_By_GroupBuildIds", tmpMap);
	}
	
	@Override
	public List<AddressCity> selectAddressCityListByCityNameDim(String cityName) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("cityName", cityName);
		return sqlSession.selectList("commonRoom.select_AddressCity_List_By_CityName_Dim", tmpMap);
	}
	
	@Override
	public RealRoomEntity selectRealRoomById(BigInteger realRoomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("realRoomId", realRoomId);
		return sqlSession.selectOne("commonRoom.select_RealRoom_ById", tmpMap);
	}
	
	@Override
	public Room selectDefaultRoomByUserId(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("commonRoom.select_DefaultRoom_ByUserId", userId);
	}
	
	@Override
	public UserHasTRoom selectDefaultUserHasTRoom(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("commonRoom.select_DefaultUserHasTRoom_ByUserId", userId);
	}
	
	@Override
	public GroupBuildingEntity selectGroupBuildingById(BigInteger groupBuildingId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("groupBuildingId", groupBuildingId);
		return sqlSession.selectOne("commonRoom.select_GroupBuilding_ById", tmpMap);
	}
	
	@Override
	public List<GroupBuildingEntity> selectGroupBuildingIsRegisted() {
		return sqlSession.selectList("commonRoom.select_GroupBuilding_IsRegisted");
	}
	
	@Override
	public List<UserRoomApplyEntity> selectUserListOfSameRoomByUserId(BigInteger userId,PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		String pageSqlKey = "commonRoom.select_UserList_OfSameRoom_ByUserId_page";
		String countSqlKey = "commonRoom.select_UserList_OfSameRoom_ByUserId_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public Integer deleteUserOfSameRoomLogic(BigInteger userId, List<BigInteger> toDelUserList) {
		if(toDelUserList==null||toDelUserList.size()<=0){return 0;}
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("toDelUserList", toDelUserList);
		return sqlSession.update("commonRoom.delete_User_OfSameRoom_Logic", tmpMap);
	}
	
	@Override
	public RoomValidate selectRealRoomFirstValidate(BigInteger realRoomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("realRoomId", realRoomId);
		return sqlSession.selectOne("commonRoom.select_RealRoom_FirstValidate", tmpMap);
	}
	
	@Override
	public RealRoom selectRealRoomByUserId(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("commonRoom.select_RealRoom_ByUserId", tmpMap);
	}
	
	@Override
	public GroupBuildingSimpleWithDistance selectNearestGroupBuildingByBaiduLocation(BaiduLocation baiduLocation) {
		//syl-del-2015-7-23 14:09:05考虑性能，临时注释掉
		//通过坐标调用baiduApi获取地理位置详情
		return null;
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("lng", baiduLocation.getLongitude());
//		tmpMap.put("lat", baiduLocation.getLatitude());
//		return sqlSession.selectOne("commonRoom.select_Nearest_GroupBuilding_ByBaiduLocation", tmpMap);
	}
	
	@Override
	public List<GroupBuildingEntityWithDistance> selectNearbyGroupBuildingByBaiduLocation(GbQueryAddressParam addressParam,
			Double maxDistance,String searchKey,BigInteger userId) {
		if(addressParam.hasGps()){
			BaiduLocation baiduLocation = addressParam.getBaiduLocation();
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("lng", baiduLocation.getLongitude());
			tmpMap.put("lat", baiduLocation.getLatitude());
			tmpMap.put("maxDistance", maxDistance);
			
			tmpMap.put("searchKey", searchKey);
			tmpMap.put("userId", userId);
			return sqlSession.selectList("commonRoom.select_Nearby_GroupBuilding_ByBaiduLocation", tmpMap);
		}else if(addressParam.hasCityInfo()){
			BigInteger cityId = addressParam.getCityId();
			BigInteger blockId = addressParam.getBlockId();
			String cityName = addressParam.getCityName();
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("cityId", cityId);
			tmpMap.put("blockId", blockId);
			tmpMap.put("cityName", cityName);
			
			tmpMap.put("searchKey", searchKey);
			tmpMap.put("userId", userId);
			return sqlSession.selectList("commonRoom.select_Nearby_GroupBuilding_ByAddressCity", tmpMap);
		}else{
			return null;
		}
		
	}
	
	@Override
	public List<Room> selectUserRoomList(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectList("commonRoom.select_UserRoom_List", tmpMap);
	}
	
	@Override
	public List<UserHasTRoomWithRoomSimpleEntity> selectUserHasTRoomWithRoomSimpleList(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectList("commonRoom.select_UserHasTRoom_WithRoomSimple_List", tmpMap);
	}
	
	@Override
	public Integer update2NewRealRoomId(BigInteger userId, BigInteger roomId, BigInteger realRoomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("roomId", roomId);
		tmpMap.put("realRoomId", realRoomId);
		return sqlSession.update("commonRoom.update_2New_RealRoomId", tmpMap);
	}
	@Override
	public List<GroupBuildingEntity> queryUserIsRegisterGroupbuilding(
			Map<String, Object> param) {
		return sqlSession.selectList("commonRoom.select_User_Registed_GroupBuilding", param);
	}
	@Override
	public Integer queryUserIsRegisterGroupbuilding(BigInteger userId,List<Map<String, Object>> paramList) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("paramList", paramList);
		return sqlSession.selectOne("commonRoom.select_User_Registed_GroupBuilding_count_multi", tmpMap);
	}
	
	@Override
	public Integer selectRoomCount(BigInteger gbId,BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("commonRoom.select_RoomCount", tmpMap);
	}
	
	@Override
	public String selectUserBindPhone(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("commonRoom.select_User_BindPhone", tmpMap);
	}

	@Override
	public Integer selectRoomMatchPhoneAndNotValidateCount(BigInteger roomId,String bindPhone){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("roomId", roomId);
		tmpMap.put("bindPhone", bindPhone);
		return sqlSession.selectOne("commonRoom.select_Room_MatchPhoneAndNotValidateCount", tmpMap);
	}
	
	@Override
	public List<Room> selectRoomListMatchPhoneAndNotValidate(BigInteger userId, String bindPhone) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("bindPhone", bindPhone);
		return sqlSession.selectList("commonRoom.select_RoomList_MatchPhoneAndNotValidate", tmpMap);
	}
	
	@Override
	public Integer selectRoomMatchPhoneAndValidateCountByUserId(BigInteger userId, String bindPhone) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("bindPhone", bindPhone);
		return sqlSession.selectOne("commonRoom.select_Room_MatchPhoneAndValidateCount_ByUserId", tmpMap);
	}
	
	@Override
	public List<BigInteger> selectRealRoomIdByPhone(String bindPhone) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("bindPhone", bindPhone);
		return sqlSession.selectList("commonRoom.select_RealRoomId_ByPhone", tmpMap);
	}
	
	@Override
	public RealRoom selectRealRoomByRoomId(BigInteger roomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("roomId", roomId);
		return sqlSession.selectOne("commonRoom.select_RealRoom_ByRoomId", tmpMap);
	}

	/**
	 * 根据门牌获取各个上级的ID gbId, blockId, cityId, provinceId
	 * @param roomId
	 * @return
	 */
	@Override
	public Map<String, Object> getRoomAddressIdByRoom(BigInteger roomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("roomId", roomId);
		return sqlSession.selectOne("commonRoom.getRoomAddressIdByRoom", tmpMap);
	}

	/**
	 * 获取门牌详情 <城市><区县><小区><楼栋-单元-房号>
	 * @param roomId
	 * @return
	 */
	@Override
	public String getRoomDetailAddress(BigInteger roomId) {
		return sqlSession.selectOne("commonRoom.getRoomDetailAddress", roomId);
	}
}
