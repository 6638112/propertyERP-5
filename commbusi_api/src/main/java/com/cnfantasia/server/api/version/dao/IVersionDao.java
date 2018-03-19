/**   
* Filename:    IVersionDao.java   
* @version:    1.0  
* Create at:   2014年6月18日 上午7:46:46   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.version.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.version.entity.AppVersionChannelEntity;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;

/**
 * Filename:    IVersionDao.java
 * @version:    1.0.0
 * Create at:   2014年6月18日 上午7:46:46
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月18日       shiyl             1.0             1.0 Version
 */
public interface IVersionDao {
	
	public AppVersionEntity selectLastVersionInfo(BigInteger appId);
	
	/**
	 * 根据用户Id查询其是否为灰度用户
	 * @param userId
	 * @return
	 */
	public boolean selectIsGateUserByUserId(BigInteger userId);
	
	/**
	 * 查询最新的版本信息
	 * @param appId
	 * @param containGateTypeList 包含的类别
	 * @param isForceUpd 0非强升，1强升 
	 * @return
	 */
	public AppVersionEntity selectLastVersionInfoWithGate(BigInteger appId,List<Integer> containGateTypeList, Integer isForceUpd);
	
	/**
	 * 通过渠道编码查询最新的版本信息
	 * @param appId
	 * @param containGateTypeList 包含的类别
	 * @param channelCode 渠道编码
	 * @return
	 */
	public AppVersionChannelEntity selectLastVersionInfoWithGateByChannelCode(
			BigInteger appId, List<Integer> containGateTypeList,
			String channelCode);
	
	
}
