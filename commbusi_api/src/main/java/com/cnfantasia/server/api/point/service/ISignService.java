/**   
* Filename:    ISignService.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午1:33:51   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.point.entity.SignResultEntity;
import com.cnfantasia.server.domainbase.commSignRecord.entity.CommSignRecord;

/**
 * Filename:    ISignService.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午1:33:51
 * Description:签到
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
public interface ISignService {
	
	/**
	 * 查询最新一次的签到记录
	 */
	public CommSignRecord getLastCommSignRecord(BigInteger userId);
	/**
	 * 查询最新一次的签到记录
	 * 增加返回当天是否已经领取
	 */
	public SignResultEntity getLastCommSignRecordWithTodayFirst(BigInteger userId);
	
	/**
	 * 进行签到
	 */
	public SignResultEntity doSign(BigInteger userId);
	
}
