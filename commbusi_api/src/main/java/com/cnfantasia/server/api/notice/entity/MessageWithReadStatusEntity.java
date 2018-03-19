/**   
* Filename:    MessageWithReadStatusEntity.java   
* @version:    1.0  
* Create at:   2015年4月21日 上午7:28:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月21日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.notice.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.message.entity.Message;

/**
 * Filename:    MessageWithReadStatusEntity.java
 * @version:    1.0.0
 * Create at:   2015年4月21日 上午7:28:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月21日       shiyl             1.0             1.0 Version
 */
public class MessageWithReadStatusEntity extends Message{
	private static final long serialVersionUID = 1L;
	
	private Integer readStatus;
	public Integer getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	/**
	 * 消息状态=={"0":"未读","1":"已读"}
	 * @return
	 */
	public Integer getReadStatus_Ext() {
		return readStatus==null?1:readStatus;
	}
	
	public BigInteger getMessageId_Ext(){
		return super.getId();
	}
}
