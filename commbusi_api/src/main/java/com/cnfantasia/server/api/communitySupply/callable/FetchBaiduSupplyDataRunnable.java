/**   
* Filename:    FetchBaiduSupplyDataRunnable.java   
* @version:    1.0  
* Create at:   2014年9月4日 上午3:35:13   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月4日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.callable;

import java.math.BigInteger;
import java.util.concurrent.Callable;

import com.cnfantasia.server.api.communitySupply.service.IAutoFetchSupllyDataService;

/**
 * Filename:    FetchBaiduSupplyDataRunnable.java
 * @version:    1.0.0
 * Create at:   2014年9月4日 上午3:35:13
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月4日       shiyl             1.0             1.0 Version
 */
public class FetchBaiduSupplyDataRunnable implements Callable<Boolean>{
	private IAutoFetchSupllyDataService autoFetchSupllyDataService;
	private BigInteger groupBuildingId;
	public FetchBaiduSupplyDataRunnable(IAutoFetchSupllyDataService autoFetchSupllyDataService,BigInteger groupBuildingId){
		this.autoFetchSupllyDataService = autoFetchSupllyDataService;
		this.groupBuildingId = groupBuildingId;
	}
	

	@Override
	public Boolean call() throws Exception {
		autoFetchSupllyDataService.fetchSupply2DBForBaidu(groupBuildingId, null);
		return true;
	}
	
}
