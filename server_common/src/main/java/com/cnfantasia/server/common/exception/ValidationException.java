package com.cnfantasia.server.common.exception;

/**
 * 类名：ValidationException  <br />
 *
 * 功能：校验异常
 *
 */
public class ValidationException extends FocException {

	private static final long serialVersionUID = -1486944705643060319L;
	private static final String DEFAULT_ERROR_CODE = "validate.error";
	/**
	 * 构造方法
	 */
	public ValidationException() {
		super(DEFAULT_ERROR_CODE);
	}

	/**
	 * 构造方法
	 */
	public ValidationException(String msg) {
		super(msg);
	}
	
	/**
	 * 构造方法
	 */
	public ValidationException(String msg, Object[] args) {
		super(msg, args);
	}
}
