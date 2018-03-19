package com.cnfantasia.server.api.access.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.api.access.entity.ThirdFeeInfoEntity;
import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 一道通
 * 
 * @author liyulin
 * @version 1.0 2017年12月11日 下午6:41:24
 */
@Service
public class QryYdtFeeService implements IQryFeeService {
	@Resource
	private YDTCarService ydtCarService;

	@Override
	public void qryTempFee(String carNum, List<ThirdFeeInfoEntity> thirdFeeInfoList) {
		List<HtCarGb> ydtCarGbList = ydtCarService.getHtCarGbList();
		for (final HtCarGb ydtCarGb : ydtCarGbList) {
			TempCarInfo tempCarInfo = ydtCarService.getTempCarInfo(ydtCarGb.gettGroupBuildingFId(), carNum);
			if (tempCarInfo != null && tempCarInfo.isState()) {
				ThirdFeeInfoEntity thirdFeeInfoEntity = new ThirdFeeInfoEntity();
				thirdFeeInfoEntity.setGbId(ydtCarGb.gettGroupBuildingFId());

				thirdFeeInfoEntity.setInDate(tempCarInfo.getEnterTime());
				thirdFeeInfoList.add(thirdFeeInfoEntity);
			}
		}
	}
}
