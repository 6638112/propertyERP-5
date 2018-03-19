/**   
* Filename:    CommonPrizeService.java   
* @version:    1.0  
* Create at:   2014年6月27日 上午12:32:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.dao.ICommonPrizeDao;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRoomDao;
import com.cnfantasia.server.api.commonBusiness.entity.StartEndDate;
import com.cnfantasia.server.api.commonBusiness.util.PropertyPeriodCalculateUtil;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.prize.constant.PrizeConstant;
import com.cnfantasia.server.api.prize.entity.FirstLastPrizeTimeEntity;
import com.cnfantasia.server.api.prize.entity.PrizeCountWithUserEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntityWithBusinessMonthYear;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.plotproperty.entity.BusinessMonthYearFactory;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.ISectionDateMulti;
import com.cnfantasia.server.commbusi.plotproperty.entity.MonthOrTime;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;
import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * Filename:    CommonPrizeService.java
 * @version:    1.0.0
 * Create at:   2014年6月27日 上午12:32:52
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月27日       shiyl             1.0             1.0 Version
 */
public class CommonPrizeService implements ICommonPrizeService{
	private ICommonPrizeDao commonPrizeDao;
	public void setCommonPrizeDao(ICommonPrizeDao commonPrizeDao) {
		this.commonPrizeDao = commonPrizeDao;
	}
	private ICommonRoomDao commonRoomDao;
	public void setCommonRoomDao(ICommonRoomDao commonRoomDao) {
		this.commonRoomDao = commonRoomDao;
	}
	
//	@Override
//	public Date getPropertyMonthByTime(BigInteger userId, String time){
//		Date resDate = null;
//		Date nowDate = null;
//		try {
//			nowDate = DateUtil.formatDay.get().parse(time);
//		} catch (ParseException e) {
//			throw new BusinessRuntimeException("CommonPrizeService.getPreMonthStartEnd.parseMonth.error",new Object[]{time},e);
//		}
//		GroupBuilding groupBuilding = commonRoomDao.selectGroupBuildingByUserId(userId);
//		if(groupBuilding!=null&&groupBuilding.getSignStatus()!=null&&groupBuilding.getSignStatus().compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0
//				&&groupBuilding.getPayPeriodStart()!=null&&groupBuilding.getPayPeriodEnd()!=null){
//			Date currMonth = PropertyPeriodCalculateUtil.getDiscountMonthByNowTime(nowDate, groupBuilding.getPayPeriodEnd());
//			resDate = currMonth;
//		}else{
//			resDate = nowDate;
//		}
//		return resDate;
//	}
	
//	/**
//	 * 根据时间获取对应物业周期起止日期
//	 * @param userId
//	 * @param time 年月日
//	 * @return
//	 */
//	@Override
//	public StartEndDate getPreMonthStartEndByTime(BigInteger userId, String time){
//		StartEndDate tmpStartEndDate = null;
//		Date nowDate = null;
//		try {
//			nowDate = DateUtil.formatDay.get().parse(time);
//		} catch (ParseException e) {
//			throw new BusinessRuntimeException("CommonPrizeService.getPreMonthStartEnd.parseMonth.error",new Object[]{time},e);
//		}
//		GroupBuilding groupBuilding = commonRoomDao.selectGroupBuildingByUserId(userId);
////		{//syl-del-2014-12-18 17:25:25 bak
////			if(groupBuilding!=null&&groupBuilding.getSignStatus()!=null&&groupBuilding.getSignStatus().compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0
////					&&groupBuilding.getPayPeriodStart()!=null&&groupBuilding.getPayPeriodEnd()!=null){
////				Date currMonth = PropertyPeriodCalculateUtil.getPropertyMonthByNowTime(nowDate, groupBuilding.getPayPeriodEnd());
////				tmpStartEndDate = PropertyPeriodCalculateUtil.parsePropertyStartEndDate(currMonth, groupBuilding.getPayPeriodEnd());
////			}else{
////				tmpStartEndDate = PropertyPeriodCalculateUtil.parseFirstLastDayByMonthDate(nowDate);
////			}
////		}
//		
//		{//syl-upd-2014-12-18 17:25:34 
//			Integer payPeriodEnd = null;
//			if(groupBuilding!=null&&groupBuilding.getSignStatus()!=null&&groupBuilding.getSignStatus().compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0
//					&&groupBuilding.getPayPeriodStart()!=null&&groupBuilding.getPayPeriodEnd()!=null){
//				payPeriodEnd = groupBuilding.getPayPeriodEnd();
//			}else{
//			}
//			tmpStartEndDate = PropertyPeriodCalculateUtil.getPreMonthStartEndByTime(nowDate, payPeriodEnd);
//		}
//		
//		return tmpStartEndDate;
//	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	/**
	 * 根据指定月份获取物业周期日期
	 * @param userId
	 * @param month 年月
	 * @return
	 */
	@Override
	public StartEndDate getPreMonthStartEndByMonth(BigInteger userId, String month){
		StartEndDate tmpStartEndDate = null;
		Date nowDate = null;
		try {
			nowDate = DateUtil.formatMonth.get().parse(month);
		} catch (ParseException e) {
			throw new BusinessRuntimeException("CommonPrizeService.getPreMonthStartEnd.parseMonth.error",new Object[]{month},e);
		}
		GroupBuilding groupBuilding = commonRoomDao.selectGroupBuildingByUserId(userId);
		if(groupBuilding!=null&&groupBuilding.getSignStatus()!=null&&groupBuilding.getSignStatus().compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0
				&&groupBuilding.getPayPeriodStart()!=null&&groupBuilding.getPayPeriodEnd()!=null){
			tmpStartEndDate = PropertyPeriodCalculateUtil.parseDiscountStartEndDate(nowDate, groupBuilding.getPayPeriodEnd());
		}else{
			tmpStartEndDate = PropertyPeriodCalculateUtil.parseFirstLastDayByMonthDate(nowDate);
		}
		return tmpStartEndDate;
	}
	
