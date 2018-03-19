/**   
 * Filename:    RedenvelopeService.java   
 * @version:    1.0  
 * Create at:   2014年6月23日 上午3:13:30   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月23日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.redenvelope.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.service.ICommonPrizeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRedenvelopeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.prize.constant.PrizeDict;
import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redenvelope.constant.RedenvelopeDict;
import com.cnfantasia.server.api.redenvelope.dao.IRedenvelopeDao;
import com.cnfantasia.server.api.redenvelope.entity.HbConvertConsumEntity;
import com.cnfantasia.server.api.redenvelope.entity.HbConvertRuleDescEntity;
import com.cnfantasia.server.api.redenvelope.entity.PayRedEnvelopeDetailEntity;
import com.cnfantasia.server.api.redenvelope.entity.PayRedEnvelopeEntity;
import com.cnfantasia.server.api.redenvelope.entity.SimpleHbBalanceEntity;
import com.cnfantasia.server.api.redenvelope.entity.SimpleHbDiscountEntity;
import com.cnfantasia.server.api.redenvelope.util.IDiscount2hbRule;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYearSignal;
import com.cnfantasia.server.commbusi.plotproperty.entity.PayBillInfo;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.payRedEnvelope.dao.PayRedEnvelopeBaseDao;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;

/**
 * Filename: RedenvelopeService.java
 * 
 * @version: 1.0.0 Create at: 2014年6月23日 上午3:13:30 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月23日 shiyl 1.0 1.0 Version
 */
public class RedenvelopeService implements IRedenvelopeService {
//	private Log logger = LogFactory.getLog(getClass());
	private IRedenvelopeDao redenvelopeDao;
	public void setRedenvelopeDao(IRedenvelopeDao redenvelopeDao) {
		this.redenvelopeDao = redenvelopeDao;
	}

	private IDiscount2hbRule discount2hbRule;
	public void setDiscount2hbRule(IDiscount2hbRule discount2hbRule) {
		this.discount2hbRule = discount2hbRule;
	}

