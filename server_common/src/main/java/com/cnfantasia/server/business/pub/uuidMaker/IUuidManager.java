/**   
* Filename:    IUuidManager.java   
* @version:    1.0  
* Create at:   2014年8月5日 上午6:34:10   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.business.pub.uuidMaker;

import java.math.BigInteger;
import java.util.List;

/**
 * Filename:    IUuidManager.java
 * @version:    1.0.0
 * Create at:   2014年8月5日 上午6:34:10
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月5日       shiyl             1.0             1.0 Version
 */
public interface IUuidManager {
	/**
	 * 获取下个Uuid
	 * @param tableName
	 * @return
	 */
	public BigInteger getNextUuidBigInteger(String tableName);
	
	/**
	 * 获取多个Uuid
	 * @param tableName
	 * @param size
	 * @return
	 */
	public List<BigInteger> getNextUuidBigInteger(String tableName,int size);
	
}
