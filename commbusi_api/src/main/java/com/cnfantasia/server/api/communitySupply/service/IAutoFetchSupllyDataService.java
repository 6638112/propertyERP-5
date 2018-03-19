/**   
* Filename:    IAutoFetchSupllyDataService.java   
* @version:    1.0  
* Create at:   2014年11月28日 上午9:11:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.service;

import java.math.BigInteger;

/**
 * Filename:    IAutoFetchSupllyDataService.java
 * @version:    1.0.0
 * Create at:   2014年11月28日 上午9:11:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月28日       shiyl             1.0             1.0 Version
 */
public interface IAutoFetchSupllyDataService {
	
	/**
	 * 抓取商家数据
	 * @param groupBuildingId
	 */
	public void fetchSupply2DB(BigInteger groupBuildingId);
	
	/**
	 * 抓取百度数据
	 * @param groupBuildingId
	 */
	public void fetchSupply2DBForBaidu(BigInteger groupBuildingId,String baiduLbsKey);
	
	/**
	 * 抓取美丽家数据
	 * @param groupBuildingId
	 * @param baiduLbsKey 为空使用默认
	 * @param needReload true 表示已经抓取过的小区 重新刷新一下数据
	 */
	public void fetchSupply2DBForMljia(BigInteger groupBuildingId,String baiduLbsKey,boolean needReload);
	
}
