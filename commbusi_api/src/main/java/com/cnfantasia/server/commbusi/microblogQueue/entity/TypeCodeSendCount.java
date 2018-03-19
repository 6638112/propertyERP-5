/**   
* Filename:    TypeCodeSendCount.java   
* @version:    1.0  
* Create at:   2015年9月1日 上午10:17:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.microblogQueue.entity;

/**
 * Filename:    TypeCodeSendCount.java
 * @version:    1.0.0
 * Create at:   2015年9月1日 上午10:17:27
 * Description:类别及发送次数实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月1日       shiyl             1.0             1.0 Version
 */
public class TypeCodeSendCount {
	/**编码*/
	private String code;
	/**已发送次数*/
	private Long sendCount;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getSendCount() {
		return sendCount;
	}
	public void setSendCount(Long sendCount) {
		this.sendCount = sendCount;
	}
	
	
}
