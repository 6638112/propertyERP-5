package com.cnfantasia.server.common.validation.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 类名：AbstractValidator  <br />
 *
 * 功能：基础校验器定义
 *
 */
public abstract class AbstractValidator implements Validator {
	
	// 日志记录器
	protected final Log logger = LogFactory.getLog(getClass());

	// 消息
	protected String message;

	/**
	 * 功能: <br/>
	 */
	//@Override
	public Object validate(String field, Object value, String message, Object config) throws Exception {
		return doValidate(field, value, message, config);
		/*try {
			return doValidate(field, value, message, config);
		} catch (ValidationException e) {
			// 在这里格式化字段参数
			
			Object[] args = e.getArgs();
			
			if (null != args) {
				for (int i = 0, len = args.length; i < len; i++) {
					//args[i] = MessageUtil.getMessage(args[i], null, locale, args[i]);
				}
			}
			
			throw e;
		}*/
	}
	
	/**
	 * 功能：由子类实现具体校验逻辑 <br/>
	 */
	protected abstract Object doValidate(String field, Object value, String message, Object config) throws Exception;
	
	/**
	 * 构造方法
	 */
	public AbstractValidator(String message) {
		this.message = message;
	}

	/**
	 */
	public String getMessage() {
		return message;
	}

	/**
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
