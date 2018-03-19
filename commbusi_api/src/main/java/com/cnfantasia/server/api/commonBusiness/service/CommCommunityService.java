/**   
* Filename:    CommCommunityService.java   
* @version:    1.0  
* Create at:   2014年12月1日 上午7:40:36   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.xanadu.service.IXanaduService;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

/**
 * Filename:    CommCommunityService.java
 * @version:    1.0.0
 * Create at:   2014年12月1日 上午7:40:36
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月1日       shiyl             1.0             1.0 Version
 */
public class CommCommunityService implements ICommCommunityService{
	private IXanaduService xanaduService;
	public void setXanaduService(IXanaduService xanaduService) {
		this.xanaduService = xanaduService;
	}

	@Override
	public void checkXanaduOperation(BigInteger userId) {
		boolean isInXanadu = xanaduService.getCurrStatus(userId);
		if(isInXanadu){
			throw new BusinessRuntimeException("CommCommunityService.checkXanaduOperation.isInXanadu");
		}
	}

}
