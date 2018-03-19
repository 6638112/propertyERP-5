package com.cnfantasia.server.api.prize.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.commonBusiness.entity.StartEndDate;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPrizeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.prize.constant.PrizeConstant;
import com.cnfantasia.server.api.prize.dao.IPrizeDao;
import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.prize.entity.PrizeCheckParamEntity;
import com.cnfantasia.server.api.prize.entity.PrizeCountWithUserEntity;
import com.cnfantasia.server.api.prize.entity.PrizeFortunesEntity;
import com.cnfantasia.server.api.prize.entity.PrizeIgnoreUsedEntity;
import com.cnfantasia.server.api.prize.entity.PrizePreConditionValidateResult;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntityWithBusinessMonthYear;
import com.cnfantasia.server.api.prize.entity.PrizeRecordQuery;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prize.entity.PrizeStateEntity;
import com.cnfantasia.server.api.prize.entity.SignalPrizeResult;
import com.cnfantasia.server.api.prize.entity.UserWithLeastDiscountEntity;
import com.cnfantasia.server.api.redenvelope.util.IDiscount2hbRule;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.surpriseGift.constant.SurpriseGiftDict;
import com.cnfantasia.server.api.surpriseGift.service.ISurpriseGiftService;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.ISectionDateMulti;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userHasTRoom.dao.IUserHasTRoomBaseDao;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;
import com.cnfantasia.server.domainbase.userTmp.entity.UserTmp;

@Service
public class PrizeService implements IPrizeService{
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	private IPrizeDao prizeDao;
	public void setPrizeDao(IPrizeDao prizeDao) {
		this.prizeDao = prizeDao;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	private IUserHasTRoomBaseDao userHasTRoomBaseDao;
	public void setUserHasTRoomBaseDao(IUserHasTRoomBaseDao userHasTRoomBaseDao) {
		this.userHasTRoomBaseDao = userHasTRoomBaseDao;
	}

	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}
	
	private IPrizePoolFactory prizePoolFactory;
	public void setPrizePoolFactory(IPrizePoolFactory prizePoolFactory) {
		this.prizePoolFactory = prizePoolFactory;
	}
	
//	private ICommonDeviceService commonDeviceService;
//	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
//		this.commonDeviceService = commonDeviceService;
//	}
	
