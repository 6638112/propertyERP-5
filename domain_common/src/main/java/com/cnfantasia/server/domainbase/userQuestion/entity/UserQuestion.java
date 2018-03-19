package com.cnfantasia.server.domainbase.userQuestion.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户提问信息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class UserQuestion implements Serializable{
*/


public class UserQuestion extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 疑问内容 */	private String content;	/** 图片地址，分号隔开 */	private String picUrl;	/** 类型 */	private Integer type;	/** 用户ID */	private BigInteger userId;	/** 房间ID */	private BigInteger tRoomFId;	/** 状态 */	private Integer status;	/** 回复内容 */	private String answerContent;	/** 公共及普通维修单ID */	private BigInteger serviceId;
	public UserQuestion(){
	}
	public UserQuestion(UserQuestion arg){
		this.id = arg.getId();		this.content = arg.getContent();		this.picUrl = arg.getPicUrl();		this.type = arg.getType();		this.userId = arg.getUserId();		this.tRoomFId = arg.gettRoomFId();		this.status = arg.getStatus();		this.answerContent = arg.getAnswerContent();		this.serviceId = arg.getServiceId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param content 疑问内容	 * @param picUrl 图片地址，分号隔开	 * @param type 类型	 * @param userId 用户ID	 * @param tRoomFId 房间ID	 * @param status 状态	 * @param answerContent 回复内容	 * @param serviceId 公共及普通维修单ID	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public UserQuestion(BigInteger id,String content,String picUrl,Integer type,BigInteger userId,BigInteger tRoomFId,Integer status,String answerContent,BigInteger serviceId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.content = content;		this.picUrl = picUrl;		this.type = type;		this.userId = userId;		this.tRoomFId = tRoomFId;		this.status = status;		this.answerContent = answerContent;		this.serviceId = serviceId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("content=").append(content).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("tRoomFId=").append(tRoomFId).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("answerContent=").append(answerContent).append(";");		sbf.append("serviceId=").append(serviceId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public BigInteger gettRoomFId() {		return tRoomFId;	}	public void settRoomFId(BigInteger tRoomFId) {		this.tRoomFId = tRoomFId;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public String getAnswerContent() {		return answerContent;	}	public void setAnswerContent(String answerContent) {		this.answerContent = answerContent;	}	public BigInteger getServiceId() {		return serviceId;	}	public void setServiceId(BigInteger serviceId) {		this.serviceId = serviceId;	}
	
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
		UserQuestion other = (UserQuestion) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
