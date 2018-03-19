/**   
* Filename:    IVersionService.java   
* @version:    1.0  
* Create at:   2014年6月18日 上午7:46:03   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.version.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.version.entity.AppVersionChannelEntity;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;

/**
 * Filename:    IVersionService.java
 * @version:    1.0.0
 * Create at:   2014年6月18日 上午7:46:03
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月18日       shiyl             1.0             1.0 Version
 */
public interface IVersionService {
	
	/**
	 * 检查更新
	 * @param appId 应用Id
	 * @param userId 当前用户
	 * @param isForceUpd 0非强升，1强升 
	 * @return 更新信息
	 */
	public AppVersionEntity getLastVersionInfo(BigInteger appId,BigInteger userId, int isForceUpd);
	
	/**
	 * 获取渠道的最新版本信息
	 * @param appId
	 * @param userId
	 * @param channelCode 渠道编码
	 * @return
	 */
	public AppVersionChannelEntity getLastVersionInfo(BigInteger appId,BigInteger userId,String channelCode);

	/**
	 * 检查更新
	 * @param appId 应用Id
	 * @param userId 当前用户
	 * @return 更新信息
	 */
	AppVersionEntity getLastVersionInfo(BigInteger appId, BigInteger userId);
}
