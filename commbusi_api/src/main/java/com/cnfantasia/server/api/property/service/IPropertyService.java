package com.cnfantasia.server.api.property.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.property.dto.ConfirmBillInfoReq;
import com.cnfantasia.server.api.property.dto.ConfirmPayReq;
import com.cnfantasia.server.api.property.dto.ConfirmPayResultDto;
import com.cnfantasia.server.api.property.dto.PaidBillDto;
import com.cnfantasia.server.api.property.dto.PaidBillInfoReq;
import com.cnfantasia.server.api.property.dto.PayDetailDto;
import com.cnfantasia.server.api.property.dto.PayAmtDto;
import com.cnfantasia.server.api.property.dto.RemainBillDto;
import com.cnfantasia.server.api.property.dto.RemainBillInfoDto;
import com.cnfantasia.server.api.property.dto.UnPaidBillDetailReq;

/**
 * 物业缴费（version>=503）
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午11:03:53
 */
public interface IPropertyService {
	
	/**
	 * 获取待缴账单信息
	 * @return
	 */
	RemainBillInfoDto getRemainBillInfo(BigInteger userId, String sessionId, BigInteger gbId);
	
	/**
	 * 获取确认账单信息
	 * @param userId
	 * @param confirmBillInfoReqs
	 * @return
	 */
	PayAmtDto getConfirmBillInfo(BigInteger userId, List<ConfirmBillInfoReq> confirmBillInfoReqs, String sessionId);
	
	/**
	 * 确认支付
	 * @param userId
	 * @param confirmPayReqs
	 * @param hbAmount
	 * @return
	 */
	ConfirmPayResultDto confirmPay(BigInteger userId, List<ConfirmPayReq> confirmPayReqs, BigDecimal hbAmount, String sessionId);
	
	/**
	 * 获取支付详情
	 * @param orderId
	 * @return
	 */
	PayDetailDto getPayDetail(BigInteger orderId);
	
	/**
	 * 获取已缴账单信息
	 * @param paidBillInfoReq
	 * @return
	 */
	PaidBillDto getPaidBillInfo(PaidBillInfoReq paidBillInfoReq);
	
	/**
	 * 获取账单详情信息（已缴）
	 * @param orderId
	 * @param type 账单详情类型（1：物业缴费；2：车禁）
	 * @return CarPaidBillDetailDto或PropertyPaidBillDetailDto
	 */
	Object getPaidBillDetail(BigInteger orderId, String type);
	
	/**
	 * 获取账单详情信息（未缴）
	 * @param param
	 * @return CarUnPaidBillDetailDto或PropertyUnPaidBillDetailDto
	 */
	Object getUnPaidBillDetail(UnPaidBillDetailReq param);

	List<RemainBillDto> getUnpaidBillInfoList(BigInteger payBillId);

	Double getTotalAmtNow(BigInteger userId, String sessionId, BigInteger gbId);
}
