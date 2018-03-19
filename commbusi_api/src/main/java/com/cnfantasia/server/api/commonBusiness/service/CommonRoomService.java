/**   
* Filename:    CommonRoomService.java   
* @version:    1.0  
* Create at:   2014年5月31日 上午5:36:48   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import com.cnfantasia.server.api.achievement.constant.AchievementConstant.AchievementEnum;
import com.cnfantasia.server.api.achievement.service.IAchievementService;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRoomDao;
import com.cnfantasia.server.api.commonBusiness.entity.UserHasTRoomParamEntity;
import com.cnfantasia.server.api.commonBusiness.entity.UserHasTRoomWithRoomSimpleEntity;
import com.cnfantasia.server.api.commonBusiness.entity.UserRoomApplyEntity;
import com.cnfantasia.server.api.communitySupply.entity.BaiduLocation;
import com.cnfantasia.server.api.inviteReward.service.IInviteRewardService;
import com.cnfantasia.server.api.pub.generator.HuaGenerator;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.entity.GbQueryAddressParam;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntityWithDistance;
import com.cnfantasia.server.api.room.entity.GroupBuildingSimpleWithDistance;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.room.util.RoomStatusCheckUtil;
import com.cnfantasia.server.api.room.util.SpecialUserChekUtil;
import com.cnfantasia.server.api.roomValidate.dao.IRoomValidateDao;
import com.cnfantasia.server.api.roomVerifyPayment.dao.IRoomVerifyPaymentDao;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.microblogQueue.util.DefaultGbUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.realRoom.dao.IRealRoomBaseDao;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.room.dao.IRoomBaseDao;
import com.cnfantasia.server.domainbase.room.entity.Room;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;
import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userHasTRoom.dao.IUserHasTRoomBaseDao;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Filename:    CommonRoomService.java
 * @version:    1.0.0
 * Create at:   2014年5月31日 上午5:36:48
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月31日       shiyl             1.0             1.0 Version
 */
