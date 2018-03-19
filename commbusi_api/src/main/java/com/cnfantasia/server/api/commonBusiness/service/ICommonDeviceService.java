/**   
* Filename:    ICommonDeviceService.java   
* @version:    1.0  
* Create at:   2014年7月6日 下午1:39:32   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.domainbase.userTmp.entity.UserTmp;

/**
 * Filename:    ICommonDeviceService.java
 * @version:    1.0.0
 * Create at:   2014年7月6日 下午1:39:32
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月6日       shiyl             1.0             1.0 Version
 */
public interface ICommonDeviceService {
	/**
	 * 校验是否存在临时用户
	 * 若不存在则新增
	 * 返回临时用户信息
	 * @return
	 */
	public UserTmp checkAndCreate(String deviceId,Long deviceType);
	
	/**
	 * 使用队列的方式检查设备是否存在，不存在则新增一条记录
	 * @param deviceId
	 * @param deviceType
	 */
	public void checkAndCreateQueue(String deviceId,Long deviceType);

	/**
	 * @return
	 */
	public UserIdType getUserIdType();
}
