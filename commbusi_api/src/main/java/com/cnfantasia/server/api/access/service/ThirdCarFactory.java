package com.cnfantasia.server.api.access.service;

import java.math.BigInteger;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.constant.AccessDict.Code;
import com.cnfantasia.server.api.access.entity.CarFeeType;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;

/**
 * 车禁
 * 
 * @author liyulin
 * @version 1.0 2017年12月11日 下午4:17:12
 */
@Repository("thirdCarFactory")
public class ThirdCarFactory implements ICarInfoService{

	public static final EnumMap<Code, ICarService> carServiceMap = new EnumMap<Code, ICarService>(Code.class);
	@Resource
	private ICarService yhsCarService;
	@Resource
	private ICarService hpfCarService;
	@Resource
	private ICarService xmfCarService;
	@Resource
	private ICarService haCarService;
	@Resource
	private ICarService ydtCarService;

	@PostConstruct
	public void initService() {
		carServiceMap.put(AccessDict.Code.yihaosheng, yhsCarService);
		carServiceMap.put(AccessDict.Code.huapengfei, hpfCarService);
		carServiceMap.put(AccessDict.Code.xiaomifeng, xmfCarService);
		carServiceMap.put(AccessDict.Code.huaan, haCarService);
		carServiceMap.put(AccessDict.Code.yidaotong, ydtCarService);
	}	
	
	public Map<Code, ICarService> getCarServiceMap() {
		return carServiceMap;
	}

	public ICarInfoService getCarService(BigInteger gbId) {
		Code code = BaseCarService.getCodeByGbId(gbId);
		return carServiceMap.get(code);
	}

	public ICarInfoService getCarService(String code) {
		return carServiceMap.get(Code.valueOf(code));
	}
	
	/**
	 * 查询月卡车信息（1个月）
	 * 
	 * @param gbId
	 * @param plateNo
	 * @return
	 */
	@Override
	public MonthCarInfo getOneMonthCarInfo(BigInteger gbId, String plateNo) {
		return getCarService(gbId).getOneMonthCarInfo(gbId, plateNo);
	}
	
	/**
	 * 获取月卡车信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @return
	 */	
	@Override
	public MonthCarInfo getMonthCarInfo(BigInteger gbId, String plateNo) {
		return getCarService(gbId).getMonthCarInfo(gbId, plateNo);
	}
	
	/**
	 * 查询月卡车支付信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @param payNum
	 * @return
	 */
	@Override
	public MonthCarInfo getMonthCarPayInfo(BigInteger gbId, String plateNo, int payNum) {
		return getCarService(gbId).getMonthCarPayInfo(gbId, plateNo, payNum);
	}


	/**
	 * 月卡代缴成功后通知第三方
	 * @param orderNo
	 * @param carNumList
	 * @param carNumPayLog
	 * @return
	 */
	@Override
	public void monthCardPayNotify(String orderNo, CarNumList carNumList, CarNumPayLog carNumPayLog) {
		getCarService(carNumList.gettGroupBuildingFId()).monthCardPayNotify(orderNo, carNumList, carNumPayLog);
	}

	/**
	 * 查询临停车信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @return
	 */
	@Override
	public TempCarInfo getTempCarInfo(BigInteger gbId, String plateNo) {
		return getCarService(gbId).getTempCarInfo(gbId, plateNo);
	}

	/**
	 * 临停车代缴成功后通知第三方
	 * @param carNum
	 * @param carNumPayLog
	 * @param orderNo
	 * @return
	 */
	@Override
	public void tempCardPayNotify(String carNum, CarNumPayLog carNumPayLog, String orderNo) {
		getCarService(carNumPayLog.gettGroupBuildingFId()).tempCardPayNotify(carNum, carNumPayLog, orderNo);
	}

	/**
	 * 可缴费月数
	 * @param gbId
	 * @param fee
	 * @param expire
	 * @param carTypeId
	 * @param carFeeTypeList
	 * @return
	 */
	@Override
	public List<CarFeeType> getCarFeeTypeList(BigInteger gbId, Long fee, String expire, String carTypeId, List<CarFeeType> carFeeTypeList) {
		return getCarService(gbId).getCarFeeTypeList(gbId, fee, expire, carTypeId, carFeeTypeList);
	}

}
