package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.server.api.access.service.ThirdCarFactory;
import com.cnfantasia.server.domainbase.carNumList.dao.ICarNumListBaseDao;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.dao.ICarNumPayLogBaseDao;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;

/**
 * 第三方车禁支付成功通知
 * 
 * @author liyulin
 * @version 1.0 2017年6月17日 下午3:02:29
 */
public class ThirdCarPayNotify {
	@Resource
	private ICarNumPayLogBaseDao carNumPayLogBaseDao;
	@Resource
	private ICarNumListBaseDao carNumListBaseDao;
	@Resource
	private ThirdCarFactory thirdCarFactory;

	public void dealNotify(BigInteger orderId, String orderNo, String payTime) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tEbuyOrderId", orderId);
		List<CarNumPayLog> carNumPayLogs = carNumPayLogBaseDao.selectCarNumPayLogByCondition(paramMap, false);
		if (carNumPayLogs != null && carNumPayLogs.size() > 0) {
			CarNumPayLog carNumPayLog = carNumPayLogs.get(0);
			CarNumList carNumList = carNumListBaseDao.selectCarNumListBySeqId(carNumPayLog.gettCarNumId());
			if (carNumPayLog.gettGroupBuildingFId() != null) {// 临停车缴费的gbId不为空
				thirdCarFactory.tempCardPayNotify(carNumList.gettCarNum(), carNumPayLog, orderNo);
			} else {// 月卡
				thirdCarFactory.monthCardPayNotify(orderNo, carNumList, carNumPayLog);
			}
		}
	}

}
