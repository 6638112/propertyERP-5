package com.cnfantasia.server.api.room.dao;

import com.cnfantasia.server.api.propertycard.entity.RealRoomConfig;
import com.cnfantasia.server.api.room.entity.AddressCityHotEntity;
import com.cnfantasia.server.api.room.entity.BuildingAndUnitEntity;
import com.cnfantasia.server.api.room.entity.RoomWithValidate;
import com.cnfantasia.server.api.user.entity.UserHasTRoomEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

import java.math.BigInteger;
import java.util.List;

/**
 * 描述:(门牌信息) 具体业务Dao层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRoomDao {
	
	/**
	 * 查询当对应用户的门牌列表信息
	 * 
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<UserHasTRoomEntity> selectRoomList(BigInteger userId, PageModel pageModel);


	/**
	 * 查询门牌的验证状态
	 * 
	 * @param userId
	 * @param roomId
	 * @return
	 */
	public RoomValidate selectRoomValidate(BigInteger userId, BigInteger roomId);

	/**
	 * 查询用户默认门牌的验证状态
	 * 
	 * @param userId
	 * @return
	 */
	public RoomValidate selectRoomValidateWithDefault(BigInteger userId);

	/**
	 * 根据用户Id和门牌Id查询对应的门牌关系信息
	 * 
	 * @param userId
	 * @param roomId
	 * @return
	 */
	public UserHasTRoom selectUserHasTRoomByUserIdRoomId(BigInteger userId, BigInteger roomId);
	
	/**
	 * 查询用户门牌关系Id和用户Id 关系记录是否有对应
	 * @param userHasTRoomId
	 * @param userId
	 * @return
	 */
	public Integer selectExistCountByUserHasTRoomId(BigInteger userHasTRoomId,BigInteger userId);
	
	/**
	 * 查询对应用户 与 指定 roomId 有相同realRoom 的 门牌信息
	 * @param userId
	 * @param roomId
	 * @return
	 */
	public RoomWithValidate selectRoomWithValidateByUserAndOtherRoomId(BigInteger userId,BigInteger roomId);

	/**
	 * 查询热门城市列表
	 * @return
	 */
	public List<AddressCityHotEntity> selectAddressCityHotList();

	/**
	 * 查询所有行政区列表
	 * @return
	 */
	public List<AddressCity> selectAddressCityListAll();
	
	/**
	 * 查询已经存在的审核通过的小区或者是用户创建的小区
	 * @param addressBlockId
	 * @param gbName
	 * @param userId
	 * @return
	 */
	public GroupBuilding selectExistGroupBuildingByName(BigInteger addressBlockId,String gbName,BigInteger userId);

	/**
	 * 根据小区查询楼栋列表
	 * @param groupBuildingId
	 * @return
	 */
	public List<Building> selectBuildingListByGbId(BigInteger groupBuildingId);

	/**
	 * 根据楼栋查询门牌列表
	 * @param groupBuildingId
	 * @return
	 */
	public List<RealRoom> selectRealRoomListByBuildId(BigInteger buildingId);


	public List<BuildingAndUnitEntity> getBuildingListByGbId(BigInteger gbId);
	
	
	public GroupBuilding getGroupBuildingbyRrid(BigInteger realRoomId);

	public List<RealRoomConfig> getPropfeeCanPayRoomByUserId(BigInteger userId);
}
