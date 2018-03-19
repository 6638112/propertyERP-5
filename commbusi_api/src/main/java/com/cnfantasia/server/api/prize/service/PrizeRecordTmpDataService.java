/**   
* Filename:    PrizeRecordTmpDataService.java   
* @version:    1.0  
* Create at:   2015年6月8日 上午8:19:06   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPrizeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.prize.constant.PrizeDict;
import com.cnfantasia.server.api.prize.dao.IPrizeDao;
import com.cnfantasia.server.api.prize.dao.IPrizeRecordTmpDataDao;
import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.prize.entity.PrizeCheckParamEntity;
import com.cnfantasia.server.api.prize.entity.PrizePreConditionValidateResult;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntityExtend;
import com.cnfantasia.server.api.prize.entityDB.PrizeResultForDB;
import com.cnfantasia.server.api.prize.entityDB.SupriseGiftForDB;
import com.cnfantasia.server.api.prizeRule.constant.PrizeRuleConstant;
import com.cnfantasia.server.api.prizeRule.service.IPrizeRuleManager;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.surpriseGift.constant.SurpriseGiftDict;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.server.api.surpriseGift.service.ISurpriseGiftService;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.microblogQueue.callable.DiscountPushRunnable;
import com.cnfantasia.server.commbusi.microblogQueue.util.DefaultGbUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.prizeRecord.dao.IPrizeRecordBaseDao;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;
import com.cnfantasia.server.domainbase.prizeRecordTmp.dao.IPrizeRecordTmpBaseDao;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;
import com.cnfantasia.server.domainbase.prizeRecordTmpData.dao.IPrizeRecordTmpDataBaseDao;
import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    PrizeRecordTmpDataService.java
 * @version:    1.0.0
 * Create at:   2015年6月8日 上午8:19:06
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月8日       shiyl             1.0             1.0 Version
 */
public class PrizeRecordTmpDataService implements IPrizeRecordTmpDataService{
	private Log logger = LogFactory.getLog(getClass());
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IPrizeRecordBaseDao prizeRecordBaseDao;
	public void setPrizeRecordBaseDao(IPrizeRecordBaseDao prizeRecordBaseDao) {
		this.prizeRecordBaseDao = prizeRecordBaseDao;
	}
	
	private IPrizeRecordTmpBaseDao prizeRecordTmpBaseDao;
	public void setPrizeRecordTmpBaseDao(IPrizeRecordTmpBaseDao prizeRecordTmpBaseDao) {
		this.prizeRecordTmpBaseDao = prizeRecordTmpBaseDao;
	}
	
	private ISurpriseGiftService surpriseGiftService;
	public void setSurpriseGiftService(ISurpriseGiftService surpriseGiftService) {
		this.surpriseGiftService = surpriseGiftService;
	}
	
	private IPrizeRecordTmpDataDao prizeRecordTmpDataDao;
	public void setPrizeRecordTmpDataDao(IPrizeRecordTmpDataDao prizeRecordTmpDataDao) {
		this.prizeRecordTmpDataDao = prizeRecordTmpDataDao;
	}

	private IPrizeRecordTmpDataBaseDao prizeRecordTmpDataBaseDao;
	public void setPrizeRecordTmpDataBaseDao(IPrizeRecordTmpDataBaseDao prizeRecordTmpDataBaseDao) {
		this.prizeRecordTmpDataBaseDao = prizeRecordTmpDataBaseDao;
	}
	
	private IPrizeRuleManager prizeRuleManager;
	public void setPrizeRuleManager(IPrizeRuleManager prizeRuleManager) {
		this.prizeRuleManager = prizeRuleManager;
	}
	
	private IPrizeDao prizeDao;
	public void setPrizeDao(IPrizeDao prizeDao) {
		this.prizeDao = prizeDao;
	}
	
	private ICommonPrizeService commonPrizeService;
	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
		this.commonPrizeService = commonPrizeService;
	}
	
