/**   
* Filename:    BaiduMessageFormat.java   
* @version:    1.0  
* Create at:   2014年9月28日 上午10:19:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush.entity;

import java.io.Serializable;
import java.math.BigInteger;

import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;

/**
 * Filename:    BaiduMessageFormat.java
 * @version:    1.0.0
 * Create at:   2014年9月28日 上午10:19:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月28日       shiyl             1.0             1.0 Version
 */
public class BaiduMessageFormat implements Serializable{
	private static final long serialVersionUID = 1L;
	private String msg_keys;
	private String messageContent;
	private BigInteger userPushInfoId;
	private String baiduChannelId; 
	private String baiduUserId; 
	private String push_type;
	private String device_type; 
	private String message_type; 
	private String tag;
	private String deploy_status;//部署状态 1：开发状态 2：生产状态 
	//
	private UserIdType userIdType;
	private BigInteger messageId;
	private Long messageType;//消息类型
	private BigInteger userHasMessageId;//用户消息关系Id
	
	private BaiduMessageFormat(String msg_keys,String messageContent, String baiduChannelId, String baiduUserId, String push_type,
			String device_type, String message_type, String tag,UserIdType userIdType,BigInteger messageId){
		this.msg_keys = msg_keys;
		this.messageContent = messageContent;
		this.baiduChannelId = baiduChannelId;
		this.baiduUserId = baiduUserId;
		this.push_type = push_type;
		this.device_type = device_type;
		this.message_type = message_type;
		this.tag = tag;
		//
		this.userIdType = userIdType;
		this.messageId = messageId;
	}
	
	public BaiduMessageFormat(String msg_keys,String messageContent, String baiduChannelId, String baiduUserId, String push_type,
			String device_type, String message_type, String tag,UserIdType userIdType,BigInteger messageId,String deploy_status,Long messageType
			,BigInteger userHasMessageId){
		
		this(msg_keys, messageContent, baiduChannelId, baiduUserId, push_type, device_type, message_type, tag, userIdType, messageId);
		this.deploy_status = deploy_status;
		this.messageType = messageType;
		this.userHasMessageId = userHasMessageId;
	}


	public BigInteger getUserPushInfoId() {
		return userPushInfoId;
	}
	public void setUserPushInfoId(BigInteger userPushInfoId) {
		this.userPushInfoId = userPushInfoId;
	}
	public String getMsg_keys() {
		return msg_keys;
	}
	public void setMsg_keys(String msg_keys) {
		this.msg_keys = msg_keys;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getBaiduChannelId() {
		return baiduChannelId;
	}
	public void setBaiduChannelId(String baiduChannelId) {
		this.baiduChannelId = baiduChannelId;
	}
	public String getBaiduUserId() {
		return baiduUserId;
	}
	public void setBaiduUserId(String baiduUserId) {
		this.baiduUserId = baiduUserId;
	}
	public String getPush_type() {
		return push_type;
	}
	public void setPush_type(String push_type) {
		this.push_type = push_type;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	public UserIdType getUserIdType() {
		return userIdType;
	}
	public void setUserIdType(UserIdType userIdType) {
		this.userIdType = userIdType;
	}
	public BigInteger getMessageId() {
		return messageId;
	}
	public void setMessageId(BigInteger messageId) {
		this.messageId = messageId;
	}

	public String getDeploy_status() {
		return deploy_status;
	}

	public void setDeploy_status(String deploy_status) {
		this.deploy_status = deploy_status;
	}

	public Long getMessageType() {
		return messageType;
	}

	public void setMessageType(Long messageType) {
		this.messageType = messageType;
	}

	public BigInteger getUserHasMessageId() {
		return userHasMessageId;
	}

	public void setUserHasMessageId(BigInteger userHasMessageId) {
		this.userHasMessageId = userHasMessageId;
	}
	
	
}
