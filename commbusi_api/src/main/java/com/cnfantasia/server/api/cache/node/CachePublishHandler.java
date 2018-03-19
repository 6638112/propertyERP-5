package com.cnfantasia.server.api.cache.node;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisTemplate;

public class CachePublishHandler implements ICachePublishHandler {
	
	private RedisTemplate<String, Object> redisTemplate = null;

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	/**
	 * 发送消息
	 * 
	 * @param message
	 */
	@Override
	public void sendMessage(Serializable message) {
		redisTemplate.convertAndSend(CacheConstants.Cache_Channel, message);
	}

}
