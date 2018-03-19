package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigInteger;

import com.cnfantasia.server.api.payment.entity.WeiXinLightAppNotifyEntity;

public interface IWeiXinLightAppPayService {
	/**
	 * 微信支付通知处理
	 * 
	 * @param userId
	 * @param resultEntity
	 */
	public void payNotify(WeiXinLightAppNotifyEntity resultEntity);

	/**
	 * 增加微信支付通知记录
	 * 
	 * @param currWeiXinNotifyEntity
	 * @param payErrInfo
	 * @param orderId
	 */
	public void addNotifyRecord(WeiXinLightAppNotifyEntity currWeiXinNotifyEntity, String payErrInfo, BigInteger orderId);

	/**
	 * 支付成功后的处理
	 * 
	 * @param orderId
	 */
	public void paySuccessOperateComm(BigInteger orderId,Integer payMethod);
}
