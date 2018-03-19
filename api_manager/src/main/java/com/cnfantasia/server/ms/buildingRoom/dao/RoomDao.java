package com.cnfantasia.server.ms.buildingRoom.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.realRoom.dao.RealRoomBaseDao;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.property.importer.entity.PropertyProprietor4Import;
import com.cnfantasia.server.ms.property.importer.entity.RealRoom4Import;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrExportModelEntity;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrImportModelEntity;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class RoomDao extends RealRoomBaseDao implements IRoomDao {
	@Override
	public int queryRoomForCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("room.select_room_forCount");
	}

	@Override
	public List<RoomEntity> queryRoomForList(Map<String, Object> paramMap) {
		return sqlSession.selectList("room.select_room_forList", paramMap);
	}

	@Override
	public RoomEntity queryRoomById(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return sqlSession.selectOne("room.select_room_byId", id);
	}

	@Override
	public int deleteRoomByBuildingId(String buildingId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("buildingId", buildingId);
		paramMap.put("sysDelUser", UserContext.getCurrUser().getId());
		return sqlSession.update("room.delete_room_byBuildingId", paramMap);
	}

	@Override
	public List<RealRoom4Import> selectRealRoomByBuildingIdList(List<BigInteger> buildingIdList) {
		return sqlSession.selectList("room.select_real_room_by_buildingIdList", buildingIdList);
	}

	@Override
	public List<PropertyProprietor4Import> selectPropertyProprietorByRealRoomIdList(List<BigInteger> realRoomIdList) {
		return sqlSession.selectList("room.select_property_proprietor_by_rrIdList", realRoomIdList);
	}

	@Override
	public int doAutoRoomValidateFromDB(BigInteger groupBuildingId) {
		Integer effectedRows = sqlSession.selectOne("room.doAutoRoomValidateFromDB", groupBuildingId);
		return effectedRows == null ? 0 : effectedRows;
	}

	@Override
	public List<MrImportModelEntity> getMrImportModelEntity(BigInteger gbId) {
		return sqlSession.selectList("room.getMrImportModelEntity", gbId);
	}

	@Override
	public List<MrExportModelEntity> getMrExportModelEntity(BigInteger gbId, BigInteger itemId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbId", gbId);
		paramMap.put("itemId", itemId);
		return sqlSession.selectList("room.getMrExportModelEntity", paramMap);
	}
}
