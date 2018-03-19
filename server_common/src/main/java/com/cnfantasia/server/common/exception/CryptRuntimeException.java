/**   
* Filename:    CryptRuntimeException.java   
* @version:    1.0  
* Create at:   2014年5月9日 上午9:15:58   
* Description:  加密异常
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.exception;

/**
 * Filename:    CryptRuntimeException.java
 * @version:    1.0.0
 * Create at:   2014年5月9日 上午9:15:58
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月9日       shiyl             1.0             1.0 Version
 */
public class CryptRuntimeException extends FocRuntimeException{

	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造
	 */
	public CryptRuntimeException() {
		super();
	}
	/**
	 * 错误代码
	 * @param errorCode
	 */
	public CryptRuntimeException(String errorCode) {
		super(errorCode);
	}
	/**
	 * 
	 * @param errorCode 错误代码
	 * @param paramArrayOfObject 错误信息参数
	 */
	public CryptRuntimeException(String errorCode,Object[] paramArrayOfObject) {
		super(errorCode,paramArrayOfObject);
	}

	/**
	 * 
	 * @param cause 异常对象
	 */
	public CryptRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param errorCode 错误码
	 * @param cause 异常对象
	 */
	public CryptRuntimeException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * 
	 * @param errorCode 错误码
	 * @param paramArrayOfObject 错误码参数
	 * @param cause 异常对象
	 */
	public CryptRuntimeException(String errorCode,Object[] paramArrayOfObject, Throwable cause) {
		super(errorCode,paramArrayOfObject,cause);
	}

}
