/**   
* Filename:    ICommonRoomService.java   
* @version:    1.0  
* Create at:   2014年5月31日 上午5:37:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.commonBusiness.entity.UserHasTRoomParamEntity;
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
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    ICommonRoomService.java
 * @version:    1.0.0
 * Create at:   2014年5月31日 上午5:37:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月31日       shiyl             1.0             1.0 Version
 */
public interface ICommonRoomService {
	/**
	 * 创建虚拟门牌
	 * @param userId
	 * @param realRoomId
	 * @param isDefault
	 * @param realName
	 * @param inviterUserId 邀请人Id
	 * @return
	 */
	UserHasTRoom addRoom(BigInteger userId, BigInteger realRoomId, Boolean isDefault, String realName);
	UserHasTRoom addRoom(BigInteger userId, BigInteger realRoomId, Boolean isDefault, String realName, BigInteger inviterUserId);
	UserHasTRoom addRoomIgnoreAutoPhoneValidate(BigInteger userId, BigInteger realRoomId, Boolean isDefault, String realName, BigInteger inviterUserId);
	/**
	 * 新增用户门牌关系
	 * @param _roomId
	 * @param _userId
	 * @param adminType
	 * @param _inviterUserId
	 * @param _bindUserId 要绑定到的主用户Id
	 * @return
	 */
	UserHasTRoom addUserHasTRoom(BigInteger _roomId, BigInteger _userId, Integer _adminType, BigInteger _inviterUserId, BigInteger _bindUserId);
	/**
	 * 批量新增
	 * @param toAddUserHasTRoomList
	 */
	void addUserHasTRoomBatch(List<UserHasTRoomParamEntity> toAddUserHasTRoomList);
	/**
	 * 根据用户Id查询默认门牌信息
	 * @param userId
	 * @return
	 */
	RoomEntityWithValidate getDefaultRoomInfo(BigInteger userId);
	/**
	 * 根据用户Id查询默认门牌信息
	 * 增加返回小区的点支持的信息
	 * @param userId
	 * @return
	 */
	RoomEntityWithValidate getDefaultRoomInfoWithSupport(BigInteger userId);
	
	/**
	 * 根据用户Id查询默认门牌信息
	 * 增加返回小区的点召唤的信息
	 * @param userId
	 * @return
	 */
	RoomEntityWithValidate getDefaultRoomInfoWithSummon(BigInteger userId);
	
	/**
	 * 根据用户Id查询默认门牌信息,如果是空门牌则抛出异常
	 * 此时客户端需要引导用户去设置默认门牌
	 * @param userId
	 * @return
	 */
	RoomEntityWithValidate getDefaultRoomInfoExceptionWhenEmpty(BigInteger userId);
	/**
	 * 根据用户Id查询默认门牌信息,增加返回小区的点支持的信息
	 * 如果是空门牌则抛出异常  此时客户端需要引导用户去设置默认门牌
	 * @param userId
	 * @return
	 */
	RoomEntityWithValidate getDefaultRoomInfoExceptionWhenEmptyWithSupport(BigInteger userId);
	
	/**
	 * 当用户没有默认门牌的的时候,为用户增加空门牌，并设置默认
	 * @param userId
	 * @return
	 */
	UserHasTRoom addNullRoom(BigInteger userId);
	
	/**
	 * 根据虚拟门牌Id查询门牌信息
	 * @param roomId
	 * @return
	 */
	RoomEntityWithValidate getRoomEntityById(BigInteger roomId);
	
//	/**
//	 * 根据虚拟门牌Id查询对应的管理员列表
//	 */
//	public List<BigInteger> getAdminIdsByRoomId(BigInteger roomId);
	
//	/**
//	 * 查询用户创建的且被标识为first_hbconvert_state的门牌数量
//	 * @param userId 门牌创建者
//	 * @return
//	 */
//	public int getFirstHbconvertStateRoomCount(BigInteger userId);
//	
//	/**
//	 * 将用户当前的门牌设置首次兑换粮票的标识
//	 * @param userId
//	 * @return
//	 */
//	public int setRoomAsFirstHbconvertState(BigInteger userId);
	
