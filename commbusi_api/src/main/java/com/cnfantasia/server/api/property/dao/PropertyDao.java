package com.cnfantasia.server.api.property.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.property.dto.CarPaidBillDetailDto;
import com.cnfantasia.server.api.property.dto.PaidBillItemDto;
import com.cnfantasia.server.api.property.dto.PayBillDetailDto;
import com.cnfantasia.server.api.property.dto.PropertyPaidBillDetailDto;
import com.cnfantasia.server.api.property.dto.PropertyUnPaidBillDetailDto;
import com.cnfantasia.server.api.property.dto.RemainBillDto;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

/**
 * 物业缴费（version>=503）
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午11:03:53
 */
public class PropertyDao extends AbstractBaseDao implements IPropertyDao {

	/**
	 * 获取（支付详情）账单明细
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public List<PayBillDetailDto> selectPayBillDetails(BigInteger orderId) {
		return sqlSession.selectList("property.select_payBill_details", orderId);
	}
	
	/**
	 * 查询已缴账单信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<PaidBillItemDto> selectPaidBillList(Map<String, Object> paramMap){
		return sqlSession.selectList("property.select_paidBill_list", paramMap);
	}
	
	/**
	 * 查询已缴账单数据条数
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public Integer selectPaidBillCount(BigInteger userId){
		return sqlSession.selectOne("property.select_paidBill_count", userId);
	}
	
	/**
	 * 查询车禁已缴费账单详情
	 * @param orderId
	 * @return
	 */
	@Override
	public CarPaidBillDetailDto selectPaidCarBillDetail(BigInteger orderId){
		return sqlSession.selectOne("property.select_paidCarBill_detail", orderId);
	}
	
	/**
	 * 查询物业已缴费账单详情
	 * @param payBillId
	 * @return
	 */
	@Override
	public PropertyPaidBillDetailDto selectPaidPropertyBillDetail(BigInteger payBillId){
		return sqlSession.selectOne("property.select_paidPropertyBill_detail", payBillId);
	}
	
	/**
	 * 查询物业未缴费账单详情（解放区）
	 * @param orderId
	 * @return
	 */
	@Override
	public PropertyUnPaidBillDetailDto selectUnPaidPropertyBillDetailForJfq(String payBillId){
		return sqlSession.selectOne("property.select_unPaidPropertyBill_detail_jfq", payBillId);
	}
	
	/**
	 * 获取物业宝抵扣（已缴）
	 * @param id
	 * @param orderType
	 * @return
	 */
	@Override
	public Long selectDeduByOrderId(BigInteger id, int orderType){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("orderType", orderType);
		
		return sqlSession.selectOne("property.select_deduByOrderId", paramMap);
	}
	
	/**
	 * 更新随机立减金额
	 * @param paramMap
	 * @return
	 */
	@Override
	public Integer updatePaybillUserPrefer(Map<String, Object> paramMap){
		return sqlSession.selectOne("property.update_paybillUserPrefer", paramMap);
	}

	@Override
	public List<RemainBillDto> getUnpaidBillInfoList(BigInteger payBillId) {
		return sqlSession.selectList("property.getUnpaidBillInfoList", payBillId);
	}

	/**
	 * 获取物业充值随机立减金额
	 * 
	 * @param gbbccId
	 * @param userId
	 * @param realroomId
	 * @param amount
	 * @return
	 */
	@Override
	public Long selectPropertyRechargePerfer(BigInteger gbbccId, BigInteger userId, BigInteger realroomId,
			long amount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbbccId", gbbccId);
		paramMap.put("userId", userId);
		paramMap.put("realroomId", realroomId);
		paramMap.put("amount", amount);

		return sqlSession.selectOne("property.select_property_recharge_perfer", paramMap);
	}
	
}
