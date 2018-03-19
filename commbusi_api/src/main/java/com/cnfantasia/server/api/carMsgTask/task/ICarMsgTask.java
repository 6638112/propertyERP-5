package com.cnfantasia.server.api.carMsgTask.task;

/**
 * 车禁消息定时任务
 * 
 * @author liyulin
 * @version 1.0 2016年11月22日 下午4:59:57
 */
public interface ICarMsgTask {

	/**
	 * 推送
	 */
	public void pushTask();
	
	/**
	 * 发短信
	 */
	public void sendMsgTask();
	

    /**
     * 断线发消息
     */
    public void pushOffline();
}
