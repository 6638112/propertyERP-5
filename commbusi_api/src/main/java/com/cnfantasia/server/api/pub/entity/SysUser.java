package com.cnfantasia.server.api.pub.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.login.entity.LoginNoEntity;

/**
 * 描述:系统用户表 //TODO 注意HashCode和equals方法
 * 
 * @version 1.00
 * @author syl
 * 
 */
public class SysUser implements Serializable{
	private static final long serialVersionUID = 1L;
	public SysUser(LoginNoEntity loginNoEntity){
		this.loginNoEntity = loginNoEntity;
		this.isAdmin = false;
	}
	
	/**登录信息存储*/
	private LoginNoEntity loginNoEntity;
	
	/** 是否为超级管理员标识 true.是 false.否 */
  private boolean isAdmin = false;
	/** 用户角色集合 */
  private List<BigInteger> roleIds;
	public LoginNoEntity getLoginNoEntity() {
		return loginNoEntity;
	}
	public void setLoginNoEntity(LoginNoEntity loginNoEntity) {
		this.loginNoEntity = loginNoEntity;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public List<BigInteger> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<BigInteger> roleIds) {
		this.roleIds = roleIds;
	}
	/**
	 * @return
	 */
	public String getUsername() {
		return loginNoEntity.getNo();
	}
	/**
	 * @return
	 */
	public String getPassword() {
		return loginNoEntity.getPassword();
	}
	
}