	private ICommonPrizeService commonPrizeService;
	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
		this.commonPrizeService = commonPrizeService;
	}

	private ICommonRedenvelopeService commonRedenvelopeService;
	public void setCommonRedenvelopeService(ICommonRedenvelopeService commonRedenvelopeService) {
		this.commonRedenvelopeService = commonRedenvelopeService;
	}

	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IPlotpropertyCfgService plotpropertyCfgService;
	public void setPlotpropertyCfgService(
			IPlotpropertyCfgService plotpropertyCfgService) {
		this.plotpropertyCfgService = plotpropertyCfgService;
	}

	@Override
	public SimpleHbDiscountEntity getLastDiscountByMonth(BigInteger userId,IBusinessMonthYearSignal monthYearWithGB) {
		// 各种校验
		// validateDiscount2hb(userId, month);
		// 查询对应月份已经使用过的折扣记录数
		int hasConvertCount = commonPrizeService.getIsUsedDiscountCount(userId, monthYearWithGB);
		Boolean hasConvert = hasConvertCount > 0;
		// 查询最低折扣的中奖信息
		// PrizeRecord prizeRecord=
		// redenvelopeDao.selectLastDiscountInfoByMonth(userId, yearMonth);
		
		Long moneyLong = null;
		PrizeRecordEntity prizeRecord = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId, monthYearWithGB);
		if (prizeRecord == null || prizeRecord.getDiscount() == null||prizeRecord.hasUsed()==null||prizeRecord.hasUsed()) {
			if(prizeRecord != null&&prizeRecord.getDiscount()!= null){//已经使用的也返回可兑换金额
				moneyLong = discount2hbRule.getMoneyByDiscount(userId,prizeRecord.getDiscount());
			}
		}else{
			moneyLong = discount2hbRule.getMoneyByDiscount(userId,prizeRecord.getDiscount());
		}
		
		Integer unConvertAbleReasonFlag = validateDiscount2hb(userId, monthYearWithGB, false);
		Boolean isConvertAble = null;
		if(RedenvelopeDict.Redenvelope_UnConvert_Reason.SUCCESS.compareTo(unConvertAbleReasonFlag)==0&&prizeRecord!=null){
			isConvertAble = true;
			try {//syl-add 2015-11-9 15:12:22 增加折扣有效期的校验
				String pre2MonthStartDay = commonPrizeService.getPre2MonthStartDay(userId);
				if(prizeRecord.getPrizeTime()==null||DateUtil.formatSecond.get().parse(prizeRecord.getPrizeTime()).getTime()<DateUtil.formatDay.get().parse(pre2MonthStartDay).getTime()){
					unConvertAbleReasonFlag = RedenvelopeDict.Redenvelope_UnConvert_Reason.IS_TIME_OUT;
					isConvertAble = false;
				}
			} catch (ParseException e) {
				unConvertAbleReasonFlag = RedenvelopeDict.Redenvelope_UnConvert_Reason.IS_TIME_OUT;
				isConvertAble = false;
			}
		}else{
			isConvertAble = false;
		}
		
		SimpleHbDiscountEntity simpleHbEntity = null;
		{
			Integer usedType = prizeRecord==null?null:prizeRecord.getUsedType();
			simpleHbEntity  = new SimpleHbDiscountEntity(hasConvert, prizeRecord, moneyLong,
					isConvertAble,unConvertAbleReasonFlag,usedType);
			Long disCountSavedMoney_bill = null;
			//如果是缴纳物业费，查询对应月份物业费账单信息
			if(prizeRecord!=null&&usedType!=null&&PrizeDict.PrizeRecord_UsedType.Plotproperty.compareTo(usedType)==0){
				boolean useDefaultIfNull = true;
				BigInteger billTypeId = plotpropertyCfgService.getPropPayBillTypeInfoByCondition(userId, null, useDefaultIfNull).getId();
				PayBillInfo payBillEntity = plotpropertyCfgService.getPayBillSimpleInfoByMonth(userId, monthYearWithGB,billTypeId);
				
//				List<PayBillEntity> tmpPayBillEntityList = plotpropertyDao.selectPayBillByMonth(userId, monthYearWithGB.getBillMonthStr(DateUtil.formatMonth.get()));
//				if(tmpPayBillEntityList!=null&tmpPayBillEntityList.size()==1){
//					payBillEntity = tmpPayBillEntityList.get(0);
//				}
				
				if(payBillEntity!=null&&payBillEntity.getIsPay()!=null&&payBillEntity.getIsPay().compareTo(PlotpropertyDict.PayBill_IsPay.TRUE)==0
						&&payBillEntity.getDiscount()!=null
						){//已缴费 且使用了折扣 
					if(payBillEntity.getAmount()!=null&&payBillEntity.getSuccPayAmount()!=null){//计算优惠的金额
						disCountSavedMoney_bill = payBillEntity.getAmount()-payBillEntity.getSuccPayAmount();
					}
				}
			}
			simpleHbEntity.setDisCountSavedMoney_bill(disCountSavedMoney_bill);
		}
		return simpleHbEntity;
	}

