package com.cnfantasia.server.domainbase.userHasTMessage.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Long;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户消息关系) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class UserHasTMessage implements Serializable{
*/


public class UserHasTMessage extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 消息 */	private BigInteger tMessageFId;	/** 用户 */	private BigInteger tUserFId;	/** 用户类别 */	private Integer userType;	/** 消息发送状态 */	private Integer sendStatus;	/** 实现时间，为空则表示永不失效 */	private String expiryTime;	/** 消息状态 */	private Integer status;	/** 尝试失败的次数 */	private Long tryFailedCount;	/** 最后一次失败的错误信息 */	private String lastErrorMsg;	/** 发送成功返回的信息 */	private String lastSuccMsg;	/** 消息推送的内容 */	private String sendData;	/** 用户当前门牌Id */	private BigInteger userRoomId;	/** 推送时的推送配置ID */	private BigInteger tUserPushInfoFId;	/** 客户端标记收到消息（为1表示已收到） */	private Integer clientMarkReceived;	/** 第三方推送的消息id（小米，华为，个推） */	private String thrdMessageId;	/** 推送通道（XIAOMI,HUAWEI,GETUI） */	private String channel;
	public UserHasTMessage(){
	}
	public UserHasTMessage(UserHasTMessage arg){
		this.id = arg.getId();		this.tMessageFId = arg.gettMessageFId();		this.tUserFId = arg.gettUserFId();		this.userType = arg.getUserType();		this.sendStatus = arg.getSendStatus();		this.expiryTime = arg.getExpiryTime();		this.status = arg.getStatus();		this.tryFailedCount = arg.getTryFailedCount();		this.lastErrorMsg = arg.getLastErrorMsg();		this.lastSuccMsg = arg.getLastSuccMsg();		this.sendData = arg.getSendData();		this.userRoomId = arg.getUserRoomId();		this.tUserPushInfoFId = arg.gettUserPushInfoFId();		this.clientMarkReceived = arg.getClientMarkReceived();		this.thrdMessageId = arg.getThrdMessageId();		this.channel = arg.getChannel();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tMessageFId 消息	 * @param tUserFId 用户	 * @param userType 用户类别	 * @param sendStatus 消息发送状态	 * @param expiryTime 实现时间，为空则表示永不失效	 * @param status 消息状态	 * @param tryFailedCount 尝试失败的次数	 * @param lastErrorMsg 最后一次失败的错误信息	 * @param lastSuccMsg 发送成功返回的信息	 * @param sendData 消息推送的内容	 * @param userRoomId 用户当前门牌Id	 * @param tUserPushInfoFId 推送时的推送配置ID	 * @param clientMarkReceived 客户端标记收到消息（为1表示已收到）	 * @param thrdMessageId 第三方推送的消息id（小米，华为，个推）	 * @param channel 推送通道（XIAOMI,HUAWEI,GETUI）	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public UserHasTMessage(BigInteger id,BigInteger tMessageFId,BigInteger tUserFId,Integer userType,Integer sendStatus,String expiryTime,Integer status,Long tryFailedCount,String lastErrorMsg,String lastSuccMsg,String sendData,BigInteger userRoomId,BigInteger tUserPushInfoFId,Integer clientMarkReceived,String thrdMessageId,String channel,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tMessageFId = tMessageFId;		this.tUserFId = tUserFId;		this.userType = userType;		this.sendStatus = sendStatus;		this.expiryTime = expiryTime;		this.status = status;		this.tryFailedCount = tryFailedCount;		this.lastErrorMsg = lastErrorMsg;		this.lastSuccMsg = lastSuccMsg;		this.sendData = sendData;		this.userRoomId = userRoomId;		this.tUserPushInfoFId = tUserPushInfoFId;		this.clientMarkReceived = clientMarkReceived;		this.thrdMessageId = thrdMessageId;		this.channel = channel;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tMessageFId=").append(tMessageFId).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("userType=").append(userType).append(";");		sbf.append("sendStatus=").append(sendStatus).append(";");		sbf.append("expiryTime=").append(expiryTime).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("tryFailedCount=").append(tryFailedCount).append(";");		sbf.append("lastErrorMsg=").append(lastErrorMsg).append(";");		sbf.append("lastSuccMsg=").append(lastSuccMsg).append(";");		sbf.append("sendData=").append(sendData).append(";");		sbf.append("userRoomId=").append(userRoomId).append(";");		sbf.append("tUserPushInfoFId=").append(tUserPushInfoFId).append(";");		sbf.append("clientMarkReceived=").append(clientMarkReceived).append(";");		sbf.append("thrdMessageId=").append(thrdMessageId).append(";");		sbf.append("channel=").append(channel).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettMessageFId() {		return tMessageFId;	}	public void settMessageFId(BigInteger tMessageFId) {		this.tMessageFId = tMessageFId;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public Integer getUserType() {		return userType;	}	public void setUserType(Integer userType) {		this.userType = userType;	}	public Integer getSendStatus() {		return sendStatus;	}	public void setSendStatus(Integer sendStatus) {		this.sendStatus = sendStatus;	}	public String getExpiryTime() {		return expiryTime;	}	public void setExpiryTime(String expiryTime) {		this.expiryTime = expiryTime;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public Long getTryFailedCount() {		return tryFailedCount;	}	public void setTryFailedCount(Long tryFailedCount) {		this.tryFailedCount = tryFailedCount;	}	public String getLastErrorMsg() {		return lastErrorMsg;	}	public void setLastErrorMsg(String lastErrorMsg) {		this.lastErrorMsg = lastErrorMsg;	}	public String getLastSuccMsg() {		return lastSuccMsg;	}	public void setLastSuccMsg(String lastSuccMsg) {		this.lastSuccMsg = lastSuccMsg;	}	public String getSendData() {		return sendData;	}	public void setSendData(String sendData) {		this.sendData = sendData;	}	public BigInteger getUserRoomId() {		return userRoomId;	}	public void setUserRoomId(BigInteger userRoomId) {		this.userRoomId = userRoomId;	}	public BigInteger gettUserPushInfoFId() {		return tUserPushInfoFId;	}	public void settUserPushInfoFId(BigInteger tUserPushInfoFId) {		this.tUserPushInfoFId = tUserPushInfoFId;	}	public Integer getClientMarkReceived() {		return clientMarkReceived;	}	public void setClientMarkReceived(Integer clientMarkReceived) {		this.clientMarkReceived = clientMarkReceived;	}	public String getThrdMessageId() {		return thrdMessageId;	}	public void setThrdMessageId(String thrdMessageId) {		this.thrdMessageId = thrdMessageId;	}	public String getChannel() {		return channel;	}	public void setChannel(String channel) {		this.channel = channel;	}
	
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
		UserHasTMessage other = (UserHasTMessage) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
