/**   
 * Filename:    AbstractAlipayService.java   
 * @version:    1.0  
 * Create at:   2014年12月9日 上午2:38:04   
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alipay.util.AlipayNotify;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.util.AlipayUtils;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;
import com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.dao.IEbuyPrepayAlipayLogBaseDao;
import com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.entity.EbuyPrepayAlipayLog;

/**
 * Filename: AbstractAlipayService.java
 * 
 * @version: 1.0.0 Create at: 2014年12月9日 上午2:38:04 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年12月9日 shiyl 1.0 1.0 Version
 */
public abstract class AbstractAlipayService implements IAlipayService {
	private Log logger = LogFactory.getLog(getClass());
	
	private IEbuyPrepayAlipayLogBaseDao ebuyPrepayAlipayLogBaseDao;
	public void setEbuyPrepayAlipayLogBaseDao(IEbuyPrepayAlipayLogBaseDao ebuyPrepayAlipayLogBaseDao) {
		this.ebuyPrepayAlipayLogBaseDao = ebuyPrepayAlipayLogBaseDao;
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

	public String prePay(BigInteger userId,BigInteger orderId,boolean isExpressGateway,String subject, String body, String price,String notifyUrl,String outTradeNo) {
		String resStr = null;
		Map<String,String> sPara = null;
		EbuyPrepayAlipayLog ebuyPrepayAlipayLogAdd = new EbuyPrepayAlipayLog();
		try {
			sPara = AlipayUtils.getOrderInfoAndSignMap(isExpressGateway, subject, body, price, notifyUrl, outTradeNo);
			resStr = AlipayUtils.createLinkString(sPara,true);//TODO 增加引号处理
		} catch (RuntimeException e) {
			ebuyPrepayAlipayLogAdd.setErrorInfo(e.getMessage());
			logger.info("Alipay prePay cause RuntimeException,errorMsg is :"+e.getMessage(),e);
			throw e;
		}finally{
			try {
				BigInteger toAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_prepay_alipay_log);
				ebuyPrepayAlipayLogAdd.setId(toAddId);
				ebuyPrepayAlipayLogAdd.setAmount(price);
				if(isExpressGateway){
					ebuyPrepayAlipayLogAdd.setIsExpressGateway(EbuyDict.EbuyPrepayAlipayLog_IsExpressGateway.YES);
				}else{
					ebuyPrepayAlipayLogAdd.setIsExpressGateway(EbuyDict.EbuyPrepayAlipayLog_IsExpressGateway.NO);
				}
				ebuyPrepayAlipayLogAdd.setNotifyUrl(notifyUrl);
				ebuyPrepayAlipayLogAdd.setOrderId(orderId);
				ebuyPrepayAlipayLogAdd.setOutTradeNo(outTradeNo);
				ebuyPrepayAlipayLogAdd.setPayLinkMap(JSON.toJSONString(sPara));
				ebuyPrepayAlipayLogAdd.setPayLinkStr(resStr);
				ebuyPrepayAlipayLogAdd.setProductDetail(body);
				ebuyPrepayAlipayLogAdd.setProductInfo(subject);
				ebuyPrepayAlipayLogAdd.setUserId(userId);
				Integer res = ebuyPrepayAlipayLogBaseDao.insertEbuyPrepayAlipayLog(ebuyPrepayAlipayLogAdd);
				if(res==null||res<=0){
					logger.info("Alipay prePay insertEbuyPrepayAlipayLog failed,res="+res);
				}
			} catch (Exception e2) {
				logger.info("Alipay prePay insertEbuyPrepayAlipayLog cause exception",e2);
			}
		}
		return resStr;
	}
	
