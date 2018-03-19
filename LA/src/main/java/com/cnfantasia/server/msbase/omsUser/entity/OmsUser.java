package com.cnfantasia.server.msbase.omsUser.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;

import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(OMS用户表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class OmsUser implements Serializable{
*/


public class OmsUser extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 用户编号 */	private BigInteger id;	/** 手机号 */	private String userAccount;	/** 密码 */	private String password;	/** 真实姓名 */	private String realName;	/** 性别 */	private String sex;	/** 出生年月 */	private String birthday;	/** 图像 */	private String imgprofile;	/** 用户状态 */	private Long userState;	/** 密码状态 */	private Integer passwdStatus;	/** 最近密码错误时间 */	private String lastPwderrTime;	/** 最近成功登录时间 */	private String lastLoginTime;	/** 最近修改密码时间 */	private String lastPwdmodTime;	/** 最近密码被重置时间 */	private String lastPwdResetTime;	/** 供应商Id */	private BigInteger merchantId;
	public OmsUser(){
	}
	/**	 * 	 * @param id 用户编号	 * @param userAccount 手机号	 * @param password 密码	 * @param realName 真实姓名	 * @param sex 性别	 * @param birthday 出生年月	 * @param imgprofile 图像	 * @param userState 用户状态	 * @param passwdStatus 密码状态	 * @param lastPwderrTime 最近密码错误时间	 * @param lastLoginTime 最近成功登录时间	 * @param lastPwdmodTime 最近修改密码时间	 * @param lastPwdResetTime 最近密码被重置时间	 * @param merchantId 供应商Id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public OmsUser(BigInteger id,String userAccount,String password,String realName,String sex,String birthday,String imgprofile,Long userState,Integer passwdStatus,String lastPwderrTime,String lastLoginTime,String lastPwdmodTime,String lastPwdResetTime,BigInteger merchantId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userAccount = userAccount;		this.password = password;		this.realName = realName;		this.sex = sex;		this.birthday = birthday;		this.imgprofile = imgprofile;		this.userState = userState;		this.passwdStatus = passwdStatus;		this.lastPwderrTime = lastPwderrTime;		this.lastLoginTime = lastLoginTime;		this.lastPwdmodTime = lastPwdmodTime;		this.lastPwdResetTime = lastPwdResetTime;		this.merchantId = merchantId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userAccount=").append(userAccount).append(";");		sbf.append("password=").append(password).append(";");		sbf.append("realName=").append(realName).append(";");		sbf.append("sex=").append(sex).append(";");		sbf.append("birthday=").append(birthday).append(";");		sbf.append("imgprofile=").append(imgprofile).append(";");		sbf.append("userState=").append(userState).append(";");		sbf.append("passwdStatus=").append(passwdStatus).append(";");		sbf.append("lastPwderrTime=").append(lastPwderrTime).append(";");		sbf.append("lastLoginTime=").append(lastLoginTime).append(";");		sbf.append("lastPwdmodTime=").append(lastPwdmodTime).append(";");		sbf.append("lastPwdResetTime=").append(lastPwdResetTime).append(";");		sbf.append("merchantId=").append(merchantId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getUserAccount() {		return userAccount;	}	public void setUserAccount(String userAccount) {		this.userAccount = userAccount;	}	public String getPassword() {		return password;	}	public void setPassword(String password) {		this.password = password;	}	public String getRealName() {		return realName;	}	public void setRealName(String realName) {		this.realName = realName;	}	public String getSex() {		return sex;	}	public void setSex(String sex) {		this.sex = sex;	}	public String getBirthday() {		return birthday;	}	public void setBirthday(String birthday) {		this.birthday = birthday;	}	public String getImgprofile() {		return imgprofile;	}	public void setImgprofile(String imgprofile) {		this.imgprofile = imgprofile;	}	public Long getUserState() {		return userState;	}	public void setUserState(Long userState) {		this.userState = userState;	}	public Integer getPasswdStatus() {		return passwdStatus;	}	public void setPasswdStatus(Integer passwdStatus) {		this.passwdStatus = passwdStatus;	}	public String getLastPwderrTime() {		return lastPwderrTime;	}	public void setLastPwderrTime(String lastPwderrTime) {		this.lastPwderrTime = lastPwderrTime;	}	public String getLastLoginTime() {		return lastLoginTime;	}	public void setLastLoginTime(String lastLoginTime) {		this.lastLoginTime = lastLoginTime;	}	public String getLastPwdmodTime() {		return lastPwdmodTime;	}	public void setLastPwdmodTime(String lastPwdmodTime) {		this.lastPwdmodTime = lastPwdmodTime;	}	public String getLastPwdResetTime() {		return lastPwdResetTime;	}	public void setLastPwdResetTime(String lastPwdResetTime) {		this.lastPwdResetTime = lastPwdResetTime;	}	public BigInteger getMerchantId() {		return merchantId;	}	public void setMerchantId(BigInteger merchantId) {		this.merchantId = merchantId;	}
	
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
		OmsUser other = (OmsUser) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
