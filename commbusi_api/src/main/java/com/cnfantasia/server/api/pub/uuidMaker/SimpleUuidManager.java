/**   
* Filename:    SimpleUuidManager.java   
* @version:    1.0  
* Create at:   2014年8月5日 上午6:41:18   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.uuidMaker;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.commUuid.service.ICommUuidService;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;

/**
 * Filename:    SimpleUuidManager.java
 * @version:    1.0.0
 * Create at:   2014年8月5日 上午6:41:18
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月5日       shiyl             1.0             1.0 Version
 */
public class SimpleUuidManager implements IUuidManager{
	
	private ICommUuidService commUuidService;
	public void setCommUuidService(ICommUuidService commUuidService) {
		this.commUuidService = commUuidService;
	}

	@Override
	public BigInteger getNextUuidBigInteger(String tableName) {
		return commUuidService.getNextUuidBigInteger(tableName);
	}

	@Override
	public List<BigInteger> getNextUuidBigInteger(String tableName, int size) {
		return commUuidService.getNextUuidBigInteger(tableName, size);
	}
	
}
