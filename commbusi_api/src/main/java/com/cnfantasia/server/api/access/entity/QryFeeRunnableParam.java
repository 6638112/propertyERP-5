package com.cnfantasia.server.api.access.entity;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.cnfantasia.server.api.access.service.IQryFeeService;

public class QryFeeRunnableParam {
	private IQryFeeService qryFeeService;
	private String carNum;
	private List<ThirdFeeInfoEntity> thirdFeeInfoList;
	private CountDownLatch countDownLatch;

	public QryFeeRunnableParam() {
		super();
	}

	public QryFeeRunnableParam(IQryFeeService qryFeeService, String carNum, List<ThirdFeeInfoEntity> thirdFeeInfoList,
			CountDownLatch countDownLatch) {
		super();
		this.qryFeeService = qryFeeService;
		this.carNum = carNum;
		this.thirdFeeInfoList = thirdFeeInfoList;
		this.countDownLatch = countDownLatch;
	}

	public IQryFeeService getQryFeeService() {
		return qryFeeService;
	}

	public void setQryFeeService(IQryFeeService qryFeeService) {
		this.qryFeeService = qryFeeService;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public List<ThirdFeeInfoEntity> getThirdFeeInfoList() {
		return thirdFeeInfoList;
	}

	public void setThirdFeeInfoList(List<ThirdFeeInfoEntity> thirdFeeInfoList) {
		this.thirdFeeInfoList = thirdFeeInfoList;
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

}
