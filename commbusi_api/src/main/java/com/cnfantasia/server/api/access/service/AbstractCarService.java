package com.cnfantasia.server.api.access.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.entity.CarFeeType;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.carNumList.dao.ICarNumListBaseDao;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.dao.ICarNumPayLogBaseDao;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 车禁抽象类
 * 
 * @author liyulin
 * @version 1.0 2017年12月7日 下午3:58:44
 */
public abstract class AbstractCarService extends BaseCarService{

	private static final Logger logger = Logger.getLogger(AbstractCarService.class);
    @Resource
    private ICarNumPayLogBaseDao carNumPayLogBaseDao;
	@Resource
	private ICarNumListBaseDao carNumListBaseDao;
	private List<BigInteger> carGbList = null;
	private List<HtCarGb> htCarGbList = null;

	/**
	 * 数据初始化
	 */
	@PostConstruct
	public void initHTCarGb() {
		List<HtCarGb> allHtCarGbList = getAllHtCarGbList();
		if(carGbList==null) {
			carGbList = new ArrayList<BigInteger>();
		} else {
			carGbList.clear();
		}
		
		if(htCarGbList==null) {
			htCarGbList = new ArrayList<HtCarGb>();
		} else {
			htCarGbList.clear();
		}
		
		String code = getCode();
		for (HtCarGb htCarGb : allHtCarGbList) {
			if(code.equals(htCarGb.getCode())) {
				carGbList.add(htCarGb.gettGroupBuildingFId());
				htCarGbList.add(htCarGb);
			}
		}
		
		init();
	}
	

	public abstract void init();
	
	public abstract String getCode();

	public boolean isHTCar(BigInteger gbId) {
		if (gbId != null) {
			for (BigInteger gbIdTmp : carGbList) {
				if (gbIdTmp.compareTo(gbId) == 0) {
					return true;
				}
			}
		}

		return false;
	}

	public HtCarGb getHTCarGbByGbId(BigInteger gbId) {
		HtCarGb htCarGb = null;
		for (HtCarGb htCarGbTmp : htCarGbList) {
			if (htCarGbTmp.gettGroupBuildingFId().compareTo(gbId) == 0) {
				htCarGb = htCarGbTmp;
				break;
			}
		}

		return htCarGb;
	}
	
	public HtCarGb getHTCarGbByParkId(String parkId) {
		HtCarGb htCarGb = null;
		for (HtCarGb htCarGbTmp : htCarGbList) {
			if (htCarGbTmp.gettParkid().equals(parkId)) {
				htCarGb = htCarGbTmp;
				break;
			}
		}

		return htCarGb;
	}
	
	public BigInteger getGbIdByParkId(String parkId) {
		HtCarGb htCarGb = getHTCarGbByParkId(parkId);
		if(htCarGb!=null) {
			return htCarGb.gettGroupBuildingFId();
		}
		return null;
	}
	
	public String getParkIdByGbId(BigInteger gbId) {
		HtCarGb htCarGb = getHTCarGbByGbId(gbId);
		if(htCarGb!=null) {
			return htCarGb.gettParkid();
		}
		return null;
	}

	public List<HtCarGb> getHtCarGbList() {
		return htCarGbList;
	}
	
	/**
	 * 可缴费月数
	 * 
	 * @param gbId
	 * @param fee
	 * @param expire
	 * @param carTypeId
	 * @param carFeeTypeList
	 * @return
	 */
	public List<CarFeeType> getCarFeeTypeList(BigInteger gbId, Long fee, String expire, String carTypeId, List<CarFeeType> carFeeTypeList) {
		if(carFeeTypeList==null) {
			carFeeTypeList = new ArrayList<CarFeeType>();
		}
		int[] months = { 1, 3, 6, 12 };
		for (int i = 0; i < months.length; i++) {
			CarFeeType carFeeType = new CarFeeType();
			carFeeType.setFee(BigDecimalUtil.div100(BigDecimal.valueOf(fee*months[i])));
			carFeeType.setNum(months[i]);
			carFeeType.setUnit("月");
			carFeeType.setTypeName(months[i] + "个月");

			carFeeTypeList.add(carFeeType);
		}

		return carFeeTypeList;
	}

	public abstract MonthCarInfo getOneMonthCarInfo(BigInteger gbId, String plateNo);
	
	/**
	 * 查询月卡车支付信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @param payNum
	 * @return
	 */
	public MonthCarInfo getMonthCarPayInfo(BigInteger gbId, String plateNo, int payNum) {
		MonthCarInfo monthCarInfo = getOneMonthCarInfo(gbId, plateNo);
		
		monthCarInfo.setAmt(monthCarInfo.getAmt()*payNum);
		monthCarInfo.setRealAmt(monthCarInfo.getRealAmt()*payNum);
		return monthCarInfo;
	}
	
	/**
	 * 获取月卡车信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @return
	 */
	public MonthCarInfo getMonthCarInfo(BigInteger gbId, String plateNo) {
		return getOneMonthCarInfo(gbId, plateNo);
	}
	
	public void dealCarNumPayLogForPayNotify(boolean isSuccess, CarNumPayLog carNumPayLog, Integer pushedTimes){
		if(isSuccess){
			carNumPayLog.setPushStatus(1);
		} 

		int times = (pushedTimes == null ? 1 : pushedTimes) + 1;
		carNumPayLog.setPushTimes(times);
		carNumPayLog.setSendTime(CarUtil.getSendTime(times));
		carNumPayLog.setSys0UpdTime(DateUtils.getCurrentDate());
		carNumPayLogBaseDao.updateCarNumPayLog(carNumPayLog);
	}
	
	public void synOneMonthCarInfo(BigInteger gbId, CarNumList carNumList, String now) {
		MonthCarInfo monthCarInfo = null;
		try {
			monthCarInfo = getOneMonthCarInfo(gbId, carNumList.gettCarNum());
		} catch (Exception e) {
			logger.error("synOneMonthCarInfo.getOneMonthCarInfo.Exception", e);
		}
		if (monthCarInfo != null && monthCarInfo.isState()) {
			boolean isUpdate = false;
			if (carNumList.getFee() == null || carNumList.getFee() != monthCarInfo.getRealAmt()) {// 金额不一致，则更新
				carNumList.setFee(monthCarInfo.getRealAmt());
				isUpdate = true;
			}

			Date localExpire = DateUtils.convertStrToDate(carNumList.getExpireDate(), AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
			if (localExpire.getTime() < monthCarInfo.getExpire()) {
				String remoteExpire = DateUtil.formatSecond.get().format(monthCarInfo.getExpire());
				carNumList.setExpireDate(remoteExpire);
				isUpdate = true;
			}

			if (isUpdate) {
				carNumList.setSys0UpdUser(BigInteger.valueOf(1L));
				carNumList.setSys0UpdTime(now);
				// 避免延迟，立即更新（不用批量更新）
				carNumListBaseDao.updateCarNumList(carNumList);
			}
		}
	}
}
