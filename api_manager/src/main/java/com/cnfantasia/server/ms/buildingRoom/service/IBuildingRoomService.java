package com.cnfantasia.server.ms.buildingRoom.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.ms.buildingRoom.entity.BuildingEntity;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.property.importer.entity.BuildingRoomProprietor;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrExportModelEntity;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrImportModelEntity;

public interface IBuildingRoomService{
	int queryRoomForCount(Map<String, Object> paramMap);
	
	List<RoomEntity> queryRoomForList(Integer curPageIndex, Integer pageSize, Map<String, Object> paramMap, boolean isPage);
	
	RoomEntity queryRoomById(String id);
	
	int saveOrUpdateRoom(String id, String buildingId,
						 String unitName, String num, String proprietorId, String proprietorName, String proprietorTel, String proprietorIdentifyNo, String roomDesc);
	
	int queryBuildingForCount(Map<String, Object> paramMap);
	
	List<BuildingEntity> queryBuildingForList(int curPageIndex, int pageSize, Map<String, Object> paramMap, boolean isPage);
	
	int saveOrUpdateBuilding(String id, String groupbuildingId, String name, String code);
	
	BuildingEntity queryBuildingById(String id);
	
	int deleteBuilding(String buildingId);
	
	int deleteRoom(String roomId, String propertyProprietorId);
	
	/**
	 * 导入房号数据
	 * */
	int saveImportRooms(List<RoomEntity> importRooms);

	/**
	 * 保存导入的数据
	 * 
	 * @param resultInfo
	 * @param buildingSet
	 * @param brpSet
	 */
	String saveBuildingRoomProprietor(BigInteger groupBuildingId, Set<String> buildingSet, Set<BuildingRoomProprietor> brpSet);

	/**
	 * 根据小区id来查询  小区下楼栋房号的数据（现在为excel导出信息校验所用）
	 * @param gbId
	 * @return
	 */
	Integer getBuildingAndRoomCountByGbId(BigInteger gbId);

	/**
	 * 查询抄表收费导入需要的房间模板
	 * @param gbId
	 * @return
     */
	List<MrImportModelEntity> getMrImportModelEntity(BigInteger gbId);

	/**
	 * 根据小区id和费用项查询抄表房间信息
	 * @param gbId
	 * @param id
     * @return
     */
	List<MrExportModelEntity> getMrExportModelEntity(BigInteger gbId, BigInteger id);
}