@Service
public class CommonRoomService implements ICommonRoomService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IRoomBaseDao roomBaseDao;
	public void setRoomBaseDao(IRoomBaseDao roomBaseDao) {
		this.roomBaseDao = roomBaseDao;
	}
	
	private IUserHasTRoomBaseDao userHasTRoomBaseDao;
	public void setUserHasTRoomBaseDao(IUserHasTRoomBaseDao userHasTRoomBaseDao) {
		this.userHasTRoomBaseDao = userHasTRoomBaseDao;
	}
	
	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}
	
	private ICommonRoomDao commonRoomDao;
	public void setCommonRoomDao(ICommonRoomDao commonRoomDao) {
		this.commonRoomDao = commonRoomDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IRoomValidateDao roomValidateDao;
	public void setRoomValidateDao(IRoomValidateDao roomValidateDao) {
		this.roomValidateDao = roomValidateDao;
	}

	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IRealRoomBaseDao realRoomBaseDao;
	public void setRealRoomBaseDao(IRealRoomBaseDao realRoomBaseDao) {
		this.realRoomBaseDao = realRoomBaseDao;
	}
	
	private IAchievementService achievementService;
	public void setAchievementService(IAchievementService achievementService) {
		this.achievementService = achievementService;
	}
	
	private ICommDataUpgradeService commDataUpgradeService;
	public void setCommDataUpgradeService(ICommDataUpgradeService commDataUpgradeService) {
		this.commDataUpgradeService = commDataUpgradeService;
	}
	private IInviteRewardService inviteRewardService;
	
	public void setInviteRewardService(IInviteRewardService inviteRewardService) {
		this.inviteRewardService = inviteRewardService;
	}
	
	private IRoomVerifyPaymentDao roomVerifyPaymentDao;
	public void setRoomVerifyPaymentDao(IRoomVerifyPaymentDao roomVerifyPaymentDao) {
		this.roomVerifyPaymentDao = roomVerifyPaymentDao;
	}

	/**
	 * 创建虚拟门牌需要进行的校验
	 * @param userId
	 * @param realRoomId
	 */
	@Override
	public void addRoomPreValidate(BigInteger userId,BigInteger realRoomId){
		{//校验是否存在
			Room roomQry = new Room();
			roomQry.setCreater(userId);
			roomQry.settRealRoomFId(realRoomId);
			int countRes = roomBaseDao.selectRoomCount(MapConverter.toMap(roomQry), false);
			if(countRes>0){
				logger.error("userId:" + userId + ",realRoomId:" + realRoomId);
				throw new BusinessRuntimeException("CommonRoomService.addRomm.hasCreated.failed");
			} else {
				// 更新t_room_verify_payment注册状态
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("tRealRoomId", realRoomId);
				List<RoomVerifyPayment> roomVerifyPayments = roomVerifyPaymentDao.selectRoomVerifyPaymentByCondition(paramMap, true);
				if(roomVerifyPayments!=null && roomVerifyPayments.size()>0){
					// update
					for(RoomVerifyPayment roomVerifyPayment:roomVerifyPayments){
						roomVerifyPayment.setRegisterState(1);
					}
					roomVerifyPaymentDao.updateRoomVerifyPaymentBatch(roomVerifyPayments);
				} else {
					// insert
					RealRoom realRoom = realRoomBaseDao.selectRealRoomBySeqId(realRoomId);
					List<RoomVerifyPayment> roomVerifyPaymentList = roomVerifyPaymentDao.selectRoomVerifyPaymentsByBuildingId(realRoom.gettBuildingFId());
					{
						List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_room_verify_payment, roomVerifyPaymentList.size());
						for(int i=0; i<roomVerifyPaymentList.size(); i++){
							RoomVerifyPayment roomVerifyPayment = roomVerifyPaymentList.get(i);
							roomVerifyPayment.setId(ids.get(i));
							roomVerifyPayment.settRealRoomId(realRoomId);
							roomVerifyPayment.setRoomNum(realRoom.getNum());
							roomVerifyPayment.setUnit(realRoom.getUnitName());
							roomVerifyPayment.setRegisterState(1);
						}
						roomVerifyPaymentDao.insertRoomVerifyPaymentBatch(roomVerifyPaymentList);
					}
				}
			}
		}
	}
	
	@Override
	public void addUserHasTRoomBatch(List<UserHasTRoomParamEntity> toAddUserHasTRoomList) {
		if(toAddUserHasTRoomList!=null&&toAddUserHasTRoomList.size()>0){//TODO 后续优化
			for(UserHasTRoomParamEntity tmpEntity:toAddUserHasTRoomList){
				BigInteger _roomId = tmpEntity.getRoomId();
				BigInteger _userId = tmpEntity.getUserId();
				BigInteger _inviterUserId = tmpEntity.getInviterUserId();
				BigInteger _bindUserId = tmpEntity.getBindUserId();
				Integer _adminType = tmpEntity.getAdminType();
				addUserHasTRoom(_roomId, _userId,_adminType, _inviterUserId, _bindUserId);
			}
		}
	}
	
	@Override
	public UserHasTRoom addUserHasTRoom(BigInteger roomId, BigInteger userId,Integer adminType, BigInteger inviterUserId,BigInteger bindUserId){
		{//判断用户是否已经存在该用户门牌关系
			UserHasTRoom userHasTRoom = new UserHasTRoom();
			userHasTRoom.settRoomFId(roomId);
			userHasTRoom.settUserFId(userId);
			List<UserHasTRoom> userHasTRoomList = userHasTRoomBaseDao.selectUserHasTRoomByCondition(MapConverter.toMap(userHasTRoom), false);
			if(userHasTRoomList!=null&&userHasTRoomList.size()>0){
				if(userHasTRoomList.size()==1){
					UserHasTRoom firstUserHasTRoom = userHasTRoomList.get(0);
					if(RoomDict.UserHasTRoom_ApplyStatus.SUCCESS.compareTo(firstUserHasTRoom.getApplyStatus())==0){
						throw new BusinessRuntimeException("CommonRoomService.addRoom.userHasTRoom.isExist.failed");
					}else{
						throw new BusinessRuntimeException("CommonRoomService.addRoom.userHasTRoom.isInApply");
					}
				}else{
					throw new BusinessRuntimeException("CommonRoomService.addRoom.userHasTRoom.multiRela.failed");
				}
			}
//			int countRes = userHasTRoomBaseDao.selectUserHasTRoomCount(MapConverter.toMap(userHasTRoom), false);
//			if(countRes>0){
//				throw new BusinessRuntimeException("CommonRoomService.addRoom.userHasTRoom.isExist.failed");
//			}
		}
		//添加门牌关系信息
		BigInteger userHasTRoomAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_room);
		UserHasTRoom userHasTRoom = new UserHasTRoom();
		userHasTRoom.setId(userHasTRoomAddId);
		userHasTRoom.settRoomFId(roomId);
		userHasTRoom.settUserFId(userId);
		userHasTRoom.setBindUserId(bindUserId);
		if(bindUserId!=null){//syl-add冗余finalUserId
			userHasTRoom.setFinalUserId(bindUserId);
		}else{
			userHasTRoom.setFinalUserId(userId);
		}
		if(!StringUtils.isEmpty(inviterUserId)){//邀请人
			userHasTRoom.setInviterId(inviterUserId);
		}
		userHasTRoom.setIsAdmin(adminType);
		userHasTRoom.setApplyStatus(RoomDict.UserHasTRoom_ApplyStatus.SUCCESS);//创建者 默认通过
		
		{
			int userHasTRoomAddres = userHasTRoomBaseDao.insertUserHasTRoom(userHasTRoom);
			if(userHasTRoomAddres<=0){
				throw new BusinessRuntimeException("CommonRoomService.addRoom.insertUserHasTRoom.failed");
			}
		}
		return userHasTRoom;
	}
	
	
	@Override
	public UserHasTRoom addRoomIgnoreAutoPhoneValidate(BigInteger userId,BigInteger realRoomId,Boolean isDefault,String realName,BigInteger inviterUserId){
		addRoomPreValidate(userId, realRoomId);
		
		//添加虚拟门牌
		Room oldRoom = commonRoomDao.selectDefaultRoomByUserId(userId);
		
		GroupBuilding groupBuilding = commonRoomDao.selectGroupBuildingByRealRoomId(realRoomId);
		BigInteger roomId = uuidManager.getNextUuidBigInteger(SEQConstants.t_room);
		Room room = new Room();
		room.setCreater(userId);
		room.setHuaId(HuaGenerator.getRoomHuaId(groupBuilding.getName(),roomId));
		room.settRealRoomFId(realRoomId);
		room.setId(roomId);
		{
			int insertRoomCount = roomBaseDao.insertRoom(room);
			if(insertRoomCount<=0){
				//throw new BusinessRuntimeException("CommonRoomService.addRoom.insertRoom.failed");
			} else {
				// 更新t_room_verify_payment注册状态
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("tRealRoomId", realRoomId);
				List<RoomVerifyPayment> roomVerifyPayments = roomVerifyPaymentDao.selectRoomVerifyPaymentByCondition(paramMap, true);
				if(roomVerifyPayments!=null && roomVerifyPayments.size()>0){
					// update
					for(RoomVerifyPayment roomVerifyPayment:roomVerifyPayments){
						roomVerifyPayment.setRegisterState(1);
					}
					roomVerifyPaymentDao.updateRoomVerifyPaymentBatch(roomVerifyPayments);
				} else {
					// insert
					RealRoom realRoom = realRoomBaseDao.selectRealRoomBySeqId(realRoomId);
					List<RoomVerifyPayment> roomVerifyPaymentList = roomVerifyPaymentDao.selectRoomVerifyPaymentsByBuildingId(realRoom.gettBuildingFId());
					{
						List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_room_verify_payment, roomVerifyPaymentList.size());
						for(int i=0; i<roomVerifyPaymentList.size(); i++){
							RoomVerifyPayment roomVerifyPayment = roomVerifyPaymentList.get(i);
							roomVerifyPayment.setId(ids.get(i));
							roomVerifyPayment.settRealRoomId(realRoomId);
							roomVerifyPayment.setRoomNum(realRoom.getNum());
							roomVerifyPayment.setUnit(realRoom.getUnitName());
							roomVerifyPayment.setRegisterState(1);
						}
						roomVerifyPaymentDao.insertRoomVerifyPaymentBatch(roomVerifyPaymentList);
					}
				}
			}
		}
		BigInteger bindUserId = null;
		UserHasTRoom userHasTRoom = addUserHasTRoom(roomId, userId,RoomDict.UserHasTRoom_IsAdmin.TRUE,inviterUserId,bindUserId);
		//设置默认门牌
		if(isDefault){
			User user = new User();
			user.setId(userId);
			user.setDefaultRoomId(roomId);
			int userUpdRes = userBaseDao.updateUser(user);
			if(userUpdRes<=0){
				throw new BusinessRuntimeException("CommonRoomService.addRoom.updUserDefaultRoom.failed");
			}
		}
		//修改姓名
		if(!StringUtils.isEmpty(realName)){
			User user = new User();
			user.setId(userId);
			user.setRealName(realName);
			int userUpdRes = userBaseDao.updateUser(user);
			if(userUpdRes<=0){
				throw new BusinessRuntimeException("CommonRoomService.addRoom.updateUser.failed");
			}
		}
		
		//syl-add-2015-5-30 21:36:29当老门牌为空门牌时,设定当前门牌为主门牌
		if(RoomStatusCheckUtil.checkIsRoomEmpty(oldRoom)){
			commonRoomDao.updateMainRoomIdByUserId(userId, roomId);
		}
		
		//2015-4-23 09:41:33 当 老门牌为空门牌 且 新门牌不是空门牌 时，转移数据
		if(RoomStatusCheckUtil.checkIsRoomEmpty(oldRoom)&&!RoomStatusCheckUtil.checkIsRealRoomEmpty(realRoomId)){
			//数据转移
//			//将old门牌下的抽奖折扣转移到新门牌下
//			commDataUpgradeService.transferPrizeRecordFromOld2New(userId, oldRoom.getId(), roomId);
//			//syl-add 将old门牌下的意外惊喜转移到新门牌下
//			commDataUpgradeService.convertSurpriseGiftDataByRoomId(userId, oldRoom.getId(), roomId);
//			//syl-add 2015-5-15 16:34:41 将old门牌下的神马粮票转移到新门牌下
//			commDataUpgradeService.convertShenMaHbOld2New(userId, oldRoom.getId(), roomId);
			commDataUpgradeService.convertDataFromOld2New(userId, null != oldRoom ? oldRoom.getId() : null, userId, roomId);
		}
		
		//查询用户是否有该小区下的门牌，若无则提示
		if(!DefaultGbUtil.checkIsDefaultGb(groupBuilding.getId())){
			Integer existRoomCount = commonRoomDao.selectRoomCount(groupBuilding.getId(),userId);
			if(existRoomCount<=1){//街坊消息推送 
				ApplicationContextBothUtil.getMicroblogQueueService().joinGroupBuilding(userId,groupBuilding.getId(),groupBuilding.getName(),roomId);
			}
		}
		return userHasTRoom;
	}
	@Override
	public UserHasTRoom addRoom(BigInteger userId,BigInteger realRoomId,Boolean isDefault,String realName,BigInteger inviterUserId){
		UserHasTRoom userHasTRoom = addRoomIgnoreAutoPhoneValidate(userId, realRoomId, isDefault, realName, inviterUserId);
		BigInteger roomId = userHasTRoom.gettRoomFId();
		//校验门牌验证,syl-add-2015-7-30 14:49:49
		if(!RoomConstants.ROOM_NULL_ID_REAL.equals(realRoomId)) {
			autoValidateRoomForAddRoom(userId, roomId);
		}
		
		return userHasTRoom;
	}
	
	
	@Override
	public UserHasTRoom addRoom(BigInteger userId, BigInteger realRoomId, Boolean isDefault, String realName) {
		return addRoom(userId, realRoomId, isDefault, realName, null);
	}
	
	/**
	 * 查询用户默认门牌Id
	 * @param userId
	 * @return
	 */
	@Override
	public BigInteger getDefaultRoomIdByUserId(BigInteger userId){
		BigInteger defaultRoomId =commonRoomDao.selectDefaultRoomIdByUserId(userId);
//		BigInteger defaultRoomId = userBaseDao.selectUserBySeqId(userId).getDefaultRoomId();
		if(StringUtils.isEmpty(defaultRoomId)){
			logger.error("CommonRoomService.getDefaultRoomInfo.defaultRoomId.isnull: " + userId);
			throw new BusinessRuntimeException("CommonRoomService.getDefaultRoomInfo.defaultRoomId.isnull");
		}
		return defaultRoomId;
	}
	@Override
	public BigInteger getDefaultRoomIdByUserIdAllowNull(BigInteger userId){
		BigInteger defaultRoomId =commonRoomDao.selectDefaultRoomIdByUserId(userId);
		return defaultRoomId;
	}
	
	@Override
	public BigInteger getMainRoomIdByUserId(BigInteger userId) {
		BigInteger mainRoomId =commonRoomDao.selectMainRoomIdByUserId(userId);
		return mainRoomId;
	}
	@Override
	public BigInteger getMainRoomIdByUserIdExceptionIfNull(BigInteger userId) {
		BigInteger mainRoomId =getMainRoomIdByUserId(userId);
		if(StringUtils.isEmpty(mainRoomId)){
			throw new BusinessRuntimeException("CommonRoomService.getMainRoomIdByUserId.mainRoomId.isnull");
		}
		return mainRoomId;
	}
	
	@Override
	public RoomEntityWithValidate getDefaultRoomInfo(BigInteger userId) {
		RoomEntityWithValidate roomEntity = null;
		//查询用户默认门牌Id
		BigInteger defaultRoomId = getDefaultRoomIdByUserId(userId);
		//查询门牌信息
		roomEntity=commonRoomDao.selectRoomEntityById(defaultRoomId);
		return roomEntity;
	}
	@Override
	public RoomEntityWithValidate getDefaultRoomInfoWithSupport(BigInteger userId) {
		RoomEntityWithValidate roomEntity = null;
		//查询用户默认门牌Id
		BigInteger defaultRoomId = getDefaultRoomIdByUserId(userId);
		//查询门牌信息
		roomEntity=commonRoomDao.selectRoomEntityByIdWithSupport(defaultRoomId, userId);
		return roomEntity;
	}
	
	@Override
	public RoomEntityWithValidate getDefaultRoomInfoWithSummon(BigInteger userId) {
		RoomEntityWithValidate roomEntity = null;
		//查询用户默认门牌Id
		BigInteger defaultRoomId = getDefaultRoomIdByUserId(userId);
		//查询门牌信息
		roomEntity=commonRoomDao.selectRoomEntityByIdWithSummon(defaultRoomId, userId);
		return roomEntity;
	}
	
	@Override
	public RoomEntityWithValidate getDefaultRoomInfoExceptionWhenEmpty(BigInteger userId) {
		RoomEntityWithValidate roomEntity = getDefaultRoomInfo(userId);
		if(roomEntity==null||roomEntity.checkIsEmptyRoom()){
			throw new BusinessRuntimeException(RoomConstants.DEFALT_ROOM_ISEMPTY_AND_NEEDSET_EXCEPTION_CODE);
		}
		return roomEntity;
	}
	@Override
	public RoomEntityWithValidate getDefaultRoomInfoExceptionWhenEmptyWithSupport(BigInteger userId) {
		RoomEntityWithValidate roomEntity = getDefaultRoomInfoWithSupport(userId);
		if(roomEntity==null||roomEntity.checkIsEmptyRoom()){
			throw new BusinessRuntimeException(RoomConstants.DEFALT_ROOM_ISEMPTY_AND_NEEDSET_EXCEPTION_CODE);
		}
		return roomEntity;
	}
	
	@Override
	public UserHasTRoom addNullRoom(BigInteger userId) {
		BigInteger realRoomId = RoomConstants.ROOM_NULL_ID_REAL;
		Boolean isDefault = true;
		return addRoom(userId, realRoomId, isDefault, null);
	}
	@Override
	public RoomEntityWithValidate getRoomEntityById(BigInteger roomId) {
		return commonRoomDao.selectRoomEntityById(roomId);
	}
	
