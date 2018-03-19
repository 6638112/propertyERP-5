package com.cnfantasia.server.api.performPro.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.performPro.entity.GlobalEntity;


/**
 * 
* Filename:    IPerformProService.java
* @version:    1.0.0
* Create at:   2015年6月23日 上午2:51:06
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年6月23日       shiyl             1.0             1.0 Version
 */
public interface IPerformProService {
	/**
	 * 判断用户是否为新注册的用户
	 * @param userId
	 * @param userType
	 * @return
	 */
	public boolean getIsNewUser(String deviceId,Long deviceType);
	
	/**
	 * 获取全部全局变量信息
	 * @param clientInfo
	 * @return
	 */
	public GlobalEntity getGlobalDataAll(BigInteger userId,Long lastBizTypeUpdTime,RequestClientInfoEntity clientInfo);
}
