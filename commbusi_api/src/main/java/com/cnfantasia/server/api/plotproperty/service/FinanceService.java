package com.cnfantasia.server.api.plotproperty.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.service.IAccessService;
import com.cnfantasia.server.api.access.service.ThirdCarFactory;
import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.service.ICommonLockService;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.plotproperty.dao.FinanceDao;
import com.cnfantasia.server.api.plotproperty.entity.FinanceAgent;
import com.cnfantasia.server.api.plotproperty.entity.FinanceBuyEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceDeductionDetail;
import com.cnfantasia.server.api.plotproperty.entity.FinanceDeductionEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceLogEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceProfitEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceReqEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceSum;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.carNumList.dao.ICarNumListBaseDao;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.dao.ICarNumPayLogBaseDao;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.propertyFeeDetail.service.IPropertyFeeDetailBaseService;

/**
 * 物业宝相关
 * 
 * @author yewj
 * 
 */
public class FinanceService {

	private Log logger = LogFactory.getLog(getClass());

	private FinanceDao financeDao;

	private ICommonLockService commonLockService;

	private IPropertyFeeDetailBaseService propertyFeeDetailBaseService;
	
	private ICarNumListBaseDao carNumListBaseDao;

	private IHomeMessageService homeMessageService;

	public IHomeMessageService getHomeMessageService() {
		return homeMessageService;
	}
	
	public void setCarNumListBaseDao(ICarNumListBaseDao carNumListBaseDao) {
		this.carNumListBaseDao = carNumListBaseDao;
	}

