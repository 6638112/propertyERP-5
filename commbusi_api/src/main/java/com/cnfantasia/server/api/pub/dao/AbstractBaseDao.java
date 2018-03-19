/**   
* Filename:    AbstractBaseDao.java   
* @version:    1.0  
* Create at:   2014年8月8日 上午3:22:54   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.dao;

import com.cnfantasia.server.business.pub.dao.CommAbstractBaseDao;

/**
 * Filename:    AbstractBaseDao.java
 * @version:    1.0.0
 * Create at:   2014年8月8日 上午3:22:54
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月8日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractBaseDao extends CommAbstractBaseDao{
    protected static final int batchSize = 100;
}
