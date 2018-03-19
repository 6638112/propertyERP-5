package com.cnfantasia.server.api.ebuyorder.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;



import java.util.Map;

import org.apache.commons.httpclient.HttpException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.ebuyorder.entity.RechargePackage;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.HttpUtil;

public class RechargePackageUtils {
	
//	public static Map<String, RechargePackage> getRechargePackageList2(String jsonStr) {
//		Map<String, RechargePackage> rechargePackageMap = new HashMap<String, RechargePackage>();
//		
//		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
//		JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("data");
//		
//		List<RechargePackage> rechargePackageList = JSONArray.parseArray(jsonArray.toJSONString(), RechargePackage.class);
//		
//		for(RechargePackage rechargePackage : rechargePackageList) {
//			rechargePackageMap.put(rechargePackage.getPackageId(), rechargePackage);
//		}
//		
//		return rechargePackageMap;
//	}
	
	/**
	 * 把流量包放入redis缓存
	 * @param jsonStr
	 */
	public static void setRechargePackageToCache(String jsonStr, String phone) {
		Map<String, String> rechargePackageMap = new HashMap<String, String>();
		
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("data");
		
		List<RechargePackage> rechargePackageList = JSONArray.parseArray(jsonArray.toJSONString(), RechargePackage.class);
		
		for(RechargePackage rechargePackage : rechargePackageList) {
			rechargePackageMap.put(rechargePackage.getPackageId() + "price", rechargePackage.getPrice().toString());
			rechargePackageMap.put(rechargePackage.getPackageId() + "salePrice", rechargePackage.getSalePrice().toString());
			if(rechargePackage.getPackageId().contains("HF500")) {
				rechargePackageMap.put(rechargePackage.getPackageId() + "salePrice", Double.toString(BigDecimalUtil.add(rechargePackage.getSalePrice(), 1.0)));
			}
		}
		rechargePackageMap.put("jsonStr", jsonStr);
		
		RedisCacheHandler.hmset(CacheConstant.KEY_RECHARGE_PACKAGE + phone, rechargePackageMap, 60*60*2); //cache 2个小时过期
//		return rechargePackageMap;
	}
	
	/**
	 * 拿相应的流量的价格
	 * @param key
	 * @return
	 */
	public static BigDecimal getRechargePrice(String phone, String packageId) {
		if(phone != null) {
//			String priceStr = "price";
//			if(packageId.contains("HF")) {
//				priceStr = "salePrice";
//			}
			String price = RedisCacheHandler.hget(CacheConstant.KEY_RECHARGE_PACKAGE + phone, packageId + "price");
			String salePrice = RedisCacheHandler.hget(CacheConstant.KEY_RECHARGE_PACKAGE + phone, packageId + "salePrice");
			try {
				if (price == null || price.trim().length() == 0) {
					throw new BusinessRuntimeException("EbuyController.rechargePackage.failed");
				}
				BigDecimal priceV = new BigDecimal(price);
				BigDecimal salePriceV = new BigDecimal(salePrice);
				if(packageId.contains("HF") && salePriceV.compareTo(priceV) <= 0) {
					return salePriceV;
				} else {
					return priceV;
				}
			} catch (RuntimeException e) {
				throw new BusinessRuntimeException("EbuyController.rechargePackage.failed");
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 拿相应的流量的价格
	 * @param key
	 * @return
	 */
	public static BigDecimal getRechargeSalePrice(String phone, String packageId) {
		String price = RedisCacheHandler.hget(CacheConstant.KEY_RECHARGE_PACKAGE + phone, packageId + "salePrice");
		try {
			if (price == null || price.trim().length() == 0) {
				throw new BusinessRuntimeException("EbuyController.rechargePackage.failed");
			}
			BigDecimal result = new BigDecimal(price);
			
			return result;
		} catch (RuntimeException e) {
			throw new BusinessRuntimeException("EbuyController.rechargePackage.failed");
		}
	}
	
//	/**
//	 * 从cache拿流量包的整个JSON串
//	 * @return
//	 */
//	public static String getRechargePackageJson(String phone) {
//		String jsonStr = RedisCacheHandler.hget(CacheConstant.KEY_RECHARGE_PACKAGE + phone, "jsonStr");
//		return jsonStr;
//	}
	
	

}
