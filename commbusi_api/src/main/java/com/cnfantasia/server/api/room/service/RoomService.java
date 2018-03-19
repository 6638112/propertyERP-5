package com.cnfantasia.server.api.room.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPrizeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.communitySupply.callable.FetchBaiduSupplyDataRunnable;
import com.cnfantasia.server.api.communitySupply.callable.FetchMljiaSupplyDataRunnable;
import com.cnfantasia.server.api.communitySupply.service.IAutoFetchSupllyDataService;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.propertycard.entity.RealRoomConfig;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.dao.IRoomDao;
import com.cnfantasia.server.api.room.entity.AddressCityHotEntity;
import com.cnfantasia.server.api.room.entity.BuildingAndUnitEntity;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.room.entity.RoomWithValidate;
import com.cnfantasia.server.api.room.util.RoomStatusCheckUtil;
import com.cnfantasia.server.api.roomValidate.dao.IRoomValidateDao;
import com.cnfantasia.server.api.roomVerifyPayment.dao.IRoomVerifyPaymentDao;
import com.cnfantasia.server.api.user.entity.UserHasTRoomEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.building.dao.IBuildingBaseDao;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.realRoom.dao.IRealRoomBaseDao;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.room.dao.IRoomBaseDao;
import com.cnfantasia.server.domainbase.room.entity.Room;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;
import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;
import com.cnfantasia.server.domainbase.userHasTRoom.dao.IUserHasTRoomBaseDao;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;
import com.cnfantasia.server.domainbase.userSwitchGb.entity.UserSwitchGb;
import com.cnfantasia.server.domainbase.userSwitchGb.service.IUserSwitchGbBaseService;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//import com.cnfantasia.server.api.room.dao.IRoomDao;
/**
 * 描述:(门牌信息) 具体业务Service层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RoomService implements IRoomService{
	private Log logger = LogFactory.getLog(getClass());
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	private IRoomDao roomDao;
	public void setRoomDao(IRoomDao roomDao) {
		this.roomDao = roomDao;
	}
	private IUserHasTRoomBaseDao userHasTRoomBaseDao;
	public void setUserHasTRoomBaseDao(IUserHasTRoomBaseDao userHasTRoomBaseDao) {
		this.userHasTRoomBaseDao = userHasTRoomBaseDao;
	}

	private IUserSwitchGbBaseService userSwitchGbBaseService;
	public void setUserSwitchGbBaseService(IUserSwitchGbBaseService userSwitchGbBaseService) {
		this.userSwitchGbBaseService = userSwitchGbBaseService;
	}
	//	private IUserBaseDao userBaseDao;
//	public void setUserBaseDao(IUserBaseDao userBaseDao) {
//		this.userBaseDao = userBaseDao;
//	}
	
	private IRealRoomBaseDao realRoomBaseDao;
	public void setRealRoomBaseDao(IRealRoomBaseDao realRoomBaseDao) {
		this.realRoomBaseDao = realRoomBaseDao;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IBuildingBaseDao buildingBaseDao;
	public void setBuildingBaseDao(IBuildingBaseDao buildingBaseDao) {
		this.buildingBaseDao = buildingBaseDao;
	}
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private IRoomValidateDao roomValidateDao;
	public void setRoomValidateDao(IRoomValidateDao roomValidateDao) {
		this.roomValidateDao = roomValidateDao;
	}
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private IAutoFetchSupllyDataService autoFetchSupllyDataService;
	public void setAutoFetchSupllyDataService(IAutoFetchSupllyDataService autoFetchSupllyDataService) {
		this.autoFetchSupllyDataService = autoFetchSupllyDataService;
	}
	
	private IRoomBaseDao roomBaseDao;
	public void setRoomBaseDao(IRoomBaseDao roomBaseDao) {
		this.roomBaseDao = roomBaseDao;
	}
	
	private IMsgpushService msgpushService;
	public void setMsgpushService(IMsgpushService msgpushService) {
		this.msgpushService = msgpushService;
	}
	
//	private IInviteRewardService inviteRewardService;
//	public void setInviteRewardService(IInviteRewardService inviteRewardService) {
//		this.inviteRewardService = inviteRewardService;
//	}
	
	private ICommonPrizeService commonPrizeService;
	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
		this.commonPrizeService = commonPrizeService;
	}
	
	private IRoomVerifyPaymentDao roomVerifyPaymentDao;
	public void setRoomVerifyPaymentDao(IRoomVerifyPaymentDao roomVerifyPaymentDao) {
		this.roomVerifyPaymentDao = roomVerifyPaymentDao;
	}

	/**
	 * 查询当对应用户的门牌列表信息
	 */
	@Override
	public List<UserHasTRoomEntity> getRoomList(BigInteger userId, PageModel pageModel) {
		return roomDao.selectRoomList(userId, pageModel);
	}

//	@Override
//	public List<UserHasTRoomEntity> getUserList(BigInteger roomId, PageModel pageModel) {
//		return roomDao.getUserList(roomId, pageModel);
//	}
	
	
//	@Override
//	public int getCountByRealRoomUserId(BigInteger realRoomId, BigInteger userId) {
//		Room room = new Room();
//		room.setCreater(userId);
//		room.settRealRoomFId(realRoomId);
//		return roomDao.selectRoomCount(MapConverter.toMap(room), false);
//	}

