/**   
* Filename:    IAlipayService.java   
* @version:    1.0  
* Create at:   2014年12月9日 上午2:36:57   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigInteger;
import java.util.Map;

/**
 * Filename:    IAlipayService.java
 * @version:    1.0.0
 * Create at:   2014年12月9日 上午2:36:57
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月9日       shiyl             1.0             1.0 Version
 */
public interface IAlipayService {
	/**
	 * 预支付
	 * @param userId
	 * @param orderId
	 * @param notifyUrl 支付通知url
	 * @param isExpressGateway 是否为调用银行卡支付
	 * @return
	 */
	public String prePay(BigInteger userId, BigInteger orderId,String notifyUrl,boolean isExpressGateway);
	
	/**
	 * 支付通知处理
	 * 
	 * @param userId
	 */
	public boolean payNotify (Map<String, String> params);
	
	public boolean payNotifyTest (Map<String, String> params);
	
//	/**
//	 * 支付通知处理
//	 * @param out_trade_no 商户订单号
//	 * @param trade_no 支付宝交易号
//	 * @param trade_status 交易状态
//	 * @param verifyNotifyRes 验证结果
//	 * @return
//	 */
//	public boolean payNotify(String out_trade_no,String trade_no,String trade_status,boolean verifyNotifyRes,Map<String,String> params);
	
	/**
	 * 支付成功后的处理
	 * 
	 * @param orderId
	 * @param ebuyPayRecord
	 */
	public void paySuccessOperateComm(BigInteger orderId,Integer payMethod);
}
