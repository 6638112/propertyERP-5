package com.cnfantasia.server.api.access.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.api.access.entity.ThirdFeeInfoEntity;
/**
 * 小蜜蜂车禁临停车车场查询线程
 * 
 * @author liyulin
 * @version 1.0 2017年10月31日 上午11:05:39
 */
@Service
public class QryXmfFeeService implements IQryFeeService{
	
	@Resource
	private XMFCarService xmfCarService;

	@Override
	public void qryTempFee(String carNum, List<ThirdFeeInfoEntity> thirdFeeInfoList) {
		TempCarInfo tempCarInfo = xmfCarService.getTempCarInfo(null, carNum);
		if (tempCarInfo != null && tempCarInfo.isState()) {
			ThirdFeeInfoEntity thirdFeeInfoEntity = new ThirdFeeInfoEntity();
			
			thirdFeeInfoEntity.setGbId(tempCarInfo.getGbId());
			thirdFeeInfoEntity.setInDate(tempCarInfo.getEnterTime());
			thirdFeeInfoList.add(thirdFeeInfoEntity);
		}
	}

}
