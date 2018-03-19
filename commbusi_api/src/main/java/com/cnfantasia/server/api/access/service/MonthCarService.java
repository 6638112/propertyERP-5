package com.cnfantasia.server.api.access.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.access.entity.CarFeeType;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;

/**
 * 车辆列表查询
 * 
 * @author liyulin
 * @version 1.0 2017年12月12日 下午4:43:59
 */
@Service
public class MonthCarService {

	private final Logger logger = Logger.getLogger(getClass());
	private static final ExecutorService MONTH_CAR_CACHED_THREAD_POOL = new ThreadPoolExecutor(10, Integer.MAX_VALUE,
			60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	@Resource
	private ThirdCarFactory thirdCarFactory;

	/**
	 * 多线程查询月卡费用、有效期相关信息
	 * @param carDetailList
	 * @param allCarFeeTypeList
	 */
	public void dealMonthCar(List<? extends CarNumList> carDetailList, List<List<CarFeeType>> allCarFeeTypeList) {
		CountDownLatch countDownLatch = new CountDownLatch(carDetailList.size());
		List<MonthCarRunnable> monthCarRunnableList = new ArrayList<MonthCarRunnable>();
		for (int i = 0; i < carDetailList.size(); i++) {
			List<CarFeeType> carFeeTypeList = null;
			if (allCarFeeTypeList != null) {
				carFeeTypeList = new ArrayList<CarFeeType>();
				allCarFeeTypeList.add(carFeeTypeList);
			}
			monthCarRunnableList.add(new MonthCarRunnable(countDownLatch, carDetailList.get(i), carFeeTypeList));
		}
		
		for (Runnable monthCarRunnable : monthCarRunnableList) {
			MONTH_CAR_CACHED_THREAD_POOL.execute(monthCarRunnable);
		}

		Integer waitTime = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.HT_QUERY_WAITING_MAX_TIME, 4*1000);
		try {
			countDownLatch.await(waitTime + 800, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			logger.error("MonthCarService.wait.InterruptedException", e);
		}	
	}

	@PreDestroy
	public void destroy() {
		MONTH_CAR_CACHED_THREAD_POOL.shutdown();
	}
}
