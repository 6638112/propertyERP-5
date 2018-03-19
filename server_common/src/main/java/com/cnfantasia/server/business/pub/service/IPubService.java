/**   
* Filename:    IPubService.java   
* @version:    1.0  
* Create at:   2014年5月8日 上午6:29:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.business.pub.service;

/**
 * Filename:    IPubService.java
 * @version:    1.0.0
 * Create at:   2014年5月8日 上午6:29:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月8日       shiyl             1.0             1.0 Version
 */
public interface IPubService {
	/**
	 * 查询数据库时间
	 * @return
	 */
	public String getNowTime();
	/**
	 * 查询数据库日期
	 * @return
	 */
	public String getNowDay();
}
