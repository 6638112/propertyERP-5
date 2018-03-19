/**   
* Filename:    IShareService.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午1:34:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.service;

import java.math.BigInteger;

/**
 * Filename:    IShareService.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午1:34:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
public interface IShareService {
	
	/**
	 * 进行分享
	 */
	public void doShare(BigInteger userId,Integer shareType,String title,String content );
	
}
