/**   
* Filename:    MessagepushEntity.java   
* @version:    1.0  
* Create at:   2014年9月22日 上午10:34:12   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush.entity;

import java.io.Serializable;
import java.math.BigInteger;

import com.cnfantasia.server.api.notice.entity.MessageEntity;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

/**
 * Filename:    MessagepushEntity.java
 * @version:    1.0.0
 * Create at:   2014年9月22日 上午10:34:12
 * Description:消息推送实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月22日       shiyl             1.0             1.0 Version
 */
public class MessagepushEntity extends AbstractMessagePushBaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String msg_keys; 
	//private String title; 
	//private  String description; 
	//private  String baiduChannelId; 
	//private  String baiduUserId; 
	//private  String push_type; 
	//private  String device_type; 
	//private  String message_type; 
	//private  String tag;

	private String thrdMessageId;//第三方消息id
	private String sendStatusStr;//消息发送状态串
	private Boolean succeedStatus;//发送是否成功
	private String channelId;//客户端cid,regid..

	/**
	 * 用户消息状态信息
	 */
	private UserHasTMessage userHasTMessage;
	
	private String mobile;
	
	public MessagepushEntity(){}
	
	/**
	 * 构造方法
	 * @param userPushInfo
	 * @param userHasTMessage
	 * @param message
	 */
	public MessagepushEntity(UserPushInfo userPushInfo,UserHasTMessage userHasTMessage,MessageEntity message){
		super(userPushInfo, message);
		this.userHasTMessage = userHasTMessage;
	}
	
	public UserHasTMessage getUserHasTMessage() {
		return userHasTMessage;
	}
	public void setUserHasTMessage(UserHasTMessage userHasTMessage) {
		this.userHasTMessage = userHasTMessage;
	}
	

	@Override
	public Integer fetchSendStatus() {
		if(userHasTMessage!=null){
			return userHasTMessage.getSendStatus();
		}
		return null;
	}
	@Override
	public BigInteger fetchUserMsgRelaId() {
		if(userHasTMessage!=null){
			return userHasTMessage.getId();
		}
		return null;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getThrdMessageId() {
		return thrdMessageId;
	}

	public void setThrdMessageId(String thrdMessageId) {
		this.thrdMessageId = thrdMessageId;
	}

	public String getSendStatusStr() {
		return sendStatusStr;
	}

	public void setSendStatusStr(String sendStatusStr) {
		this.sendStatusStr = sendStatusStr;
	}

	public Boolean getSucceedStatus() {
		return succeedStatus;
	}

	public void setSucceedStatus(Boolean succeedStatus) {
		this.succeedStatus = succeedStatus;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
}
