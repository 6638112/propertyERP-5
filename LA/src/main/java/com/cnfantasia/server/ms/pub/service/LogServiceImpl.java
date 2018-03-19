/**   
* Filename:    LogServiceImpl.java   
* @version:    1.0  
* Create at:   2014年5月19日 上午9:02:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.pub.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Filename:    LogServiceImpl.java
 * @version:    1.0.0
 * Create at:   2014年5月19日 上午9:02:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月19日       shiyl             1.0             1.0 Version
 */
@Service
public class LogServiceImpl implements ILogService{

	/* (non-Javadoc)
	 * @see com.cnfantasia.server.ms.pub.logger.ILogService#getLogControlList()
	 */
	@Override
	public List<HashMap<String, Object>> getLogControlList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cnfantasia.server.ms.pub.logger.ILogService#insertLog(java.util.HashMap)
	 */
	@Override
	public void insertLog(HashMap<String, Object> logobj) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.cnfantasia.server.ms.pub.logger.ILogService#getRealFuncUrl(java.lang.String)
	 */
	@Override
	public String getRealFuncUrl(String srcUri) {
		// TODO Auto-generated method stub
		return null;
	}

}
