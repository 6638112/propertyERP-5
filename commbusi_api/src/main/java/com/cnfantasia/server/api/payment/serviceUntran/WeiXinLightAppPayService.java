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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.entity.WeiXinLightAppNotifyEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;
import com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.dao.IEbuyPrepayWeixinLogBaseDao;

/**
 * 微信轻应用支付Service
 * 
 * @author wenfq 2015-03-11
 */
public class WeiXinLightAppPayService implements IWeiXinLightAppPayService {
	private Log logger = LogFactory.getLog(getClass());

	public IEbuyPrepayWeixinLogBaseDao ebuyPrepayWeixinLogBaseDao;

	public void setEbuyPrepayWeixinLogBaseDao(IEbuyPrepayWeixinLogBaseDao ebuyPrepayWeixinLogBaseDao) {
		this.ebuyPrepayWeixinLogBaseDao = ebuyPrepayWeixinLogBaseDao;
	}

	protected IUuidManager uuidManager;

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	protected ICommonEbuyService commonEbuyService;

	public void setCommonEbuyService(ICommonEbuyService commonEbuyService) {
		this.commonEbuyService = commonEbuyService;
	}

	protected IDualDao dualDao;

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	private ICommPayService commPayService;
	public void setCommPayService(ICommPayService commPayService) {
		this.commPayService = commPayService;
	}

	@Override
	public void paySuccessOperateComm(BigInteger orderId,Integer payMethod) {
		commPayService.paySuccessOperate(orderId,payMethod);
	}
	
	private CommEbuyPayRecordService commEbuyPayRecordService;
	public void setCommEbuyPayRecordService(
			CommEbuyPayRecordService commEbuyPayRecordService) {
		this.commEbuyPayRecordService = commEbuyPayRecordService;
	}
	
	@Override
	public synchronized void payNotify(WeiXinLightAppNotifyEntity resultEntity) {
		logger.info("WeiXinPayService payNotify start,resultEntity is:" + JSON.toJSONString(resultEntity));
		String orderNo = resultEntity.getOut_trade_no();
		// 查询数据库当前的订单情况
		EbuyOrder ebuyOrder = commonEbuyService.selectEbuyOrderByOrderNo(orderNo);

		// 处理重复通知的情况
		if (ebuyOrder.getPayStatus() != null// &&ebuyOrder.getStatus()>=DictConstants.EbuyOrder_Status.DaiFaHuo
				&& EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(ebuyOrder.getPayStatus()) == 0) {// 如果订单状态为支付成功，则不作处理，直接返回通知支付成功
			logger.info("Repeat notify,the order info has been updated .");// TODO
			//重复通知了,记录日志
			return;
		}

		{
			EbuyPayRecord ebuyPayRecord = null;
			// 生成成功支付记录
			String nowTime = dualDao.getNowTime();
			// String nowTime = dualDao.getNowTime();
			ebuyPayRecord = new EbuyPayRecord();
			ebuyPayRecord.setFlowNo(resultEntity.getTransaction_id());//
			ebuyPayRecord.setOrderNo(resultEntity.getOut_trade_no());
			ebuyPayRecord.setPayAccount(null);// TODO 暂时未获取到
			ebuyPayRecord.setPayAmount(Long.parseLong(resultEntity.getTotal_fee()));
			ebuyPayRecord.setPayDesc(null);// TODO 支付描述，用户 xxx购买商品：aa*3;bb*4;
			ebuyPayRecord.setPayMethod(EbuyDict.EbuyPay_PayMethod.WeiXin_Light);
			ebuyPayRecord.setPayResultInfo(JSON.toJSONString(resultEntity));
			ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Success);
			//			ebuyPayRecord.setPayTime(resultEntity.getTime_end());
			ebuyPayRecord.setPayTime(nowTime);
			ebuyPayRecord.settEbuyOrderFId(ebuyOrder.getId());
			// ebuyPayRecord.setCreateTime(nowTime);
			//ebuyPayRecord.setCreater(userId);
			boolean isSuccess = commEbuyPayRecordService.insertEbuyPayRecord(ebuyPayRecord);
			if (!isSuccess) {
				throw new BusinessRuntimeException("CommonEbuyService.paySuccessOperateComm.insertEbuyPayRecord.failed");
			}
		}
		paySuccessOperateComm(ebuyOrder.getId(),EbuyDict.EbuyPay_PayMethod.WeiXin);
		// 注意交易单不要重复处理
		logger.info("WeiXinPayService payNotify end.");
	}

	@Override
	public void addNotifyRecord(WeiXinLightAppNotifyEntity currWeiXinNotifyEntity, String payErrInfo, BigInteger orderId) {
		// 增加支付记录
		String nowTime = dualDao.getNowTime();
		BigInteger ebuyPayRecordAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_pay_record);
		EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
		ebuyPayRecord.setId(ebuyPayRecordAddId);
		ebuyPayRecord.setFlowNo(currWeiXinNotifyEntity.getTransaction_id());//
		ebuyPayRecord.setOrderNo(currWeiXinNotifyEntity.getOut_trade_no());
		ebuyPayRecord.setPayAccount(null);// TODO 暂时未获取到
		ebuyPayRecord.setPayAmount(Long.parseLong(currWeiXinNotifyEntity.getTotal_fee()));
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
