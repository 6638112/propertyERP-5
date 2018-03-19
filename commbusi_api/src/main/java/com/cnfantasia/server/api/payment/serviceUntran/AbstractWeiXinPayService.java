/**   
 * Filename:    AbstractWeiXinPayService.java   
 * @version:    1.0  
 * Create at:   2014年12月9日 上午1:53:37   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年12月9日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.payment.serviceUntran;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil.WeiXinPay_GoalAccType;
import com.cnfantasia.server.api.payment.entity.WeiXinNotifyEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyPayRecord.dao.IEbuyPayRecordBaseDao;
import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;
import com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.dao.IEbuyPrepayWeixinLogBaseDao;
import com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.entity.EbuyPrepayWeixinLog;
import com.tenpay.AccessTokenRequestHandler;
import com.tenpay.ClientRequestHandler;
import com.tenpay.PackageRequestHandler;
import com.tenpay.PrepayIdRequestHandler;
import com.tenpay.util.ConstantUtil;
import com.tenpay.util.WXUtil;

/**
 * Filename: AbstractWeiXinPayService.java
 * 
 * @version: 1.0.0 Create at: 2014年12月9日 上午1:53:37 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年12月9日 shiyl 1.0 1.0 Version
 */
public abstract class AbstractWeiXinPayService implements IWeiXinPayService {

