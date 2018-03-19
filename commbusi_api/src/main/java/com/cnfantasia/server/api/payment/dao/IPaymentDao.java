/**   
* Filename:    IPaymentDao.java   
* @version:    1.0  
* Create at:   2015年10月14日 下午4:29:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.dao;

import java.math.BigInteger;


/**
 * Filename:    IPaymentDao.java
 * @version:    1.0.0
 * Create at:   2015年10月14日 下午4:29:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月14日       shiyl             1.0             1.0 Version
 */
public interface IPaymentDao {

	/**
	 * 客户端标记为已支付
	 * @param billNo
	 * @return
	 */
	public Integer upadteOrderIsClientPayByOrderNo(String billNo);

	/**
	 * 查询订单支付状态
	 * @param orderId
	 * @return
	 */
	public Integer selectOrderPayStatus(BigInteger orderId);
	
}