//	@Override 2014-8-28 14:47:27 del 最终
//	public Integer getLeftDay2Convert(BigInteger userId, String yearMonth) {
//		// 查询当前时间
//		String nowTime = dualDao.getNowTime();
//		Calendar nowTimeMonth = GregorianCalendar.getInstance();
//		try {
//			nowTimeMonth.setTime(DateUtil.formatSecond.get().parse(nowTime));
//		} catch (ParseException e) {
//			logger.debug("RedenvelopeService time parse cause error,nowTime is :" + nowTime + ",nowTime is :" + nowTime, e);
//			throw new BusinessRuntimeException("RedenvelopeService.getLeftDay2Convert.validateNowTime.timeParse.error", e);
//		}
//		Calendar yearMonthCalendar = GregorianCalendar.getInstance();
//		try {
//			yearMonthCalendar.setTime(DateUtil.formatMonth.get().parse(yearMonth));
//		} catch (ParseException e) {
//			logger.debug("RedenvelopeService time parse cause error,yearMonth is :" + yearMonth + ",yearMonth is :"
//					+ yearMonth, e);
//		}
//
//		// int nowMonth = nowTimeMonth.get(Calendar.MONTH)+1;//当月月份[1,12]
//		int yearMonth_Month = yearMonthCalendar.get(Calendar.MONTH) + 1;// 当月月份[1,12]
//		// 查询剩余几天即可兑换
//		Integer leftDay2Convert = null;
//		// //查询上个月总可用折扣数
//		// int preTotalCount = commonPrizeService.getDiscountTotalCount(userId,
//		// ""+nowTimeMonth.get(Calendar.YEAR)+((nowMonth-1)>9?(nowMonth-1):"0"+(nowMonth-1)));
//		// 查询当月总可用折扣数
//		// int nowDiscountCount = commonPrizeService.getDiscountTotalCount(userId,
//		// ""+nowTimeMonth.get(Calendar.YEAR)+((nowMonth)>9?(nowMonth):"0"+(nowMonth)));
//
//		// int nowDiscountCount = commonPrizeService.getDiscountTotalCount(userId,
//		// ""+yearMonthCalendar.get(Calendar.YEAR)+((yearMonth_Month)>9?(yearMonth_Month):"0"+(yearMonth_Month)));
//		Boolean nowMonthHasDiscount = false;
//		{
//			String yearStr = DateUtil.formateYear(yearMonthCalendar.get(Calendar.YEAR));
//			String monthStr = DateUtil.formateMonth(yearMonth_Month);
//			PrizeRecordSimpleEntity prizeRecordSimpleEntity = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId, yearStr
//					+ monthStr);
//			if (prizeRecordSimpleEntity != null && !prizeRecordSimpleEntity.isUsed()) {
//				nowMonthHasDiscount = true;
//			}
//		}
//
//		// 查询对应月份已经使用过的折扣记录数
//		int convertCount = commonPrizeService.getIsUsedDiscountCount(userId, yearMonth);
//		// 上个月没折扣，或者上个月已经兑换==>本月必须有折扣
//		if (/* (preTotalCount<=0||preMonthConvertCount>0)&& */(nowMonthHasDiscount)) {
//			if (convertCount > 0 && nowMonthHasDiscount) {// 对应月份已经兑换，且当前时间有折扣
//				// 查询当前时间到下月1号的天数
//			} else if (convertCount <= 0 && yearMonthCalendar.get(Calendar.YEAR) == nowTimeMonth.get(Calendar.YEAR)
//					&& yearMonthCalendar.get(Calendar.MONTH) == nowTimeMonth.get(Calendar.MONTH)) {// 未兑换 且为当月
//				leftDay2Convert = nowTimeMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
//						- nowTimeMonth.get(Calendar.DAY_OF_MONTH) + 1;
//			}
//		}
//		return leftDay2Convert;
//	}

	// @Override 2014-8-20  del
	// public Integer getLeftDay2Convert(BigInteger userId){
	// //查询剩余几天即可兑换
	// Integer leftDay2Convert = null;
	// {
	// //查询当前时间
	// String nowTime = dualDao.getNowTime();
	// Calendar nowTimeMonth = GregorianCalendar.getInstance();
	// try {
	// nowTimeMonth.setTime(DateUtil.formatSecond.parse(nowTime));
	// } catch (ParseException e) {
	// logger.debug("RedenvelopeService time parse cause error,nowTime is :" +
	// nowTime + ",nowTime is :" + nowTime, e);
	// throw new
	// BusinessRuntimeException("RedenvelopeService.getLeftDay2Convert.validateNowTime.timeParse.error",
	// e);
	// }
	// int nowMonth = nowTimeMonth.get(Calendar.MONTH)+1;//当月月份[1,12]
	// {
	// //查询上个月总可用折扣数
	// int preTotalCount = commonPrizeService.getDiscountTotalCount(userId,
	// ""+nowTimeMonth.get(Calendar.YEAR)+((nowMonth-1)>9?(nowMonth-1):"0"+(nowMonth-1)));
	// //查询当月总可用折扣数
	// int nowDiscountCount = commonPrizeService.getDiscountTotalCount(userId,
	// ""+nowTimeMonth.get(Calendar.YEAR)+((nowMonth)>9?(nowMonth):"0"+(nowMonth)));
	// //查询上个月已经兑换折扣数
	// int preMonthConvertCount = commonPrizeService.getIsUsedDiscountCount(userId,
	// ""+nowTimeMonth.get(Calendar.YEAR)+((nowMonth-1)>9?(nowMonth-1):"0"+(nowMonth-1)));
	// if((preTotalCount<=0||preMonthConvertCount>0)&&(nowDiscountCount>0)){//上个月没折扣，或者
	// 上个月已经兑换 ==>本月必须有折扣
	// //查询当前月是否有折扣
	// if(nowDiscountCount>0){
	// //查询当前时间到下月1号的天数
	// leftDay2Convert =
	// nowTimeMonth.getActualMaximum(Calendar.DAY_OF_MONTH)-nowTimeMonth.get(Calendar.DAY_OF_MONTH)+1;
	// }
	// }
	// }
	//
	// }
	// return leftDay2Convert;
	// }

	// public static void main(String[] args) throws ParseException {
	// String nowTime = "2014-12-34 11:22:33";
	// Calendar nowTimeMonth = GregorianCalendar.getInstance();
	// nowTimeMonth.setTime(DateUtil.formatSecond.parse(nowTime));
	// System.out.println(nowTimeMonth.get(Calendar.YEAR));
	// System.out.println(nowTimeMonth.get(Calendar.MONTH));
	// int d = nowTimeMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
	// int now = nowTimeMonth.get(Calendar.DAY_OF_MONTH);
	// System.out.println(nowTimeMonth.getActualMaximum(Calendar.DAY_OF_MONTH)-nowTimeMonth.get(Calendar.DAY_OF_MONTH)+1);
	// }

	@Override
	public SimpleHbDiscountEntity doDiscount2hb(BigInteger userId,IBusinessMonthYear monthYearWithGB) {
		// 各种校验
		Integer valiRes = validateDiscount2hb(userId, monthYearWithGB,true);
		if(RedenvelopeDict.Redenvelope_UnConvert_Reason.SUCCESS.compareTo(valiRes)!=0){
			throw new BusinessRuntimeException(RedenvelopeDict.Redenvelope_UnConvert_Reason.getMsgKey(valiRes));
		}
		// 查询最低折扣的中奖记录信息
		// PrizeRecordEntity prizeRecord=redenvelopeDao.selectLastDiscountInfoByMonth(userId, month);
		PrizeRecordEntity prizeRecord = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId, monthYearWithGB);
		if (prizeRecord == null) {
			throw new BusinessRuntimeException("RedenvelopeService.doDiscount2hb.leastPrizeRecord.isNull");
		}
		
		try {//syl-add 2015-11-9 15:12:22 增加折扣有效期的校验
			String pre2MonthStartDay = commonPrizeService.getPre2MonthStartDay(userId);
			if(prizeRecord.getPrizeTime()==null||DateUtil.formatSecond.get().parse(prizeRecord.getPrizeTime()).getTime()<DateUtil.formatDay.get().parse(pre2MonthStartDay).getTime()){
				throw new BusinessRuntimeException("RedenvelopeService.discount.timeOut");
			}
		} catch (ParseException e) {
			throw new BusinessRuntimeException("RedenvelopeService.discount.timeOut");
		}
		
		// 转换成金额
		Long moneyLong = discount2hbRule.getMoneyByDiscount(userId,prizeRecord.getDiscount());
		
		// 兑换成粮票
		//syl-del 2015-4-7 16:51:20
