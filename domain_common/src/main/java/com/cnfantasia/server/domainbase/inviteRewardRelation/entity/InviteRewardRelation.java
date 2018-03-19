package com.cnfantasia.server.domainbase.inviteRewardRelation.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(邀请奖励关系纪录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class InviteRewardRelation implements Serializable{
*/


public class InviteRewardRelation extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 邀请人ID，对应t_user表的f_id */	private BigInteger tInviteUserFId;	/** 注册新人ID，对应t_user表的f_id */	private BigInteger tRegisterUserFId;	/** 受邀注册关联时间 */	private String relationTime;
	public InviteRewardRelation(){
	}
	public InviteRewardRelation(InviteRewardRelation arg){
		this.id = arg.getId();		this.tInviteUserFId = arg.gettInviteUserFId();		this.tRegisterUserFId = arg.gettRegisterUserFId();		this.relationTime = arg.getRelationTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tInviteUserFId 邀请人ID，对应t_user表的f_id	 * @param tRegisterUserFId 注册新人ID，对应t_user表的f_id	 * @param relationTime 受邀注册关联时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 修改时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public InviteRewardRelation(BigInteger id,BigInteger tInviteUserFId,BigInteger tRegisterUserFId,String relationTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tInviteUserFId = tInviteUserFId;		this.tRegisterUserFId = tRegisterUserFId;		this.relationTime = relationTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tInviteUserFId=").append(tInviteUserFId).append(";");		sbf.append("tRegisterUserFId=").append(tRegisterUserFId).append(";");		sbf.append("relationTime=").append(relationTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettInviteUserFId() {		return tInviteUserFId;	}	public void settInviteUserFId(BigInteger tInviteUserFId) {		this.tInviteUserFId = tInviteUserFId;	}	public BigInteger gettRegisterUserFId() {		return tRegisterUserFId;	}	public void settRegisterUserFId(BigInteger tRegisterUserFId) {		this.tRegisterUserFId = tRegisterUserFId;	}	public String getRelationTime() {		return relationTime;	}	public void setRelationTime(String relationTime) {		this.relationTime = relationTime;	}
	
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
		InviteRewardRelation other = (InviteRewardRelation) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
