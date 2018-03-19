package com.cnfantasia.server.common.validation.validator;

import com.cnfantasia.server.common.exception.ValidationException;

/**
 * 类名：OptionValidator  <br />
 *
 * 功能：必输校验
 */
public class OptionValidator extends AbstractValidator {
	
	// 是否可选，默认为不可选
	private boolean option = false;
	
	/**
	 * 构造方法
	 */
	public OptionValidator() {
		super("validation.option.input");
	}

	/**
	 * 功能: <br/>
	 */
	@Override
	protected Object doValidate(String field, Object value, String message, Object config) throws Exception {
		if (null == value) {
			// 非空校验
			if (! (null != config ? Boolean.parseBoolean((String) config) : option)) {
				throw new ValidationException(null != message ? message : this.message, new Object[] { field });
			}
		}
		
		return value;
	}

	/**
	 */
	public boolean isOption() {
		return option;
	}

	/**
	 */
	public void setOption(boolean option) {
		this.option = option;
	}
}
