package com.cnfantasia.server.domainbase.comments.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Double;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(评论) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class Comments implements Serializable{
*/


public class Comments extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 用户Id */	private BigInteger userId;	/** 留言内容 */	private String content;	/** 留言时间 */	private String time;	/** 留言对象类型 */	private Integer targetType;	/** 目标内容Id */	private BigInteger targetId;	/** 2级目标内容Id */	private BigInteger targetId2nd;	/** 评论星级 */	private Double level;
	public Comments(){
	}
	public Comments(Comments arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.content = arg.getContent();		this.time = arg.getTime();		this.targetType = arg.getTargetType();		this.targetId = arg.getTargetId();		this.targetId2nd = arg.getTargetId2nd();		this.level = arg.getLevel();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 用户Id	 * @param content 留言内容	 * @param time 留言时间	 * @param targetType 留言对象类型	 * @param targetId 目标内容Id	 * @param targetId2nd 2级目标内容Id	 * @param level 评论星级	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public Comments(BigInteger id,BigInteger userId,String content,String time,Integer targetType,BigInteger targetId,BigInteger targetId2nd,Double level,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.content = content;		this.time = time;		this.targetType = targetType;		this.targetId = targetId;		this.targetId2nd = targetId2nd;		this.level = level;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("content=").append(content).append(";");		sbf.append("time=").append(time).append(";");		sbf.append("targetType=").append(targetType).append(";");		sbf.append("targetId=").append(targetId).append(";");		sbf.append("targetId2nd=").append(targetId2nd).append(";");		sbf.append("level=").append(level).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public String getTime() {		return time;	}	public void setTime(String time) {		this.time = time;	}	public Integer getTargetType() {		return targetType;	}	public void setTargetType(Integer targetType) {		this.targetType = targetType;	}	public BigInteger getTargetId() {		return targetId;	}	public void setTargetId(BigInteger targetId) {		this.targetId = targetId;	}	public BigInteger getTargetId2nd() {		return targetId2nd;	}	public void setTargetId2nd(BigInteger targetId2nd) {		this.targetId2nd = targetId2nd;	}	public Double getLevel() {		return level;	}	public void setLevel(Double level) {		this.level = level;	}
	
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
		Comments other = (Comments) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