//	@Override
//	public UserHasTRoom addRomm(BigInteger userId,BigInteger realRoomId,BigInteger tBuildingFId,String roomName,Boolean isDefault,String realName) {
//		//是否需要新增真实房间
//		if(StringUtils.isEmpty(realRoomId)){
//			realRoomId = uuidManager.getNextUuidBigInteger(SEQConstants.t_real_room);
//			RealRoom realRoom = new RealRoom();
//			realRoom.setId(realRoomId);
//			realRoom.setNum(roomName);
//			realRoom.settBuildingFId(tBuildingFId);
//			realRoomBaseDao.insertRealRoom(MapConverter.toMap(realRoom));
//		}
//		//校验是否已经创建该门牌
//		int existCount =getCountByRealRoomUserId(realRoomId, userId);
//		if(existCount>0){
//			throw new BusinessRuntimeException("room.addRoom.checkExist.failed");
//		}
//		return CommonRoomServiceUtil.addRomm(roomDao, userHasTRoomBaseDao, userBaseDao, userId, realRoomId, isDefault, realName);
//	}

//	@Override
//	public boolean removeUserFromRoom(BigInteger roomId, BigInteger userId) {
//		UserHasTRoom userHasTRoom = new UserHasTRoom();
//		userHasTRoom.settUserFId(roomId);
//		userHasTRoom.settRoomFId(userId);
//		List<UserHasTRoom> resList = userHasTRoomBaseDao.selectUserHasTRoomByCondition(MapConverter.toMap(userHasTRoom),false);
//		if(resList.size()!=1){
//			throw new BusinessRuntimeException("room.removeUserFromRoom.userId_roomId.error");
//		}
//		int resCount = userHasTRoomBaseDao.deleteUserHasTRoomLogic(resList.get(0).getId());
//		if(resCount<=0){
//			throw new BusinessRuntimeException("room.removeUserFromRoom.deleteUserHasTRoomLogic.failed");
//		}
//		return false;
//	}

	@Override
	public RoomEntityWithValidate getDefaultRoomInfo(BigInteger userId) {
		RoomEntityWithValidate roomDefault =  commonRoomService.getDefaultRoomInfo(userId);
		if(null==roomDefault){
			//默认设置空门牌
			commonRoomService.addNullRoom(userId);
			roomDefault =  commonRoomService.getDefaultRoomInfo(userId);
		}
		return roomDefault;
	}

//	@Override
//	public void joinRoom(BigInteger userId,BigInteger roomId,Boolean isDefault) {
//		//校验是否已经添加过该门牌
//		UserHasTRoom userHasTRoomQry = new UserHasTRoom();
//		userHasTRoomQry.settUserFId(userId);
//		userHasTRoomQry.settRoomFId(roomId);
//		int count = userHasTRoomBaseDao.selectUserHasTRoomCount(MapConverter.toMap(userHasTRoomQry), false);
//		if(count>0){
//			throw new ValidateRuntimeException("roomService.joinRoom.haveJoin.failed");
//		}
//		//添加门牌
//		BigInteger userHasTRoomAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_room);
//		UserHasTRoom userHasTRoomAdd = new UserHasTRoom();
//		userHasTRoomAdd.settUserFId(userId);
//		userHasTRoomAdd.settRoomFId(roomId);
//		userHasTRoomAdd.setId(userHasTRoomAddId);
//		userHasTRoomAdd.setApplyStatus(aaa);
//		int addRes = userHasTRoomBaseDao.insertUserHasTRoom(userHasTRoomAdd);
//		if(addRes<=0){
//			throw new BusinessRuntimeException("roomService.joinRoom.insertUserHasTRoom.failed");
//		}
//		//是否设置为默认门牌
//		if(isDefault){
//			User userUpd = new User();
//			userUpd.setId(userId);
//			userUpd.setDefaultRoomId(roomId);
//			int userUpdRes = userBaseDao.updateUser(userUpd);
//			if(userUpdRes<=0){
//				throw new BusinessRuntimeException("roomService.joinRoom.setDefaultRoom.failed");
//			}
//		}
//	}
	
	@Override
	public RoomEntityWithValidate addBuildingAndRoom(BigInteger groupBuildingId, String buildingName,String unitName, String roomNum,BigInteger userId, boolean changeDefaultRoom) {
		BigInteger buildingAddId = addBuilding(userId, groupBuildingId, buildingName);
		BigInteger realRoomAddId = addRealRoom(userId, buildingAddId, unitName, roomNum);
		//创建虚拟门牌
		UserHasTRoom userHasTRoom = commonRoomService.addRoom(userId, realRoomAddId, changeDefaultRoom, null);
		//增加已存在小区的商家数据抓取任务
		doGroupBuildingDateFetch(groupBuildingId);
		//查询当前虚拟门牌信息
		RoomEntityWithValidate roomEntity=commonRoomService.getRoomEntityById(userHasTRoom.gettRoomFId());
		
		return roomEntity;
	}

	@Override
	public RoomEntityWithValidate addGroupBuildingAndRoom(BigInteger addressBlockId, String groupBuildingName, String buildingName, String unitName,
			String roomNum, BigInteger userId, boolean changeDefaltRoom) {
		BigInteger groupBuildingAddId = addGroupBuilding(userId, addressBlockId, groupBuildingName);
		RoomEntityWithValidate roomEntity = addBuildingAndRoom(groupBuildingAddId, buildingName,unitName, roomNum, userId, changeDefaltRoom);
		addSwitchGb(groupBuildingAddId, userId);
		return roomEntity;
	}

	@Override
	public RoomEntityWithValidate addVirtualRoomOnly(BigInteger gbId, BigInteger realRoomId, BigInteger userId, boolean changeDefaultRoom) {
		//判断当前默认门牌是否为空门牌
		getDefaultRoomInfo(userId);//历史保留 为空则创建空门牌
		UserHasTRoom userHasTRoom = commonRoomService.addRoom(userId, realRoomId, changeDefaultRoom, null);
		//查询当前虚拟门牌信息
		RoomEntityWithValidate roomEntity=commonRoomService.getRoomEntityById(userHasTRoom.gettRoomFId());
		//syl-del 2015-4-23 09:47:08
//		if(oldDefaultRoomEntity!=null&&oldDefaultRoomEntity.checkIsEmptyRoom()){
//			//将old门牌下的抽奖折扣转移到新门牌下
//			transferPrizeRecordFromOld2New(userId, oldDefaultRoomEntity.getId(), roomEntity.getId());
//			//syl-add 将old门牌下的意外惊喜转移到信息门牌下
//			commDataUpgradeService.convertSurpriseGiftDataByRoomId(userId, oldDefaultRoomEntity.getId(), roomEntity.getId());
//		}
		addSwitchGb(gbId, userId);
		//返回
		return roomEntity;
	}

	private void addSwitchGb(BigInteger gbId, BigInteger userId) {
		UserSwitchGb switchGb = new UserSwitchGb();
		switchGb.setGbId(gbId);
		switchGb.setUserId(userId);
		switchGb.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_switch_gb));
		switchGb.setSys0AddTime(DateUtil.getCurrSysTimeStr());
		userSwitchGbBaseService.insertUserSwitchGb(switchGb);
	}