//		{
//			BigInteger payRedEnvelopeAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_red_envelope);
//			PayRedEnvelope payRedEnvelope = new PayRedEnvelope();
//			payRedEnvelope.setAmountAvaible(moneyLong);
//			payRedEnvelope.setAmountTotal(moneyLong);
//			payRedEnvelope.setFromId(prizeRecord.getId());
//			payRedEnvelope.setFromTime(dualDao.getNowTime());
//			payRedEnvelope.setFromType(RedenvelopeDict.PayRedEnvelope_FromType.PRIZE_RECORD);// 抽奖记录
//			payRedEnvelope.setId(payRedEnvelopeAddId);
//			payRedEnvelope.setOutDate(null);// 不失效
//			payRedEnvelope.setStatus(RedenvelopeDict.PayRedEnvelope_Status.CAN_USE);
//			payRedEnvelope.setUserId(userId);// 拥有者
//			payRedEnvelope.setConverterId(userId);// 兑换人
//			int res = payRedEnvelopeBaseDao.insertPayRedEnvelope(payRedEnvelope);
//			if (res <= 0) {
//				throw new BusinessRuntimeException("RedenvelopeService.doDiscount2hb.insertPayRedEnvelope.failed");
//			}
//		}
		//syl-upd 2015-4-7 16:51:12
		BigInteger userDefaultRoomId = commonRoomService.getDefaultRoomIdByUserId(userId);//查询当前用户默认的门牌Id
		commonRedenvelopeService.addPayRedEnvelope(userId, userDefaultRoomId, prizeRecord.getId(), RedenvelopeDict.PayRedEnvelope_FromType.PRIZE_RECORD, moneyLong);
		
		// 对应中奖记录标记为已使用
		{
			Integer res = commonPrizeService.markDiscountAsUsed(prizeRecord.getId(),PrizeDict.PrizeRecord_UsedType.Redenvelope,moneyLong);
			if (res==null||res <= 0) {
				throw new BusinessRuntimeException("RedenvelopeService.doDiscount2hb.updatePrizeRecord.failed");
			}
		}
		// 返回粮票的金额
		PrizeRecordEntity prizeRecordFresh = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId, monthYearWithGB);
		SimpleHbDiscountEntity simpleHbEntity = new SimpleHbDiscountEntity(null, prizeRecord, moneyLong, null,null,prizeRecordFresh.getUsedType());
		return simpleHbEntity;
	}

	/**
	 * 校验折扣兑粮票所需的条件
	 * @param userId
	 * @param month
	 * @param isConvertAction true:表示当前操作是兑换粮票，
	 * 则用户当前门牌有可能会被添加首次使用粮票的标识
	 * false表示只是作查询
	 * return 返回0表示校验成功，其他则表示错误原因
	 */
	private Integer validateDiscount2hb(BigInteger userId,IBusinessMonthYear monthYearWithGB,boolean isConvertAction) {
		//查询用户默认门牌信息
		RoomEntityWithValidate userDefaultRoomEntity = commonRoomService.getDefaultRoomInfo(userId);
		if(userDefaultRoomEntity==null||userDefaultRoomEntity.checkIsEmptyRoom()){
			return RedenvelopeDict.Redenvelope_UnConvert_Reason.DEFAULT_ROOM_EMPTY;
		}
		
		//syl-del-2014-12-23 17:10:04 只有门牌验证通过就可以兑换粮票
//		//用户是否为门牌的创建者 只有创建者才能兑换粮票
//		if(userId.compareTo(userDefaultRoomEntity.getCreater())!=0){
//			return RedenvelopeDict.Redenvelope_UnConvert_Reason.NOT_ROOM_CREATER;
//		}
		
		//syl-add-2014-12-23 10:07:19 start
		if(userDefaultRoomEntity.checkIsValidedSuccess()){
//			if(!userDefaultRoomEntity.getRealRoomEntity().checkIsValidated()){
//				commonRoomService.checkRealRoomFirstValidateAndUpd(userDefaultRoomEntity.gettRealRoomFId(), true);
//			}
			if(userDefaultRoomEntity.getRealRoomEntity().getValidateUserId().compareTo(userId)!=0){//需要管理员才可以兑换粮票
				return RedenvelopeDict.Redenvelope_UnConvert_Reason.MUST_BE_ADMIN;
			}else{//必须是两个月之内的粮票才可以兑换
				
			}
		}else{
			return RedenvelopeDict.Redenvelope_UnConvert_Reason.ROOM_NEED_VALIDATE;
		}
		//syl-add-2014-12-23 10:07:19 end
		
		//syl-del-2014-12-23 10:07:19 start
//		//校验用户当前门牌是否已经被验证
//		if(userDefaultRoomEntity.checkIsValidedSuccess()||userDefaultRoomEntity.checkIsFirstHbconvertState()){
//			//已经校验了或者不需要校验
//		}else{
//			//查询当前用户创建的门牌的被标识为首次使用粮票的门牌数
//			int hbconvertStateRoomCount = commonRoomService.getFirstHbconvertStateRoomCount(userId);
//			if(hbconvertStateRoomCount<=0){
//				if(isConvertAction){
//					//设定用户当前门牌为首次使用粮票的标识
//					int res = commonRoomService.setRoomAsFirstHbconvertState(userId);
//					if(res<=0){
//						throw new BusinessRuntimeException("RedenvelopeService.validateDiscount2hb.setFirstHbconvertState.failed");
//					}
//				}
//			}else{//门牌没有校验，且用户已经有其他门牌被设置首次使用粮票标识
//				return RedenvelopeDict.Redenvelope_UnConvert_Reason.ROOM_NEED_VALIDATE;
//			}
//		}
		//syl-del-2014-12-23 10:07:19 end

//		// 校验月份是否合理
//		String nowTime = dualDao.getNowTime();
//		try {
//			Calendar srcMonth = GregorianCalendar.getInstance();
//			srcMonth.setTime(DateUtil.formatMonth.get().parse(month));
//			Calendar nowTimeMonth = GregorianCalendar.getInstance();
//			nowTimeMonth.setTime(DateUtil.formatSecond.get().parse(nowTime));
//			if (nowTimeMonth.get(Calendar.YEAR) * 12 + nowTimeMonth.get(Calendar.MONTH) > srcMonth.get(Calendar.YEAR) * 12
//					+ srcMonth.get(Calendar.MONTH)) {
//				// 时间不能超过上个月
//			} else {
//				return RedenvelopeDict.Redenvelope_UnConvert_Reason.NOT_AVAILABLE_TIME;
//			}
//		} catch (ParseException e) {
//			logger.debug("RedenvelopeService time parse cause error,month is :" + month + ",nowTime is :" + nowTime, e);
//			throw new BusinessRuntimeException("RedenvelopeService.getLastDiscountByMonth.validateMonth.timeParse.error", e);
//		}
		
		// 查询对应月份已经使用过的折扣记录数
		int convertCount = commonPrizeService.getIsUsedDiscountCount(userId, monthYearWithGB);
		if (convertCount > 0) {
			return RedenvelopeDict.Redenvelope_UnConvert_Reason.IS_USED;
		}
		
		return RedenvelopeDict.Redenvelope_UnConvert_Reason.SUCCESS;
	}

	@Override
	public SimpleHbBalanceEntity getAllBalanceCollectInfo(BigInteger userId, int type) {
		// 查询总金额
		Long balance = commonRedenvelopeService.getTotalHbBalance(userId, type);
		// 查询总兑换金额
		Long totalConvertMoney = redenvelopeDao.selectTotalConvertMoney(userId);
		// 查询总消费金额
		Long totalConsumMoney = redenvelopeDao.selectTotalConsumMoney(userId);
		SimpleHbBalanceEntity simpleHbBalanceEntity = new SimpleHbBalanceEntity(balance, totalConvertMoney,
				totalConsumMoney);
		return simpleHbBalanceEntity;
	}

	@Override
	public List<PayRedEnvelopeEntity> getRecord2hbRecord(BigInteger userId) {
		return redenvelopeDao.selectRecord2hbRecord(userId);
	}

	@Override
	public List<PayRedEnvelopeEntity> getRecord2hbRecord(BigInteger userId, int type, PageModel pageModel) {
		return redenvelopeDao.selectRecord2hbRecord(userId,type, pageModel);
	}

	@Override
	public List<PayRedEnvelopeDetailEntity> getHbConsumRecord(BigInteger userId) {
		return redenvelopeDao.selectHbConsumRecord(userId);
	}

	public List<PayRedEnvelopeDetailEntity> getHbConsumRecord(BigInteger userId, int type, PageModel pageModel) {
		return redenvelopeDao.selectHbConsumRecord(userId, type, pageModel);
	}

	@Override
	public List<HbConvertConsumEntity> getHbConvertConsumBothRecord(BigInteger userId) {
		return redenvelopeDao.selectHbConvertConsumBothRecord(userId);
	}

	@Override
	public List<HbConvertConsumEntity> getHbConvertConsumBothRecord(BigInteger userId, PageModel pageModel) {
		return redenvelopeDao.selectHbConvertConsumBothRecord(userId, pageModel);
	}

