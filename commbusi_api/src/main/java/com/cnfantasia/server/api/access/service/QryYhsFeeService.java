package com.cnfantasia.server.api.access.service;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.api.access.entity.ThirdFeeInfoEntity;
import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 易豪生车禁临停车车场查询线程
 * 
 * @author liyulin
 * @version 1.0 2017年10月31日 上午11:04:00
 */
@Service("qryYhsFeeService")
public class QryYhsFeeService implements IQryFeeService {
    private final Logger logger = Logger.getLogger(getClass());
    @Resource
    private YHSCarService yhsCarService;

	@Override
	public void qryTempFee(final String carNum, final List<ThirdFeeInfoEntity> thirdFeeInfoList) {
		final List<HtCarGb> yhsCarGbList = yhsCarService.getHtCarGbList();
		final CountDownLatch countDownLatch = new CountDownLatch(yhsCarGbList.size());
		for (int i = 0; i < yhsCarGbList.size(); i++) {
			final HtCarGb yhsCarGb = yhsCarGbList.get(i);
			new Thread(new Runnable() {
				@Override
				public void run() {
					TempCarInfo tempCarInfo = yhsCarService.getTempCarInfo(yhsCarGb.gettGroupBuildingFId(), carNum);
					if (tempCarInfo != null && tempCarInfo.isState()) {
						ThirdFeeInfoEntity thirdFeeInfoEntity = new ThirdFeeInfoEntity();
						thirdFeeInfoEntity.setGbId(yhsCarGb.gettGroupBuildingFId());

						thirdFeeInfoEntity.setInDate(tempCarInfo.getEnterTime());
						thirdFeeInfoList.add(thirdFeeInfoEntity);
					}		

					countDownLatch.countDown();
				}
			}).start();
		}
		
		try {
			countDownLatch.await(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.error("QryYhsFeeService.wait.InterruptedException", e);
		}	
	}

}