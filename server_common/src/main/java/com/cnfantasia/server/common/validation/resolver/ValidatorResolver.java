package com.cnfantasia.server.common.validation.resolver;

import com.cnfantasia.server.common.validation.validator.Validator;

/**
 * 类名：ValidatorResolver  <br />
 *
 * 功能：
 */
public interface ValidatorResolver {

	/**
	 * 功能：校验器对象解析器 <br/>
	 *
	 */
	Validator resolve(String name) throws Exception;
}
