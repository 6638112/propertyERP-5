/**   
* Filename:    ICommonRoomDao.java   
* @version:    1.0  
* Create at:   2014年6月23日 上午10:50:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.commonBusiness.entity.UserHasTRoomWithRoomSimpleEntity;
import com.cnfantasia.server.api.commonBusiness.entity.UserRoomApplyEntity;
import com.cnfantasia.server.api.communitySupply.entity.BaiduLocation;
import com.cnfantasia.server.api.room.entity.GbQueryAddressParam;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntityWithDistance;
import com.cnfantasia.server.api.room.entity.GroupBuildingSimpleWithDistance;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.room.entity.Room;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    ICommonRoomDao.java
 * @version:    1.0.0
 * Create at:   2014年6月23日 上午10:50:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月23日       shiyl             1.0             1.0 Version
 */
public interface ICommonRoomDao {
	
	/**
	 * 根据虚拟门牌Id查询门牌信息
	 * @param roomId
	 * @return
	 */
	RoomEntityWithValidate selectRoomEntityById(BigInteger roomId);
	/**
	 * 根据虚拟门牌Id查询门牌信息 增加返回小区的点支持的信息
	 * @param roomId
	 * @param userId
	 * @return
	 */
	RoomEntityWithValidate selectRoomEntityByIdWithSupport(BigInteger roomId, BigInteger userId);
	/**
	 * 根据虚拟门牌Id查询门牌信息 增加返回小区的点召唤的信息
	 * @param roomId
	 * @param userId
	 * @return
	 */
	RoomEntityWithValidate selectRoomEntityByIdWithSummon(BigInteger roomId, BigInteger userId);
	
//	/**
//	 * 根据虚拟门牌Id查询对应的管理员列表
//	 */
//	public List<BigInteger> selectAdminIdsByRoomId(BigInteger roomId);
	
	/**
	 * 根据真实门牌Id查询所在小区信息
	 * @param realRoomId
	 * @return
	 */
	GroupBuilding selectGroupBuildingByRealRoomId(BigInteger realRoomId);

//	/**
//	 * 查询用户创建的且被标识为first_hbconvert_state的门牌数量
//	 * @param userId 门牌创建者
//	 * @return
//	 */
//	public int selectFirstHbconvertStateRoomCount(BigInteger userId);
//	
//	/**
//	 * 将用户当前的门牌设置首次兑换粮票的标识
//	 * @param userId
//	 * @return
//	 */
//	public int updateRoomAsFirstHbconvertState(BigInteger userId);
	
	/**
	 * 根据用户Id查询其默认门牌所属的GroupBuilding信息
	 * @param userId
	 * @return
	 */
	GroupBuilding  selectGroupBuildingByUserId(BigInteger userId);
	
	public Integer selectGroupBuildingStatus(BigInteger userId);
	
	/**
	 * 根据用户Id查询其默认门牌的Id
	 * @param userId
	 * @return
	 */
	BigInteger selectDefaultRoomIdByUserId(BigInteger userId);
	
	/**
	 * 查询用户主门牌Id
	 * @param userId
	 * @return
	 */
	BigInteger selectMainRoomIdByUserId(BigInteger userId);
	/**
	 * 更改主门牌
	 * @param userId
	 * @return
	 */
	Integer updateMainRoomIdByUserId(BigInteger userId, BigInteger mainRoomId);
	
	/**
	 * 根据小区Id列表查询其下的门牌用户列表
	 * @param groupBuildIdList
	 * @return
	 */
	List<UserSimpleEntity> selectUserListByGroupBuildIds(List<BigInteger> groupBuildIdList);
	/**
	 * 根据城市名称查询对应的城市列表
	 * @param cityName
	 * @return
	 */
	List<AddressCity> selectAddressCityListByCityNameDim(String cityName);
	/**
	 * 根据Id查询真实门牌信息
	 * @param realRoomId
	 * @return
	 */
	RealRoomEntity selectRealRoomById(BigInteger realRoomId);
	
	/**
	 * 根据用户Id查询其默认门牌
	 * @param userId
	 * @return
	 */
	Room selectDefaultRoomByUserId(BigInteger userId);
	/**
	 * 查询用户默认用户门牌关系信息
	 * @param userId
	 * @return
	 */
	UserHasTRoom selectDefaultUserHasTRoom(BigInteger userId);
	
	/**
	 * @param groupBuildingId
	 * @return
	 */
	GroupBuildingEntity selectGroupBuildingById(BigInteger groupBuildingId);
	/**
	 * @return
	 */
	List<GroupBuildingEntity> selectGroupBuildingIsRegisted();
	
	/**
	 * 根据用户Id查询对应的用户基本信息列表
	 * @param userIdList
	 * @return
	 */
	List<UserSimpleEntity> selectUserListByUserIds(List<BigInteger> userIdList);
	
