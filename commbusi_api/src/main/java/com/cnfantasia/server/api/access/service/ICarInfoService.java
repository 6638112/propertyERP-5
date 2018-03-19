package com.cnfantasia.server.api.access.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.access.entity.CarFeeType;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;

/**
 * 第三方车禁
 * 
 * @author liyulin
 * @version 1.0 2017年12月7日 上午9:49:08
 */
public interface ICarInfoService {
	
	/**
	 * 查询月卡车信息（1个月）
	 * 
	 * @param gbId
	 * @param plateNo
	 * @return
	 */
	public MonthCarInfo getOneMonthCarInfo(BigInteger gbId, String plateNo);

	/**
	 * 获取月卡车信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @return
	 */
	public MonthCarInfo getMonthCarInfo(BigInteger gbId, String plateNo);

	/**
	 * 查询月卡车支付信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @param payNum
	 * @return
	 */
	public MonthCarInfo getMonthCarPayInfo(BigInteger gbId, String plateNo, int payNum);

	/**
	 * 月卡代缴成功后通知第三方
	 * @param orderNo
	 * @param carNumList
	 * @param carNumPayLog
	 * @return
	 */
	public void monthCardPayNotify(String orderNo, CarNumList carNumList, CarNumPayLog carNumPayLog);

	/**
	 * 查询临停车信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @return
	 */
	public TempCarInfo getTempCarInfo(BigInteger gbId, String plateNo);

	/**
	 * 临停车代缴成功后通知第三方
	 * @param carNum
	 * @param carNumPayLog
	 * @param orderNo
	 * @return
	 */
	public void tempCardPayNotify(String carNum, CarNumPayLog carNumPayLog, String orderNo);
	
	/**
	 * 可缴费月数
	 * 
	 * @param gbId
	 * @param fee
	 * @param expire
	 * @param carTypeId
	 * @param carFeeTypeList
	 * @return
	 */
	public List<CarFeeType> getCarFeeTypeList(BigInteger gbId, Long fee, String expire, String carTypeId,
			List<CarFeeType> carFeeTypeList);
}
