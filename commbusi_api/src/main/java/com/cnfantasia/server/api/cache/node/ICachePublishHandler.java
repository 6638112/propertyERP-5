package com.cnfantasia.server.api.cache.node;

import java.io.Serializable;

/**
 * 多节点刷缓存，消息接收
 * 
 * @author liyulin
 * @version 1.0 2017年1月9日 上午10:12:12
 */
public interface ICachePublishHandler {
	
	/**
	 * 发送消息
	 * 
	 * @param message
	 */
	public void sendMessage(Serializable message);
}
