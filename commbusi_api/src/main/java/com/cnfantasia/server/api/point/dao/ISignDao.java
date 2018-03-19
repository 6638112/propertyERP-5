/**   
* Filename:    ISignDao.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午2:42:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.commSignRecord.entity.CommSignRecord;

/**
 * Filename:    ISignDao.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午2:42:20
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
public interface ISignDao {
	
	/**
	 * 查询最近一条签到记录
	 * @param userId
	 * @return
	 */
	public CommSignRecord selectLastCommSignRecord(BigInteger userId);
	
}
