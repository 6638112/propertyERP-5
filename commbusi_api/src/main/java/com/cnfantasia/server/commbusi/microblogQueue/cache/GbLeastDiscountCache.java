package com.cnfantasia.server.commbusi.microblogQueue.cache;

import java.math.BigInteger;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GBLeastDiscount;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GBWithPropMonthYear;
import com.cnfantasia.server.commbusi.microblogQueue.serviceUn.IGbLeastDiscountService;
import com.cnfantasia.server.commbusi.microblogQueue.util.DefaultGbUtil;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * 小区最低折扣缓存
 * @author shiyl
 *
 */
public class GbLeastDiscountCache {
	public static void checkAndUpdate(BigInteger userId,BigInteger gbId,Double discount,BigInteger prizeRecId,BigInteger userHasRoomFId){//TODO 使用线程池 队列缓存
		if(DefaultGbUtil.checkIsDefaultGb(gbId)){return;}
		if(gbId==null||prizeRecId==null||discount==null||discount>5){return;}
		synchronized (gbId) {
			IGbLeastDiscountService gbLeastDiscountService = ApplicationContextBothUtil.getGbLeastDiscountService();
			GBWithPropMonthYear gbInfo = gbLeastDiscountService.getGbPropertyInfoByGbId(gbId);
			//根据小区Id获取当前折扣月份
			String prizeYearMonth = gbInfo.fetchPropYearMonthStr();
			//拼接key
			String dymAppendKey = gbId+"";
			String currKey = getGbLeastDiscountModelKey(dymAppendKey,prizeYearMonth);
			//判断是否已存在数据
			if(!RedisCacheHandler.exsits(currKey)){//若无，则清空历史缓存数据,同时从db加载最低折扣信息
				RedisCacheHandler.clearCachePerfixName(getGbLeastDiscountPerfix(),dymAppendKey);
				GBLeastDiscount dbGBLeastDiscount = gbLeastDiscountService.getLeastDiscountByGbId(gbId, gbInfo.getPropPayDateBegin(), gbInfo.getPropPayDateEnd());
				RedisCacheHandler.set(currKey, JSON.toJSONString(dbGBLeastDiscount));
			}
			String cacheJson = RedisCacheHandler.get(currKey);
			if(!StringUtils.isEmpty(cacheJson)){
				GBLeastDiscount cacheData = JSON.parseObject(cacheJson,GBLeastDiscount.class);
				Double cacheDiscount = cacheData.getDiscount();
				BigInteger discountId = cacheData.getDiscountId();
				//比较当前折扣是否为最低折扣
				if((cacheDiscount!=null&&discount.compareTo(cacheDiscount)<0)
						||(discountId!=null&&discountId.compareTo(prizeRecId)==0)){
					if((cacheDiscount!=null&&discount.compareTo(cacheDiscount)<0)){//若是，则更新缓存数据
						cacheData.setDiscount(discount);
						cacheData.setUserId(userId);
						cacheData.setDiscountId(prizeRecId);
						RedisCacheHandler.set(currKey, JSON.toJSONString(cacheData));
					}
					//取出缓存数据，增加一条推送消息
					ApplicationContextBothUtil.getMicroblogQueueService().groupBuildLeastDiscount(userId, cacheData.getGbId(), cacheData.getGbName(), discount,userHasRoomFId);
				}
			}
		}
	
	}
	
	private static String getGbLeastDiscountModelKey(String dymAppendKey,String time) {
		return getGbLeastDiscountPerfix()+dymAppendKey+"_"+time;
	}
	private static String getGbLeastDiscountPerfix(){
		return CacheConstant.ModelCode.set_gb_least_discount;
	}
}
