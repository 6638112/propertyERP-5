/**
 * Filename:    RedisCacheHandler.java
 *
 * @version: 1.0
 * Create at:   2015年7月8日 上午7:05:38
 * Description:
 * <p>
 * Modification History:
 * Date        Author      Version     Description
 * -----------------------------------------------------------------
 * 2015年7月8日    shiyl      1.0         1.0 Version
 */
package com.cnfantasia.server.api.cache.handler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.cnfantasia.server.api.cache.ICacheReload;
import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    RedisCacheHandler.java
 * @version: 1.0.0
 * Create at:   2015年7月8日 上午7:05:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月8日       shiyl             1.0             1.0 Version
 */
public class RedisCacheHandler {
    private static Log logger = LogFactory.getLog(RedisCacheHandler.class);

    private static JedisPool pool;
    private static List<ICacheReload> reloadList;

    public RedisCacheHandler() {
    }

    public RedisCacheHandler(JedisPool pool, List<ICacheReload> reloadList) {
        RedisCacheHandler.pool = pool;
        RedisCacheHandler.reloadList = reloadList;
    }

    //同步使用锁 同步数据类型
    /**是否已加载数据:0未加载，1加载中，2加载完成*/
    private static int NotStart = 0;
    private static int LoadIng = 1;
    private static int StartOk = 2;
    private static AtomicInteger haveReloadStatus = new AtomicInteger(NotStart);

    /**加载数据实现*/
    private static void loadAllData() {
        if (reloadList != null && reloadList.size() > 0) {
            for (ICacheReload cacheReload : reloadList) {
                cacheReload.doReLoadAllData(true);
            }
        }
    }

    /**初始加载NotStart->LoadIng->StartOk*/
    public static void initAllData() {
        if (haveReloadStatus.compareAndSet(NotStart, LoadIng)) {
            try {
                loadAllData();
            } finally {
                haveReloadStatus.set(StartOk);
            }
        }
    }

    /**重新加载数据StartOk->LoadIng->StartOk */
    public static void reLoadAllData() {
        if (haveReloadStatus.compareAndSet(StartOk, LoadIng)) {
            try {
                loadAllData();
            } finally {
                haveReloadStatus.set(StartOk);
            }
        }
    }

    public static synchronized void preCheck() {//考虑AOP判断
//		if(haveReloadStatus.get()!=StartOk){//不是加载完成的
//			throw new BusinessRuntimeException("Init date is not loaded....haveReloadStatus is:"+haveReloadStatus);
//		}
    }

