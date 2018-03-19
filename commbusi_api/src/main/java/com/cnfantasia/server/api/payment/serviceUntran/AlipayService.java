/**   
* Filename:    AlipayService.java   
* @version:    1.0  
* Create at:   2014年12月9日 上午2:39:34   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.payment.entity.OrderPayInfo;
import com.cnfantasia.server.common.utils.PriceUtil;

/**
 * Filename:    AlipayService.java
 * @version:    1.0.0
 * Create at:   2014年12月9日 上午2:39:34
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月9日       shiyl             1.0             1.0 Version
 */
public class AlipayService extends AbstractAlipayService implements IAlipayService{
	private Log logger = LogFactory.getLog(getClass());
	
	private ICommPayService commPayService;
	public void setCommPayService(ICommPayService commPayService) {
		this.commPayService = commPayService;
	}

	@Override
	public String prePay(BigInteger userId, BigInteger orderId,String notifyUrl,boolean isExpressGateway) {
		logger.info("AlipayService prePay start,userId is:"+userId+",orderId is:"+orderId+",notifyUrl is:"+notifyUrl+",isExpressGateway is:"+isExpressGateway);
		OrderPayInfo orderPayInfo = commPayService.getOrderPayInfoById(userId, orderId);
		
		String outTradeNo = orderPayInfo.getOutTradeNo();
		String subject = orderPayInfo.getProductInfo();
		String price = PriceUtil.div100(orderPayInfo.getTotalAmount()).toString();//单位转化为元
		String body = orderPayInfo.getProductDetail()!=null?orderPayInfo.getProductDetail():orderPayInfo.getProductInfo();
		
		String resStr = super.prePay(userId,orderId,isExpressGateway, subject, body, price, notifyUrl, outTradeNo);
		logger.info("AlipayService prePay end");
		return resStr;
	}

	@Override
	public void paySuccessOperateComm(BigInteger orderId,Integer payMethod) {
		commPayService.paySuccessOperate(orderId,payMethod);
	}
	
	
}
