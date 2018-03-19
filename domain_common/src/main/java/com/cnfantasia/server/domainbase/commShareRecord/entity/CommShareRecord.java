package com.cnfantasia.server.domainbase.commShareRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(分享记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommShareRecord implements Serializable{
*/


public class CommShareRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 用户Id */	private BigInteger userId;	/** 门牌Id */	private BigInteger roomId;	/** 分享时间 */	private String time;	/** 分享类型 */	private Integer type;	/** 分享的标题 */	private String title;	/** 分享的内容 */	private String content;
	public CommShareRecord(){
	}
	public CommShareRecord(CommShareRecord arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.roomId = arg.getRoomId();		this.time = arg.getTime();		this.type = arg.getType();		this.title = arg.getTitle();		this.content = arg.getContent();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 用户Id	 * @param roomId 门牌Id	 * @param time 分享时间	 * @param type 分享类型	 * @param title 分享的标题	 * @param content 分享的内容	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommShareRecord(BigInteger id,BigInteger userId,BigInteger roomId,String time,Integer type,String title,String content,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.roomId = roomId;		this.time = time;		this.type = type;		this.title = title;		this.content = content;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("roomId=").append(roomId).append(";");		sbf.append("time=").append(time).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("title=").append(title).append(";");		sbf.append("content=").append(content).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public BigInteger getRoomId() {		return roomId;	}	public void setRoomId(BigInteger roomId) {		this.roomId = roomId;	}	public String getTime() {		return time;	}	public void setTime(String time) {		this.time = time;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public String getTitle() {		return title;	}	public void setTitle(String title) {		this.title = title;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}
	
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
		CommShareRecord other = (CommShareRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
