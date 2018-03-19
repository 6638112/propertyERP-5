package com.cnfantasia.server.api.property.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.access.entity.CarBill;
import com.cnfantasia.server.api.access.entity.CarFeeType;
import com.cnfantasia.server.api.access.entity.CarPreferDto;
import com.cnfantasia.server.api.access.entity.CarPreferParam;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.entity.PayCarKeyOrderParam;
import com.cnfantasia.server.api.access.service.IAccessService;
import com.cnfantasia.server.api.access.service.MonthCarService;
import com.cnfantasia.server.api.access.service.ThirdCarFactory;
import com.cnfantasia.server.api.cashSqpayBt.dao.ICashSqpayBtDao;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRedenvelopeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.ebuy.util.OrderNoGenerator;
import com.cnfantasia.server.api.groupBuildingCycleCfg.constant.CycleCfgDict;
import com.cnfantasia.server.api.lateFee.calculateMethod.CalculateLateFeeByRoomImp;
import com.cnfantasia.server.api.lateFee.util.CalculateLateFeeRunnable;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.serviceUntran.ICommPayService;
import com.cnfantasia.server.api.payment.serviceUntran.IPayConfigService;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.entity.AlterCyclePayBillEntity;
import com.cnfantasia.server.api.plotproperty.entity.AlterCyclesEntity;
import com.cnfantasia.server.api.plotproperty.entity.AlterMonth2Bills;
import com.cnfantasia.server.api.plotproperty.entity.AlterPayBillDto;
import com.cnfantasia.server.api.plotproperty.entity.AlterPayBillEntity;
import com.cnfantasia.server.api.plotproperty.entity.PayBillEntity;
import com.cnfantasia.server.api.plotproperty.entity.PlotpropertyOrderEntity;
import com.cnfantasia.server.api.plotproperty.entity.PropertyAlterBillInfo;
import com.cnfantasia.server.api.plotproperty.entity.PropertyBillEntity;
import com.cnfantasia.server.api.plotproperty.entity.PropertyDetail;
import com.cnfantasia.server.api.plotproperty.entity.Propfee;
import com.cnfantasia.server.api.plotproperty.service.IPlotpropertyService;
import com.cnfantasia.server.api.property.constant.PropertyConstant;
import com.cnfantasia.server.api.property.dao.IPropertyDao;
import com.cnfantasia.server.api.property.dto.BillDetailDto;
import com.cnfantasia.server.api.property.dto.BillPayDto;
import com.cnfantasia.server.api.property.dto.CarBillTailDto;
import com.cnfantasia.server.api.property.dto.CarPaidBillDetailDto;
import com.cnfantasia.server.api.property.dto.CarUnPaidBillDetailDto;
import com.cnfantasia.server.api.property.dto.ConfirmBillInfoReq;
import com.cnfantasia.server.api.property.dto.ConfirmPayReq;
import com.cnfantasia.server.api.property.dto.ConfirmPayResultDto;
import com.cnfantasia.server.api.property.dto.FinanceDto;
import com.cnfantasia.server.api.property.dto.PaidBillDto;
import com.cnfantasia.server.api.property.dto.PaidBillInfoReq;
import com.cnfantasia.server.api.property.dto.PaidBillItemDto;
import com.cnfantasia.server.api.property.dto.PayAmtDto;
import com.cnfantasia.server.api.property.dto.PayAmtTmpDto;
import com.cnfantasia.server.api.property.dto.PayBillDetailDto;
import com.cnfantasia.server.api.property.dto.PayDetailDto;
import com.cnfantasia.server.api.property.dto.PreOrderDto;
import com.cnfantasia.server.api.property.dto.PreferAmtDto;
import com.cnfantasia.server.api.property.dto.PropertyBillTailDto;
import com.cnfantasia.server.api.property.dto.PropertyPaidBillDetailDto;
import com.cnfantasia.server.api.property.dto.PropertyUnPaidBillDetailDto;
import com.cnfantasia.server.api.property.dto.RemainBillDto;
import com.cnfantasia.server.api.property.dto.RemainBillInfoDto;
import com.cnfantasia.server.api.property.dto.RemainBillOtherInfoDto;
import com.cnfantasia.server.api.property.dto.UnPaidBillDetailReq;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redenvelope.entity.SimpleHbBalanceEntity;
import com.cnfantasia.server.api.redenvelope.service.IRedenvelopeService;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.alterPeriod.dao.IAlterPeriodDao;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.dao.IAlterPeriodDataDetailBaseDao;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity.AlterPeriodDataDetail;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumList.service.ICarNumListBaseService;
import com.cnfantasia.server.domainbase.carPrefer.entity.CarPrefer;
import com.cnfantasia.server.domainbase.carPrefer.service.ICarPreferBaseService;
import com.cnfantasia.server.domainbase.cashSqpayBt.entity.CashSqpayBt;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.dao.IEbuyOrderHasTPayBillBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity.EbuyOrderHasTPayBill;
import com.cnfantasia.server.domainbase.ebuyOrderRelation.dao.IEbuyOrderRelationBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderRelation.entity.EbuyOrderRelation;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.dao.IGroupBuildingBillCycleConfigBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;
import com.cnfantasia.server.domainbase.payBill.dao.IPayBillBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.payCoupon.dao.IPayCouponBaseDao;
import com.cnfantasia.server.domainbase.payCoupon.entity.PayCoupon;
import com.cnfantasia.server.domainbase.propertyAccountInfo.entity.PropertyAccountInfo;
import com.cnfantasia.server.domainbase.propertyAccountInfo.service.IPropertyAccountInfoBaseService;
import com.cnfantasia.server.domainbase.propertyFeeDetail.dao.IPropertyFeeDetailBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyManagement.service.IPropertyManagementBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.ms.payBill.constant.PropIconUtil;
import com.propertySoftwareDock.base.dao.IPropertySoftwareDockDao;
import com.propertySoftwareDock.base.entity.RealRoomSoftwareInfo;

import MoneyUtil.MoneyUtil;

/**
 * 物业缴费（version>=503）
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午11:03:53
 */
public class PropertyService implements IPropertyService {
	private final Logger logger = Logger.getLogger(getClass());

	private IPropertyDao propertyDao;
	private IPlotpropertyService plotpropertyService;
	private IPropertySoftwareDockDao propertySoftwareDockDao;
	private IPropertyAccountInfoBaseService propertyAccountInfoBaseService;
	private ICommonRoomService commonRoomService;
	private IPropertyManagementBaseService propertyManagementBaseService;
	private IAccessService accessService;
	private ICarNumListBaseService carNumListBaseService;
	private ICarPreferBaseService carPreferBaseService;
	private IAlterPeriodDao alterPeriodDao;
	private IRedenvelopeService redenvelopeService;
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	private IUuidManager uuidManager;
	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	private ICommonRedenvelopeService commonRedenvelopeService;
	private ICommPayService commPayService;
	private IPayCouponBaseDao payCouponBaseDao;
	private ICashSqpayBtDao cashSqpayBtDao;
	private IPayConfigService payConfigService;
	private IPayBillTypeBaseDao payBillTypeBaseDao;
	private IEbuyOrderRelationBaseDao ebuyOrderRelationBaseDao;
	private IAlterPeriodDataDetailBaseDao alterPeriodDataDetailBaseDao;
	private IPayBillBaseDao payBillBaseDao;
	private IGroupBuildingBillCycleConfigBaseDao groupBuildingBillCycleConfigBaseDao;
	private IPropertyFeeDetailBaseDao propertyFeeDetailBaseDao;
	private IEbuyOrderHasTPayBillBaseDao ebuyOrderHasTPayBillBaseDao;
	private MonthCarService monthCarService;
	private ThirdCarFactory thirdCarFactory;

	public void setPropertyDao(IPropertyDao propertyDao) {
		this.propertyDao = propertyDao;
	}

	public void setPlotpropertyService(IPlotpropertyService plotpropertyService) {
		this.plotpropertyService = plotpropertyService;
	}

	public void setPropertySoftwareDockDao(
			IPropertySoftwareDockDao propertySoftwareDockDao) {
		this.propertySoftwareDockDao = propertySoftwareDockDao;
	}

	public void setPropertyAccountInfoBaseService(
			IPropertyAccountInfoBaseService propertyAccountInfoBaseService) {
		this.propertyAccountInfoBaseService = propertyAccountInfoBaseService;
	}

	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	public void setPropertyManagementBaseService(
			IPropertyManagementBaseService propertyManagementBaseService) {
		this.propertyManagementBaseService = propertyManagementBaseService;
	}

	public void setAccessService(IAccessService accessService) {
		this.accessService = accessService;
	}

	public void setCarNumListBaseService(
			ICarNumListBaseService carNumListBaseService) {
		this.carNumListBaseService = carNumListBaseService;
	}

	public void setCarPreferBaseService(ICarPreferBaseService carPreferBaseService) {
		this.carPreferBaseService = carPreferBaseService;
	}

	public void setAlterPeriodDao(IAlterPeriodDao alterPeriodDao) {
		this.alterPeriodDao = alterPeriodDao;
	}

