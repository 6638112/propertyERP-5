package com.cnfantasia.server.api.access.service;

import com.cnfantasia.server.api.access.entity.QryFeeRunnableParam;

/**
 * 临停车缴费车场查询线程
 * 
 * @author liyulin
 * @version 1.0 2017年10月31日 上午11:03:07
 */
public class QryFeeRunnable implements Runnable {
	private QryFeeRunnableParam qryFeeRunnableParam;

	public QryFeeRunnable(QryFeeRunnableParam qryFeeRunnableParam) {
		this.qryFeeRunnableParam = qryFeeRunnableParam;
	}

	@Override
	public void run() {
		IQryFeeService qryFeeService = qryFeeRunnableParam.getQryFeeService();
		qryFeeService.qryTempFee(qryFeeRunnableParam.getCarNum(), qryFeeRunnableParam.getThirdFeeInfoList());

		qryFeeRunnableParam.getCountDownLatch().countDown();
	}

}
