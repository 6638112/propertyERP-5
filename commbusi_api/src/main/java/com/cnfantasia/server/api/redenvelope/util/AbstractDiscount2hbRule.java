/**   
* Filename:    AbstractDiscount2hbRule.java   
* @version:    1.0  
* Create at:   2015年2月11日 上午8:38:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.util;

import java.math.BigInteger;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    AbstractDiscount2hbRule.java
 * @version:    1.0.0
 * Create at:   2015年2月11日 上午8:38:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月11日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractDiscount2hbRule implements IDiscount2hbRule{

	@Override
	public Long getMoneyByDiscount(BigInteger userId, Double discount) {
		 return getMoneyByDiscount(discount);
	}

	@Override
	public Long getMoneyByDiscount(UserHasTRoom userHasTRoom, Double discount) {
		return getMoneyByDiscount(discount);
	}

	@Override
	public UserHasTRoom getDefaultUserHasTRoom(BigInteger userId) {
		throw new BusinessRuntimeException();
	}
	
	
	
}