//	@Override
//	public List<BigInteger> getAdminIdsByRoomId(BigInteger roomId) {
//		return commonRoomDao.selectAdminIdsByRoomId(roomId);
//	}

//	@Override
//	public int getFirstHbconvertStateRoomCount(BigInteger userId) {
//		return commonRoomDao.selectFirstHbconvertStateRoomCount(userId);
//	}
//
//	@Override
//	public int setRoomAsFirstHbconvertState(BigInteger userId) {
//		return commonRoomDao.updateRoomAsFirstHbconvertState(userId);
//	}

	@Override
	public GroupBuilding getGroupBuildingByUserId(BigInteger userId) {
		return commonRoomDao.selectGroupBuildingByUserId(userId);
	}
	
	@Override
	public BigInteger getGroupBuildingIdByUserId(BigInteger userId) {
		BigInteger groupBuildId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
		if(HeaderManager.getGbId() != null) {
			groupBuildId = HeaderManager.getGbId();
		}
		
		if(userId!=null){
			//获取用户所属小区Id，若未空则使用默认的小区Id
			GroupBuilding groupBuilding = getGroupBuildingByUserId(userId);
			if(groupBuilding!=null&&groupBuilding.getId()!=null && groupBuilding.getId().intValue() != -1){
				groupBuildId = groupBuilding.getId();
			}
		}
		
		return groupBuildId;
		
	}
	
	@Override
	public BigInteger getGroupBuildingIdByUserIdForHome(BigInteger userId) {
		BigInteger groupBuildId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
		
		if(HeaderManager.getGbId() != null) {
			groupBuildId = HeaderManager.getGbId();
		}
		
		if(userId!=null){
			//获取用户所属小区Id，若未空则使用默认的小区Id
			GroupBuilding groupBuilding = getGroupBuildingByUserId(userId);
			if(groupBuilding!=null&&groupBuilding.getId()!=null && groupBuilding.getId().intValue() != -1){
				groupBuildId = groupBuilding.getId();
			}
		}
		return groupBuildId == null ? RoomConstants.DEFAULT_GROUP_BUILDING_ID : groupBuildId;
	}
	
	@Override
	public BigInteger getGroupBuildingIdByUserIdForEbuy(BigInteger userId) {
		BigInteger groupBuildId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
		if(HeaderManager.getGbId() != null) {
			groupBuildId = HeaderManager.getGbId();
			return groupBuildId;
		}
		
		if(userId!=null){
			//获取用户所属小区Id，若未空则使用默认的小区Id
			GroupBuilding groupBuilding = getGroupBuildingByUserId(userId);
			if(groupBuilding!=null&&groupBuilding.getId()!=null && groupBuilding.getId().intValue() != -1){
				groupBuildId = groupBuilding.getId();
			}
		}
		
		return groupBuildId;
	}
	
	@Override
	public List<UserSimpleEntity> getUserListByGroupBuildIds(List<BigInteger> groupBuildIdList) {
		return commonRoomDao.selectUserListByGroupBuildIds(groupBuildIdList);
	}

	@Override
	public List<AddressCity> getAddressCityListByCityNameDim(String cityName) {
		return commonRoomDao.selectAddressCityListByCityNameDim(cityName);
	}

	@Override
	public RealRoomEntity getRealRoomById(BigInteger realRoomId) {
		return commonRoomDao.selectRealRoomById(realRoomId);
	}

	@Override
	public Room getDefaultRoomByUserId(BigInteger userId) {
		return commonRoomDao.selectDefaultRoomByUserId(userId);
	}

	@Override
	public GroupBuildingEntity getGroupBuildingById(BigInteger groupBuildingId) {
		return commonRoomDao.selectGroupBuildingById(groupBuildingId);
	}
	
	@Override
	public List<GroupBuildingEntity> selectGroupBuildingIsRegisted() {
		return commonRoomDao.selectGroupBuildingIsRegisted();
	}
	
	@Override
	public UserHasTRoom getDefaultUserHasTRoom(BigInteger userId) {
		return commonRoomDao.selectDefaultUserHasTRoom(userId);
	}
	
	@Override
	public boolean getIsSpecialUser(BigInteger userId) {
		boolean isSpecialUser = false;
		UserHasTRoom userHasTRoom = commonRoomDao.selectDefaultUserHasTRoom(userId);
		if(SpecialUserChekUtil.checkIsSpecialUser(userHasTRoom)){
			isSpecialUser = true;
		}
		return isSpecialUser;
	}
	
	@Override
	public List<UserSimpleEntity> getUserListByUserIds(List<BigInteger> userIdList) {
		return commonRoomDao.selectUserListByUserIds(userIdList);
	}
	
	@Override
	public List<UserRoomApplyEntity> getUserListOfSameRoomByUserId(BigInteger userId,PageModel pageModel) {
		return commonRoomDao.selectUserListOfSameRoomByUserId(userId,pageModel);
	}
