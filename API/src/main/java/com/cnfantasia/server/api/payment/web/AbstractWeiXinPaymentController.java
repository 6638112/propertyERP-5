/**   
* Filename:    AbstractWeiXinPaymentController.java   
* @version:    1.0  
* Create at:   2014年11月20日 上午6:08:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月20日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil.WeiXinPay_GoalAccType;
import com.cnfantasia.server.api.payment.entity.WeiXinNotifyEntity;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.tenpay.ClientRequestHandler;
import com.tenpay.PackageRequestHandler;
import com.tenpay.PrepayIdRequestHandler;
import com.tenpay.RequestHandler;
import com.tenpay.ResponseHandler;
import com.tenpay.client.ClientResponseHandler;
import com.tenpay.client.TenpayHttpClient;
import com.tenpay.util.ConstantUtil;

/**
 * Filename:    AbstractWeiXinPaymentController.java
 * @version:    1.0.0
 * Create at:   2014年11月20日 上午6:08:28
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月20日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractWeiXinPaymentController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	public JsonResponse prePayRequest(WeiXinPay_GoalAccType goalAccType,HttpServletRequest request, HttpServletResponse response) {
		logger.info("WeiXinPayController prePayRequest start..");
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		if(StringUtils.isEmpty(request.getParameter("orderId"))){
			throw new BusinessRuntimeException("Payment.orderId.isEmpty");
		}
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		// 接收财付通通知的URL
		// String notify_url =
		// "http://127.0.0.1:8180/tenpay_api_b2c/payNotifyUrl.jsp";
		
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
//				+ "/";
//		String notify_url = basePath + getNotifyUrl();
		//syl-del-2014-11-24 22:14:58
		String notify_url =  getNotifyUrl();
		
	// // ---------------生成订单号 开始------------------------
		// // 当前时间 yyyyMMddHHmmss
		// String currTime = TenpayUtil.getCurrTime();
		// // 8位日期
		// String strTime = currTime.substring(8, currTime.length());
		// // 四位随机数
		// String strRandom = TenpayUtil.buildRandom(4) + "";
		// // 10位序列号,可以自行调整。
		// String strReq = strTime + strRandom;
		// // 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
		// String out_trade_no = strReq;
		// // ---------------生成订单号 结束------------------------

		PackageRequestHandler packageReqHandler = new PackageRequestHandler(request, response);// 生成package的请求类
		PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);// 获取prepayid的请求类
		ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);// 返回客户端支付参数的请求类
		String remoteAddr = request.getRemoteAddr();
		Map<String,Object> resMap = doWeixinPrePay(goalAccType,userId, orderId, notify_url, remoteAddr, packageReqHandler, prepayReqHandler, clientHandler);
		jsonResponse.setDataValue(resMap);
		logger.info("WeiXinPayController prePayRequest end..");
		return jsonResponse;
	}
	
	public JsonResponse payNotify(WeiXinPay_GoalAccType goalAccType,HttpServletRequest request, HttpServletResponse response) {
		logger.info("WeiXinPayController payNotify start..");
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		// 2.交互
		{
			// ---------------------------------------------------------
			// 财付通支付通知（后台通知）示例，商户按照此文档进行开发即可
			// ---------------------------------------------------------
			// 商户号
			String partner = ConstantUtil.getPARTNER(goalAccType);

			// 密钥
			String key = ConstantUtil.getPARTNER_KEY(goalAccType);

			// 创建支付应答对象
			ResponseHandler resHandler = new ResponseHandler(request, response);
			resHandler.setKey(key);

			// 判断签名
			if (resHandler.isTenpaySign()) {

				// 通知id
				String notify_id = resHandler.getParameter("notify_id");

				// 创建请求对象
				RequestHandler queryReq = new RequestHandler(null, null);
				// 通信对象
				TenpayHttpClient httpClient = new TenpayHttpClient();
				// 应答对象
				ClientResponseHandler queryRes = new ClientResponseHandler();

				// 通过通知ID查询，确保通知来至财付通
				queryReq.init();
				queryReq.setKey(key);
				queryReq.setGateUrl("https://gw.tenpay.com/gateway/verifynotifyid.xml");
				queryReq.setParameter("partner", partner);
				queryReq.setParameter("notify_id", notify_id);

				// 通信对象
				httpClient.setTimeOut(5);
				// 设置请求内容
				try {
					String requestURL = queryReq.getRequestURL();
					httpClient.setReqContent(requestURL);
					logger.info("queryReq.requestURL:"+requestURL);
				} catch (UnsupportedEncodingException e) {
					throw new BusinessRuntimeException("WeiXinPayController.payNotify.httpClient.setReqContent.error",e);
				}
				// 后台调用
				if (httpClient.call()) {
					// 设置结果参数
					try {
						String resContent = httpClient.getResContent();
						queryRes.setContent(resContent);
						logger.info("queryRes.resContent:"+resContent);
					} catch (Exception e) {
						throw new BusinessRuntimeException("WeiXinPayController.payNotify.httpClient.getResContent.error",e);
					}
					queryRes.setKey(key);

					// 获取返回参数
					WeiXinNotifyEntity currWeiXinNotifyEntity = WeiXinNotifyEntity.loadData(queryRes);
					String retcode = currWeiXinNotifyEntity.getRetcode();//交易返回码
					Integer trade_state = currWeiXinNotifyEntity.getTrade_state();//交易状态
					Integer trade_mode = currWeiXinNotifyEntity.getTrade_mode();

					// 判断签名及结果
					if (queryRes.isTenpaySign() && "0".equals(retcode) && (trade_state!=null&& 0==trade_state) && (trade_mode!=null&&1==trade_mode)) {
						logger.info("order query success !");//订单查询成功
						// 取结果参数做业务处理
						logger.info("out_trade_no:" + queryRes.getParameter("out_trade_no") + " transaction_id:"+ queryRes.getParameter("transaction_id"));
						logger.info("trade_state:" + queryRes.getParameter("trade_state") + " total_fee:"+ queryRes.getParameter("total_fee"));
						// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
						logger.info("discount:" + queryRes.getParameter("discount") + " time_end:"+ queryRes.getParameter("time_end"));
						// ------------------------------
						// 处理业务开始
						// ------------------------------
						{
							payNotify(currWeiXinNotifyEntity);//处理失败则抛出异常
							//处理重复通知的情况
							// 处理数据库逻辑
							// 注意交易单不要重复处理
							// 注意判断返回金额
						}
						// ------------------------------
						// 处理业务完毕
						// ------------------------------
						
						try {//返回处理结果给财付通服务器，成功或者失败
							resHandler.sendToCFT("Success");
						} catch (IOException e) {
							logger.info("sendToCFT.errorr",e);
							throw new BusinessRuntimeException("WeiXinPayController.payNotify.resHandler.sendToCFT.error",e);
						}
					} else {//查询验证签名失败或业务错误
						// 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
						logger.info("qrysign Or Business Error");
						logger.info("retcode:" + queryRes.getParameter("retcode") + " retmsg:"+ queryRes.getParameter("retmsg"));
						//增加支付记录
						String payErrInfo = "qrysign Or Business Error,retcode:" + queryRes.getParameter("retcode") + " retmsg:"+ queryRes.getParameter("retmsg");
//						String orderNo = currWeiXinNotifyEntity.getOut_trade_no();
						try {
							addNotifyRecord(currWeiXinNotifyEntity, payErrInfo,null);
						} catch (Exception e) {
							logger.info("WeiXinPayController payNotify addNotifyRecord cause error:",e);
						}
						throw new BusinessRuntimeException("WeiXinPayController.payNotify.qrysignOrBusinessError.error");
					}
				} else {//后台调用通信失败,有可能因为网络原因，请求已经处理，但未收到应答。
					logger.info("back Call Communicate failed.");
					logger.info(httpClient.getResponseCode());
					logger.info(httpClient.getErrInfo());
					throw new BusinessRuntimeException("WeiXinPayController.payNotify.backCallCommunicate.failed");
				}
			} else {//通知签名验证失败
				logger.info("payNotify.notifySign.validate.failed");
				throw new BusinessRuntimeException("WeiXinPayController.payNotify.notifySign.validate.failed");
			}
		}
		// 3.结果返回
		logger.info("WeiXinPayController payNotify end..");
		return jsonResponse;
	}
	
	public abstract Map<String,Object> doWeixinPrePay(WeiXinPay_GoalAccType goalAccType,BigInteger userId,BigInteger orderId,String notify_url,String remoteAddr
			,PackageRequestHandler packageReqHandler,PrepayIdRequestHandler prepayReqHandler,ClientRequestHandler clientHandler);
	public abstract String getNotifyUrl();
	
	/**
	 * 微信支付通知处理
	 * @param userId
	 * @param resultEntity
	 */
	public abstract void payNotify(WeiXinNotifyEntity resultEntity);
	
	/**
	 * 增加微信支付通知记录
	 * @param currWeiXinNotifyEntity
	 * @param payErrInfo
	 * @param orderId
	 */
	public abstract void addNotifyRecord(WeiXinNotifyEntity currWeiXinNotifyEntity,String payErrInfo,BigInteger orderId);
}
