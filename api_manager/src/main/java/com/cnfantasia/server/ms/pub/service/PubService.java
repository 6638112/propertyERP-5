/**   
* Filename:    PubService.java   
* @version:    1.0  
* Create at:   2014年5月8日 上午6:29:34   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.pub.service;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.business.pub.dao.IDualDao;

/**
 * Filename:    PubService.java
 * @version:    1.0.0
 * Create at:   2014年5月8日 上午6:29:34
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月8日       shiyl             1.0             1.0 Version
 */
@Service
public class PubService implements IPubService{
	private IDualDao dualDao;
	
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	@Override
	public String getNowTime() {
		return dualDao.getNowTime();
	}

	@Override
	public String getNowDay() {
		return dualDao.getNowDay();
	}
	
}
