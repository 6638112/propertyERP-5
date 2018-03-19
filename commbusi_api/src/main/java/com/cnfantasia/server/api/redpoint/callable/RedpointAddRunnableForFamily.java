/**   
* Filename:    RedpointAddRunnableForFamily.java   
* @version:    1.0  
* Create at:   2015年4月1日 上午8:23:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.callable;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Callable;

import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.service.IRedpointService;

/**
 * Filename:    RedpointAddRunnableForFamily.java
 * @version:    1.0.0
 * Create at:   2015年4月1日 上午8:23:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月1日       shiyl             1.0             1.0 Version
 */
public class RedpointAddRunnableForFamily implements Callable<Boolean>{
	private IRedpointService redpointService;
	private BigInteger userId;
	private String modelCode;
	private List<BasicSourceEntity> sourceList;
	
	public RedpointAddRunnableForFamily(IRedpointService redpointService,BigInteger userId, String modelCode,List<BasicSourceEntity> sourceList){
		this.redpointService = redpointService;
		this.userId = userId;
		this.modelCode = modelCode;
		this.sourceList = sourceList;
	}

	@Override
	public Boolean call() throws Exception {
		redpointService.addRedpointContentForFamily_HasLogin(userId,modelCode, sourceList);
		return true;
	}

}
