/**   
 * Filename:    IWeiXinPayService.java   
 * @version:    1.0  
 * Create at:   2014年12月9日 上午1:40:52   
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
import com.cnfantasia.server.api.payment.entity.WeiXinNotifyEntity;
import com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.entity.EbuyPrepayWeixinLog;
import com.tenpay.ClientRequestHandler;
import com.tenpay.PackageRequestHandler;
import com.tenpay.PrepayIdRequestHandler;

/**
 * Filename: IWeiXinPayService.java
 * 
 * @version: 1.0.0 Create at: 2014年12月9日 上午1:40:52 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年12月9日 shiyl 1.0 1.0 Version
 */
public interface IWeiXinPayService {
	
	/**
	 * 微信支付
	 * @param userId 用户Id
	 * @param orderId 订单Id
	 * @param hbAmount 粮票金额
	 * @param notify_url 财付通支付通知url
	 * @param remoteAddr 订单生成的机器IP，指用户浏览器端IP
	 * @param packageReqHandler 生成package的请求类
	 * @param prepayReqHandler 获取prepayid的请求类
	 * @param clientHandler 返回客户端支付参数的请求类
	 * @return
	 */
	Map<String, Object> weixinPrePay(WeiXinPay_GoalAccType goalAccType, BigInteger userId, BigInteger orderId, String notify_url,
									 String remoteAddr, PackageRequestHandler packageReqHandler, PrepayIdRequestHandler prepayReqHandler,
									 ClientRequestHandler clientHandler);
	/**
	 * 增加微信预支付日志
	 * 
	 * @param ebuyPrepayWeixinLog
	 */
	void addPrepayWeixinLog(EbuyPrepayWeixinLog ebuyPrepayWeixinLog);

	/**
	 * 微信支付通知处理
	 * 
	 * @param userId
	 * @param resultEntity
	 */
	void payNotify(WeiXinNotifyEntity resultEntity);

	/**
	 * 增加微信支付通知记录
	 * 
	 * @param currWeiXinNotifyEntity
	 * @param payErrInfo
	 * @param orderId
	 */
	void addNotifyRecord(WeiXinNotifyEntity currWeiXinNotifyEntity, String payErrInfo, BigInteger orderId);

	/**
	 * 支付成功后的处理
	 * 
	 * @param orderId
	 */
	void paySuccessOperateComm(BigInteger orderId, Integer payMethod);
}
