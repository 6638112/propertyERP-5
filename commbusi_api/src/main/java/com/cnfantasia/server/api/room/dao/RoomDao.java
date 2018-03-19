package com.cnfantasia.server.api.room.dao;

import com.cnfantasia.server.api.propertycard.entity.RealRoomConfig;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.room.entity.AddressCityHotEntity;
import com.cnfantasia.server.api.room.entity.BuildingAndUnitEntity;
import com.cnfantasia.server.api.room.entity.RoomWithValidate;
import com.cnfantasia.server.api.user.entity.UserHasTRoomEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述:(门牌信息) 具体业务Dao层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RoomDao extends AbstractBaseDao implements IRoomDao{

	@Override
	public List<UserHasTRoomEntity> selectRoomList(BigInteger userId, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		String pageSqlKey = "userHasTRoom.select_userHasTRoom_withPage_RoomEntity";
		String countSqlKey = "userHasTRoom.select_userHasTRoom_withPage_RoomEntity_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public RoomValidate selectRoomValidate(BigInteger userId, BigInteger roomId) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		qryMap.put("roomId", roomId);
		return sqlSession.selectOne("room.select_RoomValidate_ByUserId_RoomId", qryMap);
	}

	@Override
	public RoomValidate selectRoomValidateWithDefault(BigInteger userId) {
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("userId", userId);
		return sqlSession.selectOne("room.select_RoomValidate_With_Default", qryMap);
	}
	
	
	@Override
	public UserHasTRoom selectUserHasTRoomByUserIdRoomId(BigInteger userId, BigInteger roomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("roomId", roomId);
		return sqlSession.selectOne("room.select_UserHasTRoom_ByUserIdRoomId", tmpMap);
	}

	@Override
	public Integer selectExistCountByUserHasTRoomId(BigInteger userHasTRoomId, BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userHasTRoomId", userHasTRoomId);
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("room.select_ExistCount_By_UserHasTRoomId", tmpMap);
	}


	@Override
	public RoomWithValidate selectRoomWithValidateByUserAndOtherRoomId(BigInteger userId, BigInteger roomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("roomId", roomId);
		return sqlSession.selectOne("room.select_RoomWithValidate_ByUserAndOtherRoomId", tmpMap);
	}


	@Override
	public List<AddressCityHotEntity> selectAddressCityHotList() {
		return sqlSession.selectList("room.select_AddressCityHot_List");
	}


	@Override
	public List<AddressCity> selectAddressCityListAll() {
		return sqlSession.selectList("room.select_AddressCityList_All");
	}


	@Override
	public GroupBuilding selectExistGroupBuildingByName(BigInteger addressBlockId, String gbName, BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("addressBlockId", addressBlockId);
		tmpMap.put("gbName", gbName);
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("room.select_Exist_GroupBuilding_ByName",tmpMap);
	}

	@Override
	public List<Building> selectBuildingListByGbId(BigInteger groupBuildingId) {
		return sqlSession.selectList("room.select_BuildingList_ByGbId", groupBuildingId);
	}

	@Override
	public List<RealRoom> selectRealRoomListByBuildId(BigInteger buildingId) {
		return sqlSession.selectList("room.select_RealRoomList_ByBuildId", buildingId);
	}

	@Override
	public List<BuildingAndUnitEntity> getBuildingListByGbId(BigInteger gbId) {
		return sqlSession.selectList("room.select_BuildingInfo_ByGroupBuildingId", gbId);
	}

	@Override
	public GroupBuilding getGroupBuildingbyRrid(BigInteger realRoomId) {
		return sqlSession.selectOne("room.select_GroupBuilding_byRealRoomId", realRoomId);
	}

	@Override
	public List<RealRoomConfig> getPropfeeCanPayRoomByUserId(BigInteger userId) {
		return sqlSession.selectList("room.select_propfee_canpay_room_by_userId", userId);
	}
}
