/**   
* Filename:    ICommonPointDao.java   
* @version:    1.0  
* Create at:   2015年1月5日 上午6:37:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.pointData.entity.PointData;

/**
 * Filename:    ICommonPointDao.java
 * @version:    1.0.0
 * Create at:   2015年1月5日 上午6:37:28
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月5日       shiyl             1.0             1.0 Version
 */
public interface ICommonPointDao {
	/**
	 * 根据用户Id查询当前积分信息
	 * @param userId
	 * @return
	 */
	public List<PointData> selectPointDataByUserId(BigInteger userId);
	
}