	public void setRedenvelopeService(IRedenvelopeService redenvelopeService) {
		this.redenvelopeService = redenvelopeService;
	}

	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
		this.ebuyOrderBaseDao = ebuyOrderBaseDao;
	}

	public void setCommonRedenvelopeService(
			ICommonRedenvelopeService commonRedenvelopeService) {
		this.commonRedenvelopeService = commonRedenvelopeService;
	}

	public void setCommPayService(ICommPayService commPayService) {
		this.commPayService = commPayService;
	}

	public void setPayCouponBaseDao(IPayCouponBaseDao payCouponBaseDao) {
		this.payCouponBaseDao = payCouponBaseDao;
	}

	public void setCashSqpayBtDao(ICashSqpayBtDao cashSqpayBtDao) {
		this.cashSqpayBtDao = cashSqpayBtDao;
	}
	
	public void setPayConfigService(IPayConfigService payConfigService) {
		this.payConfigService = payConfigService;
	}
	
	public void setPayBillTypeBaseDao(IPayBillTypeBaseDao payBillTypeBaseDao) {
		this.payBillTypeBaseDao = payBillTypeBaseDao;
	}

	public void setEbuyOrderRelationBaseDao(
			IEbuyOrderRelationBaseDao ebuyOrderRelationBaseDao) {
		this.ebuyOrderRelationBaseDao = ebuyOrderRelationBaseDao;
	}
	
	public void setAlterPeriodDataDetailBaseDao(
			IAlterPeriodDataDetailBaseDao alterPeriodDataDetailBaseDao) {
		this.alterPeriodDataDetailBaseDao = alterPeriodDataDetailBaseDao;
	}
	
	public void setPayBillBaseDao(IPayBillBaseDao payBillBaseDao) {
		this.payBillBaseDao = payBillBaseDao;
	}
    
	public void setGroupBuildingBillCycleConfigBaseDao(
			IGroupBuildingBillCycleConfigBaseDao groupBuildingBillCycleConfigBaseDao) {
		this.groupBuildingBillCycleConfigBaseDao = groupBuildingBillCycleConfigBaseDao;
	}

	public void setPropertyFeeDetailBaseDao(IPropertyFeeDetailBaseDao propertyFeeDetailBaseDao) {
		this.propertyFeeDetailBaseDao = propertyFeeDetailBaseDao;
	}

	public void setEbuyOrderHasTPayBillBaseDao(IEbuyOrderHasTPayBillBaseDao ebuyOrderHasTPayBillBaseDao) {
		this.ebuyOrderHasTPayBillBaseDao = ebuyOrderHasTPayBillBaseDao;
	}

	public void setMonthCarService(MonthCarService monthCarService) {
		this.monthCarService = monthCarService;
	}

	public void setThirdCarFactory(ThirdCarFactory thirdCarFactory) {
		this.thirdCarFactory = thirdCarFactory;
	}

	/**
	 * 获取待缴账单信息
	 * @return
	 */
	@Override
	public RemainBillInfoDto getRemainBillInfo(BigInteger userId, String sessionId, BigInteger gbId){
		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
		BigInteger realRoomId = realRoom.getId();
		BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		RemainBillInfoDto remainBillInfo = new RemainBillInfoDto();
		FinanceDto finance = getFinanceInfo(userId, realRoomId);
		remainBillInfo.setFinance(finance);
		
		RemainBillOtherInfoDto otherInfo = getRemainBillOtherInfoDto(userId, realRoom, roomId);
		remainBillInfo.setOtherInfo(otherInfo);
		
		List<String> carMonths = new ArrayList<String>();
		carMonths.add("1");
		carMonths.add("3");
		carMonths.add("6");
		carMonths.add("12");
		otherInfo.setCarMonths(carMonths);
		
		
		List<String> periodMonths = otherInfo.getPeriodMonths();
		String firstMonth = null;
		if(periodMonths!=null && periodMonths.size()>0){
			firstMonth = periodMonths.get(0);
		}
		List<RemainBillDto> allBillList = getRemainBills(userId, realRoomId, firstMonth, groupBuilding);
		List<RemainBillDto> rechargeBills = getRechargeBills(groupBuilding.getId());
		if(rechargeBills!=null) {
			allBillList.addAll(rechargeBills);
		}
		allBillList.addAll(getRemainCarBills(userId, sessionId, gbId));
		
		boolean show = (rechargeBills == null || rechargeBills.size() == 0);
		if(!show) {
			finance.setOpenFinance(false);
			otherInfo.setOpenService(false);
		}
		
		//固定周期账单
		remainBillInfo.setRemainBills(allBillList);
		//选择周期账单
		if(groupBuilding.getPropertyPeriodType() != null && groupBuilding.getPropertyPeriodType().equals(2)) {//选择周期缴费
			remainBillInfo.setAlterPayBillDtos(getAlterBills(userId, realRoomId, firstMonth, groupBuilding));
		}

		return remainBillInfo;
	}
	
	/**
	 * 获取确认账单信息
	 * @param userId
	 * @param confirmBillInfoReqs
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public PayAmtDto getConfirmBillInfo(BigInteger userId, List<ConfirmBillInfoReq> confirmBillInfoReqs, String sessionId){
		// 滞纳金计算需要的RoomId:通过账单ID去查询房间ID：RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);在切换门牌时取不到值
		BigInteger realRoomId = null;

		BigDecimal totalAmt = BigDecimal.ZERO;// 总金额
		BigDecimal amtBt = BigDecimal.ZERO;// 补贴
		BigDecimal deduAmt = BigDecimal.ZERO;// 物业宝抵扣
		
		for(ConfirmBillInfoReq cbi : confirmBillInfoReqs){
			PayAmtTmpDto payAmtTmp = null;
			if(StringUtils.isNotBlank(cbi.getCarNum())){// 车禁
				payAmtTmp = getPayCarPrefer(cbi.getCarNum(), Integer.valueOf(cbi.getPayMonth()), sessionId);
			} else if(cbi.getGbbccId()!=null) {//物业预存
				payAmtTmp = getPropertyRechargePerferAmt(userId, cbi.getGbbccId(), cbi.getRechargeAmt());
			} else if(cbi.getPayBillId()==null && StringUtils.isNotBlank(cbi.getPayMonth()) && PropertyConstant.DataFromType.Jfq.equals(cbi.getDataFromType())){// 选择性周期
				RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
				boolean isOpenPropertyPrefer = isOpenPropertyPrefer(userId);
				payAmtTmp = getPayAlterPeriodPrefer(userId, realRoom.getId(), cbi.getPayMonth(), isOpenPropertyPrefer);
			} else {// 非选择周期缴费
				payAmtTmp = getPayUnalterPeriodPrefer(userId, cbi.getPayBillId(), cbi.getDataFromType());
				//查询账单门牌滞纳金
				if(realRoomId == null) {
                    PayBill payBill = payBillBaseDao.selectPayBillBySeqId(cbi.getPayBillId());
                    realRoomId = payBill.gettRealRoomFId();
                }
			}
			logger.debug(JSON.toJSON(cbi)+"==>"+JSON.toJSON(payAmtTmp));
			
			totalAmt = totalAmt.add(payAmtTmp.getTotalAmt());
			amtBt = amtBt.add(payAmtTmp.getAmtBt());
			deduAmt = deduAmt.add(payAmtTmp.getDeduAmt());
		}
		
		PayAmtDto payAmt = new PayAmtDto();
		payAmt.setRealAmt(BigDecimalUtil.div100(totalAmt.subtract(amtBt).subtract(deduAmt)).toString());
		payAmt.setTotalAmt(BigDecimalUtil.div100(totalAmt).toString());
		payAmt.setAmtBt(BigDecimalUtil.div100(amtBt).toString());
		Double hbPercent = payConfigService.getPayConfigHbPercent(EbuyDict.EbuyOrder_Type.Property_Bill);
		payAmt.setHbPercent(hbPercent);
		
		if(amtBt.doubleValue()==0 && deduAmt.doubleValue()==0){
			payAmt.setCouponDesc(StringUtils.EMPTY);
		} else if(amtBt.doubleValue()==0 && deduAmt.doubleValue()>0){
			payAmt.setCouponDesc("物业宝抵扣 "+BigDecimalUtil.div100(deduAmt)+"元");
		} else if(amtBt.doubleValue()>0 && deduAmt.doubleValue()==0){
			payAmt.setCouponDesc("随机立减优惠 "+BigDecimalUtil.div100(amtBt)+"元");
		} else {
			payAmt.setCouponDesc("随机立减优惠 "+BigDecimalUtil.div100(amtBt)+"元\n物业宝抵扣 "+BigDecimalUtil.div100(deduAmt)+"元");
		}
		
		logger.debug("payAmt==>"+JSON.toJSON(payAmt));

		//更新滞纳金
        if(realRoomId != null) {
            CalculateLateFeeByRoomImp calculateLateFeeByRoomImp = (CalculateLateFeeByRoomImp) CnfantasiaCommbusiUtil.getBeanManager("calculateLateFeeByRoomImp");
            FutureTask<Boolean> booleanFutureTask = new FutureTask<>(new CalculateLateFeeRunnable(calculateLateFeeByRoomImp, realRoomId, userId));
            booleanFutureTask.run();
        }

		return payAmt;
	}
	
	/**
	 * 物业缴费充值各种金额（实付、优惠、总金额）获取
	 * @param userId
	 * @param gbbccId
	 * @param rechargeAmt
	 * @return
	 */
	private PayAmtTmpDto getPropertyRechargePerferAmt(BigInteger userId, BigInteger gbbccId, long rechargeAmt) {
		checkRecharge(gbbccId, rechargeAmt);
		
		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
		System.err.println(gbbccId+"==>"+userId+"==>"+realRoom.getId()+"==>"+rechargeAmt);
		long perferAmt = propertyDao.selectPropertyRechargePerfer(gbbccId, userId, realRoom.getId(), rechargeAmt);
		PayAmtTmpDto payAmtTmp = new PayAmtTmpDto();
		payAmtTmp.setAmtBt(BigDecimal.valueOf(perferAmt));
		payAmtTmp.setTotalAmt(BigDecimal.valueOf(rechargeAmt));
		payAmtTmp.setDeduAmt(BigDecimal.valueOf(0));
		payAmtTmp.setRealAmt(BigDecimal.valueOf(rechargeAmt-perferAmt));
		
		return payAmtTmp;
	}
	
	private static final String getRechargeDay(int day, int mode) {
		Date now = new Date();
		if(mode==1) {
			now = DateUtils.addMonths(now, mode);
		}
		String startDateStr = DateUtils.formatYearAndMonth(now);
		int startDateLastDay = Integer.valueOf(DateUtils.getLastDayOfMonth(now, "dd"));
		String startDayStr = StringUtils.EMPTY;
		if(day>startDateLastDay) {
			startDayStr = String.valueOf(startDateLastDay);
		} else {
			if(day<10) {
				startDayStr = "0" + day;
			} else {
				startDayStr = String.valueOf(day);
			}
		}
		startDateStr = startDateStr + "-" + startDayStr;
		
		return startDateStr;
	}
	
	private boolean isCanPay(GroupBuildingBillCycleConfig gbbcc) {
		DateTime dateTime = new DateTime(new Date());
		int nowDay = dateTime.dayOfMonth().get();

		if (gbbcc.getRechargeMonthMode() == 0) {
			return (gbbcc.getStartDay() <= nowDay && gbbcc.getEndDay() >= nowDay);
		} else if (gbbcc.getRechargeMonthMode() == 1) {
			return (gbbcc.getStartDay() <= nowDay || gbbcc.getEndDay() >= nowDay);
		}
		return false;
	}
	
	private void checkRecharge(BigInteger gbbccId, long rechargeAmt) {
		GroupBuildingBillCycleConfig gbbcc = groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigBySeqId(gbbccId);
		if (!isCanPay(gbbcc)) {
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.VALIDATE_ERR);
			businessRuntimeException.setErrorMsg("【"+gbbcc.getBillName()+"】不在缴费时间，暂时不可支付！");
			throw businessRuntimeException;
		}

		Double minRecharge = gbbcc.getMinRecharge();
		Double maxRecharge = gbbcc.getMaxRecharge();
		if ((rechargeAmt < minRecharge) || (rechargeAmt > maxRecharge)) {
			String tips = StringUtils.EMPTY;
			if(maxRecharge==null) {
				tips = "【"+gbbcc.getBillName()+"】充值金额不能小于"+BigDecimalUtil.div100(BigDecimal.valueOf(minRecharge));
			} else {
				tips = "【"+gbbcc.getBillName()+"】充值区间："+BigDecimalUtil.div100(BigDecimal.valueOf(minRecharge))+"~"+BigDecimalUtil.div100(BigDecimal.valueOf(maxRecharge));
			}
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.VALIDATE_ERR);
			businessRuntimeException.setErrorMsg(tips);
			throw businessRuntimeException;
		}
	}
	
	/**
	 * 确认支付
	 * @param userId
	 * @param confirmPayReqs
	 * @param hbAmount 红包（单位：元）
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public ConfirmPayResultDto confirmPay(BigInteger userId, List<ConfirmPayReq> confirmPayReqs, BigDecimal hbAmount, String sessionId){
		logger.debug("confirmPay.params==>"+JSON.toJSON(confirmPayReqs)+";hbAmount==>"+hbAmount);
		if(hbAmount!=null && hbAmount.doubleValue() > 0){
			hbAmount = hbAmount.multiply(BigDecimal.valueOf(100L));// 将元转分
		}
		// 1、检查是否存在支付确认中的订单
		
		// 2、获取总红包分到每个账单所占的红包金额
		List<BigDecimal> subHbAmounts = getSubHbAmounts(userId, hbAmount, confirmPayReqs, sessionId);
		logger.debug("confirmPay.subHbAmounts==>"+JSON.toJSON(subHbAmounts));
		
		// 3、各部分订单生成
		Long subChannelId = HeaderManager.getSubChannelIdLong();
		BigDecimal totalRealAmt = BigDecimal.ZERO;
		BigDecimal totalAmtBt = BigDecimal.ZERO;
		List<EbuyOrderRelation> ebuyOrderRelations =  new ArrayList<EbuyOrderRelation>();
		for(int i=0; i<confirmPayReqs.size(); i++){
			ConfirmPayReq cp = confirmPayReqs.get(i);
			PreOrderDto preOrder = null;
			Integer subType = null;
			if(StringUtils.isNotBlank(cp.getCarNum())){// 车禁
				preOrder = dealCarOrder(userId, cp.getCarNum(), cp.getPayMonth(), subHbAmounts.get(i), subChannelId, sessionId);
				
				subType = EbuyDict.EbuyOrder_Type.CarKey_Bill;
			} else if(cp.getGbbccId()!=null) {//物业预存
				String deviceId = HeaderManager.getDeviceId();
				preOrder = dealRecharge(userId, cp.getGbbccId(), cp.getRechargeAmt(), subHbAmounts.get(i).longValue(), deviceId, subChannelId);
				
				subType = EbuyDict.EbuyOrder_Type.Property_Bill;
			} else {// 物业缴费
				String deviceId = HeaderManager.getDeviceId();
				Integer dataFromType = StringUtils.isBlank(cp.getDataFromType()) ? 0: Integer.valueOf(cp.getDataFromType());
				Integer month = StringUtils.isBlank(cp.getPayMonth()) ? null : Integer.valueOf(cp.getPayMonth());
				preOrder = plotpropertyService.confirmBill(userId, cp.getPayBillId(), subChannelId, deviceId, subHbAmounts.get(i).longValue(), month, dataFromType, true);
				
				subType = EbuyDict.EbuyOrder_Type.Property_Bill;
			}
			logger.debug("confirmPay.preOrder==>"+JSON.toJSON(preOrder)+";cp==>"+JSON.toJSONString(cp));
			
			totalRealAmt = totalRealAmt.add(preOrder.getRealAmt());
			totalAmtBt = totalAmtBt.add(preOrder.getAmtBt());
			
			EbuyOrderRelation ebuyOrderRelation = new EbuyOrderRelation();
			ebuyOrderRelation.setSubId(preOrder.getOrderId());
			ebuyOrderRelation.setSubType(subType);
			ebuyOrderRelation.setSubAmount(preOrder.getRealAmt().longValue());
			ebuyOrderRelations.add(ebuyOrderRelation);
		}
		
		// 4、总订单生成
		String buyTime = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
		ConfirmPayResultDto confirmPayResult = dealTotalOrder(userId, hbAmount, totalRealAmt, totalAmtBt, subChannelId, buyTime);
		
		// 5、子账单关系处理
		dealSubOrderRelation(userId, confirmPayResult.getOrderId(), buyTime, ebuyOrderRelations);
		
		// 6、免单处理（不能将免单处理放在子账单中处理；否则，如果用户取消，会出现一部分已交，一部分未缴的bug）
		boolean isFree = dealFreeOrder(confirmPayResult.getOrderId(), totalRealAmt, hbAmount, totalAmtBt);
		confirmPayResult.setFree(isFree);
		
		logger.debug("confirmPay.confirmPayResult==>"+JSON.toJSON(confirmPayResult));
		return confirmPayResult;
	}
	
	private PreOrderDto dealRecharge(BigInteger userId, BigInteger gbbccId, long rechargeAmt, long hbAmount, String deviceId, Long subChannelId) {
		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
		long perferAmt = propertyDao.selectPropertyRechargePerfer(gbbccId, userId, realRoom.getId(), rechargeAmt);
		long realAmt = rechargeAmt-perferAmt-hbAmount;
		
		String nowTime = DateUtils.getCurrentDate();
		BigInteger orderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
		EbuyOrder ebuyOrder = new EbuyOrder();
		ebuyOrder.setId(orderId);
		ebuyOrder.setType(EbuyDict.EbuyOrder_Type.Property_Bill);//订单类型：物业账单
		ebuyOrder.setBuyerId(userId);
		BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		ebuyOrder.setCurrRoomId(roomId);
		ebuyOrder.setBuyTime(nowTime);
		ebuyOrder.setCreater(userId);
		ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(ebuyOrder.getId()));
		ebuyOrder.setPayMethod(null);
		ebuyOrder.setPayTime(null);
		ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
		ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);//支付状态为待支付
		ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);//发货状态为未发货
		ebuyOrder.setTotalDeliveryFee(0L);//总配送费l
		ebuyOrder.setAmount(realAmt);
		ebuyOrder.setTotalCouponAmount(perferAmt+hbAmount);//add-优惠金额
		//syl-add2015-5-13 18:21:34 渠道
		ebuyOrder.setSubChannel(subChannelId==null?null:subChannelId+"");
		ebuyOrder.setIsPropFee(1);
		//syl-add-2016-9-28 11:07:02增加设备id（限制设备缴费限制）
		ebuyOrder.setDeviceId(deviceId);
		ebuyOrder.setCurrRoomId(roomId);

		//更新订单的粮票支付信息
		if(hbAmount > 0) {
			commonRedenvelopeService.updateOrderEntityByJFB(ebuyOrder, hbAmount, perferAmt);
		}

		// 新增订单
		int res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
		if(res<=0){
			throw new BusinessRuntimeException("PropertyService.dealRecharge.error");
		}

		//更订单优惠信息
		if(hbAmount > 0) {//外键约束问题
			commonRedenvelopeService.updatePayCouponByJFB(ebuyOrder, hbAmount);
		}

		//增加订单账单关系
		PayBill payBill = createRechargePayBill(gbbccId, ebuyOrder, hbAmount, realRoom);
		//增加账单和收费项关系
		{
			PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
			propertyFeeDetail.settPayBillFId(payBill.getId());
			propertyFeeDetail.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail));
			propertyFeeDetail.setName(payBill.getBillTypeName());
			propertyFeeDetail.setTotalPrice(payBill.getAmount().doubleValue());
			propertyFeeDetail.setAllowancePrice(0l);
			propertyFeeDetail.setType(9);
			propertyFeeDetail.setSys0AddTime(nowTime);
			propertyFeeDetail.setSys0DelState(0);
			//v501为账单打印使用
			propertyFeeDetail.setFeeType(3);
			propertyFeeDetailBaseDao.insertPropertyFeeDetail(propertyFeeDetail);
		}
		{//创建订单关系
			BigInteger prizeUserRoomId = ebuyOrder.getCurrRoomId();
			BigInteger ebuyOrderHasTPayBillAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_t_pay_bill);
			EbuyOrderHasTPayBill ebuyOrderHasTPayBillAdd = new EbuyOrderHasTPayBill();
			ebuyOrderHasTPayBillAdd.setDiscount(null);
			ebuyOrderHasTPayBillAdd.setId(ebuyOrderHasTPayBillAddId);
			ebuyOrderHasTPayBillAdd.setPrizeRecordId(null);
			ebuyOrderHasTPayBillAdd.settEbuyOrderFId(ebuyOrder.getId());
			ebuyOrderHasTPayBillAdd.setPrizeUserRoomId(prizeUserRoomId);
			ebuyOrderHasTPayBillAdd.settPayBillFId(payBill.getId());
			Integer resCount = ebuyOrderHasTPayBillBaseDao.insertEbuyOrderHasTPayBill(ebuyOrderHasTPayBillAdd);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("PropertyService.dealRecharge.insertOrderHasPayBill.error");
			}
		}

		PreOrderDto preOrder = new PreOrderDto();
		preOrder.setOrderId(orderId);
		preOrder.setFree(false);
		preOrder.setRealAmt(BigDecimal.valueOf(ebuyOrder.getAmount()));
		preOrder.setTotalAmt(BigDecimal.valueOf(payBill.getAmount()));
		preOrder.setAmtBt(BigDecimal.valueOf(ebuyOrder.getTotalCouponAmount()==null ? 0L : ebuyOrder.getTotalCouponAmount()));
		
		return preOrder;
	}
	
	private PayBill createRechargePayBill(BigInteger gbbccId, EbuyOrder ebuyOrder, long hbAmt, RealRoom realRoom) {
		GroupBuildingBillCycleConfig gbbcc = groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigBySeqId(gbbccId);
		
		Integer preferStatus = 0;
		GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(gbbcc.gettGbId());
		if(groupBuilding.getIsPrefer() != null && groupBuilding.getIsPrefer().equals(1)) {//有优惠
			preferStatus = 1;//1随机立减
		}
		
		//物业费类型
		Map<String, Object> billTypeMap = new HashMap<String, Object>();
		billTypeMap.put("name", gbbcc.getBillName());
		billTypeMap.put("icon", PropIconUtil.PROPERTY);
		List<PayBillType> paybillTypeList = payBillTypeBaseDao.selectPayBillTypeByCondition(billTypeMap,false);
		
		String now = DateUtils.getCurrentDate();
		PayBillType payBillType = null;
		if(paybillTypeList != null && paybillTypeList.size() > 0) {
			payBillType = paybillTypeList.get(0);
		} else {
			payBillType = new PayBillType();
			payBillType.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill_type));
			payBillType.setName(gbbcc.getBillName());
			payBillType.setIcon(PropIconUtil.PROPERTY);
			payBillType.setIsPropFee(2);
			payBillType.setLastUpdTime(now);
			payBillType.setGbId(gbbcc.gettGbId());
			payBillType.setPaytimeType(2);
			payBillType.setActiveStatus(1);
			payBillType.setPreferStatus(preferStatus);
			payBillType.setSys0AddTime(now);
			payBillType.setSys0DelState(0);
			
			payBillTypeBaseDao.insertPayBillType(payBillType);
		}

		PayBill payBill = new PayBill();
		payBill.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill));
		payBill.setAmount(ebuyOrder.getAmount()+ebuyOrder.getTotalCouponAmount());
		payBill.setBillTypeName(payBillType.getName());
		payBill.setBillTypeId(payBillType.getId());
		
		payBill.setBillTimeCfgId(BigInteger.valueOf(-1));//数据库设计不能为空，选择周期没有该id 所以默认-1为id
		payBill.setHbAmount(hbAmt);
		payBill.setSys0AddTime(now);
		payBill.setIsPay(2);
		payBill.setIsPropFee(2);
		payBill.setPaytimeType(2);
		payBill.setBillMonthStart(null);
		payBill.setBillMonthEnd(null);
		String payDayStart = getRechargeDay(gbbcc.getStartDay(), 0) + " 00:00:01";
		
		payBill.setPayDayStart(payDayStart);
		String payEndStart = getRechargeDay(gbbcc.getEndDay(), gbbcc.getRechargeMonthMode().intValue()) + " 23:59:59";
		payBill.setPayDayEnd(payEndStart);
		payBill.setBillMonthSize(0L);
		payBill.setPayTime(ebuyOrder.getPayTime());
		payBill.setPropertyProprietorId(realRoom.gettPropertyProprietorFId().toString());
		payBill.setSuccPayAmount(ebuyOrder.getAmount());
		payBill.settRealRoomFId(realRoom.getId());
		payBill.setPaymentWay(1);//1 用户在线支付
		payBill.setPreferType(preferStatus);
		payBill.setSys0DelState(1);
		payBill.setCycleType(1);//1固定周期，2选择周期
		payBill.settRealroomSoftwareFeeFId(null);
		payBill.settGroupBuildingBillCycleConfigFId(gbbccId);
		payBill.setBankCollectionStatus(0);
		payBill.setCashStatus(1);
		//新增账单
		Integer resCount = payBillBaseDao.insertPayBill(payBill);
		if (resCount == null || resCount <= 0) {
			throw new BusinessRuntimeException("账单数据新增失败！");
		}

		return payBill;
	}
	
	/**
	 * 获取支付详情
	 * @param orderId
	 * @return
	 */
	@Override
	public PayDetailDto getPayDetail(BigInteger orderId){
		EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
		if(!EbuyDict.EbuyOrder_Type.Total_Property_Bill.equals(ebuyOrder.getType())){
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.VALIDATE_ERR);
    		businessRuntimeException.setErrorMsg("订单类型不匹配，数据查询失败！");
    		throw businessRuntimeException;
		}
		
		PayDetailDto payDetail = new PayDetailDto();
		Integer payStatus = ebuyOrder.getPayStatus();
		Integer clientPayStatus = ebuyOrder.getClientPayStatus();
		payDetail.setResult(getPayResult(payStatus, clientPayStatus));
		
		if (payStatus == EbuyDict.EbuyOrder_PayStatus.Not_Pay) {
			if (clientPayStatus != null && clientPayStatus == EbuyDict.EbuyOrder_ClientPayStatus.Client_Pay_Success) {
				payStatus = 5;
			}
		}
		/** "1":"未支付","2":"支付完成","3":"支付失败","4":"退款","5":"支付确认中" */
		payDetail.setPayStatus(payStatus);
		
		/**优惠金额*/
		PreferAmtDto preferAmt = new PreferAmtDto();
		Long hbAmt = getHbAmtForDetail(orderId);
		Long bankDiscount = getBankDiscount(orderId, ebuyOrder.getPayMethod());
		// 总优惠金额
		Long totalCouponAmount = (ebuyOrder.getTotalCouponAmount()==null ? 0 : ebuyOrder.getTotalCouponAmount());
		// 随机立减金额
		Long jfqDiscount = totalCouponAmount - hbAmt;
		
		String priceUnit = "￥ ";
		if(hbAmt!=null && hbAmt>0){
			preferAmt.setHbAmt(priceUnit.concat(PriceUtil.div100s(hbAmt)));
		}
		if(bankDiscount!=null && bankDiscount>0){
			preferAmt.setBankDiscount(priceUnit.concat(PriceUtil.div100s(bankDiscount)));
		}
		if(jfqDiscount!=null && jfqDiscount>0){
			preferAmt.setJfqDiscount(priceUnit.concat(PriceUtil.div100s(jfqDiscount)));
		}
		// 物业宝抵扣处理
		Long deduAmt = getDeduByOrderId(orderId, 1);
		String deDuAmtDesc = null;
		if(deduAmt!=null && deduAmt>0){
			deDuAmtDesc = "￥ ".concat(PriceUtil.div100s(deduAmt));
		} else {
			deduAmt = 0L;
		}
		preferAmt.setDeduAmt(deDuAmtDesc);
		payDetail.setPreferAmt(preferAmt);
		
		// 账单总金额
		Long totalAmt = ebuyOrder.getAmount() + totalCouponAmount + deduAmt;
		
		payDetail.setTotalAmount(priceUnit.concat(PriceUtil.div100s(totalAmt)));
		payDetail.setRealAmount(priceUnit.concat(PriceUtil.div100s(ebuyOrder.getAmount()-bankDiscount)));// 实缴金额
		boolean isPrefer = !((hbAmt==null || hbAmt==0) && (bankDiscount==null || bankDiscount==0) && (jfqDiscount==null || jfqDiscount==0) && deduAmt==0);
		payDetail.setPrefer(isPrefer);
		
		String payTime = ebuyOrder.getPayTime();
		if(StringUtils.isNotBlank(payTime)){
			payDetail.setPayTime(DateUtils.convertDateToStr(DateUtils.strToDateTime(payTime), "yy.MM.dd HH:mm"));
		}
		payDetail.setPayMethod(getOrderPayMethod(ebuyOrder.getPayMethod()));
		payDetail.setTransNo(ebuyOrder.getOrderNo());
		List<PayBillDetailDto> payBillDetailList = propertyDao.selectPayBillDetails(orderId);
		
		payDetail.setPayBillDetails(payBillDetailList);
		
		logger.debug(JSON.toJSON("getPayDetail.payDetail==>"+JSON.toJSONString(payDetail)));
		
		return payDetail;
	}
	
	/**
	 * 获取物业宝抵扣
	 * @param id
	 * @param orderType 1：总订单；2：子订单
	 * @return
	 */
	private Long getDeduByOrderId(BigInteger id, int orderType){
		// 物业宝抵扣处理
		return propertyDao.selectDeduByOrderId(id, orderType);
	}
	
	/**
	 * 获取已缴账单信息
	 * @param paidBillInfoReq 页码page从1开始
	 * @return
	 */
	@Override
	public PaidBillDto getPaidBillInfo(PaidBillInfoReq paidBillInfoReq){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("_begin", (paidBillInfoReq.getPage()-1)*paidBillInfoReq.getPageNum());
		paramMap.put("_length", paidBillInfoReq.getPageNum());
		paramMap.put("userId", paidBillInfoReq.getUserId());
		
		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(paidBillInfoReq.getUserId());
		BigInteger realRoomId = realRoom.getId();
		paramMap.put("realRoomId", realRoomId);
		
		List<PaidBillItemDto> paidBillItems = propertyDao.selectPaidBillList(paramMap);
		paidBillItems = dealPaidBillInfo(paidBillItems);
		int count = propertyDao.selectPaidBillCount(paidBillInfoReq.getUserId());
		
		boolean isLast = (paidBillInfoReq.getPage()*paidBillInfoReq.getPageNum() >= count);
		
		PaidBillDto paidBills = new PaidBillDto();
		paidBills.setList(paidBillItems);
		paidBills.setLast(isLast);
		return paidBills;
	}
	
	/**
	 * 获取账单详情信息（已缴）
	 * @param orderId
	 * @param type 账单详情类型（1：物业缴费；2：车禁）
	 * @return CarPaidBillDetailDto或PropertyPaidBillDetailDto
	 */
	@Override
	public Object getPaidBillDetail(BigInteger orderId, String type){
		if(PropertyConstant.BillDetailType.Property.equals(type)){// 物业费账单
			PropertyPaidBillDetailDto propertyPaidBillDetail = propertyDao.selectPaidPropertyBillDetail(orderId);
			
			// 支付方式处理
			BillPayDto billPay = propertyPaidBillDetail.getBillPay();
			if(PropertyConstant.BillPayMethod.Di_Kou.toString().equals(billPay.getPayMethod())){
				billPay.setPreferAmt(null);
			}
			PreferAmtDto preferAmt = billPay.getPreferAmt();
			if(preferAmt==null){
				preferAmt = new PreferAmtDto();
			}
			Long deduAmt = getDeduByOrderId(orderId, 2);
			String deDuAmtDesc = null;
			if(deduAmt!=null && deduAmt>0){
				deDuAmtDesc = "￥ ".concat(PriceUtil.div100s(deduAmt));
			}
			preferAmt.setDeduAmt(deDuAmtDesc);
			billPay.setPreferAmt(preferAmt);
			
			billPay.setPayMethod(getPayBillPayMethod(billPay.getPayMethod()));
			billPay.setResult("已支付");
			
			// 处理往月欠费
			PropertyBillTailDto propertyBillTail = propertyPaidBillDetail.getPropertyBillTail();
			if(propertyBillTail!=null){
				BigDecimal lastUnpaid = propertyBillTail.getLastUnpaid();
				if(lastUnpaid!=null && lastUnpaid.doubleValue()>0){
					List<PayBillDetailDto> payBillDetailList = propertyBillTail.getPayBillDetails();
					PayBillDetailDto payBillDetail = new PayBillDetailDto();
					payBillDetail.setBillName("往月欠费");
					payBillDetail.setBillAmount("￥ ".concat(PriceUtil.div100s(lastUnpaid.longValue())));
					
					payBillDetailList.add(payBillDetail);
				}
			}
			
			return propertyPaidBillDetail;
		} else if(PropertyConstant.BillDetailType.Car.equals(type)){// 车禁缴费
			CarPaidBillDetailDto carPaidBillDetail = propertyDao.selectPaidCarBillDetail(orderId);
			
			// 支付方式处理
			BillPayDto billPay = carPaidBillDetail.getBillPay();
			billPay.setPayMethod(getOrderPayMethod(billPay.getPayMethod()));
			billPay.setResult("已支付");
			
			return carPaidBillDetail;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取账单详情信息（未缴）
	 * @param param
	 * @return CarUnPaidBillDetailDto或PropertyUnPaidBillDetailDto
	 */
	@Override
	public Object getUnPaidBillDetail(UnPaidBillDetailReq param){
		if(StringUtils.isNotBlank(param.getCarNum())){// 车禁账单
			return getUnPaidCarBillDetail(param.getCarNum(), Integer.valueOf(param.getPayMonth()), param.getSessionId());
		} else {// 物业账单
			return getUnPaidPropertyBillDetail(param);
		}
	}

	@Override
	public List<RemainBillDto> getUnpaidBillInfoList(BigInteger payBillId) {
		return propertyDao.getUnpaidBillInfoList(payBillId);
	}
	
	/*==============================================private===============================================*/
	/**
	 * 获取物业宝信息
	 * @param userId
	 * @param realRoomId
	 * @return
	 */
	private FinanceDto getFinanceInfo(BigInteger userId, BigInteger realRoomId){
		boolean isBuy = false;
		boolean isOpenFinance = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		// 查看小区是否校验，是否可以缴费，是否导入过账单
		Propfee propfee = plotpropertyService.getPropertyFeeAndCount(paramMap);
		//如果查到当前门牌是物业软件对接的小区门牌，则不能买物业宝
		RealRoomSoftwareInfo realRoomSoftwareInfo = propertySoftwareDockDao.getRealRoomSoftwareInfo(realRoomId);
		if(propfee == null || realRoomSoftwareInfo != null) {
			isBuy = false;
			isOpenFinance = false;
		} else {
			if(propfee.getPayPeriodEnd()!=null && propfee.getPayPeriodEnd().compareTo(PlotpropertyConstant.PERID_GBCFG_LIMITEND_DEFAULT) == 0){
				isBuy = false;
				isOpenFinance = false;
			} else {
				isOpenFinance = (propfee.getPropfeeCanpay() != null && propfee.getPropfeeCanpay() == 1);
				isBuy = (propfee.getDeductionCount() != null && propfee.getHasDeductionCount() != null && propfee.getDeductionCount() > propfee.getHasDeductionCount());
			}
		}
		
		String wybUrl = CnfantasiaCommbusiUtil.getSysParaValue("wybUrl");
		
		FinanceDto finance = new FinanceDto();
		finance.setBuyUrl(wybUrl);
		finance.setContent(isBuy ? "已购买" : "免物业费");
		finance.setBuy(isBuy);
		finance.setOpenFinance(isOpenFinance);
		
		return finance;
	}
	
	/**
	 * 获取缴费周期
	 * 
	 * @param gb
	 * @param realRoomId
	 * @return
	 */
	private List<String> getPeriodMonths(GroupBuilding gb, BigInteger realRoomId){
		List<String> periodMonths = null;
		if(StringUtils.isNotBlank(gb.getPeriodMonths()) 
				&& gb.getPropertyPeriodType() != null && gb.getPropertyPeriodType().equals(2)){
			// 已配置 && 选择周期缴费
			String[] periodMonthTmps = gb.getPeriodMonths().split(",");
			// 有滞纳金才对月份进行限制
			PropertyAlterBillInfo propertyAlterBillInfo = alterPeriodDao.getAlterPeriodDetail(realRoomId);
			if(propertyAlterBillInfo!=null && propertyAlterBillInfo.getLatefeeAmount() != null && propertyAlterBillInfo.getLatefeeAmount().doubleValue() > 0) {
				int month = 0;
				if(propertyAlterBillInfo.getLatefeeStart() != null && !"0000-00-00 00:00:00".equals(propertyAlterBillInfo.getLatefeeStart())) {
					month = DateUtils.getDiffMonths(DateUtils.convertStrToDate(propertyAlterBillInfo.getLatefeeStart()), new Date());
					if((DateUtils.addMonths(DateUtils.convertStrToDate(propertyAlterBillInfo.getLatefeeStart()), month).getTime() - new Date().getTime()) < 0) {
						month = month + 1;
					}
				}
				periodMonths = new ArrayList<String>();
				for (int j = 0; j< periodMonthTmps.length; j++) {
					if(Integer.valueOf(periodMonthTmps[j]) >= month) {
						periodMonths.add(periodMonthTmps[j]);
					}
				}
			} else {
				periodMonths = Arrays.asList(periodMonthTmps);
			}
		}
		
		return periodMonths;
	}
	
	/**
	 * 获取物业代扣卡信息（余额、是否能缴费）
	 * @return
	 */
	private RemainBillOtherInfoDto getRemainBillOtherInfoDto(BigInteger userId, RealRoom realRoom, BigInteger roomId){
		boolean isOpenService = false;
		List<String> periodMonths = null;
		/*====是否能缴费、缴费月份====*/
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		if (groupBuilding != null){
			periodMonths = getPeriodMonths(groupBuilding, realRoom.getId());
			if(groupBuilding.getSignStatus() != null && groupBuilding.getSignStatus().compareTo(1) == 0
					&& groupBuilding.getPropfeeCanpay() != null && groupBuilding.getPropfeeCanpay().compareTo(1) == 0){
				isOpenService = true;
			}
		}
		
		/*====代扣卡====*/
		long balanceAmt = 0L;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tUserFId", userId);
		//查询当前账户情况
		List<PropertyAccountInfo> accounts = propertyAccountInfoBaseService.getPropertyAccountInfoByCondition(param);
		if (accounts != null && accounts.size() > 0) {
			balanceAmt = accounts.get(0).getBalanceAmt();
		}
		
		RemainBillOtherInfoDto otherInfo = new RemainBillOtherInfoDto();
		otherInfo.setPropertyCardAmt(PriceUtil.div100(balanceAmt));
		otherInfo.setOpenService(isOpenService);
		otherInfo.setPeriodMonths(periodMonths);
		
		return otherInfo;
	}
	
	/**
	 * 获取物业充值
	 * @param gbId
	 * @return
	 */
	private List<RemainBillDto> getRechargeBills(BigInteger gbId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tGbId", gbId);
		paramMap.put("chargingMode", 3);
		List<GroupBuildingBillCycleConfig> gbbccList = groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigByCondition(paramMap, false);
		if (gbbccList != null && gbbccList.size() > 0) {
			List<RemainBillDto> remainBills = new ArrayList<RemainBillDto>();
			for (int i = gbbccList.size() - 1; i >= 0; i--) {
				GroupBuildingBillCycleConfig gbbcc = gbbccList.get(i);
				RemainBillDto remainBill = new RemainBillDto();
				remainBill.setBillType(PropertyConstant.BillType.PROPERTY_RECHARGE);
				remainBill.setBillTitle(gbbcc.getBillName());

				String icon = ApplicationContextBothUtil.getAbsolutePath(PropIconUtil.getBillIcon(null), SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH, null);
				remainBill.setBillTypeImg(icon);
				remainBill.setBillTypeGrayImg(getGrayImgUrl(icon));
				
				remainBill.setCanPay(isCanPay(gbbcc));
				remainBill.setGbbccId(gbbcc.getId());
				
				
				BigDecimal minRecharge = BigDecimalUtil.div100(BigDecimal.valueOf(gbbcc.getMinRecharge()), 2);
				remainBill.setMinRecharge(minRecharge);
				
				BigDecimal maxRecharge = BigDecimalUtil.div100(BigDecimal.valueOf(gbbcc.getMaxRecharge()), 2);
				remainBill.setMaxRecharge(maxRecharge);
				
				String rechargeMode = (gbbcc.getRechargeMonthMode()==0)?"本月":"下月";
				
				StringBuilder desc = new StringBuilder();
				desc.append("* 缴费金额：").append(minRecharge).append("~").append(maxRecharge).append("\n");
				desc.append("* 缴费时间：本月").append(gbbcc.getStartDay()).append("号至").append(rechargeMode).append(gbbcc.getEndDay()).append("号");
				remainBill.setPayDateDesc(desc.toString());

				remainBills.add(remainBill);
			}
			return remainBills;
		}
		return null;
	}
	
	/**
	 * 获取待缴费界面所有账单信息
	 * @param userId
	 * @param realRoomId
	 * @param firstMonth
	 * @param groupBuilding
	 * @return
	 */
	private List<RemainBillDto> getRemainBills(BigInteger userId, BigInteger realRoomId, String firstMonth, GroupBuilding groupBuilding){
		List<RemainBillDto> remainBills = new ArrayList<RemainBillDto>();
		List<PropertyBillEntity> billList = plotpropertyService.getBillList02(userId, realRoomId, groupBuilding);
		
		Long price = 0L;
		if(billList!=null && billList.size()>0){// billType=1
			price = alterPeriodDao.getBasicSumAmt(realRoomId);//每月缴费基数总和
		}
		for (PropertyBillEntity propertyBillEntity : billList) {
			RemainBillDto remainBill = new RemainBillDto();
			remainBill.setIsSucBill(propertyBillEntity.getIsSucBill());
			remainBill.setPayBillId(propertyBillEntity.getPayBillId());
			remainBill.setBillTitle(propertyBillEntity.getBillTitle());
			remainBill.setCollectionArrearsType(propertyBillEntity.getCollectionArrearsType());
			
			//抄表也算是固定周期的一种
			String billType = propertyBillEntity.getBillType().equals(PlotpropertyDict.PayBillType_IsPropFee.NO_MR.toString()) ? PropertyConstant.BillType.NOT_PROPERTY_FEE: propertyBillEntity.getBillType();
			remainBill.setBillType(billType);
			remainBill.setBillTypeName(propertyBillEntity.getBillTypeName());
			
			String icon = ApplicationContextBothUtil.getAbsolutePath(PropIconUtil.getBillIcon(propertyBillEntity.getBillTypeName()), SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH, propertyBillEntity.getLastUpdTime());
			remainBill.setBillTypeImg(icon);
			remainBill.setBillTypeGrayImg(getGrayImgUrl(icon));

			Long lateFeeAmt = propertyBillEntity.getLateFeeAmt();
			if(lateFeeAmt == null){
				lateFeeAmt = 0L;
			}
			remainBill.setLateFeeAmt(PriceUtil.div100s(lateFeeAmt));
			
			Long billRelAmt = 0L;
			long billAmt = propertyBillEntity.getBillAmt()/* + lateFeeAmt*/;// 滞纳金不加入，前端加
			long deduPrice = propertyBillEntity.getDeduPrice();
			long preferentialAmt = propertyBillEntity.getPreferentialAmt();
			if(propertyBillEntity.getBillAmt() > 0) {
				if(propertyBillEntity.getFinanceStatus() == 1) {
					billRelAmt = billAmt - deduPrice - preferentialAmt;
				} else {
					billRelAmt = billAmt - preferentialAmt;
				}
			}
			if(billRelAmt < 0) {
				billRelAmt = 0L;
			}
			remainBill.setBillAmt(PriceUtil.div100s(billAmt));
			remainBill.setBillRelAmt(PriceUtil.div100s(billRelAmt));
			if(propertyBillEntity.getPayBillId()==null && propertyBillEntity.getDataFromType()==0){// 选择性周期
				remainBill.setPrice(PriceUtil.div100s(price));
				if(firstMonth!=null){
					remainBill.setFirstMonth(Integer.valueOf(firstMonth));
				}
			} else {// 非选择性周期也要赋值，前端会将此值传到支付确认页面
				String billTitle = remainBill.getBillTitle();
				if(StringUtils.isNotBlank(billTitle)){
					String firstMonthTmp = billTitle.substring(billTitle.indexOf("（")+1, billTitle.indexOf("个"));
					if(StringUtils.isNumeric(firstMonthTmp)){
						remainBill.setFirstMonth(Integer.valueOf(firstMonthTmp));
					}
				}
			}
			
			remainBill.setIsPrefer(propertyBillEntity.getIsPreferential());
			remainBill.setPreferAmt(PriceUtil.div100s(preferentialAmt));
			remainBill.setPreferName(propertyBillEntity.getPreferentialType());

			if(StringUtils.isNotBlank(propertyBillEntity.getBillTitle())){
				String billMonthStartStr = propertyBillEntity.getBillTitle().substring(0, 5);
				Date billMonthStart = DateUtils.convertStrToDate(billMonthStartStr, "yy.MM");
				String payDateDesc = StringUtils.EMPTY;
				if(propertyBillEntity.getPropertyPeriodType()==PropertyConstant.PropertyPeriodType.Gu_Ding){
					// 第三方（极致）的不显示
					if(PropertyConstant.DataFromType.Jfq.equals(String.valueOf(propertyBillEntity.getDataFromType()))){
						payDateDesc = "本次缴费截止 "+propertyBillEntity.getPayDateDesc();
					}
				} else {
					Date payEnd = DateUtils.decreateMonth(billMonthStart, 1);
					payDateDesc = "已缴费至 ".concat(DateUtils.convertDateToStr(payEnd, "yy.MM"));
				}
				remainBill.setMonthStart(billMonthStart.getTime());
				remainBill.setPayDateDesc(payDateDesc);
			}
			remainBill.setIsPay(propertyBillEntity.getIsPay());
			if(propertyBillEntity.getBankCollectionStatus() != null && propertyBillEntity.getBankCollectionStatus() == 1 && propertyBillEntity.getIsPay() != 1) {
				remainBill.setIsPay(4); //银行托收中
			}
			
			remainBill.setIsGetPrefer(propertyBillEntity.getIsGetPreferential());
			remainBill.setIsFinanceStatus(propertyBillEntity.getFinanceStatus());
			remainBill.setPropertyPeriodType(propertyBillEntity.getPropertyPeriodType());
			remainBill.setPeriodPayServiceStatus(propertyBillEntity.getPeriodPayServiceStatus());
			remainBill.setDataFromType(propertyBillEntity.getDataFromType());
			
			remainBills.add(remainBill);
		}
		return remainBills;
	}

	/**
	 * 选择周期账单查询
	 * @param userId
	 * @param realRoomId
	 * @param firstMonth
     * @param groupBuilding
	 * @return
     */
	private List<AlterPayBillDto> getAlterBills(BigInteger userId, BigInteger realRoomId, String firstMonth, GroupBuilding groupBuilding) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("realRoomId", realRoomId);
		List<AlterCyclesEntity> alterCyclesEntities = plotpropertyService.getAlterPayBillEntity(paraMap);

		List<AlterPayBillDto> alterPayBillDtos = new ArrayList<AlterPayBillDto>();
		for (AlterCyclesEntity alterCyclesEntity : alterCyclesEntities) {
			List<AlterPayBillEntity> alterPayBillEntities = new ArrayList<AlterPayBillEntity>();
			if(DataUtil.isEmpty(alterCyclesEntity.getAlterCyclePayBillEntities())) continue;
			String billMonthStart = alterCyclesEntity.getAlterCyclePayBillEntities().get(0).getBillMonthStart();
			String maxMonthsStr = "";
			for (AlterCyclePayBillEntity alterCyclePayBillEntity : alterCyclesEntity.getAlterCyclePayBillEntities()) {
				for (AlterPayBillEntity alterPayBillEntity : alterCyclePayBillEntity.getAlterPayBillEntities()) {
					for (PayBill payBill : alterPayBillEntity.getUnpaidList()) {
						if(comparaStrDate(billMonthStart, payBill.getBillMonthStart()) == 1) {//取出账单中最小的账期开始时间
							billMonthStart = payBill.getBillMonthStart();
						}
					}
					alterPayBillEntities.add(alterPayBillEntity);
				}
				//如果没有欠费就选择账期最小时间（有欠费时间一定比账期的小）
				if(comparaStrDate(billMonthStart, alterCyclePayBillEntity.getBillMonthStart()) == 1) {//取出账期中最小的账期开始时间
					billMonthStart = alterCyclePayBillEntity.getBillMonthStart();
				}
				if(!DataUtil.isEmpty(alterCyclePayBillEntity.getPeriodMonths()) && alterCyclePayBillEntity.getPeriodMonths().length() > maxMonthsStr.length()) {
					maxMonthsStr = alterCyclePayBillEntity.getPeriodMonths();
				}
			}

			if(DataUtil.isEmpty(maxMonthsStr)) {
				logger.info("没有找到配置的缴费月份信息（自动生成账单的时候）");
				logger.info("[alterCyclesEntities]:"+ JSON.toJSONString(alterCyclesEntities));
				return null;
			}
			String[] split = maxMonthsStr.split(",");
			Integer[] months = new Integer[split.length];
			for (int i = 0; i < split.length; i++) {
				months[i] = Integer.valueOf(split[i]);
			}

			AlterPayBillDto alterPayBillDto = new AlterPayBillDto();
			List<AlterMonth2Bills> list = setMonthBills(alterPayBillEntities, months);
			alterPayBillDto.setBillTypeName(alterCyclesEntity.getBillName());
			String icon = ApplicationContextBothUtil.getAbsolutePath(PropIconUtil.getBillIcon(alterCyclesEntity.getBillName()), SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH, alterCyclesEntity.getLastUpdTime());
			alterPayBillDto.setBillTypeImg(icon);
			alterPayBillDto.setBillTypeGrayImg(getGrayImgUrl(icon));
			alterPayBillDto.setMonthAndPayBill(list);
			String payEnd = DateUtils.convertDateToStr(DateUtils.decreateMonth(DateUtils.convertStrToDate(billMonthStart), 1), "yyyy-MM");
			alterPayBillDto.setPayEnd(payEnd);
			Date billMonthStartDate = DateUtils.convertStrToDate(billMonthStart, "yyyy-MM-dd");
			alterPayBillDto.setMonthStart(billMonthStartDate.getTime());
			alterPayBillDto.setCycleType(CycleCfgDict.RechargeMode.ALTER_CYCLE);

			Date billMonthEndDate = DateUtils.addMonths(billMonthStartDate, Integer.valueOf(list.get(0).getMonth()) - 1);
			String billMonthStartDateStr = DateUtils.convertDateToStr(billMonthStartDate, "yyyy-MM");
			String billMonthEndDateStr = DateUtils.convertDateToStr(billMonthEndDate, "yyyy-MM");
			String billTitle = billMonthStartDateStr.substring(2, 4) +"."+ billMonthStartDateStr.substring(5, 7) + "-" + billMonthEndDateStr.substring(2, 4) +"."+ billMonthEndDateStr.substring(5, 7) + "("+Integer.valueOf(list.get(0).getMonth())+"个月)";
			alterPayBillDto.setBillTitle(billTitle);

			alterPayBillDtos.add(alterPayBillDto);
		}
		return alterPayBillDtos;
	}

	private List<AlterMonth2Bills> setMonthBills(List<AlterPayBillEntity> alterList, Integer[] months) {
		List<PayBill> unpayBillList = new ArrayList<PayBill>();
		List<PayBill> nounpayBillList = new ArrayList<PayBill>();
		Map<BigInteger, Long> preferMap = new HashMap<BigInteger, Long>();
		List<AlterMonth2Bills> list = new ArrayList<AlterMonth2Bills>();
		Long unpaidSize = 0L;//欠费账单月份数
		Long size = 0L;//未欠费账单月份数
		String unPidIds = "";//欠费ids
		Long unPaidAmt = 0L;
		for (AlterPayBillEntity alterPayBillEntity : alterList) {
			size += alterPayBillEntity.getBillMonthSize();
			if(!DataUtil.isEmpty(alterPayBillEntity.getUnpaidList())) {
				for (PayBill payBill : alterPayBillEntity.getUnpaidList()) {
					unpayBillList.add(payBill);
					unpaidSize += payBill.getBillMonthSize();
					unPidIds += payBill.getId()+",";
					unPaidAmt += payBill.getAmount() - (!DataUtil.isEmpty(payBill.getDeduPrice()) ? payBill.getDeduPrice() : 0L);
				}
			}
			nounpayBillList.add(alterPayBillEntity);
			preferMap.put(alterPayBillEntity.getId(), alterPayBillEntity.getPreferAmt());
		}
		if(!DataUtil.isEmpty(months) && months[months.length - 1] >= Integer.valueOf(size.toString())) {
			for (int i = 0; i < months.length; i++) {
				if(months[i] > size) continue;//已可以缴费的月份为主：可缴费为4个月，系统配置2,4,6,8  只显示2,4
				AlterMonth2Bills alterMonth2Bills = new AlterMonth2Bills();
				int month = months[i] + Integer.valueOf(unpaidSize.toString());//用户可缴费月份= 欠费月份 + 运营后台账单配置的月份
				int billMonthSize = 0;
				Long amt = 0L;
				String ids = "";
				Long preferAmt = 0L;
				int k = 0;
				while (billMonthSize <  months[i] && k < nounpayBillList.size()) {
					PayBill payBill = nounpayBillList.get(k);
					ids += payBill.getId() +",";
					amt += payBill.getAmount() - (!DataUtil.isEmpty(payBill.getDeduPrice()) ? payBill.getDeduPrice() : 0L);
					preferAmt += preferMap.get(payBill.getId()) == null ? 0L : preferMap.get(payBill.getId());
					billMonthSize += payBill.getBillMonthSize();
					k ++;
				}
				amt += unPaidAmt;//加上欠费金额
				alterMonth2Bills.setIds(unPidIds + ids);
				alterMonth2Bills.setAmt(PriceUtil.div100sHalfUp(amt));
				alterMonth2Bills.setPreferAmt(PriceUtil.div100sHalfUp(preferAmt));
				alterMonth2Bills.setMonth(month+"");
				//欠费ID去重复，防止用户多缴钱和缴费回调失败
				if(!DataUtil.isEmpty(alterMonth2Bills.getIds())) {
					String noRepeatIds = "";
					String[] split = alterMonth2Bills.getIds().split(",");
					ArrayList<String> arrayList = new ArrayList<>(new TreeSet<String>(Arrays.asList(split)));
					for (String s : arrayList) {
						noRepeatIds += s+",";
					}
					alterMonth2Bills.setIds(noRepeatIds);
				}

				list.add(alterMonth2Bills);
			}
			return list;
		} else {
			logger.info("[months]:"+JSON.toJSONString(months));
			logger.info("[size]:"+JSON.toJSONString(size));
			logger.info("[alterPayBillEntity]:"+ JSON.toJSONString(alterList));
			return null;
		}
	}

	/**
	 * 车禁账单
	 * @param userId
	 * @param gbId
	 * @return
	 */
	private List<RemainBillDto> getRemainCarBills(BigInteger userId, String sessionId, BigInteger gbId){
		List<RemainBillDto> remainBills = new ArrayList<RemainBillDto>();
		if(gbId == null) {
			return remainBills;
		}
		List<CarBill> carBills = accessService.queryCarBill(gbId, userId);
		
		Date now = new Date();
		List<List<CarFeeType>> allCarFeeTypeList = new ArrayList<List<CarFeeType>>();
        monthCarService.dealMonthCar(carBills, allCarFeeTypeList);
        
		for (int i = 0; i < carBills.size(); i++) {
			CarBill carBill = carBills.get(i);
			if (carBill.getSys0DelState() == 1) { continue; }
			String fee = PriceUtil.div100(carBill.getFee()).toString();
			int firstMonth = 1;
			String expire = carBill.getExpireDate();
			List<CarFeeType> carFeeTypeList = allCarFeeTypeList.get(i);

			//如果没有可缴月份则不显示账单信息
			if(carFeeTypeList==null || carFeeTypeList.size()==0) continue;

			RemainBillDto remainBill = new RemainBillDto();
			remainBill.setCarFeeTypeList(carFeeTypeList);
			remainBill.setCarNum(carBill.gettCarNum());
			remainBill.setIsSucBill(1);
			remainBill.setBillTitle(carBill.gettCarNum());
			remainBill.setBillType(PropertyConstant.BillType.CAR);
			remainBill.setBillTypeName("停车费");
			remainBill.setBillAmt(fee);
			remainBill.setBillRelAmt(fee);
			remainBill.setPrice(fee);
			remainBill.setFirstMonth(firstMonth);
			remainBill.setIsPrefer(0);
			remainBill.setIsPay(carBill.isConfirmPay() ? PropertyConstant.PayStatus.Pay_Confirmed : PropertyConstant.PayStatus.Not_Pay);
			
			String icon = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.CAR_BILL_ICON);
			remainBill.setBillTypeImg(icon);
			remainBill.setBillTypeGrayImg(getGrayImgUrl(icon));
			
			remainBill.setPayDateDesc(getPayDateDesc(expire, now, carBill.getGbName()));
			remainBill.setPropertyPeriodType(2);// 前端要求加上，用于判断是否可选择月份
			
			remainBills.add(remainBill);
		}
		
		return remainBills;
	}
	
	long oneDay = 1000 * 60 * 60 * 24;
	/**
	 * 获取车禁有效期描述
	 * @param expire
	 * @param now
	 * @param gbName
	 * @return
	 */
	public String getPayDateDesc(String expire, Date now, String gbName){
		Date expireDate = null;
		try {
			expireDate = DateUtil.formatSecond.get().parse(expire);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String payDateDesc = gbName + "月卡 ";
		
		if(expireDate!=null){
			long expireDays = (expireDate.getTime() - now.getTime()) / oneDay;
			if(expireDays>0){
				payDateDesc += expireDays+"天后到期";
			} else if(expireDays==0){
				payDateDesc += "今天已到期";
			} else {
				payDateDesc += "已欠费"+(-expireDays)+"天";
			}
		}
		
		return payDateDesc;
	}
	
	/**
	 * 获取灰色图标url
	 * @param icon
	 * @return
	 */
	private String getGrayImgUrl(String icon){
		String billTypeGrayImg = null;
		if(StringUtils.isNotBlank(icon)){
			int pointIndex = icon.lastIndexOf(".");
			billTypeGrayImg = icon.substring(0, pointIndex).concat("_gray").concat(icon.substring(pointIndex));
		}
		return billTypeGrayImg;
	}
	
	/**
	 * 获取车禁随机立减优惠金额
	 * @param carNum
	 * @param payMonth
	 * @return
	 */
	private PayAmtTmpDto getPayCarPrefer(String carNum, int payMonth, String sessionId){
		PayAmtTmpDto payAmtTmp = new PayAmtTmpDto();
		
		// FIXME:V510要改为根据t_car_num_list的f_id查询
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCarNum", carNum);
		List<CarNumList> carNumLists = carNumListBaseService.getCarNumListByCondition(paramMap);
		CarNumList carNumList = carNumLists.get(0);
		
    	BigInteger gbId = carNumList.gettGroupBuildingFId();
    	MonthCarInfo monthCarInfo = thirdCarFactory.getMonthCarPayInfo(gbId, carNum, payMonth);
    	BigInteger totalAmount = BigInteger.valueOf(monthCarInfo.getRealAmt());
		
		GroupBuilding gb = groupBuildingBaseDao.selectGroupBuildingBySeqId(carNumList.gettGroupBuildingFId());
		if(gb.getCarMonthIsOpen()!=null && gb.getCarMonthIsOpen()==1){// 随机立减优惠已开通
			CarPreferParam carPreferParam = new CarPreferParam();
	    	carPreferParam.setCarId(carNumList.getId());
	    	carPreferParam.setUserId(UserContext.getOperIdMustExistBigInteger());
	    	carPreferParam.setPayType(1);// 缴费类型=={"0":"临停车缴费","1":"月卡缴费"}
			carPreferParam.setPayMonth(payMonth);
			carPreferParam.setAmount(totalAmount);
			carPreferParam.setGbId(carNumList.gettGroupBuildingFId());
	    	
	    	CarPreferDto carPreferDto = accessService.getPayCarPrefer(carPreferParam);
	    	BigInteger couponAmount = carPreferDto.getAmount();
	    	if(couponAmount.intValue()>=totalAmount.intValue()){//免单处理：免单时，最小金额为1分钱
	    		couponAmount = totalAmount.subtract(new BigInteger("1"));
	    		if(couponAmount.intValue()<0){
	    			couponAmount = BigInteger.ZERO;
	    		}
	    		carPreferDto.setAmount(couponAmount);
	    		
	    		CarPrefer carPrefer = new CarPrefer();
	    		carPrefer.setId(carPreferDto.getId());
	    		carPrefer.setCouponAmount(couponAmount.longValue());
	    		carPreferBaseService.updateCarPrefer(carPrefer);
	    	}
	    	payAmtTmp.setAmtBt(BigDecimal.valueOf(couponAmount.longValue()));
		} else {
			payAmtTmp.setAmtBt(BigDecimal.ZERO);
		}
    	
		payAmtTmp.setTotalAmt(BigDecimal.valueOf(totalAmount.doubleValue()));
		payAmtTmp.setDeduAmt(BigDecimal.ZERO);
		
		return payAmtTmp;
	}
	
	/**
	 * 是否开通物业缴费约会
	 * @param userId
	 * @return
	 */
	private boolean isOpenPropertyPrefer(BigInteger userId){
		boolean isPrefer = false;
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		if (groupBuilding != null && groupBuilding.getIsPrefer() == 1) {
			isPrefer = true;
		}
		return isPrefer;
	}
	
	/**
	 * 获取选择性周期缴费随机立减优惠金额
	 * @param carNum
	 * @param realRoomId
	 * @param payMonth
	 * @param isOpenPropertyPrefer 是否开通物业缴费随机立减
	 * @return
	 */
	private PayAmtTmpDto getPayAlterPeriodPrefer(BigInteger userId, BigInteger realRoomId, String payMonth, boolean isOpenPropertyPrefer){
		Long totalAmt = 0L;
		Long preferAmt = 0L;
		int month = Integer.valueOf(payMonth);
		if(plotpropertyService.checkAlterPeriodMonth(userId,month)){
			Long totalAmtByOneMonth = alterPeriodDao.getBasicSumAmt(realRoomId);
			if(totalAmtByOneMonth==null){totalAmtByOneMonth = 0L;}
			
			totalAmt = month*totalAmtByOneMonth;
			if(isOpenPropertyPrefer){
				preferAmt = alterPeriodDao.getPreferentialRandom(userId, realRoomId, totalAmt, month);
				if(preferAmt == null) {
					preferAmt = 0L;
				}
			}
		} else {
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.VALIDATE_ERR);
    		businessRuntimeException.setErrorMsg("不存在该缴费月份！");
    		throw businessRuntimeException;
		}
		
		PayAmtTmpDto payAmtTmp = new PayAmtTmpDto();
		payAmtTmp.setAmtBt(BigDecimal.valueOf(preferAmt));
		//选择周期滞纳金
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tRealRoomId",realRoomId);
		List<AlterPeriodDataDetail> lpdds = alterPeriodDataDetailBaseDao.selectAlterPeriodDataDetailByCondition(paramMap, false);
		if(lpdds!=null && lpdds.size()>0){
			AlterPeriodDataDetail alterPeriodDataDetail  = lpdds.get(0);
			Long lateFeeAmt = alterPeriodDataDetail.getLatefeeAmount() == null ? 0L : alterPeriodDataDetail.getLatefeeAmount();
			totalAmt += lateFeeAmt;
		}
		
		payAmtTmp.setTotalAmt(BigDecimal.valueOf(totalAmt));
		payAmtTmp.setDeduAmt(BigDecimal.ZERO);
		return payAmtTmp;
	}
	
	/**
	 * 获取非选择性周期缴费随机立减优惠金额
	 * @param userId
	 * @param payBillId
	 * @param dataFromType
	 * @return
	 */
	private PayAmtTmpDto getPayUnalterPeriodPrefer(BigInteger userId, BigInteger payBillId, String dataFromType){
		PropertyDetail propertyDetail = null;
		BigDecimal preferAmt = BigDecimal.ZERO;// 单位：元
		BigDecimal deduAmt = BigDecimal.ZERO;// 单位：分
		if(PropertyConstant.DataFromType.Third.equals(dataFromType)) {//第三方对接物业软件
			propertyDetail = plotpropertyService.getPlotpropertyNotPayOrderDetailFrom3rd(userId);
			if(propertyDetail == null) {
				BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.COMMUNICATE_ERR);
	    		businessRuntimeException.setErrorMsg("获取数据失败！");
	    		throw businessRuntimeException;
			} else {
				BigDecimal preferentialAmt = propertyDetail.getPreferentialAmt();
				if(preferentialAmt!=null){
					preferAmt = preferentialAmt;
				}
			}
		} else {//自营物业平台
			Map<String,Object> resMap = plotpropertyService.getPreferential(userId, payBillId);// 产生随机立减
			if(resMap!=null){
				Object preferentialAmt = resMap.get("preferentialAmt");
				if(preferentialAmt!=null){
					preferAmt = BigDecimal.valueOf(Double.valueOf(String.valueOf(preferentialAmt)));
				}
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("payBillId", payBillId);
			paramMap.put("userId", userId);
			PlotpropertyOrderEntity plotpropertyOrder = plotpropertyService.getPlotpropertyNotPayOrderDetail(paramMap);
			if(plotpropertyOrder!=null && plotpropertyOrder.getPayBillEntity()!=null){
				PayBillEntity payBillEntity = plotpropertyOrder.getPayBillEntity();
				dealBcBill(payBillEntity);
			}
			
			propertyDetail = plotpropertyService.convertPropertyDetail2(plotpropertyOrder);
			if(plotpropertyOrder!=null && plotpropertyOrder.getPayBillEntity()!=null
					&& plotpropertyOrder.getPayBillEntity().getDeduPrice()!=null && plotpropertyOrder.getPayBillEntity().getFinanceStatus()!=null 
					&& plotpropertyOrder.getPayBillEntity().getFinanceStatus()==1){
				Long dedu = plotpropertyOrder.getPayBillEntity().getDeduPrice();
				deduAmt = BigDecimal.valueOf(dedu);
			}
			/**有物业宝抵扣，随机立减金额为0*/
			if(deduAmt.doubleValue()>0){
				if(preferAmt.doubleValue()>0){
					paramMap.clear();
					paramMap.put("userId", userId);
					paramMap.put("payBillId", payBillId);
					paramMap.put("prefer", 0L);
					
					propertyDao.updatePaybillUserPrefer(paramMap);
					preferAmt = BigDecimal.ZERO;
				}
			}
		}
		BigDecimal totalAmt = propertyDetail.getTotalAmt().multiply(BigDecimal.valueOf(100));
		//BigDecimal realAmt = propertyDetail.getSucPay();
		preferAmt = preferAmt.multiply(BigDecimal.valueOf(100));//totalAmt.subtract(realAmt.multiply(BigDecimal.valueOf(100)));
		
		PayAmtTmpDto payAmtTmp = new PayAmtTmpDto();
		payAmtTmp.setAmtBt(preferAmt);
		payAmtTmp.setTotalAmt(totalAmt);
		payAmtTmp.setDeduAmt(deduAmt);
		return payAmtTmp;
	}
	
	/**
	 * 物业账单【托收中】缴费处理
	 * @param payBillEntity
	 */
	public static final void dealBcBill(PayBillEntity payBillEntity){
		if(payBillEntity!=null && payBillEntity.getBankCollectionStatus()!=null && payBillEntity.getBankCollectionStatus()==1){
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.VALIDATE_ERR);
			businessRuntimeException.setErrorMsg("待缴账单中存在【托收中】的账单，请退出【物业缴费】，重新进入！");
			throw businessRuntimeException;
		}
	}

	@Override
	public Double getTotalAmtNow(BigInteger userId, String sessionId, BigInteger gbId) {
		RemainBillInfoDto remainBillInfo = getRemainBillInfo(userId, sessionId, gbId);
		Double amt = 0.0;
		List<RemainBillDto> remainBills = remainBillInfo.getRemainBills();
		for (RemainBillDto remainBill : remainBills) {
			BigInteger payBillId = remainBill.getPayBillId();
			amt += !DataUtil.isEmpty(remainBill.getBillAmt()) ? Double.parseDouble(remainBill.getBillAmt()) : 0.0;
			List<RemainBillDto> unpaidBillInfoList = getUnpaidBillInfoList(payBillId);
			for (RemainBillDto remainBillDto : unpaidBillInfoList) {
				amt += !DataUtil.isEmpty(remainBillDto.getBillRelAmt()) ? Double.parseDouble(remainBillDto.getBillRelAmt()) : 0.0;
			}
		}
		List<AlterPayBillDto> alterPayBillDtos = remainBillInfo.getAlterPayBillDtos();
		if(alterPayBillDtos != null) {
			for (AlterPayBillDto alterPayBillDto : alterPayBillDtos) {
				List<AlterMonth2Bills> monthAndPayBill = alterPayBillDto.getMonthAndPayBill();
				if(!DataUtil.isEmpty(monthAndPayBill)) {
					AlterMonth2Bills alterMonth2Bills = monthAndPayBill.get(0);
					Double alterAmt = !DataUtil.isEmpty(alterMonth2Bills.getAmt()) ? Double.parseDouble(alterMonth2Bills.getAmt()) : 0.0;
					amt += alterAmt;
				}
			}
		}
		
		return amt;
	}

	/**
	 * 获取总红包分到每个账单所占的红包金额
	 * @return
	 */
	private List<BigDecimal> getSubHbAmounts(BigInteger userId, BigDecimal hbAmount, List<ConfirmPayReq> confirmPayReqs, String sessionId){
		List<BigDecimal> subHbAmts = null;// 各账单红包所占金额
		if(hbAmount==null || hbAmount.doubleValue() == 0){
			subHbAmts = new ArrayList<BigDecimal>();
			for(int i=0; i<confirmPayReqs.size(); i++){
				subHbAmts.add(BigDecimal.ZERO);
			}
		} else {
			// 1、红包金额校验
			SimpleHbBalanceEntity simpleHbBalanceEntity = redenvelopeService.getAllBalanceCollectInfo(userId, 0);
			if (simpleHbBalanceEntity == null || simpleHbBalanceEntity.getBalance() == null
					|| simpleHbBalanceEntity.getBalance() < hbAmount.longValue()) {
				BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.VALIDATE_ERR);
				businessRuntimeException.setErrorMsg("红包金额错误！");
				throw businessRuntimeException;
			} else {
				List<BigDecimal> relAmts = new ArrayList<BigDecimal>();// 各账单实付金额
				BigDecimal totalAmt = BigDecimal.ZERO;
				for(ConfirmPayReq cp : confirmPayReqs){// 获取各账单实付金额
					PayAmtTmpDto payAmtTmp = null;
					if(StringUtils.isNotBlank(cp.getCarNum())){// 车禁缴费
						payAmtTmp = getPayCarPrefer(cp.getCarNum(), Integer.valueOf(cp.getPayMonth()), sessionId);
					} else if(cp.getGbbccId()!=null) {//物业预存
						payAmtTmp = getPropertyRechargePerferAmt(userId, cp.getGbbccId(), cp.getRechargeAmt());
					} else if(cp.getPayBillId()==null && StringUtils.isNotBlank(cp.getPayMonth()) && PropertyConstant.DataFromType.Jfq.equals(cp.getDataFromType())){// 选择性周期
						RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
						boolean isOpenPropertyPrefer = isOpenPropertyPrefer(userId);
						payAmtTmp = getPayAlterPeriodPrefer(userId, realRoom.getId(), cp.getPayMonth(), isOpenPropertyPrefer);
					} else {// 非选择周期缴费
						payAmtTmp = getPayUnalterPeriodPrefer(userId, cp.getPayBillId(), cp.getDataFromType());
					}
					
					logger.debug("getSubHbAmounts.payAmtTmp==>"+JSON.toJSON(payAmtTmp)+";cp==>"+JSON.toJSONString(cp));
					
					BigDecimal realAmt = payAmtTmp.getTotalAmt().subtract(payAmtTmp.getAmtBt()).subtract(payAmtTmp.getDeduAmt());
					relAmts.add(realAmt);
					totalAmt = totalAmt.add(realAmt);
				}
				
				subHbAmts = MoneyUtil.getSubMoney(totalAmt, relAmts, hbAmount);
			}
		}
		
	    return subHbAmts;
	}
	
	/**
	 * 车禁订单处理
	 * @param userId
	 * @param carNum
	 * @param month
	 * @param hbAmt
	 * @param subChannelId
	 * @return
	 */
	private PreOrderDto dealCarOrder(BigInteger userId, String carNum, String month, BigDecimal hbAmt, Long subChannelId, String sessionId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCarNum", carNum);
		List<CarNumList> carNumLists = carNumListBaseService.getCarNumListByCondition(paramMap);
		
		PayCarKeyOrderParam param = new PayCarKeyOrderParam();
		param.setUserId(userId);
		param.setCarId(carNumLists.get(0).getId());
		param.setPayNum(Long.valueOf(month));
		param.setSubChannelId((subChannelId==null)? null : subChannelId.intValue());
		param.setNeedBill(0);
		param.setHbAmt(hbAmt);
		param.setFromTotalOrder(true);
		param.setSessionId(sessionId);
		PreOrderDto preOrder = accessService.payCarKeyOrder(param);
		
		return preOrder;
	}
	
	/**
	 * 总订单处理
	 * 
	 * @param userId
	 * @param hbTotalAmount
	 * @param realAmt 单位：分
	 * @param amtBt 单位：分
	 * @param subChannelId
	 * @param buyTime
	 * @return
	 */
	private ConfirmPayResultDto dealTotalOrder(BigInteger userId, BigDecimal hbTotalAmount, BigDecimal realAmt, BigDecimal amtBt, 
			Long subChannelId, String buyTime){
		EbuyOrder ebuyOrder = new EbuyOrder();
		ebuyOrder.setType(EbuyDict.EbuyOrder_Type.Total_Property_Bill);
		
		BigInteger orderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
		ebuyOrder.setId(orderId);
		ebuyOrder.setBuyerId(userId);
		
		BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		ebuyOrder.setCurrRoomId(roomId);
		ebuyOrder.setCreater(userId);
		
		ebuyOrder.setBuyTime(buyTime);
		ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(orderId));
		ebuyOrder.setPayMethod(null);
		ebuyOrder.setPayTime(null);
		ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
		ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);// 支付状态为待支付
		ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);// 发货状态为未发货
		ebuyOrder.setTotalDeliveryFee(0L);// 总配送费
		
		ebuyOrder.setAmount(realAmt.longValue()); // 应付金额 （单位：分）
		ebuyOrder.setTotalCouponAmount(amtBt.longValue()); // 优惠金额（单位：分）
		ebuyOrder.setSubChannel((subChannelId==null)? null : subChannelId.toString());
		ebuyOrder.setSys0AddTime(buyTime);
		ebuyOrder.setSys0AddUser(userId);
		ebuyOrder.setSys0DelState(0);
		// 1、新增订单
		int affectedRowSize = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
		if(affectedRowSize <= 0){
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.BUSINESS_FAILED);
    		businessRuntimeException.setErrorMsg("新增总订单失败！");
    		throw businessRuntimeException;
		}
		
		// 2、红包处理
		boolean isUseHb = false;
		if(hbTotalAmount!=null && hbTotalAmount.doubleValue()>0){
			isUseHb = true;
			commonRedenvelopeService.updatePayCouponByJFB(ebuyOrder, hbTotalAmount.longValue());
		}
		
		ConfirmPayResultDto confirmPayResult = new ConfirmPayResultDto();
		confirmPayResult.setOrderId(orderId);
		confirmPayResult.setUseHb(isUseHb);
		
		return confirmPayResult;
	}
	
	/**
	 * 免单处理
	 * @param realAmt
	 * @param hbTotalAmount
	 * @param amountBt
	 * @return 是否免单
	 */
	private boolean dealFreeOrder(BigInteger orderId, BigDecimal realAmt, BigDecimal hbTotalAmount, BigDecimal amountBt){
		boolean isFree = false;
		if (realAmt.doubleValue()<=0) {
			commPayService.paySuccessOperate(orderId, getFreePayMethod(hbTotalAmount, amountBt));// 纯优惠支付
			isFree = true;
		}
		return isFree;
	}
	
	/**
	 * 获取免单支付方式
	 * @param hbAmt 红包金额
	 * @param totalCouponAmt 总优惠金额
	 * @return
	 */
	private Integer getFreePayMethod(BigDecimal hbAmt, BigDecimal totalCouponAmt){
		if(hbAmt!=null && totalCouponAmt!=null && hbAmt.doubleValue()==totalCouponAmt.doubleValue()){
			return EbuyDict.EbuyPay_PayMethod.PureRedEnvelope;
		}
		return EbuyDict.EbuyPay_PayMethod.PureDiscount;
	}
	
	/**
	 * 子账单关系处理
	 * @param userId
	 * @param totalOrderId
	 * @param buyTime
	 * @param ebuyOrderRelations
	 */
	private void dealSubOrderRelation(BigInteger userId, BigInteger totalOrderId, String buyTime, List<EbuyOrderRelation> ebuyOrderRelations){
		List<BigInteger> eorIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_relation, ebuyOrderRelations.size());
		for(int i=0; i < ebuyOrderRelations.size(); i++){
			EbuyOrderRelation ebuyOrderRelation = ebuyOrderRelations.get(i);
			ebuyOrderRelation.setId(eorIds.get(i));
			ebuyOrderRelation.setParentId(totalOrderId);
			ebuyOrderRelation.setSys0AddTime(buyTime);
			ebuyOrderRelation.setSys0AddUser(userId);
			ebuyOrderRelation.setSys0DelState(0);
		}
		ebuyOrderRelationBaseDao.insertEbuyOrderRelationBatch(ebuyOrderRelations);
	}
	
	/**
	 * 支付结果
	 * @param payStatus 支付状态
	 * @param clientPayStatus 客户端支付状态
	 * @return
	 */
	private String getPayResult(Integer payStatus, Integer clientPayStatus) {
		if (payStatus == EbuyDict.EbuyOrder_PayStatus.Not_Pay) {
			if (clientPayStatus != null && clientPayStatus == EbuyDict.EbuyOrder_ClientPayStatus.Client_Pay_Success) {
				return "系统支付确认中";
			} else {
				return "未支付";
			}
		} else if (payStatus == EbuyDict.EbuyOrder_PayStatus.Pay_Success) {
			return "支付成功";
		} else {
			return "支付失败";
		}
	}
	
	/**
	 * 获取支付详情中的红包金额
	 * @param orderId
	 * @return
	 */
	private Long getHbAmtForDetail(BigInteger orderId){
		Long hbAmt = 0L;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tEbuyOrderFId", orderId);
		paramMap.put("type", 1);
		List<PayCoupon> payCouponList = payCouponBaseDao.selectPayCouponByCondition(paramMap, false);
		if(payCouponList!=null && payCouponList.size()>0){
			hbAmt = payCouponList.get(0).getAmount();
		} else {
			hbAmt = 0L;
		}
		
		return hbAmt;
	}
	
	/**
	 * 获取银行卡优惠金额
	 * @param orderId
	 * @param payMethod
	 * @return
	 */
	private Long getBankDiscount(BigInteger orderId, String payMethod){
		Long amountBt = 0L;
		Map<String, Object> paramMap = null;
		if(EbuyDict.EbuyPay_PayMethod.SqPay.toString().equals(payMethod)){// 双乾支付，查询双乾优惠
			paramMap = new HashMap<String, Object>();
			paramMap.put("tEbuyOrderFId", orderId);
			List<CashSqpayBt> cashSqpayBts = cashSqpayBtDao.selectCashSqpayBtByCondition(paramMap, false);
			if(cashSqpayBts!=null && cashSqpayBts.size()>0){
				CashSqpayBt cashSqpayBt = cashSqpayBts.get(0);
				amountBt = cashSqpayBt.getAmountBt();
			}
		} 
		return  amountBt;
	}
	
	/**
	 * 获取支付方式名称
	 * @param type
	 * @return
	 */
	private String getOrderPayMethod(String type) {
		if(EbuyDict.EbuyPay_PayMethod.WeiXin.toString().equals(type)){
			return "微信支付";
		} else if(EbuyDict.EbuyPay_PayMethod.Alipay.toString().equals(type)){
			return "支付宝";
		} else if(EbuyDict.EbuyPay_PayMethod.UnionPay.toString().equals(type)){
			return "银联支付";
		} else if(EbuyDict.EbuyPay_PayMethod.PureRedEnvelope.toString().equals(type)){
			return "纯粮票支付";
		} else if(EbuyDict.EbuyPay_PayMethod.PurePoint.toString().equals(type)){
			return "纯积分支付";
		} else if(EbuyDict.EbuyPay_PayMethod.WeiXin_Light.toString().equals(type)){
			return "微信轻应用支付";
		} else if(EbuyDict.EbuyPay_PayMethod.PureSupriseGiftList.toString().equals(type)){
			return "纯优惠券支付";
		} else if(EbuyDict.EbuyPay_PayMethod.PureDiscount.toString().equals(type)){
			return "随机优惠支付";
		} else if(EbuyDict.EbuyPay_PayMethod.SqPay.toString().equals(type)){
			return "银行卡支付";
		}
		return "未知";
	}
	
	/**
	 * 获取支付方式名称
	 * @param type
	 * @return
	 */
	private String getPayBillPayMethod(String type) {
		if(PropertyConstant.BillPayMethod.Zai_Xian.toString().equals(type)){
			return "用户在线支付";
		} else if(PropertyConstant.BillPayMethod.Shou_Gong.toString().equals(type)){
			return "物业公司手工标记";
		} else if(PropertyConstant.BillPayMethod.Hua_Kou.toString().equals(type)){
			return "物业代扣卡划扣";
		} else if(PropertyConstant.BillPayMethod.Di_Kou.toString().equals(type)){
			return "物业宝抵扣";
		} else if(PropertyConstant.BillPayMethod.Bank.toString().equals(type)){
			return "银行卡托收";
		}
		return "未知";
	}
	
	/**
	 * 处理已缴账单信息
	 * @param paidBillItems
	 * @return
	 */
	private List<PaidBillItemDto> dealPaidBillInfo(List<PaidBillItemDto> paidBillItems){
		if(paidBillItems!=null && paidBillItems.size()>0){
			String carIcon = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.CAR_BILL_ICON);
			for(PaidBillItemDto paidBillItem : paidBillItems){
				paidBillItem.setBillTitle(paidBillItem.getBillTitle().concat("物业缴费账单"));
				
				Long totalRealAmt = 0L;
				Long totalAmtBt = 0L;
				
				List<BillDetailDto> billDetails = paidBillItem.getBillDetails();
				for(BillDetailDto billDetail : billDetails){
					Long realAmt = Long.valueOf(billDetail.getAmount());
					Long amtBt = Long.valueOf(billDetail.getDiscount());
					
					totalRealAmt += realAmt;
					totalAmtBt += amtBt;
					
					billDetail.setAmount(PriceUtil.div100s(realAmt + amtBt));
					billDetail.setDiscount(PriceUtil.div100s(amtBt));
					if(PropertyConstant.BillDetailType.Car.equals(billDetail.getType())){
						billDetail.setIcon(carIcon);
					} else {
						String icon = ApplicationContextBothUtil.getAbsolutePath(PropIconUtil.getBillIcon(billDetail.getTypeName()), SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH, null);
						billDetail.setIcon(icon);
					}
					if(!paidBillItem.isRechargeBill()){
						removeRepeatMonthFromBillName(billDetail);
					}
				}
				
				Long totalAmt = totalRealAmt + totalAmtBt;
				paidBillItem.setTotalAmt(PriceUtil.div100s(totalAmt));
				paidBillItem.setDiscountAmt(PriceUtil.div100s(totalAmtBt));
				paidBillItem.setRealAmt(PriceUtil.div100s(totalRealAmt));
			}
		}
		
		return paidBillItems;
	}

	/**
	 * 处理账单名称，当开始月和结束月一样时，只须保留一个<br>
	 * eg.  "03-03月水电费" -> "3月水电费"
	 * @Author wenfq 2017-10-16
	 * @param billDetail
	 */
	private void removeRepeatMonthFromBillName(BillDetailDto billDetail) {
		String regExp = "\\d{2}";
		Pattern pattern = Pattern.compile(regExp);
		// billDetail.getName() = "03-04月水电费"，当月份一样时，只取其中一个数字即可
		Matcher m = pattern.matcher(billDetail.getName());
		String preMonth = "";
		boolean hasSameMonth = false;
		while(m.find()){
            hasSameMonth = StringUtils.isNotEmpty(preMonth) && preMonth.equals(m.group());
            if(hasSameMonth) break;
            preMonth = m.group();
        }
		if(hasSameMonth) {
            billDetail.setName(preMonth + billDetail.getName().substring(5));
        }
	}

	/**
	 * 获取车禁账单详情信息（未缴）
	 * @param carNum
	 * @param payMonth
	 * @return
	 */
	private CarUnPaidBillDetailDto getUnPaidCarBillDetail(String carNum, int payMonth, String sessionId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCarNum", carNum);
		List<CarNumList> carNumLists = carNumListBaseService.getCarNumListByCondition(paramMap);
		CarNumList carNumList = carNumLists.get(0);
    	BigInteger gbId = carNumList.gettGroupBuildingFId();
    	MonthCarInfo monthCarInfo = thirdCarFactory.getMonthCarPayInfo(gbId, carNum, payMonth);
    	
    	String amt = PriceUtil.div100s(monthCarInfo.getRealAmt());
    	BigDecimal monthFee = BigDecimalUtil.div(BigDecimal.valueOf(monthCarInfo.getRealAmt()), BigDecimal.valueOf(payMonth));
    	carNumList.setFee(monthFee.longValue());
		
		CarUnPaidBillDetailDto carUnPaidBillDetail = new CarUnPaidBillDetailDto();
		carUnPaidBillDetail.setAmount(amt);
		carUnPaidBillDetail.setDesc(payMonth+"个月月卡费");
		
		GroupBuilding gb = groupBuildingBaseDao.selectGroupBuildingBySeqId(carNumList.gettGroupBuildingFId());
		carUnPaidBillDetail.setTitle(gb.getName() + "月卡(元)");
		
		PropertyManagement propertyManagement = propertyManagementBaseService.getPropertyManagementBySeqId(gb.gettPropertyManagementFId());
		if(propertyManagement != null) {
			carUnPaidBillDetail.setTel(propertyManagement.getTel());
		}
		
		CarBillTailDto carBillTail = new CarBillTailDto();
		carBillTail.setCarFee(PriceUtil.div100s(carNumList.getFee()));
		String expireDate = DateUtils.convertDateToStr(new Date(monthCarInfo.getExpire()), "yy.MM.dd");
		carBillTail.setExpire(expireDate);
		carBillTail.setCarNum(carNumList.gettCarNum());
		carBillTail.setMonth(payMonth + "个月");
		carBillTail.setParking(gb.getName() + "停车场");
		
		carUnPaidBillDetail.setCarBillTail(carBillTail);
		
		return carUnPaidBillDetail;
	}
	
	/**
	 * 获取物业账单详情信息（未缴）
	 * @param param
	 * @return
	 */
	private PropertyUnPaidBillDetailDto getUnPaidPropertyBillDetail(UnPaidBillDetailReq param){
		if(StringUtils.isNotBlank(param.getPayMonth()) && CycleCfgDict.RechargeMode.ALTER_CYCLE.equals(param.getCycleType())){// 选择性周期
			PropertyUnPaidBillDetailDto propertyUnPaidBillDetail = new PropertyUnPaidBillDetailDto();
			// 管理处电话
			GroupBuilding gb = commonRoomService.getGroupBuildingByUserId(param.getUserId());
			PropertyManagement propertyManagement = propertyManagementBaseService.getPropertyManagementBySeqId(gb.gettPropertyManagementFId());
			if(propertyManagement != null) {
				propertyUnPaidBillDetail.setTel(propertyManagement.getTel());
			}
			
			RealRoom realRoom = commonRoomService.selectRealRoomByUserId(param.getUserId());
			BigInteger realRoomId = realRoom.getId();

			PropertyBillTailDto propertyBillTail = plotpropertyService.getRoomAddressAndPPName(realRoomId);
			//propertyBillTail.setPayAddress(propertyAlterBillInfo.getAddress());
			//propertyBillTail.setProprietorName(propertyAlterBillInfo.getProprietorName());

			BigDecimal totalAmt = BigDecimal.ZERO;
			String[] ids = param.getPayBillId().split(",");
			List<BigInteger> idsList = new ArrayList<>();
			for(int i=0; i<ids.length;i++){
				idsList.add(new BigInteger(ids[i]));
			}
			List<PayBillDetailDto> payBillDetails = plotpropertyService.getPayBillDetailByIds(idsList);
			String payBillTypeName = payBillDetails.get(0).getBillName();
			for (PayBillDetailDto payBillDetail : payBillDetails) {
				BigDecimal bigDecimal = new BigDecimal(Double.parseDouble(payBillDetail.getBillAmount()));
				if(!DataUtil.isEmpty(payBillDetail.getBillMonthSize())) {
					bigDecimal = bigDecimal.multiply(new BigDecimal(payBillDetail.getBillMonthSize()));
				}
				BigDecimal price = BigDecimalUtil.div100(bigDecimal);
				String billAmount = "￥".concat(String.format("%.2f", price.doubleValue()));
				payBillDetail.setBillAmount(billAmount);
				totalAmt = totalAmt.add(price);
			}

			propertyBillTail.setPayBillDetails(payBillDetails);
			propertyUnPaidBillDetail.setPropertyBillTail(propertyBillTail);
			
			propertyUnPaidBillDetail.setTitle(payBillTypeName.concat("账单(元)"));
			propertyUnPaidBillDetail.setDesc(param.getPayMonth() + "个月" + payBillTypeName);
			propertyUnPaidBillDetail.setAmount(String.format("%.2f", totalAmt.doubleValue()));
			
			return propertyUnPaidBillDetail;
		} else {// 非选择周期缴费 
			if(PropertyConstant.DataFromType.Third.equals(param.getDataFromType())) {//第三方对接物业软件
				PropertyDetail propertyDetail = plotpropertyService.getPlotpropertyNotPayOrderDetailFrom3rd(param.getUserId());
				if(propertyDetail == null) {
					BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.COMMUNICATE_ERR);
		    		businessRuntimeException.setErrorMsg("获取数据失败！");
		    		throw businessRuntimeException;
				}
				PropertyUnPaidBillDetailDto propertyUnPaidBillDetail = new PropertyUnPaidBillDetailDto();
				{
					GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(param.getUserId());
					//物业费类型
					Map<String, Object> billTypeMap = new HashMap<String, Object>();
					billTypeMap.put("gbId",groupBuilding.getId());
//					billTypeMap.put("isPropFee",2);//物业费
//					billTypeMap.put("paytimeType",2);//周期缴费
					
					List<PayBillType> pbTypeList = payBillTypeBaseDao.selectPayBillTypeByCondition(billTypeMap,false);
					if(pbTypeList != null && pbTypeList.size() > 0) {
						PayBillType payBillType = pbTypeList.get(0);
						propertyUnPaidBillDetail.setTitle(payBillType.getName()+"账单");
						propertyUnPaidBillDetail.setDesc("1个月"+payBillType.getName());
					} else {
						propertyUnPaidBillDetail.setTitle("物业费账单");
						propertyUnPaidBillDetail.setDesc("物业费");
					}
				}
				propertyUnPaidBillDetail.setTel(propertyDetail.getManagerTel());
				BigDecimal amount = propertyDetail.getTotalAmt();
				if(amount!=null){
					propertyUnPaidBillDetail.setAmount(String.format("%.2f", amount.doubleValue()));
				}
				
				PropertyBillTailDto propertyBillTail = new PropertyBillTailDto();
				
				List<PayBillDetailDto> payBillDetails = new ArrayList<PayBillDetailDto>();
				List<Map<String,Object>> detailMaps = propertyDetail.getFeeDetail();
				if(detailMaps!=null && detailMaps.size()>0){
					for(Map<String,Object> detailMap : detailMaps){
						PayBillDetailDto payBillDetail = new PayBillDetailDto();
						Object billAmount = detailMap.get("totalPrice");
						payBillDetail.setBillAmount(billAmount!=null? String.valueOf(billAmount):StringUtils.EMPTY);
						Object billName = detailMap.get("name");
						payBillDetail.setBillName(billName!=null? String.valueOf(billName):StringUtils.EMPTY);
						
						payBillDetails.add(payBillDetail);
					}
				}
				
				propertyBillTail.setPayBillDetails(payBillDetails);
				propertyBillTail.setProprietorName(propertyDetail.getProprietorName());
				propertyBillTail.setPayAddress(propertyDetail.getAddrBuilding()+"-"+propertyDetail.getAddrRoom());
				
				propertyUnPaidBillDetail.setPropertyBillTail(propertyBillTail);
				return propertyUnPaidBillDetail;
			} else {//自营物业平台
				// 物业宝抵扣
				PropertyUnPaidBillDetailDto propertyUnPaidBillDetail = propertyDao.selectUnPaidPropertyBillDetailForJfq(param.getPayBillId());
				
				String dedu = getDeduByPayBillId(new BigInteger(param.getPayBillId()));
				if(dedu!=null){
					if(propertyUnPaidBillDetail!=null){
						PropertyBillTailDto propertyBillTail = propertyUnPaidBillDetail.getPropertyBillTail();
						if(propertyBillTail!=null){
							List<PayBillDetailDto> payBillDetails = propertyBillTail.getPayBillDetails();
							if(payBillDetails==null){
								payBillDetails = new ArrayList<PayBillDetailDto>();
							}
							PayBillDetailDto payBillDetail = new PayBillDetailDto();
							payBillDetail.setBillAmount(dedu);
							payBillDetail.setBillName("物业宝抵扣");
							payBillDetails.add(payBillDetail);
						}
					}
				}
				
				return propertyUnPaidBillDetail;
			}
		}
	}
	
	/**
	 * 获取物业宝抵扣
	 * @param orderId
	 * @return
	 */
	private String getDeduByPayBillId(BigInteger payBillId){
		// 物业宝抵扣处理
		PayBill payBill = payBillBaseDao.selectPayBillBySeqId(payBillId);
		if(payBill!=null 
				&& payBill.getFinanceStatus()!=null && payBill.getFinanceStatus()==1
				&& payBill.getDeduPrice()!=null && payBill.getDeduPrice()>0){
			return "￥ ".concat(PriceUtil.div100s(payBill.getDeduPrice()));
		}
		return null;
	}

	/**
	 * 字符串时间比较 str >= str1 == true;
	 * @param str
	 * @param str1
	 * @return
	 */
	private static int comparaStrDate(String str, String str1) {
		if(DataUtil.isEmpty(str) && !DataUtil.isEmpty(str1)) {
			return -1;
		}
		if(!DataUtil.isEmpty(str) && DataUtil.isEmpty(str1)) {
			return 1;
		}
		if(DataUtil.isEmpty(str) && DataUtil.isEmpty(str1)) {
			return 1;
		}
		long date = DateUtils.convertStrToDate(str, "yyyy-MM").getTime();
		long date1 = DateUtils.convertStrToDate(str1, "yyyy-MM").getTime();
		if(date >  date1) {
			return 1;
		} else if(date == date1) {
			return 0;
		}
		return -1;
	}
	
}
