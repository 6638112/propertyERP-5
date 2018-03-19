/**   
* Filename:    FamilyWealthService.java   
* @version:    1.0  
* Create at:   2015年4月29日 上午2:51:03   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyWealth.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.service.ICommonPointService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPrizeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRedenvelopeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.util.UserShowNameUtil;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.familyWealth.constant.FamilyWealthDict;
import com.cnfantasia.server.api.familyWealth.dao.IFamilyWealthDao;
import com.cnfantasia.server.api.familyWealth.entity.FamilyWealthOptionEntity;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.point.entity.SignResultEntity;
import com.cnfantasia.server.api.point.service.ISignService;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntityWithBusinessMonthYear;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.surpriseGift.service.ISurpriseGiftService;
import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.ISectionDateMulti;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

/**
 * Filename:    FamilyWealthService.java
 * @version:    1.0.0
 * Create at:   2015年4月29日 上午2:51:03
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月29日       shiyl             1.0             1.0 Version
 */
public class FamilyWealthService implements IFamilyWealthService{
	
	private IFamilyWealthDao familyWealthDao;
	public void setFamilyWealthDao(IFamilyWealthDao familyWealthDao) {
		this.familyWealthDao = familyWealthDao;
	}
	
	private ISurpriseGiftService surpriseGiftService;
	public void setSurpriseGiftService(ISurpriseGiftService surpriseGiftService) {
		this.surpriseGiftService = surpriseGiftService;
	}

	private IUserCouponService userCouponService;
	public void setUserCouponService(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}

	private ICommonRedenvelopeService commonRedenvelopeService;
	public void setCommonRedenvelopeService(ICommonRedenvelopeService commonRedenvelopeService) {
		this.commonRedenvelopeService = commonRedenvelopeService;
	}
	
	private ICommonPointService commonPointService;
	public void setCommonPointService(ICommonPointService commonPointService) {
		this.commonPointService = commonPointService;
	}
	
	private ISignService signService;
	public void setSignService(ISignService signService) {
		this.signService = signService;
	}

	private ICommonPrizeService commonPrizeService;
	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
		this.commonPrizeService = commonPrizeService;
	}
	
//	private IDualDao dualDao;
//	public void setDualDao(IDualDao dualDao) {
//		this.dualDao = dualDao;
//	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}


	@Override
	public List<FamilyWealthOptionEntity> getFamilyWealthOptionList(BigInteger userId,Boolean checkIsLightApp) {
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;
		List<FamilyWealthOptionEntity> currOptionList = familyWealthDao.selectFamilyWealthOptionList(userId);
		if(currOptionList!=null&&currOptionList.size()>0){//附加获取对应项的相关信息
			for(FamilyWealthOptionEntity tmpFamilyWealthOptionEntity:currOptionList){
				Integer type = tmpFamilyWealthOptionEntity.getType();
				if(FamilyWealthDict.FamilyWealthOption_Type.ConsumTicket.compareTo(type)==0){
					//剩余可用的消费券总张数
					UserCoupon userCoupon = new UserCoupon();
					userCoupon.settUserFId(userId);
					userCoupon.setStatus(UserCouponStatus.VALID);
					Integer count = userCouponService.getUserCouponCount(MapConverter.toMap(userCoupon));
//					Integer count = surpriseGiftService.getCouponAvailableCount(userId, userType,checkIsLightApp);
					tmpFamilyWealthOptionEntity.setValueStr(count==null?"0":count+"");
					tmpFamilyWealthOptionEntity.setBottomLeft("消费使用时不找零");
				}else if(FamilyWealthDict.FamilyWealthOption_Type.HB.compareTo(type)==0){
					//当前剩余粮票金额
					Long hbBalance = commonRedenvelopeService.getTotalHbBalance(userId, 0) + commonRedenvelopeService.getTotalHbBalance(userId, 1);
					tmpFamilyWealthOptionEntity.setValueStr(hbBalance==null?"0":PriceUtil.div100(hbBalance)+"");
				}else if(FamilyWealthDict.FamilyWealthOption_Type.Point.compareTo(type)==0){
					{//总积分
						PointData tmpPointData = commonPointService.getPointDataByUserId(userId);
						Long currPoint = tmpPointData==null?0:tmpPointData.getPointValue();
						tmpFamilyWealthOptionEntity.setValueStr(currPoint==null?"0":currPoint+"");
					}
					{//今天是否领取、已经连续领取多少天
						SignResultEntity commSignRecord = signService.getLastCommSignRecordWithTodayFirst(userId);
						StringBuffer bottomLeftSbf = new StringBuffer();
						if(commSignRecord!=null&&commSignRecord.getIsFetched()==true){//已签到
							bottomLeftSbf.append("今天已领取,");
						}else{
							bottomLeftSbf.append("今天未领取,");
						}
						Long dayCount = null;
						if(commSignRecord!=null&&commSignRecord.getCommSignRecord()!=null){
							dayCount = commSignRecord.getCommSignRecord().getDayCount();
						}
						bottomLeftSbf.append("已经连续领取"+(dayCount==null?0:dayCount)+"天");
						tmpFamilyWealthOptionEntity.setBottomLeft(bottomLeftSbf.toString());
					}
				}else if(FamilyWealthDict.FamilyWealthOption_Type.PropertyDiscount.compareTo(type)==0){
//					String nowTime = dualDao.getNowTime();
//					BusinessMonthYearWithGB monthYearWithGB = new BusinessMonthYearWithGB(nowTime, commonRoomService.getGroupBuildingByUserId(userId), MonthOrTime.time);
//					PrizeRecordEntity prizeRecordEntity = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId, monthYearWithGB);
					
					PrizeRecordEntityWithBusinessMonthYear prizeRecordEntityExtend = commonPrizeService.getLeastDiscountFirstByMonthAndUsedNowTime(userId);
					IBusinessMonthYear monthYearWithGB = prizeRecordEntityExtend.getBusinessMonthYearWithGB();
					PrizeRecordEntity prizeRecordEntity = prizeRecordEntityExtend.getPrizeRecordEntity();
					
					{//折扣、折扣抽取人
						Double discount = null;
						String prizeUserShowName = null;
						if(prizeRecordEntity!=null){
							discount = prizeRecordEntity.getDiscount();
							prizeUserShowName = UserShowNameUtil.getUserShowName(prizeRecordEntity.getPrizeUser());
						}
						tmpFamilyWealthOptionEntity.setValueStr(discount==null?"无":discount+"");
						tmpFamilyWealthOptionEntity.setBottomRight(prizeUserShowName==null?"":"抽取人:"+prizeUserShowName);
					}
					{//对应缴费周期
						ISectionDateMulti tmpStartEndDate = monthYearWithGB.getPayTime();
						Date startDate = tmpStartEndDate.getStartDate();
						String startMonthYear = startDate==null?null:DateUtil.formatDay.get().format(startDate);
						tmpFamilyWealthOptionEntity.setBottomLeft(startMonthYear==null?"":"本期折扣："+startMonthYear+"起");
					}
					{//门牌验证状态
						Boolean isEmptyRoom = null;
						RoomEntityWithValidate roomEntityWithValidate = commonRoomService.getDefaultRoomInfo(userId);
						isEmptyRoom = roomEntityWithValidate.checkIsEmptyRoom();
						tmpFamilyWealthOptionEntity.setIsEmptyRoom(isEmptyRoom);
					}
				}
			}
		}
		return currOptionList;
	}

	
}
