/**   
* Filename:    ErrorInfoEntity.java   
* @version:    1.0  
* Create at:   2014年7月30日 上午9:59:49   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.exception.entity;

/**
 * Filename:    ErrorInfoEntity.java
 * @version:    1.0.0
 * Create at:   2014年7月30日 上午9:59:49
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月30日       shiyl             1.0             1.0 Version
 */
public class ErrorInfoEntity {
	/** 错误码 */
	private String errorNum;
	/** 错误信息Key*/
	private String errorMsgKey;
	
	public ErrorInfoEntity(String errorNum,String errorMsgKey){
		this.errorNum = errorNum;
		this.errorMsgKey = errorMsgKey;
	}
	
	public String getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(String errorNum) {
		this.errorNum = errorNum;
	}
	public String getErrorMsgKey() {
		return errorMsgKey;
	}
	public void setErrorMsgKey(String errorMsgKey) {
		this.errorMsgKey = errorMsgKey;
	}
	
	
}
