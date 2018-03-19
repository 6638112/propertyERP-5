/**   
* Filename:    IDualDao.java   
* @version:    1.0  
* Create at:   2014年5月8日 上午6:17:15   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.business.pub.dao;

import java.util.Date;

/**
 * Filename:    IDualDao.java
 * @version:    1.0.0
 * Create at:   2014年5月8日 上午6:17:15
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月8日       shiyl             1.0             1.0 Version
 */
public interface IDualDao {
	/**
	 * 查询数据库时间
	 * %Y-%m-%d %T
	 * @return
	 */
	public String getNowTime();
	/**
	 * 查询数据库日期
	 * %Y-%m-%d'
	 * @return
	 */
	public String getNowDay();
	
	/**
	 * 查询数据库日期
	 * @return
	 */
	public Date getNowDate();
}
