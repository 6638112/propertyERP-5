package com.cnfantasia.server.api.access.session;

import java.io.Serializable;

/**
 * 消息发布
 * 
 * @author liyulin
 * @version 1.0 2016年6月27日 下午3:52:07
 */
public interface IAccessPublishHandler {
	
	/**
	 * 发送消息
	 * 
	 * @param message
	 */
	public void sendMessage(Serializable message);
}