//	@Override
//	public List<RoomApplyEntity> getRoomApplyListOfSameRoomByUserId(BigInteger userId,PageModel pageModel) {
//		return commonRoomDao.selectRoomApplyListOfSameRoomByUserId(userId, pageModel);
//	}

	@Override
	public void removeUserOfSameRoom(BigInteger userId, List<BigInteger> toDelUserList) {
		Integer resCount =  commonRoomDao.deleteUserOfSameRoomLogic(userId, toDelUserList);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("CommonRoomService.removeUserOfSameRoom.failed");
		}
	}
	
	@Override
	public void addValidateSuccessInfo(BigInteger userId,BigInteger roomId,Integer roomValidateSourceType,String roomValidateDesc) {
		if(userId==null||roomId==null){return;}
		{//检查是否已存在验证信息
			RoomValidate roomValidateQry = new RoomValidate();
			roomValidateQry.settRoomFId(roomId);
			List<RoomValidate> existList = roomValidateDao.selectRoomValidateByCondition(MapConverter.toMap(roomValidateQry), false);
			if(existList!=null&&existList.size()>0){
				List<BigInteger> toDelIds = new ArrayList<BigInteger>();
				for(RoomValidate tmpRoomValidate:existList){
					toDelIds.add(tmpRoomValidate.getId());
					if(tmpRoomValidate.getVerifyStatus()!=null
							&&tmpRoomValidate.getVerifyStatus().compareTo(RoomDict.RoomValidte_VerifyStatus.SUCCESS)==0){
						Room tmpRoom = roomBaseDao.selectRoomBySeqId(roomId);
						checkRealRoomFirstValidateAndUpd(tmpRoom.gettRealRoomFId(), false);
						return;
					}
				}
				roomValidateDao.deleteRoomValidateLogicBatch(toDelIds);//删除之前的验证
			}else{
			}
		}
		
		//新增
		RoomValidate roomValidate = new RoomValidate();
		BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_room_validate);
		roomValidate.setId(addId);
		roomValidate.settRoomFId(roomId);
		roomValidate.setVerifyPicUrl(null);