	@Override
	public StartEndDate getFirstLastPropMonth(BigInteger userId) {
		StartEndDate resStartEndDate = null;
		//根据用户Id查询家庭对应所有的起止抽奖时间
		FirstLastPrizeTimeEntity firstLastPrizeTimeEntity =  commonPrizeDao.selectFamilyFirstLastPrizeTimeByUserId(userId);
		String firstPrizeTime = null;
		String lastPrizeTime = null;
		if(firstLastPrizeTimeEntity!=null){
			firstPrizeTime = firstLastPrizeTimeEntity.getFirstPrizeTime();//首次抽奖时间
			lastPrizeTime = firstLastPrizeTimeEntity.getLastPrizeTime();//最后一次抽奖时间
		}
		if(firstPrizeTime!=null&&lastPrizeTime!=null){
			Integer periodEnd = null;
			GroupBuilding groupBuilding = commonRoomDao.selectGroupBuildingByUserId(userId);
			if(groupBuilding!=null&&groupBuilding.getSignStatus()!=null&&groupBuilding.getSignStatus().compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0
					&&groupBuilding.getPayPeriodStart()!=null&&groupBuilding.getPayPeriodEnd()!=null){
				periodEnd = groupBuilding.getPayPeriodEnd();
			}
			Date startDate = PropertyPeriodCalculateUtil.getDiscountMonthByNowTime(firstLastPrizeTimeEntity.getFirstPrizeTime(), periodEnd);
			Date endDate = PropertyPeriodCalculateUtil.getDiscountMonthByNowTime(firstLastPrizeTimeEntity.getLastPrizeTime(), periodEnd);
			resStartEndDate = new StartEndDate(startDate, endDate);
		}
		return resStartEndDate;
	}
	
