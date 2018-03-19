package com.cnfantasia.server.domainbase.inviteRewardConfig.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(邀请奖励配置表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class InviteRewardConfig implements Serializable{
*/


public class InviteRewardConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 邀请人ID */	private BigInteger tInviteUserFId;	/** 邀请码 */	private String inviteCode;	/** 邀请类型 */	private Integer inviteType;
	public InviteRewardConfig(){
	}
	public InviteRewardConfig(InviteRewardConfig arg){
		this.id = arg.getId();		this.tInviteUserFId = arg.gettInviteUserFId();		this.inviteCode = arg.getInviteCode();		this.inviteType = arg.getInviteType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tInviteUserFId 邀请人ID	 * @param inviteCode 邀请码	 * @param inviteType 邀请类型	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 修改时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public InviteRewardConfig(BigInteger id,BigInteger tInviteUserFId,String inviteCode,Integer inviteType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tInviteUserFId = tInviteUserFId;		this.inviteCode = inviteCode;		this.inviteType = inviteType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tInviteUserFId=").append(tInviteUserFId).append(";");		sbf.append("inviteCode=").append(inviteCode).append(";");		sbf.append("inviteType=").append(inviteType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettInviteUserFId() {		return tInviteUserFId;	}	public void settInviteUserFId(BigInteger tInviteUserFId) {		this.tInviteUserFId = tInviteUserFId;	}	public String getInviteCode() {		return inviteCode;	}	public void setInviteCode(String inviteCode) {		this.inviteCode = inviteCode;	}	public Integer getInviteType() {		return inviteType;	}	public void setInviteType(Integer inviteType) {		this.inviteType = inviteType;	}
	
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
		InviteRewardConfig other = (InviteRewardConfig) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
