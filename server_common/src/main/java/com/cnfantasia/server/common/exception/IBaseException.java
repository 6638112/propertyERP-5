/**   
* Filename:    IBaseException.java   
* @version:    1.0  
* Create at:   2014年8月5日 上午1:02:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.exception;

/**
 * Filename:    IBaseException.java
 * @version:    1.0.0
 * Create at:   2014年8月5日 上午1:02:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月5日       shiyl             1.0             1.0 Version
 */
public interface IBaseException {
	
	public String getErrCode();

	public Object[] getParamArrayOfObject();
	
	public String getErrorMsg();

	public void setErrorMsg(String errorMsg);
}
