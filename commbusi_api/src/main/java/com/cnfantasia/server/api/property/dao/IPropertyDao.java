package com.cnfantasia.server.api.property.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.property.dto.CarPaidBillDetailDto;
import com.cnfantasia.server.api.property.dto.PaidBillItemDto;
import com.cnfantasia.server.api.property.dto.PayBillDetailDto;
import com.cnfantasia.server.api.property.dto.PropertyPaidBillDetailDto;
import com.cnfantasia.server.api.property.dto.PropertyUnPaidBillDetailDto;
import com.cnfantasia.server.api.property.dto.RemainBillDto;

/**
 * 物业缴费（version>=503）
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午11:03:53
 */
public interface IPropertyDao {

	/**
	 * 获取（支付详情）账单明细
	 * 
	 * @param orderId
	 * @return
	 */
	public List<PayBillDetailDto> selectPayBillDetails(BigInteger orderId);
	
	/**
	 * 查询已缴账单列表
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<PaidBillItemDto> selectPaidBillList(Map<String, Object> paramMap);
	
	/**
	 * 查询已缴账单数据条数
	 * 
	 * @param userId
	 * @return
	 */
	public Integer selectPaidBillCount(BigInteger userId);
	
	/**
	 * 查询车禁已缴费账单详情
	 * @param orderId
	 * @return
	 */
	public CarPaidBillDetailDto selectPaidCarBillDetail(BigInteger orderId);
	
	/**
	 * 查询物业已缴费账单详情
	 * @param payBillId
	 * @return
	 */
	public PropertyPaidBillDetailDto selectPaidPropertyBillDetail(BigInteger payBillId);
	
	/**
	 * 查询物业未缴费账单详情（解放区）
	 * @param orderId
	 * @return
	 */
	public PropertyUnPaidBillDetailDto selectUnPaidPropertyBillDetailForJfq(String payBillId);
	
	/**
	 * 获取物业宝抵扣（已缴）
	 * @param id
	 * @param orderType
	 * @return
	 */
	public Long selectDeduByOrderId(BigInteger id, int orderType);
	
	/**
	 * 更新随机立减金额
	 * @param paramMap
	 * @return
	 */
	public Integer updatePaybillUserPrefer(Map<String, Object> paramMap);

	public List<RemainBillDto> getUnpaidBillInfoList(BigInteger payBillId);
	
	/**
	 * 获取物业充值随机立减金额
	 * 
	 * @param gbbccId
	 * @param userId
	 * @param realroomId
	 * @param amount
	 * @return
	 */
	public Long selectPropertyRechargePerfer(BigInteger gbbccId, BigInteger userId, BigInteger realroomId, long amount);
}