//	@Override
//	public List<PrizeRecord> getAllPrizeRecord(BigInteger userId, BigInteger roomId) {
//		return roomDao.selectAllPrizeRecord(userId, roomId);
//	}

	@Override
	public RoomValidate getRoomValidate(BigInteger userId, BigInteger roomId) {
		return roomDao.selectRoomValidate(userId, roomId);
	}

	@Override
	public RoomValidate getRoomValidateWithDefault(BigInteger userId) {
		return roomDao.selectRoomValidateWithDefault(userId);
	}

	@Override
	public RoomValidate confirmValidate(BigInteger userId, BigInteger roomId, RequestFileEntity requestFileEntity) {
		if(roomId==null){//门牌Id为空则使用默认门牌
			roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		}
		RoomValidate currRoomValidate = getRoomValidate(userId, roomId);
		//如果已经验证成功
		if(currRoomValidate!=null&&currRoomValidate.getVerifyStatus().compareTo(RoomDict.RoomValidte_VerifyStatus.SUCCESS)==0){
			throw new BusinessRuntimeException("RoomService.confirmValidate.hasValided");
		}
		if(currRoomValidate!=null){//未验证，验证中，验证失败的 删除当前记录
			int res = roomValidateDao.deleteRoomValidateLogic(currRoomValidate.getId());
			if(res<=0){
				throw new BusinessRuntimeException("RoomService.confirmValidate.removeOldValidate.failed");
			}
		}
		{//重新创建验证记录信息
			BigInteger roomValidateAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_room_validate);
			//获取要存的文件名和路径,记录Id__当前时间.上传的文件后缀名
			String valiPicBasePath = sysParamManager.getSysParaValue(SysParamKey.ROOM_VALIDATE_PIC_BASEPATH);
			String verifyPicUrl = new StringBuffer()
				.append(roomValidateAddId).append("_").append(DateUtil.getCurrSysTimeStr())
				.append(".").append(requestFileEntity.getFileType()).toString();
			//保存验证信息
			RoomValidate roomValidateAdd = new RoomValidate();
			roomValidateAdd.setId(roomValidateAddId);
			roomValidateAdd.settRoomFId(roomId);
			roomValidateAdd.setVerifyPicUrl(verifyPicUrl.toString());
			roomValidateAdd.setVerifyResultDesc(null);
			roomValidateAdd.setVerifyStatus(RoomDict.RoomValidte_VerifyStatus.DOING);//校验中
			roomValidateAdd.setUserId(userId);
			roomValidateAdd.setSourceType(RoomDict.RoomValidte_SourceType.Submit_File);
			roomValidateAdd.setRecordNum(roomValidateDao.selectRecordNum(userId)+1);
			int addRes = roomValidateDao.insertRoomValidate(roomValidateAdd);
			if(addRes<=0){
				throw new BusinessRuntimeException("RoomService.confirmValidate.insertRoomValidate.failed");
			}
			//上传图片
			try {
				fileServerService.uploadFile(new StringBuffer().append(valiPicBasePath).append(verifyPicUrl).toString(), requestFileEntity.getFileContent());
			} catch (IOException e) {
				throw new BusinessRuntimeException("RoomService.confirmValidate.uploadFile.failed",e);
			}
		}
		return getRoomValidate(userId, roomId);
	}

