/**   
* Filename:    SurpriseGiftService.java   
* @version:    1.0  
* Create at:   2015年3月25日 上午8:09:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.surpriseGift.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.api.commonBusiness.service.ICommonRedenvelopeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.util.EbuyChannelParseUtil;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.prize.dao.IPrizeDao;
import com.cnfantasia.server.api.prize.entityDB.SupriseGiftForDB;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.redenvelope.constant.RedenvelopeDict;
import com.cnfantasia.server.api.surpriseGift.constant.SurpriseGiftDict;
import com.cnfantasia.server.api.surpriseGift.constant.SurpriseGiftDict.PrizeSurpriseGift_FromType;
import com.cnfantasia.server.api.surpriseGift.constant.SurpriseGiftDict.PrizeSurpriseGift_UseStatus;
import com.cnfantasia.server.api.surpriseGift.dao.ISurpriseGiftDao;
import com.cnfantasia.server.api.surpriseGift.entity.CouponCombiEntity;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntityDetail;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeGiftForPrize;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.prizeSurpriseGift.dao.IPrizeSurpriseGiftBaseDao;
import com.cnfantasia.server.domainbase.prizeSurpriseGift.entity.PrizeSurpriseGift;
import com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity.SurpriseGiftConfigPic;

/**
 * Filename:    SurpriseGiftService.java
 * @version:    1.0.0
 * Create at:   2015年3月25日 上午8:09:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月25日       shiyl             1.0             1.0 Version
 */
public class SurpriseGiftService implements ISurpriseGiftService{
	
	private ISurpriseGiftDao surpriseGiftDao;
	public void setSurpriseGiftDao(ISurpriseGiftDao surpriseGiftDao) {
		this.surpriseGiftDao = surpriseGiftDao;
	}
	
//	private ICommonPrizeService commonPrizeService;
//	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
//		this.commonPrizeService = commonPrizeService;
//	}
	
