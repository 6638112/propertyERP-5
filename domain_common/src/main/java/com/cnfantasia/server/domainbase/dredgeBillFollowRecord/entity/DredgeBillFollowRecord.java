package com.cnfantasia.server.domainbase.dredgeBillFollowRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(维修订单跟进记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeBillFollowRecord implements Serializable{
*/


public class DredgeBillFollowRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 维修单ID */	private BigInteger tDredgeBillFId;	/** 跟进内容 */	private String content;	/** 跟进时间时间 */	private String followTime;	/** 跟进人（bi.jiefangqu.com平台账号） */	private String followUser;
	public DredgeBillFollowRecord(){
	}
	public DredgeBillFollowRecord(DredgeBillFollowRecord arg){
		this.id = arg.getId();		this.tDredgeBillFId = arg.gettDredgeBillFId();		this.content = arg.getContent();		this.followTime = arg.getFollowTime();		this.followUser = arg.getFollowUser();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tDredgeBillFId 维修单ID	 * @param content 跟进内容	 * @param followTime 跟进时间时间	 * @param followUser 跟进人（bi.jiefangqu.com平台账号）	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public DredgeBillFollowRecord(BigInteger id,BigInteger tDredgeBillFId,String content,String followTime,String followUser,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tDredgeBillFId = tDredgeBillFId;		this.content = content;		this.followTime = followTime;		this.followUser = followUser;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tDredgeBillFId=").append(tDredgeBillFId).append(";");		sbf.append("content=").append(content).append(";");		sbf.append("followTime=").append(followTime).append(";");		sbf.append("followUser=").append(followUser).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettDredgeBillFId() {		return tDredgeBillFId;	}	public void settDredgeBillFId(BigInteger tDredgeBillFId) {		this.tDredgeBillFId = tDredgeBillFId;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public String getFollowTime() {		return followTime;	}	public void setFollowTime(String followTime) {		this.followTime = followTime;	}	public String getFollowUser() {		return followUser;	}	public void setFollowUser(String followUser) {		this.followUser = followUser;	}
	
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
		DredgeBillFollowRecord other = (DredgeBillFollowRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
