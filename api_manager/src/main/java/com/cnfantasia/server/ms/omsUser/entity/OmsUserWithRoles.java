package com.cnfantasia.server.ms.omsUser.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
/**
 * 带角色名称的后台用户
 * @author wenfq 2017-02-12
 */
public class OmsUserWithRoles extends OmsUser{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 拥有的角色
	 */
	List<String> roleNames;
	
	/**
	 * 账号归属，例如：物业公司名称或管理处名称
	 */
	String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
}