	private IPrizeSurpriseGiftBaseDao prizeSurpriseGiftBaseDao;
	public void setPrizeSurpriseGiftBaseDao(IPrizeSurpriseGiftBaseDao prizeSurpriseGiftBaseDao) {
		this.prizeSurpriseGiftBaseDao = prizeSurpriseGiftBaseDao;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommonRedenvelopeService commonRedenvelopeService;
	public void setCommonRedenvelopeService(ICommonRedenvelopeService commonRedenvelopeService) {
		this.commonRedenvelopeService = commonRedenvelopeService;
	}
	
	private IPrizeDao prizeDao;
	public void setPrizeDao(IPrizeDao prizeDao) {
		this.prizeDao = prizeDao;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}

//	private Long checkAndUpdSrcType(Long lastTimeLong,String newTime,Long srcType,Long goalSrcType){
//		Long resType = srcType;
//		Long newTimeLong = null;
//		if(!StringUtils.isEmpty(newTime)){
//			newTimeLong = DateUtil.timeToLong(newTime);
//		}
//		if(newTimeLong!=null){
//			if(lastTimeLong==null||newTimeLong>lastTimeLong){
//				resType = goalSrcType;
//			}
//		}
//		return resType;
//	}
	
	@Override
	public SurpriseGiftConfigPic qryTopLog(BigInteger userId, Integer userType,Boolean checkIsLightApp) {
		SurpriseGiftConfigPic resEntity = null;
		Long srcType = null;
		
		//syl-upd-2015-6-23 18:07:56 性能问题考虑的处理
		srcType = SurpriseGiftDict.SurpriseGiftPic_Type.FirstPrize;
		
//		Long lastTimeLong = null;
//		{
//			//判断是否有意外惊喜
//			PrizeSurpriseGift lastPrizeSurpriseGift = surpriseGiftDao.selectLastPrizeSurpriseGift(userId, userType,true,checkIsLightApp);
//			if(lastPrizeSurpriseGift!=null){
//				if(lastPrizeSurpriseGift.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.HBAmount)==0){
//					srcType = checkAndUpdSrcType(lastTimeLong, lastPrizeSurpriseGift.getCreateTime(), srcType, SurpriseGiftDict.SurpriseGiftPic_Type.HbPrize);
//				}
//			}
//		}
//		{
//			if(userType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){// 查询注册用户折扣惊喜
////				BusinessMonthYearWithGB monthYearWithGB = new BusinessMonthYearWithGB(dualDao.getNowTime(), commonRoomService.getGroupBuildingByUserId(userId), MonthOrTime.time);
////				PrizeRecord prizeRecord = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId, monthYearWithGB);
//				PrizeRecord prizeRecord = commonPrizeService.getLeastDiscountFirstByMonthAndUsedNowTime(userId);
//				if(prizeRecord!=null){
//					srcType = checkAndUpdSrcType(lastTimeLong, prizeRecord.getPrizeTime(), srcType, SurpriseGiftDict.SurpriseGiftPic_Type.FirstPrize);
//				}
//			}else if(userType.compareTo(LoginDict.UserRegistOrTmp.TMP_USER)==0){// 查询临时用户折扣惊喜
//				PrizeRecordTmp prizeRecordTmp = commonPrizeService.getLeastDiscountNotLoginNowDayTmpUser(userId);
//				if(prizeRecordTmp!=null){
//					srcType = checkAndUpdSrcType(lastTimeLong, prizeRecordTmp.getPrizeTime(), srcType, SurpriseGiftDict.SurpriseGiftPic_Type.FirstPrize);
//				}
//			}
//		}
		
		if(srcType!=null){
			resEntity = surpriseGiftDao.selectSurpriseGiftConfigPicByType(srcType);
		}
		if(resEntity==null){
			resEntity = new SurpriseGiftConfigPic();
			resEntity.setType(srcType);
			resEntity.setPicUrl("");
		}
		return resEntity;
	}
	@Override
	public SurpriseGiftConfigPic qryTopLog(BigInteger userId, Integer userType, Long srcType,Boolean checkIsLightApp) {
		return qryTopLog(userId, userType,checkIsLightApp);
//		if(SurpriseGiftDict.SurpriseGiftPic_Type.FirstPrize.compareTo(srcType)!=0){
//			throw new BusinessRuntimeException("surpriseGiftService.qryTopLog.srcType.unsupport");
//		}
//		SurpriseGiftConfigPic resEntity = null;
//		Integer doneCount = commonPrizeService.getAllPrizeDoneCount(userId, userType);
//		if(doneCount>0){
//			resEntity = surpriseGiftDao.selectSurpriseGiftConfigPicByType(srcType);
//		}else{
//			resEntity = new SurpriseGiftConfigPic();
//			resEntity.setType(srcType);
//			resEntity.setPicUrl("");
//		}
//		return resEntity;
	}

	@Override
	public List<PrizeSurpriseGiftEntity> qryPrizeSurpriseGiftList(BigInteger userId, Integer userType, PageModel pageModel,boolean checkIsLightApp) {
		return surpriseGiftDao.selectPrizeSurpriseGiftList(userId,userType,pageModel,true,checkIsLightApp);
	}
	
	@Override
	public PrizeSurpriseGiftEntity addPrizeSurpriseGift(SupriseGiftForDB supriseGiftForDB,BigInteger userId,Integer userType,BigInteger fromId,Integer fromType,boolean isAutoFetch,Boolean checkIsLightApp) {
		PrizeSurpriseGiftEntity prizeSurpriseGift = null;
		if(supriseGiftForDB!=null&&supriseGiftForDB.isDataAvailable()){//保存意外惊喜
			Integer dataType = supriseGiftForDB.getSurpriseType();
			String name;
			BigInteger dataId;
			String expiryTime;
			String showCount;
			String marketIcon;
			if(dataType.compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.MARKET_OPT)==0){
				dataId = supriseGiftForDB.getGiftId();//syl-upd-2015-9-17 17:59:52
				MsPrizeGiftForPrize giftDetail = ApplicationContextBothUtil.getPrizeOptionService().getMsPrizeGiftDetail(dataId);
				name=giftDetail.getMsPrizeOption().getName();
				expiryTime = giftDetail.getMsPrizeOption().getValiEndTime();
				showCount = giftDetail.fetchShowCount();
				marketIcon = giftDetail.fetchMarketIcon();
			}else{
				dataId = null;//为空
				name = supriseGiftForDB.getSurpriseName();
				expiryTime = supriseGiftForDB.getExpiryTime();
				showCount = null;//为空
				marketIcon = null;
			}
			Long hbAmount = supriseGiftForDB.getHbAmount()==null?null:PriceUtil.multiply100(supriseGiftForDB.getHbAmount());
			prizeSurpriseGift = addPrizeSurpriseGift(name, dataType, dataId, userId, userType, expiryTime, hbAmount, fromType, fromId,isAutoFetch,checkIsLightApp,supriseGiftForDB.getExchangeCode(),showCount,marketIcon);
		}
		return prizeSurpriseGift;
	}
	
