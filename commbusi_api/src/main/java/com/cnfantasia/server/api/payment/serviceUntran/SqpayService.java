/**   
 * Filename:    SqpayService.java   
 * @version:    1.0  
 * Create at:   2015年10月12日 下午12:00:18   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2015年10月12日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.dao.IPaymentDao;
import com.cnfantasia.server.api.payment.entity.OrderPayInfo;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.common.httpcllient.https.SqMD5Util;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;

/**
 * Filename: SqpayService.java
 * 
 * @version: 1.0.0 Create at: 2015年10月12日 下午12:00:18 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2015年10月12日 shiyl 1.0 1.0 Version
 */
public class SqpayService implements ISqpayService {
	private Log logger = LogFactory.getLog(getClass());
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommPayService commPayService;
	public void setCommPayService(ICommPayService commPayService) {
		this.commPayService = commPayService;
	}

	private ICommonEbuyService commonEbuyService;
	public void setCommonEbuyService(ICommonEbuyService commonEbuyService) {
		this.commonEbuyService = commonEbuyService;
	}
	
	private IPaymentDao paymentDao;
	public void setPaymentDao(IPaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}
	
	private CommEbuyPayRecordService commEbuyPayRecordService;
	public void setCommEbuyPayRecordService(
			CommEbuyPayRecordService commEbuyPayRecordService) {
		this.commEbuyPayRecordService = commEbuyPayRecordService;
	}

	@Override
	public boolean payNotify(String MerNo, String BillNo, String Amount, String Succeed, String Result, String MD5info,
			String merRemark, String MD5key, String paramAll,String flowNo) {
		logger.info("Sqpay.payNotify start,MerNo:" + MerNo + ",BillNo:" + BillNo + ",Amount:" + Amount + ",Succeed:"
				+ Succeed + ",Result:" + Result + ",MD5info:" + MD5info + ",merRemark:" + merRemark + ",MD5key:" + MD5key
				+ ",paramAll:" + paramAll+ ",flowNo:" + flowNo);
		Boolean payNotifyRes = false;
		EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
		ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Failed);// 默认为失败
		try {
			Boolean verifyNotifyRes = false;
			try {
				String md5str = new SqMD5Util().signMap(new String[] { MerNo, BillNo, Amount, String.valueOf(Succeed) },
						MD5key, "RES");
				if (MD5info.equals(md5str)) {
					verifyNotifyRes = true;
				}
			} catch (Exception e) {
				logger.info("Sqpay.payNotify.verifyNotify.failed", e);
			}
			if (verifyNotifyRes) {// 验证成功
				// 查询数据库当前的订单情况
				EbuyOrder ebuyOrder = commonEbuyService.selectEbuyOrderByOrderNo(BillNo);
				ebuyPayRecord.settEbuyOrderFId(ebuyOrder.getId());
				if (Succeed.equals("88")) {
					if (ebuyOrder.getPayStatus() != null
							&& EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(ebuyOrder.getPayStatus()) == 0) {
						logger.info("SqPay Repeat notify,the order info has been updated .");
						ebuyPayRecord.setPayErrInfo("SqPay Repeat notify,the order info has been updated");
					} else {
						ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Success);// 标记为支付成功
						paySuccessOperateComm(ebuyOrder.getId(), EbuyDict.EbuyPay_PayMethod.SqPay);// 支付成功处理
					}
					payNotifyRes = true;
				} else {
					ebuyPayRecord.setPayErrInfo("trade_status result is:" + Succeed);
				}
			} else {// 验证失败
				ebuyPayRecord.setPayErrInfo("签名验证失败,verifyNotifyRes=" + verifyNotifyRes);
			}
		} catch (Exception e) {
			logger.info("SqPay payNotify cause Exception " + e.getMessage(), e);
			ebuyPayRecord.setPayErrInfo(e.getMessage());
		} finally {
			try {
				String nowTime = dualDao.getNowTime();
				ebuyPayRecord.setFlowNo(flowNo);//syl-upd-2015-12-16 10:11:59 增加支付流水号
				ebuyPayRecord.setOrderNo(BillNo);
				ebuyPayRecord.setPayAccount(null);// 暂时未获取到
				ebuyPayRecord.setPayAmount(null);//payAmount已存入到paramAll
				ebuyPayRecord.setPayDesc(null);// 支付描述，用户 xxx购买商品：aa*3;bb*4;
				ebuyPayRecord.setPayMethod(EbuyDict.EbuyPay_PayMethod.SqPay);
				ebuyPayRecord.setPayResultInfo(paramAll);
				ebuyPayRecord.setPayTime(nowTime);// 未返回
				// ebuyPayRecord.setCreateTime(nowTime);
				// ebuyPayRecord.setCreater(userId);
				boolean isSuccess = commEbuyPayRecordService.insertEbuyPayRecord(ebuyPayRecord);
				if(!isSuccess){
					logger.info("SqPay payNotify failed,resCount is " + isSuccess);
				}
			} catch (Exception e2) {
				logger.info("SqPay payNotify cause error" + e2.getMessage(), e2);
			}
			logger.info("SqPay payNotify end.");
		}
		return payNotifyRes;
	}

	@Override
	public void paySuccessOperateComm(BigInteger orderId,Integer payMethod) {
		commPayService.paySuccessOperate(orderId,payMethod);
	}

	@Override
	public OrderPayInfo getOrderPayInfoById(BigInteger userId, BigInteger orderId) {
		return commPayService.getOrderPayInfoById(userId, orderId);
	}


	@Override
	public void markOrderIsClientPay(String billNo) {
		Integer count = paymentDao.upadteOrderIsClientPayByOrderNo(billNo);
		logger.info("SqPay markOrderIsClientPay resCount is:"+count+",billNo is:"+billNo);
	}

	@Override
	public boolean qryOrderIsPay(BigInteger orderId) {
		Integer payStatus = paymentDao.selectOrderPayStatus(orderId);
		if(payStatus!=null&&EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(payStatus)==0){
			return true;
		}
		return false;
	}

}
