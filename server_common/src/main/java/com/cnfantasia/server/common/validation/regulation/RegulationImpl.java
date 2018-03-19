package com.cnfantasia.server.common.validation.regulation;

import java.util.Map;
import java.util.Map.Entry;

import com.cnfantasia.server.common.exception.SystemException;
import com.cnfantasia.server.common.validation.resolver.ValidatorResolver;
import com.cnfantasia.server.common.validation.validator.Validator;

/**
 * 类名：RegulationImpl  <br />
 *
 * 功能：规章实现
 */
public class RegulationImpl implements Regulation {
	
	// 校验器配置
	private Map<String, String> setting;
	
	// 校验消息
	private Map<String, String> message;
	
	// 校验器解析器
	private ValidatorResolver validatorResolver;

	/**
	 * 功能: <br/>
	 * 
	 */
	//@Override
	public Object regulate(String field, Object value, Map<String, String> config) throws Exception {
		
		// 依次处理每个校验器
		for (Entry<String, String> entry : setting.entrySet()) {
			
			String key = entry.getKey();
			
			String cfg = entry.getValue();
			if (null != config && config.containsKey(key)) {
				cfg = config.get(key);
			}
			
			Validator validator = resolveValidator(key);
			
			if (null == validator) {
				throw new SystemException("validator.resolve.result.is.null", new Object[] { key });
			}
			
			// 交给校验器处理
			value = validator.validate(field, value, null == message ? null : message.get(key), cfg);
		}
		
		return value;
	}
	
	/**
	 * 
	 */
	protected Validator resolveValidator(String name) throws Exception {
		return validatorResolver.resolve(name);
	}

	/**
	 */
	public Map<String, String> getSetting() {
		return setting;
	}

	/**
	 */
	public void setSetting(Map<String, String> setting) {
		this.setting = setting;
	}

	/**
	 */
	public Map<String, String> getMessage() {
		return message;
	}

	/**
	 */
	public void setMessage(Map<String, String> message) {
		this.message = message;
	}

	/**
	 */
	public ValidatorResolver getValidatorResolver() {
		return validatorResolver;
	}

	/**
	 */
	public void setValidatorResolver(ValidatorResolver validatorResolver) {
		this.validatorResolver = validatorResolver;
	}
}
