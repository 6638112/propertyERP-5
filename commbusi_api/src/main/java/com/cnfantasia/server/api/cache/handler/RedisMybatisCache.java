/**   
 * Filename:    RedisMybatisCache.java   
 * @version:    1.0  
 * Create at:   2015年7月31日 下午6:13:26   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2015年7月31日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.cache.handler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Filename: RedisMybatisCache.java
 * 
 * @version: 1.0.0 Create at: 2015年7月31日 下午6:13:26 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2015年7月31日 shiyl 1.0 1.0 Version
 */
public class RedisMybatisCache implements Cache {
	private static Log logger = LogFactory.getLog(RedisMybatisCache.class);
	private Jedis redisClient = createClient();
	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String id;

	public RedisMybatisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>MybatisRedisMybatisCache:id=" + id);
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public int getSize() {
		return Integer.valueOf(redisClient.dbSize().toString());
	}

	@Override
	public void putObject(Object key, Object value) {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>putObject:" + key + "=" + value);
		redisClient.set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
	}

	@Override
	public Object getObject(Object key) {
		Object value = SerializeUtil.unserialize(redisClient.get(SerializeUtil.serialize(key.toString())));
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>getObject:" + key + "=" + value);
		return value;
	}

	@Override
	public Object removeObject(Object key) {
		return redisClient.expire(SerializeUtil.serialize(key.toString()), 0);
	}

	@Override
	public void clear() {
		redisClient.flushDB();
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	protected static Jedis createClient() {
		try {
			JedisPool pool = new JedisPool(new JedisPoolConfig(), "172.60.0.172");
			return pool.getResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("初始化连接池错误");
	}

}

class SerializeUtil {
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object unserialize(byte[] bytes) {
		if (bytes == null)
			return null;
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}