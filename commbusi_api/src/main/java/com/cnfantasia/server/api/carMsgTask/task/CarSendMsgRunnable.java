package com.cnfantasia.server.api.carMsgTask.task;

import com.cnfantasia.server.api.carMsgTask.service.ICarMsgService;

public class CarSendMsgRunnable implements Runnable{
	
	private ICarMsgService carMsgService; 
	
	public void setCarMsgService(ICarMsgService carMsgService) {
		this.carMsgService = carMsgService;
	}

	@Override
	public void run() {
		carMsgService.sendMsg();
	}

}