//	private ICommonLockService commonLockService;
//	public void setCommonLockService(ICommonLockService commonLockService) {
//		this.commonLockService = commonLockService;
//	}

	@Override
	public PrizePreConditionValidateResult validatePrizePreCondition(BigInteger userId, Integer userType) {
		//查询最大抽奖次数
		int defaultMaxCount = prizeRuleManager.getMaxPrizeCount();
		//检查剩余抽奖次数
		Integer doneCount =prizeDao.selectPrizeDoneCount(userId, userType);
		if(doneCount==null){
			throw new BusinessRuntimeException("prize.PrizeRecordTmpData.selectPrizeDoneCount.failed");
		}
		if(doneCount>=defaultMaxCount){
			throw new BusinessRuntimeException("prize.doPrizeIsLoginAndDefault.maxCount.failed");
		}
		return new PrizePreConditionValidateResult(defaultMaxCount, doneCount);
	}
	
	@Override
	public PrizePreConditionValidateResult validatePrizePreCondition(BigInteger userId, Integer userType, PrizeCheckParamEntity prizeCheckParamEntity) {
		dealTmpPrizeDataByNotifyInfo(userId, userType, prizeCheckParamEntity);//检查并处理临时表数据
		PrizePreConditionValidateResult validateResult = validatePrizePreCondition(userId, userType);
		//修复校验当前实际对应的抽奖次数
		prizeCheckParamEntity.setCurrPrizeNum(validateResult.fetchCurrPrizeCount());
		return validateResult;
	}
	
	@Override
	public void dealTmpPrizeDataByNotifyInfo(BigInteger userId, Integer userType,PrizeCheckParamEntity prizeCheckParam) {
		if(prizeCheckParam!=null&&prizeCheckParam.ifExistConfirmData()){
			Integer confirmPrizeNum = prizeCheckParam.getConfirmPrizeNum();
			BigInteger confirmRoomId = prizeCheckParam.getConfirmRoomId();
			String confirmPrizeTime = prizeCheckParam.getConfirmPrizeTime();
			if(prizeCheckParam.ifConfirmSuccess()){//上次折扣状态为成功
				//将对应临时表折扣状态为成功
				prizeRecordTmpDataDao.updatePrizeRecordTmpDataAsSuccess(userId,userType,confirmPrizeNum,confirmRoomId,confirmPrizeTime);
			}else{//上次折扣状态为失败
				//服务端标记对应临时表折扣状态为失败
				prizeRecordTmpDataDao.updatePrizeRecordTmpDataAsFailed(userId,userType,confirmPrizeNum,confirmRoomId,confirmPrizeTime);
				//将当前要请求的抽奖次数减1
//				prizeCheckParam.subCurrPrizeNumOne();
			}
		}else{//没有上次抽奖信息
			//将之前的未失败临时折扣转入到正式表;
			//转到下面处理
		}
		
		{//统一检查处理当前用户未处理的临时折扣信息
			Boolean isTimeOut = null;//不区分是否超时通知
			transferTmpPrizeData2Formal(userId, userType,isTimeOut);
			if(userType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){//syl-add-2015-5-22 11:12:37刷新抽奖结果
				commonPrizeService.freshMiniDiscountByUserId(userId,null,null);
			}
		}
		
	}
	
	@Override
	public PrizeRecordTmpData addTmpPrizeData(BigInteger userId,Integer userType,PrizeRecordTmpData prizeRecordTmpData) {
		if(prizeRecordTmpData.getId()==null){
			BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_prize_record_tmp_data);
			prizeRecordTmpData.setId(addId);
		}
		prizeRecordTmpDataBaseDao.insertPrizeRecordTmpData(prizeRecordTmpData);
		return prizeRecordTmpData;
	}

	@Override
	public void transferTmpPrizeData2Formal(BigInteger goalUserId, Integer goalUserType,Boolean isTimeOut) {
//		commonLockService.getLock(Lock.PRIZE_RECORD_TMP_DATA);
		List<PrizeRecordTmpData> prizeRecordTmpDataList = prizeRecordTmpDataDao.selectTmpPrizeDataListNotFailedByUserInfo(goalUserId,goalUserType,isTimeOut);
		if(prizeRecordTmpDataList!=null&&prizeRecordTmpDataList.size()>0){
			for(PrizeRecordTmpData tmpData:prizeRecordTmpDataList){
				try {
					transferTmpPrizeData2Formal(tmpData.getId());
				} catch (Exception e) {
					logger.debug("transferTmpPrizeData2Formal,BigInteger goalUserId, Integer goalUserType,Boolean isTimeOut,failed,",e);
				}
			}
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)//开启新事务
	public PrizeResultDiscountEntity transferTmpPrizeData2Formal(BigInteger toDealRecordId) {
//		commonLockService.getLock(Lock.PRIZE_RECORD_TMP_DATA);
		PrizeRecordTmpData tmpData = prizeRecordTmpDataDao.selectPrizeRecordTmpDataDetail(toDealRecordId);
		if(tmpData==null){
			return null;
		}
		BigInteger recordTmpDataId = tmpData.getId();
		Integer currUserType = tmpData.getUserType();
		//如果状态不是失败的则transfer
		if(tmpData!=null&&tmpData.getDealStatus().compareTo(PrizeDict.PrizeRecordTmpData_DealStatus.FAILED)!=0){
			BigInteger prizeRecordAddId = null;
			BigInteger tUserHasTRoomFId = tmpData.getUserhasroomId();
			Double discount = tmpData.getDiscount();
			String prizeTime = tmpData.getPrizetime();
			Integer poolType = tmpData.getPoolType();
			BigInteger prizeUserId = tmpData.getUserId();
			Integer prizeUserType = tmpData.getUserType();
			SupriseGiftForDB supriseGiftForDB = new SupriseGiftForDB(tmpData.getPrizeUniqueId(), tmpData.getPrizeStatus(), tmpData.getSurpriseType(), tmpData.getSurpriseName(), tmpData.getSurpriseExpirytime(), tmpData.getSurpriseHbamount());
			if(LoginDict.UserRegistOrTmp.REGIST_USER.compareTo(currUserType)==0){//注册用户
				PrizeRecord prizeRecord = new PrizeRecord();
				prizeRecordAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_prize_record);
				prizeRecord.setDiscount(discount);
				prizeRecord.setPrizeTime(prizeTime);
				prizeRecord.setEndTime(getPrizeEndTime(prizeTime));
				prizeRecord.settUserHasTRoomFId(tUserHasTRoomFId);//用户门牌关系Id
				prizeRecord.setId(prizeRecordAddId);
				prizeRecord.setStatus(PrizeDict.PrizeRecord_Status.NOT_USE);
				prizeRecord.setBackPoolStatus(PrizeDict.PrizeRecord_BackPoolStatus.Has_Back);//标记为已退回
				prizeRecord.setPoolType(poolType);
				prizeRecord.setSys0AddUser(prizeUserId);//syl-add-2015-7-2 17:42:29
				int resCount = prizeRecordBaseDao.insertPrizeRecord(prizeRecord);
				if(resCount<=0){
					throw new BusinessRuntimeException("prize.doPrizeIsLoginAndDefault.insertPrizeRecord.failed");
				}else{
					BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(prizeUserId);
					if(!DefaultGbUtil.checkIsDefaultGb(gbId)){//
						try {
							if(discount==0){//0折
								ApplicationContextBothUtil.getMicroblogQueueService().discountZero(prizeUserId,tUserHasTRoomFId);
							}else{
								DiscountPushRunnable.execute(prizeUserId, gbId, discount, tUserHasTRoomFId, prizeRecordAddId);
//								new Thread(new DiscountPushRunnable(prizeUserId, gbId, discount, tUserHasTRoomFId,prizeRecordAddId)).start();
							}
						} catch (Exception e) {
							logger.error("transferTmpPrizeData2Formal.push discouunt info error", e);
						}
					}
				}
			}else if(LoginDict.UserRegistOrTmp.TMP_USER.compareTo(currUserType)==0){//临时用户
				//保存抽奖结果
				PrizeRecordTmp prizeRecordTmpAdd = new PrizeRecordTmp();
				prizeRecordAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_prize_record_tmp);
				prizeRecordTmpAdd.setDiscount(discount);
				prizeRecordTmpAdd.setPrizeTime(prizeTime);
				prizeRecordTmpAdd.setEndTime(getPrizeEndTime(prizeTime));
				prizeRecordTmpAdd.settUserTmpFId(prizeUserId);
				prizeRecordTmpAdd.setId(prizeRecordAddId);
	//			prizeRecordTmpAdd.setBackPoolStatus(backPoolStatus);
				prizeRecordTmpAdd.setPoolType(poolType);
				int resCount = prizeRecordTmpBaseDao.insertPrizeRecordTmp(prizeRecordTmpAdd);
				if(resCount<=0){
					throw new BusinessRuntimeException("prize.doPrizeNotLogin.insertPrizeRecordTmp.failed");
				}
			}
			PrizeSurpriseGiftEntity prizeSurpriseGift = null;
//				//版本判断处理
//				if(RequestClientInfoEntity.checkSupriseGiftCanAdd(requestClientInfoEntity)){
//					prizeSurpriseGift = checkAndStorePrizeSurpriseGift(supriseGiftForDB, prizeUserType, prizeUserId, prizeRecordAddId);
//				}
			//版本判断已处理ok，此处直接取用
			prizeSurpriseGift = checkAndStorePrizeSurpriseGift(supriseGiftForDB, prizeUserType, prizeUserId, prizeRecordAddId);
			
			//临时表记录删除处理
			prizeRecordTmpDataDao.updatePrizeRecordTmpDataAsFinished(recordTmpDataId);
			
			DiscountValueEntity discountValueEntity = new DiscountValueEntity((long)(discount*PrizeRuleConstant.PRIZE_DISCOUNT_DIVNUM),PrizeRuleConstant.PRIZE_DISCOUNT_DIVNUM);
			PrizeResultDiscountEntity resDVE = new PrizeResultDiscountEntity(discountValueEntity,prizeSurpriseGift);
			return resDVE;
		}
		return null;//不符合条件的返回null
	}
	
