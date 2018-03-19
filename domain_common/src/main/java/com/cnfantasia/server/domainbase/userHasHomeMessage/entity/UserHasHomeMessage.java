package com.cnfantasia.server.domainbase.userHasHomeMessage.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户首页消息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class UserHasHomeMessage implements Serializable{
*/


public class UserHasHomeMessage extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 用户ID */	private BigInteger userId;	/** 房间ID */	private BigInteger tRoomFId;	/** t_home_message_type 的code */	private String messageCode;	/** 消息内容 */	private String content;	/** 额外数据 */	private String extInfo;	/** 来源ID */	private BigInteger resouceId;	/** 有效时间 */	private String validTime;	/** 过期时间 */	private String expireTime;
	public UserHasHomeMessage(){
	}
	public UserHasHomeMessage(UserHasHomeMessage arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.tRoomFId = arg.gettRoomFId();		this.messageCode = arg.getMessageCode();		this.content = arg.getContent();		this.extInfo = arg.getExtInfo();		this.resouceId = arg.getResouceId();		this.validTime = arg.getValidTime();		this.expireTime = arg.getExpireTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 用户ID	 * @param tRoomFId 房间ID	 * @param messageCode t_home_message_type 的code	 * @param content 消息内容	 * @param extInfo 额外数据	 * @param resouceId 来源ID	 * @param validTime 有效时间	 * @param expireTime 过期时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public UserHasHomeMessage(BigInteger id,BigInteger userId,BigInteger tRoomFId,String messageCode,String content,String extInfo,BigInteger resouceId,String validTime,String expireTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.tRoomFId = tRoomFId;		this.messageCode = messageCode;		this.content = content;		this.extInfo = extInfo;		this.resouceId = resouceId;		this.validTime = validTime;		this.expireTime = expireTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("tRoomFId=").append(tRoomFId).append(";");		sbf.append("messageCode=").append(messageCode).append(";");		sbf.append("content=").append(content).append(";");		sbf.append("extInfo=").append(extInfo).append(";");		sbf.append("resouceId=").append(resouceId).append(";");		sbf.append("validTime=").append(validTime).append(";");		sbf.append("expireTime=").append(expireTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public BigInteger gettRoomFId() {		return tRoomFId;	}	public void settRoomFId(BigInteger tRoomFId) {		this.tRoomFId = tRoomFId;	}	public String getMessageCode() {		return messageCode;	}	public void setMessageCode(String messageCode) {		this.messageCode = messageCode;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public String getExtInfo() {		return extInfo;	}	public void setExtInfo(String extInfo) {		this.extInfo = extInfo;	}	public BigInteger getResouceId() {		return resouceId;	}	public void setResouceId(BigInteger resouceId) {		this.resouceId = resouceId;	}	public String getValidTime() {		return validTime;	}	public void setValidTime(String validTime) {		this.validTime = validTime;	}	public String getExpireTime() {		return expireTime;	}	public void setExpireTime(String expireTime) {		this.expireTime = expireTime;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserHasHomeMessage other = (UserHasHomeMessage) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
