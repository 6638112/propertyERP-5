/**   
* Filename:    BusinessRuntimeException.java   
* @version:    1.0  
* Create at:   2014年5月9日 上午8:45:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.exception;


/**
 * Filename:    BusinessRuntimeException.java
 * @version:    1.0.0
 * Create at:   2014年5月9日 上午8:45:22
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月9日       shiyl             1.0             1.0 Version
 */
public class BusinessRuntimeException extends FocRuntimeException{

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_ERROR_CODE = "business.error";
	/**
	 * 默认构造
	 */
	public BusinessRuntimeException() {
		super(DEFAULT_ERROR_CODE);
	}
	/**
	 * 错误代码
	 * @param errorCode
	 */
	public BusinessRuntimeException(String errorCode) {
		super(errorCode);
	}
	/**
	 * 
	 * @param errorCode 错误代码
	 * @param paramArrayOfObject 错误信息参数
	 */
	public BusinessRuntimeException(String errorCode,Object[] paramArrayOfObject) {
		super(errorCode,paramArrayOfObject);
	}

	/**
	 * 
	 * @param cause 异常对象
	 */
	public BusinessRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param errorCode 错误码
	 * @param cause 异常对象
	 */
	public BusinessRuntimeException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * 
	 * @param errorCode 错误码
	 * @param paramArrayOfObject 错误码参数
	 * @param cause 异常对象
	 */
	public BusinessRuntimeException(String errorCode,Object[] paramArrayOfObject, Throwable cause) {
		super(errorCode,paramArrayOfObject,cause);
	}

}