	@Override
	public boolean payNotify(Map<String, String> params){
		logger.info("Alipay payNotify start,params is:"+JSON.toJSONString(params));
		String out_trade_no = params.get("out_trade_no");// 商户订单号
		String trade_no = params.get("trade_no");// 支付宝交易号
		String trade_status = params.get("trade_status");// 交易状态
		logger.info("Alipay payNotify,out_trade_no is :"+out_trade_no+",trade_no is:"+trade_no+",trade_status is:"+trade_status);
		
		Boolean payNotifyRes = false;
		EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
		ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Failed);//默认为失败
		try {
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			Boolean verifyNotifyRes = null;
			try {
				verifyNotifyRes = AlipayNotify.verify(params);
			} catch (Exception e) {
				logger.info("Alipay.payNotify.verifyNotify.params.failed", e);
				throw new BusinessRuntimeException("Alipay.payNotify.verifyNotify.params.failed", e);
			}
			logger.info("Alipay payNotify,verifyNotifyRes is :"+verifyNotifyRes);
			if (verifyNotifyRes) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				
				// 查询数据库当前的订单情况
				EbuyOrder ebuyOrder = commonEbuyService.selectEbuyOrderByOrderNo(out_trade_no);
				ebuyPayRecord.settEbuyOrderFId(ebuyOrder.getId());
				if (trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")) {
					if (ebuyOrder.getPayStatus() != null && EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(ebuyOrder.getPayStatus()) == 0){
						logger.info("Repeat notify,the order info has been updated .");
						ebuyPayRecord.setPayErrInfo("Repeat notify,the order info has been updated");
					}else{
						ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Success);//标记为支付成功
						paySuccessOperateComm(ebuyOrder.getId(),EbuyDict.EbuyPay_PayMethod.Alipay);//支付成功处理
					}
					payNotifyRes = true;
					//out.println("success"); // 请不要修改或删除
				}else{
					ebuyPayRecord.setPayErrInfo("trade_status result is:"+trade_status);
				}
			// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				//out.println("fail");
				ebuyPayRecord.setPayErrInfo("签名验证失败,verifyNotifyRes="+verifyNotifyRes);
			}
			
		} catch (RuntimeException e) {
			logger.info("Alipay payNotify cause RuntimeException "+e.getMessage(), e);
			ebuyPayRecord.setPayErrInfo(e.getMessage());
			throw e;
		}finally{
			try {
				String nowTime = dualDao.getNowTime();
				ebuyPayRecord.setFlowNo(trade_no);//
				ebuyPayRecord.setOrderNo(out_trade_no);
				ebuyPayRecord.setPayAccount(null);//暂时未获取到
				ebuyPayRecord.setPayAmount(null);//暂时未获取到
				ebuyPayRecord.setPayDesc(null);//支付描述，用户 xxx购买商品：aa*3;bb*4;
				ebuyPayRecord.setPayMethod(EbuyDict.EbuyPay_PayMethod.Alipay);
				ebuyPayRecord.setPayResultInfo(JSON.toJSONString(params));
				ebuyPayRecord.setPayTime(nowTime);//未返回
				// ebuyPayRecord.setCreateTime(nowTime);
				//ebuyPayRecord.setCreater(userId);
				boolean isSuccess = commEbuyPayRecordService.insertEbuyPayRecord(ebuyPayRecord);
				if(!isSuccess){
					logger.info("Alipay payNotify Alipay payNotify failed,resCount is "+isSuccess);
				}
			} catch (Exception e2) {
				logger.info("Alipay payNotify Alipay payNotify cause error"+e2.getMessage(),e2);
			}
			logger.info("Alipay payNotify end.");
		}
		return payNotifyRes;
	}
	
	@Override
	public boolean payNotifyTest(Map<String, String> params){
		logger.info("Alipay payNotify start,params is:"+JSON.toJSONString(params));
		String out_trade_no = params.get("out_trade_no");// 商户订单号
		String trade_no = params.get("trade_no");// 支付宝交易号
		String trade_status = params.get("trade_status");// 交易状态
		logger.info("Alipay payNotify,out_trade_no is :"+out_trade_no+",trade_no is:"+trade_no+",trade_status is:"+trade_status);
		
		Boolean payNotifyRes = false;
		EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
		ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Failed);//默认为失败
		try {
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			Boolean verifyNotifyRes = null;
			try {
//				verifyNotifyRes = AlipayNotify.verify(params);
				verifyNotifyRes = true;
			} catch (Exception e) {
				logger.info("Alipay.payNotify.verifyNotify.params.failed", e);
				throw new BusinessRuntimeException("Alipay.payNotify.verifyNotify.params.failed", e);
			}
			logger.info("Alipay payNotify,verifyNotifyRes is :"+verifyNotifyRes);
			if (verifyNotifyRes) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				
				// 查询数据库当前的订单情况
				EbuyOrder ebuyOrder = commonEbuyService.selectEbuyOrderByOrderNo(out_trade_no);
				ebuyPayRecord.settEbuyOrderFId(ebuyOrder.getId());
				if (trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")) {
					if (ebuyOrder.getPayStatus() != null && EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(ebuyOrder.getPayStatus()) == 0){
						logger.info("Repeat notify,the order info has been updated .");
						ebuyPayRecord.setPayErrInfo("Repeat notify,the order info has been updated");
					}else{
						ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Success);//标记为支付成功
						paySuccessOperateComm(ebuyOrder.getId(),EbuyDict.EbuyPay_PayMethod.Alipay);//支付成功处理
					}
					payNotifyRes = true;
					//out.println("success"); // 请不要修改或删除
				}else{
					ebuyPayRecord.setPayErrInfo("trade_status result is:"+trade_status);
				}
			// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				//out.println("fail");
				ebuyPayRecord.setPayErrInfo("签名验证失败,verifyNotifyRes="+verifyNotifyRes);
			}
			
		} catch (RuntimeException e) {
			logger.info("Alipay payNotify cause RuntimeException "+e.getMessage(), e);
			ebuyPayRecord.setPayErrInfo(e.getMessage());
			throw e;
		}finally{
			try {
				String nowTime = dualDao.getNowTime();
				ebuyPayRecord.setFlowNo(trade_no);//
				ebuyPayRecord.setOrderNo(out_trade_no);
				ebuyPayRecord.setPayAccount(null);//暂时未获取到
				ebuyPayRecord.setPayAmount(null);//暂时未获取到
				ebuyPayRecord.setPayDesc(null);//支付描述，用户 xxx购买商品：aa*3;bb*4;
				ebuyPayRecord.setPayMethod(EbuyDict.EbuyPay_PayMethod.Alipay);
				ebuyPayRecord.setPayResultInfo(JSON.toJSONString(params));
				ebuyPayRecord.setPayTime(nowTime);//未返回
				// ebuyPayRecord.setCreateTime(nowTime);
				//ebuyPayRecord.setCreater(userId);
				boolean isSuccess = commEbuyPayRecordService.insertEbuyPayRecord(ebuyPayRecord);
				if(!isSuccess){
					logger.info("Alipay payNotify Alipay payNotify failed,resCount is "+isSuccess);
				}
			} catch (Exception e2) {
				logger.info("Alipay payNotify Alipay payNotify cause error"+e2.getMessage(),e2);
			}
			logger.info("Alipay payNotify end.");
		}
		return payNotifyRes;
	}
	
//	@Override
//	public boolean payNotify(String out_trade_no, String trade_no, String trade_status, boolean verifyNotifyRes,Map<String,String> params) {
//		Boolean payNotifyRes = null;
//		logger.info("Alipay payNotify,out_trade_no is :"+out_trade_no+",trade_no is:"+trade_no+",trade_status is:"+trade_status+",verifyNotifyRes is "+verifyNotifyRes);
//		if (verifyNotifyRes) {// 验证成功
//			
//
//			if (trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")) {
//				if (ebuyOrder.getPayStatus() != null && EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(ebuyOrder.getPayStatus()) == 0){
//					logger.info("Repeat notify,the order info has been updated .");
//				}else{
//					
//				}
//				payNotifyRes = true;
////				out.println("success"); // 请不要修改或删除
//			}
//			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//
//			// ////////////////////////////////////////////////////////////////////////////////////////
//		} else {// 验证失败
//			payNotifyRes = false;
////			out.println("fail");
//		}
//		return payNotifyRes;
//	}
	
}