	/**
	 * 根据用户Id查询其默认门牌所属的GroupBuilding信息
	 * @param userId
	 * @return
	 */
	GroupBuilding  getGroupBuildingByUserId(BigInteger userId);
	/**
	 * 根据用户Id查询其默认门牌所属的小区Id信息
	 * 若用户为空或者所属小区为空则返回默认小区
	 * @param userId
	 * @return
	 */
	BigInteger  getGroupBuildingIdByUserId(BigInteger userId);
	
	/**
	 * 根据用户Id查询其默认门牌的Id
	 * @param userId
	 * @return
	 */
	BigInteger getDefaultRoomIdByUserId(BigInteger userId);
	BigInteger getDefaultRoomIdByUserIdAllowNull(BigInteger userId);
	
	/**
	 * 查询用户主门牌Id
	 * @param userId
	 * @return
	 */
	BigInteger getMainRoomIdByUserId(BigInteger userId);
	BigInteger getMainRoomIdByUserIdExceptionIfNull(BigInteger userId);
	
	/**
	 * 根据小区Id列表查询其下的门牌用户列表
	 * @param groupBuildIdList
	 * @return
	 */
	List<UserSimpleEntity> getUserListByGroupBuildIds(List<BigInteger> groupBuildIdList);
	
	/**
	 * 根据城市名称查询对应的城市列表
	 * @param cityName
	 * @return
	 */
	List<AddressCity> getAddressCityListByCityNameDim(String cityName);

	/**
	 * 根据Id查询真实门牌信息
	 * @param realRoomId
	 * @return
	 */
	RealRoomEntity getRealRoomById(BigInteger realRoomId);
	
	/**
	 * 根据用户Id查询其默认门牌
	 * @param userId
	 * @return
	 */
	Room getDefaultRoomByUserId(BigInteger userId);
	
	/**
	 * 查询用户默认用户门牌关系信息
	 * @param userId
	 * @return
	 */
	UserHasTRoom getDefaultUserHasTRoom(BigInteger userId);
	
	/**
	 * 判断用户是否为特使用户
	 * @param userId
	 * @return
	 */
	boolean getIsSpecialUser(BigInteger userId);
	
	/**
	 * 根据小区Id查询小区信息
	 * @param groupBuildingId
	 * @return
	 */
	GroupBuildingEntity getGroupBuildingById(BigInteger groupBuildingId);
	
	
	/**
	 * 查询有用户注册过的小区列表
	 * @return
	 */
	List<GroupBuildingEntity> selectGroupBuildingIsRegisted();
	
	/**
	 * 根据用户Id查询对应的用户基本信息列表
	 * @param userIdList
	 * @return
	 */
	List<UserSimpleEntity> getUserListByUserIds(List<BigInteger> userIdList);
	
	/**
	 * 查询当前用户门牌下的住户列表 包含审核状态 返回未处理和已通过的 
	 * @return
	 */
	List<UserRoomApplyEntity> getUserListOfSameRoomByUserId(BigInteger userId, PageModel pageModel);
//	public List<RoomApplyEntity> getRoomApplyListOfSameRoomByUserId(BigInteger userId,PageModel pageModel);
	
	/**
	 * 移除当前用户下的住户列表 仅邀请人和管理员可以删除
	 * @param userId 当前用户
	 * @param toDelUserList 待删除的住户Id
	 * @return
	 */
	void removeUserOfSameRoom(BigInteger userId, List<BigInteger> toDelUserList);
	
	/**
	 * 默认为门牌增加一个验证通过的信息
	 * 同时刷新真实门牌的验证信息
	 * @param roomId
	 * @return
	 */
	void addValidateSuccessInfo(BigInteger userId, BigInteger roomId, Integer roomValidateSourceType, String roomValidateDesc);
	
	/**
	 * 检查真实门牌是否有验证过的门牌信息，如果有，则更新第一个验证通过的信息到t_real_room中
	 * @param realRoomId 真实门牌Id
	 * @param needFresh 如果真实门牌已经存在验证信息，是否重新刷新验证信息
	 * 返回是否执行了刷新
	 */
	boolean checkRealRoomFirstValidateAndUpd(BigInteger realRoomId, boolean needFresh);
	