//	private void transferTmpPrizeData2Formal(List<PrizeRecordTmpData> tmpData) {
//		//批量筛选出可处理的
//		//准备数据，待批量新增的登录抽奖，临时抽奖，意外惊喜列表
//		//批量获取登录的 addIdList,批量新增prizeRecordList
//		//批量获取临时的 addIdList,批量新增prizeRecordList
//		//批量保存意外惊喜，批量设置为已领取
//		//批量标记为处理过
//	}
	
	/**
	 * 将抽奖结果的意外惊喜存入数据库(如果有意外惊喜)
	 * @param supriseGiftForDB
	 * @param goalUserType
	 * @param goalUserId
	 * @param prizeRecordAddId
	 * @return
	 */
	@Override
	public PrizeSurpriseGiftEntity checkAndStorePrizeSurpriseGift(SupriseGiftForDB supriseGiftForDB,Integer goalUserType,BigInteger goalUserId,BigInteger prizeRecordAddId){
		PrizeSurpriseGiftEntity prizeSurpriseGift = null;
		if(supriseGiftForDB!=null&&supriseGiftForDB.isDataAvailable()){//保存意外惊喜
			Integer fromType = null;
			if(goalUserType.compareTo(LoginDict.UserRegistOrTmp.TMP_USER)==0){
				fromType = SurpriseGiftDict.PrizeSurpriseGift_FromType.TmpUserPrize;
			}else if(goalUserType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){
				fromType = SurpriseGiftDict.PrizeSurpriseGift_FromType.RegistUserPrize;
			}
			BigInteger fromId = prizeRecordAddId;//来源Id为抽奖记录的Id
			boolean isAutoFetch = false;//抽奖不直接获取
//			if(supriseGiftForDB.getSurpriseType()!=null&&
//					SurpriseGiftDict.PrizeSurpriseGift_DataType.YiBaoTicket.compareTo(supriseGiftForDB.getSurpriseType())==0
//					||SurpriseGiftDict.PrizeSurpriseGift_DataType.FmlTicket58.compareTo(supriseGiftForDB.getSurpriseType())==0
//					||SurpriseGiftDict.PrizeSurpriseGift_DataType.MovieTicket.compareTo(supriseGiftForDB.getSurpriseType())==0
//					||SurpriseGiftDict.PrizeSurpriseGift_DataType.AT_Taxi.compareTo(supriseGiftForDB.getSurpriseType())==0
//				){//怡宝和58自动标记为领取
//				isAutoFetch = true;
//			}
			if(supriseGiftForDB.getSurpriseType()!=null&&SurpriseGiftDict.PrizeSurpriseGift_DataType.HBAmount.compareTo(supriseGiftForDB.getSurpriseType())!=0){
				isAutoFetch = true;
			}
			Boolean checkIsLightApp = false;//默认为App
			prizeSurpriseGift = surpriseGiftService.addPrizeSurpriseGift(supriseGiftForDB, goalUserId, goalUserType, fromId, fromType,isAutoFetch,checkIsLightApp);
		}
		return prizeSurpriseGift;
	}
	
	/**
	 * 根据当前时间得到抽奖结束有效时间
	 * @param nowTime
	 * @return
	 */
	@Override
	public String getPrizeEndTime(String nowTime){
		return DateUtil.getMonthLastDay(nowTime);
	}
	
	@Override
	public PrizeResultDiscountEntity storePrizeResult(BigInteger goalUserId,Integer goalUserType,PrizeResultForDB prizeResult,RequestClientInfoEntity requestClientInfoEntity){
		BigInteger prizeRecordAddId = null;
		Double discount = prizeResult.getDiscount();
		String prizeTime = prizeResult.getPrizeTime();
		String prizeEndTime = this.getPrizeEndTime(prizeTime);
		if(LoginDict.UserRegistOrTmp.REGIST_USER.compareTo(goalUserType)==0){
			UserHasTRoom userHasTRoom = commonRoomService.getDefaultUserHasTRoom(goalUserId);
			BigInteger tUserHasTRoomFId = userHasTRoom.getId();
			
			PrizeRecord prizeRecord = new PrizeRecord();
			prizeRecordAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_prize_record);
			prizeRecord.setDiscount(discount);
			prizeRecord.setPrizeTime(prizeTime);
			prizeRecord.setEndTime(prizeEndTime);
			prizeRecord.settUserHasTRoomFId(tUserHasTRoomFId);//用户门牌关系Id
			prizeRecord.setId(prizeRecordAddId);
			prizeRecord.setStatus(PrizeDict.PrizeRecord_Status.NOT_USE);
			prizeRecord.setBackPoolStatus(PrizeDict.PrizeRecord_BackPoolStatus.Has_Back);//标记为已退回
			prizeRecord.setPoolType(prizeResult.getPoolType());
			int resCount = prizeRecordBaseDao.insertPrizeRecord(prizeRecord);
			if(resCount<=0){
				throw new BusinessRuntimeException("prize.doPrizeIsLoginAndDefault.insertPrizeRecord.failed");
			}
		}else{
			//保存抽奖结果
			PrizeRecordTmp prizeRecordTmpAdd = new PrizeRecordTmp();
			prizeRecordAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_prize_record_tmp);
			prizeRecordTmpAdd.setDiscount(discount);
			prizeRecordTmpAdd.setPrizeTime(prizeTime);
			prizeRecordTmpAdd.setEndTime(prizeEndTime);
			prizeRecordTmpAdd.settUserTmpFId(goalUserId);
			prizeRecordTmpAdd.setId(prizeRecordAddId);
	//		prizeRecordTmpAdd.setBackPoolStatus(backPoolStatus);
			prizeRecordTmpAdd.setPoolType(prizeResult.getPoolType());
			int resCount = prizeRecordTmpBaseDao.insertPrizeRecordTmp(prizeRecordTmpAdd);
			if(resCount<=0){
				throw new BusinessRuntimeException("prize.doPrizeNotLogin.insertPrizeRecordTmp.failed");
			}
		}
		PrizeSurpriseGiftEntity prizeSurpriseGift = null;
		//版本判断处理
		if(RequestClientInfoEntity.checkSupriseGiftCanAdd(requestClientInfoEntity)){
			SupriseGiftForDB supriseGiftForDB = prizeResult.getSupriseGiftForDB();
			prizeSurpriseGift = this.checkAndStorePrizeSurpriseGift(supriseGiftForDB, goalUserType, goalUserId, prizeRecordAddId);
		}
		DiscountValueEntity discountValueEntity = new DiscountValueEntity((long)(discount*PrizeRuleConstant.PRIZE_DISCOUNT_DIVNUM),PrizeRuleConstant.PRIZE_DISCOUNT_DIVNUM);
		PrizeResultDiscountEntity resDVE = new PrizeResultDiscountEntity(discountValueEntity,prizeSurpriseGift);
		return resDVE;
	}
	
	@Override
	public PrizeResultDiscountEntityExtend storePrizeResult(BigInteger goalUserId, Integer goalUserType,
			PrizeResultForDB prizeResult, RequestClientInfoEntity requestClientInfoEntity,PrizeCheckParamEntity prizeCheckParamEntity) {
		BigInteger userhasroomId = null;
		BigInteger roomId = null;
		if(LoginDict.UserRegistOrTmp.REGIST_USER.compareTo(goalUserType)==0){
			UserHasTRoom userHasTRoom = commonRoomService.getDefaultUserHasTRoom(goalUserId);
			userhasroomId = userHasTRoom.getId();
			roomId = userHasTRoom.gettRoomFId();
		}
		Integer dealStatus = PrizeDict.PrizeRecordTmpData_DealStatus.UNDO;
		Double discount = prizeResult.getDiscount();
		String prizeTime = prizeResult.getPrizeTime();
		Integer poolType = prizeResult.getPoolType();
		String prizeStatus = prizeResult.getStatus();
		String prizeUniqueId = prizeResult.getId();
		String autoSuccTime = null;//TODO 通过查询的方式判断处理
		PrizeRecordTmpData prizeRecordTmpData = new PrizeRecordTmpData();
		prizeRecordTmpData.setAutoSuccTime(autoSuccTime);
		prizeRecordTmpData.setDealStatus(dealStatus);
		prizeRecordTmpData.setDiscount(discount);
		prizeRecordTmpData.setPoolType(poolType);
		prizeRecordTmpData.setPrizeStatus(prizeStatus);
		prizeRecordTmpData.setPrizetime(prizeTime);
		prizeRecordTmpData.setPrizeUniqueId(prizeUniqueId);
		prizeRecordTmpData.setUserhasroomId(userhasroomId);
		prizeRecordTmpData.setRoomId(roomId);
		prizeRecordTmpData.setUserId(goalUserId);
		prizeRecordTmpData.setUserType(goalUserType);
		prizeRecordTmpData.setPrizeNum(prizeCheckParamEntity.getCurrPrizeNum());
		//版本判断处理
		if(RequestClientInfoEntity.checkSupriseGiftCanAdd(requestClientInfoEntity)){
			SupriseGiftForDB supriseGiftForDB = prizeResult.getSupriseGiftForDB();
			if(supriseGiftForDB!=null){
				String surpriseExpirytime = supriseGiftForDB.getExpiryTime();
				Double surpriseHbamount = supriseGiftForDB.getHbAmount();
				String surpriseName = supriseGiftForDB.getSurpriseName();
				Integer surpriseType = supriseGiftForDB.getSurpriseType();
				prizeRecordTmpData.setSurpriseExpirytime(surpriseExpirytime);
				prizeRecordTmpData.setSurpriseHbamount(surpriseHbamount);
				prizeRecordTmpData.setSurpriseName(surpriseName);
				prizeRecordTmpData.setSurpriseType(surpriseType);
			}
		}
		//加入到临时处理表
		PrizeRecordTmpData prizeRecordTmpDataRes = addTmpPrizeData(goalUserId, goalUserType, prizeRecordTmpData);
		DiscountValueEntity discountValueEntity = new DiscountValueEntity((long)(discount*PrizeRuleConstant.PRIZE_DISCOUNT_DIVNUM),PrizeRuleConstant.PRIZE_DISCOUNT_DIVNUM);
		return new PrizeResultDiscountEntityExtend(discountValueEntity, prizeRecordTmpDataRes);
	}
	
	@Override
	public PrizeResultDiscountEntity transferTmpPrizeData2FormalForSuprise(BigInteger toDealRecordId) {
//		commonLockService.getLock(Lock.PRIZE_RECORD_TMP_DATA);
		return transferTmpPrizeData2Formal(toDealRecordId);
	}

}
