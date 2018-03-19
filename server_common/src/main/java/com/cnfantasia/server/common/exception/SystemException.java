package com.cnfantasia.server.common.exception;

/**
 * 类名：SystemException  <br />
 *
 * 功能：系统异常
 *
 */
public class SystemException extends FocException {

	private static final long serialVersionUID = 8391529302436861031L;
	
	/**
	 * 构造方法
	 */
	public SystemException() {
	}

	/**
	 * 构造方法
	 */
	public SystemException(String msg) {
		super(msg);
	}
	
	/**
	 * 构造方法
	 */
	public SystemException(String msg, Object[] args) {
		super(msg, args);
	}
}
