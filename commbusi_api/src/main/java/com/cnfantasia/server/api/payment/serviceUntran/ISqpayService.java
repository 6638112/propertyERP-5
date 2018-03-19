/**   
* Filename:    ISqpayService.java   
* @version:    1.0  
* Create at:   2015年10月12日 上午11:59:58   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigInteger;

import com.cnfantasia.server.api.payment.entity.OrderPayInfo;


/**
 * Filename:    ISqpayService.java
 * @version:    1.0.0
 * Create at:   2015年10月12日 上午11:59:58
 * Description:双乾支付服务
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月12日       shiyl             1.0             1.0 Version
 */
public interface ISqpayService {


	/**
	 * 支付回调
	 * @param merNo
	 * @param billNo
	 * @param amount
	 * @param succeed
	 * @param result
	 * @param mD5info
	 * @param merRemark
	 */
	public boolean payNotify(String merNo, String billNo, String amount, String succeed, String result, String mD5info,
			String merRemark,String MD5key,String paramAll,String flowNo);

	/**
	 * 支付成功
	 * @param orderId
	 * @param payMethod
	 */
	public void paySuccessOperateComm(BigInteger orderId, Integer payMethod);

	/**
	 * 根据Id查询订单信息
	 * @param userId
	 * @param orderId
	 * @return
	 */
	public OrderPayInfo getOrderPayInfoById(BigInteger userId, BigInteger orderId);
	
	/**
	 * 客户端标记为已支付
	 * @param billNo
	 */
	public void markOrderIsClientPay(String billNo);

	/**
	 * 查询订单是否已支付成功
	 * @param orderId
	 * @return
	 */
	public boolean qryOrderIsPay(BigInteger orderId);
	
}
