package com.cnfantasia.server.api.access.session;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * 消息发布
 * 
 * @author liyulin
 * @version 1.0 2016年6月27日 下午3:52:07
 */
public class AccessPublishHandler implements IAccessPublishHandler {
	private static RedisTemplate<String, Object> redisTemplate = null;
	private final String channel = "access_yhs";

	public static void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		AccessPublishHandler.redisTemplate = redisTemplate;
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 */
	@Override
	public void sendMessage(Serializable message) {
		redisTemplate.convertAndSend(channel, message);
	}

}
