/**   
* Filename:    SysLoggerSynch2DatabaseRunnable.java   
* @version:    1.0  
* Create at:   2014年8月6日 上午1:58:33   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysLogger.callable;

import java.util.List;
import java.util.concurrent.Callable;

import com.cnfantasia.server.business.commSysLogger.service.ISysLoggerService;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;
import com.cnfantasia.server.domainbase.commLoggerControl.entity.CommLoggerControl;

/**
 * Filename:    SysLoggerSynch2DatabaseRunnable.java
 * @version:    1.0.0
 * Create at:   2014年8月6日 上午1:58:33
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月6日       shiyl             1.0             1.0 Version
 */
public class SysLoggerSynch2DatabaseRunnable implements Callable<List<CommLogger>>{
	private ISysLoggerService<CommLogger,CommLoggerControl> sysLoggerService;
	private List<CommLogger> logCatcheList;
	public SysLoggerSynch2DatabaseRunnable(ISysLoggerService<CommLogger,CommLoggerControl> sysLoggerService,List<CommLogger> logCatcheList){
		this.sysLoggerService = sysLoggerService;
		this.logCatcheList = logCatcheList;
	}
	
	@Override
	public List<CommLogger> call() throws Exception {
		List<CommLogger> resList = sysLoggerService.synch2Database(logCatcheList);
		return resList;
	}
	
}