	private ICommonPrizeService commonPrizeService;
	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
		this.commonPrizeService = commonPrizeService;
	}
	
	private ICommonUserService commonUserService;
	public void setCommonUserService(ICommonUserService commonUserService) {
		this.commonUserService = commonUserService;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IMsgpushService msgpushService;
	public void setMsgpushService(IMsgpushService msgpushService) {
		this.msgpushService = msgpushService;
	}
	
	private ISurpriseGiftService surpriseGiftService;
	public void setSurpriseGiftService(ISurpriseGiftService surpriseGiftService) {
		this.surpriseGiftService = surpriseGiftService;
	}
	
	private IDiscount2hbRule discount2hbRule;
	public void setDiscount2hbRule(IDiscount2hbRule discount2hbRule) {
		this.discount2hbRule = discount2hbRule;
	}
	
	
//	/**
//	 * @return
//	 */
//	private int getOnLineDays(BigInteger userId,String monthFirstDay,String monthLastDay) {
//		if(StringUtils.isEmpty(userId)){
//			return 0;
//		}
//		int count = commonUserService.selectOnLineDays(userId, monthFirstDay, monthLastDay);
//		return count;
//	}

//	/**
//	 * 查询当前正常的前用户数
//	 * @return
//	 */
//	public int getCurrUserCount() {
//		List<String> states= new ArrayList<String>();
//		states.add(UserDict.UserState.IN_USE.toString());
//		int count = userDao.selectCurrUserCount(states);
//		return count;
//	}
	
//	@Override
//	public Double getLeastDiscountIsLognWithDefault(BigInteger userHasTRoomId, String monthFirstDay, String monthLastDay) {
//		return prizeDao.selectLeastDiscountIsLognWithDefault(userHasTRoomId, monthFirstDay, monthLastDay);
//	}
//
//	@Override
//	public Double getLeastDiscountNotLogin(String deviceId,Long deviceType, String monthFirstDay, String monthLastDay) {
//		return prizeDao.selectLeastDiscountNotLogin(deviceId, deviceType,monthFirstDay, monthLastDay);
//	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<PrizeRecord> getPrizeRecordOrderByPrizeTime(BigInteger userId,PageModel pageModel) {
			//查询用户默认门牌
			User user = userBaseDao.selectUserBySeqId(userId);
			BigInteger defaultRoomId=user.getDefaultRoomId();
			if(StringUtils.isEmpty(defaultRoomId)){//当没有设定默认门牌时
				UserHasTRoom userHasTRoom = addNullRoom(userId);
				defaultRoomId = userHasTRoom.gettRoomFId();
			}
			//获取userHasTRoomId关系Id
			UserHasTRoom userHasTRoom=new UserHasTRoom();
			userHasTRoom.settUserFId(userId);
			userHasTRoom.settRoomFId(defaultRoomId);
			userHasTRoom.setApplyStatus(RoomDict.UserHasTRoom_ApplyStatus.SUCCESS);//申请通过的门牌
			List<UserHasTRoom> userHasTRoomList = userHasTRoomBaseDao.selectUserHasTRoomByCondition(MapConverter.toMap(userHasTRoom),false);
			if(userHasTRoomList==null||userHasTRoomList.size()!=1){
				throw new BusinessRuntimeException("prize.qryPrizeList.getUserHasTRoomList.failed");
			}
			//查询中奖记录
			PrizeRecordQuery prizeRecordQuery = new PrizeRecordQuery();
			prizeRecordQuery.settUserHasTRoomFId(userHasTRoomList.get(0).getId());
			List<PrizeRecord> prizeRecordList = prizeDao.selectPrizeRecordOrderByPrizeTime(prizeRecordQuery, pageModel);
			return prizeRecordList;
	}
	
	private UserHasTRoom addNullRoom(BigInteger userId){
		BigInteger realRoomId = RoomConstants.ROOM_NULL_ID_REAL;
		Boolean isDefault = true;
		return commonRoomService.addRoom(userId, realRoomId, isDefault, null);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<PrizeRecordEntity> getPrizeRecordByMonth(BigInteger userId, IBusinessMonthYear monthYearWithGB, PageModel pageModel) {
		return commonPrizeService.getDiscountListByMonthWithUser(userId, monthYearWithGB, pageModel);
	}
	
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public PrizeStateEntity getCurrPrizeStateNotLogin(BigInteger userTmpId) {
		Integer prizeDoneCount =  prizeDao.selectPrizeDoneCount(userTmpId, LoginDict.UserRegistOrTmp.TMP_USER);
		PrizeRecordTmp prizeRecordTmp = commonPrizeService.getLeastDiscountNotLoginNowDayTmpUser(userTmpId);
		Double leastDiscount = prizeRecordTmp==null?null:prizeRecordTmp.getDiscount();
		//syl-add-2015-4-20 11:24:29 可兑换的粮票金额
		Long savedHbMoney = null;
		if(leastDiscount!=null){
			BigInteger userId = null;
			savedHbMoney = discount2hbRule.getMoneyByDiscount(userId, leastDiscount);
		}
		PrizeStateEntity prizeStateEntity = new PrizeStateEntity(prizeDoneCount, leastDiscount,null,savedHbMoney);
		return prizeStateEntity;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public PrizeStateEntity getCurrPrizeStateIsLogin(BigInteger userId) {
		Integer prizeDoneCount = prizeDao.selectPrizeDoneCount(userId, LoginDict.UserRegistOrTmp.REGIST_USER);
		
		PrizeRecordEntityWithBusinessMonthYear prizeRecordEntityExtend = commonPrizeService.getLeastDiscountFirstByMonthAndUsedNowTime(userId);
		PrizeRecordEntity prizeRecordSimpleEntity = prizeRecordEntityExtend.getPrizeRecordEntity();
		
		Double leastDiscount = prizeRecordSimpleEntity==null?null:prizeRecordSimpleEntity.getDiscount();
		Boolean hasUsed = prizeRecordSimpleEntity==null?null:prizeRecordSimpleEntity.hasUsed();
		
		//syl-add-2015-4-20 11:24:29 可兑换的粮票金额
		Long savedHbMoney = null;
		if(leastDiscount!=null){
			savedHbMoney = discount2hbRule.getMoneyByDiscount(userId, leastDiscount);
		}
		PrizeStateEntity prizeStateEntity = new PrizeStateEntity(prizeDoneCount, leastDiscount,hasUsed,savedHbMoney);
		return prizeStateEntity;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public PrizeFortunesEntity getPrizeFortunesByMonthYear(BigInteger userId,IBusinessMonthYear monthYearWithGB) {
		//折扣周期起止时间
		ISectionDateMulti propStartEndDate = monthYearWithGB.getPayTime();
		//本月折扣列表
		String startYearMonthDay = propStartEndDate.getStartYearMonthDay();
		String endYearMonthDay = propStartEndDate.getEndYearMonthDay();
		List<PrizeRecordEntity> prizeList = commonPrizeService.getPrizeRecordEntityMonthAllWithNull(userId, startYearMonthDay, endYearMonthDay);
		PrizeCountWithUserEntity maxPrizeCountUserEntity = null;
		PrizeCountWithUserEntity currPrizeCountUser = null;
		PrizeRecordEntity leastPrizeRecord = null;
		{//syl-del 2015-5-21 00:55:25
			//最大抽奖次数用户 及次数信息  
			maxPrizeCountUserEntity = commonPrizeService.getFamilyMaxPrizeCountInfoByUserId(userId, startYearMonthDay, endYearMonthDay);
			//当前用户本月抽奖次数
			currPrizeCountUser = commonPrizeService.getSignalPrizeCountInfoByUserId(userId, startYearMonthDay, endYearMonthDay);
			//最低折扣记录
			leastPrizeRecord = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId, monthYearWithGB);
		}
		
		//门牌首次抽奖时间及最后一次抽奖时间
		StartEndDate firstLastPropMonth = commonPrizeService.getFirstLastPropMonth(userId);
		PrizeFortunesEntity resPrizeFortunesEntity = new PrizeFortunesEntity(propStartEndDate, leastPrizeRecord, prizeList, maxPrizeCountUserEntity, currPrizeCountUser,firstLastPropMonth);
		return resPrizeFortunesEntity;
	}

	@Override
	public void notifyFamilyLeastDiscount(BigInteger userId, Double discount) {
		if(!StringUtils.isEmpty(userId)){
			//查询家里人列表
			List<UserSimpleEntity> familyUserList = commonUserService.getFamilyMembersWithoutSelf(userId, false);
			//批量新增消息记录
			if(familyUserList!=null&&familyUserList.size()>0){
				for(UserSimpleEntity currUser:familyUserList){
					//哇，家里谁抽了个更低物业折扣~
					//由于每个用户的消息参数不同，所以需要逐个遍历
					addMsgPushInfo(currUser.getId(), "prize.notifyFamily.leastDiscount", null,NoticeDict.Message_Type.Family_Member_List,currUser.getDefaultRoomId());
				}
			}
		}
	}
	
	private BigInteger addMsgPushInfo(BigInteger userId, String msgInfoKey, Object[] msgParams,Long msgType,BigInteger defaultRoomId) {
		Message messageAdd = packingMessage(userId, msgInfoKey, msgParams,msgType);
		//设定消息参数
		String expiryTime = null;//失效时间为空
		List<MessageParameter> paramList = new ArrayList<MessageParameter>();
		{
			MessageParameter tmpMessageParameter = new MessageParameter();
			tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
			tmpMessageParameter.setKey("id");//房间Id
			tmpMessageParameter.settMessageFId(messageAdd.getId());
			tmpMessageParameter.setValue(defaultRoomId==null?null:defaultRoomId.toString());
			paramList.add(tmpMessageParameter);
		}
		CommUserDataEntity commUserDataEntity = new CommUserDataEntity(userId, LoginDict.UserRegistOrTmp.REGIST_USER, defaultRoomId);
		msgpushService.addMessage2Pool(commUserDataEntity, messageAdd, expiryTime,paramList);//立即推送
		return messageAdd.getId();
	}
	
	private Message packingMessage(BigInteger userId, String msgInfoKey, Object[] msgParams,Long msgType){
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
			messageAdd.setTitle(PrizeConstant.Msgpush_Title_Least_Discount);
			messageAdd.setType(msgType);
		}
		return messageAdd;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<UserWithLeastDiscountEntity> getUserWithLeastDiscountList(BigInteger userId,IBusinessMonthYear monthYearWithGB) {
		//折扣周期起止时间
		ISectionDateMulti propStartEndDate = monthYearWithGB.getPayTime();
		//本月折扣列表
		String startYearMonthDay = propStartEndDate.getStartYearMonthDay();
		String endYearMonthDay = propStartEndDate.getEndYearMonthDay();
			
		return prizeDao.selectUserWithLeastDiscountList(userId, startYearMonthDay, endYearMonthDay);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public SignalPrizeResult doPrizeNotLogin(UserTmp userTmp,RequestClientInfoEntity requestClientInfoEntity,Boolean checkIsLightApp) {
		BigInteger userTmpId = userTmp.getId();
		//抽奖
		PrizeResultDiscountEntity prizeTmpRes = prizePoolFactory.doPrize(userTmpId,LoginDict.UserRegistOrTmp.TMP_USER,requestClientInfoEntity);
		//3.结果处理
		SignalPrizeResult signalPrizeResult = handleSignalPrizeResultForTmpUser(userTmpId, prizeTmpRes, checkIsLightApp);
		return signalPrizeResult;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public SignalPrizeResult doPrizeIsLoginWithDefault(BigInteger userId,RequestClientInfoEntity requestClientInfoEntity,Boolean checkIsLightApp) {
		//临时用户抽奖
		PrizeResultDiscountEntity prizeTmpRes = prizePoolFactory.doPrize(userId,LoginDict.UserRegistOrTmp.REGIST_USER,requestClientInfoEntity);
		//结果处理
		SignalPrizeResult signalPrizeResult = handleSignalPrizeResultForLoginUser(userId, prizeTmpRes,checkIsLightApp);
		return signalPrizeResult;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public SignalPrizeResult doPrizeNotLogin(UserTmp userTmp,
		RequestClientInfoEntity requestClientInfoEntity, Boolean checkIsLightApp, PrizeCheckParamEntity prizeCheckParam) {
		BigInteger userTmpId = userTmp.getId();
		//抽奖
		PrizeResultDiscountEntity prizeTmpRes = prizePoolFactory.doPrize(userTmpId,LoginDict.UserRegistOrTmp.TMP_USER,requestClientInfoEntity,prizeCheckParam);
		//结果处理
		SignalPrizeResult signalPrizeResult = handleSignalPrizeResultForTmpUser(userTmpId, prizeTmpRes, checkIsLightApp); 
		return signalPrizeResult;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public SignalPrizeResult doPrizeIsLoginWithDefault(BigInteger userId,
			RequestClientInfoEntity requestClientInfoEntity, Boolean checkIsLightApp, PrizeCheckParamEntity prizeCheckParam) {
		//临时用户抽奖
		PrizeResultDiscountEntity prizeTmpRes = prizePoolFactory.doPrize(userId,LoginDict.UserRegistOrTmp.REGIST_USER,requestClientInfoEntity,prizeCheckParam);
		//结果处理
		SignalPrizeResult signalPrizeResult = handleSignalPrizeResultForLoginUser(userId, prizeTmpRes,checkIsLightApp);
		return signalPrizeResult;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	private SignalPrizeResult handleSignalPrizeResultForLoginUser(BigInteger userId,PrizeResultDiscountEntity prizeTmpRes,Boolean checkIsLightApp){
		PrizeRecordEntityWithBusinessMonthYear prizeRecordEntityExtend = commonPrizeService.getLeastDiscountFirstByMonthAndUsedNowTime(userId);
		PrizeRecordEntity prizeRecordEntity = prizeRecordEntityExtend.getPrizeRecordEntity();
		
		String prizeTimeStr = prizeRecordEntity==null?null:prizeRecordEntity.getPrizeTime();
		Double leastDiscount = prizeRecordEntity==null?null:prizeRecordEntity.getDiscount();
		boolean isLeastDiscountUsed = (prizeRecordEntity != null && prizeRecordEntity.hasUsed() != null && prizeRecordEntity.hasUsed());
		BigInteger registUserId = userId;
		
		Long savedMoney = prizeRecordEntity==null?null:prizeRecordEntity.getSavedMoney();
		Integer usedType = prizeRecordEntity==null?null:prizeRecordEntity.getUsedType();
		SignalPrizeResult signalPrizeResult = handleSignalPrizeResultCommon(userId, LoginDict.UserRegistOrTmp.REGIST_USER, prizeTmpRes, checkIsLightApp, prizeTimeStr, leastDiscount,isLeastDiscountUsed,registUserId
				,savedMoney,usedType);
		return signalPrizeResult;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	private SignalPrizeResult handleSignalPrizeResultForTmpUser(BigInteger userTmpId,PrizeResultDiscountEntity prizeTmpRes,Boolean checkIsLightApp){
		PrizeRecordTmp prizeRecordTmpLeast = commonPrizeService.getLeastDiscountNotLoginNowDayTmpUser(userTmpId);
		
		String prizeTimeStr = prizeRecordTmpLeast==null?null:prizeRecordTmpLeast.getPrizeTime();
		Double leastDiscount = prizeRecordTmpLeast==null?null:prizeRecordTmpLeast.getDiscount();
		boolean isLeastDiscountUsed = false;
		BigInteger registUserId = null;
		
		Long savedMoney = null;
		Integer usedType = null;
		SignalPrizeResult signalPrizeResult = handleSignalPrizeResultCommon(userTmpId, LoginDict.UserRegistOrTmp.TMP_USER, prizeTmpRes, checkIsLightApp, prizeTimeStr, leastDiscount,isLeastDiscountUsed,registUserId
				,savedMoney,usedType);
		return signalPrizeResult;
	}
	
	/**
	 * 抽奖结果数据转化
	 * @param _userId 用户Id
	 * @param _userType 用户类型
	 * @param prizeTmpRes 抽奖结果
	 * @param checkIsLightApp 是否为轻应用
	 * @param prizeTimeStr 抽奖时间Str
	 * @param leastDiscount 最低折扣
	 * @return
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	private SignalPrizeResult handleSignalPrizeResultCommon(BigInteger _userId,Integer _userType,PrizeResultDiscountEntity prizeTmpRes,Boolean checkIsLightApp,String prizeTimeStr,Double leastDiscount,boolean isLeastDiscountUsed,BigInteger registUserId
			,Long savedMoney,Integer usedType){
		DiscountValueEntity discount = prizeTmpRes.getDiscountValueEntity();
		Boolean isLeastDiscount = null;
		Long prizeTime = prizeTimeStr==null?null:DateUtil.timeToLong(prizeTimeStr);
		
		boolean isLeastDiscount_ignoreUsed = false;
		Double leastDiscount_ignoreUsed=null;
		if(leastDiscount!=null){
			if(discount.doubleValue().compareTo(leastDiscount)<0){//当次抽取的折扣小于已保存的最低折扣
				if(!isLeastDiscountUsed){//最低折扣未使用
					isLeastDiscount = true;
					leastDiscount = discount.doubleValue();
					if(_userType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){//syl-add-2015-5-22 11:12:37刷新抽奖结果 TODO ...
						commonPrizeService.freshMiniDiscountByUserId(_userId,null,null);
					}
				}else{
					isLeastDiscount_ignoreUsed = true;
					leastDiscount_ignoreUsed = discount.doubleValue();
				}
			}else{
				isLeastDiscount = false;
			}
		}else{
			isLeastDiscount = true;
			leastDiscount = discount.doubleValue();
			prizeTime = DateUtil.timeToLong(dualDao.getNowTime());
		}
		
		PrizeIgnoreUsedEntity ignoreUsedEntity = new PrizeIgnoreUsedEntity(isLeastDiscountUsed, savedMoney, usedType, isLeastDiscount_ignoreUsed?isLeastDiscount_ignoreUsed:isLeastDiscount);
		
		Long savedHbMoney = null;
		{
			savedHbMoney = discount2hbRule.getMoneyByDiscount(registUserId,isLeastDiscount_ignoreUsed?leastDiscount_ignoreUsed:leastDiscount);
		}
		
		Integer leftCount = null;
		{
			PrizePreConditionValidateResult validateResult = prizeTmpRes.getPrizePreConditionValidateResult();
			leftCount = validateResult.fetchLeftCount();
		}
		
		SignalPrizeResult signalPrizeResult = new SignalPrizeResult();
		signalPrizeResult.setDiscount(discount.doubleValue());
		signalPrizeResult.setLeftCount(leftCount);
		signalPrizeResult.setLeastDiscount(isLeastDiscount_ignoreUsed?leastDiscount_ignoreUsed:leastDiscount);
		signalPrizeResult.setSavedHbMoney(savedHbMoney);
		signalPrizeResult.setIsLeastDiscount(isLeastDiscount);
		signalPrizeResult.setPrizeTime(prizeTime);
		signalPrizeResult.setPrizeResultDiscountEntity(prizeTmpRes);//syl-add-2015-4-8 11:59:51
		signalPrizeResult.setIgnoreUsedEntity(ignoreUsedEntity);
		
		signalPrizeResult.setSurpriseGiftConfigPic(surpriseGiftService.qryTopLog(_userId,_userType, SurpriseGiftDict.SurpriseGiftPic_Type.FirstPrize,checkIsLightApp));//syl-add-2015-4-8 16:12:21
		return signalPrizeResult;
	}


	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Double getLastDiscount(BigInteger userId, Integer userType) {
		return prizeDao.selectLastDiscount(userId, userType);
	}
}