	public void setHomeMessageService(IHomeMessageService homeMessageService) {
		this.homeMessageService = homeMessageService;
	}
	
	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}

	public void setCommonLockService(ICommonLockService commonLockService) {
		this.commonLockService = commonLockService;
	}

	public void setPropertyFeeDetailBaseService(
			IPropertyFeeDetailBaseService propertyFeeDetailBaseService) {
		this.propertyFeeDetailBaseService = propertyFeeDetailBaseService;
	}

	public Long getPropertyFee(Long realRoomId) {
		return financeDao.getPropertyFee(realRoomId);
	}

	public FinanceReqEntity getFinanceReqForResp(Map<String, Object> paramMap) {
		return financeDao.getFinanceReqForResp(paramMap);
	}

	public FinanceReqEntity getFinanceReqForCar(Map<String, Object> paramMap) {
		return financeDao.getFinanceReqForCar(paramMap);
	}

	public int insertFinanceReq(FinanceReqEntity financeReqEntity) {
		return financeDao.insertFinanceReq(financeReqEntity);
	}

	public boolean isExistFinanceBuyCount(FinanceBuyEntity financeBuyEntity) {
		return financeDao.isExistFinanceBuyCount(financeBuyEntity);
	}

	public int insertFinanceBuy(FinanceBuyEntity financeBuyEntity) {
		return financeDao.insertFinanceBuy(financeBuyEntity);
	}

	public int insertFinanceProfit(FinanceProfitEntity financeProfitEntity) {
		return financeDao.insertFinanceProfit(financeProfitEntity);
	}

	public List<FinanceDeductionEntity> getFinanceForDeductionList(
			Map<String, Object> paramMap) {
		return financeDao.getFinanceForDeductionList(paramMap);
	}

	public int updatePropertyDeductionPrice(
			FinanceDeductionEntity financeDeductionEntity) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tPayBillFId", financeDeductionEntity.getPayBillId());
		List<PropertyFeeDetail> feeDetailList = propertyFeeDetailBaseService
				.getPropertyFeeDetailByCondition(paramMap);
		boolean isExitsOther = false;
		boolean isDeductionAll = true;
		long remainDeduPrice = financeDeductionEntity.getDeductionPrice()
				.longValue();
		long deduPrice = 0;

		for (int i = feeDetailList.size() - 1; i >= 0; i--) {
			PropertyFeeDetail feeDetail = feeDetailList.get(i);
			if (feeDetail.getType() != 1 && feeDetail.getType() != 2) {
				isExitsOther = true;
				feeDetailList.remove(i);
			}
		}
		Collections.sort(feeDetailList, new Comparator<PropertyFeeDetail>() {
			@Override
			public int compare(PropertyFeeDetail o1, PropertyFeeDetail o2) {
				return o1.getType() - o2.getType();
			}
		});

		for (PropertyFeeDetail feeDetail : feeDetailList) {
			if (remainDeduPrice >= feeDetail.getTotalPrice()) {
				feeDetail.setAllowancePrice((feeDetail.getTotalPrice()==null)?0:feeDetail.getTotalPrice().longValue());
				remainDeduPrice -= feeDetail.getTotalPrice();
				deduPrice += feeDetail.getTotalPrice();
			} else {
				feeDetail.setAllowancePrice(remainDeduPrice);
				deduPrice += remainDeduPrice;
				remainDeduPrice = 0;
				isDeductionAll = false;
			}
		}

		if (!isExitsOther && isDeductionAll) {
			financeDao.updatePayBillStatus(
					financeDeductionEntity.getPayBillId(), 1, deduPrice);
		} else {
			financeDao.updatePayBillStatus(
					financeDeductionEntity.getPayBillId(), 2, deduPrice);
		}
		return propertyFeeDetailBaseService
				.updatePropertyFeeDetailBatch(feeDetailList);
		// boolean isExitsOther =
		// financeDao.isExitsPaybillFeeOther(financeDeductionEntity.getPayBillId(),
		// BigDecimalUtil.mul100(financeDeductionEntity.getDeductionPrice()));
		// return
		// financeDao.updatePropertyDeductionPrice(financeDeductionEntity);
	}

	public int insertFinanceDeduction(
			FinanceDeductionEntity financeDeductionEntity) {
		return financeDao.insertFinanceDeduction(financeDeductionEntity);
	}

	public int updateDeductionCount(String orderNo) {
		return financeDao.updateDeductionCount(orderNo);
	}

	public void saveReturnBuyFinance(FinanceBuyEntity financeBuyEntity) {
		int month = DateUtils.getDiffMonths(
				financeBuyEntity.getDeductionBeginDate(),
				financeBuyEntity.getDeductionEndDate());
		financeBuyEntity.setDeductionCount(month);
		insertFinanceBuy(financeBuyEntity);

		BigDecimal wyProfit = financeBuyEntity.getBuyMoney().multiply(
				financeBuyEntity.getWyRate());
		BigDecimal profitAgent = financeBuyEntity.getBuyMoney().multiply(
				financeBuyEntity.getChannelRate());

		FinanceProfitEntity financeProfitEntity = new FinanceProfitEntity();
		financeProfitEntity.setOrderNo(financeBuyEntity.getOrderNo());
		if (financeBuyEntity.getChannelPartnerId() == null) {
			financeProfitEntity.setProfitAgent(new BigDecimal("0"));
		} else {
			financeProfitEntity.setProfitAgent(profitAgent.divide(
					new BigDecimal(month), 2, BigDecimal.ROUND_DOWN));
		}
		financeProfitEntity.setProfitWy(wyProfit.divide(new BigDecimal(month),
				2, BigDecimal.ROUND_DOWN));

		Calendar cal = Calendar.getInstance();
		cal.setTime(financeBuyEntity.getDeductionBeginTime());
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		processCal(cal);
		Date profitTm = cal.getTime();

		while (profitTm.before(financeBuyEntity.getDeductionEndDate())) {
			financeProfitEntity.setProfitTm(profitTm);
			insertFinanceProfit(financeProfitEntity);

			cal.add(Calendar.MONTH, 1);
			processCal(cal);
			profitTm = cal.getTime();
		}
	}

	private ICarNumPayLogBaseDao carNumPayLogBaseDao;
	
	public void setCarNumPayLogBaseDao(ICarNumPayLogBaseDao carNumPayLogBaseDao) {
		this.carNumPayLogBaseDao = carNumPayLogBaseDao;
	}

	private IUuidManager uuidManager;
    public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	@Transactional
	public void saveReturnBuyFinanceForCar(FinanceBuyEntity financeBuyEntity, BigInteger carId, BigInteger gbId) {
		int month = DateUtils.getDiffMonths(
				financeBuyEntity.getDeductionBeginDate(),
				financeBuyEntity.getDeductionEndDate());
		financeBuyEntity.setDeductionCount(month);
		insertFinanceBuy(financeBuyEntity);
		
		long payNum = financeBuyEntity.getDeductionCount().longValue();
		// 更新有效期
		MonthCarInfo monthCarInfo = ThirdCarFactoryBean.getThirdCarFactory().getMonthCarInfo(gbId, financeBuyEntity.getLicensePlate());
		String startExpiredate = DateUtil.formatSecond.get().format(new Date(monthCarInfo.getExpire()));
		String endExpireDate = AccessServiceBean.getAccessService().dealPayExpire(monthCarInfo.getExpire(), (int)payNum);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("carId", carId);
		paramMap.put("expireDate", endExpireDate);
		AccessServiceBean.getAccessService().updateCarexpireDate(paramMap);
		
		// 更新t_finance_buy有效器
		financeDao.updateParkingExpireDate(financeBuyEntity.getOrderNo());
		
		// t_car_num_pay_log表插入记录：购买停车宝后易豪升的后台车牌的有效期更新需要
		CarNumPayLog carNumPayLog = new CarNumPayLog();
		carNumPayLog.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_pay_log));
		carNumPayLog.setSys0AddTime(DateUtils.convertDateToStr(financeBuyEntity.getAddTm(), "yyyy-MM-dd HH:mm:ss"));
		carNumPayLog.setPayTime(DateUtils.convertDateToStr(financeBuyEntity.getBuyTime(), "yyyy-MM-dd HH:mm:ss"));
		carNumPayLog.settCarNumId(carId);
		carNumPayLog.setStatus(2);//停车宝缴费
		long fee = BigDecimalUtil.mul100(financeBuyEntity.getParkingFee()).longValue()*financeBuyEntity.getDeductionCount();
		carNumPayLog.setFee(fee);
		carNumPayLog.setPayNum(payNum);
		carNumPayLog.settEbuyOrderId(new BigInteger(financeBuyEntity.getOrderNo()));
		carNumPayLog.setPushStatus(0);
		
		{
			if(payNum>0 && monthCarInfo!=null){
		        carNumPayLog.setPayStartDate(startExpiredate);
		        carNumPayLog.setPayEndDate(endExpireDate);
			}
		}
		carNumPayLogBaseDao.insertCarNumPayLog(carNumPayLog);
		logger.info("financeCar:insert into carNumPayLog===>"+carNumPayLog.toString());
		
		CarNumList carNumList = carNumListBaseDao.selectCarNumListBySeqId(carId);
		ThirdCarFactoryBean.getThirdCarFactory().monthCardPayNotify(financeBuyEntity.getOrderNo(), carNumList, carNumPayLog);
	}
	
	public static class ThirdCarFactoryBean{
		private static ThirdCarFactory thirdCarFactory;
		public static ThirdCarFactory getThirdCarFactory() {
			if(thirdCarFactory == null) {
				thirdCarFactory = (ThirdCarFactory)CnfantasiaCommbusiUtil.getBeanManager("thirdCarFactory");
			}
			return thirdCarFactory;
		}
	}
	
	public static class AccessServiceBean{
		private static IAccessService accessService;
		public static IAccessService getAccessService() {
			if(accessService == null) {
				accessService = (IAccessService)CnfantasiaCommbusiUtil.getBeanManager("accessService");
			}
			return accessService;
		}
	}
	
	// 如当天是周末，顺延时期到下一个周一
	private void processCal(Calendar cal) {
		int day = cal.get(Calendar.DAY_OF_WEEK);

		if (day == Calendar.SUNDAY) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
		} else if (day == Calendar.SATURDAY) {
			cal.add(Calendar.DAY_OF_MONTH, 2);
		}
	}

	/**
	 * 抵扣物业账单： 1、在导入物业账单时抵扣：此时传入realRoomId,或者传入参数都为空即可
	 * 2、购买物业宝后，查看是否有已导入的账单在抵扣周期内，若有生成抵扣：此时传入orderNo，realRoomId为空即可
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deductionPropertyBill(String orderNo, Long realRoomId) {
		FinanceDeductionEntity financeDedForLog = null;
		try {
			// 生成抵扣记录，数据量并不大。由于金额大，对数据的一致性要求高。
			// 避免并发问题，加上锁
			commonLockService.getLock(Lock.FINANCE_DEDUCTION);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("realRoomId", realRoomId);
			paramMap.put("orderNo", orderNo);

			// 查询需要生成抵扣的记录
			List<FinanceDeductionEntity> financeDedList = getFinanceForDeductionList(paramMap);
			List<Long> payBillIds = new ArrayList<Long>();
			for (FinanceDeductionEntity financeDed : financeDedList) {
				financeDedForLog = financeDed;

				updatePropertyDeductionPrice(financeDed); // 更新t_property_fee_detail抵扣金额字段
				insertFinanceDeduction(financeDed); // 生成抵扣物业明细数据

				//一个账单对应多个固定收费项，抵扣的时候抵扣次数已账单为准:
				if(payBillIds.contains(financeDed.getPayBillId())) continue;

				updateDeductionCount(financeDed.getOrderNo()); // 更新t_finance_buy已抵扣次数
				payBillIds.add(financeDed.getPayBillId());
			}
		} catch (Exception e) {
			logger.info("物业宝抵扣过程中出现异常：房间号（" + realRoomId + "），订单号（" + orderNo + "）");
			logger.info("异常信息为：" + e);
			// 记录异常日志
			FinanceLogEntity financeLog = new FinanceLogEntity();
			financeLog.setOrderNo(orderNo);
			financeLog.setRealRoomId(realRoomId);
			financeLog.setExcepMsg(e.getMessage().length() > 333 ? e
					.getMessage().substring(0, 333) : e.getMessage());
			if (financeDedForLog != null) {
				financeLog.setOrderNo(financeDedForLog.getOrderNo());
			}
			financeDao.insertFinanceLog(financeLog);
		}
	}

	/**
	 * 查看所有的未付款并且有购买物业宝的账单，检查是否需要生成抵扣信息
	 */
	public void deductionPropertyBill() {
		deductionPropertyBill(null, null);
	}

	public List<FinanceProfitEntity> getProfitListByWyOrAgent(
			Map<String, Object> paramMap) {
		return financeDao.getProfitListByWyOrAgent(paramMap);
	}

	public int getProfitListByWyOrAgentCount(Map<String, Object> paramMap) {
		return financeDao.getProfitListByWyOrAgentCount(paramMap);
	}

	public List<FinanceBuyEntity> getFinanceBuyList(Map<String, Object> paramMap) {
		return financeDao.getFinanceBuyList(paramMap);
	}

	public int getFinanceBuyListCount(Map<String, Object> paramMap) {
		return financeDao.getFinanceBuyListCount(paramMap);
	}

	public FinanceSum getFinanceBuyListSum(Map<String, Object> paramMap) {
		return financeDao.getFinanceBuyListSum(paramMap);
	}

	public FinanceSum getProfitListByWyOrAgentSum(Map<String, Object> paramMap) {
		return financeDao.getProfitListByWyOrAgentSum(paramMap);
	}

	public List<FinanceProfitEntity> getProfitAllList(
			Map<String, Object> paramMap) {
		return financeDao.getProfitAllList(paramMap);
	}

	public int getProfitAllListCount(Map<String, Object> paramMap) {
		return financeDao.getProfitAllListCount(paramMap);
	}

	public FinanceSum getProfitAllListSum(Map<String, Object> paramMap) {
		return financeDao.getProfitAllListSum(paramMap);
	}

	public FinanceAgent getFinanceAgent(Map<String, Object> paramMap) {
		return financeDao.getFinanceAgent(paramMap);
	}
	
	public FinanceAgent getFinanceAgent2(Map<String, Object> paramMap) {
		return financeDao.getFinanceAgent2(paramMap);
	}

	public List<FinanceDeductionDetail> getFinanceDeductionDetailList(
			Map<String, Object> paramMap) {
		return financeDao.getFinanceDeductionDetailList(paramMap);
	}

	public int getFinanceDeductionDetailCount(Map<String, Object> paramMap) {
		return financeDao.getFinanceDeductionDetailCount(paramMap);
	}

	public FinanceSum getFinanceDeductionDetailSum(Map<String, Object> paramMap) {
		return financeDao.getFinanceDeductionDetailSum(paramMap);
	}

	/**
	 * 查询物业宝未抵扣月数
	 * 
	 * @param realRoomId
	 * @return
	 */
	public Date getWYBDeduMonth(BigInteger realRoomId){
		return financeDao.getWYBDeduMonth(realRoomId);
	}

}
