package com.cnfantasia.server.ms.revenue.task;

import com.cnfantasia.server.api.livingPay.service.LivingPayService;
import com.cnfantasia.server.common.utils.DateUtils;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

/**
 * 生活缴费 定时任务
 * @author wenfq 2017-11-15
 */
@Repository
public class LivingFeePayBillSynTask implements ISynTask{
	
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	LivingPayService livingPayService;

	@Override
	public Integer execTask() {
		int size = 0;
		logger.debug("LivingFeePayBillSynTask start:" + DateUtils.getCurrentDate());
		livingPayService.generateRevenueSignalAmount();
		logger.debug("LivingFeePayBillSynTask end:" + DateUtils.getCurrentDate() + ";size:" + size);
		return size;
	}

}