//		roomValidate.setVerifyResultDesc("system default");
		roomValidate.setVerifyResultDesc(roomValidateDesc);//syl-2015-1-14 15:16:52-upd "缴费自动审核通过"
		roomValidate.setVerifyStatus(RoomDict.RoomValidte_VerifyStatus.SUCCESS);
		roomValidate.setSourceType(roomValidateSourceType);//RoomDict.RoomValidte_SourceType.PayBill_Auto_Add
		roomValidate.setUserId(userId);
		roomValidate.setVerifySuccTime(dualDao.getNowTime());
		roomValidate.setRecordNum(roomValidateDao.selectRecordNum(userId)+1);
		Integer res =  roomValidateDao.insertRoomValidate(roomValidate);
		if(res==null||res<=0){
			throw new BusinessRuntimeException("CommonRoomService.addValidateSuccessInfo.failed");
		}
		//添加成就信息
//		try {
//			achievementService.addAchievement(userId, AchievementEnum.renzhyonghu);
//		} catch (Exception e) {
//			logger.error("addValidateSuccessInfo.addAchievement.failed", e);
//		}
		//神码行动新增邀请人和注册新人的验证门牌粮票奖励 huangzj 2015-05-12
//		inviteRewardService.excuteInviteRewardForVerifyRoom(roomValidate.getUserId(), roomId);
		
		/*{//街坊消息推送-huangzj2015-06-26 syl-del-2015-7-16 10:03:41
			String text = "解放号"+userId+"用户已通过门牌验证！已认证的小伙伴快来报名体验“全年物业费打折”福利，同小区5户成团报名即可，详情加群：458449740";
			BigInteger groupBuildingId = this.getGroupBuildingIdByUserId(userId);
			MicroblogQueuePool.addActivity(
					MicroblogQueuePool.packageMicroblogQueue(text, 
							MicroblogQueueConstant.Source_Type.USER, MicroblogQueueConstant.Timing.NO, 
							groupBuildingId, MicroblogQueueConstant.MQ_Level.Code_60));
		}*/
		
		Room tmpRoom = roomBaseDao.selectRoomBySeqId(roomId);
		checkRealRoomFirstValidateAndUpd(tmpRoom.gettRealRoomFId(), true);
	}
	
	@Override
	public boolean checkRealRoomFirstValidateAndUpd(BigInteger realRoomId,boolean needFresh) {
		RealRoom realRoom = realRoomBaseDao.selectRealRoomBySeqId(realRoomId);
		boolean needUpd = false;
		if(realRoom!=null){
//			if(realRoom.getValidateStatus()!=null&&realRoom.getValidateStatus().compareTo(RoomDict.RealRoom_ValidateStatus.IS_VALIDATED)==0){
			if(RoomStatusCheckUtil.checkIsRealRoomValidated(realRoom)){
				//已经被验证的
//				if(needFresh){needUpd = true;}//指定刷新则更新 //syl-del-2015-3-20 10:38:47 已经有验证信息的则不再更新
			}else{//realRoom未被验证的则需要同步更新
				needUpd = true;
			}
		}
		//如果有则
		if(needUpd){
			//查询真实门牌下已经验证过的且时间最早验证通过的
			RoomValidate firstRoomValidate = commonRoomDao.selectRealRoomFirstValidate(realRoomId);
			if(firstRoomValidate!=null){
				RealRoom realRoomUpd = new RealRoom();
				realRoomUpd.setId(realRoom.getId());
				realRoomUpd.setValidateRoomId(firstRoomValidate.gettRoomFId());
				realRoomUpd.setValidateStatus(RoomDict.RealRoom_ValidateStatus.IS_VALIDATED);
				realRoomUpd.setValidateTime(firstRoomValidate.getVerifySuccTime());
				realRoomUpd.setValidateUserId(firstRoomValidate.getUserId());
				Integer resCount = realRoomBaseDao.updateRealRoom(realRoomUpd);
				if(resCount==null||resCount<=0){
					throw new BusinessRuntimeException("CommonRoomService.checkRealRoomFirstValidateAndUpd.updateRealRoom.failed");
				}
			}
		}
		return needUpd;
	}

	@Override
	public Boolean getRealRoomValidateStatusByUserId(BigInteger userId) {
		RealRoom realRoom = commonRoomDao.selectRealRoomByUserId(userId);
		boolean res = false;
//		if(realRoom!=null&&realRoom.getValidateStatus().compareTo(RoomDict.RealRoom_ValidateStatus.IS_VALIDATED)==0){
		if(RoomStatusCheckUtil.checkIsRealRoomValidated(realRoom)){
			res = true;
		}
		return res;
	}


	@Override
	public GroupBuildingSimpleWithDistance getNearestGroupBuildingByBaiduLocation(BaiduLocation baiduLocation) {
		if(baiduLocation==null||!baiduLocation.isAvaible()){
			return null;
		}
		return commonRoomDao.selectNearestGroupBuildingByBaiduLocation(baiduLocation);
	}

	@Override
	public List<GroupBuildingEntityWithDistance> getNearbyGroupBuildingByBaiduLocation(GbQueryAddressParam addressParam,Double maxDistance,String searchKey,BigInteger userId) {
		if(searchKey!=null){
			searchKey = searchKey.trim();
		}
		return commonRoomDao.selectNearbyGroupBuildingByBaiduLocation(addressParam,maxDistance,searchKey,userId);
	}

	@Override
	public List<Room> getUserRoomList(BigInteger userId) {
		return commonRoomDao.selectUserRoomList(userId);
	}
	@Override
	public List<UserHasTRoomWithRoomSimpleEntity> getUserHasTRoomWithRoomSimpleList(BigInteger userId) {
		return commonRoomDao.selectUserHasTRoomWithRoomSimpleList(userId);
	}
	@Override
	public void change2NewRealRoomId(BigInteger userId, BigInteger roomId, BigInteger realRoomId) {
		Integer resCount = commonRoomDao.update2NewRealRoomId(userId, roomId, realRoomId);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("commonRoom.change2NewRealRoomId.failed");
		}
	}
	@Override
	public List<GroupBuildingEntity> queryUserIsRegisterGroupbuilding(
			Map<String, Object> param) {
		return commonRoomDao.queryUserIsRegisterGroupbuilding(param);
	}
	
	@Override
	public boolean queryUserIsRegisterGroupbuilding(BigInteger userId,List<Map<String, Object>> paramList) {
		boolean res = false;
		if(userId!=null&&paramList.size()>0){
			Integer resCount = commonRoomDao.queryUserIsRegisterGroupbuilding(userId, paramList);
			if(resCount!=null&&resCount>0){
				res = true;
			}
		}
		return res;
	}
	