	/**
	 * 根据用户Id 查询默认门牌对应的真实门牌信息
	 * @param userId
	 * @return
	 */
	Boolean getRealRoomValidateStatusByUserId(BigInteger userId);
	
	/**
	 * 根据百度经纬度查询最近的小区信息
	 * @param baiduLocation
	 * @return
	 */
	GroupBuildingSimpleWithDistance getNearestGroupBuildingByBaiduLocation(BaiduLocation baiduLocation);
	
	/**
	 * 根据百度经纬度查询附加的小区列表
	 * @param baiduLocation 百度地址
	 * @param maxDistance 最大距离
	 * @return
	 */
//	public List<GroupBuildingEntityWithDistance> getNearbyGroupBuildingByBaiduLocation(BaiduLocation baiduLocation,Double maxDistance,String searchKey);
	List<GroupBuildingEntityWithDistance> getNearbyGroupBuildingByBaiduLocation(GbQueryAddressParam addressParam, Double maxDistance, String searchKey, BigInteger userId);
	
	/**
	 * 查询用户自己的门牌列表
	 * @param userId
	 * @return
	 */
	List<Room> getUserRoomList(BigInteger userId);
	/**
	 * 查询用户下的用户门牌关系信息列表，附加返回room基本信息
	 * @param userId
	 * @return
	 */
	List<UserHasTRoomWithRoomSimpleEntity> getUserHasTRoomWithRoomSimpleList(BigInteger userId);
	
	/**
	 * 创建虚拟门牌需要进行的校验
	 * @param userId
	 * @param realRoomId
	 */
	void addRoomPreValidate(BigInteger userId, BigInteger realRoomId);
	
	/**
	 * 用户将指定的门牌切换到新realRoom
	 * @param userId
	 * @param roomId
	 * @param realRoomId
	 * @return
	 */
	void change2NewRealRoomId(BigInteger userId, BigInteger roomId, BigInteger realRoomId);
	
	BigInteger getGroupBuildingIdByUserIdForEbuy(BigInteger userId);
	
	public BigInteger getGroupBuildingIdByUserIdForHome(BigInteger userId);
	
	
//	/**
//	 * 检查并更新物业账单月份
//	 * @param userId
//	 * @param yearMonth
//	 * @return
//	 */
//	public String month2BillMonthByGroupBuilding(BigInteger userId,String yearMonth); 
//	public Date month2BillMonthByGroupBuilding(BigInteger userId,Date date);
//	
//	public String month2DiscountMonth(BigInteger userId,String yearMonth);
//	public Date month2DiscountMonth(BigInteger userId,Date date);
	
//	/**
//	 * 获取小区的账单月份与折扣月份的相差月份
//	 * @param userId
//	 * @return 账单月份-当前折扣月份= 返回值
//	 */
//	public Integer getGroupBuildingPropertyMonthDistance(BigInteger userId);
	
	/**
	 * 查询用户注册过的小区
	 * @author huangzj
	 * @paramter Map
	 * */
	List<GroupBuildingEntity> queryUserIsRegisterGroupbuilding(Map<String, Object> param);
	boolean queryUserIsRegisterGroupbuilding(BigInteger userId, List<Map<String, Object>> paramList);
	
	
	/**
	 * 完善门牌，自动验证门牌的判断
	 * @param userId
	 * @param roomId
	 */
	void autoValidateRoomForAddRoom(BigInteger userId, BigInteger roomId);
	/**
	 * 用户绑定手机号，自动验证门牌的判断
	 * @param userId
	 * @param bindPhone
	 */
	void autoValidateRoomForBindPhone(BigInteger userId, String bindPhone);
	
	/**
	 * 根据用户id获取真实门牌信息
	 */
	RealRoom selectRealRoomByUserId(BigInteger userId);

	/**
	 * 根据门牌获取各个上级的ID gbId, blockId, cityId, provinceId
	 * @param roomId
	 * @return
     */
	Map<String, Object> getRoomAddressIdByRoom(BigInteger roomId);

	/**
	 * 获取门牌详情 <城市><区县><小区><楼栋-单元-房号> .
	 * @param roomId 门牌ID
	 * @return
	 */
	String getRoomDetailAddress(BigInteger roomId);
}
