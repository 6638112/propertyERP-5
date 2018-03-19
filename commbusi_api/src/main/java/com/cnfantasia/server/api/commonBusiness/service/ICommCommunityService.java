/**   
* Filename:    ICommCommunityService.java   
* @version:    1.0  
* Create at:   2014年12月1日 上午7:38:53   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;

/**
 * Filename:    ICommCommunityService.java
 * @version:    1.0.0
 * Create at:   2014年12月1日 上午7:38:53
 * Description:街坊模块公共Service
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月1日       shiyl             1.0             1.0 Version
 */
public interface ICommCommunityService {
	
	/**
	 * 检查世外桃源的操作
	 * @param userId
	 */
	public void checkXanaduOperation(BigInteger userId);
}
