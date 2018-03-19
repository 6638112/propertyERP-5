/**   
* Filename:    RedpointAddRunnableMulti.java   
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

import java.util.List;
import java.util.concurrent.Callable;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.service.IRedpointService;

/**
 * Filename:    RedpointAddRunnableMulti.java
 * @version:    1.0.0
 * Create at:   2015年4月1日 上午8:23:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月1日       shiyl             1.0             1.0 Version
 */
public class RedpointAddRunnableMulti implements Callable<Boolean>{
	private IRedpointService redpointService;
	private List<CommUserDataEntity> commUserDataEntityList; 
	private String modelCode;
	private List<BasicSourceEntity> sourceList;
	
	public RedpointAddRunnableMulti(IRedpointService redpointService,List<CommUserDataEntity> commUserDataEntityList, String modelCode,List<BasicSourceEntity> sourceList){
		this.redpointService = redpointService;
		this.commUserDataEntityList = commUserDataEntityList;
		this.modelCode = modelCode;
		this.sourceList = sourceList;
	}

	@Override
	public Boolean call() throws Exception {
		redpointService.addRedpointContentMulti(commUserDataEntityList, modelCode, sourceList);
		return true;
	}

}
