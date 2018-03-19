package com.cnfantasia.server.common.exception;

import com.cnfantasia.server.common.CommConstants;


/**
 * 描述: 自定义异常
 * 
 * @version 1.00
 * @author syl
 * 
 */

public class FocException extends Exception implements IBaseException{

	private static final long serialVersionUID = 1L;

	/** 默认系统错误代码 */
	private static final String SYS_ERR_CODE = CommConstants.DEFAULT_SYS_ERRCODE;

	/** 错误代码 */
	private String errCode;
	/**错误信息参数*/
	private Object[] paramArrayOfObject;
	/**错误信息*/
	private String errorMsg;
	
	/**
	 * 默认构造
	 */
	public FocException() {
		this(SYS_ERR_CODE);
	}
	/**
	 * 错误代码
	 * @param errorCode
	 */
	public FocException(String errorCode) {
		super(errorCode);
		this.errCode = errorCode;
	}
	/**
	 * 
	 * @param errorCode 错误代码
	 * @param paramArrayOfObject 错误信息参数
	 */
	public FocException(String errorCode,Object[] paramArrayOfObject) {
		super(errorCode);
		this.errCode = errorCode;
		this.paramArrayOfObject = paramArrayOfObject;
	}

	/**
	 * 
	 * @param cause 异常对象
	 */
	public FocException(Throwable cause) {
		this(SYS_ERR_CODE, cause);
	}

	/**
	 * 
	 * @param errorCode 错误码
	 * @param cause 异常对象
	 */
	public FocException(String errorCode, Throwable cause) {
		super(errorCode, cause);
		this.errCode = errorCode;
	}

	/**
	 * 
	 * @param errorCode 错误码
	 * @param paramArrayOfObject 错误码参数
	 * @param cause 异常对象
	 */
	public FocException(String errorCode,Object[] paramArrayOfObject, Throwable cause) {
		super(errorCode, cause);
		this.errCode = errorCode;
		this.paramArrayOfObject = paramArrayOfObject;
	}
	
	@Override
	public String getErrCode() {
		return errCode;
	}
	
	@Override
	public Object[] getParamArrayOfObject() {
		return paramArrayOfObject;
	}
	
	@Override
	public String getErrorMsg() {
		return errorMsg;
	}
	@Override
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
