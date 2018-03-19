package com.cnfantasia.server.notification;

/**
 * 通知框架接口
 * @author wenfq 2016-02-25
 *
 */
public interface INotification {
	/**
	 * 产生通知
	 * @return 返回成功产生了几条
	 */
	public int addNotification();
	
	/**
	 * 发送通知
	 * @return 成功则返回true，失败返回false
	 * 
	 */
	public boolean sendNotification();
}
