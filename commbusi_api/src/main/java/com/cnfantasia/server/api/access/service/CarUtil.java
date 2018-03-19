package com.cnfantasia.server.api.access.service;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.constant.HpfCarDict;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;

public final class CarUtil {

	/**
	 * 根据已经发送的次数，获取下次发送的时间
	 * 
	 * @param times
	 *            下次发送的次数
	 * @return
	 */
	public static String getSendTime(Integer times) {
		int mins = 0;// 分钟
		if (null == times || times == 0) {
			mins = 0;
		} else if (times == 1) {
			mins = 1;
		} else if (times == 2) {
			mins = 3;
		} else if (times == 3) {
			mins = 5;
		} else if (times == 4) {
			mins = 10;
		} else if (times == 5) {
			mins = 20;
		} else if (times == 6) {
			mins = 60;
		} else if (times == 7) {
			mins = 60 * 3;
		} else if (times == 8) {
			mins = 60 * 12;
		} else if (times == 9) {
			mins = 60 * 24;
		} else if (times >= 10) {
			mins = 60 * 48;
		}

		return DateTime.now().plusMinutes(mins).toString("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 从cache（redis）中获取结果
	 * @param key
	 * @param waitTime
	 * @return
	 */
	public static String getResultFromCache(String key, String command, Integer waitTime){
    	long begin = System.currentTimeMillis();
    	long end = System.currentTimeMillis();
    	String v = RedisCacheHandler.get(key);
		while ((v==null) && ((end - begin) < waitTime)) {
    		try {
				Thread.sleep(AccessDict.WHILE_WAITING_TIME);
			} catch (InterruptedException e) {
				//logger.error("InterruptedException", e);
			}
    		end = System.currentTimeMillis();
    		v = RedisCacheHandler.get(key);
    	}
		
		if(HpfCarDict.Command.REMOTE_RESPONSE_TEMP_CAR_PAY.equals(command)){// 临停车查询结果，从缓存取后删除，其它操作的不删除
			if(StringUtils.isNotBlank(v)){// 取后必须删除；否则下次取值可能有问题
				v = new String(v);// 处理RedisCacheHandler.del(key)后值为null
				RedisCacheHandler.del(key);
			}
		}
		
		return v;
	}
	
	public static final String getLockValue(String code, String key) {
		return code + key;
	}
	
}
