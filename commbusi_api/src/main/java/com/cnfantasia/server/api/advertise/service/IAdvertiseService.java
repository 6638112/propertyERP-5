/**   
* Filename:    IAdvertiseService.java   
* @version:    1.0  
* Create at:   2014年12月1日 上午5:24:13   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.advertise.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.advertise.entity.AdvertiseModel;

/**
 * Filename:    IAdvertiseService.java
 * @version:    1.0.0
 * Create at:   2014年12月1日 上午5:24:13
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月1日       shiyl             1.0             1.0 Version
 */
public interface IAdvertiseService {
	/**
	 * 获取所有广告列表
	 * @return
	 */
	public List<AdvertiseModel> getAdvertiseList(BigInteger userId,String version);
	
//	/**
//	 * 查询世外桃源广告信息
//	 * @return
//	 */
//	public AdvertiseModel getXanaduAdvertise(BigInteger userId,String version);
	
	/**
	 * 根据用户Id动态获取街坊广告信息
	 * @param userId
	 * @return
	 */
	public List<AdvertiseModel> getCommunityAdsDym(BigInteger userId,String version);
}
