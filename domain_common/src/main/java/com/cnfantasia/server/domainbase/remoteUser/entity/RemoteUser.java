package com.cnfantasia.server.domainbase.remoteUser.entity;

/* */ import java.io.Serializable;/* */
import java.lang.Long;import java.math.BigInteger;import java.lang.String;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(远程用户信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class RemoteUser implements Serializable{
/* */

/* 
public class RemoteUser extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/** 远程用户Id */	private Long uid;	/** 本系统用户Id */	private BigInteger tUserFId;	/** 远程用户名 */	private String userName;	/** 远程用户密码 */	private String password;	/** 远程用户邮箱 */	private String email;
	public RemoteUser(){
	}
	public RemoteUser(RemoteUser arg){
		this.uid = arg.getUid();		this.tUserFId = arg.gettUserFId();		this.userName = arg.getUserName();		this.password = arg.getPassword();		this.email = arg.getEmail();
	}
	/**	 * 	 * @param uid 远程用户Id	 * @param tUserFId 本系统用户Id	 * @param userName 远程用户名	 * @param password 远程用户密码	 * @param email 远程用户邮箱	 */
	public RemoteUser(Long uid,BigInteger tUserFId,String userName,String password,String email){
		this.uid = uid;		this.tUserFId = tUserFId;		this.userName = userName;		this.password = password;		this.email = email;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("uid=").append(uid).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("userName=").append(userName).append(";");		sbf.append("password=").append(password).append(";");		sbf.append("email=").append(email).append(";");		return sbf.toString();
	}
	
	public Long getUid() {		return uid;	}	public void setUid(Long uid) {		this.uid = uid;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public String getUserName() {		return userName;	}	public void setUserName(String userName) {		this.userName = userName;	}	public String getPassword() {		return password;	}	public void setPassword(String password) {		this.password = password;	}	public String getEmail() {		return email;	}	public void setEmail(String email) {		this.email = email;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
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
		RemoteUser other = (RemoteUser) obj;
		if (uid == null) {			if (other.uid != null)				return false;		} else if (!uid.equals(other.uid))			return false;
		return true;
	}
	
}
