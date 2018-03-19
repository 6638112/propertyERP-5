/**   
 * Filename:    WeiXinPayService.java   
 * @version:    1.0  
 * Create at:   2014年12月9日 上午1:57:08   
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

import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil.WeiXinPay_GoalAccType;
import com.cnfantasia.server.api.payment.entity.OrderPayInfo;
import com.tenpay.ClientRequestHandler;
import com.tenpay.PackageRequestHandler;
import com.tenpay.PrepayIdRequestHandler;

/**
 * Filename: WeiXinPayService.java
 * 
 * @version: 1.0.0 Create at: 2014年12月9日 上午1:57:08 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年12月9日 shiyl 1.0 1.0 Version
 */
public class WeiXinPayService extends AbstractWeiXinPayService implements IWeiXinPayService {
	private ICommPayService commPayService;
	public void setCommPayService(ICommPayService commPayService) {
		this.commPayService = commPayService;
	}

	@Override
	public Map<String, Object> weixinPrePay(WeiXinPay_GoalAccType goalAccType,BigInteger userId, BigInteger orderId, String notify_url, String remoteAddr,
			PackageRequestHandler packageReqHandler, PrepayIdRequestHandler prepayReqHandler,
			ClientRequestHandler clientHandler) {
		OrderPayInfo orderPayInfo = commPayService.getOrderPayInfoById(userId, orderId);
		String productInfo = orderPayInfo.getProductInfo();
		Long totalAmount = orderPayInfo.getTotalAmount();
		String out_trade_no = orderPayInfo.getOutTradeNo();
		Map<String, Object> prePayResMap = super.weixinPrePay(goalAccType,userId, orderId, notify_url, remoteAddr, packageReqHandler,
				prepayReqHandler, clientHandler, productInfo, totalAmount, out_trade_no);
		return prePayResMap;
	}

	@Override
	public void paySuccessOperateComm(BigInteger orderId,Integer payMethod) {
		commPayService.paySuccessOperate(orderId,payMethod);
	}
	
}
