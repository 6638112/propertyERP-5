package com.cnfantasia.server.domainbase.easPushAccount.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(EAS推送账户信息配置表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EasPushAccount implements Serializable{
*/


public class EasPushAccount extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 所属最小粒度角色类型 */	private Integer miniRoleType;	/** 提款对象类型 */	private Integer goalType;	/** eas用户名称 */	private String easUserName;	/** EAS用户登录名 */	private String easUserAccount;	/** EAS登录用户密码 */	private String easUserPassword;
	public EasPushAccount(){
	}
	public EasPushAccount(EasPushAccount arg){
		this.id = arg.getId();		this.miniRoleType = arg.getMiniRoleType();		this.goalType = arg.getGoalType();		this.easUserName = arg.getEasUserName();		this.easUserAccount = arg.getEasUserAccount();		this.easUserPassword = arg.getEasUserPassword();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param miniRoleType 所属最小粒度角色类型	 * @param goalType 提款对象类型	 * @param easUserName eas用户名称	 * @param easUserAccount EAS用户登录名	 * @param easUserPassword EAS登录用户密码	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EasPushAccount(BigInteger id,Integer miniRoleType,Integer goalType,String easUserName,String easUserAccount,String easUserPassword,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.miniRoleType = miniRoleType;		this.goalType = goalType;		this.easUserName = easUserName;		this.easUserAccount = easUserAccount;		this.easUserPassword = easUserPassword;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("miniRoleType=").append(miniRoleType).append(";");		sbf.append("goalType=").append(goalType).append(";");		sbf.append("easUserName=").append(easUserName).append(";");		sbf.append("easUserAccount=").append(easUserAccount).append(";");		sbf.append("easUserPassword=").append(easUserPassword).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getMiniRoleType() {		return miniRoleType;	}	public void setMiniRoleType(Integer miniRoleType) {		this.miniRoleType = miniRoleType;	}	public Integer getGoalType() {		return goalType;	}	public void setGoalType(Integer goalType) {		this.goalType = goalType;	}	public String getEasUserName() {		return easUserName;	}	public void setEasUserName(String easUserName) {		this.easUserName = easUserName;	}	public String getEasUserAccount() {		return easUserAccount;	}	public void setEasUserAccount(String easUserAccount) {		this.easUserAccount = easUserAccount;	}	public String getEasUserPassword() {		return easUserPassword;	}	public void setEasUserPassword(String easUserPassword) {		this.easUserPassword = easUserPassword;	}
	
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
		EasPushAccount other = (EasPushAccount) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
