/**   
* Filename:    ValicodeParamConfig.java   
* @version:    1.0  
* Create at:   2014年5月26日 上午12:50:01   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.entity;

import java.io.Serializable;

/**
 * Filename:    ValicodeParamConfig.java
 * @version:    1.0.0
 * Create at:   2014年5月26日 上午12:50:01
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月26日       shiyl             1.0             1.0 Version
 */
public class ValicodeParamConfig implements Serializable{
	private static final long serialVersionUID = 1L;
	/**最长超时时间 */
	private long timeOut;
	/** 最大错误次数 */
	private int errCount;
	public ValicodeParamConfig(long timeOut,int errCount){
		this.timeOut = timeOut;
		this.errCount = errCount;
	}
	public long getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}
	public int getErrCount() {
		return errCount;
	}
	public void setErrCount(int errCount) {
		this.errCount = errCount;
	}
	
	
}