	@Override
	public void markPrizeSurpriseGiftAsFetched(BigInteger userId, Integer userType, BigInteger prizeSurpriseGiftId,Boolean checkIsLightApp) {
		PrizeSurpriseGift prizeSurpriseGiftDetail = surpriseGiftDao.selectPrizeSurpriseGiftDetail(userId, userType, prizeSurpriseGiftId,false,checkIsLightApp);
		
//		if(prizeSurpriseGiftDetail!=null&&
//				(prizeSurpriseGiftDetail.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.HBAmount)==0
//				||prizeSurpriseGiftDetail.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.YiBaoTicket)==0
//				||prizeSurpriseGiftDetail.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.FmlTicket58)==0
//				/*||prizeSurpriseGiftDetail.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.MovieTicket)==0
//				||prizeSurpriseGiftDetail.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.AT_Taxi)==0*/
//				)
//			){//ok
		if(prizeSurpriseGiftDetail!=null){
			BigInteger userIdCurr = prizeSurpriseGiftDetail.getUserId();//抽奖调整
			Integer userTypeCurr = prizeSurpriseGiftDetail.getUserType();
			if(userTypeCurr.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){//注册用户
				if(prizeSurpriseGiftDetail.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.HBAmount)==0){//将对应粮票金额设置给对应用户,粮票金额增加
					Long moneyLong = prizeSurpriseGiftDetail.getHbAmount();
					if(prizeSurpriseGiftDetail.getFromType() == SurpriseGiftDict.PrizeSurpriseGift_FromType.Neighbour_Activity.intValue()) {
						int fromType =  RedenvelopeDict.PayRedEnvelope_FromType.SM_ACTION; //需要统计到可用粮票中
						commonRedenvelopeService.addPayRedEnvelope(userIdCurr, prizeSurpriseGiftDetail.getRoomId(), prizeSurpriseGiftId, fromType, moneyLong);
					}else{
						int fromType = RedenvelopeDict.PayRedEnvelope_FromType.SURPRISE_GIFT;
						commonRedenvelopeService.addPayRedEnvelope(userIdCurr, prizeSurpriseGiftDetail.getRoomId(), prizeSurpriseGiftId, fromType, moneyLong);
					}
				}
				Integer resCount = surpriseGiftDao.updatePrizeSurpriseGiftAsFetched(userIdCurr,userTypeCurr,prizeSurpriseGiftId);
				if(resCount==null||resCount<=0){
					throw new BusinessRuntimeException("surpriseGiftService.markPrizeSurpriseGiftAsFetched.failed");
				}
			}else{
				throw new BusinessRuntimeException("surpriseGiftService.markPrizeSurpriseGiftAsFetched.HBAmount.notRegUser");
			}
		}
		
	}

	@Override
	public PrizeSurpriseGiftEntityDetail getPrizeSurpriseGiftDetail(BigInteger userId, Integer userType,
			BigInteger prizeSurpriseGiftId,Boolean checkIsLightApp) {
		return surpriseGiftDao.selectPrizeSurpriseGiftDetail(userId,userType,prizeSurpriseGiftId,null,checkIsLightApp);
	}

	@Override
	public PrizeSurpriseGiftEntity getSurpriseGiftDirect(BigInteger userId, Integer userType, Integer fromType,BigInteger fromId,Boolean checkIsLightApp,String cityName) {
		if(PrizeSurpriseGift_FromType.Share.compareTo(fromType)!=0
				&&PrizeSurpriseGift_FromType.LightApp_TGLJ.compareTo(fromType)!=0
				&&PrizeSurpriseGift_FromType.Neighbour_Activity.compareTo(fromType)!=0
				&&PrizeSurpriseGift_FromType.LightApp_Prize.compareTo(fromType)!=0){
			throw new BusinessRuntimeException("surpriseGiftService.doGetSurpriseGift.fromType.unsupport");
		}
		SupriseGiftForDB supriseGiftForDB = prizeDao.fetchSupriseGiftFromDB(userId, userType, fromType,fromId,cityName);
		if(!supriseGiftForDB.isDataAvailable()){
			return null;
		}
		//存入数据库并返回
		boolean isAutoFetch = true;//分享直接获取粮票
//		if(SurpriseGiftDict.PrizeSurpriseGift_DataType.YiBaoTicket.compareTo(supriseGiftForDB.getSurpriseType())==0
//				||SurpriseGiftDict.PrizeSurpriseGift_DataType.FmlTicket58.compareTo(supriseGiftForDB.getSurpriseType())==0
//				||SurpriseGiftDict.PrizeSurpriseGift_DataType.MovieTicket.compareTo(supriseGiftForDB.getSurpriseType())==0
//				||SurpriseGiftDict.PrizeSurpriseGift_DataType.AT_Taxi.compareTo(supriseGiftForDB.getSurpriseType())==0
//			){//怡宝和58自动标记为领取
//			isAutoFetch = true;
//		}
		
		return addPrizeSurpriseGift(supriseGiftForDB, userId, userType, fromId, fromType,isAutoFetch,checkIsLightApp);
	}
	
	
	private PrizeSurpriseGiftEntity addPrizeSurpriseGift(String name,Integer dataType,BigInteger dataId,BigInteger userId, Integer userType,String expiryTime
			,Long hbAmount,Integer fromType,BigInteger fromId,boolean isAutoFetch,Boolean checkIsLightApp,String exchCode,String showCount,String marketIcon) {
		BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_prize_surprise_gift);
		BigInteger roomId = null;
		if(userType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){
			if(checkIsLightApp){//轻应用设置为主账号
				roomId = commonRoomService.getMainRoomIdByUserIdExceptionIfNull(userId);
			}else{
				roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
			}
		}
		String nowTime = dualDao.getNowTime();
		PrizeSurpriseGift prizeSurpriseGiftAdd = new PrizeSurpriseGift();
		{
			String createTime = nowTime;
			Integer fetchStatus = SurpriseGiftDict.PrizeSurpriseGift_FetchStatus.NotFetch;//默认未领取
			String fetchTime = null;//领取时间 默认为空
			prizeSurpriseGiftAdd.setCreateTime(createTime);
			prizeSurpriseGiftAdd.setExpiryTime(expiryTime);
			prizeSurpriseGiftAdd.setFetchStatus(fetchStatus);
			prizeSurpriseGiftAdd.setFetchTime(fetchTime);
			prizeSurpriseGiftAdd.setHbAmount(hbAmount);
			prizeSurpriseGiftAdd.setId(addId);
			prizeSurpriseGiftAdd.setName(name);
			prizeSurpriseGiftAdd.setRoomId(roomId);
			prizeSurpriseGiftAdd.setDataId(dataId);
			prizeSurpriseGiftAdd.setShowCount(showCount);
			prizeSurpriseGiftAdd.setMarketIcon(marketIcon);
			prizeSurpriseGiftAdd.setDataType(dataType);
			prizeSurpriseGiftAdd.setUserId(userId);
			prizeSurpriseGiftAdd.setUserType(userType);
			prizeSurpriseGiftAdd.setFromType(fromType);
			prizeSurpriseGiftAdd.setFromId(fromId);
			prizeSurpriseGiftAdd.setExchCode(exchCode);
			prizeSurpriseGiftAdd.setUseStatus(PrizeSurpriseGift_UseStatus.NotUse);//默认未使用
			prizeSurpriseGiftBaseDao.insertPrizeSurpriseGift(prizeSurpriseGiftAdd);
		}
		if(isAutoFetch){//判断是否自动领取
			markPrizeSurpriseGiftAsFetched(userId, userType, addId,checkIsLightApp);
		}
		return surpriseGiftDao.selectPrizeSurpriseGiftDetail(userId,userType,addId,null,checkIsLightApp);
	}

	@Override
	public CouponCombiEntity getCouponCombiData(BigInteger userId, Integer userType,Long totalAmount,Integer jfqSubType) {
		Boolean checkIsLightApp = EbuyChannelParseUtil.parseIsLightApp(jfqSubType);
		List<PrizeSurpriseGiftEntity> totalList =  getCouponAvailableList(userId, userType,checkIsLightApp);
//		Double payPercent = Double.valueOf(sysParamManager.getSysParaValue(SysParamKey.Coupon_User_Percent));//syl-del-2015-4-27 14:46:47 
		Double payPercent = EbuyChannelParseUtil.fetchPayPercent(jfqSubType, sysParamManager);
		
		CouponCombiEntity resCouponCombiEntity = new CouponCombiEntity(totalList, payPercent, totalAmount);
		return resCouponCombiEntity;
	}

	@Override
	public List<PrizeSurpriseGiftEntity> getCouponAvailableList(BigInteger userId, Integer userType,Boolean checkIsLightApp) {
		List<PrizeSurpriseGiftEntity> totalList =  surpriseGiftDao.selectCouponAvailableList(userId,userType,true,checkIsLightApp);
		return totalList;
	}
	
	@Override
	public Integer getCouponAvailableCount(BigInteger userId, Integer userType,Boolean checkIsLightApp) {
		return surpriseGiftDao.getCouponAvailableCount(userId,userType,true,checkIsLightApp);
	}
	
	@Override
	public void markCouponListAsUsed(BigInteger userId, Integer userType, Set<BigInteger> couponIdList,Boolean checkIsLightApp) {
		Integer resCount = surpriseGiftDao.updateCouponListAsUsed(userId,userType,couponIdList,checkIsLightApp);
		if(resCount==null||resCount!=couponIdList.size()){
			throw new BusinessRuntimeException("surpriseGiftService.markCouponListAsUsed.sizeNotEqual");
		}
	}

	@Override
	public void backSurpriseGiftBatch(List<BigInteger> surpriseGiftIdList) {
		Integer resCount = surpriseGiftDao.updateSurpriseGiftBatch2NotUse(surpriseGiftIdList);
		if(resCount==null||resCount!=surpriseGiftIdList.size()){
			throw new BusinessRuntimeException("surpriseGiftService.backSurpriseGiftBatch.sizeNotEqual");
		}
	}


}
