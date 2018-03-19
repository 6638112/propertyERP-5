package com.cnfantasia.server.business.pub.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 存储校验信息的上下文对象
 * @author shiyl
 *
 */
public class ValidationContext {
	private static Log logger = LogFactory.getLog(ValidationContext.class);
	private static Map<String,Map<String,Object>> validateMap = new HashMap<String, Map<String,Object>>();;
	
	private ValidationContext(List<ValidateEntity> validateList){
//		validateMap = new HashMap<String, Map<String,Object>>();
		for(ValidateEntity entity:validateList){
			validateMap.put(entity.getUrl(), entity.getRegulations());
		}
		logger.info("ValidationContext init success.");
	}
	
	public static Map<String,Object> getRegulations(String url) {
		return validateMap.get(url);
	}
	public static boolean checkValidate(String url){
		return validateMap.containsKey(url);
	}
	
}
