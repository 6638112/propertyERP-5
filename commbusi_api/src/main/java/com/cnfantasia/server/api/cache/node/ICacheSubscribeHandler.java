package com.cnfantasia.server.api.cache.node;

import java.io.Serializable;

/**
 * 多节点刷缓存，消息处理
 * 
 * @author liyulin
 * @version 1.0 2017年1月9日 上午10:12:36
 */
public interface ICacheSubscribeHandler {
	
	/**
	 * 消息处理
	 * 
	 * @param message
	 */
	public void handleMessage(Serializable message);
}
