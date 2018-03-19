package com.cnfantasia.server.api.access.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import com.cnfantasia.server.api.access.entity.CarFeeType;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;

public class MonthCarRunnable implements Runnable {

	private final Logger logger = Logger.getLogger(getClass());
	private CountDownLatch countDownLatch;
	private CarNumList carNumList;
	private List<CarFeeType> carFeeTypeList;
	private ThirdCarFactory thirdCarFactory = ThirdCarFactoryBean.getThirdCarFactory();

	public MonthCarRunnable(CountDownLatch countDownLatch, CarNumList carNumList, List<CarFeeType> carFeeTypeList) {
		this.countDownLatch = countDownLatch;
		this.carNumList = carNumList;
		this.carFeeTypeList = carFeeTypeList;
	}

	@Override
	public void run() {
		MonthCarInfo monthCarInfo = null;
		boolean error = false;
		try {
			monthCarInfo = thirdCarFactory.getOneMonthCarInfo(carNumList.gettGroupBuildingFId(), carNumList.gettCarNum());
		} catch (Exception e) {
			error = true;
			logger.error("MonthCarService.run.Exception", e);
		}

		if (monthCarInfo != null) {
			carNumList.setFee(monthCarInfo.getRealAmt());
			Date expire = new Date(monthCarInfo.getExpire());
			carNumList.setExpireDate(DateUtil.formatSecond.get().format(expire));
			if (carFeeTypeList != null) {
				thirdCarFactory.getCarFeeTypeList(carNumList.gettGroupBuildingFId(), monthCarInfo.getRealAmt(),
						DateUtil.formatDay.get().format(expire), monthCarInfo.getCarTypeId(), carFeeTypeList);
			}
		} 
		if (error || monthCarInfo == null) {
			dealNotExistCar();
		}
		
		countDownLatch.countDown();
	}
	
	private void dealNotExistCar() {
		carNumList.setSys0DelState(1);
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
}
