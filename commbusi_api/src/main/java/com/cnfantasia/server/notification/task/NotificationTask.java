package com.cnfantasia.server.notification.task;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.cnfantasia.server.notification.INotification;

/**
 * 通知自动任务超类
 * @author wenfq 2016-02-25
 *
 */
public abstract class NotificationTask {
	private INotification iNotification;
	
	abstract public boolean sendNotification() throws ClientProtocolException, IOException;

	public INotification getiNotification() {
		return iNotification;
	}

	public void setiNotification(INotification iNotification)  {
		this.iNotification = iNotification;
	}
}
