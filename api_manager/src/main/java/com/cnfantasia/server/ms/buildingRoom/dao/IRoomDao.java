package com.cnfantasia.server.ms.buildingRoom.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.realRoom.dao.IRealRoomBaseDao;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.property.importer.entity.PropertyProprietor4Import;
import com.cnfantasia.server.ms.property.importer.entity.RealRoom4Import;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrExportModelEntity;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrImportModelEntity;

public interface IRoomDao extends IRealRoomBaseDao {
	int queryRoomForCount(Map<String, Object> paramMap);
	
	List<RoomEntity> queryRoomForList(Map<String, Object> paramMap);
	
	RoomEntity queryRoomById(String id);
	
	int deleteRoomByBuildingId(String buildingId);

	List<RealRoom4Import> selectRealRoomByBuildingIdList(List<BigInteger> buildingIdList);

	List<PropertyProprietor4Import> selectPropertyProprietorByRealRoomIdList(List<BigInteger> realRoomIdList);

	/**
	 * 调用存储过程做门牌自动验证
	 * 
	 * @param groupBuildingId
	 * @return
	 */
	int doAutoRoomValidateFromDB(BigInteger groupBuildingId);

	/**
	 * 查询抄表收费导入需要的房间模板
	 * @param gbId
	 * @return
	 */
	List<MrImportModelEntity> getMrImportModelEntity(BigInteger gbId);

	/**
	 * 查询抄表房间信息
	 * @param gbId
	 * @param itemId
     * @return
     */
	List<MrExportModelEntity> getMrExportModelEntity(BigInteger gbId, BigInteger itemId);
}
