package com.cnfantasia.server.api.performPro.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.performPro.dao.IPerformProDao;
import com.cnfantasia.server.api.performPro.entity.GlobalEntity;

/**
 * 
* Filename:    PerformProService.java
* @version:    1.0.0
* Create at:   2015年6月23日 上午2:51:37
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年6月23日       shiyl             1.0             1.0 Version
 */
public class PerformProService implements IPerformProService{
	
	private IPerformProDao performProDao;
	public void setPerformProDao(IPerformProDao performProDao) {
		this.performProDao = performProDao;
	}

	@Override
	public boolean getIsNewUser(String deviceId,Long deviceType) {
		return performProDao.selectIsNewUser(deviceId, deviceType);
	}
	
	@Override
	public GlobalEntity getGlobalDataAll(BigInteger userId,Long lastBizTypeUpdTime,RequestClientInfoEntity clientInfo) {
		String deviceId = clientInfo.getDeviceId();
		Long deviceType = clientInfo.getDeviceType();
		Long version = clientInfo.getVersionLong();
		return performProDao.selectGlobalDataAll(userId, deviceId, deviceType, lastBizTypeUpdTime, version);
	}


	
}