	private Log logger = LogFactory.getLog(getClass());
	public IEbuyPrepayWeixinLogBaseDao ebuyPrepayWeixinLogBaseDao;
	public void setEbuyPrepayWeixinLogBaseDao(IEbuyPrepayWeixinLogBaseDao ebuyPrepayWeixinLogBaseDao) {
		this.ebuyPrepayWeixinLogBaseDao = ebuyPrepayWeixinLogBaseDao;
	}

	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	private ICommonEbuyService commonEbuyService;
	public void setCommonEbuyService(ICommonEbuyService commonEbuyService) {
		this.commonEbuyService = commonEbuyService;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private CommEbuyPayRecordService commEbuyPayRecordService;
	public void setCommEbuyPayRecordService(
			CommEbuyPayRecordService commEbuyPayRecordService) {
		this.commEbuyPayRecordService = commEbuyPayRecordService;
	}
	
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
	 * @param productInfo 商品描述信息
	 * @param totalAmount 需要支付的总金额,以分为单位
	 * @param out_trade_no 对外的订单编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> weixinPrePay(WeiXinPay_GoalAccType goalAccType,BigInteger userId, BigInteger orderId, String notify_url, String remoteAddr,
			PackageRequestHandler packageReqHandler, PrepayIdRequestHandler prepayReqHandler,
			ClientRequestHandler clientHandler, String productInfo, Long totalAmount, String out_trade_no) {
		logger.info("WeiXinPayService prePayRequest start, userId is:" + userId 
				+ ",orderId is:" + orderId
				+ ",notify_url is:"+ notify_url 
				+ ",remoteAddr is:"+ remoteAddr 
				+ ",productInfo is:" + productInfo
				+ ",totalAmount is:" + totalAmount
				+ ",out_trade_no is:" + out_trade_no + "..");
		// 日志记录对象
		EbuyPrepayWeixinLog ebuyPrepayWeixinLog = new EbuyPrepayWeixinLog();
		try {
			{// 日志
				ebuyPrepayWeixinLog.setUserId(userId);
				ebuyPrepayWeixinLog.setOrderId(orderId);
			}
			// 2.交互
			int retcode;
			// String xml_body = "";
			Map<String, Object> clientParameters = null;
			{
				// ---------------------------------------------------------
				// 微信支付请求示例，商户按照此文档进行开发即可
				// ---------------------------------------------------------

				// 接收财付通通知的URL
				{// 日志
					ebuyPrepayWeixinLog.setNotifyUrl(notify_url);
				}
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

				packageReqHandler.setKey(ConstantUtil.getPARTNER_KEY(goalAccType));

				// 获取token值
				String token = AccessTokenRequestHandler.getAccessToken(goalAccType);
				{// 日志
					ebuyPrepayWeixinLog.setAccessToken(token);
					logger.info("token=" + token);
				}
				if (!"".equals(token)) {
					// 设置package订单参数
					packageReqHandler.setParameter("bank_type", "WX");// 银行渠道
					packageReqHandler.setParameter("body", productInfo); // 商品描述
					packageReqHandler.setParameter("notify_url", notify_url); // 接收财付通通知的URL
					packageReqHandler.setParameter("partner", ConstantUtil.getPARTNER(goalAccType)); // 商户号
					packageReqHandler.setParameter("out_trade_no", out_trade_no); // 商家订单号
					packageReqHandler.setParameter("total_fee", totalAmount.toString()); // 商品金额,以分为单位
					packageReqHandler.setParameter("spbill_create_ip", remoteAddr); // 订单生成的机器IP，指用户浏览器端IP
					packageReqHandler.setParameter("fee_type", "1"); // 币种，1人民币 66
					packageReqHandler.setParameter("input_charset", "UTF-8"); // 字符编码
					{// 日志
						Map<String, Object> packageParamsMap = new HashMap<String, Object>();
						packageParamsMap.put("bank_type", "WX");// 银行渠道
						if(productInfo != null && productInfo.length() > 59) {
							productInfo = productInfo.substring(0, 59);
						}
						packageParamsMap.put("body", productInfo); // 商品描述
						packageParamsMap.put("notify_url", notify_url); // 接收财付通通知的URL
						packageParamsMap.put("partner", ConstantUtil.getPARTNER(goalAccType)); // 商户号
						packageParamsMap.put("out_trade_no", out_trade_no); // 商家订单号
						packageParamsMap.put("total_fee", totalAmount.toString()); // 商品金额,以分为单位
						packageParamsMap.put("spbill_create_ip", remoteAddr); // 订单生成的机器IP，指用户浏览器端IP
						packageParamsMap.put("fee_type", "1"); // 币种，1人民币 66
						packageParamsMap.put("input_charset", "UTF-8"); // 字符编码
						ebuyPrepayWeixinLog.setPackageParams(JSON.toJSONString(packageParamsMap));
						if (logger.isDebugEnabled()) {
							logger.debug("packageParamsMap=" + JSON.toJSONString(packageParamsMap));
						}
					}

					// 获取package包
					String packageValue = null;
					try {
						packageValue = packageReqHandler.getRequestURL();
						{// 日志
							ebuyPrepayWeixinLog.setPackageValue(packageValue);
							if (logger.isDebugEnabled()) {
								logger.debug("packageValue=" + packageValue);
							}
						}
					} catch (UnsupportedEncodingException e) {
						logger.info(
								"EbuyPayService.prePayRequest.getRequestURL.UnsupportedEncodingException.error" + e.getMessage(), e);
						throw new BusinessRuntimeException(
								"EbuyPayService.prePayRequest.getRequestURL.UnsupportedEncodingException.error", e);
					}

					String noncestr = WXUtil.getNonceStr();
					String timestamp = WXUtil.getTimeStamp();
					// String traceid = "";
					String traceid = userId.toString();// 使用用户Id
					// //设置获取prepayid支付参数
					prepayReqHandler.setParameter("appid", ConstantUtil.getAPP_ID(goalAccType));
					prepayReqHandler.setParameter("appkey", ConstantUtil.getAPP_KEY(goalAccType));
					prepayReqHandler.setParameter("noncestr", noncestr);
					prepayReqHandler.setParameter("package", packageValue);
					prepayReqHandler.setParameter("timestamp", timestamp);
					prepayReqHandler.setParameter("traceid", traceid);

					// 生成获取预支付签名
					String sign = prepayReqHandler.createSHA1Sign();
					// 增加非参与签名的额外参数
					prepayReqHandler.setParameter("app_signature", sign);
					prepayReqHandler.setParameter("sign_method", ConstantUtil.SIGN_METHOD);
					String gateUrl = ConstantUtil.GATEURL + token;
					prepayReqHandler.setGateUrl(gateUrl);
					{// 日志
						Map<String, Object> prepayIdParams = new HashMap<String, Object>();
						prepayIdParams.put("appid", ConstantUtil.getAPP_ID(goalAccType));
						prepayIdParams.put("appkey", ConstantUtil.getAPP_KEY(goalAccType));
						prepayIdParams.put("noncestr", noncestr);
						prepayIdParams.put("package", packageValue);
						prepayIdParams.put("timestamp", timestamp);
						prepayIdParams.put("traceid", traceid);
						prepayIdParams.put("app_signature", sign);
						prepayIdParams.put("sign_method", ConstantUtil.SIGN_METHOD);
						prepayIdParams.put("prepayReqHandler.gateUrl", gateUrl);
						ebuyPrepayWeixinLog.setPrepayIdParams(JSON.toJSONString(prepayIdParams));
						if (logger.isDebugEnabled()) {
							logger.debug("prepayIdParams=" + prepayIdParams);
						}
					}
					// 获取prepayId
					String prepayid = null;
					try {
						prepayid = prepayReqHandler.sendPrepay();
						{// 日志
							ebuyPrepayWeixinLog.setPrepayId(prepayid);
							logger.info("prepayid=" + prepayid);
						}
					} catch (JSONException e) {
						logger.info("EbuyPayService.prePayRequest.prepayReqHandler.sendPrepay.error:" + e.getMessage(), e);
						throw new BusinessRuntimeException("EbuyPayService.prePayRequest.prepayReqHandler.sendPrepay.error", e);
					}
					// 吐回给客户端的参数
					if (null != prepayid && !"".equals(prepayid)) {
						// 输出参数列表
						clientHandler.setParameter("appid", ConstantUtil.getAPP_ID(goalAccType));
						clientHandler.setParameter("appkey", ConstantUtil.getAPP_KEY(goalAccType));
						clientHandler.setParameter("noncestr", noncestr);
						clientHandler.setParameter("package", "Sign=WXPay");
						clientHandler.setParameter("partnerid", ConstantUtil.getPARTNER(goalAccType));
						clientHandler.setParameter("prepayid", prepayid);
						clientHandler.setParameter("timestamp", timestamp);
						// 生成签名
						sign = clientHandler.createSHA1Sign();
						clientHandler.setParameter("sign", sign);

						// xml_body = clientHandler.getXmlBody();
						clientParameters = clientHandler.getAllParameters();
						{// 日志
							Map<String, Object> clientBackParamsMap = new HashMap<String, Object>();
							clientBackParamsMap.put("appid", ConstantUtil.getAPP_ID(goalAccType));
							clientBackParamsMap.put("appkey", ConstantUtil.getAPP_KEY(goalAccType));
							clientBackParamsMap.put("noncestr", noncestr);
							clientBackParamsMap.put("package", "Sign=WXPay");
							clientBackParamsMap.put("partnerid", ConstantUtil.getPARTNER(goalAccType));
							clientBackParamsMap.put("prepayid", prepayid);
							clientBackParamsMap.put("timestamp", timestamp);
							clientBackParamsMap.put("sign", sign);
							ebuyPrepayWeixinLog.setClientBackParams(JSON.toJSONString(clientBackParamsMap));
							if (logger.isDebugEnabled()) {
								logger.debug("clientBackParamsMap=" + clientBackParamsMap);
							}
						}
						retcode = 0;
					} else {
						retcode = -2;
					}
				} else {
					retcode = -1;
				}
				/**
				 * 打印debug信息
				 */
				if (logger.isDebugEnabled()) {
					logger.debug("\r\ndebuginfo:\r\n" + new Date());
					logger.debug("packageReqHandler.getDebugInfo()=" + packageReqHandler.getDebugInfo());
					logger.debug("prepayReqHandler.getDebugInfo()=" + prepayReqHandler.getDebugInfo());
					logger.debug("clientHandler.getDebugInfo()=" + clientHandler.getDebugInfo());
				}
				{// 日志
					Map<String, Object> debugInfoMap = new HashMap<String, Object>();
					debugInfoMap.put("time", new Date());
					debugInfoMap.put("packageReqHandler.getDebugInfo()", packageReqHandler.getDebugInfo());
					debugInfoMap.put("prepayReqHandler.getDebugInfo()", prepayReqHandler.getDebugInfo());
					debugInfoMap.put("clientHandler.getDebugInfo()", clientHandler.getDebugInfo());
					ebuyPrepayWeixinLog.setDebugInfo(JSON.toJSONString(debugInfoMap));
				}

				{// 日志
					ebuyPrepayWeixinLog.setRetcode(retcode + "");
					logger.info("retcode=" + retcode);
				}
				if (retcode == 0) {
				} else if (retcode == -2) {
					throw new BusinessRuntimeException("EbuyPayService.prePayRequest.getPrepayId.error");
				} else if (retcode == -1) {
					throw new BusinessRuntimeException("EbuyPayService.prePayRequest.getToken.error");
				} else {
					throw new BusinessRuntimeException("EbuyPayService.prePayRequest.unkonwn.error");
				}

			}
			// 3.结果处理
			Map<String, Object> resMap = new HashMap<String, Object>();
			for (String key : clientParameters.keySet()) {
				if (!"appkey".equals(key)) {
					if ("package".equals(key)) {
						resMap.put("packageValue", clientParameters.get(key));
					} else {
						resMap.put(key, clientParameters.get(key));
					}
				}
			}
			logger.info(JSON.toJSONString(resMap));
			return resMap;
		} catch (RuntimeException e) {
			ebuyPrepayWeixinLog.setErrInfo(e.getMessage());
			logger.info("EbuyPayService prePayRequest cause RuntimeException :"+e.getMessage(), e);
			throw e;
		} finally {
			addPrepayWeixinLog(ebuyPrepayWeixinLog);
			logger.info("EbuyPayService prePayRequest end..");
		}
		
	}

	@Override
	public void addPrepayWeixinLog(EbuyPrepayWeixinLog ebuyPrepayWeixinLog) {
		try {
			BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_prepay_weixin_log);
			ebuyPrepayWeixinLog.setId(addId);
			ebuyPrepayWeixinLogBaseDao.insertEbuyPrepayWeixinLog(ebuyPrepayWeixinLog);
		} catch (Exception e) {
			logger.info("addPrepayWeixinLog cause error", e);
		}
	}