//	@Override
//	public void applyJoinRoom(BigInteger userId, BigInteger roomId) {
//		//1.roomId是否已加入
//		//2.是否在审核中
//		//3.加入,状态为待通过
//		//4.
//		
//	}
	
	@Override
	public void applyJoinRoomFace2Face(BigInteger applyUserId,BigInteger roomId,BigInteger inviteUserId) {
		//只有校验通过的门牌 才可以邀请
		RoomValidate inviteUserRoomValidate = roomDao.selectRoomValidate(inviteUserId, roomId);
		if(inviteUserRoomValidate!=null&&inviteUserRoomValidate.getVerifyStatus()!=null
				&&inviteUserRoomValidate.getVerifyStatus().compareTo(RoomDict.RoomValidte_VerifyStatus.SUCCESS)==0){
		}else{
			throw new BusinessRuntimeException("RoomService.applyJoinRoomFace2Face.goalRoom.notValidate");
		}
		
		//是否已经加入 查询 用户与roomId 相同realRoomId 对应的 room信息及validate信息
		RoomWithValidate tmpRoomWithValidate = roomDao.selectRoomWithValidateByUserAndOtherRoomId(applyUserId, roomId);
		//验证状态
		if(tmpRoomWithValidate!=null){
			throw new BusinessRuntimeException("RoomService.applyJoinRoomFace2Face.haveExist");
		}else{
			Room tmpRoom = roomBaseDao.selectRoomBySeqId(roomId);
//			RealRoom tmpRealRoom = realRoomBaseDao.selectRealRoomBySeqId(tmpRoomWithValidate.gettRealRoomFId());
			UserHasTRoom tmpUserHasTRoom = commonRoomService.addRoom(applyUserId, tmpRoom.gettRealRoomFId(), true, null,inviteUserId);
			//设定校验通过
			commonRoomService.addValidateSuccessInfo(applyUserId, tmpUserHasTRoom.gettRoomFId(),RoomDict.RoomValidte_SourceType.Face2Face_Auto_Add,"面对面邀请");
		}
	}
	
//	@Override
//	public void applyJoinRoomFace2Face(BigInteger applyUserId,BigInteger roomId,BigInteger inviteUserId) {
//		//1.roomId是否已加入
//		UserHasTRoom userHasTRoom = null;
//		userHasTRoom = roomDao.selectUserHasTRoomByUserIdRoomId(applyUserId, roomId);
//		if(userHasTRoom!=null){//存在则更新
//			if(userHasTRoom.getApplyStatus()!=null&&userHasTRoom.getApplyStatus().compareTo(RoomDict.UserHasTRoom_ApplyStatus.SUCCESS)==0){
//				//已经审核通过
//			}else{
//				//2.是否在审核中
//				UserHasTRoom userHasTRoomUpd = new UserHasTRoom();
//				userHasTRoomUpd.setId(userHasTRoom.getId());
//				userHasTRoomUpd.setInviterId(inviteUserId);
//				userHasTRoomUpd.setApplyStatus(RoomDict.UserHasTRoom_ApplyStatus.SUCCESS);
//				Integer resCount = userHasTRoomBaseDao.updateUserHasTRoom(userHasTRoomUpd);
//				if(resCount==null||resCount<=0){
//					throw new BusinessRuntimeException("RoomService.applyJoinRoomFace2Face.updateUserHasTRoom.failed");
//				}
//			}
//		}else{//不存在则新增
//			//3.加入,状态为通过!!!
//			UserHasTRoom userHasTRoomAdd = new UserHasTRoom();
//			BigInteger userHasTRoomAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_room);
//			userHasTRoomAdd.setId(userHasTRoomAddId);
//			userHasTRoomAdd.setInviterId(inviteUserId);
//			userHasTRoomAdd.setApplyStatus(RoomDict.UserHasTRoom_ApplyStatus.SUCCESS);
//			userHasTRoomAdd.settRoomFId(roomId);
//			userHasTRoomAdd.settUserFId(applyUserId);
//			userHasTRoomAdd.setIsAdmin(RoomDict.UserHasTRoom_IsAdmin.FALSE);
//			Integer resCount = userHasTRoomBaseDao.insertUserHasTRoom(userHasTRoomAdd);
//			if(resCount==null||resCount<=0){
//				throw new BusinessRuntimeException("RoomService.applyJoinRoomFace2Face.insertUserHasTRoom.failed");
//			}
//		}
//		//4.完成，设定为默认门牌 
//		User user = new User();
//		user.setId(applyUserId);
//		user.setDefaultRoomId(roomId);
//		int userUpdRes = userBaseDao.updateUser(user);
//		if(userUpdRes<=0){
//			throw new BusinessRuntimeException("RoomService.applyJoinRoomFace2Face.setDefaultRoom.failed");
//		}
//	}
	
	@Override
	public List<AddressCityHotEntity> getAddressCityHotList() {
		return roomDao.selectAddressCityHotList();
	}
	
	@Override
	public boolean decideJoinRoom(BigInteger userId, BigInteger userHasTRoomId, Boolean isAgree) {
		RoomEntityWithValidate defaultRoomEntity = commonRoomService.getDefaultRoomInfo(userId);
		RealRoomEntity currRealRoom = defaultRoomEntity.getRealRoomEntity();
		{//数据不一致 处理
			if(defaultRoomEntity.checkIsValidedSuccess()){
				if(!currRealRoom.checkIsValidated()){
					commonRoomService.checkRealRoomFirstValidateAndUpd(currRealRoom.getId(), true);
					return false;
				}
			}
		}
		{//验证用户身份
			if(currRealRoom.checkIsValidated()){//用户对应的真实门牌已经验证通过
				if(currRealRoom.getValidateUserId().compareTo(userId)!=0){//是否为管理员
					throw new BusinessRuntimeException("RoomService.decideJoinRoom.notAdmin.failed");
				}
			}else{
				//syl-upd
//				if(defaultRoomEntity.getCreater().compareTo(userId)!=0){
//					throw new BusinessRuntimeException("RoomService.decideJoinRoom.notCreater.failed");
//				}
				throw new BusinessRuntimeException("RoomService.decideJoinRoom.notValidated.failed");
			}
		}
		
		//校验userHasTRoomId 与 userId的关系
		{
			Integer resCount = roomDao.selectExistCountByUserHasTRoomId(userHasTRoomId, userId);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RoomService.decideJoinRoom.notCompare.failed");
			}
		}
		
