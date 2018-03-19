package com.cnfantasia.server.api.carMsgTask.task;

/**
 * 车禁消息定时任务
 * 
 * @author liyulin
 * @version 1.0 2016年11月22日 下午4:59:57
 */
public class CarMsgTask implements ICarMsgTask {
	
	private CarSendPushRunnable carSendPushRunnable;
	private CarSendMsgRunnable carSendMsgRunnable;
	private CarSendOfflineMsgRunnable carSendOfflineMsgRunnable;

	public void setCarSendOfflineMsgRunnable(CarSendOfflineMsgRunnable carSendOfflineMsgRunnable) {
        this.carSendOfflineMsgRunnable = carSendOfflineMsgRunnable;
    }

    public void setCarSendPushRunnable(CarSendPushRunnable carSendPushRunnable) {
		this.carSendPushRunnable = carSendPushRunnable;
	}

	public void setCarSendMsgRunnable(CarSendMsgRunnable carSendMsgRunnable) {
		this.carSendMsgRunnable = carSendMsgRunnable;
	}

	/**
	 * 推送
	 */
	@Override
	public void pushTask() {
		new Thread(carSendPushRunnable).start();
	}

	/**
	 * 发短信
	 */
	@Override
	public void sendMsgTask() {
		new Thread(carSendMsgRunnable).start();
	}

    @Override
	public void pushOffline(){
        new Thread(carSendOfflineMsgRunnable).start();
	}

}
