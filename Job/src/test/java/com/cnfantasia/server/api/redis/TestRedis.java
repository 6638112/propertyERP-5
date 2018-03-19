/**   
 * Filename:    TestRedis.java   
 * @version:    1.0  
 * Create at:   2015年7月8日 上午2:05:39   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2015年7月8日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.redis;

import com.cnfantasia.server.api.BaseTest;

/**
 * Filename: TestRedis.java
 * 
 * @version: 1.0.0 Create at: 2015年7月8日 上午2:05:39 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2015年7月8日 shiyl 1.0 1.0 Version
 */
public class TestRedis extends BaseTest{
	
//	@Test
//	public void test(){
//		JedisPool jedisPool = ctx.getBean("jedisPool", JedisPool.class);
//		ShardedJedisPool shardedJedisPool = ctx.getBean("shardedJedisPool", ShardedJedisPool.class);
//		// 从池中获取一个Jedis对象  
//		Jedis jedis = jedisPool.getResource();
//		ShardedJedis shardedJedis = shardedJedisPool.getResource();
//		hset(shardedJedis, "hset_sa_idcode", new BigInteger("101"), "-1.19.233.3243.0.1");
//		hset(shardedJedis, "hset_sa_idcode", new BigInteger("102"), "-1.19.233.3243.0.0");
//		hset(shardedJedis, "hset_sa_idcode", new BigInteger("103"), "-1.19.0.0.0.0");
//		
//		
//	}
//	public static void hset(BinaryJedisCommands command,String key,BigInteger field,String value){
//		command.hset(SerializingUtil.serialize(key), SerializingUtil.serialize(field), SerializingUtil.serialize(value));
//	}
	
}
