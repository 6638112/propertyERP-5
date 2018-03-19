/**   
* Filename:    IXanaduService.java   
* @version:    1.0  
* Create at:   2014年12月1日 上午3:33:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.xanadu.service;

import java.math.BigInteger;

/**
 * Filename:    IXanaduService.java
 * @version:    1.0.0
 * Create at:   2014年12月1日 上午3:33:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月1日       shiyl             1.0             1.0 Version
 */
public interface IXanaduService {

	/**
	 * 判断当前用户是否在世外桃源
	 * @param userId
	 * @return true表示在世外桃源，false表示不在
	 */
	public Boolean getCurrStatus(BigInteger userId);

	/**
	 * 切换世外桃源状态
	 * @param userId
	 * @param statusReq true表示要切换到世外桃源,false表示离开
	 * @return
	 */
	public Boolean changeXanaduStatus(BigInteger userId, Boolean statusReq);
	
}