	@Override
	public int getIsUsedDiscountCount(BigInteger userId,IBusinessMonthYear monthYearWithGB) {
		ISectionDateMulti tmpStartEndDate = monthYearWithGB.getPayTime();
		return commonPrizeDao.selectIsUsedDiscountCount(userId,tmpStartEndDate.getStartYearMonthDay(),tmpStartEndDate.getEndYearMonthDay());
	}
	
//	@Override
//	public PrizeRecordSimpleEntity getLeastDiscountFirstByMonth(BigInteger userId, String month) {
//		return commonPrizeDao.selectLeastDiscountFirstByMonth(userId, month);
//	}
	
//	@Override
//	public PrizeRecordEntity getLeastDiscountFirstByMonthAndUsed(BigInteger userId, String month) {
////		PrizeRecordEntity isUsedPR = commonPrizeDao.selectIsUsedPrizeRecordByMonth(userId, month);
////		if(isUsedPR==null){
////			isUsedPR = commonPrizeDao.selectLeastDiscountFirstByMonth(userId, month);
////		}
////		return isUsedPR;
//		
//		StartEndDate tmpStartEndDate = getPreMonthStartEndByMonth(userId, month);
//		PrizeRecordEntity isUsedPR = commonPrizeDao.selectIsUsedPrizeRecordByMonth(userId,tmpStartEndDate.getStartYearMonthDay(),tmpStartEndDate.getEndYearMonthDay());
//		if(isUsedPR==null){
//			isUsedPR = commonPrizeDao.selectLeastDiscountFirstByMonth(userId,tmpStartEndDate.getStartYearMonthDay(),tmpStartEndDate.getEndYearMonthDay());
//		}
//		return isUsedPR;
//	}
//	
//	@Override
////	public PrizeRecordEntity getLeastDiscountFirstByDayTimeAndUsed(BigInteger userId,String time) {
//	public PrizeRecordEntity getLeastDiscountFirstByDayTimeAndUsed(BigInteger userId,BusinessMonthYearWithGB monthYearWithGB) {
//		StartEndDate tmpStartEndDate = monthYearWithGB.getPreMonthStartEnd();
//		PrizeRecordEntity isUsedPR = commonPrizeDao.selectIsUsedPrizeRecordByMonth(userId,tmpStartEndDate.getStartYearMonthDay(),tmpStartEndDate.getEndYearMonthDay());
//		if(isUsedPR==null){
//			isUsedPR = commonPrizeDao.selectLeastDiscountFirstByMonth(userId,tmpStartEndDate.getStartYearMonthDay(),tmpStartEndDate.getEndYearMonthDay());
//		}
//		return isUsedPR; 
//	}
	
//	@Override
//	public PrizeRecordSimpleEntity getLeastDiscountFirstCurrMonth(BigInteger userId) {
//		String nowTime = dualDao.getNowTime();
//		String tmpYearMonthStr = DateUtil.strFormate(nowTime,DateUtil.formatSecond.get(), DateUtil.formatMonth.get());
//		return getLeastDiscountFirstByMonthAndUsed(userId, tmpYearMonthStr);
//	}
	
//	@Override
//	public Double getLeastDiscountNotLoginByMonth(String deviceId, Long deviceType, String yearMonth) {
//		return commonPrizeDao.selectLeastDiscountNotLoginByMonth(deviceId, deviceType, yearMonth);
//	}

//	@Override
//	public PrizeRecordTmp getLeastDiscountNotLoginNowDay(String deviceId, Long deviceType) {
//		return commonPrizeDao.selectLeastDiscountNotLoginNowDay(deviceId, deviceType);
//	}
	
	@Override
	public PrizeRecordTmp getLeastDiscountNotLoginNowDayTmpUser(BigInteger tmpUserId){
		return commonPrizeDao.selectLeastDiscountNotLoginNowDayByTmpUser(tmpUserId);
	}

	@Override
	public Room getRoomByPrizeRecordId(BigInteger prizeRecordId) {
		return commonPrizeDao.selectRoomByPrizeRecordId(prizeRecordId);
	}

