package com.cnfantasia.server.api.performPro.dao;

import java.math.BigInteger;

import com.cnfantasia.server.api.performPro.entity.GlobalEntity;


/**
 * 
* Filename:    IPerformProDao.java
* @version:    1.0.0
* Create at:   2015年6月23日 上午3:02:14
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年6月23日       shiyl             1.0             1.0 Version
 */
public interface IPerformProDao {
	/**
	 * 判断用户是否为新注册的用户
	 * @param userId
	 * @param userType
	 * @return
	 */
	public boolean selectIsNewUser(String deviceId,Long deviceType);

	/**
	 * 查询全局变量信息
	 * @param clientInfo
	 * @return
	 */
	public GlobalEntity selectGlobalDataAll(BigInteger userId,String deviceId,Long deviceType,Long lastBizTypeUpdTime,Long version);
	
}
