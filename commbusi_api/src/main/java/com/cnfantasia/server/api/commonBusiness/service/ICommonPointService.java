/**   
* Filename:    ICommonPointService.java   
* @version:    1.0  
* Create at:   2015年1月5日 上午6:36:56   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.pointData.entity.PointData;

/**
 * Filename:    ICommonPointService.java
 * @version:    1.0.0
 * Create at:   2015年1月5日 上午6:36:56
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月5日       shiyl             1.0             1.0 Version
 */
public interface ICommonPointService {
	
	/**
	 * 根据用户Id查询当前积分信息
	 * @param userId
	 * @return
	 */
	public PointData getPointDataByUserId(BigInteger userId);
	
}
