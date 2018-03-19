package com.cnfantasia.server.api.access.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.api.access.entity.ThirdFeeInfoEntity;
import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 华安车禁临停车车场查询线程
 * 
 * @author liyulin
 * @version 1.0 2017年10月31日 上午11:05:39
 */
@Service
public class QryHaFeeService implements IQryFeeService {

	@Resource
	private HACarService haCarService;

	@Override
	public void qryTempFee(String carNum, List<ThirdFeeInfoEntity> thirdFeeInfoList) {
		List<HtCarGb> haCarGbList = haCarService.getHtCarGbList();
		for (final HtCarGb haCarGb : haCarGbList) {
			TempCarInfo tempCarInfo = haCarService.getTempCarInfo(haCarGb.gettGroupBuildingFId(), carNum);
			if (tempCarInfo != null && tempCarInfo.isState()) {
				ThirdFeeInfoEntity thirdFeeInfoEntity = new ThirdFeeInfoEntity();
				thirdFeeInfoEntity.setGbId(haCarGb.gettGroupBuildingFId());

				thirdFeeInfoEntity.setInDate(tempCarInfo.getEnterTime());
				thirdFeeInfoList.add(thirdFeeInfoEntity);
			}
		}
	}

}