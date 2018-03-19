package com.cnfantasia.server.common.validation.validator;

/**
 * 类名：Validator  <br />
 *
 * 功能：校验器定义
 */
public interface Validator {

	/**
	 * 功能：执行校验，并返回校验后的值 <br/>
	 */
	//Object validate(String field, Object value, String message, Map<String, Object> config) throws Exception;
	Object validate(String field, Object value, String message, Object config) throws Exception;
}

