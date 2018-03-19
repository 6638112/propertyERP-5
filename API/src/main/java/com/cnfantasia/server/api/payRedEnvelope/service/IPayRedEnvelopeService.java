package com.cnfantasia.server.api.payRedEnvelope.service;

import java.math.BigInteger;

import com.cnfantasia.server.common.json.JsonResponse;

/**
 * 代扣卡转粮票
 * 
 * @author liyulin
 * @version 1.0 2016年11月4日 下午1:30:46
 */
public interface IPayRedEnvelopeService {
	/**
	 * 代扣卡转粮票
	 * 
	 * @param userId
	 * @return
	 */
	JsonResponse propertyCard2Envelope(BigInteger userId);
}