	@Override
	public Integer markDiscountAsUsed(BigInteger prizeRecordId,Integer usedType,Long savedMoney) {
		return commonPrizeDao.updateDiscountAsUsed(prizeRecordId,usedType,savedMoney);
	}

	@Override
	public List<PrizeRecordEntity> getDiscountListByMonth(BigInteger userId, IBusinessMonthYear monthYearWithGB) {
		ISectionDateMulti tmpStartEndDate = monthYearWithGB.getPayTime();
		return commonPrizeDao.selectDiscountListByMonth(userId, tmpStartEndDate.getStartYearMonthDay(), tmpStartEndDate.getEndYearMonthDay());
	}
	@Override
	public List<PrizeRecordEntity> getDiscountListByMonthWithUser(BigInteger userId, IBusinessMonthYear monthYearWithGB, PageModel pageModel) {
		List<PrizeRecordEntity> resList = null;
		PrizeRecordEntity tmpPrizeRecordEntity = getLeastDiscountFirstByMonthAndUsed(userId, monthYearWithGB);
		ISectionDateMulti tmpStartEndDate = monthYearWithGB.getPayTime();
		resList = commonPrizeDao.selectDiscountListByMonthWithUser(userId, tmpStartEndDate.getStartYearMonthDay(), tmpStartEndDate.getEndYearMonthDay(),pageModel,tmpPrizeRecordEntity==null?null:tmpPrizeRecordEntity.getId());
		return resList;
	}

	@Override
	public Integer markPrizeRecordAsIsBack(List<BigInteger> toBackIds) {
		return commonPrizeDao.markPrizeRecordAsIsBack(toBackIds);
	}
	
	@Override
	public int getPrizeDoneCountAllTime(BigInteger userId){
		return commonPrizeDao.selectPrizeDoneCountAllTime(userId);
	}

	@Override
	public Integer getPrizeCountSigned() {
		return commonPrizeDao.selectSignedRealRoomTotalCount()*PrizeConstant.DEFAULT_EACH_ROOM_USER_COUNT;
	}

	@Override
	public Integer getPrizeCountUnSigned() {
		return commonPrizeDao.selectUnSignedUserTotalCount();
	}

	@Override
	public Integer getPrizeCountTotal() {
		return getPrizeCountSigned()+getPrizeCountUnSigned();
	}

	@Override
	public Integer getPrizeCountAuto(BigInteger userId) {
		GroupBuilding groupBuilding = commonRoomDao.selectGroupBuildingByUserId(userId);
		if(groupBuilding!=null&&groupBuilding.getSignStatus()!=null&&groupBuilding.getSignStatus().compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0){
			return getPrizeCountSigned();
		}else{
			return getPrizeCountUnSigned();
		}
	}

	@Override
	public List<PrizeRecordEntity> getPrizeRecordEntityMonthAllWithNull(BigInteger userId, String startYearMonthDay,
			String endYearMonthDay) {
		return commonPrizeDao.selectPrizeRecordEntityMonthAllWithNull(userId, startYearMonthDay, endYearMonthDay);
	}

	@Override
	public PrizeCountWithUserEntity getFamilyMaxPrizeCountInfoByUserId(BigInteger userId, String startYearMonthDay,
			String endYearMonthDay) {
		return commonPrizeDao.selectFamilyMaxPrizeCountInfoByUserId(userId, startYearMonthDay, endYearMonthDay);
	}

	@Override
	public PrizeCountWithUserEntity getSignalPrizeCountInfoByUserId(BigInteger userId, String startYearMonthDay,
			String endYearMonthDay) {
		return commonPrizeDao.selectSignalPrizeCountInfoByUserId(userId, startYearMonthDay, endYearMonthDay);
	}

	@Override
	public Integer getAllPrizeDoneCount(BigInteger userId, Integer userType) {
		Integer resCount = 0;
		if(userId!=null&&userType!=null){
			if(userType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){
				resCount = commonPrizeDao.selectAllPrizeDoneCountRegistUser(userId);
			}else if(userType.compareTo(LoginDict.UserRegistOrTmp.TMP_USER)==0){
				resCount = commonPrizeDao.selectAllPrizeDoneCountTmpUser(userId);
			}
		}
		return resCount;
	}

