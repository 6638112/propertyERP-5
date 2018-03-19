package com.cnfantasia.server.api.access.service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.dao.IAccessDao;
import com.cnfantasia.server.api.access.entity.QryFeeRunnableParam;
import com.cnfantasia.server.api.access.entity.ThirdFeeInfoEntity;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DataUtil;

/**
 * 临停车当前停车场查询
 * 
 * @author liyulin
 * @version 1.0 2017年11月2日 上午11:07:12
 */
@Service
public class TempCarPlotService {

	private final Logger logger = Logger.getLogger(getClass());
	private static final ExecutorService QRY_TMP_CAR_PLOT_CACHED_THREAD_POOL = new ThreadPoolExecutor(
			AccessDict.Code.class.getFields().length, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
			new SynchronousQueue<Runnable>());
	private static final ThirdFeeInfoComparator THIRD_FEE_INFO_COMPARATOR = new ThirdFeeInfoComparator();

	@Resource
	private IQryFeeService qryXmfFeeService;
	@Resource
	private IQryFeeService qryHaFeeService;
	@Resource
	private IQryFeeService qryYhsFeeService;
	@Resource
	private IQryFeeService qryHpfFeeService;
	@Resource
	private IQryFeeService qryYdtFeeService;
	@Resource
	private IAccessDao accessDao;

	/**
	 * 查询车牌所在的停车场小区id
	 * 
	 * @return
	 */
	public BigInteger qryTmpCarGbId(final String carNum) {
		BigInteger gbId = accessDao.getGbIdByCarNum(carNum);
		if (DataUtil.isEmpty(gbId)) {
			String cacheKey = AccessDict.CachePrefix.TMP_CAR_PLOT_PREFIX + carNum;
			String cachePlot = RedisCacheHandler.get(cacheKey);
			if (StringUtils.isNotBlank(cachePlot)) {
				gbId = new BigInteger(cachePlot);
			} else {
				gbId = qryTmpCarGbIdWithThread(carNum);
			}
			if (gbId != null) {
				Integer plotCacheExpireSeconds = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.PLOT_CACHE_EXPIRE_SECONDS, 5 * 60);
				RedisCacheHandler.set(cacheKey, gbId.toString(), plotCacheExpireSeconds);
			}
		}

		return gbId;
	}

	/**
	 * 多线程查询当前临停车所在的停车场gbId
	 * 
	 * @param carNum
	 * @return
	 */
	private BigInteger qryTmpCarGbIdWithThread(String carNum) {
		IQryFeeService[] qryFeeService = { qryYhsFeeService, qryXmfFeeService, qryHpfFeeService, qryHaFeeService, qryYdtFeeService };
		final CountDownLatch countDownLatch = new CountDownLatch(qryFeeService.length);
		final List<ThirdFeeInfoEntity> thirdFeeInfoList = new CopyOnWriteArrayList<ThirdFeeInfoEntity>();
		// 当未查询到车牌停车信息，多线程去所有停车场查询该车辆信息
		for (int i = 0; i < qryFeeService.length; i++) {
			QryFeeRunnableParam qryFeeRunnableParam = new QryFeeRunnableParam();
			qryFeeRunnableParam.setCarNum(carNum);
			qryFeeRunnableParam.setQryFeeService(qryFeeService[i]);
			qryFeeRunnableParam.setThirdFeeInfoList(thirdFeeInfoList);
			qryFeeRunnableParam.setCountDownLatch(countDownLatch);

			QRY_TMP_CAR_PLOT_CACHED_THREAD_POOL.execute(new QryFeeRunnable(qryFeeRunnableParam));
		}

		try {
			countDownLatch.await(6, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.error("QryFeeThread.await.InterruptedException", e);
		}		

		return getTmpCarGbId(thirdFeeInfoList);
	}

	private BigInteger getTmpCarGbId(List<ThirdFeeInfoEntity> thirdFeeInfoList) {
		BigInteger gbId = null;
		if (thirdFeeInfoList.size() > 0) {
			ThirdFeeInfoEntity thirdFeeInfoEntity = Collections.max(thirdFeeInfoList, THIRD_FEE_INFO_COMPARATOR);
			gbId = thirdFeeInfoEntity.getGbId();
		}
		return gbId;
	}

	static class ThirdFeeInfoComparator implements Comparator<ThirdFeeInfoEntity> {

		@Override
		public int compare(ThirdFeeInfoEntity o1, ThirdFeeInfoEntity o2) {
			long inTime1 = o1.getInDate();
			long inTime2 = o2.getInDate();
			if (inTime1 > inTime2) {
				return 1;
			}
			if (inTime1 < inTime2) {
				return -1;
			}
			return 0;
		}

	}

	@PreDestroy
	public void destroy() {
		QRY_TMP_CAR_PLOT_CACHED_THREAD_POOL.shutdown();
	}
}
