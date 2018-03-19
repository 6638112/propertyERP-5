package com.cnfantasia.server.common.validation.resolver;

import java.util.Map;

import com.cnfantasia.server.common.validation.validator.Validator;

/**
 * 类名：MappingValidatorResolver  <br />
 *
 * 功能：基于名称映射关系的校验对象解析器
 */
public class MappingValidatorResolver implements ValidatorResolver {
	
	// 校验器
	private Map<String, Validator> mapping;
	
	/**
	 * 功能: <br/>
	 */
	//@Override
	public Validator resolve(String name) throws Exception {
		return mapping.get(name);
	}

	/**
	 */
	public void setMapping(Map<String, Validator> mapping) {
		this.mapping = mapping;
	}
}