//		//更改状态为指定的isAgree
//		{
//			Integer applyStatus = isAgree?RoomDict.UserHasTRoom_ApplyStatus.SUCCESS:RoomDict.UserHasTRoom_ApplyStatus.REJECT;
//			UserHasTRoom userHasTRoomUpd = new UserHasTRoom();
//			userHasTRoomUpd.setId(userHasTRoomId);
//			userHasTRoomUpd.setApplyStatus(applyStatus);
//			Integer resCount = userHasTRoomBaseDao.updateUserHasTRoom(userHasTRoomUpd);
//			if(resCount==null||resCount<=0){
//				throw new BusinessRuntimeException("RoomService.decideJoinRoom.failed");
//			}
//		}
		
		UserHasTRoom tmpUserHasTRoom = userHasTRoomBaseDao.selectUserHasTRoomBySeqId(userHasTRoomId);
		{//为对应的虚拟门牌增加验证通过的信息
			RoomValidate tmpRoomValidate = roomDao.selectRoomValidate(tmpUserHasTRoom.gettUserFId(), tmpUserHasTRoom.gettRoomFId());
			if(tmpRoomValidate==null&&isAgree){
				commonRoomService.addValidateSuccessInfo(tmpUserHasTRoom.gettUserFId(), tmpUserHasTRoom.gettRoomFId(),RoomDict.RoomValidte_SourceType.Admin_Decide_ByClient_,"房间管理员同意加入");
			}else{
				Integer verifyStatus = isAgree?RoomDict.RoomValidte_VerifyStatus.SUCCESS:RoomDict.RoomValidte_VerifyStatus.FAILED;
				RoomValidate roomValidateUpd = new RoomValidate();
				roomValidateUpd.setId(tmpRoomValidate.getId());
				roomValidateUpd.setVerifyStatus(verifyStatus);
				String verifyResultDesc = null;
				if(isAgree){
					verifyResultDesc = "房间管理员同意加入";
				}else{
					verifyResultDesc = "房间管理员把你无情拒绝于门外";
				}
				roomValidateUpd.setVerifyResultDesc(verifyResultDesc);
				Integer resCount = roomValidateDao.updateRoomValidate(roomValidateUpd);
				if(resCount==null||resCount<=0){
					throw new BusinessRuntimeException("RoomService.decideJoinRoom.failed");
				}
			}
		}
		if(isAgree){// 通过审核，推送内容：恭喜您顺利进入家门~点击后跳转到"家里人列表"页面
			addMsgPushInfo(tmpUserHasTRoom.gettUserFId(), "roomValidate.decideJoin.agree", null, NoticeDict.Message_Type.Family_Member_List, RoomConstants.Msgpush_Title_Room_Apply_Decide);
			/*//神码行动新增邀请人和注册新人的验证门牌粮票奖励 huangzj 2015-05-12
			this.inviteRewardService.excuteInviteRewardForVerifyRoom(tmpUserHasTRoom.gettUserFId(), tmpUserHasTRoom.gettRoomFId());
			//街坊消息推送-huangzj2015-06-26
			IMicroblogQueueService microblogQueueService = (IMicroblogQueueService)CnfantasiaCommUtil.getBeanManager("microblogQueueService");
			String text = "解放号"+userId+"用户已通过门牌验证！已认证的小伙伴快来报名体验“全年物业费打折”福利，同小区5户成团报名即可，详情加群：458449740";
			//GroupBuilding groupBuilding = this.commonRoomDao.selectGroupBuildingByUserId(userId);
			microblogQueueService.saveMicroblogQueue(text, 
					MicroblogQueueConstant.Source_Type.USER, MicroblogQueueConstant.Timing.NO, 
					currRealRoom.getBuildingEntity().gettGroupBuildingFId(), MicroblogQueueConstant.MQ_Level.Code_80);*/
			commonPrizeService.freshMiniDiscountByUserId(userId,null,null);//syl-add 2015-5-22 13:09:23 刷新门牌最低折扣信息
		}else{//未通过审核，推送内容：管理员无情的拒绝您进入家门~点击后跳转到门牌审核详情
			addMsgPushInfo(tmpUserHasTRoom.gettUserFId(), "roomValidate.decideJoin.disAgree", null, NoticeDict.Message_Type.Room_Validate_Detail, RoomConstants.Msgpush_Title_Room_Apply_Decide);
		}
		return true;
	}

	@Override
	public void applyRoomValidate(BigInteger userId) {
		//用户是否有门牌
		RoomEntityWithValidate defaultRoomEntity = commonRoomService.getDefaultRoomInfo(userId);
		RealRoom currRealRoom = defaultRoomEntity.getRealRoomEntity();
		//对应真实门牌是否已经被验证
//		if(currRealRoom.getValidateStatus()!=null&&currRealRoom.getValidateStatus().compareTo(RoomDict.RealRoom_ValidateStatus.IS_VALIDATED)==0){
		if(RoomStatusCheckUtil.checkIsRealRoomValidated(currRealRoom)){
			
		}else{
			throw new BusinessRuntimeException("roomValidate.preConfirmValidate.notExistValidate");//roomValidate.preConfirmValidate.existValidate
		}
		//用户是否已经提交了申请,审核状态
		RoomValidate currRoomValidate = defaultRoomEntity.getRoomValidate();
		if(currRoomValidate!=null){//存在则更新
			if(currRoomValidate.getVerifyStatus()!=null){
				if(currRoomValidate.getVerifyStatus().compareTo(RoomDict.RoomValidte_VerifyStatus.SUCCESS)==0){
					throw new BusinessRuntimeException("RoomService.applyRoomValidate.verifyStatus.SUCCESS");
				}else if(currRoomValidate.getVerifyStatus().compareTo(RoomDict.RoomValidte_VerifyStatus.FAILED)==0){//验证失败
					throw new BusinessRuntimeException("RoomService.applyRoomValidate.verifyStatus.FAILED");
				}else if(currRoomValidate.getVerifyStatus().compareTo(RoomDict.RoomValidte_VerifyStatus.DOING)==0){//验证中
					throw new BusinessRuntimeException("RoomService.applyRoomValidate.verifyStatus.DOING");
				}else{
					RoomValidate roomValidateUpd = new RoomValidate();
					roomValidateUpd.setId(currRoomValidate.getId());
					roomValidateUpd.setVerifyStatus(RoomDict.RoomValidte_VerifyStatus.DOING);//改为验证中
					roomValidateUpd.setSourceType(RoomDict.RoomValidte_SourceType.Simple_Join);
					Integer resCount =  roomValidateDao.updateRoomValidate(roomValidateUpd);
					if(resCount==null||resCount<=0){
						throw new BusinessRuntimeException("RoomService.applyRoomValidate.updateRoomValidate.failed");
					}
				}
			}
		}else{//新增
			//若未提交，则增加申请记录
			RoomValidate roomValidateAdd = new RoomValidate();
			BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_room_validate);
			roomValidateAdd.setId(addId);
			roomValidateAdd.settRoomFId(defaultRoomEntity.getId());
			roomValidateAdd.setVerifyPicUrl(null);
//			roomValidateAdd.setVerifyResultDesc("user apply validate when realroom is validated.");
			roomValidateAdd.setVerifyStatus(RoomDict.RoomValidte_VerifyStatus.DOING);
			roomValidateAdd.setSourceType(RoomDict.RoomValidte_SourceType.Simple_Join);
			roomValidateAdd.setUserId(userId);
			roomValidateAdd.setVerifySuccTime(dualDao.getNowTime());
			roomValidateAdd.setRecordNum(roomValidateDao.selectRecordNum(userId)+1);
			Integer resCount =  roomValidateDao.insertRoomValidate(roomValidateAdd);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RoomService.applyRoomValidate.insertRoomValidate.failed");
			}
		}
		
		if(currRealRoom.getValidateUserId()!=null){
			//消息推送给管理员
			//推送内容：有人要进家门，看看是谁~点击后跳转到“家里人列表”页面
			addMsgPushInfo(currRealRoom.getValidateUserId(), "roomValidate.applyJoin.simple", null, NoticeDict.Message_Type.Family_Member_List, RoomConstants.Msgpush_Title_Room_Apply_Join);
		}
	}
	
	private BigInteger addMsgPushInfo(BigInteger userId, String msgInfoKey, Object[] msgParams,Long msgType,String title) {
		Message messageAdd = packingMessage(userId, msgInfoKey, msgParams,msgType,title);
		//设定消息参数
		String expiryTime = null;//失效时间为空
		List<MessageParameter> paramList = null;
		BigInteger defaultRoomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		CommUserDataEntity commUserDataEntity = new CommUserDataEntity(userId, LoginDict.UserRegistOrTmp.REGIST_USER, defaultRoomId);
		msgpushService.addMessage2Pool(commUserDataEntity, messageAdd, expiryTime,paramList);//立即推送
		return messageAdd.getId();
	}
	
	private Message packingMessage(BigInteger userId, String msgInfoKey, Object[] msgParams,Long msgType,String title){
		//新增一条消息
		Message messageAdd = null;
		{//组装消息信息
			messageAdd = new Message();
			String content = MessageSourceUtil.getMessage(msgInfoKey, msgParams);
			String nowTime = dualDao.getNowTime();
			BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
			messageAdd.setAndroidTargetView(null);
			messageAdd.setContent(content);
			messageAdd.setCreater(userId);
			messageAdd.setId(addId);
			messageAdd.setIosTargetView(null);
			messageAdd.setMsggroupFid(null);
			messageAdd.setPicUrl(null);
			messageAdd.setTime(nowTime);
			messageAdd.setTitle(title);// RoomConstants.Msgpush_Title_Room_Apply_Join 申请加入门牌
			messageAdd.setType(msgType);
		}
		return messageAdd;
	}

	@Override
	public RoomEntityWithValidate changeRealRoom(BigInteger userId,BigInteger roomId, BigInteger realRoomId, BigInteger groupBuildingId,
			BigInteger addressBlockId, String groupBuildingName, String buildingName, String unitName, String roomNum) {
		//门牌修改条件,该门牌未被验证
		RoomValidate roomValidate = roomDao.selectRoomValidate(userId, roomId);
		if(roomValidate!=null&&roomValidate.getVerifyStatus().compareTo(RoomDict.RoomValidte_VerifyStatus.SUCCESS)==0){
			throw new BusinessRuntimeException("roomService.changeRealRoom.roomIsValidate");
		}
		if(realRoomId==null){
			if(groupBuildingId==null){
				//新增小区
				groupBuildingId = addGroupBuilding(userId, addressBlockId, groupBuildingName);
			}
			//新增楼栋
			BigInteger buildingAddId = addBuilding(userId, groupBuildingId, buildingName);
			//新增realRoom
			realRoomId = addRealRoom(userId, buildingAddId,unitName,roomNum);
		}
		//执行修改门牌的处理
		commonRoomService.addRoomPreValidate(userId, realRoomId);//验证
		commonRoomService.change2NewRealRoomId(userId, roomId, realRoomId);//修改为新门牌
		RoomEntityWithValidate defaultRoomEntity = commonRoomService.getDefaultRoomInfo(userId);
		return defaultRoomEntity;
	}
	
	/**
	 * 新增小区
	 * @param userId
	 * @param addressBlockId
	 * @param groupBuildingName
	 * @return
	 */
	private BigInteger addGroupBuilding(BigInteger userId,BigInteger addressBlockId,String groupBuildingName){
		BigInteger groupBuildingAddId = null;
		//校验小区名称是否存在，若存在则使用已存在的小区
		//不同用户输入相同小区名称，则创建不同小区
		//同一用户输入相同小区名称，若都为未审核，则共用之前创建的小区
		{
			//syl-del-2015-3-19 11:42:42
//			GroupBuilding groupBuildingQry = new GroupBuilding();
//			groupBuildingQry.settBlockFId(addressBlockId);
//			groupBuildingQry.setName(groupBuildingName);
//			groupBuildingQry.setCreater(userId);//当前用户创建
//	//		groupBuildingQry.setCheckStatus(RoomDict.CheckStatus.DaiShenHe);//状态为未审核的
//			List<GroupBuilding> existGBList = groupBuildingBaseDao.selectGroupBuildingByCondition(MapConverter.toMap(groupBuildingQry), false);
//			if(existGBList!=null&&existGBList.size()>0){
//				groupBuildingAddId = existGBList.get(0).getId();
//			}
			//syl-add-2015-3-19 11:42:48
			GroupBuilding existGroupBuilding = roomDao.selectExistGroupBuildingByName(addressBlockId, groupBuildingName, userId);
			if(existGroupBuilding!=null){
				groupBuildingAddId = existGroupBuilding.getId();
			}
			else{
				//添加小区
				groupBuildingAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building);
				GroupBuilding groupBuildingAdd = new GroupBuilding();
				groupBuildingAdd.setId(groupBuildingAddId);
				groupBuildingAdd.setName(groupBuildingName);
				groupBuildingAdd.setCheckStatus(RoomDict.CheckStatus.DaiShenHe);
				groupBuildingAdd.setCreater(userId);
				String pinyinName = null;
				try {
					pinyinName = PinyinUtil.hanyuToPinyinSimple(groupBuildingName);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					logger.info("The pinyin name for "+groupBuildingName+" is failed to transfer,exception is :"+e.getMessage(),e);
				}
				groupBuildingAdd.setPinyinName(pinyinName);
				groupBuildingAdd.setSignStatus(RoomDict.GroupBuilding_SignStatus.NOT_SIGN);
				groupBuildingAdd.settBlockFId(addressBlockId);
				groupBuildingAdd.settPropertyManagementFId(null);//暂时没有物业管理处
				int groupBuildingAddRes = groupBuildingBaseDao.insertGroupBuilding(groupBuildingAdd);
				if(groupBuildingAddRes<=0){throw new BusinessRuntimeException("roomService.addGroupBuildingAndRoom.groupBuildingAddRes.faild");}
			}
		}
		return groupBuildingAddId;
	}
	
	/**
	 * 新增楼栋
	 * @param userId
	 * @param groupBuildingId
	 * @param buildingName
	 * @return
	 */
	private BigInteger addBuilding(BigInteger userId,BigInteger groupBuildingId,String buildingName){
		BigInteger buildingAddId = null;
		{//校验用户是否已创建该楼栋名称
			Building buildingQry = new Building();
			buildingQry.settGroupBuildingFId(groupBuildingId);
//			buildingQry.setCreater(userId);//syl-del-2015-5-20 10:57:42
			buildingQry.setName(buildingName);
	//		buildingQry.setCheckStatus(RoomDict.CheckStatus.DaiShenHe);//状态为未审核的
			List<Building> existBuildingList = buildingBaseDao.selectBuildingByCondition(MapConverter.toMap(buildingQry), false);
			if(existBuildingList!=null&&existBuildingList.size()>0){
				buildingAddId = existBuildingList.get(0).getId();
			}else{
			//添加建筑
				buildingAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_building);
				Building buildingAdd = new Building();
				buildingAdd.setId(buildingAddId);
				buildingAdd.setName(buildingName);
				buildingAdd.setNameCharOrder(com.cnfantasia.server.common.utils.StringUtils.getSubCharString(buildingName));
				buildingAdd.setNameDigitOrder(com.cnfantasia.server.common.utils.StringUtils.getSubDigitString(buildingName));
				buildingAdd.settGroupBuildingFId(groupBuildingId);
				buildingAdd.settGroupBuildingFIdTmp(null);
				buildingAdd.setCheckStatus(RoomDict.CheckStatus.DaiShenHe);
				buildingAdd.setCreater(userId);
				int insertBuildingRes = buildingBaseDao.insertBuilding(buildingAdd);
				if(insertBuildingRes<=0){throw new BusinessRuntimeException("roomService.addBuildingAndRoom.insertBuilding.faild");}
			}
		}
		return buildingAddId;
	}
	
	/**
	 * 新增realRoom
	 * @param userId
	 * @param buildingAddId
	 * @param roomNum
	 * @return
	 */
	private BigInteger addRealRoom(BigInteger userId,BigInteger buildingAddId,String unitName, String roomNum){
		BigInteger realRoomAddId = null;
		{//校验用户是否已创建该真实门牌
			RealRoom realRoomQry = new RealRoom();
			realRoomQry.settBuildingFId(buildingAddId);
//			realRoomQry.setCreater(userId);//syl-del-2015-5-20 10:58:00
			realRoomQry.setNum(roomNum);
//			realRoomQry.setCheckStatus(RoomDict.CheckStatus.DaiShenHe);//状态为未审核的
			List<RealRoom> existRealRoomList = realRoomBaseDao.selectRealRoomByCondition(MapConverter.toMap(realRoomQry), false);
			if(existRealRoomList!=null&&existRealRoomList.size()>0){
				realRoomAddId = existRealRoomList.get(0).getId();
			}else{
				//添加真实门牌
				realRoomAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_real_room);
				
				RealRoom realRoomAdd = new RealRoom();
				realRoomAdd.setId(realRoomAddId);
				if(StringUtils.isEmpty(unitName)){
					realRoomAdd.setNum(roomNum);
				}else{
					realRoomAdd.setUnitName(unitName);
					realRoomAdd.setNum(unitName + "-" + roomNum);
				}
				realRoomAdd.setNumTail(roomNum);
				realRoomAdd.setNumTailCharOrder(com.cnfantasia.server.common.utils.StringUtils.getSubCharString(roomNum));
				realRoomAdd.setNumTailDigitOrder(com.cnfantasia.server.common.utils.StringUtils.getSubDigitString(roomNum));
				
				realRoomAdd.settBuildingFId(buildingAddId);
				realRoomAdd.settBuildingFIdTmp(null);
				realRoomAdd.setCheckStatus(RoomDict.CheckStatus.DaiShenHe);
				realRoomAdd.setCreater(userId);
				realRoomAdd.setValidateStatus(RoomDict.RealRoom_ValidateStatus.NOT_VALIDATED);//默认未被用户验证
				int insertRealRoomRes = realRoomBaseDao.insertRealRoom(realRoomAdd);
				if(insertRealRoomRes<=0){
					throw new BusinessRuntimeException("roomService.addBuildingAndRoom.insertRealRoom.faild");
				}else{
					//门牌验证缴费情况查询表
					List<RoomVerifyPayment> roomVerifyPayments = roomVerifyPaymentDao.selectRoomVerifyPaymentsByBuildingId(buildingAddId);
					{
						List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_room_verify_payment, roomVerifyPayments.size());
						for(int i=0; i<roomVerifyPayments.size(); i++){
							RoomVerifyPayment roomVerifyPayment = roomVerifyPayments.get(i);
							roomVerifyPayment.setId(ids.get(i));
							roomVerifyPayment.settRealRoomId(realRoomAddId);
							roomVerifyPayment.setRoomNum(realRoomAdd.getNum());
							roomVerifyPayment.setUnit(realRoomAdd.getUnitName());
							roomVerifyPayment.setRegisterState(1);
						}
						roomVerifyPaymentDao.insertRoomVerifyPaymentBatch(roomVerifyPayments);
					}
				}
				
			}
		}
		return realRoomAddId;
	}
	
	/**
	 * 已存在小区的商家数据抓取任务
	 * @param groupBuildingId
	 */
	private void doGroupBuildingDateFetch(BigInteger groupBuildingId){
		{//百度抓取
			FutureTask<Boolean> task = new FutureTask<Boolean>(new FetchBaiduSupplyDataRunnable(autoFetchSupllyDataService, groupBuildingId));
			new Thread(task).start();
		}
		{//美丽家数据抓取
			FutureTask<Boolean> task02 = new FutureTask<Boolean>(new FetchMljiaSupplyDataRunnable(autoFetchSupllyDataService, groupBuildingId));
			new Thread(task02).start();
		}
	}

	@Override
	public List<AddressCity> getAddressCityListAll() {
		return roomDao.selectAddressCityListAll();
	}

	@Override
	public List<Building> getBuildingListByGbId(BigInteger groupBuildingId) {
		return roomDao.selectBuildingListByGbId(groupBuildingId);
	}

	@Override
	public List<RealRoom> getRealRoomListByBuildId(BigInteger buildingId) {
		return roomDao.selectRealRoomListByBuildId(buildingId);
	}

	@Override
	public List<BuildingAndUnitEntity> getBuildingListByGroupBuildingId(BigInteger gbId) {
		return roomDao.getBuildingListByGbId(gbId);
	}

	@Override
	public GroupBuilding getGroupBuildingbyRrid(BigInteger realRoomId) {
		return roomDao.getGroupBuildingbyRrid(realRoomId);
	}

	@Override
	public List<RealRoomConfig> getPropfeeCanPayRoomByUserId(BigInteger userId) {
		return roomDao.getPropfeeCanPayRoomByUserId(userId);
	}

}
