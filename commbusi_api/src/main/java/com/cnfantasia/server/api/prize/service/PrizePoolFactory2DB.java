package com.cnfantasia.server.api.prize.service;

import java.math.BigInteger;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.prize.dao.IPrizeDao;
import com.cnfantasia.server.api.prize.entity.PrizeCheckParamEntity;
import com.cnfantasia.server.api.prize.entity.PrizePreConditionValidateResult;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntityExtend;
import com.cnfantasia.server.api.prize.entityDB.PrizeResultForDB;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

public class PrizePoolFactory2DB implements IPrizePoolFactory{
	
	private IPrizeDao prizeDao;
	public void setPrizeDao(IPrizeDao prizeDao) {
		this.prizeDao = prizeDao;
	}

	private IPrizeRecordTmpDataService prizeRecordTmpDataService;
	public void setPrizeRecordTmpDataService(IPrizeRecordTmpDataService prizeRecordTmpDataService) {
		this.prizeRecordTmpDataService = prizeRecordTmpDataService;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
//	private ISurpriseGiftService surpriseGiftService;
//	public void setSurpriseGiftService(ISurpriseGiftService surpriseGiftService) {
//		this.surpriseGiftService = surpriseGiftService;
//	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PrizeResultDiscountEntity doPrize(BigInteger userId,Integer userType,RequestClientInfoEntity requestClientInfoEntity){
		defaultRoomCheckAndInit(userId, userType);
		PrizePreConditionValidateResult vaidateResult = prizeRecordTmpDataService.validatePrizePreCondition(userId, userType);
		PrizeResultForDB prizeResult = doPrizeFromDB(userId, userType);//进行抽奖
		//保存中奖纪录
		PrizeResultDiscountEntity resDVE = prizeRecordTmpDataService.storePrizeResult(userId, userType, prizeResult, requestClientInfoEntity);;
		resDVE.setPrizePreConditionValidateResult(vaidateResult);
		return resDVE;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PrizeResultDiscountEntityExtend doPrize(BigInteger userId, Integer userType,
			RequestClientInfoEntity requestClientInfoEntity, PrizeCheckParamEntity prizeCheckParamEntity) {
		defaultRoomCheckAndInit(userId, userType);
		PrizePreConditionValidateResult vaidateResult = prizeRecordTmpDataService.validatePrizePreCondition(userId, userType, prizeCheckParamEntity);
		Double lastDiscount = prizeDao.selectLastDiscount(userId, userType);//上次抽奖折扣
		PrizeResultForDB prizeResult = doPrizeFromDB(userId, userType);//进行抽奖
		//保存中奖纪录
		PrizeResultDiscountEntityExtend resDVE = prizeRecordTmpDataService.storePrizeResult(userId, userType, prizeResult, requestClientInfoEntity, prizeCheckParamEntity);
		resDVE.setPrizePreConditionValidateResult(vaidateResult);
		resDVE.setLastDiscount(lastDiscount);
		return resDVE;
	}
	
	/**
	 * 默认门牌check初始化                       
	 * @param userId
	 * @param userType
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public void defaultRoomCheckAndInit(BigInteger userId,Integer userType){
		if(userId==null||userType==null){
			throw new BusinessRuntimeException("prize.PrizeRecordTmpData.userIdOrTypeEmpty");
		}
		if(LoginDict.UserRegistOrTmp.REGIST_USER.compareTo(userType)==0){//注册用户
		}else if(LoginDict.UserRegistOrTmp.TMP_USER.compareTo(userType)==0){//临时用户
		}else{
			throw new BusinessRuntimeException("prize.PrizeRecordTmpData.unkonwnUserType");
		}
		
		if(userType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){
			//查询用户默认门牌
			RoomEntity roomEntity=commonRoomService.getDefaultRoomInfo(userId);
			if(roomEntity==null){
				commonRoomService.addNullRoom(userId);
				roomEntity = commonRoomService.getDefaultRoomInfo(userId);
			}
		}
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public PrizeResultForDB doPrizeFromDB(BigInteger userId,Integer userType){
//		prizeDao.testTrans();
		PrizeResultForDB prizeResult = null;
		//进行抽奖
		prizeResult = prizeDao.doPrizeFromDB(userId, userType);
//		if(prizeResult==null||!prizeResult.getStatus().equals("0")){
//			throw new BusinessRuntimeException("prize.doPrizeFromDB.result.error");
//		}
		if(prizeResult==null||!prizeResult.isDataAvailable()){
			throw new BusinessRuntimeException("prize.doPrizeFromDB.result.error");
		}
		return prizeResult;
	}
	
}
