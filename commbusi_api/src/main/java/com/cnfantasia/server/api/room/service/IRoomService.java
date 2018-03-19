package com.cnfantasia.server.api.room.service;

import com.cnfantasia.server.api.propertycard.entity.RealRoomConfig;
import com.cnfantasia.server.api.room.entity.AddressCityHotEntity;
import com.cnfantasia.server.api.room.entity.BuildingAndUnitEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.user.entity.UserHasTRoomEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;

import java.math.BigInteger;
import java.util.List;
/**
 * 描述:(门牌信息) 具体业务Service层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRoomService {
	/**
	 * 根据用户Id查询门牌列表
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<UserHasTRoomEntity> getRoomList(BigInteger userId, PageModel pageModel);
	
//	/**
//	 * 根据门牌Id查询用户列表
//	 * @param roomId
//	 * @param pageModel
//	 * @return
//	 */
//	public List<UserHasTRoomEntity> getUserList(BigInteger roomId, PageModel pageModel);
	
//	/**
//	 * 根据真实房间Id和用户Id查询用户已经绑定的门牌数目
//	 * @param realRoomId
//	 * @param userId
//	 * @return
//	 */
//	public int getCountByRealRoomUserId(BigInteger realRoomId, BigInteger userId);
//	/**用户通过选择真是房间，创建门牌
//	 * 
//	 * @param userId 用户Id
//	 * @param realRoomId 真实房间Id
//	 * @param isDefault 是否设置为默认门牌
//	 * @param realName 用户真实名称 为空则不作替换
//	 */
//	public UserHasTRoom addRomm(BigInteger userId,BigInteger realRoomId,BigInteger tBuildingFId,String roomName,Boolean isDefault,String realName);
	
//	/**
//	 * 移除门牌下的用户：状态改为禁用
//	 * @param roomId 虚拟门牌的Id
//	 * @param userId 用户Id
//	 * @return
//	 */
//	public boolean removeUserFromRoom(BigInteger roomId,BigInteger userId);
	
	/**
	 * 根据用户Id查询默认门牌信息
	 */
	public RoomEntityWithValidate getDefaultRoomInfo(BigInteger userId);
	
//	/**
//	 * 加入门牌
//	 * @param userId 用户Id
//	 * @param roomId 虚拟门牌Id
//	 * @param isDefault 是否设置为默认门牌
//	 */
//	public void joinRoom(BigInteger userId,BigInteger roomId,Boolean isDefault);
	
	/**
	 * 在小区中添加建筑和门牌
	 * @param groupBuildingId
	 * @param buildingName
	 * @param roomNum
	 * @param roomNum 
	 * @param userId
	 * @return
	 */
	public RoomEntityWithValidate addBuildingAndRoom(BigInteger groupBuildingId,String buildingName,String unitName,String roomNum, BigInteger userId, boolean changeDefaultRoom);
	
	/**
	 * 添加小区、建筑和门牌
	 * @param addressBlockId
	 * @param groupBuildingName
	 * @param buildingName
	 * @param roomNum
	 * @param userId
	 * @return
	 */
	public RoomEntityWithValidate addGroupBuildingAndRoom(BigInteger addressBlockId,String groupBuildingName,String buildingName,String unitName,String roomNum,BigInteger userId, boolean changeDefaltRoom);
	
	/**
	 * 只创建虚拟门牌
	 * @param realRoomId
	 * @param userId
	 * @return
	 */
	public RoomEntityWithValidate addVirtualRoomOnly(BigInteger gbId, BigInteger realRoomId,BigInteger userId, boolean changeDefaultRoom);
	
//	/**
//	 * 查询门牌下的所有抽奖记录
//	 * @param userId
//	 * @param roomId
//	 * @return
//	 */
//	public List<PrizeRecord> getAllPrizeRecord(BigInteger userId,BigInteger roomId);
	
	/**
	 * 查询门牌的验证状态
	 * @param userId
	 * @param roomId
	 * @return
	 */
	public RoomValidate getRoomValidate(BigInteger userId,BigInteger roomId);
	/**
	 * 查询用户默认门牌的验证状态
	 * @param userId
	 * @return
	 */
	public RoomValidate getRoomValidateWithDefault(BigInteger userId);
	
	/**
	 * 提交门牌验证
	 * @param userId
	 * @param roomId 为空则使用当前用户的默认门牌
	 * @param requestFileEntity
	 * @return
	 */
	public RoomValidate confirmValidate(BigInteger userId,BigInteger roomId,RequestFileEntity requestFileEntity);

//	/**
//	 * 用户申请加入门牌
//	 * @param userId 申请人
//	 * @param roomId 待加入的门牌
//	 */
//	public void applyJoinRoom(BigInteger userId, BigInteger roomId);
	
	/**
	 * 面对面要求加入门牌 直接同意 
	 * @param applyUserId 申请人Id
	 * @param roomId 申请加入的门牌Id
	 * @param inviteUserId 邀请人Id
	 */
	public void applyJoinRoomFace2Face(BigInteger applyUserId,BigInteger roomId,BigInteger inviteUserId);

	/**
	 * 用户决策是否同意加入门牌
	 * @param userId 决策者
	 * @param userHasTRoomId 申请的记录Id
	 * @param isAgree 是否同意 true同意,false不同意
	 * 返回true表示操作成功 返回false或者异常表示失败
	 */
	public boolean decideJoinRoom(BigInteger userId, BigInteger userHasTRoomId, Boolean isAgree);

	/**
	 * 门牌已经被其他用户验证过
	 * 用户申请门牌验证
	 * @param userId
	 */
	public void applyRoomValidate(BigInteger userId);
	
	/**
	 * 查询热门城市列表
	 * @return
	 */
	public List<AddressCityHotEntity> getAddressCityHotList();

	/**
	 * 用户修改门牌
	 * @param roomId
	 * @param realRoomId
	 * @param groupBuildingId
	 * @param addressBlockId
	 * @param groupBuildingName
	 * @param buildingName
	 * @param roomNum
	 * @return 返回修改后的用户默认门牌信息
	 */
	public RoomEntityWithValidate changeRealRoom(BigInteger userId,BigInteger roomId, BigInteger realRoomId, BigInteger groupBuildingId,
			BigInteger addressBlockId, String groupBuildingName, String buildingName, String unitName, String roomNum);
	
	/**
	 * 查询所有行政区列表
	 * @return
	 */
	public List<AddressCity> getAddressCityListAll();
	
	/**
	 * 根据小区查询楼栋列表
	 * @param groupBuildingId
	 * @return
	 */
	public List<Building> getBuildingListByGbId(BigInteger groupBuildingId);
	
	/**
	 * 根据楼栋查询门牌列表
	 * @param buildingId
	 * @return
	 */
	public List<RealRoom> getRealRoomListByBuildId(BigInteger buildingId);

	public List<BuildingAndUnitEntity> getBuildingListByGroupBuildingId(BigInteger gbId);
	
	public GroupBuilding getGroupBuildingbyRrid(BigInteger realRoomId);

	public List<RealRoomConfig> getPropfeeCanPayRoomByUserId(BigInteger userId);
	
}