//	/**
//	 * 是否为下个月小区
//	 * @param userId
//	 * @return
//	 */
//	private boolean isNextMonthGroupBuilding(BigInteger userId){
//		boolean res = false;
//		GroupBuilding currGroupBuilding = getGroupBuildingByUserId(userId);
//		//syl-add 动态判断物业周期
//		if(currGroupBuilding!=null&&currGroupBuilding.getPropertyMonthChange()!=null
//				&&GroupBuildingDict.GroupBuilding_PropertyMonthChange.NextMonth.compareTo(currGroupBuilding.getPropertyMonthChange())==0){
//			res = true;
//		}
//		return res;
//	}
	
//	/**
//	 * 获取小区的账单月份与折扣月份的相差月份
//	 * @param userId
//	 * @return
//	 */
//	@Override
//	public Integer getGroupBuildingPropertyMonthDistance(BigInteger userId){
//		GroupBuilding currGroupBuilding = getGroupBuildingByUserId(userId);
//		Integer res = BusinessMonthYear.getMonthDistance(currGroupBuilding);
//		return res;
//	}
	
//	@Override
//	public String month2BillMonthByGroupBuilding(BigInteger userId, String yearMonth) {
//		String resData = yearMonth;
////		if(isNextMonthGroupBuilding(userId)){
////			resData = DateUtil.getNextMonth(yearMonth, DateUtil.formatMonth.get(),1);
////		}
//		Integer property_discount = getGroupBuildingPropertyMonthDistance(userId);
//		resData = DateUtil.getNextMonth(yearMonth, DateUtil.formatMonth.get(),property_discount);
//		return resData;
//	}
//
//	@Override
//	public Date month2BillMonthByGroupBuilding(BigInteger userId, Date date) {
//		Date resData = date;
//		if(date!=null){
////			if(isNextMonthGroupBuilding(userId)){
////				resData = DateUtil.getNextMonth(date,1);
////			}
//			Integer property_discount = getGroupBuildingPropertyMonthDistance(userId);
//			resData = DateUtil.getNextMonth(date,property_discount);
//		}
//		return resData;
//	}
//
//	@Override
//	public String month2DiscountMonth(BigInteger userId, String yearMonth) {
//		String resData = yearMonth;
////		if(isNextMonthGroupBuilding(userId)){
////			resData = DateUtil.getNextMonth(yearMonth, DateUtil.formatMonth.get(),-1);
////		}
//		Integer property_discount = getGroupBuildingPropertyMonthDistance(userId);
//		resData = DateUtil.getNextMonth(yearMonth, DateUtil.formatMonth.get(),-1*property_discount);
//		return resData;
//	}
//
//	@Override
//	public Date month2DiscountMonth(BigInteger userId, Date date) {
//		Date resData = date;
//		if(date!=null){
////			if(isNextMonthGroupBuilding(userId)){
////				resData = DateUtil.getNextMonth(date,-1);
////			}
//			Integer property_discount = getGroupBuildingPropertyMonthDistance(userId);
//			resData = DateUtil.getNextMonth(date,-1*property_discount);
//		}
//		return resData;
//	}

	@Override
	public void autoValidateRoomForAddRoom(BigInteger userId, BigInteger roomId) {
		//查询用户相关账号，是否有手机号phoneNo
		String bindPhone = commonRoomDao.selectUserBindPhone(userId);
		if(!StringUtils.isEmpty(bindPhone)){
			//查询用户门牌数量，未验证且对应业主手机号为bindPhone
			Integer existCount = commonRoomDao.selectRoomMatchPhoneAndNotValidateCount(roomId,bindPhone);
			//若数量>0
			if(existCount!=null&&existCount>0){
				addValidateSuccessInfoForPhoneMatch(userId, roomId);
			}else{//没有匹配到，尝试自动添加验证
				checkNotExist2AutoValidate(userId, bindPhone);
			}
		}
	}
	
	@Override
	public void autoValidateRoomForBindPhone(BigInteger userId, String bindPhone) {
		//查询未验证的，用户的门牌列表，并且业主手机号为bindPhone
		@SuppressWarnings("unchecked")
		RedisTemplate<String, Integer> redisTemplate = (RedisTemplate<String, Integer>) CnfantasiaCommbusiUtil.getBeanManager("redisTemplate");
		if(redisTemplate.opsForValue().getAndSet(userId + bindPhone, 1) != null) {
			return;
		}
		redisTemplate.expire(userId + bindPhone, 13, TimeUnit.SECONDS);
		
		List<Room> matchedRoomList = commonRoomDao.selectRoomListMatchPhoneAndNotValidate(userId,bindPhone);
		if(matchedRoomList!=null&&matchedRoomList.size()>0){
			for(Room tmpRoom:matchedRoomList){
				BigInteger roomId = tmpRoom.getId();
				addValidateSuccessInfoForPhoneMatch(userId, roomId);
			}
		}else{//没有匹配到，尝试自动添加验证
			checkNotExist2AutoValidate(userId, bindPhone);
		}
	}
	
	private void checkNotExist2AutoValidate(BigInteger userId, String bindPhone){
		//前面未验证的都已经标示为已验证的了，接下来的就是查询是否存在已验证的且手机号为bindPhone
		//如果不存在，则自动创建一个验证通过的
		if(!StringUtils.isEmpty(bindPhone)){
//			Integer existCount = commonRoomDao.selectRoomMatchPhoneAndValidateCountByUserId(userId,bindPhone);
//			if(existCount==null||existCount<=0){//没有则新增
				//查询 bindPhone 对应的realRoomId
				List<BigInteger> realRoomIdList = commonRoomDao.selectRealRoomIdByPhone(bindPhone);
				if(realRoomIdList!=null&&realRoomIdList.size()>0){
					Room defaultRoom = commonRoomDao.selectDefaultRoomByUserId(userId);
					boolean isDefaultRoomEmpty = RoomStatusCheckUtil.checkIsRoomEmpty(defaultRoom);
					//isDefault 用户默认门牌为空或者空门牌 isDefault = true
					boolean isDefault = isDefaultRoomEmpty?true:false;//默认门牌为空则设置为默认门牌
					for(int i=0;i<realRoomIdList.size();i++){
						UserHasTRoom userHasTRoom = null;
						BigInteger realRoomId = realRoomIdList.get(i);
						try {
							if(i==0){
								userHasTRoom =addRoomIgnoreAutoPhoneValidate(userId, realRoomId, isDefault, null, null);//第一个尝试设置为默认
							}else{
								userHasTRoom =addRoomIgnoreAutoPhoneValidate(userId, realRoomId, false, null, null);//其它不设置
							}
							addValidateSuccessInfoForPhoneMatch(userId, userHasTRoom.gettRoomFId());
						} catch (Exception e) {
							logger.error("addRoomIgnoreAutoPhoneValidate room has added!");
						}
					}//for(int i=0;i<realRoomIdList.size();i++){
				}//if(realRoomIdList!=null&&realRoomIdList.size()>0){
//			}//if(existCount==null||existCount<=0){//没有则新增
		}//if(!StringUtils.isEmpty(bindPhone)){
		
	}
	
	private void addValidateSuccessInfoForPhoneMatch(BigInteger userId, BigInteger roomId){
		String roomValidateDesc = "成功匹配业主手机号，门牌自动验证通过。";
		addValidateSuccessInfo(userId, roomId, RoomDict.RoomValidte_SourceType.RoomOwnerPhone_Equal_Auto_Add, roomValidateDesc);
	}
	@Override
	public RealRoom selectRealRoomByUserId(BigInteger userId) {
		return commonRoomDao.selectRealRoomByUserId(userId);
	}

	/**
	 * 根据门牌获取各个上级的ID gbId, blockId, cityId, provinceId
	 * @param roomId
	 * @return
	 */
	@Override
	public Map<String, Object> getRoomAddressIdByRoom(BigInteger roomId) {
		return commonRoomDao.getRoomAddressIdByRoom(roomId);
	}

	@Override
	public String getRoomDetailAddress(BigInteger roomId) {
		return commonRoomDao.getRoomDetailAddress(roomId);
	}

}
