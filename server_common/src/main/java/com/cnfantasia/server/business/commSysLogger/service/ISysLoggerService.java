/**   
* Filename:    ISysLoggerService.java   
* @version:    1.0  
* Create at:   2014年8月6日 上午2:11:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.business.commSysLogger.service;

import java.util.List;

/**
 * Filename:    ISysLoggerService.java
 * @version:    1.0.0
 * Create at:   2014年8月6日 上午2:11:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月6日       shiyl             1.0             1.0 Version
 */
public interface ISysLoggerService<T,Q> {//T :CommLogger,Q: CommLoggerControl
	/**
	 * 将缓存的数据同步到数据库
	 * 返回处理失败的记录信息
	 */
	public List<T> synch2Database(List<T> logCatcheList);
	
	/**
	 * 获取日志控制信息
	 * @return
	 */
	public List<Q> getLoggerCtrlList();
}