    public static void hset(final String codeName, final String field, final String value) {
        preCheck();
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            jedis.hset(codeName, field, value);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static void hmset(final String codeName, final Map<String, String> hash) {
        preCheck();
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            jedis.hmset(codeName, hash);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static void hmset(final String codeName, final Map<String, String> hash, final int epSeconds) {
        preCheck();
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            jedis.hmset(codeName, hash);
            jedis.expire(codeName, epSeconds);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static void hdel(final String codeName, final String field) {
        preCheck();
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            jedis.hdel(codeName, field);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static void hdel(final String codeName, final String[] field) {
        preCheck();
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            jedis.hdel(codeName, field);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }


    /**
     * 新增
     * @param codeName
     * @param count
     * @return
     */
    public static Long hincrBy(final String codeName, final String field, final Long count) {
        preCheck();
        Jedis jedis = null;
        try {
            //从池中获取一个Jedis对象
            jedis = pool.getResource();
            return jedis.hincrBy(codeName, field, count);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static List<String> hmget(String codeName, String[] fieldList) {
        preCheck();
        Jedis jedis = null;
        List<String> resList = null;
        try {
            //从池中获取一个Jedis对象
            jedis = pool.getResource();
            resList = jedis.hmget(codeName, fieldList);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
        return resList;
    }

    public static BigInteger hgetBigInteger(String codeName, String field) {
        String resStr = hget(codeName, field);
        if (!StringUtils.isEmpty(resStr)) {
            return new BigInteger(resStr);
        }
        return null;
    }

    public static List<BigInteger> hmgetBigInteger(String codeName, String[] fieldList) {
        preCheck();
        List<BigInteger> resList = null;
        List<String> strList = hmget(codeName, fieldList);
        if (strList != null && strList.size() > 0) {
            resList = new ArrayList<BigInteger>();
            for (String tmpStr : strList) {
                if (tmpStr != null) {
                    resList.add(new BigInteger(tmpStr));
                }
            }
        }
        return resList;
    }

    public static String hget(String codeName, String field) {
        preCheck();
        Jedis jedis = null;
        String result = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            result = jedis.hget(codeName, field);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
        return result;
    }

    public static Set<String> hkeys(String codeName) {
        Jedis jedis = null;
        Set<String> result = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            result = jedis.hkeys(codeName);
            return result;
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    /**
     * 获取所有的values
     * @param codeName
     * @return
     */
    public static List<String> hvals(final String codeName) {
        preCheck();
        Jedis jedis = null;
        List<String> resList = null;
        try {
            //从池中获取一个Jedis对象
            jedis = pool.getResource();
            resList = jedis.hvals(codeName);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
        return resList;
    }

    public static String get(final String key) {
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static void set(final String key, final String value) {
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            jedis.set(key, value);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static void del(final String key) {
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            jedis.del(key);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static void set(final String key, final String value, final int epSeconds) {
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            jedis.set(key, value);
            jedis.expire(key, epSeconds);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    /**
     * 存储REDIS队列 顺序存储
     *
     * @param key
     * @param values
     */
    public static void lpush(String key, String... values) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.lpush(key, values);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    /**
     * 存储REDIS队列 存至last
     *
     * @param key
     * @param values
     */
    public static void rpush(String key, String... values) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.rpush(key, values);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static String lpop(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            value = jedis.lpop(key);
        } finally {
            if (jedis != null) {
                pool.returnResourceObject(jedis);
            }
        }
        return value;
    }

    public static String rpop(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            value = jedis.rpop(key);
        } finally {
            if (jedis != null) {
                pool.returnResourceObject(jedis);
            }
        }
        return value;
    }

    /**
     * 获取list中的所有value
     *
     * @param key
     * @return
     */
    public static List<String> lpopAll(String key) {
        List<String> values = new ArrayList<String>();

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            long len = jedis.llen(key);
            for (long i = 0; i < len; i++) {
                values.add(jedis.lpop(key));
            }
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }

        return values;
    }

    /**
     * jedis计数
     *
     * @param key
     */
    public static void count(String key) {
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            String count = jedis.get(key);
            if (count == null) {
                jedis.set(key, "1");
            } else {
                jedis.set(key, String.valueOf(Integer.parseInt(count) + 1));
            }
        } finally {
            if (jedis != null) {// 释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static boolean exsits(final String key) {
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            return jedis.exists(key);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    // 暂时禁用
    public static void clearCache() {
        logger.debug("-----------clearCache---------");
//		//持久化数据到文件，保证数据不丢失，下次启动时可重新加载
//		Jedis jedis = null;
//		try {
//			// 从池中获取一个Jedis对象  
//			jedis = pool.getResource();
//			jedis.flushDB();
//		}finally{
//			if(jedis!=null){//释放资源
//				pool.returnResourceObject(jedis);
//			}
//		}
    }

    public static void clearCache(String codeName) {
        preCheck();
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            jedis.del(codeName);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static void clearCachePerfixName(String codeNamePerfix, String dymStr) {
        preCheck();
        //校验
        if (CacheConstant.allowBatchDelByPerfix(codeNamePerfix)) {
            Jedis jedis = null;
            try {
                // 从池中获取一个Jedis对象
                jedis = pool.getResource();
                String pattern = codeNamePerfix + dymStr + "*";
                Set<String> matchKeys = jedis.keys(pattern);
                if (matchKeys != null && matchKeys.size() > 0) {
                    String[] delKeys = new String[matchKeys.size()];
                    matchKeys.toArray(delKeys);
                    jedis.del(delKeys);
                }
            } finally {
                if (jedis != null) {//释放资源
                    pool.returnResourceObject(jedis);
                }
            }
        } else {
            throw new BusinessRuntimeException("Batch clear is not allowd.");
        }
    }

    public static void clearCachePerfixName(String codeNamePerfix) {
        preCheck();
        //校验
        if (CacheConstant.allowBatchDelByPerfix(codeNamePerfix)) {
            Jedis jedis = null;
            try {
                // 从池中获取一个Jedis对象
                jedis = pool.getResource();
                String pattern = codeNamePerfix + "*";
                Set<String> matchKeys = jedis.keys(pattern);
                if (matchKeys != null && matchKeys.size() > 0) {
                    String[] delKeys = new String[matchKeys.size()];
                    matchKeys.toArray(delKeys);
                    jedis.del(delKeys);
                }
            } finally {
                if (jedis != null) {//释放资源
                    pool.returnResourceObject(jedis);
                }
            }
        } else {
            throw new BusinessRuntimeException("Batch clear is not allowd.");
        }
    }

    public static Set<String> keys(String codeNamePerfix) {
        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            return jedis.keys(codeNamePerfix + "*");
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static Long sadd(final String key, final String... members) {

        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            return jedis.sadd(key, members);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static Long srem(final String key, final String... members) {

        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            return jedis.srem(key, members);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    public static Set<String> sinter(final String... keys) {

        Jedis jedis = null;
        try {
            // 从池中获取一个Jedis对象
            jedis = pool.getResource();
            return jedis.sinter(keys);
        } finally {
            if (jedis != null) {//释放资源
                pool.returnResourceObject(jedis);
            }
        }
    }

    /**
     * 分布式锁
     *
     * @author liyulin
     * @version 1.0 2017年7月25日 上午10:15:41
     */
    public static class Lock {

        /**
         * 获取锁
         *
         * @param lock
         * @param expireSeconds
         * @return
         * 	   true：获取成功<br>
         * 	   false：获取失败
         */
        public static boolean acquireLock(String lock, int expireSeconds) {
            preCheck();
            Jedis jedis = null;
            boolean acquiredSuccess = false;
            try {
                // 从池中获取一个Jedis对象
                jedis = pool.getResource();
                // 通过SETNX试图获取一个lock
                String value = "1";
                long acquired = jedis.setnx(lock, value);
                acquiredSuccess = (acquired == 1);
                if(acquiredSuccess) {
                	jedis.expire(lock, expireSeconds);
                }
            } finally {
                if (jedis != null) {//释放资源
                    pool.returnResourceObject(jedis);
                }
            }
            return acquiredSuccess;
        }

        /**
         * 释放锁  
         * @param lock
         */
        public static void releaseLock(String lock) {
            del(lock);
        }
    }

}
