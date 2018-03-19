/**   
* Filename:    TimeOutRuntimeException.java   
* @version:    1.0  
* Create at:   2014年5月29日 上午6:41:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.exception;

import com.cnfantasia.server.common.CommConstants;

/**
 * Filename:    TimeOutRuntimeException.java
 * @version:    1.0.0
 * Create at:   2014年5月29日 上午6:41:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月29日       shiyl             1.0             1.0 Version
 */
public class TimeOutRuntimeException extends FocRuntimeException{

	private static final long serialVersionUID = 1L;
	/** 默认系统错误代码 */
	private static final String SYS_ERRCODE_TIME_OUT = CommConstants.DEFAULT_SYS_ERRCODE_TIME_OUT;
	/**
	 * 默认构造
	 */
	public TimeOutRuntimeException() {
		super(SYS_ERRCODE_TIME_OUT);
	}
	/**
	 * 错误代码
	 * @param errorCode
	 */
	public TimeOutRuntimeException(String errorCode) {
		super(errorCode);
	}
	/**
	 * 
	 * @param errorCode 错误代码
	 * @param paramArrayOfObject 错误信息参数
	 */
	public TimeOutRuntimeException(String errorCode,Object[] paramArrayOfObject) {
		super(errorCode,paramArrayOfObject);
	}

	/**
	 * 
	 * @param cause 异常对象
	 */
	public TimeOutRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param errorCode 错误码
	 * @param cause 异常对象
	 */
	public TimeOutRuntimeException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * 
	 * @param errorCode 错误码
	 * @param paramArrayOfObject 错误码参数
	 * @param cause 异常对象
	 */
	public TimeOutRuntimeException(String errorCode,Object[] paramArrayOfObject, Throwable cause) {
		super(errorCode,paramArrayOfObject,cause);
	}

}
