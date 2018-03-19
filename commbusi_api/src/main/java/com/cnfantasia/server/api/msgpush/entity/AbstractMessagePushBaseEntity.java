/**   
* Filename:    MessagePushBaseEntity.java   
* @version:    1.0  
* Create at:   2014年9月29日 上午9:08:14   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.notice.entity.MessageEntity;
import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

/**
 * Filename:    MessagePushBaseEntity.java
 * @version:    1.0.0
 * Create at:   2014年9月29日 上午9:08:14
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月29日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractMessagePushBaseEntity{
	
	public abstract Integer fetchSendStatus();
	public abstract BigInteger fetchUserMsgRelaId();
	
	/**
	 * 消息接收对象
	 */
	private UserPushInfo userPushInfo;
	/**
	 * 消息内容
	 */
	private MessageEntity message;
	
	public AbstractMessagePushBaseEntity(){}
	
	/**
	 * 构造方法
	 * @param userPushInfo
	 * @param userHasTMessage
	 * @param message
	 */
	public AbstractMessagePushBaseEntity(UserPushInfo userPushInfo,MessageEntity message){
		this.userPushInfo = userPushInfo;
		this.message = message;
	}
	
	public UserPushInfo getUserPushInfo() {
		return userPushInfo;
	}
	public void setUserPushInfo(UserPushInfo userPushInfo) {
		this.userPushInfo = userPushInfo;
	}

	public MessageEntity getMessage() {
		return message;
	}

	public void setMessage(MessageEntity message) {
		this.message = message;
	}
	
	
	
}
