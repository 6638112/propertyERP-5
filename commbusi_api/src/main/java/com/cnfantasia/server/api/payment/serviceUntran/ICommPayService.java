/**   
* Filename:    ICommPayService.java   
* @version:    1.0  
* Create at:   2014年12月9日 上午3:10:03   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigInteger;

import com.cnfantasia.server.api.payment.entity.OrderPayInfo;

/**
 * Filename:    ICommPayService.java
 * @version:    1.0.0
 * Create at:   2014年12月9日 上午3:10:03
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月9日       shiyl             1.0             1.0 Version
 */
public interface ICommPayService {
	
	public OrderPayInfo getOrderPayInfoById(BigInteger userId,BigInteger orderId);

	/**
	 * @param orderId
	 * @param payMethod
	 * @param preferType 
	 */
	public void paySuccessOperate(BigInteger orderId,Integer payMethod);
	
}
