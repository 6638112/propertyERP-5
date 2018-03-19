package com.cnfantasia.server.domainbase.familyMsg.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(家庭留言板) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class FamilyMsg implements Serializable{
*/


public class FamilyMsg extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 留言发布者 */	private BigInteger userId;	/** 虚拟门牌Id */	private BigInteger roomId;	/** 留言板内容 */	private String content;	/** 创建时间 */	private String createTime;	/** 真实门牌Id */	private BigInteger realRoomId;
	public FamilyMsg(){
	}
	public FamilyMsg(FamilyMsg arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.roomId = arg.getRoomId();		this.content = arg.getContent();		this.createTime = arg.getCreateTime();		this.realRoomId = arg.getRealRoomId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 留言发布者	 * @param roomId 虚拟门牌Id	 * @param content 留言板内容	 * @param createTime 创建时间	 * @param realRoomId 真实门牌Id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public FamilyMsg(BigInteger id,BigInteger userId,BigInteger roomId,String content,String createTime,BigInteger realRoomId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.roomId = roomId;		this.content = content;		this.createTime = createTime;		this.realRoomId = realRoomId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("roomId=").append(roomId).append(";");		sbf.append("content=").append(content).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("realRoomId=").append(realRoomId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public BigInteger getRoomId() {		return roomId;	}	public void setRoomId(BigInteger roomId) {		this.roomId = roomId;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public BigInteger getRealRoomId() {		return realRoomId;	}	public void setRealRoomId(BigInteger realRoomId) {		this.realRoomId = realRoomId;	}
	
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
		FamilyMsg other = (FamilyMsg) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
