package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.payment.entity.OrderPayInfo;
import com.cnfantasia.server.api.payment.entity.SqPayBtResponse;
import com.cnfantasia.server.common.json.JsonResponse;

/**
 * 双乾支付补贴优惠
 * 
 * @author liyulin
 * @version 1.0 2016年9月20日 上午9:33:22
 */
public interface ISqpayBtService {
	
	/**
	 * 预支付请求处理
	 * 
	 * @param orderId
	 * @param merNo
	 * @param request
	 * @return
	 */
	public JsonResponse prePayRequestHandler(BigInteger orderId, String merNo, HttpServletRequest request);
	
	/**
	 * 支付回调
	 * 
	 * @param sqPayBtRequest
	 * @return
	 */
	public boolean payNotify(SqPayBtResponse sqPayBtRequest);
	
	/**
	 * 支付回调
	 * 
	 * @param sqPayBtRequest
	 * @return
	 */
	public boolean payNotifyTest(SqPayBtResponse sqPayBtRequest);

	/**
	 * 支付成功
	 * 
	 * @param orderId
	 * @param payMethod
	 */
	public void paySuccessOperateComm(BigInteger orderId, Integer payMethod);

	/**
	 * 根据Id查询订单信息
	 * 
	 * @param userId
	 * @param orderId
	 * @return
	 */
	public OrderPayInfo getOrderPayInfoById(BigInteger userId, BigInteger orderId);
	
	/**
	 * 对于商户端发起报文，需要使用自己的私钥进行签名和邻花钱的公钥进行加密
	 * 
	 * @param sqPayBtPrePayDto
	 * @return
	 */
	/*public String signAndEncrypt(SqPayBtPrePayDto sqPayBtPrePayDto);*/
	
	/**
	 * 签名校验
	 * 
	 * @param sqPayBtRequest
	 * @return
	 */
	public boolean sign(SqPayBtResponse sqPayBtRequest);
}
