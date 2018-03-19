package com.cnfantasia.server.api.access.session;

import java.io.Serializable;

/**
 * 消息订阅
 * 
 * @author liyulin
 * @version 1.0 2016年6月27日 下午3:52:17
 */
public interface IAccessSubscribeHandler {
	
	/**
	 * 消息处理
	 * 
	 * @param message
	 */
	public void handleMessage(Serializable message);
}