//	@Override
//	public List<HbConvertRuleDescEntity> getHbConvertRule() {
//		List<HbConvertRuleDescEntity> resList = new ArrayList<HbConvertRuleDescEntity>();
//		for(long i=0;i<10;i++){
//			DiscountValueEntity startDiscount = new DiscountValueEntity(i*10, 10L);
//			DiscountValueEntity endDiscount = new DiscountValueEntity(i*10+9, 10L);
//			HbConvertRuleDescEntity hbConvertRuleDescEntity = new HbConvertRuleDescEntity(startDiscount, endDiscount);
//			resList.add(hbConvertRuleDescEntity);
//		}
//		return resList;
//	}
	@Override
	public List<HbConvertRuleDescEntity> getHbConvertRule() {
		List<HbConvertRuleDescEntity> resList = new ArrayList<HbConvertRuleDescEntity>();
		{//0-1
			DiscountValueEntity startDiscount = new DiscountValueEntity(0*10L, 10L);
			DiscountValueEntity endDiscount = new DiscountValueEntity(9*10L, 100L);
			HbConvertRuleDescEntity hbConvertRuleDescEntity = new HbConvertRuleDescEntity(startDiscount, endDiscount);
			resList.add(hbConvertRuleDescEntity);
		}
		{//1-5
			DiscountValueEntity startDiscount = new DiscountValueEntity(1*10L, 10L);
			DiscountValueEntity endDiscount = new DiscountValueEntity(49*10L, 100L);
			HbConvertRuleDescEntity hbConvertRuleDescEntity = new HbConvertRuleDescEntity(startDiscount, endDiscount);
			resList.add(hbConvertRuleDescEntity);
		}
		{//5-8
			DiscountValueEntity startDiscount = new DiscountValueEntity(5*10L, 10L);
			DiscountValueEntity endDiscount = new DiscountValueEntity(79*10L, 100L);
			HbConvertRuleDescEntity hbConvertRuleDescEntity = new HbConvertRuleDescEntity(startDiscount, endDiscount);
			resList.add(hbConvertRuleDescEntity);
		}
		{//8-9
			DiscountValueEntity startDiscount = new DiscountValueEntity(8*10L, 10L);
			DiscountValueEntity endDiscount = new DiscountValueEntity(89*10L, 100L);
			HbConvertRuleDescEntity hbConvertRuleDescEntity = new HbConvertRuleDescEntity(startDiscount, endDiscount);
			resList.add(hbConvertRuleDescEntity);
		}
		{//9-10
			DiscountValueEntity startDiscount = new DiscountValueEntity(9*10L, 10L);
			DiscountValueEntity endDiscount = new DiscountValueEntity(99*10L, 100L);
			HbConvertRuleDescEntity hbConvertRuleDescEntity = new HbConvertRuleDescEntity(startDiscount, endDiscount);
			resList.add(hbConvertRuleDescEntity);
		}
		return resList;
	}

	@Override
	public void appendShenmaHb(BigInteger userId, BigInteger roomId, BigInteger fromId, Long bonus) {
		commonRedenvelopeService.addPayRedEnvelope(userId, roomId, fromId, RedenvelopeDict.PayRedEnvelope_FromType.SM_ACTION, bonus);
	}

	@Override
	public List<SimpleHbBalanceEntity> getHbBalanceEntityList(BigInteger userId) {
		List<SimpleHbBalanceEntity> simpleHbBalanceEntityList = new ArrayList<SimpleHbBalanceEntity>();
		long totalHBBalance1 = commonRedenvelopeService.getTotalHbBalance(userId, 0);
		SimpleHbBalanceEntity simpleHbBalanceEntity1 = new SimpleHbBalanceEntity(totalHBBalance1, 0L, 0L);
		simpleHbBalanceEntity1.setSubject("平台粮票金额");
		simpleHbBalanceEntity1.setDesc("全平台通用");
		simpleHbBalanceEntity1.setType(0);
		simpleHbBalanceEntityList.add(simpleHbBalanceEntity1);


		PayRedEnvelope preQry = new PayRedEnvelope();
		preQry.setFromType(RedenvelopeDict.PayRedEnvelope_FromType.Experience_Store);
		preQry.setUserId(userId);
		PayRedEnvelopeBaseDao payRedEnvelopeBaseDao = (PayRedEnvelopeBaseDao) CnfantasiaCommbusiUtil.getBeanManager("payRedEnvelopeBaseDao");
		if(payRedEnvelopeBaseDao.selectPayRedEnvelopeCount(MapConverter.toMap(preQry), false)>0) {
			//有体验店充值记录的才需要返回给接口
			Long totalHBBalance2 = commonRedenvelopeService.getTotalHbBalance(userId, 1);
			SimpleHbBalanceEntity simpleHbBalanceEntity2 = new SimpleHbBalanceEntity(totalHBBalance2, 0L, 0L);
			simpleHbBalanceEntity2.setSubject("体验店粮票金额");
			simpleHbBalanceEntity2.setDesc("限解放区优选体验店（园景园名苑）使用");
			simpleHbBalanceEntity2.setType(1);
			simpleHbBalanceEntityList.add(simpleHbBalanceEntity2);
		}

		return simpleHbBalanceEntityList;
	}

}
