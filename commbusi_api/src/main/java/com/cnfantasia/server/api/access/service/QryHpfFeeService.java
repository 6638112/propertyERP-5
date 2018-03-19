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
 * 华鹏飞车禁临停车车场查询线程
 * 
 * @author liyulin
 * @version 1.0 2017年10月31日 上午11:05:39
 */
@Service
public class QryHpfFeeService implements IQryFeeService{
    private final Logger logger = Logger.getLogger(getClass());
    
    @Resource
    private HpfCarService hpfCarService;
	
	@Override
	public void qryTempFee(final String carNum, final List<ThirdFeeInfoEntity> thirdFeeInfoList) {
		final List<HtCarGb> hpfCarGbList = hpfCarService.getHtCarGbList();
		final CountDownLatch countDownLatch = new CountDownLatch(hpfCarGbList.size());
		for (int i = 0; i < hpfCarGbList.size(); i++) {
			final HtCarGb hpfCarGb = hpfCarGbList.get(i);
			new Thread(new Runnable() {
				@Override
				public void run() {				
					TempCarInfo tempCarInfo = hpfCarService.getTempCarInfo(hpfCarGb.gettGroupBuildingFId(), carNum);
					if (tempCarInfo != null && tempCarInfo.isState()) {
						ThirdFeeInfoEntity thirdFeeInfoEntity = new ThirdFeeInfoEntity();
						thirdFeeInfoEntity.setGbId(hpfCarGb.gettGroupBuildingFId());

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
			logger.error("QryHpfFeeService.wait.InterruptedException", e);
		}	
	}

}