	@Override
	public UserSimpleEntity getUserByPrizeRecordId(BigInteger prizeRecordId) {
		return commonPrizeDao.selectUserByPrizeRecordId(prizeRecordId);
	}
	
	
	@Override
	public PrizeRecordEntity getLeastDiscountFirstByMonthAndUsed(BigInteger userId,IBusinessMonthYear monthYearWithGB) {
		ISectionDateMulti tmpStartEndDate = monthYearWithGB.getPayTime();
		PrizeRecordEntity prizeRecordEntity = fetchLeastDiscountFirstByMonthAndUsedSimple(userId, tmpStartEndDate.getStartYearMonthDay(),tmpStartEndDate.getEndYearMonthDay());
		return prizeRecordEntity;
	}
	
	
	@Override
	public PrizeRecordEntityWithBusinessMonthYear getLeastDiscountFirstByMonthAndUsedNowTime(BigInteger userId) {
		String nowTime = dualDao.getNowTime();
		IBusinessMonthYear monthYearWithGB = BusinessMonthYearFactory.newInstance(nowTime, commonRoomService.getGroupBuildingByUserId(userId), MonthOrTime.time);
		ISectionDateMulti tmpStartEndDate = monthYearWithGB.getPayTime();
		PrizeRecordEntity prizeRecordEntity = null;
//		{//尝试直接获取
//			prizeRecordEntity = commonPrizeDao.selectMiniPrizeRecordEntityFromRoomNowTime(userId, tmpStartEndDate.getStartYearMonthDay(),tmpStartEndDate.getEndYearMonthDay());
//		}
		
		if(prizeRecordEntity==null){//未取到则重新关联去查询
			prizeRecordEntity = fetchLeastDiscountFirstByMonthAndUsedSimple(userId, tmpStartEndDate.getStartYearMonthDay(),tmpStartEndDate.getEndYearMonthDay());
		}
		
//		if(prizeRecordEntity!=null){//syl-add-2015-6-26 14:21:41刷新缓存的折扣数据
//			freshMiniDiscountByUserId(userId, prizeRecordEntity.getDiscount(), prizeRecordEntity.getId());
//		}
		return new PrizeRecordEntityWithBusinessMonthYear(prizeRecordEntity, monthYearWithGB);
	}
	
	//关联查询实现
	private PrizeRecordEntity fetchLeastDiscountFirstByMonthAndUsedSimple(BigInteger userId,String startYearMonthDay,String endYearMonthDay){
		PrizeRecordEntity isUsedPR = commonPrizeDao.selectIsUsedPrizeRecordByMonth(userId,startYearMonthDay,endYearMonthDay);
		if(isUsedPR==null){
			isUsedPR = commonPrizeDao.selectLeastDiscountFirstByMonth(userId,startYearMonthDay,endYearMonthDay);
		}
		return isUsedPR; 
	}
	
	@Override
	public void freshMiniDiscountByUserId(BigInteger userId,Double discount,BigInteger prizeRecordId) {
		if(prizeRecordId==null){
			PrizeRecordEntityWithBusinessMonthYear prizeRecord = getLeastDiscountFirstByMonthAndUsedNowTime(userId);
			if(prizeRecord!=null){
				discount = prizeRecord.getDiscount();
				prizeRecordId = prizeRecord.getId();
			}
		}
		if(prizeRecordId!=null){
			commonPrizeDao.updateMiniDiscountByUserId(userId, discount, prizeRecordId);
		}
		
	}

	@Override
	public String getPre2MonthStartDay(BigInteger userId) {
		BigInteger groupBuildId = commonRoomService.getGroupBuildingIdByUserId(userId);
		return commonPrizeDao.selectPre2MonthStartDay(groupBuildId);
	}
	
	
}