	/**
	 * 查询当前用户门牌下的住户列表  包含审核状态 返回未处理和已通过的 
	 * @param userId 当前用户Id
	 * @param pageModel
	 * @return
	 */
	List<UserRoomApplyEntity> selectUserListOfSameRoomByUserId(BigInteger userId, PageModel pageModel);
//	public List<RoomApplyEntity> selectRoomApplyListOfSameRoomByUserId(BigInteger userId,PageModel pageModel);
	
	/**
	 * 逻辑删除当前用户下的住户列表 仅邀请人和管理员可以删除
	 * @param userId 当前用户
	 * @param toDelUserList 待删除的住户Id
	 * @return
	 */
	Integer deleteUserOfSameRoomLogic(BigInteger userId, List<BigInteger> toDelUserList);
	
	/**
	 * 根据真实门牌查询对应最早验证过的验证信息
	 * @param realRoomId
	 * @return
	 */
	RoomValidate selectRealRoomFirstValidate(BigInteger realRoomId);
	
	/**
	 * 根据用户Id 查询默认门牌对应的真实门牌信息
	 * @param userId
	 * @return
	 */
	RealRoom selectRealRoomByUserId(BigInteger userId);
	
	/**
	 * 根据百度经纬度查询最近的小区信息
	 * @param baiduLocation
	 * @return
	 */
	GroupBuildingSimpleWithDistance selectNearestGroupBuildingByBaiduLocation(BaiduLocation baiduLocation);
	
	/**
	 * 根据百度经纬度查询最近的小区信息
	 * @param baiduLocation
	 * @param maxDistance 最远的距离
	 * @return
	 */
//	public List<GroupBuildingEntityWithDistance> selectNearbyGroupBuildingByBaiduLocation(BaiduLocation baiduLocation,
//			Double maxDistance,String searchKey);
	List<GroupBuildingEntityWithDistance> selectNearbyGroupBuildingByBaiduLocation(GbQueryAddressParam addressParam,
																				   Double maxDistance, String searchKey, BigInteger userId);
	
	/**
	 * 查询用户的门牌列表
	 * @param userId
	 * @return
	 */
	List<Room> selectUserRoomList(BigInteger userId);
	/**
	 * 查询用户下的用户门牌关系信息列表，附加返回room基本信息
	 * @param userId
	 * @return
	 */
	List<UserHasTRoomWithRoomSimpleEntity> selectUserHasTRoomWithRoomSimpleList(BigInteger userId);
	
	/**
	 * 用户将指定的门牌切换到新realRoom
	 * @param userId
	 * @param roomId
	 * @param realRoomId
	 * @return
	 */
	Integer update2NewRealRoomId(BigInteger userId, BigInteger roomId, BigInteger realRoomId);
	
	/**
	 * 查询用户注册过的小区
	 * @author huangzj
	 * @paramter Map
	 * */
	List<GroupBuildingEntity> queryUserIsRegisterGroupbuilding(Map<String, Object> param);
	/**
	 * @param paramList
	 * @return
	 */
	Integer queryUserIsRegisterGroupbuilding(BigInteger userId, List<Map<String, Object>> paramList);
	/**
	 * 查询用户在小区下的门牌数量
	 * @param id
	 * @return
	 */
	Integer selectRoomCount(BigInteger gbId, BigInteger userId);
	
	
	/**
	 * 根据用户Id查询对应绑定的门牌
	 * @param userId
	 * @return
	 */
	String selectUserBindPhone(BigInteger userId);


	/**
	 * 查询用户门牌数量，未验证且对应业主手机号为bindPhone
	 * @param roomId
	 * @return
	 */
	Integer selectRoomMatchPhoneAndNotValidateCount(BigInteger roomId, String bindPhone);
	
	/**
	 * 查询用户门牌列表，未验证且对应业主手机号为bindPhone
	 * @param userId
	 * @param bindPhone
	 * @return
	 */
	List<Room> selectRoomListMatchPhoneAndNotValidate(BigInteger userId, String bindPhone);
	
	/**
	 * 查询用户门牌数量，已验证且对应业主手机号为bindPhone
	 * @param userId
	 * @param bindPhone
	 * @return
	 */
	Integer selectRoomMatchPhoneAndValidateCountByUserId(BigInteger userId, String bindPhone);
	
	/**
	 * 根据手机号查询对应的真实门牌Id
	 * @param bindPhone
	 * @return
	 */
	List<BigInteger> selectRealRoomIdByPhone(String bindPhone);
	
	/**
	 * 根据门牌Id查询真实门牌
	 * @param roomId
	 * @return
	 */
	RealRoom selectRealRoomByRoomId(BigInteger roomId);

	/**
	 * 根据门牌获取各个上级的ID gbId, blockId, cityId, provinceId
	 * @param roomId
	 * @return
	 */
	Map<String, Object> getRoomAddressIdByRoom(BigInteger roomId);

	String getRoomDetailAddress(BigInteger roomId);
}
