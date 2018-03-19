package com.cnfantasia.server.common.validation.validator;

import com.cnfantasia.server.common.exception.SystemException;
import com.cnfantasia.server.common.exception.ValidationException;

/**
 * 类名：PatternValidator  <br />
 *
 * 功能：按正则表达式进行匹配的校验器实现
 */
public class PatternValidator extends AbstractValidator {
	
	/**
	 * 构造方法
	 */
	public PatternValidator() {
		super("validation.error.pattern");
	}

	/**
	 * 功能: <br/>
	 * 
	 */
	@Override
	protected Object doValidate(String field, Object value, String message, Object config) throws Exception {
		if (null == config) {
			throw new SystemException("system.error.config.is.empty");
		}
		
		if (null != value) {
			if (! (value instanceof String)) {
				throw new SystemException("system.error.type.is.not.support", new Object[] { value.getClass().getName() });
			}
			
			if (! ((String) value).matches((String) config)) {
				throw new ValidationException(null != message ? message : this.message, new Object[] { field });
			}
		}
		
		return value;
	}
}