	@Override
	public void payNotify(WeiXinNotifyEntity resultEntity) {
		logger.info("WeiXinPayService payNotify start,resultEntity is:"+JSON.toJSONString(resultEntity));
		String orderNo = resultEntity.getOut_trade_no();
		// 查询数据库当前的订单情况
		EbuyOrder ebuyOrder = commonEbuyService.selectEbuyOrderByOrderNo(orderNo);

		// 处理重复通知的情况
		if (ebuyOrder.getPayStatus() != null// &&ebuyOrder.getStatus()>=DictConstants.EbuyOrder_Status.DaiFaHuo
				&& EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(ebuyOrder.getPayStatus()) == 0 
		) {// 如果订单状态为支付成功，则不作处理，直接返回通知支付成功
			logger.info("Repeat notify,the order info has been updated .");// TODO
			//重复通知了,记录日志
			return;
		}

		// 2 处理数据库逻辑
		{// 校验返回数据金额自身合法性
			boolean valiFeeBool = resultEntity.validateFee();
			if (!valiFeeBool) {
				logger.error("payNotify validateFee failed,product_fee is " + resultEntity.getProduct_fee()
						+ ";transport_fee is " + resultEntity.getTransport_fee() + ";total_fee is" + resultEntity.getTotal_fee());
				throw new ValidateRuntimeException("ebuyService.payNotify.resultEntity.validate.falied");
			}
		}
		{// 注意判断返回金额,校验
			Long myAmount = ebuyOrder.getAmount();
			Long returnAmount = resultEntity.fetchAmount();
			if (myAmount == null || returnAmount == null || myAmount.compareTo(returnAmount) != 0) {
				logger.error("payNotify checkAmount notEqual,myAmount is " + myAmount + ";returnAmount is " + returnAmount);
				throw new BusinessRuntimeException("ebuyService.payNotify.checkAmount.notEqual.error");
			}
		}

		
		{
			// 生成成功支付记录
			String nowTime = dualDao.getNowTime();
			EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
			ebuyPayRecord.setFlowNo(resultEntity.getTransaction_id());//
			ebuyPayRecord.setOrderNo(resultEntity.getOut_trade_no());
			ebuyPayRecord.setPayAccount(null);// TODO 暂时未获取到
			ebuyPayRecord.setPayAmount(resultEntity.fetchAmount());
			ebuyPayRecord.setPayDesc(null);// TODO 支付描述，用户 xxx购买商品：aa*3;bb*4;
			ebuyPayRecord.setPayMethod(EbuyDict.EbuyPay_PayMethod.WeiXin);
			ebuyPayRecord.setPayResultInfo(JSON.toJSONString(resultEntity));
			ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Success);
//			ebuyPayRecord.setPayTime(resultEntity.getTime_end());
			ebuyPayRecord.setPayTime(nowTime);
			ebuyPayRecord.settEbuyOrderFId(ebuyOrder.getId());
			// ebuyPayRecord.setCreateTime(nowTime);
			//ebuyPayRecord.setCreater(userId);
			boolean isSuccess = commEbuyPayRecordService.insertEbuyPayRecord(ebuyPayRecord);
			if(!isSuccess){
				throw new BusinessRuntimeException("CommonEbuyService.paySuccessOperateComm.insertEbuyPayRecord.failed");
			}
		}
		paySuccessOperateComm(ebuyOrder.getId(),EbuyDict.EbuyPay_PayMethod.WeiXin);
		// 注意交易单不要重复处理
		logger.info("WeiXinPayService payNotify end.");
	}

	@Override
	public void addNotifyRecord(WeiXinNotifyEntity currWeiXinNotifyEntity, String payErrInfo, BigInteger orderId) {
		// 增加支付记录
		String nowTime = dualDao.getNowTime();
		EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
		ebuyPayRecord.setFlowNo(currWeiXinNotifyEntity.getTransaction_id());//
		ebuyPayRecord.setOrderNo(currWeiXinNotifyEntity.getOut_trade_no());
		ebuyPayRecord.setPayAccount(null);// TODO 暂时未获取到
		ebuyPayRecord.setPayAmount(currWeiXinNotifyEntity.fetchAmount());
		ebuyPayRecord.setPayDesc(null);// TODO 支付描述，用户 xxx购买商品：aa*3;bb*4;
		ebuyPayRecord.setPayMethod(EbuyDict.EbuyPay_PayMethod.WeiXin);
		ebuyPayRecord.setPayResultInfo(JSON.toJSONString(currWeiXinNotifyEntity));
		ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Failed);
		ebuyPayRecord.setPayTime(nowTime);
//		ebuyPayRecord.setPayTime(currWeiXinNotifyEntity.getTime_end());
		ebuyPayRecord.settEbuyOrderFId(orderId);
		ebuyPayRecord.setPayErrInfo(payErrInfo);
		// String nowTime = dualDao.getNowTime();
		// ebuyPayRecord.setCreateTime(nowTime);
		commEbuyPayRecordService.insertEbuyPayRecord(ebuyPayRecord);
	}

}
